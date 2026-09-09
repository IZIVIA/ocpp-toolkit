# ocpp-toolkit

Kotlin/JVM library implementing **OCPP** (Open Charge Point Protocol) for both the CSMS and the
Charging Station side, across OCPP **1.5 / 1.6 / 2.0.1**, over **OCPP-J** (WebSocket/JSON)
and **OCPP-S** (SOAP, 1.x only).

It is a strict protocol implementation with **no business logic** — you use it as a library and
own the behaviour. Published to Maven Central under group `com.izivia`; consumers normally depend
on the `toolkit` module, which aggregates everything else.

## Quick orientation

- 25 Gradle modules plus `buildSrc`, ~1000 Kotlin files. Build with `./gradlew build`
  (JDK 21, wrapper only).
- Modules are named `ocpp-<version>-<layer>`. The five repeated layers are **core**, **api**,
  **api-adapter**, **json** and **soap**; each has a guidelines file in `docs/`.
- The version × layer matrix is not full: there is **no OCPP 2.0.1 SOAP** module, and
  **`ocpp-1-5-api-adapter` is declared in `settings.gradle.kts` but has no sources** — the generic
  API is therefore unavailable for 1.5 (`ApiFactory.getCSMSApi` throws `NotImplementedError` for
  it). `ocpp-1-6-security` is the one module outside the naming scheme: an opt-in extension
  carrying the 1.6 Security Whitepaper operations.
- Every module has its own `CLAUDE.md`. Read the module's file before changing it.

## Documentation Index

### Getting started
- [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md) — prerequisites, daily commands, build-system notes, gotchas
- [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) — module graph, request flows, design decisions, known gaps

### Standards
- [docs/CONVENTIONS.md](docs/CONVENTIONS.md) — naming, packages, nullability, error-handling idioms
- [docs/TESTING.md](docs/TESTING.md) — test strategy by layer, what each failure means
- [docs/TECH_STACK.md](docs/TECH_STACK.md) — what each dependency is for
- [docs/SECURITY.md](docs/SECURITY.md) — security scope, and what is explicitly out of it
- [docs/DEPLOYMENT.md](docs/DEPLOYMENT.md) — release process and Maven Central publishing

### Protocol reference
- [docs/protocol/ACTIONS.md](docs/protocol/ACTIONS.md) — every OCPP action × version, with direction
- [docs/protocol/](docs/protocol/README.md) — per-version field reference: every field of every
  action, with type, constraints, enum values, the Kotlin class, and the section and page of the
  normative OCA document. Committed — grep it, no generation step
- [docs/protocol/spec/](docs/protocol/spec/README.md) — which OCA document specifies a topic, and
  on which page: every heading of all 18 documents
- [docs/protocol/SPECS.md](docs/protocol/SPECS.md) — which edition the vendored schemas match, the
  verified divergences from OCA's schemas, and the licensing

### Layer guidelines
Conventions shared across each family of repeated modules:
- [docs/CORE.guidelines.md](docs/CORE.guidelines.md) — protocol model, operation interfaces, `Actions` registry
- [docs/API.guidelines.md](docs/API.guidelines.md) — charging-station callback surface
- [docs/API-ADAPTER.guidelines.md](docs/API-ADAPTER.guidelines.md) — MapStruct bridge to the generic API
- [docs/JSON.guidelines.md](docs/JSON.guidelines.md) — OCPP-J wire format and schema validation
- [docs/SOAP.guidelines.md](docs/SOAP.guidelines.md) — OCPP-S wire format and envelope handling

### Modules

Facade and version-agnostic API:
- [toolkit/](toolkit/CLAUDE.md) — `ApiFactory` / `CSMS`, the public entry points
- [generic-api/](generic-api/CLAUDE.md) — version-agnostic request/response model

Foundation:
- [operation-information/](operation-information/CLAUDE.md) — shared vocabulary (`OperationExecution`, `RequestStatus`)
- [utils/](utils/CLAUDE.md) — errors, time serialisation, field-coercion helpers
- [buildSrc/](buildSrc/CLAUDE.md) — Gradle convention functions

Transport:
- [ocpp-transport/](ocpp-transport/CLAUDE.md) — `ClientTransport` / `ServerTransport` contracts
- [ocpp-transport-websocket/](ocpp-transport-websocket/CLAUDE.md) — OCPP-J transport
- [ocpp-transport-soap/](ocpp-transport-soap/CLAUDE.md) — OCPP-S transport
- [ocpp-wamp/](ocpp-wamp/CLAUDE.md) — WAMP framing, WebSocket client/server

Wire-format bases:
- [ocpp-json/](ocpp-json/CLAUDE.md) — shared JSON pipeline
- [ocpp-soap/](ocpp-soap/CLAUDE.md) — shared SOAP envelope machinery

Per-version families:
- OCPP 1.5 — [core](ocpp-1-5-core/CLAUDE.md) · [api](ocpp-1-5-api/CLAUDE.md) · [json](ocpp-1-5-json/CLAUDE.md) · [soap](ocpp-1-5-soap/CLAUDE.md) *(api-adapter: module declared, no sources)*
- OCPP 1.6 — [core](ocpp-1-6-core/CLAUDE.md) · [api](ocpp-1-6-api/CLAUDE.md) · [api-adapter](ocpp-1-6-api-adapter/CLAUDE.md) · [json](ocpp-1-6-json/CLAUDE.md) · [soap](ocpp-1-6-soap/CLAUDE.md) · [security](ocpp-1-6-security/CLAUDE.md)
- OCPP 2.0.1 — [core](ocpp-2-0-core/CLAUDE.md) · [api](ocpp-2-0-api/CLAUDE.md) · [api-adapter](ocpp-2-0-api-adapter/CLAUDE.md) · [json](ocpp-2-0-json/CLAUDE.md)

## Things to know before changing code

- **Versions live only in [`versions.properties`](versions.properties).** Dependency coordinates
  end in `:_` (refreshVersions). Do not pin a version in a module's build file.
- **The Gradle daemon is deliberately disabled** in `gradle.properties` with the comment
  `# !!!! causes build issues !!!!`. Do not re-enable it.
- **An operation missing from a core module's `Actions` registry is unreachable over the wire**,
  no matter how complete its model is. This is the most common way to half-implement an operation.
- **`OcppVersion` is declared twice** — `com.izivia.ocpp.transport.OcppVersion` (`ocpp-transport`)
  and `com.izivia.ocpp.OcppVersion` (`ocpp-wamp`) — with identical entries and no derivation
  between them. A new protocol version must be added to both.
- **`ApiFactory`'s companion functions are the only public entry points.** `ocpp16ConnectionToCSMS`
  and friends are `companion object` members, not top-level or constructor calls. The opt-in tests
  in `toolkit` are the runnable reference; the root `README.md` mirrors them.

Start at [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md) for setup, or
[docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) to understand the shape of the system.
