# remoteStartTransaction — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTARTTRANSACTION`
- Kotlin `RemoteStartTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionReq.kt)
- Kotlin `RemoteStartTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.9 Remote Start Transaction — pdf-page 34
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.29 RemoteStartTransaction.req — pdf-page 52
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.30 RemoteStartTransaction.conf — pdf-page 53

## remoteStartTransaction request

- schema: [`RemoteStartTransaction.json`](../../../ocpp-1-5-json/src/main/resources/RemoteStartTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.req.connectorId` — integer, optional
- `remoteStartTransaction.req.idTag` — string, required, maxLength 20

## remoteStartTransaction response

- schema: [`RemoteStartTransactionResponse.json`](../../../ocpp-1-5-json/src/main/resources/RemoteStartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
