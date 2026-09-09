# remoteStopTransaction — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTOPTRANSACTION`
- Kotlin `RemoteStopTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionReq.kt)
- Kotlin `RemoteStopTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.10 Remote Stop Transaction — pdf-page 35
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.31 RemoteStopTransaction.req — pdf-page 53
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.32 RemoteStopTransaction.conf — pdf-page 53

## remoteStopTransaction request

- schema: [`RemoteStopTransaction.json`](../../../ocpp-1-5-json/src/main/resources/RemoteStopTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.req.transactionId` — integer, required

## remoteStopTransaction response

- schema: [`RemoteStopTransactionResponse.json`](../../../ocpp-1-5-json/src/main/resources/RemoteStopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
