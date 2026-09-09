# notifyReport — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYREPORT`
- Kotlin `NotifyReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportReq.kt)
- Kotlin `NotifyReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.40 NotifyReport — pdf-page 366
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.40.1 NotifyReportRequest — pdf-page 366
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.40.2 NotifyReportResponse — pdf-page 366
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## notifyReport request

- schema: [`NotifyReportRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.requestId` — integer, required — The id of the GetReportRequest or GetBaseReportRequest that requested this report
- `notifyReport.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyReport.req.reportData` — array, optional, minItems 1
- `notifyReport.req.reportData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component` — ComponentType, required — A physical or logical component
- `notifyReport.req.reportData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].component.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyReport.req.reportData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyReport.req.reportData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyReport.req.reportData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyReport.req.reportData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variableAttribute` — array, required, maxItems 4, minItems 1
- `notifyReport.req.reportData[].variableAttribute[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variableAttribute[].customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variableAttribute[].type` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
- `notifyReport.req.reportData[].variableAttribute[].value` — string, optional, maxLength 2500 — Value of the attribute. May only be omitted when mutability is set to 'WriteOnly'. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `notifyReport.req.reportData[].variableAttribute[].mutability` — MutabilityEnumType (string), optional, enum: ReadOnly | WriteOnly | ReadWrite — Defines the mutability of this attribute. Default is ReadWrite when omitted.
- `notifyReport.req.reportData[].variableAttribute[].persistent` — boolean, optional — If true, value will be persistent across system reboots or power down. Default when omitted is false.
- `notifyReport.req.reportData[].variableAttribute[].constant` — boolean, optional — If true, value that will never be changed by the Charging Station at runtime. Default when omitted is false.
- `notifyReport.req.reportData[].variableCharacteristics` — VariableCharacteristicsType, optional — Fixed read-only parameters of a variable.
- `notifyReport.req.reportData[].variableCharacteristics.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variableCharacteristics.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variableCharacteristics.unit` — string, optional, maxLength 16 — Unit of the variable. When the transmitted value has a unit, this field SHALL be included.
- `notifyReport.req.reportData[].variableCharacteristics.dataType` — DataEnumType (string), required, enum: string | decimal | integer | dateTime | boolean | OptionList | SequenceList | MemberList — Data type of this variable.
- `notifyReport.req.reportData[].variableCharacteristics.minLimit` — number, optional — Minimum possible value of this variable.
- `notifyReport.req.reportData[].variableCharacteristics.maxLimit` — number, optional — Maximum possible value of this variable. When the datatype of this Variable is String, OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV) string.
- `notifyReport.req.reportData[].variableCharacteristics.valuesList` — string, optional, maxLength 1000 — Allowed values when variable is Option/Member/SequenceList. * OptionList: The (Actual) Variable value must be a single value from the reported (CSV) enumeration list. * MemberList: The (Actual) Variable value may be an (unordered) (sub-)set of the reported (CSV) valid values list. * SequenceList: The (Actual) Variable value may be an ordered (priority, etc) (sub-)set of the reported (CSV) valid values. This is a comma separated list. The Configuration Variable &lt;&lt;configkey-configuration-value-size,ConfigurationValueSize&gt;&gt; can be used to limit SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these values will always remain equal.
- `notifyReport.req.reportData[].variableCharacteristics.supportsMonitoring` — boolean, required — Flag indicating if this variable supports monitoring.
- `notifyReport.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the report follows in an upcoming notifyReportRequest message. Default value when omitted is false.
- `notifyReport.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.

## notifyReport response

- schema: [`NotifyReportResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.resp.customData.vendorId` — string, required, maxLength 255

### notifyReport enumerations

- `notifyReport` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
- `notifyReport` `DataEnumType`: string | decimal | integer | dateTime | boolean | OptionList | SequenceList | MemberList — Data type of this variable.
- `notifyReport` `MutabilityEnumType`: ReadOnly | WriteOnly | ReadWrite — Defines the mutability of this attribute. Default is ReadWrite when omitted.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
