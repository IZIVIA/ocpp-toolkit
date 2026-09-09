# remoteStartTransaction — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTARTTRANSACTION`
- Kotlin `RemoteStartTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionReq.kt)
- Kotlin `RemoteStartTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.11 Remote Start Transaction — pdf-page 54
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.33 RemoteStartTransaction.req — pdf-page 73
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.34 RemoteStartTransaction.conf — pdf-page 73
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 15, 18, 26

## remoteStartTransaction request

- schema: [`RemoteStartTransactionRequest.json`](../../../ocpp-1-6-json/src/main/resources/RemoteStartTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.req.connectorId` — integer, optional
- `remoteStartTransaction.req.idTag` — string, required, maxLength 20
- `remoteStartTransaction.req.chargingProfile` — object, optional
- `remoteStartTransaction.req.chargingProfile.chargingProfileId` — integer, required
- `remoteStartTransaction.req.chargingProfile.transactionId` — integer, optional
- `remoteStartTransaction.req.chargingProfile.stackLevel` — integer, required
- `remoteStartTransaction.req.chargingProfile.chargingProfilePurpose` — string, required, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `remoteStartTransaction.req.chargingProfile.chargingProfileKind` — string, required, enum: Absolute | Recurring | Relative
- `remoteStartTransaction.req.chargingProfile.recurrencyKind` — string, optional, enum: Daily | Weekly
- `remoteStartTransaction.req.chargingProfile.validFrom` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.validTo` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.chargingSchedule` — object, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.duration` — integer, optional
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.startSchedule` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod` — array, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

## remoteStartTransaction response

- schema: [`RemoteStartTransactionResponse.json`](../../../ocpp-1-6-json/src/main/resources/RemoteStartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
