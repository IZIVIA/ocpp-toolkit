# remoteStopTransaction — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTOPTRANSACTION`
- Kotlin `RemoteStopTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionReq.kt)
- Kotlin `RemoteStopTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.12 Remote Stop Transaction — pdf-page 55
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.35 RemoteStopTransaction.req — pdf-page 73
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.36 RemoteStopTransaction.conf — pdf-page 73
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 15

## remoteStopTransaction request

- schema: [`RemoteStopTransactionRequest.json`](../../../ocpp-1-6-json/src/main/resources/RemoteStopTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.req.transactionId` — integer, required

## remoteStopTransaction response

- schema: [`RemoteStopTransactionResponse.json`](../../../ocpp-1-6-json/src/main/resources/RemoteStopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
