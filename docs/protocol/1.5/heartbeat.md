# heartbeat — OCPP 1.5

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.6 Heartbeat — pdf-page 22
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.25 Heartbeat.req — pdf-page 51
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.26 Heartbeat.conf — pdf-page 51

## heartbeat request

- schema: [`Heartbeat.json`](../../../ocpp-1-5-json/src/main/resources/Heartbeat.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## heartbeat response

- schema: [`HeartbeatResponse.json`](../../../ocpp-1-5-json/src/main/resources/HeartbeatResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.currentTime` — string, required, format date-time

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
