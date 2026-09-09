# getLocalListVersion — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.10 Get Local List Version — pdf-page 54
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.27 GetLocalListVersion.req — pdf-page 71
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.28 GetLocalListVersion.conf — pdf-page 72
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 21, 22

## getLocalListVersion request

- schema: [`GetLocalListVersionRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetLocalListVersionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetLocalListVersionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.listVersion` — integer, required

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
