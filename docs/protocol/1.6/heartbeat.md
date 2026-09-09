# heartbeat — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.6 Heartbeat — pdf-page 40
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.29 Heartbeat.req — pdf-page 72
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.30 Heartbeat.conf — pdf-page 72
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §9.1.10 HeartbeatInterval — pdf-page 104

## heartbeat request

- schema: [`HeartbeatRequest.json`](../../../ocpp-1-6-json/src/main/resources/HeartbeatRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## heartbeat response

- schema: [`HeartbeatResponse.json`](../../../ocpp-1-6-json/src/main/resources/HeartbeatResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.currentTime` — string, required, format date-time

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
