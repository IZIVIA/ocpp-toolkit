# getDiagnostics — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETDIAGNOSTICS`
- Kotlin `GetDiagnosticsReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsReq.kt)
- Kotlin `GetDiagnosticsResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.7 Get Diagnostics — pdf-page 32
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.21 GetDiagnostics.req — pdf-page 50
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.22 GetDiagnostics.conf — pdf-page 50

## getDiagnostics request

- schema: [`GetDiagnostics.json`](../../../ocpp-1-5-json/src/main/resources/GetDiagnostics.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.req.location` — string, required, format uri
- `getDiagnostics.req.retries` — integer, optional
- `getDiagnostics.req.retryInterval` — integer, optional
- `getDiagnostics.req.startTime` — string, optional, format date-time
- `getDiagnostics.req.stopTime` — string, optional, format date-time

## getDiagnostics response

- schema: [`GetDiagnosticsResponse.json`](../../../ocpp-1-5-json/src/main/resources/GetDiagnosticsResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.resp.fileName` — string, optional, maxLength 255

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
