# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `src/main/java/com/evaluacion/springboot/infrastructure/config/OpenApiConfig.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/evaluacion/springboot/Application.java` — `com.evaluacion.springboot.infrastructure.config.AppProperties`: El import com.evaluacion.springboot.infrastructure.config.AppProperties usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/repositories/CreditRequestJpaRepository.java` — `com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity`: El import com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestCreateRequest`: El import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestCreateRequest usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestResponse`: El import com.evaluacion.springboot.infrastructure.controllers.dto.CreditRequestResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse`: El import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse`: El import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse usa un paquete propio del proyecto pero ningun archivo generado declara ese tipo. Falta generar la clase/interfaz, o el import esta mal escrito.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `io.swagger.v3`: El import io.swagger.v3.oas.annotations.Operation pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java` — `org.aspectj.lang`: El import org.aspectj.lang.ProceedingJoinPoint pertenece a org.aspectj.lang, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java` — `io.swagger.v3`: El import io.swagger.v3.oas.models.Components pertenece a io.swagger.v3, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java` — `com.fasterxml.jackson`: El import com.fasterxml.jackson.databind.ObjectMapper pertenece a com.fasterxml.jackson, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.operationNumber`: Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.channel`: Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequestJpaRepository.save`: Se invoca `save` sobre `CreditRequestJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequestJpaRepository.findById`: Se invoca `findById` sobre `CreditRequestJpaRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientId`: Se invoca `clientId` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientName`: Se invoca `clientName` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientEmail`: Se invoca `clientEmail` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.clientPhone`: Se invoca `clientPhone` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.requestedAmount`: Se invoca `requestedAmount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.currency`: Se invoca `currency` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.paymentTermMonths`: Se invoca `paymentTermMonths` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.purpose`: Se invoca `purpose` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.status`: Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.rejectionReason`: Se invoca `rejectionReason` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.antifraudReference`: Se invoca `antifraudReference` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.riskBureauReference`: Se invoca `riskBureauReference` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.createdAt`: Se invoca `createdAt` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java` — `CreditRequest.updatedAt`: Se invoca `updatedAt` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.operationNumber`: Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.channel`: Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `CreditRequest.status`: Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `AntifraudResult.approved`: Se invoca `approved` sobre `AntifraudResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.approved`: Se invoca `approved` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.riskLevel`: Se invoca `riskLevel` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `AntifraudResult.reason`: Se invoca `reason` sobre `AntifraudResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.reason`: Se invoca `reason` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java` — `RiskBureauResult.score`: Se invoca `score` sobre `RiskBureauResult`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.amount`: Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.clientDocumentNumber`: Se invoca `clientDocumentNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java` — `CreditRequest.operationNumber`: Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.clientDocumentNumber`: Se invoca `clientDocumentNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.amount`: Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java` — `CreditRequest.channel`: Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreditRequest.status`: Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreateCreditRequestUseCase.findById`: Se invoca `findById` sobre `CreateCreditRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java` — `CreateCreditRequestUseCase.findByFilters`: Se invoca `findByFilters` sobre `CreateCreditRequestUseCase`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.operationNumber`: Se invoca `operationNumber` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.channel`: Se invoca `channel` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.customerId`: Se invoca `customerId` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.amount`: Se invoca `amount` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java` — `CreditRequest.status`: Se invoca `status` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java` — `CreditRequest.id`: Se invoca `id` sobre `CreditRequest`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Backend, Especialidad Desarrollador, Tecnología Java, Advanced

### Brecha de conocimiento
Experiencia comprobable en al menos un marco de trabajo (Framework) en su lenguaje de programación principal (por ejemplo, Spring Framework en Java o ASP .net core en C#) y puede realizar el enrutamiento de solicitudes, controlar las variables de entorno, generar registros de rastros (Logs) y conectarse con bases de datos utilizando un Mapeador Relacional de Objetos (ORM).

### Misión / candidato
Reto de evaluación de la competencia descrita.

### Datos adicionales
El candidato es un desarrollador Backend Advanced con experiencia en estructuras de datos y patrones GRASP. Está listo para profundizar en frameworks como Spring Boot.

### Reto
- Tema: Evaluación de Competencia en Frameworks para Desarrollador Backend Advanced
- Seniority: advanced-l2
- Tipo: practical
- Título: Evaluación de Competencia en Spring Boot
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Configuración del Entorno — objetivo: Establecer un entorno de desarrollo utilizando Spring Boot. — entregable (NO resolver): Proyecto Spring Boot configurado con las dependencias y variables de entorno necesarias.
- Fase 2: Enrutamiento de Solicitudes — objetivo: Implementar el enrutamiento de solicitudes utilizando Spring Boot. — entregable (NO resolver): Controladores implementados que enrutan las solicitudes a los servicios correspondientes.
- Fase 3: Conexión con Base de Datos — objetivo: Conectar la aplicación a una base de datos utilizando un ORM. — entregable (NO resolver): Aplicación conectada a una base de datos con entidades mapeadas utilizando un ORM.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/>
    </parent>

    <groupId>com.evaluacion</groupId>
    <artifactId>springboot-evaluation</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>springboot-evaluation</name>
    <description>Evaluación de competencia en Spring Boot para gestión de solicitudes de crédito</description>

    <properties>
        <java.version>21</java.version>
        <springdoc-openapi-ui.version>2.6.0</springdoc-openapi-ui.version>
        <lombok.version>1.18.34</lombok.version>
        <h2.version>2.2.224</h2.version>
        <postgresql.version>42.7.3</postgresql.version>
        <mockito.version>5.12.0</mockito.version>
    </properties>

    <dependencies>
        <!-- Spring Boot Starters -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-configuration-processor</artifactId>
            <optional>true</optional>
        </dependency>

        <!-- OpenAPI Documentation -->
        <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
            <version>${springdoc-openapi-ui.version}</version>
        </dependency>

        <!-- Database -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>${h2.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.postgresql</groupId>
            <artifactId>postgresql</artifactId>
            <version>${postgresql.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- Testing -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <version>${mockito.version}</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <version>${mockito.version}</version>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <profiles>
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <properties>
                <spring.profiles.active>dev</spring.profiles.active>
            </properties>
        </profile>
        <profile>
            <id>prod</id>
            <properties>
                <spring.profiles.active>prod</spring.profiles.active>
            </properties>
        </profile>
    </profiles>
</project>

// === ARCHIVO: src/main/java/com/evaluacion/springboot/Application.java ===
package com.evaluacion.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.evaluacion.springboot.infrastructure.config.AppProperties;

import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
@EnableTransactionManagement
@EnableAsync
public class Application {
    
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        loggingFilter.setIncludeHeaders(true);
        return loggingFilter;
    }
    
    @Bean
    public String applicationInfo(AppProperties properties) {
        System.out.println("\n===========================================\n");
        System.out.println("Aplicación: " + properties.getApplicationName());
        System.out.println("Ambiente: " + properties.getEnvironment());
        System.out.println("Versión: " + properties.getVersion());
        System.out.println("Tamaño máximo de solicitud: " + properties.getMaxRequestSize() + " MB");
        System.out.println("Idempotencia habilitada: " + properties.isIdempotencyEnabled());
        System.out.println("===========================================\n");
        return "Application info logged";
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: credit-request-evaluation
  
  profiles:
    active: dev
    
  datasource:
    url: jdbc:h2:mem:creditdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: ""
    hikari:
      connection-timeout: 30000
      maximum-pool-size: 10
      minimum-idle: 5
      idle-timeout: 600000
      max-lifetime: 1800000
      pool-name: CreditRequestHikariPool
      
  jpa:
    open-in-view: false
    hibernate:
      ddl-auto: update
    show-sql: false
    properties:
      hibernate:
        format_sql: true
        dialect: org.hibernate.dialect.H2Dialect
        jdbc:
          batch_size: 50
          fetch_size: 50
        order_inserts: true
        order_updates: true
        connection:
          provider_disables_autocommit: true

  h2:
    console:
      enabled: true
      path: /h2-console
      settings:
        web-allow-others: true

  mvc:
    async:
      request-timeout: 30000

server:
  port: 8080
  servlet:
    context-path: /api/credit
  tomcat:
    max-swallow-size: -1
    max-http-form-post-size: 20MB
    max-threads: 200
    min-spare-threads: 10

management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics,prometheus,env,loggers
  endpoint:
    health:
      show-details: always
      probes:
        enabled: true
  info:
    env:
      enabled: true
  metrics:
    tags:
      application: ${spring.application.name}

logging:
  level:
    root: INFO
    com.evaluacion.springboot: DEBUG
    org.hibernate.SQL: DEBUG
    org.hibernate.type.descriptor.sql.BasicBinder: TRACE
    org.springframework.web.filter.CommonsRequestLoggingFilter: DEBUG
    org.springframework.transaction: TRACE
  pattern:
    console: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
    file: "%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
  file:
    name: logs/credit-request-evaluation.log
    max-size: 10MB
    max-history: 7

app:
  application-name: Credit Request Evaluation System
  version: 1.0.0
  environment: ${spring.profiles.active}
  max-request-size: 20
  idempotency:
    enabled: true
    key-prefix: credit-request-
    expiration-hours: 24
  antifraud:
    base-url: http://localhost:8081/api/antifraud
    timeout-millis: 2000
    retry:
      max-attempts: 3
      backoff-millis: 500
  risk-bureau:
    base-url: http://localhost:8082/api/risk
    timeout-millis: 3000
    retry:
      max-attempts: 2
      backoff-millis: 1000
  channels:
    - name: MOBILE
      max-requests-per-second: 500
    - name: WEB
      max-requests-per-second: 1000
"

// === ARCHIVO: src/main/java/com/evaluacion/springboot/domain/model/CreditRequest.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/domain/ports/CreditRequestRepository.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/domain/ports/AntifraudService.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/domain/ports/RiskBureauService.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/adapters/JpaCreditRequestRepository.java ===
package com.evaluacion.springboot.infrastructure.adapters;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.domain.ports.CreditRequestRepository;
import com.evaluacion.springboot.infrastructure.repositories.CreditRequestJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Repository
@Transactional
public class JpaCreditRequestRepository implements CreditRequestRepository {
    
    private static final Logger log = LoggerFactory.getLogger(JpaCreditRequestRepository.class);
    
    private final CreditRequestJpaRepository jpaRepository;
    
    public JpaCreditRequestRepository(CreditRequestJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }
    
    @Override
    public CreditRequest save(CreditRequest creditRequest) {
        log.debug("Guardando solicitud de crédito: operationNumber={}, channel={}",
            creditRequest.operationNumber(), creditRequest.channel());
        
        CreditRequestEntity entity = toEntity(creditRequest);
        CreditRequestEntity savedEntity = jpaRepository.save(entity);
        
        log.info("Solicitud de crédito guardada exitosamente: id={}, status={}",
            savedEntity.getId(), savedEntity.getStatus());
        
        return toDomain(savedEntity);
    }
    
    @Override
    public Optional<CreditRequest> findById(UUID id) {
        log.debug("Buscando solicitud de crédito por id: {}", id);
        return jpaRepository.findById(id).map(this::toDomain);
    }
    
    @Override
    public Optional<CreditRequest> findByOperationNumberAndChannel(String operationNumber, String channel) {
        log.debug("Buscando solicitud por operationNumber={}, channel={}", operationNumber, channel);
        return jpaRepository.findByOperationNumberAndChannel(operationNumber, channel)
            .map(this::toDomain);
    }
    
    @Override
    public boolean existsByOperationNumberAndChannel(String operationNumber, String channel) {
        return jpaRepository.existsByOperationNumberAndChannel(operationNumber, channel);
    }
    
    private CreditRequestEntity toEntity(CreditRequest domain) {
        CreditRequestEntity entity = new CreditRequestEntity();
        entity.setId(domain.id());
        entity.setClientId(domain.clientId());
        entity.setClientName(domain.clientName());
        entity.setClientEmail(domain.clientEmail());
        entity.setClientPhone(domain.clientPhone());
        entity.setRequestedAmount(domain.requestedAmount());
        entity.setCurrency(domain.currency());
        entity.setPaymentTermMonths(domain.paymentTermMonths());
        entity.setPurpose(domain.purpose());
        entity.setOperationNumber(domain.operationNumber());
        entity.setChannel(domain.channel());
        entity.setStatus(domain.status().name());
        entity.setRejectionReason(domain.rejectionReason());
        entity.setAntifraudReference(domain.antifraudReference());
        entity.setRiskBureauReference(domain.riskBureauReference());
        entity.setCreatedAt(domain.createdAt());
        entity.setUpdatedAt(domain.updatedAt());
        return entity;
    }
    
    private CreditRequest toDomain(CreditRequestEntity entity) {
        return CreditRequest.builder()
            .id(entity.getId())
            .clientId(entity.getClientId())
            .clientName(entity.getClientName())
            .clientEmail(entity.getClientEmail())
            .clientPhone(entity.getClientPhone())
            .requestedAmount(entity.getRequestedAmount())
            .currency(entity.getCurrency())
            .paymentTermMonths(entity.getPaymentTermMonths())
            .purpose(entity.getPurpose())
            .operationNumber(entity.getOperationNumber())
            .channel(entity.getChannel())
            .status(CreditRequestStatus.valueOf(entity.getStatus()))
            .rejectionReason(entity.getRejectionReason())
            .antifraudReference(entity.getAntifraudReference())
            .riskBureauReference(entity.getRiskBureauReference())
            .createdAt(entity.getCreatedAt())
            .updatedAt(entity.getUpdatedAt())
            .build();
    }
}

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/repositories/CreditRequestJpaRepository.java ===
package com.evaluacion.springboot.infrastructure.repositories;

import com.evaluacion.springboot.infrastructure.adapters.CreditRequestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CreditRequestJpaRepository extends JpaRepository<CreditRequestEntity, UUID> {
    
    Optional<CreditRequestEntity> findByOperationNumberAndChannel(String operationNumber, String channel);
    
    boolean existsByOperationNumberAndChannel(String operationNumber, String channel);
    
    @Query("SELECT cr FROM CreditRequestEntity cr WHERE cr.clientId = :clientId AND cr.status = :status")
    java.util.List<CreditRequestEntity> findByClientIdAndStatus(
        @Param("clientId") String clientId, 
        @Param("status") String status
    );
    
    @Query("SELECT COUNT(cr) FROM CreditRequestEntity cr WHERE cr.createdAt >= :startDate")
    long countRequestsSince(java.time.Instant startDate);
    
    @Query("SELECT cr FROM CreditRequestEntity cr WHERE cr.status = 'PENDING' AND cr.createdAt < :threshold")
    java.util.List<CreditRequestEntity> findStalePendingRequests(java.time.Instant threshold);
}

// === ARCHIVO: src/main/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCase.java ===
package com.evaluacion.springboot.application.usecases;

import com.evaluacion.springboot.domain.model.CreditRequest;
import com.evaluacion.springboot.domain.model.CreditRequestStatus;
import com.evaluacion.springboot.domain.ports.AntifraudService;
import com.evaluacion.springboot.domain.ports.AntifraudService.AntifraudResult;
import com.evaluacion.springboot.domain.ports.CreditRequestRepository;
import com.evaluacion.springboot.domain.ports.RiskBureauService;
import com.evaluacion.springboot.domain.ports.RiskBureauService.RiskBureauResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CreateCreditRequestUseCase {

    private static final Logger log = LoggerFactory.getLogger(CreateCreditRequestUseCase.class);

    private final CreditRequestRepository creditRequestRepository;
    private final AntifraudService antifraudService;
    private final RiskBureauService riskBureauService;

    public CreateCreditRequestUseCase(
            CreditRequestRepository creditRequestRepository,
            AntifraudService antifraudService,
            RiskBureauService riskBureauService) {
        this.creditRequestRepository = creditRequestRepository;
        this.antifraudService = antifraudService;
        this.riskBureauService = riskBureauService;
    }

    @Transactional
    public CreditRequest execute(CreditRequest creditRequest) {
        log.info("Iniciando procesamiento de solicitud de crédito: operationNumber={}, channel={}",
                creditRequest.operationNumber(), creditRequest.channel());

        if (creditRequestRepository.existsByOperationNumberAndChannel(
                creditRequest.operationNumber(), creditRequest.channel())) {
            log.warn("Solicitud duplicada detectada: operationNumber={}, channel={}",
                    creditRequest.operationNumber(), creditRequest.channel());
            return creditRequestRepository
                    .findByOperationNumberAndChannel(
                            creditRequest.operationNumber(), creditRequest.channel())
                    .orElseThrow(() -> new IllegalStateException(
                            "Inconsistencia: existe pero no se puede recuperar"));
        }

        CreditRequest pendingRequest = creditRequest.withStatus(
                CreditRequestStatus.PENDING,
                "Solicitud creada y en espera de evaluación");
        CreditRequest savedRequest = creditRequestRepository.save(pendingRequest);
        log.info("Solicitud guardada con status PENDING: id={}", savedRequest.id());

        try {
            CompletableFuture<AntifraudResult> antifraudFuture =
                    antifraudService.evaluate(savedRequest);
            CompletableFuture<RiskBureauResult> riskBureauFuture =
                    riskBureauService.evaluate(savedRequest);

            CompletableFuture<Void> allFutures =
                    CompletableFuture.allOf(antifraudFuture, riskBureauFuture);

            allFutures.join();

            AntifraudResult antifraudResult = antifraudFuture.get();
            RiskBureauResult riskBureauResult = riskBureauFuture.get();

            CreditRequestStatus finalStatus = determineFinalStatus(antifraudResult, riskBureauResult);
            String finalReason = buildFinalReason(antifraudResult, riskBureauResult);

            CreditRequest evaluatedRequest = savedRequest.withStatus(finalStatus, finalReason);
            CreditRequest finalRequest = creditRequestRepository.save(evaluatedRequest);

            log.info("Solicitud procesada completamente: id={}, status={}",
                    finalRequest.id(), finalRequest.status());

            return finalRequest;

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Proceso de evaluación interrumpido para solicitud: id={}", savedRequest.id(), e);
            CreditRequest errorRequest = savedRequest.withStatus(
                    CreditRequestStatus.ERROR,
                    "Proceso interrumpido durante evaluación");
            return creditRequestRepository.save(errorRequest);
        } catch (ExecutionException e) {
            log.error("Error en evaluación de servicios para solicitud: id={}", savedRequest.id(), e);
            CreditRequest errorRequest = savedRequest.withStatus(
                    CreditRequestStatus.ERROR,
                    "Error en evaluación: " + e.getCause().getMessage());
            return creditRequestRepository.save(errorRequest);
        }
    }

    private CreditRequestStatus determineFinalStatus(
            AntifraudResult antifraudResult, RiskBureauResult riskBureauResult) {

        if (!antifraudResult.approved()) {
            return CreditRequestStatus.REJECTED;
        }
        if (!riskBureauResult.approved()) {
            return CreditRequestStatus.REJECTED;
        }
        if (riskBureauResult.riskLevel() > 70) {
            return CreditRequestStatus.REVIEW_REQUIRED;
        }
        return CreditRequestStatus.APPROVED;
    }

    private String buildFinalReason(AntifraudResult antifraudResult, RiskBureauResult riskBureauResult) {
        StringBuilder reason = new StringBuilder();

        if (!antifraudResult.approved()) {
            reason.append("Rechazada por antifraude: ").append(antifraudResult.reason());
        } else if (!riskBureauResult.approved()) {
            reason.append("Rechazada por buró de riesgos: ").append(riskBureauResult.reason());
        } else if (riskBureauResult.riskLevel() > 70) {
            reason.append("Requiere revisión manual. Nivel de riesgo: ")
                    .append(riskBureauResult.riskLevel());
        } else {
            reason.append("Aprobada automáticamente. Score bureau: ")
                    .append(riskBureauResult.score());
        }

        return reason.toString();
    }
}

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/adapters/AntifraudServiceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/adapters/RiskBureauServiceAdapter.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestController.java ===
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

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/config/IdempotencyConfig.java ===
package com.evaluacion.springboot.infrastructure.config;


import com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
public class IdempotencyConfig {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyConfig.class);
    private static final long CACHE_EXPIRY_MILLIS = 3600000;
    private static final int MAX_CACHE_ENTRIES = 10000;

    private final Map<String, IdempotencyCacheEntry> idempotencyCache = new ConcurrentHashMap<>();
    private final ReentrantLock cacheCleanupLock = new ReentrantLock();

    @Around("execution(* com.evaluacion.springboot.application.usecases.CreateCreditRequestUseCase.execute(..))")
    public Object checkIdempotency(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        
        if (args.length < 2) {
            log.debug("Método no requiere verificación de idempotencia");
            return joinPoint.proceed();
        }
        
        String operationNumber = extractOperationNumber(args);
        String channel = extractChannel(args);
        String idempotencyKey = extractIdempotencyKey(args);
        
        if (operationNumber == null || channel == null) {
            log.debug("Parámetros de idempotencia no proporcionados, continuando con ejecución normal");
            return joinPoint.proceed();
        }
        
        String cacheKey = buildCacheKey(operationNumber, channel);
        
        if (idempotencyKey != null && !idempotencyKey.isBlank()) {
            cacheKey = buildCacheKey(idempotencyKey, channel);
        }
        
        log.debug("Verificando idempotencia para clave: {}", cacheKey);
        
        IdempotencyCacheEntry existingEntry = idempotencyCache.get(cacheKey);
        
        if (existingEntry != null) {
            if (System.currentTimeMillis() - existingEntry.timestamp > CACHE_EXPIRY_MILLIS) {
                log.info("Entrada de caché expirada para clave: {}, removiendo", cacheKey);
                idempotencyCache.remove(cacheKey);
            } else if (existingInProgress(cacheKey)) {
                log.warn("Solicitud con clave de idempotencia {} ya está en progreso", cacheKey);
                throw new IllegalStateException("Request with this idempotency key is already being processed");
            } else {
                log.info("Respuesta cacheada encontrada para clave: {}", cacheKey);
                return existingEntry.result;
            }
        }
        
        if (idempotencyCache.size() >= MAX_CACHE_ENTRIES) {
            cleanupExpiredEntries();
        }
        
        markInProgress(cacheKey);
        
        try {
            Object result = joinPoint.proceed();
            
            idempotencyCache.put(cacheKey, new IdempotencyCacheEntry(result, System.currentTimeMillis()));
            log.info("Resultado almacenado en caché de idempotencia para clave: {}", cacheKey);
            
            return result;
            
        } finally {
            removeInProgress(cacheKey);
        }
    }

    private String extractOperationNumber(Object[] args) {
        if (args.length > 0 && args[0] != null) {
            try {
                return args[0].getClass().getMethod("operationNumber").invoke(args[0]).toString();
            } catch (Exception e) {
                log.debug("No se pudo extraer operationNumber del argumento");
            }
        }
        return null;
    }

    private String extractChannel(Object[] args) {
        if (args.length > 0 && args[0] != null) {
            try {
                return args[0].getClass().getMethod("channel").invoke(args[0]).toString();
            } catch (Exception e) {
                log.debug("No se pudo extraer channel del argumento");
            }
        }
        return null;
    }

    private String extractIdempotencyKey(Object[] args) {
        if (args.length > 1 && args[1] != null) {
            return args[1].toString();
        }
        return null;
    }

    private String buildCacheKey(String operationNumber, String channel) {
        return operationNumber + ":" + channel;
    }

    private boolean existingInProgress(String cacheKey) {
        return idempotencyCache.containsKey(cacheKey + ":inProgress");
    }

    private void markInProgress(String cacheKey) {
        idempotencyCache.put(cacheKey + ":inProgress", 
                new IdempotencyCacheEntry(null, System.currentTimeMillis()));
    }

    private void removeInProgress(String cacheKey) {
        idempotencyCache.remove(cacheKey + ":inProgress");
    }

    private void cleanupExpiredEntries() {
        if (cacheCleanupLock.tryLock()) {
            try {
                long now = System.currentTimeMillis();
                long expiryThreshold = now - CACHE_EXPIRY_MILLIS;
                
                idempotencyCache.entrySet().removeIf(entry -> 
                        entry.getValue().timestamp < expiryThreshold);
                
                log.info("Limpieza de caché de idempotencia completada. Entradas restantes: {}", 
                        idempotencyCache.size());
                
            } finally {
                cacheCleanupLock.unlock();
            }
        }
    }

    public void clearCache() {
        log.info("Limpiando caché de idempotencia");
        idempotencyCache.clear();
    }

    public int getCacheSize() {
        return idempotencyCache.size();
    }

    private static class IdempotencyCacheEntry {
        final Object result;
        final long timestamp;

        IdempotencyCacheEntry(Object result, long timestamp) {
            this.result = result;
            this.timestamp = timestamp;
        }
    }
}

// === ARCHIVO: src/main/java/com/evaluacion/springboot/infrastructure/config/ExceptionHandlerConfig.java ===
package com.evaluacion.springboot.infrastructure.config;

import com.evaluacion.springboot.infrastructure.controllers.dto.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.Instant;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerConfig {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandlerConfig.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {
        
        log.warn("Error de validación en solicitud: {}", ex.getMessage());
        
        String details = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Validation Failed")
                .message("Los datos de la solicitud contienen errores de validación")
                .details(details)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, WebRequest request) {
        
        log.warn("Mensaje HTTP no legible: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Malformed Request Body")
                .message("El cuerpo de la solicitud no tiene el formato esperado")
                .details(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(
            EntityNotFoundException ex, WebRequest request) {
        
        log.warn("Entidad no encontrada: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Entity Not Found")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        
        log.warn("Tipo de argumento inválido: {}", ex.getMessage());
        
        String message = String.format("El parámetro '%s' tiene un valor inválido: '%s'", 
                ex.getName(), ex.getValue());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Type Mismatch")
                .message(message)
                .details(String.format("Se esperaba un tipo: %s", ex.getRequiredType()))
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(
            IllegalArgumentException ex, WebRequest request) {
        
        log.warn("Argumento ilegal: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponse> handleIllegalState(
            IllegalStateException ex, WebRequest request) {
        
        log.warn("Estado ilegal: {}", ex.getMessage());
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.CONFLICT.value())
                .error("Conflict")
                .message(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(
            Exception ex, WebRequest request) {
        
        log.error("Error inesperado procesando solicitud: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ocurrió un error interno al procesar la solicitud")
                .details(ex.getMessage())
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ErrorResponse> handleThrowable(
            Throwable ex, WebRequest request) {
        
        log.error("Error grave no controlado: {}", ex.getMessage(), ex);
        
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error("Internal Server Error")
                .message("Ocurrió un error crítico al procesar la solicitud")
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}

package com.evaluacion.springboot.infrastructure.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada de OpenAPI/Swagger para documentación automática de la API.
 * Expone la documentación en /swagger-ui.html y /v3/api-docs.
 */
@Configuration
public class OpenApiConfig {

    private static final String API_TITLE = "API de Gestión de Solicitudes de Crédito";
    private static final String API_VERSION = "1.0.0";
    private static final String API_DESCRIPTION = """
            API REST para la gestión de solicitudes de crédito con soporte para:
            - Creación de solicitudes de crédito
            - Evaluación anti-fraude
            - Consulta de burós de riesgo
            - Verificación de idempotencia
            - Documentación automática de endpoints
            """;
    private static final String CONTACT_NAME = "Equipo de Desarrollo";
    private static final String CONTACT_EMAIL = "desarrollo@empresa.com";
    private static final String LICENSE_NAME = "Apache 2.0";
    private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    private static final String SECURITY_SCHEME_NAME = "Bearer Authentication";
    private static final String SECURITY_SCHEME_DESCRIPTION = """
            JWT Bearer Token authentication. Obtenga el token mediante el endpoint de autenticación.
            Ejemplo de uso: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
            """;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(buildApiInfo())
                .addSecurityItem(buildSecurityRequirement())
                .components(buildComponents());
    }

    private Info buildApiInfo() {
        return new Info()
                .title(API_TITLE)
                .version(API_VERSION)
                .description(API_DESCRIPTION)
                .contact(buildContact())
                .license(buildLicense());
    }

    private Contact buildContact() {
        return new Contact()
                .name(CONTACT_NAME)
                .email(CONTACT_EMAIL);
    }

    private License buildLicense() {
        return new License()
                .name(LICENSE_NAME)
                .url(LICENSE_URL);
    }

    private SecurityRequirement buildSecurityRequirement() {
        return new SecurityRequirement()
                .addList(SECURITY_SCHEME_NAME);
    }

    private Components buildComponents() {
        return new Components()
                .addSecuritySchemes(SECURITY_SCHEME_NAME, buildSecurityScheme());
    }

    private SecurityScheme buildSecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization")
                .description(SECURITY_SCHEME_DESCRIPTION);
    }
}

// === ARCHIVO: src/main/resources/logback-spring.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>
    <include resource="org/springframework/boot/logging/logback/console-appender.xml"/>
    <include resource="org/springframework/boot/logging/logback/file-appender.xml"/>

    <springProperty scope="context" name="applicationName" source="spring.application.name" defaultValue="springboot-evaluation"/>
    <springProperty scope="context" name="logLevel" source="app.log.level" defaultValue="INFO"/>
    <springProperty scope="context" name="logFilePath" source="app.log.file.path" defaultValue="logs/${applicationName}"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %highlight(%-5level) %cyan(%logger{36}) - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${logFilePath}.log</file>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${logFilePath}.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>10MB</maxFileSize>
            <maxHistory>30</maxHistory>
            <totalSizeCap>1GB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <appender name="ERROR_FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${logFilePath}-error.log</file>
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
            <fileNamePattern>${logFilePath}-error.%d{yyyy-MM-dd}.%i.log</fileNamePattern>
            <maxFileSize>10MB</maxFileSize>
            <maxHistory>60</maxHistory>
            <totalSizeCap>500MB</totalSizeCap>
        </rollingPolicy>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
            <charset>UTF-8</charset>
        </encoder>
    </appender>

    <springProfile name="dev">
        <logger name="com.evaluacion.springboot" level="DEBUG"/>
        <logger name="org.springframework.web" level="DEBUG"/>
        <logger name="org.springframework.boot.actuate" level="INFO"/>
        <logger name="org.hibernate.SQL" level="DEBUG"/>
        <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="TRACE"/>
        <root level="${logLevel}">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>

    <springProfile name="prod">
        <logger name="com.evaluacion.springboot" level="INFO"/>
        <logger name="org.springframework.web" level="WARN"/>
        <logger name="org.springframework.boot.actuate" level="INFO"/>
        <logger name="org.hibernate.SQL" level="WARN"/>
        <logger name="org.hibernate.type.descriptor.sql.BasicBinder" level="WARN"/>
        <root level="WARN">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
            <appender-ref ref="ERROR_FILE"/>
        </root>
    </springProfile>

    <springProfile name="test">
        <logger name="com.evaluacion.springboot" level="DEBUG"/>
        <logger name="org.springframework" level="WARN"/>
        <logger name="org.hibernate" level="WARN"/>
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
        </root>
    </springProfile>

    <springProfile name="!dev &amp; !prod &amp; !test">
        <logger name="com.evaluacion.springboot" level="INFO"/>
        <logger name="org.springframework" level="WARN"/>
        <root level="INFO">
            <appender-ref ref="CONSOLE"/>
            <appender-ref ref="FILE"/>
        </root>
    </springProfile>
</configuration>

// === ARCHIVO: src/test/java/com/evaluacion/springboot/application/usecases/CreateCreditRequestUseCaseTest.java ===
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

// === ARCHIVO: src/test/java/com/evaluacion/springboot/infrastructure/controllers/CreditRequestControllerTest.java ===
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
```
