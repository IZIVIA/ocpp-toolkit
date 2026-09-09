# JSON Layer Guidelines

## Purpose

The `json` layer implements OCPP-J (JSON over WebSocket) for a given protocol version: it binds
the shared parsing/validation machinery in `ocpp-json` to a version's `core` model, and ships the
official OCPP JSON schemas used to validate every payload on the wire.

Instances: `ocpp-1-5-json`, `ocpp-1-6-json`, `ocpp-2-0-json` — one per supported OCPP version.
OCPP-J is the only wire format for 2.0.1 (there is no `ocpp-2-0-soap`).

```mermaid
flowchart LR
    Wire["Raw JSON frame"] --> Parser["Ocpp&lt;NN&gt;JsonParser"]
    Parser -- "Actions.valueOf(action)" --> Dispatch["core&lt;NN&gt;.Actions enum"]
    Dispatch --> ReqClass["getRequestPayloadClass /\ngetResponsePayloadClass"]
    Parser --> Validator["OcppJsonValidator"]
    Validator -- "loads by name" --> Schema["resources/&lt;Action&gt;&lt;Suffix&gt;.json"]
    Parser --> Mapper["Ocpp&lt;NN&gt;JsonObjectMapper"]
    Mapper -- "@JsonValue mix-ins" --> Model["core&lt;NN&gt; model instance"]
```

## Common Patterns

- **`Actions` enum is the single dispatch table.** All four version-specific hooks required by
  `OcppJsonParser` — `getRequestPayloadClass`, `getResponsePayloadClass`, `getActionFromClass`,
  `validateJson` — resolve through the version's `core<NN>.model.common.enumeration.Actions`
  enum, keyed by `Actions.valueOf(action.uppercase())`. An operation is unreachable over OCPP-J
  until it has an `Actions` entry.
- **Schema files are matched by name, not path.** `OcppJsonValidator` looks up
  `<Action><Suffix>.json` in `src/main/resources/`. A schema file whose name does not exactly
  match the expected `Actions`-derived name fails validation with a confusing "schema not
  found" error rather than a clear naming complaint — get the file name right.
- **Validation is opt-out, not opt-in.** The parser constructor takes
  `enableValidation: Boolean = true`; passing `false` drops the `OcppJsonValidator` entirely via
  `takeIf { enableValidation }`. Useful for talking to a non-conforming peer, at the cost of
  losing all schema checking.
- **Three escape hatches for non-conforming peers**, all forwarded to `OcppJsonParser`:
  - `ignoredNullRestrictions` — accept nulls the schema forbids
  - `forcedFieldTypes` — coerce a field's JSON type before mapping
  - `ignoredValidationCodes` — suppress specific `ValidatorTypeCode`s

  These are version-typed (e.g. `Ocpp16IgnoredNullRestriction`, `Ocpp16ForcedFieldType`) and
  come from the matching `core<NN>` module's `DeserializeOptions.kt`.
- **Enum `@JsonValue` mix-ins.** The object mapper attaches Jackson mix-ins so enums serialise
  to their wire `value` string rather than the Kotlin constant name. An enum whose wire form
  differs from its constant name needs a mix-in here to round-trip correctly.
- **`CALL_ERROR` messages skip validation.** `validateJson` returns early for error frames —
  only `CALL` and `CALL_RESULT` payloads are schema-checked.

## Naming Conventions

- **Package**: `com.izivia.ocpp.json<NN>` (e.g. `com.izivia.ocpp.json16`)
- **Parser class**: `Ocpp<NN>JsonParser`, extends `OcppJsonParser`
- **Mapper object**: `Ocpp<NN>JsonObjectMapper`, `internal object` extending `OcppJsonMapper`
- **Schema files**: `<Action><Suffix>.json` in `src/main/resources/` (suffix conventions differ
  by version — see below)
- **Module name**: `ocpp-<version>-json` (e.g. `ocpp-1-6-json`)

## File Organization

```
ocpp-<version>-json/
├── src/main/kotlin/com/izivia/ocpp/json<NN>/
│   ├── Ocpp<NN>JsonParser.kt        # extends OcppJsonParser, 4 hooks
│   └── Ocpp<NN>JsonObjectMapper.kt  # internal object, Jackson mix-ins
├── src/main/resources/              # official OCPP JSON schemas, one file per Req/Resp
└── src/test/kotlin/com/izivia/ocpp/json<NN>/
    └── JsonSchemaTest.kt            # round-trips models through parser + shipped schemas
```

## Adding an Operation

1. Ensure the operation has a `core<NN>` model (Req/Resp) and an `Actions` entry.
2. Implement `getRequestPayloadClass` / `getResponsePayloadClass` / `getActionFromClass` support
   for the new action in `Ocpp<NN>JsonParser` (usually automatic once `Actions` is updated).
3. Add the official request and response schema files to `src/main/resources/`, named exactly
   per the version's naming convention (see Domain-Specific Variations below).
4. If any field on the new model is an enum with a wire value differing from its constant name,
   add a `@JsonValue` mix-in in `Ocpp<NN>JsonObjectMapper`.
5. Add a case to `JsonSchemaTest` round-tripping the new model through the parser. A failure
   there is a protocol-conformance bug, not a test-data problem — treat it accordingly.

## Common Dependencies

- **`ocpp-json`**: shared `OcppJsonParser`, `OcppJsonMapper`, `OcppJsonValidator` base classes.
- **Jackson**: JSON (de)serialisation, mix-ins for enum wire values.
- **`networknt/json-schema-validator`** (via `ocpp-json`): schema validation against a specific
  JSON Schema draft (version-dependent, see below).
- The matching `core<NN>` module: models, `Actions` enum, `DeserializeOptions.kt`.

## Cross-Version Divergences to Watch

These are real, deliberate differences between instances — do not "fix" one to match another:

- **JSON Schema draft differs**: 1.5 and 1.6 validate against `SpecVersion.VersionFlag.V4`;
  2.0.1 validates against `V6`.
- **CALL request schema name differs**: 1.5 names the request schema after the **bare** action
  (e.g. `Authorize.json`, no `Request` suffix); 1.6 and 2.0.1 use `${action}Request.json`. The
  response schema is always `${action}Response.json` in all three.
- **Forced-field option name differs**: 2.0.1 spells it `Ocpp20ForceTypeField`; 1.5 and 1.6 use
  `Ocpp<NN>ForcedFieldType`. This is a naming inconsistency in the codebase, not a version rule.

## Domain-Specific Variations

Each version's `json` module has its own schema set size and per-version quirks. See:
- [../ocpp-1-5-json/CLAUDE.md](../ocpp-1-5-json/CLAUDE.md)
- [../ocpp-1-6-json/CLAUDE.md](../ocpp-1-6-json/CLAUDE.md)
- [../ocpp-2-0-json/CLAUDE.md](../ocpp-2-0-json/CLAUDE.md)
- [../ocpp-json/CLAUDE.md](../ocpp-json/CLAUDE.md) — the shared parse/serialise/validate pipeline
