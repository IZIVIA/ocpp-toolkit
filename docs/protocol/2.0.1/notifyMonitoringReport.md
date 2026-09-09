# notifyMonitoringReport — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYMONITORINGREPORT`
- Kotlin `NotifyMonitoringReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportReq.kt)
- Kotlin `NotifyMonitoringReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.39 NotifyMonitoringReport — pdf-page 365
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.39.1 NotifyMonitoringReportRequest — pdf-page 365
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.39.2 NotifyMonitoringReportResponse — pdf-page 366
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## notifyMonitoringReport request

- schema: [`NotifyMonitoringReportRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyMonitoringReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyMonitoringReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor` — array, optional, minItems 1
- `notifyMonitoringReport.req.monitor[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component` — ComponentType, required — A physical or logical component
- `notifyMonitoringReport.req.monitor[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].component.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyMonitoringReport.req.monitor[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyMonitoringReport.req.monitor[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyMonitoringReport.req.monitor[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyMonitoringReport.req.monitor[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variableMonitoring` — array, required, minItems 1
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].id` — integer, required — Identifies the monitor.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].transaction` — boolean, required — Monitor only active when a transaction is ongoing on a component relevant to this transaction.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].value` — number, required — Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the interval in seconds.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.
- `notifyMonitoringReport.req.requestId` — integer, required — The id of the GetMonitoringRequest that requested this report.
- `notifyMonitoringReport.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the monitoringData follows in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
- `notifyMonitoringReport.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyMonitoringReport.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.

## notifyMonitoringReport response

- schema: [`NotifyMonitoringReportResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyMonitoringReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyMonitoringReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.resp.customData.vendorId` — string, required, maxLength 255

### notifyMonitoringReport enumerations

- `notifyMonitoringReport` `MonitorEnumType`: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
