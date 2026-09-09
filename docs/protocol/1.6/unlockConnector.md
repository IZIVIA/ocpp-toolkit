# unlockConnector — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.18 Unlock Connector — pdf-page 61
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.53 UnlockConnector.req — pdf-page 78
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.54 UnlockConnector.conf — pdf-page 78
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 34

## unlockConnector request

- schema: [`UnlockConnectorRequest.json`](../../../ocpp-1-6-json/src/main/resources/UnlockConnectorRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.connectorId` — integer, required

## unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../../ocpp-1-6-json/src/main/resources/UnlockConnectorResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.status` — string, required, enum: Unlocked | UnlockFailed | NotSupported

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
