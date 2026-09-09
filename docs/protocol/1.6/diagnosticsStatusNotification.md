# diagnosticsStatusNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.DIAGNOSTICSSTATUSNOTIFICATION`
- Kotlin `DiagnosticsStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt)
- Kotlin `DiagnosticsStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.4 Diagnostics Status Notification — pdf-page 39
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.17 DiagnosticsStatusNotification.req — pdf-page 69
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.18 DiagnosticsStatusNotification.conf — pdf-page 69
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 14, 15

## diagnosticsStatusNotification request

- schema: [`DiagnosticsStatusNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/DiagnosticsStatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `diagnosticsStatusNotification.req.status` — string, required, enum: Idle | Uploaded | UploadFailed | Uploading

## diagnosticsStatusNotification response

- schema: [`DiagnosticsStatusNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/DiagnosticsStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
