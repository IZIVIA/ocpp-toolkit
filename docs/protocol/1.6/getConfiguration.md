# getConfiguration — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCONFIGURATION`
- Kotlin `GetConfigurationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationReq.kt)
- Kotlin `GetConfigurationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.8 Get Configuration — pdf-page 53
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.23 GetConfiguration.req — pdf-page 70
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.24 GetConfiguration.conf — pdf-page 71
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §9.1.9 GetConfigurationMaxKeys — pdf-page 104
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 43

## getConfiguration request

- schema: [`GetConfigurationRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetConfigurationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.req.key` — array, optional
- `getConfiguration.req.key[]` — string, maxLength 50

## getConfiguration response

- schema: [`GetConfigurationResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.resp.configurationKey` — array, optional
- `getConfiguration.resp.configurationKey[].key` — string, required, maxLength 50
- `getConfiguration.resp.configurationKey[].readonly` — boolean, required
- `getConfiguration.resp.configurationKey[].value` — string, optional, maxLength 500
- `getConfiguration.resp.unknownKey` — array, optional
- `getConfiguration.resp.unknownKey[]` — string, maxLength 50

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
