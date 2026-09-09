# ocpp-json

## Purpose
Provides the version-agnostic JSON machinery (message model, parser skeleton, mapper, schema validator) shared by every OCPP-J version-specific parser module.

## Role in the Architecture
This module is the **shared base for the `json` scattered layer**, but it is not itself one of its instances — `ocpp-1-5-json`, `ocpp-1-6-json`, and `ocpp-2-0-json` each depend on it and supply the version-specific pieces (action-to-class mapping, schema resources, validation quirks). See [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) for the conventions those consumer modules follow when extending `OcppJsonParser`.

`ocpp-wamp` depends on this module to build the OCPP-J CALL / CALLRESULT / CALLERROR WAMP framing.

## Key Types

- **`JsonMessage<T>`** (`JsonMessage.kt`) — the generic envelope for the three OCPP-J message kinds. Built via named factory functions on its companion object (`JsonMessage.Call`, `JsonMessage.CallResult`, `JsonMessage.CallError`) rather than the raw constructor, so callers don't have to juggle which fields apply to which message type.
- **`JsonMessageType`** — enum `CALL(2) / CALL_RESULT(3) / CALL_ERROR(4)`, mirroring the OCPP-J array's first element. `fromId(Int)` throws `IllegalArgumentException` for unknown ids.
- **`JsonMessageEmptyPayload`** — marker type substituted as payload when a message has a null/blank payload, so serialisation always emits an object (`{}`) instead of `null`.
- **`OcppJsonMapper`** — a preconfigured Jackson `ObjectMapper` (Kotlin module, `KotlinInstantModule` for `kotlin.time.Instant`, `NON_EMPTY` inclusion, unknown properties ignored on deserialize). Version-specific mapper subclasses are expected to extend/reuse this instead of hand-building Jackson config.
- **`OcppJsonValidator`** — wraps `networknt` `JsonSchemaFactory` for a given `SpecVersion.VersionFlag`. Lazily loads and caches one `JsonSchema` per `action` from a classpath resource named `"$action.json"`; forces `Locale.ENGLISH` on validation messages for deterministic error details regardless of server locale.
- **`OcppJsonParser`** (abstract) — the parse/validate/serialise pipeline described below.

## The Parse Pipeline (`OcppJsonParser`)

`parseAnyFromString(messageStr, useClazz)` is the single entry point consumers call. It:

1. Parses the raw string to a `JsonNode` (`parseStringToJsonNode`), then decodes the OCPP-J array shape into a `JsonMessage<JsonNode>` (`parseNodePayload`) based on the first array element (`2/3/4`).
2. Resolves the target payload class:
   - `CALL` → `getRequestPayloadClass(action, errorHandler)` (abstract, implemented per version).
   - `CALL_RESULT` → requires `useClazz` to be passed in (the response class can't be inferred from the message alone); derives the `action` from that class name via `getActionFromClass`.
   - `CALL_ERROR` → maps to `Fault::class` (from `utils`), deriving `action` from `useClazz` when available.
3. Applies pre-mapping fixups from `ignoredNullRestrictions` and `forcedFieldTypes` (both `List<Abstract...>` from `utils`), collecting any adjustments as `warnings` (`List<ErrorDetail>`) rather than failing.
4. Calls the abstract `validateJson(jsonMessage, errorsHandler)` hook — version modules implement this using `OcppJsonValidator.isValidObject(action, payload)` against their bundled schemas. Validation messages whose code is in `ignoredValidationCodes` become warnings instead of hard failures; anything else throws `ValidationException`.
5. Maps the validated `JsonNode` payload to the resolved class with Jackson (`mapJsonNodeToObject`), producing the final `JsonMessage<Any>`.
6. All parsing/validation failures are caught and normalized into a `CALL_ERROR` `JsonMessage` wrapping a `Fault` payload — the parser never throws out of `parseAnyFromString`; callers always get back a `JsonMessage`.

Companion helpers:
- `parseAnyFromJson<T>(messageStr)` — reified convenience wrapper that passes `T::class.java` as `useClazz`.
- `parsePayloadFromJson(payload, clazz)` / `mapPayloadToString(payload)` — direct Jackson passthroughs for callers that already know the target type.
- `mapToJson(message)` — the inverse of the parse pipeline: rebuilds the OCPP-J array (`[type, msgId, ...]`, shape depending on `msgType`) and serializes it, substituting `JsonMessageEmptyPayload` for null/blank payloads.

## What a Version-Specific Parser Must Supply

A concrete `OcppXXJsonParser` in a `json` layer module extends `OcppJsonParser` and implements:

- `getRequestPayloadClass(action, errorHandler)` — action name → `*Req` class for that OCPP version.
- `getResponsePayloadClass(action, errorHandler)` — action name → `*Resp` class (used by callers building `useClazz`, not directly by the pipeline).
- `getActionFromClass(className)` — typically delegates to the provided `getActionFromClassName` helper (strips trailing `Req`/`Resp` and uppercases).
- `validateJson(jsonMessage, errorsHandler)` — wires an `OcppJsonValidator` instance (constructed with the version's `SpecVersion.VersionFlag`) against the message's `action` and `payload`.
- Constructor wiring for `mapper` (an `OcppJsonMapper` or subclass), `ocppJsonValidator`, and optionally `ignoredNullRestrictions` / `ignoredValidationCodes` / `forcedFieldTypes` to accommodate schema quirks specific to that OCPP version.

See [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) for the concrete per-version implementation conventions (schema resource layout, action naming, etc.).

## Conventions

- All parser errors are typed exceptions from `com.izivia.ocpp.utils` (`OcppParserException` subtypes: `MalformedOcppMessageException`, `FormatViolationException`, `MessageTypeException`, `ActionRequestNullOrUnknownException`, `ActionResponseNotSpecifiedException`, `ValidationException`) carrying `messageId` and structured `errorDetails: List<ErrorDetail>` — never raw strings.
- Non-fatal issues (ignored-null fixups, forced-field conversions, ignored validation codes) are surfaced as `warnings` on the returned `JsonMessage`, not exceptions.
- No mutable/global state beyond `OcppJsonValidator`'s per-action schema cache.

## See Also
- [../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) — conventions for the `ocpp-1-5-json` / `ocpp-1-6-json` / `ocpp-2-0-json` consumer modules.
- [../utils](../utils) — `ErrorDetail`, `MessageErrorCode`, `Fault`, `OcppParserException` hierarchy, `AbstractIgnoredNullRestriction`, `AbstractForcedFieldType`, `KotlinInstantModule`.
- [../ocpp-wamp](../ocpp-wamp) — consumes this module for WAMP call framing.
