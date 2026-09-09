# statusNotification — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §G01 Status Notification — pdf-page 194
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.59 StatusNotification — pdf-page 375
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.59.1 StatusNotificationRequest — pdf-page 375
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.59.2 StatusNotificationResponse — pdf-page 375
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 16, 26, 28, 69
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 118

## statusNotification request

- schema: [`StatusNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/StatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `statusNotification.req.customData.vendorId` — string, required, maxLength 255
- `statusNotification.req.timestamp` — string, required, format date-time — The time for which the status is reported. If absent time of receipt of the message will be assumed.
- `statusNotification.req.connectorStatus` — ConnectorStatusEnumType (string), required, enum: Available | Occupied | Reserved | Unavailable | Faulted — This contains the current status of the Connector.
- `statusNotification.req.evseId` — integer, required — The id of the EVSE to which the connector belongs for which the the status is reported.
- `statusNotification.req.connectorId` — integer, required — The id of the connector within the EVSE for which the status is reported.

## statusNotification response

- schema: [`StatusNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/StatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `statusNotification.resp.customData.vendorId` — string, required, maxLength 255

### statusNotification enumerations

- `statusNotification` `ConnectorStatusEnumType`: Available | Occupied | Reserved | Unavailable | Faulted — This contains the current status of the Connector.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
