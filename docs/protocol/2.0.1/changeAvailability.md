# changeAvailability — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.5 ChangeAvailability — pdf-page 350
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.5.1 ChangeAvailabilityRequest — pdf-page 350
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.5.2 ChangeAvailabilityResponse — pdf-page 350
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 49

## changeAvailability request

- schema: [`ChangeAvailabilityRequest.json`](../../../ocpp-2-0-json/src/main/resources/ChangeAvailabilityRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.req.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `changeAvailability.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.req.evse.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `changeAvailability.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `changeAvailability.req.operationalStatus` — OperationalStatusEnumType (string), required, enum: Inoperative | Operative — This contains the type of availability change that the Charging Station should perform.

## changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../../ocpp-2-0-json/src/main/resources/ChangeAvailabilityResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.resp.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.resp.status` — ChangeAvailabilityStatusEnumType (string), required, enum: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the availability change.
- `changeAvailability.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `changeAvailability.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `changeAvailability.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### changeAvailability enumerations

- `changeAvailability` `ChangeAvailabilityStatusEnumType`: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the availability change.
- `changeAvailability` `OperationalStatusEnumType`: Inoperative | Operative — This contains the type of availability change that the Charging Station should perform.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
