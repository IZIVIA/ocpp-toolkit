# cancelReservation — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.1 Cancel Reservation — pdf-page 50
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.5 CancelReservation.req — pdf-page 66
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.6 CancelReservation.conf — pdf-page 66
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.5 CancelReservationStatus — pdf-page 81

## cancelReservation request

- schema: [`CancelReservationRequest.json`](../../../ocpp-1-6-json/src/main/resources/CancelReservationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.reservationId` — integer, required

## cancelReservation response

- schema: [`CancelReservationResponse.json`](../../../ocpp-1-6-json/src/main/resources/CancelReservationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
