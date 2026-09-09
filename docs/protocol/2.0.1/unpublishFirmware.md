# unpublishFirmware — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNPUBLISHFIRMWARE`
- Kotlin `UnpublishFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareReq.kt)
- Kotlin `UnpublishFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.63 UnpublishFirmware — pdf-page 378
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.63.1 UnpublishFirmwareRequest — pdf-page 378
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.63.2 UnpublishFirmwareResponse — pdf-page 378

## unpublishFirmware request

- schema: [`UnpublishFirmwareRequest.json`](../../../ocpp-2-0-json/src/main/resources/UnpublishFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unpublishFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unpublishFirmware.req.customData.vendorId` — string, required, maxLength 255
- `unpublishFirmware.req.checksum` — string, required, maxLength 32 — The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.

## unpublishFirmware response

- schema: [`UnpublishFirmwareResponse.json`](../../../ocpp-2-0-json/src/main/resources/UnpublishFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unpublishFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unpublishFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `unpublishFirmware.resp.status` — UnpublishFirmwareStatusEnumType (string), required, enum: DownloadOngoing | NoFirmware | Unpublished — Indicates whether the Local Controller succeeded in unpublishing the firmware.

### unpublishFirmware enumerations

- `unpublishFirmware` `UnpublishFirmwareStatusEnumType`: DownloadOngoing | NoFirmware | Unpublished — Indicates whether the Local Controller succeeded in unpublishing the firmware.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
