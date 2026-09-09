# setVariableMonitoring — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETVARIABLEMONITORING`
- Kotlin `SetVariableMonitoringReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringReq.kt)
- Kotlin `SetVariableMonitoringResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §N04 Set Variable Monitoring — pdf-page 316
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.56 SetVariableMonitoring — pdf-page 374
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.56.1 SetVariableMonitoringRequest — pdf-page 374
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.56.2 SetVariableMonitoringResponse — pdf-page 374
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 72

## setVariableMonitoring request

- schema: [`SetVariableMonitoringRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetVariableMonitoringRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariableMonitoring.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData` — array, required, minItems 1
- `setVariableMonitoring.req.setMonitoringData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].id` — integer, optional — An id SHALL only be given to replace an existing monitor. The Charging Station handles the generation of id's for new monitors.
- `setVariableMonitoring.req.setMonitoringData[].transaction` — boolean, optional — Monitor only active when a transaction is ongoing on a component relevant to this transaction. Default = false.
- `setVariableMonitoring.req.setMonitoringData[].value` — number, required — Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the interval in seconds.
- `setVariableMonitoring.req.setMonitoringData[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring.req.setMonitoringData[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.
- `setVariableMonitoring.req.setMonitoringData[].component` — ComponentType, required — A physical or logical component
- `setVariableMonitoring.req.setMonitoringData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].component.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariableMonitoring.req.setMonitoringData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariableMonitoring.req.setMonitoringData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariableMonitoring.req.setMonitoringData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariableMonitoring.req.setMonitoringData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

## setVariableMonitoring response

- schema: [`SetVariableMonitoringResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetVariableMonitoringResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariableMonitoring.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult` — array, required, minItems 1
- `setVariableMonitoring.resp.setMonitoringResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].id` — integer, optional — Id given to the VariableMonitor by the Charging Station. The Id is only returned when status is accepted. Installed VariableMonitors should have unique id's but the id's of removed Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `setVariableMonitoring.resp.setMonitoringResult[].status` — SetMonitoringStatusEnumType (string), required, enum: Accepted | UnknownComponent | UnknownVariable | UnsupportedMonitorType | Rejected | Duplicate — Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.
- `setVariableMonitoring.resp.setMonitoringResult[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring.resp.setMonitoringResult[].component` — ComponentType, required — A physical or logical component
- `setVariableMonitoring.resp.setMonitoringResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].component.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariableMonitoring.resp.setMonitoringResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.

### setVariableMonitoring enumerations

- `setVariableMonitoring` `MonitorEnumType`: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring` `SetMonitoringStatusEnumType`: Accepted | UnknownComponent | UnknownVariable | UnsupportedMonitorType | Rejected | Duplicate — Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
