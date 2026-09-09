# statusNotification — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.9 Status Notification — pdf-page 25
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.41 StatusNotification.req — pdf-page 57
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.42 StatusNotification.conf — pdf-page 58

## statusNotification request

- schema: [`StatusNotification.json`](../../../ocpp-1-5-json/src/main/resources/StatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.connectorId` — integer, required
- `statusNotification.req.errorCode` — string, required, enum: ConnectorLockFailure | GroundFailure | HighTemperature | Mode3Error | NoError | OtherError | OverCurrentFailure | PowerMeterFailure | PowerSwitchFailure | ReaderFailure | ResetFailure | UnderVoltage | WeakSignal
- `statusNotification.req.info` — string, optional, maxLength 50
- `statusNotification.req.status` — string, required, enum: Available | Occupied | Reserved | Unavailable | Faulted
- `statusNotification.req.timestamp` — string, optional, format date-time
- `statusNotification.req.vendorId` — string, optional, maxLength 255
- `statusNotification.req.vendorErrorCode` — string, optional, maxLength 50

## statusNotification response

- schema: [`StatusNotificationResponse.json`](../../../ocpp-1-5-json/src/main/resources/StatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
