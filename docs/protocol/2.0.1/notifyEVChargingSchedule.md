# notifyEVChargingSchedule — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVCHARGINGSCHEDULE`
- Kotlin `NotifyEVChargingScheduleReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleReq.kt)
- Kotlin `NotifyEVChargingScheduleResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2 NotifyEVChargingScheduleRequest — pdf-page 242
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.37 NotifyEVChargingSchedule — pdf-page 364
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.37.1 NotifyEVChargingScheduleRequest — pdf-page 364
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.37.2 NotifyEVChargingScheduleResponse — pdf-page 364
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 40

## notifyEVChargingSchedule request

- schema: [`NotifyEVChargingScheduleRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEVChargingScheduleRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingSchedule.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.timeBase` — string, required, format date-time — Periods contained in the charging profile are relative to this point in time.
- `notifyEVChargingSchedule.req.chargingSchedule` — ChargingScheduleType, required — Charging_ Schedule urn:x-oca:ocpp:uid:2:233256 Charging schedule structure defines a list of charging periods, as used in: GetCompositeSchedule.conf and ChargingProfile.
- `notifyEVChargingSchedule.req.chargingSchedule.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.id` — integer, required — Identifies the ChargingSchedule.
- `notifyEVChargingSchedule.req.chargingSchedule.startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `notifyEVChargingSchedule.req.chargingSchedule.duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `notifyEVChargingSchedule.req.chargingSchedule.minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `notifyEVChargingSchedule.req.evseId` — integer, required — The charging schedule contained in this notification applies to an EVSE. EvseId must be &gt; 0.

## notifyEVChargingSchedule response

- schema: [`NotifyEVChargingScheduleResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEVChargingScheduleResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingSchedule.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.resp.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether the CSMS has been able to process the message successfully. It does not imply any approval of the charging schedule.
- `notifyEVChargingSchedule.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `notifyEVChargingSchedule.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `notifyEVChargingSchedule.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### notifyEVChargingSchedule enumerations

- `notifyEVChargingSchedule` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyEVChargingSchedule` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `notifyEVChargingSchedule` `GenericStatusEnumType`: Accepted | Rejected — Returns whether the CSMS has been able to process the message successfully. It does not imply any approval of the charging schedule.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
