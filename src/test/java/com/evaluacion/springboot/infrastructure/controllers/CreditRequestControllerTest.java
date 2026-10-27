package com.evaluacion.springboot.infrastructure.controllers;

import com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase;
import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.infrastructure.config.ExceptionHandlerConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CreditRequestController.class)
@Import(ExceptionHandlerConfig.class)
@DisplayName("CreditRequestController - Pruebas de Integración")
class CreditRequestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCreditRequestUseCase createCreditRequestUseCase;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Nested
    @DisplayName("POST /api/v1/credit-requests - Creación de solicitudes")
    class CreacionSolicitudes {

        @Test
        @DisplayName("Debe retornar 201 cuando la solicitud se crea exitosamente")
        void debeRetornar201CuandoSolicitudSeCreaExitosamente() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-TEST-001",
                    "channel": "WEB",
                    "customerId": "CUST-TEST-001",
                    "amount": 50000.00,
                    "dueDate": "2024-12-31"
                }
                """;

            CreditRequest creditRequest = CreditRequest.create(
                UUID.randomUUID(),
                "OP-TEST-001",
                "WEB",
                "CUST-TEST-001",
                new BigDecimal("50000.00"),
                LocalDate.of(2024, 12, 31),
                CreditRequestStatus.PENDING,
                "IDEMP-001"
            );

            when(createCreditRequestUseCase.execute(
                eq("OP-TEST-001"),
                eq("WEB"),
                eq("CUST-TEST-001"),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenReturn(creditRequest);

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/v1/credit-requests/" + creditRequest.id()))
                .andExpect(jsonPath("$.id").value(creditRequest.id().toString()))
                .andExpect(jsonPath("$.operationNumber").value("OP-TEST-001"))
                .andExpect(jsonPath("$.channel").value("WEB"))
                .andExpect(jsonPath("$.customerId").value("CUST-TEST-001"))
                .andExpect(jsonPath("$.amount").value(50000.00))
                .andExpect(jsonPath("$.status").value("PENDING"));
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el cuerpo de la solicitud es inválido")
        void debeRetornar400CuandoCuerpoEsInvalido() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "",
                    "channel": "WEB",
                    "customerId": "",
                    "amount": -1000,
                    "dueDate": "2024-12-31"
                }
                """;

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors").isArray());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando falta operationNumber")
        void debeRetornar400CuandoFaltaOperationNumber() throws Exception {
            String requestBody = """
                {
                    "channel": "WEB",
                    "customerId": "CUST-001",
                    "amount": 10000.00,
                    "dueDate": "2024-12-31"
                }
                """;

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[?(@.field=='operationNumber')]").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando amount es cero")
        void debeRetornar400CuandoAmountEsCero() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-001",
                    "channel": "API",
                    "customerId": "CUST-001",
                    "amount": 0,
                    "dueDate": "2024-12-31"
                }
                """;

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[?(@.field=='amount')]").exists());
        }

        @Test
        @DisplayName("Debe retornar 409 cuando hay conflicto de idempotencia")
        void debeRetornar409CuandoHayConflictoIdempotencia() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-DUPLICATE-001",
                    "channel": "API",
                    "customerId": "CUST-001",
                    "amount": 25000.00,
                    "dueDate": "2024-12-31"
                }
                """;

            CreditRequest existingRequest = CreditRequest.create(
                UUID.randomUUID(),
                "OP-DUPLICATE-001",
                "API",
                "CUST-001",
                new BigDecimal("25000.00"),
                LocalDate.of(2024, 12, 31),
                CreditRequestStatus.APPROVED,
                "IDEMP-DUP-001"
            );

            when(createCreditRequestUseCase.execute(
                eq("OP-DUPLICATE-001"),
                eq("API"),
                anyString(),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenReturn(existingRequest);

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("Recurso existente con misma clave de idempotencia"))
                .andExpect(jsonPath("$.existingResource").exists());
        }

        @Test
        @DisplayName("Debe retornar 422 cuando el monto excede el límite permitido")
        void debeRetornar422CuandoMontoExcedeLimite() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-LIMIT-001",
                    "channel": "API",
                    "customerId": "CUST-001",
                    "amount": 999999.99,
                    "dueDate": "2024-12-31"
                }
                """;

            when(createCreditRequestUseCase.execute(
                eq("OP-LIMIT-001"),
                eq("API"),
                anyString(),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenThrow(new IllegalArgumentException("Monto excede el límite máximo de 500000.00"));

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.message").value("Monto excede el límite máximo de 500000.00"));
        }

        @Test
        @DisplayName("Debe aceptar solicitud con todos los campos opcionales")
        void debeAceptarSolicitudConTodosLosCamposOpcionales() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-COMPLETE-001",
                    "channel": "MOBILE",
                    "customerId": "CUST-COMPLETE",
                    "amount": 75000.50,
                    "dueDate": "2025-06-30",
                    "productType": "CONSUMER",
                    "paymentFrequency": "MONTHLY"
                }
                """;

            CreditRequest creditRequest = CreditRequest.create(
                UUID.randomUUID(),
                "OP-COMPLETE-001",
                "MOBILE",
                "CUST-COMPLETE",
                new BigDecimal("75000.50"),
                LocalDate.of(2025, 6, 30),
                CreditRequestStatus.PENDING,
                "IDEMP-COMPLETE"
            );

            when(createCreditRequestUseCase.execute(
                eq("OP-COMPLETE-001"),
                eq("MOBILE"),
                eq("CUST-COMPLETE"),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenReturn(creditRequest);

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.operationNumber").value("OP-COMPLETE-001"))
                .andExpect(jsonPath("$.channel").value("MOBILE"));
        }
    }

    @Nested
    @DisplayName("Validación de Content-Type y formato")
    class ValidacionContentType {

        @Test
        @DisplayName("Debe retornar 415 cuando el Content-Type es incorrecto")
        void debeRetornar415CuandoContentTypeIncorrecto() throws Exception {
            String requestBody = "{}";

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.TEXT_PLAIN)
                    .content(requestBody))
                .andExpect(status().isUnsupportedMediaType());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el JSON está mal formado")
        void debeRetornar400CuandoJsonMalFormado() throws Exception {
            String requestBody = "{ operationNumber: 'OP-001', corrupt: true }";

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 cuando el cuerpo está vacío")
        void debeRetornar400CuandoCuerpoVacio() throws Exception {
            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(""))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Casos edge y límites del sistema")
    class CasosEdge {

        @Test
        @DisplayName("Debe manejar operación con caracteres especiales en operationNumber")
        void debeManejarCaracteresEspeciales() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-2024/12/31-001",
                    "channel": "API",
                    "customerId": "CUST-SPECIAL",
                    "amount": 10000.00,
                    "dueDate": "2024-12-31"
                }
                """;

            CreditRequest creditRequest = CreditRequest.create(
                UUID.randomUUID(),
                "OP-2024/12/31-001",
                "API",
                "CUST-SPECIAL",
                new BigDecimal("10000.00"),
                LocalDate.of(2024, 12, 31),
                CreditRequestStatus.PENDING,
                "IDEMP-SPECIAL"
            );

            when(createCreditRequestUseCase.execute(
                eq("OP-2024/12/31-001"),
                anyString(),
                anyString(),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenReturn(creditRequest);

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Debe manejar amount con muchos decimales")
        void debeManejarAmountConMuchosDecimales() throws Exception {
            String requestBody = """
                {
                    "operationNumber": "OP-DECIMAL-001",
                    "channel": "API",
                    "customerId": "CUST-DECIMAL",
                    "amount": 12345.678901,
                    "dueDate": "2024-12-31"
                }
                """;

            CreditRequest creditRequest = CreditRequest.create(
                UUID.randomUUID(),
                "OP-DECIMAL-001",
                "API",
                "CUST-DECIMAL",
                new BigDecimal("12345.678901"),
                LocalDate.of(2024, 12, 31),
                CreditRequestStatus.PENDING,
                "IDEMP-DECIMAL"
            );

            when(createCreditRequestUseCase.execute(
                eq("OP-DECIMAL-001"),
                anyString(),
                anyString(),
                any(BigDecimal.class),
                any(LocalDate.class)
            )).thenReturn(creditRequest);

            mockMvc.perform(post("/api/v1/credit-requests")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.amount").value(12345.678901));
        }
    }
}