# reserveNow — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.13 Reserve Now — pdf-page 56
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.37 ReserveNow.req — pdf-page 73
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.38 ReserveNow.conf — pdf-page 74

## reserveNow request

- schema: [`ReserveNowRequest.json`](../../../ocpp-1-6-json/src/main/resources/ReserveNowRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.connectorId` — integer, required
- `reserveNow.req.expiryDate` — string, required, format date-time
- `reserveNow.req.idTag` — string, required, maxLength 20
- `reserveNow.req.parentIdTag` — string, optional, maxLength 20
- `reserveNow.req.reservationId` — integer, required

## reserveNow response

- schema: [`ReserveNowResponse.json`](../../../ocpp-1-6-json/src/main/resources/ReserveNowResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.status` — string, required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
