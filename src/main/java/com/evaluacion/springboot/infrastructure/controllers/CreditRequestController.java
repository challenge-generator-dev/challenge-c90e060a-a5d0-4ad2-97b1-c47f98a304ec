package com.evaluacion.springboot.infrastructure.controllers;

import com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase;
import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestCreateRequest;
import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestResponse;
import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/credit-requests")
@Tag(name = "Credit Requests", description = "API para la gestión de solicitudes de crédito")
public class CreditRequestController {

    private static final Logger log = LoggerFactory.getLogger(CreditRequestController.class);

    private final CreateCreditRequestUseCase createCreditRequestUseCase;

    public CreditRequestController(CreateCreditRequestUseCase createCreditRequestUseCase) {
        this.createCreditRequestUseCase = createCreditRequestUseCase;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva solicitud de crédito", 
               description = "Crea una nueva solicitud de crédito con validación de idempotencia")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Solicitud creada exitosamente",
                     content = @Content(schema = @Schema(implementation = CreditRequestResponse.class))),
        @ApiResponse(responseCode = "400", description = "Datos de solicitud inválidos",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "409", description = "Solicitud duplicada (violación de idempotencia)",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CreditRequestResponse> createCreditRequest(
            @Valid @RequestBody CreditRequestCreateRequest request,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey) {
        
        log.info("Recibida solicitud de crédito para cliente: {}, operación: {}, canal: {}",
                request.clientId(), request.operationNumber(), request.channel());
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            log.debug("Clave de idempotencia proporcionada: {}", idempotencyKey);
        }

        try {
            CreditRequest creditRequest = createCreditRequestUseCase.execute(request, idempotencyKey);
            CreditRequestResponse response = CreditRequestResponse.fromDomain(creditRequest);
            
            log.info("Solicitud de crédito creada exitosamente con ID: {}, estado: {}",
                    creditRequest.id(), creditRequest.status());
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (IllegalArgumentException e) {
            log.warn("Error de validación al crear solicitud: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage(), e);
        } catch (IllegalStateException e) {
            log.warn("Violación de idempotencia: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.CONFLICT, e.getMessage(), e);
        }
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener una solicitud de crédito por ID",
               description = "Recupera los detalles de una solicitud de crédito existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Solicitud encontrada",
                     content = @Content(schema = @Schema(implementation = CreditRequestResponse.class))),
        @ApiResponse(responseCode = "404", description = "Solicitud no encontrada",
                     content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<CreditRequestResponse> getCreditRequestById(
            @Parameter(description = "ID único de la solicitud de crédito", required = true)
            @PathVariable UUID id) {
        
        log.debug("Buscando solicitud de crédito con ID: {}", id);
        
        CreditRequest creditRequest = createCreditRequestUseCase.findById(id)
                .orElseThrow(() -> {
                    log.warn("Solicitud de crédito no encontrada con ID: {}", id);
                    return new ResponseStatusException(HttpStatus.NOT_FOUND, 
                            "Credit request not found with id: " + id);
                });
        
        return ResponseEntity.ok(CreditRequestResponse.fromDomain(creditRequest));
    }

    @GetMapping
    @Operation(summary = "Listar solicitudes de crédito",
               description = "Obtiene todas las solicitudes de crédito con filtros opcionales")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de solicitudes retrieved",
                     content = @Content(schema = @Schema(implementation = CreditRequestResponse.class)))
    })
    public ResponseEntity<List<CreditRequestResponse>> listCreditRequests(
            @RequestParam(required = false) String clientId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        
        log.debug("Listando solicitudes de crédito - cliente: {}, estado: {}, página: {}, tamaño: {}",
                clientId, status, page, size);
        
        CreditRequestStatus filterStatus = null;
        if (status != null && !status.isBlank()) {
            try {
                filterStatus = CreditRequestStatus.valueOf(status.toUpperCase());
            } catch (IllegalArgumentException e) {
                log.warn("Estado inválido proporcionado: {}", status);
            }
        }
        
        List<CreditRequest> requests = createCreditRequestUseCase.findByFilters(clientId, filterStatus, page, size);
        List<CreditRequestResponse> responses = requests.stream()
                .map(CreditRequestResponse::fromDomain)
                .collect(Collectors.toList());
        
        log.info("Se encontraron {} solicitudes de crédito", responses.size());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/health")
    @Operation(summary = "Verificar estado del servicio",
               description = "Endpoint de health check para monitorear la disponibilidad del servicio")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Servicio disponible")
    })
    public ResponseEntity<String> healthCheck() {
        log.debug("Health check solicitado");
        return ResponseEntity.ok("OK");
    }
}