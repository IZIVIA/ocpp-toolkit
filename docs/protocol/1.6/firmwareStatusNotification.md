# firmwareStatusNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.5 Firmware Status Notification — pdf-page 39
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.19 FirmwareStatusNotification.req — pdf-page 69
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.20 FirmwareStatusNotification.conf — pdf-page 70
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 6, 19, 20

## firmwareStatusNotification request

- schema: [`FirmwareStatusNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/FirmwareStatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.status` — string, required, enum: Downloaded | DownloadFailed | Downloading | Idle | InstallationFailed | Installing | Installed

## firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/FirmwareStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
