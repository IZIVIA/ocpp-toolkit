# ocpp-2-0-json

## Purpose

OCPP-J (JSON over WebSocket) wire format for OCPP 2.0.1: binds the shared parsing machinery in
`ocpp-json` to the `ocpp-2-0-core` model, and validates every payload against the official
OCPP 2.0.1 JSON schemas shipped in this module's resources.

This module is an instance of the `json` layer — see
[../docs/JSON.guidelines.md](../docs/JSON.guidelines.md) for the conventions shared by
`ocpp-1-5-json`, `ocpp-1-6-json` and `ocpp-2-0-json`.

Published as `ocpp-2-0-json`. Consumed by `ocpp-transport-websocket`.

## Contents

Package `com.izivia.ocpp.json20`:

- `Ocpp20JsonParser` — extends `OcppJsonParser`, supplying the four version-specific hooks.
- `Ocpp20JsonObjectMapper` — an `internal object` extending `OcppJsonMapper` with the Jackson
  mix-ins this version needs.
- `src/main/resources/` — the official OCPP 2.0.1 JSON schemas (~127 files), one per
  request/response, loaded by action name at validation time.

## Key patterns

- **`Actions` enum is the single dispatch table.** All four parser hooks
  (`getRequestPayloadClass`, `getResponsePayloadClass`, `getActionFromClass`, `validateJson`)
  resolve through `com.izivia.ocpp.core20.model.common.enumeration.Actions`, keyed by
  `Actions.valueOf(action.uppercase())`. A new operation is unreachable over OCPP-J until it is
  registered there.
- **Schema files are matched by name, not path.** `OcppJsonValidator` looks up
  `<Action><Suffix>.json` in resources, so a schema file whose name doesn't match the
  `Actions.camelCase()` value fails validation with a confusing "schema not found" error.
- **Validation is opt-out.** `enableValidation: Boolean = true` in the constructor; passing
  `false` drops the `OcppJsonValidator` entirely (`takeIf { enableValidation }`). Useful when
  talking to a non-conforming peer, at the cost of losing all schema checking.
- **Three escape hatches for non-conforming peers**, all forwarded to `OcppJsonParser`:
  `ignoredNullRestrictions` (accept nulls the schema forbids), `forcedFieldTypes` (coerce a
  field's JSON type before mapping), `ignoredValidationCodes` (suppress specific
  `ValidatorTypeCode`s). These are version-typed — `Ocpp20IgnoredNullRestriction` etc. come
  from `ocpp-2-0-core`'s `DeserializeOptions.kt`.
- **Enum `@JsonValue` mix-ins.** The object mapper attaches mix-ins so enums serialise to their
  wire `value` string rather than the Kotlin constant name. Adding an enum whose wire form
  differs from its constant name means adding a mix-in here.

## OCPP 2.0.1 specifics

- JSON Schema draft: **`SpecVersion.VersionFlag.V6`**.
- Request schema name for a `CALL`: **``${action}Request``**; response schema is always `${action}Response`.
- `CALL_ERROR` messages skip validation entirely (early `return` in `validateJson`).

- The largest schema set of the three (~127 files), reflecting 2.0.1's device-model and
  variable-monitoring operations.
- The forced-field-type option is spelled `Ocpp20ForceTypeField` here, not
  `Ocpp20ForcedFieldType` — an inconsistency with the 1.5/1.6 naming.

## Testing

Tests live in `src/test/kotlin/com/izivia/ocpp/json20/`. `JsonSchemaTest` is the important one:
it round-trips model instances through the parser and asserts they satisfy the shipped schemas.
Treat a failure there as a real protocol-conformance bug, not a test-data problem.

## See also

- [../ocpp-json/CLAUDE.md](../ocpp-json/CLAUDE.md) — the shared parse/serialise/validate pipeline
- [../ocpp-2-0-core/CLAUDE.md](../ocpp-2-0-core/CLAUDE.md) — the models and the `Actions` registry
- [../ocpp-transport-websocket/CLAUDE.md](../ocpp-transport-websocket/CLAUDE.md) — parser selection per version
