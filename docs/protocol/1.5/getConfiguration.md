# getConfiguration — OCPP 1.5

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCONFIGURATION`
- Kotlin `GetConfigurationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationReq.kt)
- Kotlin `GetConfigurationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.6 Get Configuration — pdf-page 31
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.19 GetConfiguration.req — pdf-page 49
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.20 GetConfiguration.conf — pdf-page 49

## getConfiguration request

- schema: [`GetConfiguration.json`](../../../ocpp-1-5-json/src/main/resources/GetConfiguration.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.req.key` — array, optional
- `getConfiguration.req.key[]` — string, maxLength 50

## getConfiguration response

- schema: [`GetConfigurationResponse.json`](../../../ocpp-1-5-json/src/main/resources/GetConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.resp.configurationKey` — array, optional
- `getConfiguration.resp.configurationKey[].key` — string, required, maxLength 50
- `getConfiguration.resp.configurationKey[].readonly` — boolean, required
- `getConfiguration.resp.configurationKey[].value` — string, optional, maxLength 500
- `getConfiguration.resp.unknownKey` — array, optional
- `getConfiguration.resp.unknownKey[]` — string, maxLength 50

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
