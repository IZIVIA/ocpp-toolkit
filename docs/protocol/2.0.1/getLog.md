# getLog — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOG`
- Kotlin `GetLogReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogReq.kt)
- Kotlin `GetLogResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.24 GetLog — pdf-page 358
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.24.1 GetLogRequest — pdf-page 359
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.24.2 GetLogResponse — pdf-page 359
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## getLog request

- schema: [`GetLogRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetLogRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLog.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.req.customData.vendorId` — string, required, maxLength 255
- `getLog.req.log` — LogParametersType, required — Log urn:x-enexis:ecdm:uid:2:233373 Generic class for the configuration of logging entries.
- `getLog.req.log.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.req.log.customData.vendorId` — string, required, maxLength 255
- `getLog.req.log.remoteLocation` — string, required, maxLength 512 — Log. Remote_ Location. URI urn:x-enexis:ecdm:uid:1:569484 The URL of the location at the remote system where the log should be stored.
- `getLog.req.log.oldestTimestamp` — string, optional, format date-time — Log. Oldest_ Timestamp. Date_ Time urn:x-enexis:ecdm:uid:1:569477 This contains the date and time of the oldest logging information to include in the diagnostics.
- `getLog.req.log.latestTimestamp` — string, optional, format date-time — Log. Latest_ Timestamp. Date_ Time urn:x-enexis:ecdm:uid:1:569482 This contains the date and time of the latest logging information to include in the diagnostics.
- `getLog.req.logType` — LogEnumType (string), required, enum: DiagnosticsLog | SecurityLog — This contains the type of log file that the Charging Station should send.
- `getLog.req.requestId` — integer, required — The Id of this request
- `getLog.req.retries` — integer, optional — This specifies how many times the Charging Station must try to upload the log before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `getLog.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.

## getLog response

- schema: [`GetLogResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetLogResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLog.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.resp.customData.vendorId` — string, required, maxLength 255
- `getLog.resp.status` — LogStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled — This field indicates whether the Charging Station was able to accept the request.
- `getLog.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getLog.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getLog.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getLog.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getLog.resp.filename` — string, optional, maxLength 255 — This contains the name of the log file that will be uploaded. This field is not present when no logging information is available.

### getLog enumerations

- `getLog` `LogEnumType`: DiagnosticsLog | SecurityLog — This contains the type of log file that the Charging Station should send.
- `getLog` `LogStatusEnumType`: Accepted | Rejected | AcceptedCanceled — This field indicates whether the Charging Station was able to accept the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
