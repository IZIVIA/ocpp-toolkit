# cancelReservation — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.1 Cancel Reservation — pdf-page 27
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.5 CancelReservation.req — pdf-page 43
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.6 CancelReservation.conf — pdf-page 43
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §7.5 CancelReservationStatus — pdf-page 63

## cancelReservation request

- schema: [`CancelReservation.json`](../../../ocpp-1-5-json/src/main/resources/CancelReservation.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.reservationId` — integer, required

## cancelReservation response

- schema: [`CancelReservationResponse.json`](../../../ocpp-1-5-json/src/main/resources/CancelReservationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
