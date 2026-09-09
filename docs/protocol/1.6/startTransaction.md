# startTransaction — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STARTTRANSACTION`
- Kotlin `StartTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionReq.kt)
- Kotlin `StartTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.8 Start Transaction — pdf-page 42
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.45 StartTransaction.req — pdf-page 76
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.46 StartTransaction.conf — pdf-page 76
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 4, 10, 11, 22, 25

## startTransaction request

- schema: [`StartTransactionRequest.json`](../../../ocpp-1-6-json/src/main/resources/StartTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.req.connectorId` — integer, required
- `startTransaction.req.idTag` — string, required, maxLength 20
- `startTransaction.req.meterStart` — integer, required
- `startTransaction.req.reservationId` — integer, optional
- `startTransaction.req.timestamp` — string, required, format date-time

## startTransaction response

- schema: [`StartTransactionResponse.json`](../../../ocpp-1-6-json/src/main/resources/StartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.resp.idTagInfo` — object, required
- `startTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `startTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `startTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `startTransaction.resp.transactionId` — integer, required

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
