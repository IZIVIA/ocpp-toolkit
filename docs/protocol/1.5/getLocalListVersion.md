# getLocalListVersion — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.8 Get Local List Version — pdf-page 33
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.23 GetLocalListVersion.req — pdf-page 51
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.24 GetLocalListVersion.conf — pdf-page 51

## getLocalListVersion request

- schema: [`GetLocalListVersion.json`](../../../ocpp-1-5-json/src/main/resources/GetLocalListVersion.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../../ocpp-1-5-json/src/main/resources/GetLocalListVersionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.listVersion` — integer, required

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
