# getCompositeSchedule — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCOMPOSITESCHEDULE`
- Kotlin `GetCompositeScheduleReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleReq.kt)
- Kotlin `GetCompositeScheduleResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §K08 Get Composite Schedule — pdf-page 258
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.20 GetCompositeSchedule — pdf-page 357
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.20.1 GetCompositeScheduleRequest — pdf-page 357
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.20.2 GetCompositeScheduleResponse — pdf-page 357
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 62

## getCompositeSchedule request

- schema: [`GetCompositeScheduleRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetCompositeScheduleRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.req.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.req.duration` — integer, required — Length of the requested schedule in seconds.
- `getCompositeSchedule.req.chargingRateUnit` — ChargingRateUnitEnumType (string), optional, enum: W | A — Can be used to force a power or current profile.
- `getCompositeSchedule.req.evseId` — integer, required — The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station will calculate the expected consumption for the grid connection.

## getCompositeSchedule response

- schema: [`GetCompositeScheduleResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetCompositeScheduleResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — The Charging Station will indicate if it was able to process the request
- `getCompositeSchedule.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getCompositeSchedule.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getCompositeSchedule.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getCompositeSchedule.resp.schedule` — CompositeScheduleType, optional — Composite_ Schedule urn:x-oca:ocpp:uid:2:233362
- `getCompositeSchedule.resp.schedule.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.schedule.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod` — array, required, minItems 1
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `getCompositeSchedule.resp.schedule.evseId` — integer, required — The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station calculated the expected consumption for the grid connection.
- `getCompositeSchedule.resp.schedule.duration` — integer, required — Duration of the schedule in seconds.
- `getCompositeSchedule.resp.schedule.scheduleStart` — string, required, format date-time — Composite_ Schedule. Start. Date_ Time urn:x-oca:ocpp:uid:1:569456 Date and time at which the schedule becomes active. All time measurements within the schedule are relative to this timestamp.
- `getCompositeSchedule.resp.schedule.chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — The unit of measure Limit is expressed in.

### getCompositeSchedule enumerations

- `getCompositeSchedule` `ChargingRateUnitEnumType`: W | A — Can be used to force a power or current profile.
- `getCompositeSchedule` `ChargingRateUnitEnumType`: W | A — The unit of measure Limit is expressed in.
- `getCompositeSchedule` `GenericStatusEnumType`: Accepted | Rejected — The Charging Station will indicate if it was able to process the request

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
