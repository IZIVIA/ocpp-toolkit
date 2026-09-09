# reset — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.12 Reset — pdf-page 37
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.35 Reset.req — pdf-page 54
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.36 Reset.conf — pdf-page 55
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §7.33 ResetStatus — pdf-page 74
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §7.34 ResetType — pdf-page 74

## reset request

- schema: [`Reset.json`](../../../ocpp-1-5-json/src/main/resources/Reset.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.type` — string, required, enum: Hard | Soft

## reset response

- schema: [`ResetResponse.json`](../../../ocpp-1-5-json/src/main/resources/ResetResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
