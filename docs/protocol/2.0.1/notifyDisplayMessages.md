# notifyDisplayMessages — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYDISPLAYMESSAGES`
- Kotlin `NotifyDisplayMessagesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesReq.kt)
- Kotlin `NotifyDisplayMessagesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.35 NotifyDisplayMessages — pdf-page 363
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.35.1 NotifyDisplayMessagesRequest — pdf-page 363
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.35.2 NotifyDisplayMessagesResponse — pdf-page 364

## notifyDisplayMessages request

- schema: [`NotifyDisplayMessagesRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyDisplayMessagesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyDisplayMessages.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo` — array, optional, minItems 1
- `notifyDisplayMessages.req.messageInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display` — ComponentType, optional — A physical or logical component
- `notifyDisplayMessages.req.messageInfo[].display.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].display.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyDisplayMessages.req.messageInfo[].display.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].display.evse.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyDisplayMessages.req.messageInfo[].display.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyDisplayMessages.req.messageInfo[].display.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyDisplayMessages.req.messageInfo[].display.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyDisplayMessages.req.messageInfo[].id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Master resource identifier, unique within an exchange context. It is defined within the OCPP context as a positive Integer value (greater or equal to zero).
- `notifyDisplayMessages.req.messageInfo[].priority` — MessagePriorityEnumType (string), required, enum: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `notifyDisplayMessages.req.messageInfo[].state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.
- `notifyDisplayMessages.req.messageInfo[].startDateTime` — string, optional, format date-time — Message_ Info. Start. Date_ Time urn:x-enexis:ecdm:uid:1:569256 From what date-time should this message be shown. If omitted: directly.
- `notifyDisplayMessages.req.messageInfo[].endDateTime` — string, optional, format date-time — Message_ Info. End. Date_ Time urn:x-enexis:ecdm:uid:1:569257 Until what date-time should this message be shown, after this date/time this message SHALL be removed.
- `notifyDisplayMessages.req.messageInfo[].transactionId` — string, optional, maxLength 36 — During which transaction shall this message be shown. Message SHALL be removed by the Charging Station after transaction has ended.
- `notifyDisplayMessages.req.messageInfo[].message` — MessageContentType, required — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `notifyDisplayMessages.req.messageInfo[].message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].message.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].message.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `notifyDisplayMessages.req.messageInfo[].message.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `notifyDisplayMessages.req.messageInfo[].message.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `notifyDisplayMessages.req.requestId` — integer, required — The id of the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt; that requested this message.
- `notifyDisplayMessages.req.tbc` — boolean, optional — "to be continued" indicator. Indicates whether another part of the report follows in an upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.

## notifyDisplayMessages response

- schema: [`NotifyDisplayMessagesResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyDisplayMessagesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyDisplayMessages.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.resp.customData.vendorId` — string, required, maxLength 255

### notifyDisplayMessages enumerations

- `notifyDisplayMessages` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `notifyDisplayMessages` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `notifyDisplayMessages` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
