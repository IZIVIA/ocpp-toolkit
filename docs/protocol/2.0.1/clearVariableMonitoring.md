# clearVariableMonitoring — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARVARIABLEMONITORING`
- Kotlin `ClearVariableMonitoringReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringReq.kt)
- Kotlin `ClearVariableMonitoringResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.10 ClearVariableMonitoring — pdf-page 352
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.10.1 ClearVariableMonitoringRequest — pdf-page 352
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.10.2 ClearVariableMonitoringResponse — pdf-page 352
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 72

## clearVariableMonitoring request

- schema: [`ClearVariableMonitoringRequest.json`](../../../ocpp-2-0-json/src/main/resources/ClearVariableMonitoringRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearVariableMonitoring.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.req.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.req.ids` — array, required, minItems 1 — List of the monitors to be cleared, identified by there Id.
- `clearVariableMonitoring.req.ids[]` — integer

## clearVariableMonitoring response

- schema: [`ClearVariableMonitoringResponse.json`](../../../ocpp-2-0-json/src/main/resources/ClearVariableMonitoringResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearVariableMonitoring.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults` — array, required, minItems 1
- `clearVariableMonitoring.resp.clearMonitoringResults[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.clearMonitoringResults[].customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults[].status` — ClearMonitoringStatusEnumType (string), required, enum: Accepted | Rejected | NotFound — Result of the clear request for this monitor, identified by its Id.
- `clearVariableMonitoring.resp.clearMonitoringResults[].id` — integer, required — Id of the monitor of which a clear was requested.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### clearVariableMonitoring enumerations

- `clearVariableMonitoring` `ClearMonitoringStatusEnumType`: Accepted | Rejected | NotFound — Result of the clear request for this monitor, identified by its Id.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
