# extendedTriggerMessage — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.EXTENDEDTRIGGERMESSAGE`
- Kotlin `ExtendedTriggerMessageReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageReq.kt)
- Kotlin `ExtendedTriggerMessageResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.5 ExtendedTriggerMessage.req — pdf-page 50
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.6 ExtendedTriggerMessage.conf — pdf-page 50

## extendedTriggerMessage request

- schema: [`ExtendedTriggerMessageRequest.json`](../../../ocpp-1-6-json/src/main/resources/ExtendedTriggerMessageRequest.json) · `urn:OCPP:Cp:1.6:2020:3:ExtendedTriggerMessage.req`
- `additionalProperties: false` — an unknown field fails validation

- `extendedTriggerMessage.req.requestedMessage` — MessageTriggerEnumType (string), required, enum: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargePointCertificate | StatusNotification
- `extendedTriggerMessage.req.connectorId` — integer, optional

## extendedTriggerMessage response

- schema: [`ExtendedTriggerMessageResponse.json`](../../../ocpp-1-6-json/src/main/resources/ExtendedTriggerMessageResponse.json) · `urn:OCPP:Cp:1.6:2020:3:ExtendedTriggerMessage.conf`
- `additionalProperties: false` — an unknown field fails validation

- `extendedTriggerMessage.resp.status` — TriggerMessageStatusEnumType (string), required, enum: Accepted | Rejected | NotImplemented

### extendedTriggerMessage enumerations

- `extendedTriggerMessage` `MessageTriggerEnumType`: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargePointCertificate | StatusNotification
- `extendedTriggerMessage` `TriggerMessageStatusEnumType`: Accepted | Rejected | NotImplemented

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
