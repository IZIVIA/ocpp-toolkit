# authorize — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.1 Authorize — pdf-page 18
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.1 Authorize.req — pdf-page 41
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.2 Authorize.conf — pdf-page 41

## authorize request

- schema: [`Authorize.json`](../../../ocpp-1-5-json/src/main/resources/Authorize.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.req.idTag` — string, required, maxLength 20

## authorize response

- schema: [`AuthorizeResponse.json`](../../../ocpp-1-5-json/src/main/resources/AuthorizeResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.resp.idTagInfo` — object, required
- `authorize.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `authorize.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
