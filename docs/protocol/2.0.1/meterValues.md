# meterValues — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §3.1 MeterValues — pdf-page 227
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.32 MeterValues — pdf-page 362
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.32.1 MeterValuesRequest — pdf-page 362
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.32.2 MeterValuesResponse — pdf-page 362
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 54, 60

## meterValues request

- schema: [`MeterValuesRequest.json`](../../../ocpp-2-0-json/src/main/resources/MeterValuesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.evseId` — integer, required — Request_ Body. EVSEID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:571101 This contains a number (&gt;0) designating an EVSE of the Charging Station. ‘0’ (zero) is used to designate the main power meter.
- `meterValues.req.meterValue` — array, required, minItems 1
- `meterValues.req.meterValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue` — array, required, minItems 1
- `meterValues.req.meterValue[].sampledValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].value` — number, required — Sampled_ Value. Value. Measure urn:x-oca:ocpp:uid:1:569260 Indicates the measured value.
- `meterValues.req.meterValue[].sampledValue[].context` — ReadingContextEnumType (string), optional, enum: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `meterValues.req.meterValue[].sampledValue[].measurand` — MeasurandEnumType (string), optional, enum: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `meterValues.req.meterValue[].sampledValue[].phase` — PhaseEnumType (string), optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `meterValues.req.meterValue[].sampledValue[].location` — LocationEnumType (string), optional, enum: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue` — SignedMeterValueType, optional — Represent a signed version of the meter value.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.signedMeterData` — string, required, maxLength 2500 — Base64 encoded, contains the signed data which might contain more then just the meter value. It can contain information like timestamps, reference to a customer etc.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.signingMethod` — string, required, maxLength 50 — Method used to create the digital signature.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.encodingMethod` — string, required, maxLength 50 — Method used to encode the meter values before applying the digital signature algorithm.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.publicKey` — string, required, maxLength 2500 — Base64 encoded, sending depends on configuration variable _PublicKeyWithSignedMeterValue_.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure` — UnitOfMeasureType, optional — Represents a UnitOfMeasure with a multiplier
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.unit` — string, optional, maxLength 20 — Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices. If an applicable unit is available in that list, otherwise a "custom" unit might be used.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.multiplier` — integer, optional — Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to the 3rd power. Default is 0.
- `meterValues.req.meterValue[].timestamp` — string, required, format date-time — Meter_ Value. Timestamp. Date_ Time urn:x-oca:ocpp:uid:1:569259 Timestamp for measured value(s).

## meterValues response

- schema: [`MeterValuesResponse.json`](../../../ocpp-2-0-json/src/main/resources/MeterValuesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.resp.customData.vendorId` — string, required, maxLength 255

### meterValues enumerations

- `meterValues` `LocationEnumType`: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `meterValues` `MeasurandEnumType`: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `meterValues` `PhaseEnumType`: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `meterValues` `ReadingContextEnumType`: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
