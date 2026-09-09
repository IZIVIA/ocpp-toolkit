# ocpp-1-6-soap

## Purpose

OCPP-S (SOAP/XML) wire format for OCPP 1.6: binds the shared envelope machinery in `ocpp-soap`
to the `ocpp-1-6-core` model.

This module is an instance of the `soap` layer — see
[../docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) for the conventions shared by
`ocpp-1-5-soap` and `ocpp-1-6-soap`.

Published as `ocpp-1-6-soap`. Consumed by `ocpp-transport-soap`.

## Contents

Package `com.izivia.ocpp.soap16`, three files:

- `Ocpp16SoapParser` — extends `OcppSoapParserImpl`, supplying the version's namespaces,
  mappers, and body-content dispatch.
- `Ocpp16SoapBody` — the SOAP body type: one nullable property per operation Req/Resp plus
  `fault`. Jackson picks whichever element is present.
- `Ocpp16SoapMapper` — two `internal object` `XmlMapper`s (`Ocpp16SoapMapperIn` for reading,
  `Ocpp16SoapMapper` for writing) built on `OcppSoapMapper`, plus the per-type Jackson mix-ins.

## Key patterns

- **Separate in/out mappers.** Reading and writing use different `ObjectMapper` instances with
  different mix-in sets. A serialisation-only fix belongs in `Ocpp16SoapMapper`; a
  deserialisation-only fix in `Ocpp16SoapMapperIn`. Getting this wrong silently affects only
  one direction.
- **`Ocpp16SoapBody` is an exhaustive nullable-field union.** Every supported operation needs
  a `Req` and a `Resp` property here, named so `BaseResolver` in `ocpp-soap` can derive the
  outbound element name (`*Req` → `o:xxxRequest`, `*Resp` → `o:xxxResponse`). Omitting a field
  makes the operation silently unparseable rather than producing a compile error.
- **`Actions` enum drives initiator resolution.** `getOcppInitiator(action)` resolves through
  `com.izivia.ocpp.core16.model.common.enumeration.Actions`, whose `initiatedBy` decides which
  of the two namespaces is stamped on an outbound message.
- **Faults are values, not exceptions.** `readToEnvelop` wraps parse failures via
  `parseSoapFaulted(soap, e) { Ocpp16SoapBody(fault = it) }`, and `getRequestBodyContent`
  converts a `SoapFault` into a fault request with `.isA<SoapFault> { it.toFaultReq() }`.
  Malformed XML therefore surfaces as a fault payload, not a thrown exception.
- **Asymmetric request/response handling.** `getRequestBodyContent` performs the fault
  conversion; `getResponseBodyContent` does not.
- **Same escape hatches as the JSON layer**: `ignoredNullRestrictions` and `forcedFieldTypes`,
  typed as `Ocpp16IgnoredNullRestriction` / `Ocpp16ForcedFieldType` from
  `ocpp-1-6-core`'s `DeserializeOptions.kt`, applied in `applyDeserializerOptions` before mapping.

## OCPP 1.6 namespaces

```
ocppCpNs = "urn://Ocpp/Cp/2015/10/"   // charge-point-hosted operations
ocppCsNs = "urn://Ocpp/Cs/2015/10/"   // central-system-hosted operations
```

These are the version discriminator on the wire. They are the first thing to check when a peer
rejects a message as unrecognised.

OCPP 1.6 supports both transports: this module for OCPP-S and `ocpp-1-6-json` for OCPP-J. The
README describes OCPP-J as the fully supported path for 1.6, so treat SOAP coverage here as
less exercised. `Ocpp16SoapMapper` carries by far the largest mix-in set of the three versions
(per-operation Req/Resp mix-ins in addition to the shared enum ones).

## Testing

`Ocpp16SoapParserTest` in `src/test/kotlin/` round-trips representative envelopes. Add a case
there for every new operation — the nullable-union body means a missing binding produces a
silent null rather than a failure.

## See also

- [../ocpp-soap/CLAUDE.md](../ocpp-soap/CLAUDE.md) — envelope model, `BaseResolver`, fault handling
- [../ocpp-1-6-core/CLAUDE.md](../ocpp-1-6-core/CLAUDE.md) — models and the `Actions` registry
- [../ocpp-transport-soap/CLAUDE.md](../ocpp-transport-soap/CLAUDE.md) — the HTTP transport
