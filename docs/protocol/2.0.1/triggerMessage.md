# triggerMessage — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.TRIGGERMESSAGE`
- Kotlin `TriggerMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageReq.kt)
- Kotlin `TriggerMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §F06 Trigger Message — pdf-page 189
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.61 TriggerMessage — pdf-page 377
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.61.1 TriggerMessageRequest — pdf-page 377
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.61.2 TriggerMessageResponse — pdf-page 377
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 48

## triggerMessage request

- schema: [`TriggerMessageRequest.json`](../../../ocpp-2-0-json/src/main/resources/TriggerMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.req.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `triggerMessage.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.req.evse.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `triggerMessage.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `triggerMessage.req.requestedMessage` — MessageTriggerEnumType (string), required, enum: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargingStationCertificate | SignV2GCertificate | StatusNotification | TransactionEvent | SignCombinedCertificate | PublishFirmwareStatusNotification — Type of message to be triggered.

## triggerMessage response

- schema: [`TriggerMessageResponse.json`](../../../ocpp-2-0-json/src/main/resources/TriggerMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.resp.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.resp.status` — TriggerMessageStatusEnumType (string), required, enum: Accepted | Rejected | NotImplemented — Indicates whether the Charging Station will send the requested notification or not.
- `triggerMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `triggerMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `triggerMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### triggerMessage enumerations

- `triggerMessage` `MessageTriggerEnumType`: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargingStationCertificate | SignV2GCertificate | StatusNotification | TransactionEvent | SignCombinedCertificate | PublishFirmwareStatusNotification — Type of message to be triggered.
- `triggerMessage` `TriggerMessageStatusEnumType`: Accepted | Rejected | NotImplemented — Indicates whether the Charging Station will send the requested notification or not.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
