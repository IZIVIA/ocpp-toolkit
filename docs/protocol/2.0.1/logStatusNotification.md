# logStatusNotification — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.LOGSTATUSNOTIFICATION`
- Kotlin `LogStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationReq.kt)
- Kotlin `LogStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.31 LogStatusNotification — pdf-page 362
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.31.1 LogStatusNotificationRequest — pdf-page 362
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.31.2 LogStatusNotificationResponse — pdf-page 362
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 95

## logStatusNotification request

- schema: [`LogStatusNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/LogStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `logStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `logStatusNotification.req.status` — UploadLogStatusEnumType (string), required, enum: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading | AcceptedCanceled — This contains the status of the log upload.
- `logStatusNotification.req.requestId` — integer, optional — The request id that was provided in GetLogRequest that started this log upload. This field is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log upload ongoing.

## logStatusNotification response

- schema: [`LogStatusNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/LogStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `logStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

### logStatusNotification enumerations

- `logStatusNotification` `UploadLogStatusEnumType`: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading | AcceptedCanceled — This contains the status of the log upload.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
