# clearCache — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.6 ClearCache — pdf-page 350
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.6.1 ClearCacheRequest — pdf-page 350
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.6.2 ClearCacheResponse — pdf-page 350

## clearCache request

- schema: [`ClearCacheRequest.json`](../../../ocpp-2-0-json/src/main/resources/ClearCacheRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.req.customData.vendorId` — string, required, maxLength 255

## clearCache response

- schema: [`ClearCacheResponse.json`](../../../ocpp-2-0-json/src/main/resources/ClearCacheResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.resp.customData.vendorId` — string, required, maxLength 255
- `clearCache.resp.status` — ClearCacheStatusEnumType (string), required, enum: Accepted | Rejected — Accepted if the Charging Station has executed the request, otherwise rejected.
- `clearCache.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearCache.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearCache.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearCache.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### clearCache enumerations

- `clearCache` `ClearCacheStatusEnumType`: Accepted | Rejected — Accepted if the Charging Station has executed the request, otherwise rejected.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
