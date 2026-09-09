# Code Conventions

Conventions actually observed in this codebase. Where a rule is enforced by tooling that is said
explicitly; everything else is convention only, so reviewers are the enforcement mechanism.

## Formatting

Governed by [`.editorconfig`](../.editorconfig) and `kotlin.code.style=official` in
[`gradle.properties`](../gradle.properties):

- UTF-8, LF line endings, final newline, no trailing whitespace
- 4-space indent, 8-space continuation indent
- **120-character line limit**

**There is no ktlint, detekt or spotless task in the build.** Nothing fails CI on a style
violation. Configure your IDE from `.editorconfig` and keep diffs clean by hand.

## Module naming

Modules follow `ocpp-<version>-<layer>`, where version is dash-separated (`1-2`, `1-5`, `1-6`,
`2-0`) and layer is one of `core`, `api`, `api-adapter`, `json`, `soap`. Cross-version modules
drop the version segment (`ocpp-json`, `ocpp-soap`, `ocpp-transport`, `ocpp-wamp`).

A new module must be registered in [`settings.gradle.kts`](../settings.gradle.kts) **and** added
to `toolkit/build.gradle.kts`'s `api(project(...))` aggregation, or consumers of the `toolkit`
artifact will not see it.

## Package naming

Root packages are `com.izivia.ocpp.<segment>` where the segment compresses the version with no
separator: `core16`, `api20`, `json15`, `soap12`, `adapter16`. Cross-version modules use a plain
segment: `com.izivia.ocpp.transport`, `com.izivia.ocpp.json`, `com.izivia.ocpp.utils`.

Operation packages under `model/` are **all-lowercase, no separators**: `bootnotification`,
`remotestart`, `statusnotification`. Known exception: `certificateSigned` in `ocpp-2-0-core`
breaks this — do not copy it.

Enumerations live in an `enumeration/` sub-package of their operation package. Known exception:
`deletecertificate/enumerations` (plural) in `generic-api`.

## Type naming

| Kind | Convention | Example |
|---|---|---|
| Request DTO | `<Operation>Req` | `AuthorizeReq` |
| Response DTO | `<Operation>Resp` | `AuthorizeResp` |
| Enum | `<Name>` in 1.x, often `<Name>EnumType` in 2.0.1 / `generic-api` | `AuthorizationStatus`, `AuthorizeCertificateStatusEnumType` |
| MapStruct mapper | `<Operation>Mapper` | `BootNotificationMapper` |
| Version-scoped public type | `Ocpp<NN><Thing>` | `Ocpp16JsonParser`, `Ocpp15SoapBody` |

The `Req`/`Resp` suffixes are **load-bearing, not cosmetic**: `BaseResolver` in `ocpp-soap`
derives outbound XML element names from them, and the JSON parsers derive action names from class
names. Renaming a DTO changes the wire format.

## Enums

Enum constants carry their wire representation as a constructor property:

```kotlin
enum class SomeStatus(val value: String) { Accepted("Accepted"), ... }
```

Serialisation to `value` is wired through Jackson `@JsonValue` mix-ins registered in each
version's object mapper — **not** by annotating the enum itself. A new enum whose wire form
differs from its constant name needs a mix-in in the matching `*JsonObjectMapper` or
`*SoapMapper`.

## Nullability

Optional protocol fields are modelled as nullable Kotlin properties with a `null` default.
Required fields are non-null with no default. This mirrors the OCPP schemas directly and is what
makes schema validation meaningful — do not add defaults to required fields to make construction
convenient.

## Time

`kotlin.time.Instant` everywhere. Never `java.time` in public API. `utils` supplies
`InstantSerializer` / `InstantDeserializer` / `KotlinInstantModule`; note the serializer
truncates to milliseconds.

## Error handling

Three distinct idioms, applied deliberately in different layers:

1. **Parsers return errors as values.** `OcppJsonParser` normalises every failure into a
   `CALL_ERROR` result; `OcppSoapParserImpl` converts parse failures into a `SoapFault` payload.
   Do not let exceptions escape a parser.
2. **Adapters distinguish "unsupported" from "failed".** A whole operation with no counterpart in
   the target OCPP version throws `IllegalStateException`; an individual unsupported *enum value*
   is filtered by an `isSupported(...)` pre-check that yields `RequestStatus.NOT_SEND` and a
   warning log. See [API-ADAPTER.guidelines.md](API-ADAPTER.guidelines.md).
3. **Callback surfaces use `NotImplementedError`.** Every `OcppCSCallbacks` method defaults to
   `throw NotImplementedError()`; implementers override only what they support. See
   [API.guidelines.md](API.guidelines.md).

## The `Actions` registry

Each core module's `model/common/enumeration/Actions.kt` maps a wire action name to its Req/Resp
classes and its initiating role. **Both** the JSON and SOAP parsers dispatch through it. An
operation missing from `Actions` is unreachable over the wire regardless of how complete its
model and interfaces are — this is the most common way to half-implement an operation.

## Layer-specific conventions

Per-layer rules live in the guidelines files rather than here:

- [CORE.guidelines.md](CORE.guidelines.md)
- [API.guidelines.md](API.guidelines.md)
- [API-ADAPTER.guidelines.md](API-ADAPTER.guidelines.md)
- [JSON.guidelines.md](JSON.guidelines.md)
- [SOAP.guidelines.md](SOAP.guidelines.md)

## Documentation

Every module has a `CLAUDE.md` describing its purpose, patterns and gotchas. A new module needs
one. Keep them under ~200 lines and about *patterns*, not file inventories — file lists go stale,
patterns do not.
