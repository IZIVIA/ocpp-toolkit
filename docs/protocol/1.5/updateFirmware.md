# updateFirmware — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.15 Update Firmware — pdf-page 40
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.47 UpdateFirmware.req — pdf-page 60
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.48 UpdateFirmware.conf — pdf-page 61

## updateFirmware request

- schema: [`UpdateFirmware.json`](../../../ocpp-1-5-json/src/main/resources/UpdateFirmware.json)
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.location` — string, required, format uri
- `updateFirmware.req.retries` — integer, optional
- `updateFirmware.req.retrieveDate` — string, required, format date-time
- `updateFirmware.req.retryInterval` — integer, optional

## updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../../ocpp-1-5-json/src/main/resources/UpdateFirmwareResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
