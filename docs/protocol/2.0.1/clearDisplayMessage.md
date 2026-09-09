# clearDisplayMessage — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARDISPLAYMESSAGE`
- Kotlin `ClearDisplayMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageReq.kt)
- Kotlin `ClearDisplayMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.8 ClearDisplayMessage — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.8.1 ClearDisplayMessageRequest — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.8.2 ClearDisplayMessageResponse — pdf-page 351

## clearDisplayMessage request

- schema: [`ClearDisplayMessageRequest.json`](../../../ocpp-2-0-json/src/main/resources/ClearDisplayMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearDisplayMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.req.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.req.id` — integer, required — Id of the message that SHALL be removed from the Charging Station.

## clearDisplayMessage response

- schema: [`ClearDisplayMessageResponse.json`](../../../ocpp-2-0-json/src/main/resources/ClearDisplayMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearDisplayMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.resp.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.resp.status` — ClearMessageStatusEnumType (string), required, enum: Accepted | Unknown — Returns whether the Charging Station has been able to remove the message.
- `clearDisplayMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearDisplayMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearDisplayMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### clearDisplayMessage enumerations

- `clearDisplayMessage` `ClearMessageStatusEnumType`: Accepted | Unknown — Returns whether the Charging Station has been able to remove the message.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
