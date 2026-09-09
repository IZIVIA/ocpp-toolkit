# getLocalListVersion — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §D02 Get Local List Version — pdf-page 123
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.23 GetLocalListVersion — pdf-page 358
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.23.1 GetLocalListVersionRequest — pdf-page 358
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.23.2 GetLocalListVersionResponse — pdf-page 358

## getLocalListVersion request

- schema: [`GetLocalListVersionRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetLocalListVersionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLocalListVersion.req.customData.vendorId` — string, required, maxLength 255

## getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetLocalListVersionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLocalListVersion.resp.customData.vendorId` — string, required, maxLength 255
- `getLocalListVersion.resp.versionNumber` — integer, required — This contains the current version number of the local authorization list in the Charging Station.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
