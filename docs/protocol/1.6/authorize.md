# authorize — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.1 Authorize — pdf-page 37
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.1 Authorize.req — pdf-page 65
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.2 Authorize.conf — pdf-page 65
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 28, 42, 43

## authorize request

- schema: [`AuthorizeRequest.json`](../../../ocpp-1-6-json/src/main/resources/AuthorizeRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.req.idTag` — string, required, maxLength 20

## authorize response

- schema: [`AuthorizeResponse.json`](../../../ocpp-1-6-json/src/main/resources/AuthorizeResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.resp.idTagInfo` — object, required
- `authorize.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `authorize.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
