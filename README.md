# Evaluación de Competencia en Spring Boot

El candidato debe demostrar su habilidad para utilizar Spring Boot en un entorno de desarrollo backend. El problema consiste en crear una aplicación que gestione solicitudes de clientes, controle variables de entorno, genere registros de trazas y se conecte a una base de datos utilizando un ORM. Los actores involucrados son el 'originador de créditos', el'motor antifraude' y el 'buró de riesgos'. La aplicación debe manejar un volumen de 1 500 solicitudes por segundo en hora pico y garantizar la idempotencia del registro de solicitudes por número de operación y canal.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Evaluación de Competencia en Frameworks para Desarrollador Backend Advanced |
| **Nivel** | advanced-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Configuración del Entorno

**Objetivo:** Establecer un entorno de desarrollo utilizando Spring Boot.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Configurar el proyecto Spring Boot para incluir las dependencias necesarias.
- Definir las variables de entorno para la aplicación.

**Entregable:** Proyecto Spring Boot configurado con las dependencias y variables de entorno necesarias.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la gestión de dependencias y variables de entorno en Spring Boot.

</details>

### Fase 2: Enrutamiento de Solicitudes

**Objetivo:** Implementar el enrutamiento de solicitudes utilizando Spring Boot.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Crear controladores para manejar las solicitudes entrantes.
- Asegurar que las solicitudes se enruten correctamente a los servicios correspondientes.

**Entregable:** Controladores implementados que enrutan las solicitudes a los servicios correspondientes.

<details>
<summary>Pistas de conocimiento</summary>

- Piensa en cómo manejarías las solicitudes duplicadas para garantizar la idempotencia.

</details>

### Fase 3: Conexión con Base de Datos

**Objetivo:** Conectar la aplicación a una base de datos utilizando un ORM.

**Tiempo estimado:** 3 horas

**Instrucciones:**

- Configurar la conexión a la base de datos.
- Implementar el mapeo de entidades utilizando un ORM.

**Entregable:** Aplicación conectada a una base de datos con entidades mapeadas utilizando un ORM.

<details>
<summary>Pistas de conocimiento</summary>

- Considera las mejores prácticas para la gestión de conexiones y transacciones en tu ORM.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué es Spring Boot y para qué se utiliza en el desarrollo backend?
- **paraQueSirve**: ¿Cómo se utiliza Spring Boot para enrutar solicitudes y gestionar variables de entorno?
- **comoSeUsa**: ¿Cómo se conecta una aplicación Spring Boot a una base de datos utilizando un ORM?
- **erroresComunes**: ¿Cuáles son los errores comunes al configurar Spring Boot y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones arquitectónicas implica el uso de Spring Boot en un proyecto backend?

## Criterios de Evaluacion

- Configuración correcta del entorno Spring Boot.
- Implementación efectiva del enrutamiento de solicitudes.
- Conexión exitosa a la base de datos utilizando un ORM.
- Gestión adecuada de variables de entorno y dependencias.
- Implementación de idempotencia en el registro de solicitudes.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
