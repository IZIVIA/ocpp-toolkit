# updateFirmware — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.64 UpdateFirmware — pdf-page 378
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.64.1 UpdateFirmwareRequest — pdf-page 378
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.64.2 UpdateFirmwareResponse — pdf-page 379
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 74

## updateFirmware request

- schema: [`UpdateFirmwareRequest.json`](../../../ocpp-2-0-json/src/main/resources/UpdateFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.req.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.req.retries` — integer, optional — This specifies how many times Charging Station must try to download the firmware before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `updateFirmware.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.
- `updateFirmware.req.requestId` — integer, required — The Id of this request
- `updateFirmware.req.firmware` — FirmwareType, required — Firmware urn:x-enexis:ecdm:uid:2:233291 Represents a copy of the firmware that can be loaded/updated on the Charging Station.
- `updateFirmware.req.firmware.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.req.firmware.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.req.firmware.location` — string, required, maxLength 512 — Firmware. Location. URI urn:x-enexis:ecdm:uid:1:569460 URI defining the origin of the firmware.
- `updateFirmware.req.firmware.retrieveDateTime` — string, required, format date-time — Firmware. Retrieve. Date_ Time urn:x-enexis:ecdm:uid:1:569461 Date and time at which the firmware shall be retrieved.
- `updateFirmware.req.firmware.installDateTime` — string, optional, format date-time — Firmware. Install. Date_ Time urn:x-enexis:ecdm:uid:1:569462 Date and time at which the firmware shall be installed.
- `updateFirmware.req.firmware.signingCertificate` — string, optional, maxLength 5500 — Certificate with which the firmware was signed. PEM encoded X.509 certificate.
- `updateFirmware.req.firmware.signature` — string, optional, maxLength 800 — Firmware. Signature. Signature urn:x-enexis:ecdm:uid:1:569464 Base64 encoded firmware signature.

## updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../../ocpp-2-0-json/src/main/resources/UpdateFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.resp.status` — UpdateFirmwareStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate — This field indicates whether the Charging Station was able to accept the request.
- `updateFirmware.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `updateFirmware.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `updateFirmware.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### updateFirmware enumerations

- `updateFirmware` `UpdateFirmwareStatusEnumType`: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate — This field indicates whether the Charging Station was able to accept the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
