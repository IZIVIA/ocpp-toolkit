# setChargingProfile — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETCHARGINGPROFILE`
- Kotlin `SetChargingProfileReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileReq.kt)
- Kotlin `SetChargingProfileResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.16 Set Charging Profile — pdf-page 58
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.43 SetChargingProfile.req — pdf-page 75
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.44 SetChargingProfile.conf — pdf-page 75
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 16, 17, 22, 44

## setChargingProfile request

- schema: [`SetChargingProfileRequest.json`](../../../ocpp-1-6-json/src/main/resources/SetChargingProfileRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.req.connectorId` — integer, required
- `setChargingProfile.req.csChargingProfiles` — object, required
- `setChargingProfile.req.csChargingProfiles.chargingProfileId` — integer, required
- `setChargingProfile.req.csChargingProfiles.transactionId` — integer, optional
- `setChargingProfile.req.csChargingProfiles.stackLevel` — integer, required
- `setChargingProfile.req.csChargingProfiles.chargingProfilePurpose` — string, required, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `setChargingProfile.req.csChargingProfiles.chargingProfileKind` — string, required, enum: Absolute | Recurring | Relative
- `setChargingProfile.req.csChargingProfiles.recurrencyKind` — string, optional, enum: Daily | Weekly
- `setChargingProfile.req.csChargingProfiles.validFrom` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.validTo` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.chargingSchedule` — object, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.duration` — integer, optional
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.startSchedule` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod` — array, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

## setChargingProfile response

- schema: [`SetChargingProfileResponse.json`](../../../ocpp-1-6-json/src/main/resources/SetChargingProfileResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.resp.status` — string, required, enum: Accepted | Rejected | NotSupported

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
