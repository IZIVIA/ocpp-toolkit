# clearChargingProfile — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCHARGINGPROFILE`
- Kotlin `ClearChargingProfileReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileReq.kt)
- Kotlin `ClearChargingProfileResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.5 Clear Charging Profile — pdf-page 52
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.13 ClearChargingProfile.req — pdf-page 68
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.14 ClearChargingProfile.conf — pdf-page 68
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.21 ClearChargingProfileStatus — pdf-page 88
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 13, 42

## clearChargingProfile request

- schema: [`ClearChargingProfileRequest.json`](../../../ocpp-1-6-json/src/main/resources/ClearChargingProfileRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.req.id` — integer, optional
- `clearChargingProfile.req.connectorId` — integer, optional
- `clearChargingProfile.req.chargingProfilePurpose` — string, optional, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `clearChargingProfile.req.stackLevel` — integer, optional

## clearChargingProfile response

- schema: [`ClearChargingProfileResponse.json`](../../../ocpp-1-6-json/src/main/resources/ClearChargingProfileResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.resp.status` — string, required, enum: Accepted | Unknown

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
