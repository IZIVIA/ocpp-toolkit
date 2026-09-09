# reset — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.14 Reset — pdf-page 57
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.39 Reset.req — pdf-page 74
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.40 Reset.conf — pdf-page 74
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.41 ResetStatus — pdf-page 97
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.42 ResetType — pdf-page 97
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §9.1.22 ResetRetries — pdf-page 107
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 16, 20, 32, 43

## reset request

- schema: [`ResetRequest.json`](../../../ocpp-1-6-json/src/main/resources/ResetRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.type` — string, required, enum: Hard | Soft

## reset response

- schema: [`ResetResponse.json`](../../../ocpp-1-6-json/src/main/resources/ResetResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.status` — string, required, enum: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
