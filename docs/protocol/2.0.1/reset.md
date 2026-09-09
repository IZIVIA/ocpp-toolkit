# reset — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.48 Reset — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.48.1 ResetRequest — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.48.2 ResetResponse — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §3.74 ResetEnumType — pdf-page 423
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2.1.14 ResetRetries — pdf-page 433
- changelog mentions: [`changelog-2.0-to-2.0.1`](../spec/2.0.1/changelog-2.0-to-2.0.1.md) pdf-page 8
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 28, 29, 30, 110

## reset request

- schema: [`ResetRequest.json`](../../../ocpp-2-0-json/src/main/resources/ResetRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.req.customData.vendorId` — string, required, maxLength 255
- `reset.req.type` — ResetEnumType (string), required, enum: Immediate | OnIdle — This contains the type of reset that the Charging Station or EVSE should perform.
- `reset.req.evseId` — integer, optional — This contains the ID of a specific EVSE that needs to be reset, instead of the entire Charging Station.

## reset response

- schema: [`ResetResponse.json`](../../../ocpp-2-0-json/src/main/resources/ResetResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.resp.customData.vendorId` — string, required, maxLength 255
- `reset.resp.status` — ResetStatusEnumType (string), required, enum: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the reset.
- `reset.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `reset.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `reset.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `reset.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### reset enumerations

- `reset` `ResetEnumType`: Immediate | OnIdle — This contains the type of reset that the Charging Station or EVSE should perform.
- `reset` `ResetStatusEnumType`: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the reset.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
