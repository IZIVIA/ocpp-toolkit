# setVariables — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETVARIABLES`
- Kotlin `SetVariablesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesReq.kt)
- Kotlin `SetVariablesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §B05 Set Variables — pdf-page 62
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.57 SetVariables — pdf-page 374
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.57.1 SetVariablesRequest — pdf-page 374
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.57.2 SetVariablesResponse — pdf-page 374
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 14, 70

## setVariables request

- schema: [`SetVariablesRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetVariablesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariables.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData` — array, required, minItems 1
- `setVariables.req.setVariableData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables.req.setVariableData[].attributeValue` — string, required, maxLength 1000 — Value to be assigned to attribute of variable. The Configuration Variable &lt;&lt;configkey-configuration-value-size,ConfigurationValueSize&gt;&gt; can be used to limit SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these values will always remain equal.
- `setVariables.req.setVariableData[].component` — ComponentType, required — A physical or logical component
- `setVariables.req.setVariableData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].component.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariables.req.setVariableData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariables.req.setVariableData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariables.req.setVariableData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariables.req.setVariableData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

## setVariables response

- schema: [`SetVariablesResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetVariablesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariables.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult` — array, required, minItems 1
- `setVariables.resp.setVariableResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables.resp.setVariableResult[].attributeStatus` — SetVariableStatusEnumType (string), required, enum: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType | RebootRequired — Result status of setting the variable.
- `setVariables.resp.setVariableResult[].attributeStatusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].attributeStatusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `setVariables.resp.setVariableResult[].component` — ComponentType, required — A physical or logical component
- `setVariables.resp.setVariableResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].component.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariables.resp.setVariableResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariables.resp.setVariableResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariables.resp.setVariableResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariables.resp.setVariableResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### setVariables enumerations

- `setVariables` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables` `SetVariableStatusEnumType`: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType | RebootRequired — Result status of setting the variable.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
