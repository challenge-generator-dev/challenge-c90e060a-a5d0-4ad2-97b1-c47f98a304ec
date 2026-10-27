package com.evaluacion.springboot.domain.ports;

import com.evaluacion.springboot.domain.model.CreditRequest;
import java.util.concurrent.CompletableFuture;

public interface RiskBureauService {
    
    CompletableFuture<RiskBureauResult> evaluate(CreditRequest creditRequest);
    
    record RiskBureauResult(
        String riskScore,
        String riskCategory,
        boolean isBlocked,
        String bureauReference,
        java.time.Instant evaluatedAt
    ) {
        public static RiskBureauResult approved(String bureauReference) {
            return new RiskBureauResult(
                "LOW",
                "APPROVED",
                false,
                bureauReference,
                java.time.Instant.now()
            );
        }
        
        public static RiskBureauResult rejected(String bureauReference, String reason) {
            return new RiskBureauResult(
                "HIGH",
                "REJECTED",
                true,
                bureauReference,
                java.time.Instant.now()
            );
        }
        
        public static RiskBureauResult pending(String bureauReference) {
            return new RiskBureauResult(
                "MEDIUM",
                "PENDING_REVIEW",
                false,
                bureauReference,
                java.time.Instant.now()
            );
        }
    }
}