# setChargingProfile — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETCHARGINGPROFILE`
- Kotlin `SetChargingProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileReq.kt)
- Kotlin `SetChargingProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §K01 SetChargingProfile — pdf-page 243
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.51 SetChargingProfile — pdf-page 371
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.51.1 SetChargingProfileRequest — pdf-page 371
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.51.2 SetChargingProfileResponse — pdf-page 371
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 3, 35, 41
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 57, 58, 59, 71

## setChargingProfile request

- schema: [`SetChargingProfileRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetChargingProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.evseId` — integer, required — For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal limit for the whole Charging Station.
- `setChargingProfile.req.chargingProfile` — ChargingProfileType, required — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `setChargingProfile.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Id of ChargingProfile.
- `setChargingProfile.req.chargingProfile.stackLevel` — integer, required — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `setChargingProfile.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), required, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `setChargingProfile.req.chargingProfile.chargingProfileKind` — ChargingProfileKindEnumType (string), required, enum: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `setChargingProfile.req.chargingProfile.recurrencyKind` — RecurrencyKindEnumType (string), optional, enum: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `setChargingProfile.req.chargingProfile.validFrom` — string, optional, format date-time — Charging_ Profile. Valid_ From. Date_ Time urn:x-oca:ocpp:uid:1:569234 Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
- `setChargingProfile.req.chargingProfile.validTo` — string, optional, format date-time — Charging_ Profile. Valid_ To. Date_ Time urn:x-oca:ocpp:uid:1:569235 Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
- `setChargingProfile.req.chargingProfile.chargingSchedule` — array, required, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `setChargingProfile.req.chargingProfile.transactionId` — string, optional, maxLength 36 — SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.

## setChargingProfile response

- schema: [`SetChargingProfileResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetChargingProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.resp.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.resp.status` — ChargingProfileStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether the Charging Station has been able to process the message successfully. This does not guarantee the schedule will be followed to the letter. There might be other constraints the Charging Station may need to take into account.
- `setChargingProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setChargingProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setChargingProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### setChargingProfile enumerations

- `setChargingProfile` `ChargingProfileKindEnumType`: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `setChargingProfile` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `setChargingProfile` `ChargingProfileStatusEnumType`: Accepted | Rejected — Returns whether the Charging Station has been able to process the message successfully. This does not guarantee the schedule will be followed to the letter. There might be other constraints the Charging Station may need to take into account.
- `setChargingProfile` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `setChargingProfile` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `setChargingProfile` `RecurrencyKindEnumType`: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
