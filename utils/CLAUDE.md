# utils

## Purpose
Shared, protocol-version-agnostic building blocks (published as the `ocpp-utils` Maven artifact) used across nearly every other module: OCPP error-code modeling, exception hierarchy, Jackson `kotlin.time.Instant` (de)serialization, and generic JSON-node manipulation helpers used by OCPP-J field-coercion/validation logic.

## Key Patterns

- **`MessageErrorCode` enum is the single source of truth for OCPP error semantics.** Each constant carries the wire-format `errorCode` string and a human-readable `description`. `MessageErrorCode.fromValue(String)` does case-insensitive lookup and throws `IllegalArgumentException` if no match is found — do not add ad-hoc string-to-enum mapping elsewhere.
- **Exception hierarchy is centered on `OcppParserException`** (`Exceptions.kt`), an open `RuntimeException` carrying `message`, `errorCode: MessageErrorCode`, optional `messageId`, and optional `errorDetails: List<ErrorDetail>`. Concrete subclasses (`ValidationException`, `FormatViolationException`, `ActionRequestNullOrUnknownException`, `MessageTypeException`, `MalformedOcppMessageException`, `MissingRelatesToHeaderException`, `ActionResponseNotSpecifiedException`) each hard-wire a specific `MessageErrorCode` and automatically prepend a corresponding `ErrorDetail` (code + description from that `MessageErrorCode`) ahead of any caller-supplied `errorDetails`. Callers only pass `message`, `messageId`, and optionally extra `errorDetails`.
- **`Instant` (kotlin.time) Jackson support** is provided via `InstantSerializer`/`InstantDeserializer` bundled into `KotlinInstantModule` (a Jackson `SimpleModule`). Any `ObjectMapper` used to (de)serialize OCPP payloads across the codebase should register this module rather than relying on Jackson's default Instant handling. `InstantSerializer` always truncates to millisecond precision before writing (OCPP wire format has no use for sub-millisecond timestamps); `InstantDeserializer` parses via `Instant.parse` and throws `IllegalStateException` if the parser is null.
- **`HasActionTimestamp`** is a marker interface (`val timestamp: Instant?`) implemented by generated OCPP request/response DTOs in the `*-core` modules that carry a payload timestamp field — used for generic timestamp extraction without knowing the concrete request type.
- **JSON-node field-coercion helpers** (`Commons.kt`) implement rule-based post-processing of parsed Jackson `ObjectNode` trees before/around OCPP-J validation:
  - `AbstractIgnoredNullRestriction` + `parseNullField()` / `setDefaultNullValue()`: when a message is missing a required field, injects a caller-provided default value at the given `fieldPath` (dot-separated by default) and records an `ErrorDetail` (`MISSING_FIELD_REPLACED`) via a callback.
  - `AbstractForcedFieldType` + `parseFieldToConvert()` / `convertField()`: coerces a field to a target `TypeConvertEnum` (`STRING`, `SET`, `LIST`), recording `CONVERT_FIELD_REPLACED` or `CONVERT_FIELD_EXCEPTION` `ErrorDetail`s via the same callback pattern.
  - Both rule types extend `DefaultImplementation`/`InterfaceFieldOption`, which derive the OCPP action body key (`"${action.value}Request"` / `"...Response"`) from an `IActions` reference plus an `ActionTypeEnum`. `IActions` (implemented by generated per-version action enums in `*-core`/`*-api` modules) exposes `classRequest`/`classResponse`/`initiatedBy: OcppInitiator`.
  - These helpers are consumed by version-specific `*-json` parser modules that need lenient handling of malformed/legacy OCPP payloads; see [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) for how they're wired into parsing.
- **`Fault`** (`fault/Fault.kt`) models a SOAP-style fault (`errorCode`, `errorDescription`, `errorDetails: List<ErrorDetail>`); `FAULT` constant holds the literal `"Fault"` tag name used by SOAP transport/serialization code.
- **`isA` extension** (`Any.isA<T> { ... }`) is a small reified-generic helper to conditionally apply a transform only if the receiver is of type `T`, otherwise returning the receiver unchanged.

## Conventions

- All types live directly under `com.izivia.ocpp.utils` (single flat package), with the sole exception of `fault/Fault.kt` in the `com.izivia.ocpp.utils.fault` subpackage.
- Every custom `OcppParserException` subclass follows the same three-argument shape (`message`, `messageId`, `errorDetails = emptyList()`) and never accepts an explicit `errorCode` — the mapping to `MessageErrorCode` is fixed per exception type. When adding a new parser-level failure case, prefer reusing/extending this hierarchy rather than throwing raw exceptions.
- Error/detail codes are always enums with an explicit wire-format string (`errorCode`/`value` field) rather than raw strings scattered through the code — extend `MessageErrorCode` or `ErrorDetailCode` instead of hardcoding new string literals.
- Field-coercion rule callbacks always take the shape `(code: ErrorDetailCode, detail: String) -> Unit` and accumulate into a `List<ErrorDetail>` that is only returned if non-empty (`takeIf { it.size > 0 }` / `null` otherwise) — preserve this "null means nothing changed" contract if extending `Commons.kt`.

## Adding New Functionality

1. **New OCPP error code**: add a constant to `MessageErrorCode` with its wire-format string and description; if it needs a dedicated exception type, add a subclass in `Exceptions.kt` following the existing three-arg pattern.
2. **New parser-level exception**: extend `OcppParserException` in `Exceptions.kt`, hard-wiring the appropriate `MessageErrorCode` and auto-prepending its `ErrorDetail`, matching the existing subclasses.
3. **New generic (version-agnostic) JSON helper**: add it to `Commons.kt` (or a new file in this package) only if it must be shared by more than one `*-json` module; version-specific logic belongs in the corresponding `ocpp-X-json` module instead (see [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md)).
4. Do not introduce a dependency on any `ocpp-*-core`/`ocpp-*-api` module here — `utils` sits at the bottom of the dependency graph and must remain protocol-version-agnostic.

## See Also
- [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) — how `*-json` modules consume the field-coercion helpers in `Commons.kt`
- [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md) — how `*-core` modules implement `HasActionTimestamp` and `IActions`
