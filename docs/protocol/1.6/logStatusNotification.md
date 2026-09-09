# logStatusNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.LOGSTATUSNOTIFICATION`
- Kotlin `LogStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationReq.kt)
- Kotlin `LogStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.13 LogStatusNotification.req — pdf-page 52
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.14 LogStatusNotification.conf — pdf-page 53

## logStatusNotification request

- schema: [`LogStatusNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/LogStatusNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:LogStatusNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.req.status` — UploadLogStatusEnumType (string), required, enum: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading
- `logStatusNotification.req.requestId` — integer, optional

## logStatusNotification response

- schema: [`LogStatusNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/LogStatusNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:LogStatusNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### logStatusNotification enumerations

- `logStatusNotification` `UploadLogStatusEnumType`: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
