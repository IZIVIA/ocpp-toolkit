# clearedChargingLimit — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.CLEAREDCHARGINGLIMIT`
- Kotlin `ClearedChargingLimitReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitReq.kt)
- Kotlin `ClearedChargingLimitResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.9 ClearedChargingLimit — pdf-page 351
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.9.1 ClearedChargingLimitRequest — pdf-page 352
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.9.2 ClearedChargingLimitResponse — pdf-page 352

## clearedChargingLimit request

- schema: [`ClearedChargingLimitRequest.json`](../../../ocpp-2-0-json/src/main/resources/ClearedChargingLimitRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearedChargingLimit.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearedChargingLimit.req.customData.vendorId` — string, required, maxLength 255
- `clearedChargingLimit.req.chargingLimitSource` — ChargingLimitSourceEnumType (string), required, enum: EMS | Other | SO | CSO — Source of the charging limit.
- `clearedChargingLimit.req.evseId` — integer, optional — EVSE Identifier.

## clearedChargingLimit response

- schema: [`ClearedChargingLimitResponse.json`](../../../ocpp-2-0-json/src/main/resources/ClearedChargingLimitResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearedChargingLimit.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearedChargingLimit.resp.customData.vendorId` — string, required, maxLength 255

### clearedChargingLimit enumerations

- `clearedChargingLimit` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO — Source of the charging limit.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
