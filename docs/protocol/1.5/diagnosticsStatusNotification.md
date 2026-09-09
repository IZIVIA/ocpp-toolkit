# diagnosticsStatusNotification — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.DIAGNOSTICSSTATUSNOTIFICATION`
- Kotlin `DiagnosticsStatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt)
- Kotlin `DiagnosticsStatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.4 Diagnostics Status Notification — pdf-page 21
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.15 DiagnosticsStatusNotification.req — pdf-page 48
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.16 DiagnosticsStatusNotification.conf — pdf-page 48

## diagnosticsStatusNotification request

- schema: [`DiagnosticsStatusNotification.json`](../../../ocpp-1-5-json/src/main/resources/DiagnosticsStatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `diagnosticsStatusNotification.req.status` — string, required, enum: Idle | Uploaded | UploadFailed | Uploading

## diagnosticsStatusNotification response

- schema: [`DiagnosticsStatusNotificationResponse.json`](../../../ocpp-1-5-json/src/main/resources/DiagnosticsStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
