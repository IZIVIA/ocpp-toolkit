# changeConfiguration — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGECONFIGURATION`
- Kotlin `ChangeConfigurationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationReq.kt)
- Kotlin `ChangeConfigurationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.3 Change Configuration — pdf-page 29
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.9 ChangeConfiguration.req — pdf-page 44
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.10 ChangeConfiguration.conf — pdf-page 47

## changeConfiguration request

- schema: [`ChangeConfiguration.json`](../../../ocpp-1-5-json/src/main/resources/ChangeConfiguration.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.req.key` — string, required, maxLength 50
- `changeConfiguration.req.value` — string, required, maxLength 500

## changeConfiguration response

- schema: [`ChangeConfigurationResponse.json`](../../../ocpp-1-5-json/src/main/resources/ChangeConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.resp.status` — string, required, enum: Accepted | Rejected | NotSupported

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
