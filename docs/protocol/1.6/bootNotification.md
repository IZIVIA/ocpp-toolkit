# bootNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.2 Boot Notification — pdf-page 37
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.3 BootNotification.req — pdf-page 65
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.4 BootNotification.conf — pdf-page 66
- spec: [`ocpp-s-1.6-specification`](../spec/1.6/ocpp-s-1.6-specification.md) §7.1 BootNotification — pdf-page 13
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 6, 12, 16, 17, 20, 33, 41
- errata mentions: [`ocpp-j-1.6-errata`](../spec/1.6/ocpp-j-1.6-errata.md) pdf-page 10, 11, 12

## bootNotification request

- schema: [`BootNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/BootNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.req.chargePointVendor` — string, required, maxLength 20
- `bootNotification.req.chargePointModel` — string, required, maxLength 20
- `bootNotification.req.chargePointSerialNumber` — string, optional, maxLength 25
- `bootNotification.req.chargeBoxSerialNumber` — string, optional, maxLength 25
- `bootNotification.req.firmwareVersion` — string, optional, maxLength 50
- `bootNotification.req.iccid` — string, optional, maxLength 20
- `bootNotification.req.imsi` — string, optional, maxLength 20
- `bootNotification.req.meterType` — string, optional, maxLength 25
- `bootNotification.req.meterSerialNumber` — string, optional, maxLength 25

## bootNotification response

- schema: [`BootNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/BootNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.status` — string, required, enum: Accepted | Pending | Rejected
- `bootNotification.resp.currentTime` — string, required, format date-time
- `bootNotification.resp.interval` — integer, required

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
