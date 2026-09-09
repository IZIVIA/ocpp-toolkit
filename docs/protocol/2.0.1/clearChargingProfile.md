# clearChargingProfile — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCHARGINGPROFILE`
- Kotlin `ClearChargingProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileReq.kt)
- Kotlin `ClearChargingProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §K10 Clear Charging Profile — pdf-page 261
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.7 ClearChargingProfile — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.7.1 ClearChargingProfileRequest — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.7.2 ClearChargingProfileResponse — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2.14 ClearChargingProfileType — pdf-page 384
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 38
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 91

## clearChargingProfile request

- schema: [`ClearChargingProfileRequest.json`](../../../ocpp-2-0-json/src/main/resources/ClearChargingProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.req.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.req.chargingProfileId` — integer, optional — The Id of the charging profile to clear.
- `clearChargingProfile.req.chargingProfileCriteria` — ClearChargingProfileType, optional — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of a ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `clearChargingProfile.req.chargingProfileCriteria.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.req.chargingProfileCriteria.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.req.chargingProfileCriteria.evseId` — integer, optional — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0) specifies the charging profile for the overall Charging Station. Absence of this parameter means the clearing applies to all charging profiles that match the other criteria in the request.
- `clearChargingProfile.req.chargingProfileCriteria.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), optional, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Specifies to purpose of the charging profiles that will be cleared, if they meet the other criteria in the request.
- `clearChargingProfile.req.chargingProfileCriteria.stackLevel` — integer, optional — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Specifies the stackLevel for which charging profiles will be cleared, if they meet the other criteria in the request.

## clearChargingProfile response

- schema: [`ClearChargingProfileResponse.json`](../../../ocpp-2-0-json/src/main/resources/ClearChargingProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.resp.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.resp.status` — ClearChargingProfileStatusEnumType (string), required, enum: Accepted | Unknown — Indicates if the Charging Station was able to execute the request.
- `clearChargingProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearChargingProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearChargingProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### clearChargingProfile enumerations

- `clearChargingProfile` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Specifies to purpose of the charging profiles that will be cleared, if they meet the other criteria in the request.
- `clearChargingProfile` `ClearChargingProfileStatusEnumType`: Accepted | Unknown — Indicates if the Charging Station was able to execute the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
