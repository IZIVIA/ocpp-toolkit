# reserveNow — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.47 ReserveNow — pdf-page 369
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.47.1 ReserveNowRequest — pdf-page 369
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.47.2 ReserveNowResponse — pdf-page 369
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 96

## reserveNow request

- schema: [`ReserveNowRequest.json`](../../../ocpp-2-0-json/src/main/resources/ReserveNowRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.id` — integer, required — Id of reservation.
- `reserveNow.req.expiryDateTime` — string, required, format date-time — Date and time at which the reservation expires.
- `reserveNow.req.connectorType` — ConnectorEnumType (string), optional, enum: cCCS1 | cCCS2 | cG105 | cTesla | cType1 | cType2 | s309-1P-16A | s309-1P-32A | s309-3P-16A | s309-3P-32A | sBS1361 | sCEE-7-7 | sType2 | sType3 | Other1PhMax16A | Other1PhOver16A | Other3Ph | Pan | wInductive | wResonant | Undetermined | Unknown — This field specifies the connector type.
- `reserveNow.req.idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `reserveNow.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.idToken.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.idToken.additionalInfo` — array, optional, minItems 1
- `reserveNow.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `reserveNow.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `reserveNow.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `reserveNow.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `reserveNow.req.evseId` — integer, optional — This contains ID of the evse to be reserved.
- `reserveNow.req.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `reserveNow.req.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.groupIdToken.additionalInfo` — array, optional, minItems 1
- `reserveNow.req.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `reserveNow.req.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `reserveNow.req.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `reserveNow.req.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

## reserveNow response

- schema: [`ReserveNowResponse.json`](../../../ocpp-2-0-json/src/main/resources/ReserveNowResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.resp.customData.vendorId` — string, required, maxLength 255
- `reserveNow.resp.status` — ReserveNowStatusEnumType (string), required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable — This indicates the success or failure of the reservation.
- `reserveNow.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `reserveNow.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `reserveNow.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `reserveNow.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### reserveNow enumerations

- `reserveNow` `ConnectorEnumType`: cCCS1 | cCCS2 | cG105 | cTesla | cType1 | cType2 | s309-1P-16A | s309-1P-32A | s309-3P-16A | s309-3P-32A | sBS1361 | sCEE-7-7 | sType2 | sType3 | Other1PhMax16A | Other1PhOver16A | Other3Ph | Pan | wInductive | wResonant | Undetermined | Unknown — This field specifies the connector type.
- `reserveNow` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `reserveNow` `ReserveNowStatusEnumType`: Accepted | Faulted | Occupied | Rejected | Unavailable — This indicates the success or failure of the reservation.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
