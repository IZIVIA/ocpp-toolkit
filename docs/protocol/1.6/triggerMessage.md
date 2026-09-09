# triggerMessage — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.TRIGGERMESSAGE`
- Kotlin `TriggerMessageReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageReq.kt)
- Kotlin `TriggerMessageResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.17 Trigger Message — pdf-page 60
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.51 TriggerMessage.req — pdf-page 78
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.52 TriggerMessage.conf — pdf-page 78
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.44 TriggerMessageStatus — pdf-page 98
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 16, 17, 19

## triggerMessage request

- schema: [`TriggerMessageRequest.json`](../../../ocpp-1-6-json/src/main/resources/TriggerMessageRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.req.requestedMessage` — string, required, enum: BootNotification | DiagnosticsStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | StatusNotification
- `triggerMessage.req.connectorId` — integer, optional

## triggerMessage response

- schema: [`TriggerMessageResponse.json`](../../../ocpp-1-6-json/src/main/resources/TriggerMessageResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.resp.status` — string, required, enum: Accepted | Rejected | NotImplemented

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
