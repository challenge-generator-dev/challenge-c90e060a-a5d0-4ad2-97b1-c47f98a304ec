package com.evaluacion.springboot.domain.ports;

import com.evaluacion.springboot.domain.model.CreditRequest;
import java.util.Optional;
import java.util.UUID;

/**
 * Puerto de salida para la persistencia de solicitudes de crédito.
 * Define las operaciones que la capa de dominio necesita para interactuar con el almacenamiento.
 */
public interface CreditRequestRepository {
    /**
     * Guarda una solicitud de crédito.
     * @param creditRequest Solicitud a guardar
     * @return Solicitud guardada con posibles actualizaciones (como IDs generados)
     */
    CreditRequest save(CreditRequest creditRequest);

    /**
     * Busca una solicitud de crédito por su ID.
     * @param id Identificador de la solicitud
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<CreditRequest> findById(UUID id);

    /**
     * Busca una solicitud de crédito por su número de operación y canal.
     * Se usa para verificar idempotencia.
     * @param operationNumber Número de operación
     * @param channel Canal de origen
     * @return Optional con la solicitud si existe, vacío en caso contrario
     */
    Optional<CreditRequest> findByOperationNumberAndChannel(String operationNumber, String channel);

    /**
     * Verifica si ya existe una solicitud con el mismo número de operación y canal.
     * @param operationNumber Número de operación
     * @param channel Canal de origen
     * @return true si existe, false en caso contrario
     */
    boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
}