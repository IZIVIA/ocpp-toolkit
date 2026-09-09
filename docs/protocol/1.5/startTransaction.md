# startTransaction — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STARTTRANSACTION`
- Kotlin `StartTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionReq.kt)
- Kotlin `StartTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.8 Start Transaction — pdf-page 24
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.39 StartTransaction.req — pdf-page 56
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.40 StartTransaction.conf — pdf-page 57

## startTransaction request

- schema: [`StartTransaction.json`](../../../ocpp-1-5-json/src/main/resources/StartTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.req.connectorId` — integer, required
- `startTransaction.req.idTag` — string, required, maxLength 20
- `startTransaction.req.meterStart` — integer, required
- `startTransaction.req.reservationId` — integer, optional
- `startTransaction.req.timestamp` — string, required, format date-time

## startTransaction response

- schema: [`StartTransactionResponse.json`](../../../ocpp-1-5-json/src/main/resources/StartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.resp.idTagInfo` — object, required
- `startTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `startTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `startTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `startTransaction.resp.transactionId` — integer, required

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
