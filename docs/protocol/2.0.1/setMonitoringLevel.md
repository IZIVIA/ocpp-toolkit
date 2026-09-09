# setMonitoringLevel — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETMONITORINGLEVEL`
- Kotlin `SetMonitoringLevelReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelReq.kt)
- Kotlin `SetMonitoringLevelResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §N05 Set Monitoring Level — pdf-page 319
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.54 SetMonitoringLevel — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.54.1 SetMonitoringLevelRequest — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.54.2 SetMonitoringLevelResponse — pdf-page 373
- spec **(errata)**: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) §N05 Set Monitoring Level) — pdf-page 86

## setMonitoringLevel request

- schema: [`SetMonitoringLevelRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetMonitoringLevelRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringLevel.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.req.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.req.severity` — integer, required — The Charging Station SHALL only report events with a severity number lower than or equal to this severity. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.

## setMonitoringLevel response

- schema: [`SetMonitoringLevelResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetMonitoringLevelResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringLevel.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.resp.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringLevel.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setMonitoringLevel.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setMonitoringLevel.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### setMonitoringLevel enumerations

- `setMonitoringLevel` `GenericStatusEnumType`: Accepted | Rejected — Indicates whether the Charging Station was able to accept the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
