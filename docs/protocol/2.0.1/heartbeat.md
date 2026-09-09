# heartbeat — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §G02 Heartbeat — pdf-page 195
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.29 Heartbeat — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.29.1 HeartbeatRequest — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.29.2 HeartbeatResponse — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2.1.5 HeartbeatInterval — pdf-page 431

## heartbeat request

- schema: [`HeartbeatRequest.json`](../../../ocpp-2-0-json/src/main/resources/HeartbeatRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `heartbeat.req.customData.vendorId` — string, required, maxLength 255

## heartbeat response

- schema: [`HeartbeatResponse.json`](../../../ocpp-2-0-json/src/main/resources/HeartbeatResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `heartbeat.resp.customData.vendorId` — string, required, maxLength 255
- `heartbeat.resp.currentTime` — string, required, format date-time — Contains the current time of the CSMS.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
