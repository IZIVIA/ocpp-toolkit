# securityEventNotification — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SECURITYEVENTNOTIFICATION`
- Kotlin `SecurityEventNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationReq.kt)
- Kotlin `SecurityEventNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §A04 Security Event Notification — pdf-page 48
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.49 SecurityEventNotification — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.49.1 SecurityEventNotificationRequest — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.49.2 SecurityEventNotificationResponse — pdf-page 370
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 54

## securityEventNotification request

- schema: [`SecurityEventNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/SecurityEventNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `securityEventNotification.req.customData.vendorId` — string, required, maxLength 255
- `securityEventNotification.req.type` — string, required, maxLength 50 — Type of the security event. This value should be taken from the Security events list.
- `securityEventNotification.req.timestamp` — string, required, format date-time — Date and time at which the event occurred.
- `securityEventNotification.req.techInfo` — string, optional, maxLength 255 — Additional information about the occurred security event.

## securityEventNotification response

- schema: [`SecurityEventNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/SecurityEventNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `securityEventNotification.resp.customData.vendorId` — string, required, maxLength 255

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
