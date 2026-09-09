# setDisplayMessage — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETDISPLAYMESSAGE`
- Kotlin `SetDisplayMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageReq.kt)
- Kotlin `SetDisplayMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §O01 Set DisplayMessage — pdf-page 331
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.52 SetDisplayMessage — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.52.1 SetDisplayMessageRequest — pdf-page 372
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.52.2 SetDisplayMessageResponse — pdf-page 372

## setDisplayMessage request

- schema: [`SetDisplayMessageRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetDisplayMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setDisplayMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message` — MessageInfoType, required — Message_ Info urn:x-enexis:ecdm:uid:2:233264 Contains message details, for a message to be displayed on a Charging Station.
- `setDisplayMessage.req.message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display` — ComponentType, optional — A physical or logical component
- `setDisplayMessage.req.message.display.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.display.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setDisplayMessage.req.message.display.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.display.evse.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setDisplayMessage.req.message.display.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setDisplayMessage.req.message.display.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setDisplayMessage.req.message.display.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setDisplayMessage.req.message.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Master resource identifier, unique within an exchange context. It is defined within the OCPP context as a positive Integer value (greater or equal to zero).
- `setDisplayMessage.req.message.priority` — MessagePriorityEnumType (string), required, enum: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `setDisplayMessage.req.message.state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.
- `setDisplayMessage.req.message.startDateTime` — string, optional, format date-time — Message_ Info. Start. Date_ Time urn:x-enexis:ecdm:uid:1:569256 From what date-time should this message be shown. If omitted: directly.
- `setDisplayMessage.req.message.endDateTime` — string, optional, format date-time — Message_ Info. End. Date_ Time urn:x-enexis:ecdm:uid:1:569257 Until what date-time should this message be shown, after this date/time this message SHALL be removed.
- `setDisplayMessage.req.message.transactionId` — string, optional, maxLength 36 — During which transaction shall this message be shown. Message SHALL be removed by the Charging Station after transaction has ended.
- `setDisplayMessage.req.message.message` — MessageContentType, required — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `setDisplayMessage.req.message.message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.message.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.message.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `setDisplayMessage.req.message.message.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `setDisplayMessage.req.message.message.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.

## setDisplayMessage response

- schema: [`SetDisplayMessageResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetDisplayMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setDisplayMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.resp.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.resp.status` — DisplayMessageStatusEnumType (string), required, enum: Accepted | NotSupportedMessageFormat | Rejected | NotSupportedPriority | NotSupportedState | UnknownTransaction — This indicates whether the Charging Station is able to display the message.
- `setDisplayMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setDisplayMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setDisplayMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### setDisplayMessage enumerations

- `setDisplayMessage` `DisplayMessageStatusEnumType`: Accepted | NotSupportedMessageFormat | Rejected | NotSupportedPriority | NotSupportedState | UnknownTransaction — This indicates whether the Charging Station is able to display the message.
- `setDisplayMessage` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `setDisplayMessage` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `setDisplayMessage` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
