# stopTransaction — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STOPTRANSACTION`
- Kotlin `StopTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionReq.kt)
- Kotlin `StopTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.10 Stop Transaction — pdf-page 26
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.43 StopTransaction.req — pdf-page 58
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.44 StopTransaction.conf — pdf-page 59

## stopTransaction request

- schema: [`StopTransaction.json`](../../../ocpp-1-5-json/src/main/resources/StopTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.req.idTag` — string, optional, maxLength 20
- `stopTransaction.req.meterStop` — integer, required
- `stopTransaction.req.timestamp` — string, required, format date-time
- `stopTransaction.req.transactionId` — integer, required
- `stopTransaction.req.transactionData` — array, optional
- `stopTransaction.req.transactionData[].values` — array, optional
- `stopTransaction.req.transactionData[].values[].timestamp` — string, required, format date-time
- `stopTransaction.req.transactionData[].values[].value` — array, required
- `stopTransaction.req.transactionData[].values[].value[].value` — string, required
- `stopTransaction.req.transactionData[].values[].value[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End
- `stopTransaction.req.transactionData[].values[].value[].format` — string, optional, enum: Raw | SignedData
- `stopTransaction.req.transactionData[].values[].value[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Reactive.Export | Power.Reactive.Import | Current.Import | Current.Export | Voltage | Temperature
- `stopTransaction.req.transactionData[].values[].value[].location` — string, optional, enum: Inlet | Outlet | Body
- `stopTransaction.req.transactionData[].values[].value[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | var | kvar | Amp | Volt | Celsius

## stopTransaction response

- schema: [`StopTransactionResponse.json`](../../../ocpp-1-5-json/src/main/resources/StopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.resp.idTagInfo` — object, optional
- `stopTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `stopTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `stopTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
