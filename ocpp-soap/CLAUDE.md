# ocpp-soap

## Purpose
Shared SOAP machinery consumed by every version-specific OCPP-S parser (`ocpp-1-5-soap`, `ocpp-1-6-soap`): the SOAP envelope/header model, the Jackson XML `ObjectMapper` configuration, the generic parse/serialise pipeline (`OcppSoapParserImpl`), and the WS-Addressing SOAP fault model.

This module is the **base** for the `soap` scattered layer, not one of its instances — see [docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) for the conventions each version-specific parser module must follow on top of what's provided here.

## Key Patterns

### Asymmetric envelope model (read vs. write)
XML **deserialization** and **serialization** use distinct data classes because inbound OCPP-S messages use unprefixed field names (no XML namespace awareness required to read them) while outbound messages must emit WS-Addressing/SOAP-prefixed element names (`s:`, `a:`, `o:`):
- Read side: `SoapEnvelope<T : SoapBody>`, `SoapHeader`, `SoapHeaderFrom`, `ValueText` (plain field names, e.g. `MessageID`, `Action`).
- Write side: `SoapEnvelopeOut<T>`, `SoapHeaderOut`, `SoapHeaderFromOut` (namespaced field names, e.g. `a:MessageID`, `s:Body`), plus `xmlns:s` / `xmlns:a` / `xmlns:o` attributes and the OCPP namespace URI itself (`ocpp` field, resolved per-initiator — see below).
- All namespace/element-name string constants live centrally in `OcppConstant` (`HEADER`, `BODY`, `MESSAGE_ID`, `ACTION`, `NS_*` prefixed variants, `SOAP`/`ADDRESSING` URIs). Never hardcode these strings in consumer modules — reuse `OcppConstant`.
- `SoapBody` is an empty marker interface; each version-specific module supplies its own request/response body sealed hierarchy implementing it (e.g. `OcppXXSoapBody`).

### Action-driven type resolution on write (`BaseResolver`)
`SoapEnvelopeOut.body` is annotated with `@JsonTypeIdResolver(BaseResolver::class)` (`JsonTypeInfo.As.WRAPPER_OBJECT`). `BaseResolver.idFromValue` derives the outer XML wrapper element name from the **Kotlin class simple name** of the body payload: classes ending in `Req`/`Resp` become `o:xxxRequest`/`o:xxxResponse`, `SoapFault` becomes `s:Fault`, anything else is wrapped as-is under the `o:` (OCPP) namespace. Version-specific `*Req`/`*Resp` payload classes must follow this naming suffix convention for serialization to produce correct element names — `typeFromId`/`getMechanism` are intentionally unimplemented (`TODO`) since this resolver is write-only.

### Per-initiator OCPP namespace (`OcppNs`)
`OcppSoapParserImpl` is constructed with an `OcppNs(ocppCpNs, ocppCsNs)` pair (Central System vs. Charging Station namespace URIs for that protocol version). `toInitiatorNamespace` picks the URI to stamp into `SoapEnvelopeOut.ocpp` based on `OcppInitiator` — either passed explicitly (`forcedInitiator`) or resolved by the abstract `getOcppInitiator(action: String)` that each subclass must implement (action → sender/receiver direction table).

### Parse pipeline (`OcppSoapParserImpl`)
`parseAnyRequestFromSoap` / `parseAnyResponseFromSoap` are the generic entry points:
1. `readToEnvelop` (abstract) deserializes the raw XML string into a `SoapEnvelope<*>`, collecting Jackson-level `ErrorDetail` warnings via a callback.
2. `applyDeserializerOptions` post-processes the raw `JsonNode` tree using `ignoredNullRestrictions` / `forcedFieldTypes` (constructor-injected `AbstractIgnoredNullRestriction`/`AbstractForcedFieldType` lists from `utils`) — version-specific quirk handling (e.g. legacy fields that must tolerate null, or need type coercion), keyed by action via `isNodeAction`.
3. `getRequestBodyContent` / `getResponseBodyContent` (abstract) extract and cast the actual typed payload out of the generic envelope body — this is where each version's body sealed-class dispatch happens.
4. On any exception during payload extraction, the request/response is still returned but with `payload = buildSoapFault(...)` (a `SoapFault` wrapping a `PROTOCOL_ERROR`), rather than throwing — callers always get a `RequestSoapMessage`/`ResponseSoapMessage`, never an unhandled parse exception.

`mapRequestToSoap` / `mapResponseToSoap` do the inverse: build a `SoapHeaderOut` (action string is capitalized and, for responses, suffixed with `Response`) and a `SoapEnvelopeOut`, then serialize with the injected `soapMapperOutput`.

`parseRequestFromSoap<T>()` / `parseResponseFromSoap<T>()` (reified + `KClass` overloads) are typed convenience wrappers around the `Any`-returning methods, throwing `IllegalArgumentException` if the resolved payload isn't an instance of the expected type.

### Fault handling
`SoapFault` models a WS-SOAP 1.2 `<Fault>` (`Code`/`Reason`/`Value`), with typed enums `FaultCodeValue` (Sender/Receiver), `FaultSubCodeValue`, `FaultReasonTextValue` and companion factories (`securityError()`, `identityMismatch()`, `protocolError()`, `internalError()`, `notSupported()`) for standard OCPP-S fault scenarios.
- `parseSoapFaulted` builds a best-effort `SoapEnvelope<SoapBody>` from a raw XML string that failed to parse, using regex extraction (not the Jackson mapper, since the XML may itself be malformed) to salvage `chargeBoxIdentity`, `messageId`, `action`; the caller supplies `func: (SoapFault) -> SoapBody` to wrap the fault into that version's body type.
- `SoapFault.toFaultReq()` maps a `SoapFault` back to the generic `com.izivia.ocpp.utils.fault.Fault` model (via `MessageErrorCode.fromValue` on the fault subcode), for surfacing faults through the generic OCPP error pipeline.

### Jackson XML mapper configuration (`OcppSoapMapper`)
`OcppSoapMapper` extends `ObjectMapper` wrapping a namespace-aware `XmlMapper` built with a `CustomXmlModule` (`setDefaultUseWrapper(false)`, `setXMLTextElementName("text")`, a `CustomBooleanDeserializer` tolerant of `"1"`/`"true"`/numeric booleans). Notable configuration: `FAIL_ON_UNKNOWN_PROPERTIES=false`, `Include.NON_EMPTY`, case-insensitive properties/enums, `ACCEPT_SINGLE_VALUE_AS_ARRAY=true`, Kotlin module with `NullIsSameAsDefault`, plus `KotlinInstantModule` (from `utils`) for `kotlin.time.Instant` support. See the code comment in the file for why post-construction `.configure()`/`.enable()` calls are used instead of the `XmlMapper.builder()` form (Jackson 2.x limitation, tracked against a future Jackson 3 migration).
- `EnumMixin`, `SoapFaultMixin`, `FaultCodeMixin`, `FaultSubCodeMixin`, `FaultReasonMixin` are Jackson mixin base classes available for version-specific mappers that need to attach XML annotations to types they don't own.
- `getNewFactory(nsAware: Boolean)` builds a raw `XmlFactory`/`XMLInputFactory` — used to construct namespace-aware vs. namespace-unaware input parsing as needed.

## What a version-specific SOAP parser module must supply
Concrete `OcppXXSoapParser` implementations extend `OcppSoapParserImpl` and must provide:
1. `OcppNs(ocppCpNs, ocppCsNs)` — that protocol version's SOAP namespace URIs.
2. `soapMapperOutput` / `soapMapperInput` — typically an `OcppSoapMapper` instance, optionally with version-specific mixins/modules registered.
3. `readToEnvelop` — deserialize into that version's `SoapEnvelope<OcppXXSoapBody>`.
4. `getRequestBodyContent` / `getResponseBodyContent` — dispatch on the version's `SoapBody` sealed hierarchy to extract the typed request/response payload.
5. `getOcppInitiator(action)` — action-name → `OcppInitiator` (Central System vs. Charging Station) lookup table for that version's action set.
6. Optionally `ignoredNullRestrictions` / `forcedFieldTypes` for known quirks in that version's XSD/message set.

See [docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) for the full per-version conventions (body sealed-class naming, mapper/mixin registration patterns, action table structure).

## Adding a New Fault Type
1. Add a `FaultSubCodeValue` / `FaultReasonTextValue` enum pair for the new subcode + human-readable reason.
2. Add a companion factory function on `SoapFault` (mirroring `securityError()`, `protocolError()`, etc.) using the `create(codeValue, subCode, reason)` helper.

## See Also
- [docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) — conventions for version-specific SOAP parser modules that depend on this one.
- [ocpp-transport-soap](../ocpp-transport-soap/CLAUDE.md) — HTTP server/client transport built on top of this module.
- [utils](../utils/CLAUDE.md) — `ErrorDetail`, `OcppInitiator`, `AbstractIgnoredNullRestriction`/`AbstractForcedFieldType`, `KotlinInstantModule`, `MessageErrorCode`, `fault.Fault` consumed here.
