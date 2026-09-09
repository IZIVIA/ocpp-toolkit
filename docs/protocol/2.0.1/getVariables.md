# getVariables — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETVARIABLES`
- Kotlin `GetVariablesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesReq.kt)
- Kotlin `GetVariablesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §B06 Get Variables — pdf-page 64
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.28 GetVariables — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.28.1 GetVariablesRequest — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.28.2 GetVariablesResponse — pdf-page 361
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 14, 70

## getVariables request

- schema: [`GetVariablesRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetVariablesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getVariables.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData` — array, required, minItems 1
- `getVariables.req.getVariableData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables.req.getVariableData[].component` — ComponentType, required — A physical or logical component
- `getVariables.req.getVariableData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].component.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getVariables.req.getVariableData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getVariables.req.getVariableData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getVariables.req.getVariableData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].variable` — VariableType, required — Reference key to a component-variable.
- `getVariables.req.getVariableData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].variable.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

## getVariables response

- schema: [`GetVariablesResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetVariablesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getVariables.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult` — array, required, minItems 1
- `getVariables.resp.getVariableResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].attributeStatusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].attributeStatusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getVariables.resp.getVariableResult[].attributeStatus` — GetVariableStatusEnumType (string), required, enum: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType — Result status of getting the variable.
- `getVariables.resp.getVariableResult[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables.resp.getVariableResult[].attributeValue` — string, optional, maxLength 2500 — Value of requested attribute type of component-variable. This field can only be empty when the given status is NOT accepted. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `getVariables.resp.getVariableResult[].component` — ComponentType, required — A physical or logical component
- `getVariables.resp.getVariableResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].component.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getVariables.resp.getVariableResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getVariables.resp.getVariableResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getVariables.resp.getVariableResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].variable` — VariableType, required — Reference key to a component-variable.
- `getVariables.resp.getVariableResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].variable.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### getVariables enumerations

- `getVariables` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables` `GetVariableStatusEnumType`: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType — Result status of getting the variable.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
