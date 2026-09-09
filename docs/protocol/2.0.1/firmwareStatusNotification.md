# firmwareStatusNotification — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.15 FirmwareStatusNotification — pdf-page 354
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.15.1 FirmwareStatusNotificationRequest — pdf-page 354
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.15.2 FirmwareStatusNotificationResponse — pdf-page 355
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 72, 92, 93

## firmwareStatusNotification request

- schema: [`FirmwareStatusNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/FirmwareStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `firmwareStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `firmwareStatusNotification.req.status` — FirmwareStatusEnumType (string), required, enum: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified — This contains the progress status of the firmware installation.
- `firmwareStatusNotification.req.requestId` — integer, optional — The request id that was provided in the UpdateFirmwareRequest that started this firmware update. This field is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no firmware update ongoing.

## firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/FirmwareStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `firmwareStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

### firmwareStatusNotification enumerations

- `firmwareStatusNotification` `FirmwareStatusEnumType`: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified — This contains the progress status of the firmware installation.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
