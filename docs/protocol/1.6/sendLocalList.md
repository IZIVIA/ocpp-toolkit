# sendLocalList — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.15 Send Local List — pdf-page 57
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.41 SendLocalList.req — pdf-page 74
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.42 SendLocalList.conf — pdf-page 75
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 21
- errata mentions: [`ocpp-j-1.6-errata`](../spec/1.6/ocpp-j-1.6-errata.md) pdf-page 3, 11

## sendLocalList request

- schema: [`SendLocalListRequest.json`](../../../ocpp-1-6-json/src/main/resources/SendLocalListRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.req.listVersion` — integer, required
- `sendLocalList.req.localAuthorizationList` — array, optional
- `sendLocalList.req.localAuthorizationList[].idTag` — string, required, maxLength 20
- `sendLocalList.req.localAuthorizationList[].idTagInfo` — object, optional
- `sendLocalList.req.localAuthorizationList[].idTagInfo.expiryDate` — string, optional, format date-time
- `sendLocalList.req.localAuthorizationList[].idTagInfo.parentIdTag` — string, optional, maxLength 20
- `sendLocalList.req.localAuthorizationList[].idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `sendLocalList.req.updateType` — string, required, enum: Differential | Full

## sendLocalList response

- schema: [`SendLocalListResponse.json`](../../../ocpp-1-6-json/src/main/resources/SendLocalListResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.status` — string, required, enum: Accepted | Failed | NotSupported | VersionMismatch

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
