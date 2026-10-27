package com.evaluacion.springboot.domain.ports;

import com.evaluacion.springboot.domain.model.CreditRequest;
import java.util.concurrent.CompletableFuture;

/**
 * Puerto de salida para el servicio de antifraude.
 * Define las operaciones para evaluar posibles fraudes en solicitudes de crédito.
 */
public interface AntifraudService {
    /**
     * Evalúa una solicitud de crédito para detectar posibles fraudes.
     * @param creditRequest Solicitud a evaluar
     * @return Resultado de la evaluación antifraude
     */
    CompletableFuture<AntifraudResult> evaluate(CreditRequest creditRequest);

    /**
     * Resultado de la evaluación antifraude.
     */
    record AntifraudResult(
            boolean isFraud,
            String reason,
            double riskScore
    ) {
        /**
         * Constructor compacto para resultados sin detalles.
         * @param isFraud Indica si se detectó fraude
         */
        public AntifraudResult(boolean isFraud) {
            this(isFraud, null, 0.0);
        }
    }
}