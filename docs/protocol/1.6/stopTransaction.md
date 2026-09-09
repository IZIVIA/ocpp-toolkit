# stopTransaction — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STOPTRANSACTION`
- Kotlin `StopTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionReq.kt)
- Kotlin `StopTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.10 Stop Transaction — pdf-page 48
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.49 StopTransaction.req — pdf-page 77
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.50 StopTransaction.conf — pdf-page 77
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 7, 8, 9, 16, 33, 35, 36, 41, 45
- errata mentions: [`ocpp-j-1.6-errata`](../spec/1.6/ocpp-j-1.6-errata.md) pdf-page 3, 9

## stopTransaction request

- schema: [`StopTransactionRequest.json`](../../../ocpp-1-6-json/src/main/resources/StopTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.req.idTag` — string, optional, maxLength 20
- `stopTransaction.req.meterStop` — integer, required
- `stopTransaction.req.timestamp` — string, required, format date-time
- `stopTransaction.req.transactionId` — integer, required
- `stopTransaction.req.reason` — string, optional, enum: EmergencyStop | EVDisconnected | HardReset | Local | Other | PowerLoss | Reboot | Remote | SoftReset | UnlockCommand | DeAuthorized
- `stopTransaction.req.transactionData` — array, optional
- `stopTransaction.req.transactionData[].timestamp` — string, required, format date-time
- `stopTransaction.req.transactionData[].sampledValue` — array, required
- `stopTransaction.req.transactionData[].sampledValue[].value` — string, required
- `stopTransaction.req.transactionData[].sampledValue[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger | Other
- `stopTransaction.req.transactionData[].sampledValue[].format` — string, optional, enum: Raw | SignedData
- `stopTransaction.req.transactionData[].sampledValue[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | Power.Factor | Current.Import | Current.Export | Current.Offered | Voltage | Frequency | Temperature | SoC | RPM
- `stopTransaction.req.transactionData[].sampledValue[].phase` — string, optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1
- `stopTransaction.req.transactionData[].sampledValue[].location` — string, optional, enum: Cable | EV | Inlet | Outlet | Body
- `stopTransaction.req.transactionData[].sampledValue[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | VA | kVA | var | kvar | A | V | K | Celcius | Fahrenheit | Percent

## stopTransaction response

- schema: [`StopTransactionResponse.json`](../../../ocpp-1-6-json/src/main/resources/StopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.resp.idTagInfo` — object, optional
- `stopTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `stopTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `stopTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
