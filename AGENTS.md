# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Evaluación de Competencia en Spring Boot**.

| | |
|---|---|
| Tema | Evaluación de Competencia en Frameworks para Desarrollador Backend Advanced |
| Nivel | advanced-l2 |
| Chapter | Backend |
| Especialidad | Java |
| Stack | Java / Spring Boot 3.5 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz`
- `clase con @SpringBootApplication`
- `application.yml en src/main/resources`
- `capa de dominio con entidades y puertos`
- `capa de aplicacion con casos de uso`
- `capa de infraestructura con adaptadores y @RestController`

Trampas conocidas:

- TODA `<version>` del pom va con tres segmentos: la del parent (ej. `3.5.6`) y la de cada dependencia que la lleve (ej. Resilience4j `2.2.0`). `3.4` y `2.0` no existen como artefacto y el build muere resolviendo dependencias.
- Las dependencias que el parent POM gestiona van SIN `<version>`: `spring-boot-starter-web`, `-data-jpa`, `-validation`, `-test`, etc.
- Resilience4j publica un artefacto por linea de Spring Boot. Con Spring Boot 3 va `resilience4j-spring-boot3` con version de tres segmentos (ej. `2.2.0`, no `2.0`). `resilience4j-spring-boot2` es de Spring Boot 2 y rompe el arranque.
- Si usas anotaciones de validacion (`@NotNull`, `@Size`, `@Positive`) declara `spring-boot-starter-validation`: el starter web no las trae.
- El `spring-boot-maven-plugin` tiene que estar en `<build><plugins>` o no se empaqueta ejecutable.
- Spring Boot 3 usa `jakarta.*`, nunca `javax.*`.
- Cada archivo empieza con su `package` y con un `import` por cada clase del proyecto que viva en otro paquete. Usar `PaymentService` desde `infrastructure` sin `import com.x.application.PaymentService` no compila.

Dependencias:

- org.springframework.boot:spring-boot-starter-web n/a
- org.springframework.boot:spring-boot-starter-data-jpa n/a
- org.springframework.boot:spring-boot-starter-validation n/a
- org.springframework.boot:spring-boot-starter-actuator n/a
- org.springframework.boot:spring-boot-starter-aop n/a
- org.springframework.boot:spring-boot-configuration-processor n/a
- org.springdoc:springdoc-openapi-starter-webmvc-ui 2.6.0
- com.h2database:h2 2.2.224
- org.postgresql:postgresql 42.7.3
- org.projectlombok:lombok 1.18.34
- org.springframework.boot:spring-boot-starter-test n/a
- org.mockito:mockito-core 5.12.0
- org.mockito:mockito-junit-jupiter 5.12.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Configuración del Entorno**: Proyecto Spring Boot configurado con las dependencias y variables de entorno necesarias.
- **Fase 2 — Enrutamiento de Solicitudes**: Controladores implementados que enrutan las solicitudes a los servicios correspondientes.
- **Fase 3 — Conexión con Base de Datos**: Aplicación conectada a una base de datos con entidades mapeadas utilizando un ORM.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (1 de 16)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/OpenApiConfig.java`

### 2. Referencias colgando (64)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/evaluacion/springboot/Application.java` — `com.evaluacion.springboot.infrastructure.config.AppProperties`
      El import com.evaluacion.springboot.infrastructure.config.AppProperties usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/repositories/CreditRequestJpaRepository.java` — `com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity`
      El import com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestCreateRequest`
      El import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestCreateRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestResponse`
      El import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse`
      El import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse`
      El import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java` — `org.aspectj.lang`
      El import org.aspectj.lang.ProceedingJoinPoint pertenece a org.aspectj.lang, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `io.swagger.v3`
      El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java` — `com.fasterxml.jackson`
      El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.operationNumber`
      Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.channel`
      Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequestJpaRepository.save`
      Se invoca `save` sobre `CreditRequestJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequestJpaRepository.findById`
      Se invoca `findById` sobre `CreditRequestJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientId`
      Se invoca `clientId` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientName`
      Se invoca `clientName` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientEmail`
      Se invoca `clientEmail` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientPhone`
      Se invoca `clientPhone` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.requestedAmount`
      Se invoca `requestedAmount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.currency`
      Se invoca `currency` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.paymentTermMonths`
      Se invoca `paymentTermMonths` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.purpose`
      Se invoca `purpose` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.status`
      Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.rejectionReason`
      Se invoca `rejectionReason` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.antifraudReference`
      Se invoca `antifraudReference` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.riskBureauReference`
      Se invoca `riskBureauReference` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.createdAt`
      Se invoca `createdAt` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.updatedAt`
      Se invoca `updatedAt` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.operationNumber`
      Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.channel`
      Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.status`
      Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `AntifraudResult.approved`
      Se invoca `approved` sobre `AntifraudResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.approved`
      Se invoca `approved` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.riskLevel`
      Se invoca `riskLevel` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `AntifraudResult.reason`
      Se invoca `reason` sobre `AntifraudResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.reason`
      Se invoca `reason` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.score`
      Se invoca `score` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.amount`
      Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.clientDocumentNumber`
      Se invoca `clientDocumentNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.operationNumber`
      Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.clientDocumentNumber`
      Se invoca `clientDocumentNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.amount`
      Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.channel`
      Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreditRequest.status`
      Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreateCreditRequestUseCase.findById`
      Se invoca `findById` sobre `CreateCreditRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreateCreditRequestUseCase.findByFilters`
      Se invoca `findByFilters` sobre `CreateCreditRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.operationNumber`
      Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.channel`
      Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.customerId`
      Se invoca `customerId` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.amount`
      Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.status`
      Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java` — `CreditRequest.id`
      Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (18)

- `pom.xml`
- `src/main/java/com/evaluacion/springboot/Application.java`
- `src/main/resources/application.yml`
- `src/main/java/com/evaluacion/springboot/domain/model/CreditRequest.java`
- `src/main/java/com/evaluacion/springboot/domain/ports/CreditRequestRepository.java`
- `src/main/java/com/evaluacion/springboot/domain/ports/AntifraudService.java`
- `src/main/java/com/evaluacion/springboot/domain/ports/RiskBureauService.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/repositories/CreditRequestJpaRepository.java`
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java`
- `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java`
- `src/main/resources/logback-spring.xml`
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java`
- `src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/evaluacion/springboot`
- `src/main/java/com/evaluacion/springboot/application`
- `src/main/java/com/evaluacion/springboot/application/usecases`
- `src/main/java/com/evaluacion/springboot/domain`
- `src/main/java/com/evaluacion/springboot/domain/model`
- `src/main/java/com/evaluacion/springboot/domain/ports`
- `src/main/java/com/evaluacion/springboot/infrastructure`
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters`
- `src/main/java/com/evaluacion/springboot/infrastructure/config`
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers`
- `src/main/java/com/evaluacion/springboot/infrastructure/repositories`
- `src/main/resources`
- `src/test/java/com/evaluacion/springboot`

## Verificacion

```bash
mvn clean compile
```

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced
- Brecha que el reto ataca: Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).
- Mision: Reto de evaluación de la competencia descrita.

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
