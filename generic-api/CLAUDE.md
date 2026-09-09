# generic-api

## Purpose

Version-agnostic OCPP API: a single normalised request/response model plus role-based service
interfaces (`CSMSApi`, `CSApi`) that let callers work with OCPP 1.5/1.6/2.0.1 without depending
on any version-specific wire format. Its only project dependency is `:operation-information`.

## Key Patterns

- **One package per OCPP operation** under `com.izivia.ocpp.api.model.<operationname>` (lowercase,
  no separators, e.g. `authorize`, `bootnotification`, `metervalues`). Each package holds exactly
  the `Req`/`Resp` pair for that operation and, when needed, operation-specific value types
  (e.g. `bootnotification/ChargingStationType.kt`, `bootnotification/ModemType.kt`).
- **Req/Resp marker interfaces**: every request data class implements `com.izivia.ocpp.api.model.Request`,
  every response implements `com.izivia.ocpp.api.model.Response` (both are empty marker interfaces).
  This lets `CSMSApi.send(meta, request: Request): OperationExecution<Request, Response>` dispatch
  generically via a `when (request) { is XReq -> ... }` block.
- **Enumerations** live in an `enumeration` sub-package of the operation (occasionally spelled
  `enumerations`, e.g. `deletecertificate/enumerations` — check the actual sub-package name before
  adding a file). They are plain Kotlin `enum class` with a `val value: String` matching the OCPP
  wire value, and are almost always named `<Concept>EnumType` (a few pre-2.0.1-heritage exceptions
  exist, e.g. `statusnotification.enumeration.ChargePointErrorCode` without the suffix).
- **Shared/common value types** (used by 2+ operations, e.g. `IdTokenType`, `EVSEType`,
  `MeterValueType`, and their enums) live in `model/common` and `model/common/enumeration`, not in
  an individual operation package.
- **Role-based facades**: `CSMSApi` declares the operations a Charging Station sends *to* a CSMS
  (Authorize, BootNotification, MeterValues, TransactionEvent, …); `CSApi` declares the mirror
  operations a CSMS sends *to* a Charging Station (ChangeAvailability, ClearCache, GetBaseReport, …).
  Every method has the signature `fun opName(meta: RequestMetadata, request: XReq): OperationExecution<XReq, XResp>`.
- **`send` dispatcher extension**: each of `CSMSApi.kt`/`CSApi.kt` defines a top-level `fun XApi.send(meta, request: Request): OperationExecution<Request, Response>`
  with an exhaustive `when (request) { is ...Req -> ... }` over every operation of that role, `@Suppress("UNCHECKED_CAST")`
  cast at the end, `else -> throw IllegalStateException()`. **This must be updated whenever an operation is added.**
- **`impl/` default implementations**: `DefaultCSMSApi` implements `CSMSApi` by delegating to an
  injected `CSMSApiCallbacks` interface (the actual business logic hook) and wrapping the result as
  `OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, kotlin.time.Clock.System.now()), request, callbacks.xxx(request))`.
  There is currently no `DefaultCSApi` counterpart in this module.
- **`testFixtures` source set**: exposes Kotlin builder DSLs (e.g. `TransactionEventReqBuilder`,
  annotated `@DslMarker`/`@TransactionDsl`) for constructing complex `Req` objects in tests.
  Every `ocpp-X-api-adapter` module consumes this via `testImplementation(testFixtures(project(":generic-api")))`.

## Conventions

- **Nullability mirrors the OCPP spec**: optional fields are nullable with `= null` default;
  mandatory fields are non-null with no default. Do not add defaults to mandatory fields.
- **Timestamps** always use `kotlin.time.Instant`, never `java.time.*` or raw strings.
- **Data classes only** for Req/Resp/value types — no behavior, no validation logic, just structure.
- Package/class names follow the OCPP spec's operation and type names (PascalCase types, the
  operation package itself lowercase-concatenated).

## Adding a New Operation

1. Create `model/<operationname>` (lowercase) with `<Operation>Req.kt` (implements `Request`) and
   `<Operation>Resp.kt` (implements `Response`).
2. Add an `enumeration` sub-package for any enums unique to this operation; reuse types from
   `model/common`/`model/common/enumeration` instead of duplicating shared types.
3. Add the method signature to the appropriate role interface — `CSMSApi.kt` (CS→CSMS) or
   `CSApi.kt` (CSMS→CS) — following the `(meta: RequestMetadata, request: XReq): OperationExecution<XReq, XResp>` shape.
4. Add the matching `is XReq -> methodName(meta, request)` branch to that interface's `send` extension function.
5. Implement the method in `impl/DefaultCSMSApi.kt` (or the callbacks interface it delegates to),
   returning `OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, now()), request, callbacks.xxx(request))`.
6. Add a `testFixtures` builder if the operation's request is complex enough that adapter-module
   tests will need one.
7. Update the mapping layer in every relevant `ocpp-X-api-adapter` module so the new generic type
   maps to/from the version-specific `ocpp-X-core` type — see `docs/API-ADAPTER.guidelines.md`.

## Gotchas

- With Kotlin Gradle Plugin 2.x, `testFixturesImplementation` no longer extends `implementation`.
  This module is the one with `java-test-fixtures` enabled, so if `testFixtures` code fails to
  resolve a dependency that `main` already has, re-declare it explicitly for `testFixtures`.

## Relationship to Other Modules

- Depends only on `:operation-information` (for `RequestMetadata`, `OperationExecution`,
  `ExecutionMetadata`, `RequestStatus`).
- Is the **mapping target** for every `ocpp-X-api-adapter` module: adapters use MapStruct to convert
  between these generic types and version-specific `ocpp-X-core` types. See
  [docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md) for the adapter-side conventions.
- Published to Maven Central as `ocpp-generic-api` (see `build.gradle.kts`).

## See Also

- [docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
- [docs/CONVENTIONS.md](../docs/CONVENTIONS.md)
- [docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md)
