# firmwareStatusNotification — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.5 Firmware Status Notification — pdf-page 21
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.17 FirmwareStatusNotification.req — pdf-page 49
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.18 FirmwareStatusNotification.conf — pdf-page 49

## firmwareStatusNotification request

- schema: [`FirmwareStatusNotification.json`](../../../ocpp-1-5-json/src/main/resources/FirmwareStatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.status` — string, required, enum: Downloaded | DownloadFailed | Downloading | Idle | InstallationFailed | Installing | Installed

## firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../../ocpp-1-5-json/src/main/resources/FirmwareStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
