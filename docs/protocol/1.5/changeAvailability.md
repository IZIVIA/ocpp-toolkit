# changeAvailability — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.2 Change Availability — pdf-page 28
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.7 ChangeAvailability.req — pdf-page 43
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.8 ChangeAvailability.conf — pdf-page 44

## changeAvailability request

- schema: [`ChangeAvailability.json`](../../../ocpp-1-5-json/src/main/resources/ChangeAvailability.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.connectorId` — integer, required
- `changeAvailability.req.type` — string, required, enum: Inoperative | Operative

## changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../../ocpp-1-5-json/src/main/resources/ChangeAvailabilityResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.status` — string, required, enum: Accepted | Rejected | Scheduled

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
