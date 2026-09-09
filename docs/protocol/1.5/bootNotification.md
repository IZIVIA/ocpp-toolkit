# bootNotification — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.2 Boot Notification — pdf-page 19
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.3 BootNotification.req — pdf-page 41
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.4 BootNotification.conf — pdf-page 42

## bootNotification request

- schema: [`BootNotification.json`](../../../ocpp-1-5-json/src/main/resources/BootNotification.json)
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

- schema: [`BootNotificationResponse.json`](../../../ocpp-1-5-json/src/main/resources/BootNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.status` — string, required, enum: Accepted | Rejected
- `bootNotification.resp.currentTime` — string, required, format date-time
- `bootNotification.resp.heartbeatInterval` — integer, required

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
