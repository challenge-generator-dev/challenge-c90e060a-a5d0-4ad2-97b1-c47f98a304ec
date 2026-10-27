package com.evaluacion.springboot.domain.model;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entidad de dominio que representa una solicitud de crédito.
 * Contiene los datos esenciales para procesar la solicitud y evaluar su elegibilidad.
 */
public record CreditRequest(
        @NotNull
        UUID id,

        @NotNull
        @Size(min = 1, max = 50)
        String operationNumber,

        @NotNull
        @Size(min = 1, max = 20)
        String channel,

        @NotNull
        @Min(1)
        Long clientId,

        @NotNull
        @DecimalMin(value = "0.0", inclusive = false)
        BigDecimal amount,

        @NotNull
        @Size(min = 1, max = 3)
        String currency,

        @NotNull
        LocalDateTime requestDate,

        @NotNull
        CreditRequestStatus status,

        @Size(max = 200)
        String rejectionReason,

        @NotNull
        LocalDateTime createdAt,

        @NotNull
        LocalDateTime updatedAt,

        @NotNull
        @Min(0)
        Integer version
) {
    /**
     * Constructor que inicializa una solicitud con los campos obligatorios.
     * Genera automáticamente el ID y los timestamps de creación y actualización.
     */
    public CreditRequest {
        if (id == null) {
            id = UUID.randomUUID();
        }
        if (requestDate == null) {
            requestDate = LocalDateTime.now();
        }
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
        if (status == null) {
            status = CreditRequestStatus.PENDING;
        }
        if (version == null) {
            version = 0;
        }
    }

    /**
     * Crea una nueva instancia de CreditRequest con los datos proporcionados.
     * @param operationNumber Número único de operación
     * @param channel Canal por el que se originó la solicitud
     * @param clientId Identificador del cliente
     * @param amount Monto solicitado
     * @param currency Moneda del monto
     * @return Nueva instancia de CreditRequest
     */
    public static CreditRequest create(
            String operationNumber,
            String channel,
            Long clientId,
            BigDecimal amount,
            String currency) {
        return new CreditRequest(
                UUID.randomUUID(),
                operationNumber,
                channel,
                clientId,
                amount,
                currency,
                LocalDateTime.now(),
                CreditRequestStatus.PENDING,
                null,
                LocalDateTime.now(),
                LocalDateTime.now(),
                0
        );
    }

    /**
     * Actualiza el estado de la solicitud y registra la razón de rechazo si aplica.
     * @param newStatus Nuevo estado de la solicitud
     * @param reason Razón de rechazo (opcional)
     * @return Nueva instancia con el estado actualizado
     */
    public CreditRequest withStatus(CreditRequestStatus newStatus, String reason) {
        return new CreditRequest(
                this.id,
                this.operationNumber,
                this.channel,
                this.clientId,
                this.amount,
                this.currency,
                this.requestDate,
                newStatus,
                newStatus == CreditRequestStatus.REJECTED ? reason : this.rejectionReason,
                this.createdAt,
                LocalDateTime.now(),
                this.version + 1
        );
    }

    /**
     * Verifica si la solicitud es idempotente basada en el número de operación y canal.
     * @param operationNumber Número de operación a verificar
     * @param channel Canal a verificar
     * @return true si coincide con los valores actuales
     */
    public boolean isIdempotent(String operationNumber, String channel) {
        return this.operationNumber.equals(operationNumber) && this.channel.equals(channel);
    }
}

/**
 * Estados posibles de una solicitud de crédito.
 */
public enum CreditRequestStatus {
    PENDING,
    APPROVED,
    REJECTED,
    FRAUD_SUSPECTED,
    RISK_REJECTED
}