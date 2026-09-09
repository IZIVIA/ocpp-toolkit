# publishFirmwareStatusNotification — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.PUBLISHFIRMWARESTATUSNOTIFICATION`
- Kotlin `PublishFirmwareStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationReq.kt)
- Kotlin `PublishFirmwareStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.42 PublishFirmwareStatusNotification — pdf-page 367
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.42.1 PublishFirmwareStatusNotificationRequest — pdf-page 367
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.42.2 PublishFirmwareStatusNotificationResponse — pdf-page 367
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 73, 93, 102

## publishFirmwareStatusNotification request

- schema: [`PublishFirmwareStatusNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/PublishFirmwareStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmwareStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmwareStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `publishFirmwareStatusNotification.req.status` — PublishFirmwareStatusEnumType (string), required, enum: Idle | DownloadScheduled | Downloading | Downloaded | Published | DownloadFailed | DownloadPaused | InvalidChecksum | ChecksumVerified | PublishFailed — This contains the progress status of the publishfirmware installation.
- `publishFirmwareStatusNotification.req.location` — array, optional, minItems 1 — Required if status is Published. Can be multiple URI’s, if the Local Controller supports e.g. HTTP, HTTPS, and FTP.
- `publishFirmwareStatusNotification.req.location[]` — string, maxLength 512
- `publishFirmwareStatusNotification.req.requestId` — integer, optional — The request id that was provided in the PublishFirmwareRequest which triggered this action.

## publishFirmwareStatusNotification response

- schema: [`PublishFirmwareStatusNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/PublishFirmwareStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmwareStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmwareStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

### publishFirmwareStatusNotification enumerations

- `publishFirmwareStatusNotification` `PublishFirmwareStatusEnumType`: Idle | DownloadScheduled | Downloading | Downloaded | Published | DownloadFailed | DownloadPaused | InvalidChecksum | ChecksumVerified | PublishFailed — This contains the progress status of the publishfirmware installation.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
