# clearCache — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.4 Clear Cache — pdf-page 30
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.11 ClearCache.req — pdf-page 47
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.12 ClearCache.conf — pdf-page 47
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §7.12 ClearCacheStatus — pdf-page 66

## clearCache request

- schema: [`ClearCache.json`](../../../ocpp-1-5-json/src/main/resources/ClearCache.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## clearCache response

- schema: [`ClearCacheResponse.json`](../../../ocpp-1-5-json/src/main/resources/ClearCacheResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
