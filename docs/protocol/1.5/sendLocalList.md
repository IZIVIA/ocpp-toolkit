# sendLocalList — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.13 Send Local List — pdf-page 38
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.37 SendLocalList.req — pdf-page 55
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.38 SendLocalList.conf — pdf-page 56

## sendLocalList request

- schema: [`SendLocalList.json`](../../../ocpp-1-5-json/src/main/resources/SendLocalList.json)
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

- schema: [`SendLocalListResponse.json`](../../../ocpp-1-5-json/src/main/resources/SendLocalListResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.hash` — string, optional, maxLength 64
- `sendLocalList.resp.status` — string, required, enum: Accepted | Failed | HashError | NotSupported | VersionMismatch

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
