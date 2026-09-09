# setMonitoringBase — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETMONITORINGBASE`
- Kotlin `SetMonitoringBaseReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseReq.kt)
- Kotlin `SetMonitoringBaseResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §N03 Set Monitoring Base — pdf-page 315
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.53 SetMonitoringBase — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.53.1 SetMonitoringBaseRequest — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.53.2 SetMonitoringBaseResponse — pdf-page 372
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 108, 109

## setMonitoringBase request

- schema: [`SetMonitoringBaseRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetMonitoringBaseRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringBase.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.req.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.req.monitoringBase` — MonitoringBaseEnumType (string), required, enum: All | FactoryDefault | HardWiredOnly — Specify which monitoring base will be set

## setMonitoringBase response

- schema: [`SetMonitoringBaseResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetMonitoringBaseResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringBase.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.resp.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringBase.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setMonitoringBase.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setMonitoringBase.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### setMonitoringBase enumerations

- `setMonitoringBase` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringBase` `MonitoringBaseEnumType`: All | FactoryDefault | HardWiredOnly — Specify which monitoring base will be set

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
