# Technology Stack

What each dependency is here for, and which module owns it. Exact versions live in
[`versions.properties`](../versions.properties) — never quoted here, and never pinned anywhere else.

## Language and build

| Concern | Choice | Notes |
|---|---|---|
| Language | Kotlin/JVM | `kotlin.code.style=official` |
| Bytecode target | Java 21 | set in the root `build.gradle.kts` for both `JavaCompile` and `KotlinCompile` |
| Language / API level | Kotlin 2.2 | pinned via `languageVersion` / `apiVersion`, deliberately **behind** the compiler version in `versions.properties` — a conservative floor for consumers |
| Compiler args | `-Xjsr305=strict`, `-opt-in=kotlin.RequiresOptIn` | strict nullability for Java annotations |
| Build tool | Gradle (Kotlin DSL) via `./gradlew` | 25 modules, listed in `settings.gradle.kts`, plus `buildSrc` |
| Version management | [refreshVersions](https://splitties.github.io/refreshVersions/) | coordinates end in `:_`; versions resolved from `versions.properties` |
| Build scans | Gradle Develocity | configured in `settings.gradle.kts` |
| Shared build logic | `buildSrc` | `kotlinProject()` / `coreProject()` convention functions |
| Annotation processing | kapt | in the `*-api-adapter` and `*-api` modules, for MapStruct |

## Runtime libraries

| Library | Used by | Why |
|---|---|---|
| **http4k** (core, client-websocket, client-apache, server-undertow, api-openapi) | `toolkit`, `ocpp-wamp`, both transports | HTTP/WebSocket server and client abstraction. A BOM is applied via `coreProject()`. |
| **Undertow** | `ocpp-wamp`, `ocpp-transport-soap` | the actual embedded server behind http4k |
| **OkHttp** | `ocpp-wamp` | the WebSocket *client*. See the gotcha below. |
| **Jackson** (core, databind, module-kotlin, dataformat-xml, datatype-jsr310) | `ocpp-json`, `ocpp-soap`, `utils`, per-version parsers | JSON and XML (de)serialisation. `dataformat-xml` is the SOAP side; `module-kotlin` gives data-class support. |
| **networknt json-schema-validator** | `ocpp-json`, `utils` | validates payloads against the official OCPP JSON schemas. Draft V4 for OCPP 1.5/1.6, V6 for 2.0.1. |
| **MapStruct** (+ processor via kapt) | the `*-api-adapter` modules | generates the generic-API ↔ version-core mappers |
| **`kotlin.time`** (stdlib) | almost everywhere | `kotlin.time.Instant` is the single timestamp type across the toolkit, and `kotlin.time.Clock` the single clock. No external date-time dependency: `kotlinx-datetime` was dropped in favour of the stdlib types. `utils` supplies the Jackson module (`KotlinInstantModule`) for them. |
| **kotlinx-coroutines** | `ocpp-wamp` | per-message dispatch on `Dispatchers.Default` |
| **kotlin-reflect** | via `coreProject()` | used by parsers for class-token dispatch |
| **commons-lang3** | `utils` | small string/reflection helpers |
| **SLF4J API** + **kotlin-logging** | via `coreProject()` | logging facade. No binding is imposed on consumers. |
| **Logback** | `ocpp-wamp` and the adapters, `runtimeOnly` | a binding for tests and demos only — `runtimeOnly` so it does not leak into consumers' classpaths |

## Test libraries

| Library | Role |
|---|---|
| **JUnit 5 (Jupiter)** | test engine; `useJUnitPlatform()` is forced by `coreProject()` |
| **junit-platform-launcher** | declared **explicitly** — Gradle 9 no longer adds it implicitly, and test discovery silently breaks without it |
| **Strikt** | the primary assertion library |
| **MockK** | the primary mocking library |
| **mockito-kotlin** | used only by `toolkit`; new tests should prefer MockK |

## Publishing

`maven-publish` + `signing` + `io.github.gradle-nexus.publish-plugin`, targeting Maven Central via
Sonatype. See [DEPLOYMENT.md](DEPLOYMENT.md).

## Gotchas and loose ends

- **OkHttp is version-pinned inline.** `ocpp-wamp/build.gradle.kts` declares
  `com.squareup.okhttp3:okhttp` with a literal version instead of the `:_` placeholder, so it is
  the one dependency refreshVersions does not manage. It will silently go stale.
- **The protobuf Gradle plugin is declared but unused.** The root `build.gradle.kts` has
  `id("com.google.protobuf") ... apply false` and nothing applies it. Dead configuration.
- **Two HTTP client stacks coexist.** `ocpp-wamp` uses OkHttp for its WebSocket client while the
  rest of the toolkit goes through http4k (which itself pulls an Apache client). Consumers get
  both on the classpath.
- **No static analysis in the build.** There is no ktlint, detekt or spotless task; style is
  enforced only by `.editorconfig` and the IDE. See [CONVENTIONS.md](CONVENTIONS.md).

## See also

- [ARCHITECTURE.md](ARCHITECTURE.md) — how the modules fit together
- [DEVELOPMENT.md](DEVELOPMENT.md) — build and test commands
- [`../buildSrc/CLAUDE.md`](../buildSrc/CLAUDE.md) — the convention functions
