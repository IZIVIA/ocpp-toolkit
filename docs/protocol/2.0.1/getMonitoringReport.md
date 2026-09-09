# getMonitoringReport — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETMONITORINGREPORT`
- Kotlin `GetMonitoringReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportReq.kt)
- Kotlin `GetMonitoringReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §N02 Get Monitoring report — pdf-page 314
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.25 GetMonitoringReport — pdf-page 359
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.25.1 GetMonitoringReportRequest — pdf-page 359
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.25.2 GetMonitoringReportResponse — pdf-page 359
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 99

## getMonitoringReport request

- schema: [`GetMonitoringReportRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetMonitoringReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getMonitoringReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable` — array, optional, minItems 1
- `getMonitoringReport.req.componentVariable[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component` — ComponentType, required — A physical or logical component
- `getMonitoringReport.req.componentVariable[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].component.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getMonitoringReport.req.componentVariable[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getMonitoringReport.req.componentVariable[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getMonitoringReport.req.componentVariable[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].variable` — VariableType, optional — Reference key to a component-variable.
- `getMonitoringReport.req.componentVariable[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].variable.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.requestId` — integer, required — The Id of the request.
- `getMonitoringReport.req.monitoringCriteria` — array, optional, maxItems 3, minItems 1 — This field contains criteria for components for which a monitoring report is requested
- `getMonitoringReport.req.monitoringCriteria[]` — MonitoringCriterionEnumType, enum: ThresholdMonitoring | DeltaMonitoring | PeriodicMonitoring

## getMonitoringReport response

- schema: [`GetMonitoringReportResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetMonitoringReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getMonitoringReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.resp.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getMonitoringReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getMonitoringReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getMonitoringReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### getMonitoringReport enumerations

- `getMonitoringReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getMonitoringReport` `MonitoringCriterionEnumType`: ThresholdMonitoring | DeltaMonitoring | PeriodicMonitoring

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
