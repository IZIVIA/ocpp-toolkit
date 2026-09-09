# getDiagnostics — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETDIAGNOSTICS`
- Kotlin `GetDiagnosticsReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsReq.kt)
- Kotlin `GetDiagnosticsResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.9 Get Diagnostics — pdf-page 53
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.25 GetDiagnostics.req — pdf-page 71
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.26 GetDiagnostics.conf — pdf-page 71
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 14, 15

## getDiagnostics request

- schema: [`GetDiagnosticsRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetDiagnosticsRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.req.location` — string, required, format uri
- `getDiagnostics.req.retries` — integer, optional
- `getDiagnostics.req.retryInterval` — integer, optional
- `getDiagnostics.req.startTime` — string, optional, format date-time
- `getDiagnostics.req.stopTime` — string, optional, format date-time

## getDiagnostics response

- schema: [`GetDiagnosticsResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetDiagnosticsResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.resp.fileName` — string, optional, maxLength 255

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
