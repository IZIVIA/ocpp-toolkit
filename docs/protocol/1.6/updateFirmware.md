# updateFirmware — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.19 Update Firmware — pdf-page 62
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.55 UpdateFirmware.req — pdf-page 78
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.56 UpdateFirmware.conf — pdf-page 79
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 19, 20, 24, 41, 42
- errata mentions: [`ocpp-j-1.6-errata`](../spec/1.6/ocpp-j-1.6-errata.md) pdf-page 7

## updateFirmware request

- schema: [`UpdateFirmwareRequest.json`](../../../ocpp-1-6-json/src/main/resources/UpdateFirmwareRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.location` — string, required, format uri
- `updateFirmware.req.retries` — integer, optional
- `updateFirmware.req.retrieveDate` — string, required, format date-time
- `updateFirmware.req.retryInterval` — integer, optional

## updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../../ocpp-1-6-json/src/main/resources/UpdateFirmwareResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
