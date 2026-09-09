# changeAvailability — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.2 Change Availability — pdf-page 50
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.7 ChangeAvailability.req — pdf-page 66
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.8 ChangeAvailability.conf — pdf-page 67

## changeAvailability request

- schema: [`ChangeAvailabilityRequest.json`](../../../ocpp-1-6-json/src/main/resources/ChangeAvailabilityRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.connectorId` — integer, required
- `changeAvailability.req.type` — string, required, enum: Inoperative | Operative

## changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../../ocpp-1-6-json/src/main/resources/ChangeAvailabilityResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.status` — string, required, enum: Accepted | Rejected | Scheduled

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
