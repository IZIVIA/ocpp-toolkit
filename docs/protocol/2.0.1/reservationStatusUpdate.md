# reservationStatusUpdate — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.RESERVATIONSTATUSUPDATE`
- Kotlin `ReservationStatusUpdateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateReq.kt)
- Kotlin `ReservationStatusUpdateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.46 ReservationStatusUpdate — pdf-page 369
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.46.1 ReservationStatusUpdateRequest — pdf-page 369
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.46.2 ReservationStatusUpdateResponse — pdf-page 369
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 51

## reservationStatusUpdate request

- schema: [`ReservationStatusUpdateRequest.json`](../../../ocpp-2-0-json/src/main/resources/ReservationStatusUpdateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reservationStatusUpdate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reservationStatusUpdate.req.customData.vendorId` — string, required, maxLength 255
- `reservationStatusUpdate.req.reservationId` — integer, required — The ID of the reservation.
- `reservationStatusUpdate.req.reservationUpdateStatus` — ReservationUpdateStatusEnumType (string), required, enum: Expired | Removed — The updated reservation status.

## reservationStatusUpdate response

- schema: [`ReservationStatusUpdateResponse.json`](../../../ocpp-2-0-json/src/main/resources/ReservationStatusUpdateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reservationStatusUpdate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reservationStatusUpdate.resp.customData.vendorId` — string, required, maxLength 255

### reservationStatusUpdate enumerations

- `reservationStatusUpdate` `ReservationUpdateStatusEnumType`: Expired | Removed — The updated reservation status.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
