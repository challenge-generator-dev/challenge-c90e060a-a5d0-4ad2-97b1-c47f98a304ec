package com.evaluacion.springboot.infrastructure.adapters;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.ports.RiskBureauService;
import com.evaluacion.springboot.domain.ports.RiskBureauService.RiskBureauResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class RiskBureauServiceAdapter implements RiskBureauService {

    private static final Logger log = LoggerFactory.getLogger(RiskBureauServiceAdapter.class);

    private final ExecutorService executorService;

    @Value("${external.services.riskbureau.timeout-ms:8000}")
    private int timeoutMs;

    @Value("${external.services.riskbureau.enabled:true}")
    private boolean enabled;

    @Value("${external.services.riskbureau.default-risk-threshold:70}")
    private int defaultRiskThreshold;

    public RiskBureauServiceAdapter() {
        this.executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public CompletableFuture<RiskBureauResult> evaluate(CreditRequest creditRequest) {
        log.debug("Evaluando solicitud en buró de riesgos: requestId={}, document={}",
                creditRequest.id(), creditRequest.clientDocumentNumber());

        if (!enabled) {
            log.warn("Buró de riesgos deshabilitado, retornando resultado por defecto");
            return CompletableFuture.completedFuture(
                    new RiskBureauResult(true, 50, 650, "SERVICIO_DESHABILITADO",
                            "Aprobado por defecto"));
        }

        return CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(150 + (long) (Math.random() * 300));

                int riskLevel = calculateRiskLevel(creditRequest);
                int score = calculateCreditScore(creditRequest);
                boolean approved = riskLevel <= defaultRiskThreshold && score >= 500;
                String reason = buildRiskReason(riskLevel, score);

                log.info("Evaluación de riesgo completada: requestId={}, riskLevel={}, score={}, approved={}",
                        creditRequest.id(), riskLevel, score, approved);

                return new RiskBureauResult(approved, riskLevel, score,
                        riskLevel > defaultRiskThreshold ? "HIGH_RISK" : "OK",
                        reason);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.error("Evaluación de riesgo interrumpida: requestId={}", creditRequest.id(), e);
                return new RiskBureauResult(false, 100, 0, "INTERRUPTED",
                        "Evaluación interrumpida");
            }
        }, executorService);
    }

    private int calculateRiskLevel(CreditRequest creditRequest) {
        int baseRisk = 20;

        String documentNumber = creditRequest.clientDocumentNumber();
        if (documentNumber != null) {
            if (documentNumber.length() < 8) {
                baseRisk += 15;
            }
            char lastDigit = documentNumber.charAt(documentNumber.length() - 1);
            if (Character.getNumericValue(lastDigit) == 0 ||
                    Character.getNumericValue(lastDigit) == 1) {
                baseRisk += 10;
            }
        }

        if (creditRequest.amount() != null) {
            if (creditRequest.amount().compareTo(new java.math.BigDecimal("50000")) > 0) {
                baseRisk += 20;
            } else if (creditRequest.amount().compareTo(new java.math.BigDecimal("10000")) > 0) {
                baseRisk += 10;
            }
        }

        String channel = creditRequest.channel();
        if (channel != null && channel.equalsIgnoreCase("API")) {
            baseRisk += 5;
        }

        return Math.min(baseRisk, 100);
    }

    private int calculateCreditScore(CreditRequest creditRequest) {
        int baseScore = 600;

        if (creditRequest.amount() != null) {
            if (creditRequest.amount().compareTo(new java.math.BigDecimal("5000")) < 0) {
                baseScore += 50;
            } else if (creditRequest.amount().compareTo(new java.math.BigDecimal("30000")) > 0) {
                baseScore -= 30;
            }
        }

        String channel = creditRequest.channel();
        if (channel != null && channel.equalsIgnoreCase("BRANCH")) {
            baseScore += 20;
        }

        return Math.max(baseScore, 300);
    }

    private String buildRiskReason(int riskLevel, int score) {
        if (riskLevel <= defaultRiskThreshold && score >= 500) {
            return String.format("Riesgo aceptable (nivel: %d, score: %d)", riskLevel, score);
        }

        StringBuilder reason = new StringBuilder();
        if (riskLevel > defaultRiskThreshold) {
            reason.append(String.format("Nivel de riesgo excesivo: %d (umbral: %d). ",
                    riskLevel, defaultRiskThreshold));
        }
        if (score < 500) {
            reason.append(String.format("Score crediticio insuficiente: %d (mínimo: 500). ", score));
        }

        return reason.toString().trim();
    }
}