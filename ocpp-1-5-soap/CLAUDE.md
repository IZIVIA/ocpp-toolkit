# ocpp-1-5-soap

## Purpose

OCPP-S (SOAP/XML) wire format for OCPP 1.5: binds the shared envelope machinery in `ocpp-soap`
to the `ocpp-1-5-core` model.

This module is an instance of the `soap` layer — see
[../docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) for the conventions shared by
`ocpp-1-5-soap` and `ocpp-1-6-soap`.

Published as `ocpp-1-5-soap`. Consumed by `ocpp-transport-soap`.

## Contents

Package `com.izivia.ocpp.soap15`, three files:

- `Ocpp15SoapParser` — extends `OcppSoapParserImpl`, supplying the version's namespaces,
  mappers, and body-content dispatch.
- `Ocpp15SoapBody` — the SOAP body type: one nullable property per operation Req/Resp plus
  `fault`. Jackson picks whichever element is present.
- `Ocpp15SoapMapper` — two `internal object` `XmlMapper`s (`Ocpp15SoapMapperIn` for reading,
  `Ocpp15SoapMapper` for writing) built on `OcppSoapMapper`, plus the per-type Jackson mix-ins.

## Key patterns

- **Separate in/out mappers.** Reading and writing use different `ObjectMapper` instances with
  different mix-in sets. A serialisation-only fix belongs in `Ocpp15SoapMapper`; a
  deserialisation-only fix in `Ocpp15SoapMapperIn`. Getting this wrong silently affects only
  one direction.
- **`Ocpp15SoapBody` is an exhaustive nullable-field union.** Every supported operation needs
  a `Req` and a `Resp` property here, named so `BaseResolver` in `ocpp-soap` can derive the
  outbound element name (`*Req` → `o:xxxRequest`, `*Resp` → `o:xxxResponse`). Omitting a field
  makes the operation silently unparseable rather than producing a compile error.
- **`Actions` enum drives initiator resolution.** `getOcppInitiator(action)` resolves through
  `com.izivia.ocpp.core15.model.common.enumeration.Actions`, whose `initiatedBy` decides which
  of the two namespaces is stamped on an outbound message.
- **Faults are values, not exceptions.** `readToEnvelop` wraps parse failures via
  `parseSoapFaulted(soap, e) { Ocpp15SoapBody(fault = it) }`, and `getRequestBodyContent`
  converts a `SoapFault` into a fault request with `.isA<SoapFault> { it.toFaultReq() }`.
  Malformed XML therefore surfaces as a fault payload, not a thrown exception.
- **Asymmetric request/response handling.** `getRequestBodyContent` performs the fault
  conversion; `getResponseBodyContent` does not.
- **Same escape hatches as the JSON layer**: `ignoredNullRestrictions` and `forcedFieldTypes`,
  typed as `Ocpp15IgnoredNullRestriction` / `Ocpp15ForcedFieldType` from
  `ocpp-1-5-core`'s `DeserializeOptions.kt`, applied in `applyDeserializerOptions` before mapping.

## OCPP 1.5 namespaces

```
ocppCpNs = "urn://Ocpp/Cp/2012/06/"   // charge-point-hosted operations
ocppCsNs = "urn://Ocpp/Cs/2012/06/"   // central-system-hosted operations
```

These are the version discriminator on the wire. They are the first thing to check when a peer
rejects a message as unrecognised.

OCPP 1.5 supports both transports: this module for OCPP-S and `ocpp-1-5-json` for OCPP-J.
SOAP was the primary transport for 1.5 in practice, so this is the better-exercised path.

## Testing

`Ocpp15SoapParserTest` in `src/test/kotlin/` round-trips representative envelopes. Add a case
there for every new operation — the nullable-union body means a missing binding produces a
silent null rather than a failure.

## See also

- [../ocpp-soap/CLAUDE.md](../ocpp-soap/CLAUDE.md) — envelope model, `BaseResolver`, fault handling
- [../ocpp-1-5-core/CLAUDE.md](../ocpp-1-5-core/CLAUDE.md) — models and the `Actions` registry
- [../ocpp-transport-soap/CLAUDE.md](../ocpp-transport-soap/CLAUDE.md) — the HTTP transport
