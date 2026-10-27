package com.evaluacion.springboot.infrastructure.adapters;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.ports.AntifraudService;
import com.evaluacion.springboot.domain.ports.AntifraudService.AntifraudResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class AntifraudServiceAdapter implements AntifraudService {

    private static final Logger log = LoggerFactory.getLogger(AntifraudServiceAdapter.class);

    private final ExecutorService executorService;

    @Value("${external.services.antifraud.timeout-ms:5000}")
    private int timeoutMs;

    @Value("${external.services.antifraud.enabled:true}")
    private boolean enabled;

    public AntifraudServiceAdapter() {
        this.executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public CompletableFuture<AntifraudResult> evaluate(CreditRequest creditRequest) {
        log.debug("Evaluando solicitud en servicio antifraude: requestId={}, amount={}",
                creditRequest.id(), creditRequest.amount());

        if (!enabled) {
            log.warn("Servicio de antifraude deshabilitado, retornando aprobación por defecto");
            return CompletableFuture.completedFuture(
                    new AntifraudResult(true, "SERVICIO_DESHABILITADO", "Aprobado por defecto"));
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100 + (long) (Math.random() * 200));

                boolean hasFraudHistory = evaluateFraudHistory(creditRequest);
                boolean hasSuspiciousPattern = evaluateSuspiciousPattern(creditRequest);
                boolean amountWithinLimits = evaluateAmountLimits(creditRequest);

                boolean approved = !hasFraudHistory && !hasSuspiciousPattern && amountWithinLimits;
                String reason = buildEvaluationReason(hasFraudHistory, hasSuspiciousPattern, amountWithinLimits);

                log.info("Evaluación antifraude completada: requestId={}, approved={}, reason={}",
                        creditRequest.id(), approved, reason);

                return new AntifraudResult(approved,
                        hasFraudHistory ? "FRAUD_HISTORY" :
                                hasSuspiciousPattern ? "SUSPICIOUS_PATTERN" : "OK",
                        reason);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Evaluación antifraude interrumpida: requestId={}", creditRequest.id(), e);
                return new AntifraudResult(false, "INTERRUPTED",
                        "Evaluación interrumpida");
            }
        }, executorService);
    }

    private boolean evaluateFraudHistory(CreditRequest creditRequest) {
        String documentNumber = creditRequest.clientDocumentNumber();
        if (documentNumber != null && documentNumber.startsWith("000")) {
            log.debug("Cliente con historial fraudulento detectado: document={}", documentNumber);
            return true;
        }
        return false;
    }

    private boolean evaluateSuspiciousPattern(CreditRequest creditRequest) {
        if (creditRequest.operationNumber() != null &&
                creditRequest.operationNumber().contains("TEST")) {
            log.debug("Patrón sospechoso detectado en número de operación: {}",
                    creditRequest.operationNumber());
            return true;
        }
        return false;
    }

    private boolean evaluateAmountLimits(CreditRequest creditRequest) {
        if (creditRequest.amount() == null) {
            return false;
        }
        if (creditRequest.amount().compareTo(java.math.BigDecimal.ZERO) <= 0) {
            return false;
        }
        if (creditRequest.amount().compareTo(new java.math.BigDecimal("100000")) > 0) {
            log.debug("Monto excede límite máximo: amount={}", creditRequest.amount());
            return false;
        }
        return true;
    }

    private String buildEvaluationReason(boolean hasFraudHistory,
            boolean hasSuspiciousPattern, boolean amountWithinLimits) {
        if (!hasFraudHistory && !hasSuspiciousPattern && amountWithinLimits) {
            return "Todas las verificaciones pasaron satisfactoriamente";
        }

        StringBuilder reason = new StringBuilder();
        if (hasFraudHistory) {
            reason.append("Cliente con historial fraudulento. ");
        }
        if (hasSuspiciousPattern) {
            reason.append("Patrón de operación sospechoso. ");
        }
        if (!amountWithinLimits) {
            reason.append("Monto fuera de límites permitidos. ");
        }

        return reason.toString().trim();
    }
}