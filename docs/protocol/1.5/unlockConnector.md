# unlockConnector — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.14 Unlock Connector — pdf-page 39
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.45 UnlockConnector.req — pdf-page 60
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.46 UnlockConnector.conf — pdf-page 60

## unlockConnector request

- schema: [`UnlockConnector.json`](../../../ocpp-1-5-json/src/main/resources/UnlockConnector.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.connectorId` — integer, required

## unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../../ocpp-1-5-json/src/main/resources/UnlockConnectorResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
