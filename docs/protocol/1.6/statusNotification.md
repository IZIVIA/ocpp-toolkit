# statusNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.9 Status Notification — pdf-page 43
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.47 StatusNotification.req — pdf-page 76
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.48 StatusNotification.conf — pdf-page 77
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 11, 12

## statusNotification request

- schema: [`StatusNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/StatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.connectorId` — integer, required
- `statusNotification.req.errorCode` — string, required, enum: ConnectorLockFailure | EVCommunicationError | GroundFailure | HighTemperature | InternalError | LocalListConflict | NoError | OtherError | OverCurrentFailure | PowerMeterFailure | PowerSwitchFailure | ReaderFailure | ResetFailure | UnderVoltage | OverVoltage | WeakSignal
- `statusNotification.req.info` — string, optional, maxLength 50
- `statusNotification.req.status` — string, required, enum: Available | Preparing | Charging | SuspendedEVSE | SuspendedEV | Finishing | Reserved | Unavailable | Faulted
- `statusNotification.req.timestamp` — string, optional, format date-time
- `statusNotification.req.vendorId` — string, optional, maxLength 255
- `statusNotification.req.vendorErrorCode` — string, optional, maxLength 50

## statusNotification response

- schema: [`StatusNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/StatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
