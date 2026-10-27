package com.evaluacion.springboot.application.usecases;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.domain.ports.AntifraudService;
import com.evaluacion.springboot.domain.ports.AntifraudService.AntifraudResult;
import com.evaluacion.springboot.domain.ports.CreditRequestRepository;
import com.evaluacion.springboot.domain.ports.RiskBureauService;
import com.evaluacion.springboot.domain.ports.RiskBureauService.RiskBureauResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CreateCreditRequestUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateCreditRequestUseCase.class);

    private final CreditRequestRepository creditRequestRepository;
    private final AntifraudService antifraudService;
    private final RiskBureauService riskBureauService;

    public CreateCreditRequestUseCase(
            CreditRequestRepository creditRequestRepository,
            AntifraudService antifraudService,
            RiskBureauService riskBureauService) {
        this.creditRequestRepository = creditRequestRepository;
        this.antifraudService = antifraudService;
        this.riskBureauService = riskBureauService;
    }

    @Transactional
    public CreditRequest execute(CreditRequest creditRequest) {
        log.info("Iniciando procesamiento de solicitud de crédito: operationNumber={}, channel={}",
                creditRequest.operationNumber(), creditRequest.channel());

        if (creditRequestRepository.existsByOperationNumberAndChannel(
                creditRequest.operationNumber(), creditRequest.channel())) {
            log.warn("Solicitud duplicada detectada: operationNumber={}, channel={}",
                    creditRequest.operationNumber(), creditRequest.channel());
            return creditRequestRepository
                    .findByOperationNumberAndChannel(
                            creditRequest.operationNumber(), creditRequest.channel())
                    .orElseThrow(() -> new IllegalStateException(
                            "Inconsistencia: existe pero no se puede recuperar"));
        }

        CreditRequest pendingRequest = creditRequest.withStatus(
                CreditRequestStatus.PENDING,
                "Solicitud creada y en espera de evaluación");
        CreditRequest savedRequest = creditRequestRepository.save(pendingRequest);
        log.info("Solicitud guardada con status PENDING: id={}", savedRequest.id());

        try {
            CompletableFuture<AntifraudResult> antifraudFuture =
                    antifraudService.evaluate(savedRequest);
            CompletableFuture<RiskBureauResult> riskBureauFuture =
                    riskBureauService.evaluate(savedRequest);

            CompletableFuture<Void> allFutures =
                    CompletableFuture.allOf(antifraudFuture, riskBureauFuture);

            allFutures.join();

            AntifraudResult antifraudResult = antifraudFuture.get();
            RiskBureauResult riskBureauResult = riskBureauFuture.get();

            CreditRequestStatus finalStatus = determineFinalStatus(antifraudResult, riskBureauResult);
            String finalReason = buildFinalReason(antifraudResult, riskBureauResult);

            CreditRequest evaluatedRequest = savedRequest.withStatus(finalStatus, finalReason);
            CreditRequest finalRequest = creditRequestRepository.save(evaluatedRequest);

            log.info("Solicitud procesada completamente: id={}, status={}",
                    finalRequest.id(), finalRequest.status());

            return finalRequest;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Proceso de evaluación interrumpido para solicitud: id={}", savedRequest.id(), e);
            CreditRequest errorRequest = savedRequest.withStatus(
                    CreditRequestStatus.ERROR,
                    "Proceso interrumpido durante evaluación");
            return creditRequestRepository.save(errorRequest);
        } catch (ExecutionException e) {
            log.error("Error en evaluación de servicios para solicitud: id={}", savedRequest.id(), e);
            CreditRequest errorRequest = savedRequest.withStatus(
                    CreditRequestStatus.ERROR,
                    "Error en evaluación: " + e.getCause().getMessage());
            return creditRequestRepository.save(errorRequest);
        }
    }

    private CreditRequestStatus determineFinalStatus(
            AntifraudResult antifraudResult, RiskBureauResult riskBureauResult) {

        if (!antifraudResult.approved()) {
            return CreditRequestStatus.REJECTED;
        }
        if (!riskBureauResult.approved()) {
            return CreditRequestStatus.REJECTED;
        }
        if (riskBureauResult.riskLevel() > 70) {
            return CreditRequestStatus.REVIEW_REQUIRED;
        }
        return CreditRequestStatus.APPROVED;
    }

    private String buildFinalReason(AntifraudResult antifraudResult, RiskBureauResult riskBureauResult) {
        StringBuilder reason = new StringBuilder();

        if (!antifraudResult.approved()) {
            reason.append("Rechazada por antifraude: ").append(antifraudResult.reason());
        } else if (!riskBureauResult.approved()) {
            reason.append("Rechazada por buró de riesgos: ").append(riskBureauResult.reason());
        } else if (riskBureauResult.riskLevel() > 70) {
            reason.append("Requiere revisión manual. Nivel de riesgo: ")
                    .append(riskBureauResult.riskLevel());
        } else {
            reason.append("Aprobada automáticamente. Score bureau: ")
                    .append(riskBureauResult.score());
        }

        return reason.toString();
    }
}