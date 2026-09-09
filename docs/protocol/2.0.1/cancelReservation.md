# cancelReservation — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §H02 Cancel Reservation — pdf-page 209
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.3 CancelReservation — pdf-page 349
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.3.1 CancelReservationRequest — pdf-page 349
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.3.2 CancelReservationResponse — pdf-page 349

## cancelReservation request

- schema: [`CancelReservationRequest.json`](../../../ocpp-2-0-json/src/main/resources/CancelReservationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.req.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.req.reservationId` — integer, required — Id of the reservation to cancel.

## cancelReservation response

- schema: [`CancelReservationResponse.json`](../../../ocpp-2-0-json/src/main/resources/CancelReservationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.resp.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.resp.status` — CancelReservationStatusEnumType (string), required, enum: Accepted | Rejected — This indicates the success or failure of the canceling of a reservation by CSMS.
- `cancelReservation.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `cancelReservation.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `cancelReservation.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### cancelReservation enumerations

- `cancelReservation` `CancelReservationStatusEnumType`: Accepted | Rejected — This indicates the success or failure of the canceling of a reservation by CSMS.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
