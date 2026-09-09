# meterValues — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.7 Meter Values — pdf-page 40
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.31 MeterValues.req — pdf-page 72
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.32 MeterValues.conf — pdf-page 72
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 4, 6, 7, 8, 9, 10, 19, 41, 45
- errata mentions: [`ocpp-j-1.6-errata`](../spec/1.6/ocpp-j-1.6-errata.md) pdf-page 3

## meterValues request

- schema: [`MeterValuesRequest.json`](../../../ocpp-1-6-json/src/main/resources/MeterValuesRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.connectorId` — integer, required
- `meterValues.req.transactionId` — integer, optional
- `meterValues.req.meterValue` — array, required
- `meterValues.req.meterValue[].timestamp` — string, required, format date-time
- `meterValues.req.meterValue[].sampledValue` — array, required
- `meterValues.req.meterValue[].sampledValue[].value` — string, required
- `meterValues.req.meterValue[].sampledValue[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger | Other
- `meterValues.req.meterValue[].sampledValue[].format` — string, optional, enum: Raw | SignedData
- `meterValues.req.meterValue[].sampledValue[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | Power.Factor | Current.Import | Current.Export | Current.Offered | Voltage | Frequency | Temperature | SoC | RPM
- `meterValues.req.meterValue[].sampledValue[].phase` — string, optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1
- `meterValues.req.meterValue[].sampledValue[].location` — string, optional, enum: Cable | EV | Inlet | Outlet | Body
- `meterValues.req.meterValue[].sampledValue[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | VA | kVA | var | kvar | A | V | K | Celcius | Celsius | Fahrenheit | Percent

## meterValues response

- schema: [`MeterValuesResponse.json`](../../../ocpp-1-6-json/src/main/resources/MeterValuesResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
