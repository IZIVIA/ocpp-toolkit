# getReport — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETREPORT`
- Kotlin `GetReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportReq.kt)
- Kotlin `GetReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.26 GetReport — pdf-page 360
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.26.1 GetReportRequest — pdf-page 360
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.26.2 GetReportResponse — pdf-page 360
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7, 70
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 99

## getReport request

- schema: [`GetReportRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable` — array, optional, minItems 1
- `getReport.req.componentVariable[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component` — ComponentType, required — A physical or logical component
- `getReport.req.componentVariable[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].component.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getReport.req.componentVariable[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getReport.req.componentVariable[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getReport.req.componentVariable[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].variable` — VariableType, optional — Reference key to a component-variable.
- `getReport.req.componentVariable[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].variable.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.requestId` — integer, required — The Id of the request.
- `getReport.req.componentCriteria` — array, optional, maxItems 4, minItems 1 — This field contains criteria for components for which a report is requested
- `getReport.req.componentCriteria[]` — ComponentCriterionEnumType, enum: Active | Available | Enabled | Problem

## getReport response

- schema: [`GetReportResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.resp.customData.vendorId` — string, required, maxLength 255
- `getReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### getReport enumerations

- `getReport` `ComponentCriterionEnumType`: Active | Available | Enabled | Problem
- `getReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
