# reserveNow — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.11 Reserve Now — pdf-page 35
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.33 ReserveNow.req — pdf-page 54
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.34 ReserveNow.conf — pdf-page 54

## reserveNow request

- schema: [`ReserveNow.json`](../../../ocpp-1-5-json/src/main/resources/ReserveNow.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.connectorId` — integer, required
- `reserveNow.req.expiryDate` — string, required, format date-time
- `reserveNow.req.idTag` — string, required, maxLength 20
- `reserveNow.req.parentIdTag` — string, optional, maxLength 20
- `reserveNow.req.reservationId` — integer, required

## reserveNow response

- schema: [`ReserveNowResponse.json`](../../../ocpp-1-5-json/src/main/resources/ReserveNowResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.status` — string, required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
