# requestStartTransaction — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REQUESTSTARTTRANSACTION`
- Kotlin `RequestStartTransactionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionReq.kt)
- Kotlin `RequestStartTransactionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.44 RequestStartTransaction — pdf-page 368
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.44.1 RequestStartTransactionRequest — pdf-page 368
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.44.2 RequestStartTransactionResponse — pdf-page 368
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 16, 25, 26
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 96

## requestStartTransaction request

- schema: [`RequestStartTransactionRequest.json`](../../../ocpp-2-0-json/src/main/resources/RequestStartTransactionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStartTransaction.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.evseId` — integer, optional — Number of the EVSE on which to start the transaction. EvseId SHALL be &gt; 0
- `requestStartTransaction.req.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `requestStartTransaction.req.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.groupIdToken.additionalInfo` — array, optional, minItems 1
- `requestStartTransaction.req.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `requestStartTransaction.req.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `requestStartTransaction.req.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `requestStartTransaction.req.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction.req.idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `requestStartTransaction.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.idToken.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.idToken.additionalInfo` — array, optional, minItems 1
- `requestStartTransaction.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `requestStartTransaction.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `requestStartTransaction.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `requestStartTransaction.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction.req.remoteStartId` — integer, required — Id given by the server to this start request. The Charging Station might return this in the &lt;&lt;transactioneventrequest, TransactionEventRequest&gt;&gt;, letting the server know which transaction was started for this request. Use to start a transaction.
- `requestStartTransaction.req.chargingProfile` — ChargingProfileType, optional — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `requestStartTransaction.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Id of ChargingProfile.
- `requestStartTransaction.req.chargingProfile.stackLevel` — integer, required — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `requestStartTransaction.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), required, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `requestStartTransaction.req.chargingProfile.chargingProfileKind` — ChargingProfileKindEnumType (string), required, enum: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `requestStartTransaction.req.chargingProfile.recurrencyKind` — RecurrencyKindEnumType (string), optional, enum: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `requestStartTransaction.req.chargingProfile.validFrom` — string, optional, format date-time — Charging_ Profile. Valid_ From. Date_ Time urn:x-oca:ocpp:uid:1:569234 Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
- `requestStartTransaction.req.chargingProfile.validTo` — string, optional, format date-time — Charging_ Profile. Valid_ To. Date_ Time urn:x-oca:ocpp:uid:1:569235 Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
- `requestStartTransaction.req.chargingProfile.chargingSchedule` — array, required, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `requestStartTransaction.req.chargingProfile.transactionId` — string, optional, maxLength 36 — SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.

## requestStartTransaction response

- schema: [`RequestStartTransactionResponse.json`](../../../ocpp-2-0-json/src/main/resources/RequestStartTransactionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStartTransaction.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.resp.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.resp.status` — RequestStartStopStatusEnumType (string), required, enum: Accepted | Rejected — Status indicating whether the Charging Station accepts the request to start a transaction.
- `requestStartTransaction.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `requestStartTransaction.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `requestStartTransaction.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `requestStartTransaction.resp.transactionId` — string, optional, maxLength 36 — When the transaction was already started by the Charging Station before the RequestStartTransactionRequest was received, for example: cable plugged in first. This contains the transactionId of the already started transaction.

### requestStartTransaction enumerations

- `requestStartTransaction` `ChargingProfileKindEnumType`: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `requestStartTransaction` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `requestStartTransaction` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `requestStartTransaction` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `requestStartTransaction` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction` `RecurrencyKindEnumType`: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `requestStartTransaction` `RequestStartStopStatusEnumType`: Accepted | Rejected — Status indicating whether the Charging Station accepts the request to start a transaction.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
