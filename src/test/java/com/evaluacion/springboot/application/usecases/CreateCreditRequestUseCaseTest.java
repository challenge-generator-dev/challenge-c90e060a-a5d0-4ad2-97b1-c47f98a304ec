package com.evaluacion.springboot.application.usecases;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.domain.ports.AntifraudService;
import com.evaluacion.springboot.domain.ports.AntifraudService.AntifraudResult;
import com.evaluacion.springboot.domain.ports.CreditRequestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CreateCreditRequestUseCase - Pruebas Unitarias")
class CreateCreditRequestUseCaseTest {

    @Mock
    private CreditRequestRepository creditRequestRepository;

    @Mock
    private AntifraudService antifraudService;

    private CreateCreditRequestUseCase createCreditRequestUseCase;

    @BeforeEach
    void setUp() {
        createCreditRequestUseCase = new CreateCreditRequestUseCase(
            creditRequestRepository,
            antifraudService
        );
    }

    @Nested
    @DisplayName("Escenario: Creación exitosa de solicitud de crédito")
    class CreacionExitosa {

        @Test
        @DisplayName("Debe crear solicitud cuando no existe idempotencia previa")
        void debeCrearSolicitudCuandoNoExisteIdempotenciaPrevia() {
            String operationNumber = "OP-2024-001";
            String channel = "WEB";
            String customerId = "CUST-123";
            BigDecimal amount = new BigDecimal("50000.00");

            when(creditRequestRepository.existsByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(false);
            when(creditRequestRepository.save(any(CreditRequest.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
            when(antifraudService.evaluate(any(CreditRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(
                    new AntifraudResult(true, "APPROVED", "Sin riesgo detectado")
                ));

            CreditRequest result = createCreditRequestUseCase.execute(
                operationNumber, channel, customerId, amount, LocalDate.now().plusDays(30)
            );

            assertThat(result).isNotNull();
            assertThat(result.operationNumber()).isEqualTo(operationNumber);
            assertThat(result.channel()).isEqualTo(channel);
            assertThat(result.customerId()).isEqualTo(customerId);
            assertThat(result.amount()).isEqualByComparingTo(amount);
            assertThat(result.status()).isEqualTo(CreditRequestStatus.PENDING);

            verify(creditRequestRepository).existsByOperationNumberAndChannel(operationNumber, channel);
            verify(creditRequestRepository).save(any(CreditRequest.class));
            verify(antifraudService).evaluate(any(CreditRequest.class));
        }

        @Test
        @DisplayName("Debe retornar solicitud existente cuando hay idempotencia")
        void debeRetornarSolicitudExistenteCuandoHayIdempotencia() {
            String operationNumber = "OP-2024-002";
            String channel = "MOBILE";
            String customerId = "CUST-456";
            BigDecimal amount = new BigDecimal("25000.00");

            CreditRequest existingRequest = CreditRequest.create(
                UUID.randomUUID(),
                operationNumber,
                channel,
                customerId,
                amount,
                LocalDate.now().plusDays(15),
                CreditRequestStatus.APPROVED,
                "IDEMPOTENT_KEY"
            );

            when(creditRequestRepository.existsByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(true);
            when(creditRequestRepository.findByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(Optional.of(existingRequest));

            CreditRequest result = createCreditRequestUseCase.execute(
                operationNumber, channel, customerId, amount, LocalDate.now().plusDays(15)
            );

            assertThat(result).isEqualTo(existingRequest);
            assertThat(result.status()).isEqualTo(CreditRequestStatus.APPROVED);

            verify(creditRequestRepository).existsByOperationNumberAndChannel(operationNumber, channel);
            verify(creditRequestRepository, never()).save(any(CreditRequest.class));
            verify(antifraudService, never()).evaluate(any(CreditRequest.class));
        }
    }

    @Nested
    @DisplayName("Escenario: Validación de parámetros de entrada")
    class ValidacionParametros {

        @Test
        @DisplayName("Debe lanzar excepción cuando operationNumber es nulo")
        void debeLanzarExcepcionCuandoOperationNumberEsNulo() {
            String customerId = "CUST-789";
            BigDecimal amount = new BigDecimal("10000.00");

            assertThatThrownBy(() ->
                createCreditRequestUseCase.execute(
                    null, "API", customerId, amount, LocalDate.now()
                )
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("operationNumber no puede ser nulo o vacío");
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando operationNumber está vacío")
        void debeLanzarExcepcionCuandoOperationNumberEstaVacio() {
            String customerId = "CUST-101";
            BigDecimal amount = new BigDecimal("5000.00");

            assertThatThrownBy(() ->
                createCreditRequestUseCase.execute(
                    "   ", "API", customerId, amount, LocalDate.now()
                )
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("operationNumber no puede ser nulo o vacío");
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando amount es negativo")
        void debeLanzarExcepcionCuandoAmountEsNegativo() {
            assertThatThrownBy(() ->
                createCreditRequestUseCase.execute(
                    "OP-003", "API", "CUST-202", new BigDecimal("-1000.00"), LocalDate.now()
                )
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("amount debe ser mayor que cero");
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando amount es cero")
        void debeLanzarExcepcionCuandoAmountEsCero() {
            assertThatThrownBy(() ->
                createCreditRequestUseCase.execute(
                    "OP-004", "API", "CUST-303", BigDecimal.ZERO, LocalDate.now()
                )
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("amount debe ser mayor que cero");
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando customerId es nulo")
        void debeLanzarExcepcionCuandoCustomerIdEsNulo() {
            BigDecimal amount = new BigDecimal("15000.00");

            assertThatThrownBy(() ->
                createCreditRequestUseCase.execute(
                    "OP-005", "API", null, amount, LocalDate.now()
                )
            ).isInstanceOf(IllegalArgumentException.class)
             .hasMessageContaining("customerId no puede ser nulo o vacío");
        }
    }

    @Nested
    @DisplayName("Escenario: Integración con servicio antifraude")
    class IntegracionAntifraude {

        @Test
        @DisplayName("Debe actualizar estado cuando antifraude aprueba")
        void debeActualizarEstadoCuandoAntifraudeAprueba() {
            String operationNumber = "OP-2024-010";
            String channel = "API";
            String customerId = "CUST-999";
            BigDecimal amount = new BigDecimal("75000.00");

            when(creditRequestRepository.existsByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(false);
            when(creditRequestRepository.save(any(CreditRequest.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
            when(antifraudService.evaluate(any(CreditRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(
                    new AntifraudResult(true, "APPROVED", "Cliente sin historial de fraude")
                ));

            CreditRequest result = createCreditRequestUseCase.execute(
                operationNumber, channel, customerId, amount, LocalDate.now().plusDays(45)
            );

            ArgumentCaptor<CreditRequest> captor = ArgumentCaptor.forClass(CreditRequest.class);
            verify(creditRequestRepository, times(2)).save(captor.capture());

            CreditRequest lastSaved = captor.getAllValues().get(1);
            assertThat(lastSaved.status()).isEqualTo(CreditRequestStatus.APPROVED);
        }

        @Test
        @DisplayName("Debe rechazar cuando antifraude detecta riesgo")
        void debeRechazarCuandoAntifraudeDetectaRiesgo() {
            String operationNumber = "OP-2024-011";
            String channel = "API";
            String customerId = "CUST-888";
            BigDecimal amount = new BigDecimal("100000.00");

            when(creditRequestRepository.existsByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(false);
            when(creditRequestRepository.save(any(CreditRequest.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
            when(antifraudService.evaluate(any(CreditRequest.class)))
                .thenReturn(CompletableFuture.completedFuture(
                    new AntifraudResult(false, "REJECTED", "Alto riesgo detectado")
                ));

            CreditRequest result = createCreditRequestUseCase.execute(
                operationNumber, channel, customerId, amount, LocalDate.now().plusDays(30)
            );

            ArgumentCaptor<CreditRequest> captor = ArgumentCaptor.forClass(CreditRequest.class);
            verify(creditRequestRepository, times(2)).save(captor.capture());

            CreditRequest lastSaved = captor.getAllValues().get(1);
            assertThat(lastSaved.status()).isEqualTo(CreditRequestStatus.REJECTED);
        }

        @Test
        @DisplayName("Debe manejar fallo en servicio antifraude gracefully")
        void debeManejarFalloEnAntifraudeGracefully() {
            String operationNumber = "OP-2024-012";
            String channel = "API";
            String customerId = "CUST-777";
            BigDecimal amount = new BigDecimal("30000.00");

            when(creditRequestRepository.existsByOperationNumberAndChannel(operationNumber, channel))
                .thenReturn(false);
            when(creditRequestRepository.save(any(CreditRequest.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));
            when(antifraudService.evaluate(any(CreditRequest.class)))
                .thenReturn(CompletableFuture.failedFuture(
                    new RuntimeError("Servicio antifraude no disponible")
                ));

            CreditRequest result = createCreditRequestUseCase.execute(
                operationNumber, channel, customerId, amount, LocalDate.now().plusDays(20)
            );

            assertThat(result.status()).isEqualTo(CreditRequestStatus.PENDING);
            verify(creditRequestRepository, times(1)).save(any(CreditRequest.class));
        }
    }
}