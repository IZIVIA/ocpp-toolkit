# notifyEVChargingNeeds — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVCHARGINGNEEDS`
- Kotlin `NotifyEVChargingNeedsReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsReq.kt)
- Kotlin `NotifyEVChargingNeedsResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.36 NotifyEVChargingNeeds — pdf-page 364
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.36.1 NotifyEVChargingNeedsRequest — pdf-page 364
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.36.2 NotifyEVChargingNeedsResponse — pdf-page 364
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 39, 41, 42
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 63

## notifyEVChargingNeeds request

- schema: [`NotifyEVChargingNeedsRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEVChargingNeedsRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingNeeds.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.maxScheduleTuples` — integer, optional — Contains the maximum schedule tuples the car supports per schedule.
- `notifyEVChargingNeeds.req.chargingNeeds` — ChargingNeedsType, required — Charging_ Needs urn:x-oca:ocpp:uid:2:233249
- `notifyEVChargingNeeds.req.chargingNeeds.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters` — ACChargingParametersType, optional — AC_ Charging_ Parameters urn:x-oca:ocpp:uid:2:233250 EV AC charging parameters.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.energyAmount` — integer, required — AC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount urn:x-oca:ocpp:uid:1:569211 Amount of energy requested (in Wh). This includes energy required for preconditioning.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMinCurrent` — integer, required — AC_ Charging_ Parameters. EV_ Min. Current urn:x-oca:ocpp:uid:1:569212 Minimum current (amps) supported by the electric vehicle (per phase).
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMaxCurrent` — integer, required — AC_ Charging_ Parameters. EV_ Max. Current urn:x-oca:ocpp:uid:1:569213 Maximum current (amps) supported by the electric vehicle (per phase). Includes cable capacity.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMaxVoltage` — integer, required — AC_ Charging_ Parameters. EV_ Max. Voltage urn:x-oca:ocpp:uid:1:569214 Maximum voltage supported by the electric vehicle
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters` — DCChargingParametersType, optional — DC_ Charging_ Parameters urn:x-oca:ocpp:uid:2:233251 EV DC charging parameters
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxCurrent` — integer, required — DC_ Charging_ Parameters. EV_ Max. Current urn:x-oca:ocpp:uid:1:569215 Maximum current (amps) supported by the electric vehicle. Includes cable capacity.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxVoltage` — integer, required — DC_ Charging_ Parameters. EV_ Max. Voltage urn:x-oca:ocpp:uid:1:569216 Maximum voltage supported by the electric vehicle
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.energyAmount` — integer, optional — DC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount urn:x-oca:ocpp:uid:1:569217 Amount of energy requested (in Wh). This inludes energy required for preconditioning.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxPower` — integer, optional — DC_ Charging_ Parameters. EV_ Max. Power urn:x-oca:ocpp:uid:1:569218 Maximum power (in W) supported by the electric vehicle. Required for DC charging.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.stateOfCharge` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. State_ Of_ Charge. Numeric urn:x-oca:ocpp:uid:1:569219 Energy available in the battery (in percent of the battery capacity)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evEnergyCapacity` — integer, optional — DC_ Charging_ Parameters. EV_ Energy_ Capacity. Numeric urn:x-oca:ocpp:uid:1:569220 Capacity of the electric vehicle battery (in Wh)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.fullSoC` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. Full_ SOC. Percentage urn:x-oca:ocpp:uid:1:569221 Percentage of SoC at which the EV considers the battery fully charged. (possible values: 0 - 100)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.bulkSoC` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. Bulk_ SOC. Percentage urn:x-oca:ocpp:uid:1:569222 Percentage of SoC at which the EV considers a fast charging process to end. (possible values: 0 - 100)
- `notifyEVChargingNeeds.req.chargingNeeds.requestedEnergyTransfer` — EnergyTransferModeEnumType (string), required, enum: DC | AC_single_phase | AC_two_phase | AC_three_phase — Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code urn:x-oca:ocpp:uid:1:569209 Mode of energy transfer requested by the EV.
- `notifyEVChargingNeeds.req.chargingNeeds.departureTime` — string, optional, format date-time — Charging_ Needs. Departure_ Time. Date_ Time urn:x-oca:ocpp:uid:1:569223 Estimated departure time of the EV.
- `notifyEVChargingNeeds.req.evseId` — integer, required — Defines the EVSE and connector to which the EV is connected. EvseId may not be 0.

## notifyEVChargingNeeds response

- schema: [`NotifyEVChargingNeedsResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEVChargingNeedsResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingNeeds.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.resp.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.resp.status` — NotifyEVChargingNeedsStatusEnumType (string), required, enum: Accepted | Rejected | Processing — Returns whether the CSMS has been able to process the message successfully. It does not imply that the evChargingNeeds can be met with the current charging profile.
- `notifyEVChargingNeeds.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `notifyEVChargingNeeds.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `notifyEVChargingNeeds.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### notifyEVChargingNeeds enumerations

- `notifyEVChargingNeeds` `EnergyTransferModeEnumType`: DC | AC_single_phase | AC_two_phase | AC_three_phase — Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code urn:x-oca:ocpp:uid:1:569209 Mode of energy transfer requested by the EV.
- `notifyEVChargingNeeds` `NotifyEVChargingNeedsStatusEnumType`: Accepted | Rejected | Processing — Returns whether the CSMS has been able to process the message successfully. It does not imply that the evChargingNeeds can be met with the current charging profile.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
