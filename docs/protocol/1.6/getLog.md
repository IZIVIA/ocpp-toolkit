# getLog — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOG`
- Kotlin `GetLogReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogReq.kt)
- Kotlin `GetLogResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.9 GetLog.req — pdf-page 51
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.10 GetLog.conf — pdf-page 52

## getLog request

- schema: [`GetLogRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetLogRequest.json) · `urn:OCPP:Cp:1.6:2020:3:GetLog.req`
- `additionalProperties: false` — an unknown field fails validation

- `getLog.req.log` — LogParametersType, required
- `getLog.req.log.remoteLocation` — string, required, maxLength 512
- `getLog.req.log.oldestTimestamp` — string, optional, format date-time
- `getLog.req.log.latestTimestamp` — string, optional, format date-time
- `getLog.req.logType` — LogEnumType (string), required, enum: DiagnosticsLog | SecurityLog
- `getLog.req.requestId` — integer, required
- `getLog.req.retries` — integer, optional
- `getLog.req.retryInterval` — integer, optional

## getLog response

- schema: [`GetLogResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetLogResponse.json) · `urn:OCPP:Cp:1.6:2020:3:GetLog.conf`
- `additionalProperties: false` — an unknown field fails validation

- `getLog.resp.status` — LogStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled
- `getLog.resp.filename` — string, optional, maxLength 255

### getLog enumerations

- `getLog` `LogEnumType`: DiagnosticsLog | SecurityLog
- `getLog` `LogStatusEnumType`: Accepted | Rejected | AcceptedCanceled

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
