# clearCache — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.4 Clear Cache — pdf-page 51
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.11 ClearCache.req — pdf-page 67
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.12 ClearCache.conf — pdf-page 68
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.20 ClearCacheStatus — pdf-page 87
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 12, 42

## clearCache request

- schema: [`ClearCacheRequest.json`](../../../ocpp-1-6-json/src/main/resources/ClearCacheRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## clearCache response

- schema: [`ClearCacheResponse.json`](../../../ocpp-1-6-json/src/main/resources/ClearCacheResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
