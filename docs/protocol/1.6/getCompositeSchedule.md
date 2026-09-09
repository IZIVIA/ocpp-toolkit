# getCompositeSchedule — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCOMPOSITESCHEDULE`
- Kotlin `GetCompositeScheduleReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleReq.kt)
- Kotlin `GetCompositeScheduleResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.7 Get Composite Schedule — pdf-page 52
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.21 GetCompositeSchedule.req — pdf-page 70
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.22 GetCompositeSchedule.conf — pdf-page 70
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.26 GetCompositeScheduleStatus — pdf-page 89
- errata mentions: [`ocpp-1.6-errata`](../spec/1.6/ocpp-1.6-errata.md) pdf-page 13, 14, 21, 23, 36, 44

## getCompositeSchedule request

- schema: [`GetCompositeScheduleRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetCompositeScheduleRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.req.connectorId` — integer, required
- `getCompositeSchedule.req.duration` — integer, required
- `getCompositeSchedule.req.chargingRateUnit` — string, optional, enum: A | W

## getCompositeSchedule response

- schema: [`GetCompositeScheduleResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetCompositeScheduleResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.resp.status` — string, required, enum: Accepted | Rejected
- `getCompositeSchedule.resp.connectorId` — integer, optional
- `getCompositeSchedule.resp.scheduleStart` — string, optional, format date-time
- `getCompositeSchedule.resp.chargingSchedule` — object, optional
- `getCompositeSchedule.resp.chargingSchedule.duration` — integer, optional
- `getCompositeSchedule.resp.chargingSchedule.startSchedule` — string, optional, format date-time
- `getCompositeSchedule.resp.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod` — array, required
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `getCompositeSchedule.resp.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
