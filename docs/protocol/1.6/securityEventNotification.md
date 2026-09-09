# securityEventNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SECURITYEVENTNOTIFICATION`
- Kotlin `SecurityEventNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationReq.kt)
- Kotlin `SecurityEventNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §A04 Security Event Notification — pdf-page 37
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.15 SecurityEventNotification.req — pdf-page 53
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.16 SecurityEventNotification.conf — pdf-page 53

## securityEventNotification request

- schema: [`SecurityEventNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/SecurityEventNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SecurityEventNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.req.type` — string, required, maxLength 50
- `securityEventNotification.req.timestamp` — string, required, format date-time
- `securityEventNotification.req.techInfo` — string, optional, maxLength 255

## securityEventNotification response

- schema: [`SecurityEventNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/SecurityEventNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SecurityEventNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
