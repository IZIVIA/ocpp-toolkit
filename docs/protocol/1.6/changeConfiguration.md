# changeConfiguration — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGECONFIGURATION`
- Kotlin `ChangeConfigurationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationReq.kt)
- Kotlin `ChangeConfigurationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.3 Change Configuration — pdf-page 50
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.9 ChangeConfiguration.req — pdf-page 67
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.10 ChangeConfiguration.conf — pdf-page 67
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 9, 46

## changeConfiguration request

- schema: [`ChangeConfigurationRequest.json`](../../../ocpp-1-6-json/src/main/resources/ChangeConfigurationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.req.key` — string, required, maxLength 50
- `changeConfiguration.req.value` — string, required, maxLength 500

## changeConfiguration response

- schema: [`ChangeConfigurationResponse.json`](../../../ocpp-1-6-json/src/main/resources/ChangeConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.resp.status` — string, required, enum: Accepted | Rejected | RebootRequired | NotSupported

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
