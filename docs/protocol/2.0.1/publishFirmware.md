# publishFirmware — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.PUBLISHFIRMWARE`
- Kotlin `PublishFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareReq.kt)
- Kotlin `PublishFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.41 PublishFirmware — pdf-page 366
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.41.1 PublishFirmwareRequest — pdf-page 366
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.41.2 PublishFirmwareResponse — pdf-page 366
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## publishFirmware request

- schema: [`PublishFirmwareRequest.json`](../../../ocpp-2-0-json/src/main/resources/PublishFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.req.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.req.location` — string, required, maxLength 512 — This contains a string containing a URI pointing to a location from which to retrieve the firmware.
- `publishFirmware.req.retries` — integer, optional — This specifies how many times Charging Station must try to download the firmware before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `publishFirmware.req.checksum` — string, required, maxLength 32 — The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
- `publishFirmware.req.requestId` — integer, required — The Id of the request.
- `publishFirmware.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.

## publishFirmware response

- schema: [`PublishFirmwareResponse.json`](../../../ocpp-2-0-json/src/main/resources/PublishFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Indicates whether the request was accepted.
- `publishFirmware.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `publishFirmware.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `publishFirmware.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### publishFirmware enumerations

- `publishFirmware` `GenericStatusEnumType`: Accepted | Rejected — Indicates whether the request was accepted.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
