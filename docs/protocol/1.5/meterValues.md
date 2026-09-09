# meterValues — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.7 Meter Values — pdf-page 23
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.27 MeterValues.req — pdf-page 52
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.28 MeterValues.conf — pdf-page 52

## meterValues request

- schema: [`MeterValues.json`](../../../ocpp-1-5-json/src/main/resources/MeterValues.json)
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.connectorId` — integer, required
- `meterValues.req.transactionId` — integer, optional
- `meterValues.req.values` — array, optional
- `meterValues.req.values[].timestamp` — string, required, format date-time
- `meterValues.req.values[].value` — array, required
- `meterValues.req.values[].value[].value` — string, required
- `meterValues.req.values[].value[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End
- `meterValues.req.values[].value[].format` — string, optional, enum: Raw | SignedData
- `meterValues.req.values[].value[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Reactive.Export | Power.Reactive.Import | Current.Import | Current.Export | Voltage | Temperature
- `meterValues.req.values[].value[].location` — string, optional, enum: Inlet | Outlet | Body
- `meterValues.req.values[].value[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | var | kvar | Amp | Volt | Celsius

## meterValues response

- schema: [`MeterValuesResponse.json`](../../../ocpp-1-5-json/src/main/resources/MeterValuesResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
