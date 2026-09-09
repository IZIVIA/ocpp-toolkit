# getChargingProfiles — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCHARGINGPROFILES`
- Kotlin `GetChargingProfilesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesReq.kt)
- Kotlin `GetChargingProfilesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §K09 Get Charging Profiles — pdf-page 260
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.19 GetChargingProfiles — pdf-page 356
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.19.1 GetChargingProfilesRequest — pdf-page 356
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.19.2 GetChargingProfilesResponse — pdf-page 356
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 3, 7, 36

## getChargingProfiles request

- schema: [`GetChargingProfilesRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetChargingProfilesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getChargingProfiles.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.req.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.req.requestId` — integer, required — Reference identification that is to be used by the Charging Station in the &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; when provided.
- `getChargingProfiles.req.evseId` — integer, optional — For which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted, all installed charging profiles SHALL be reported.
- `getChargingProfiles.req.chargingProfile` — ChargingProfileCriterionType, required — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `getChargingProfiles.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), optional, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `getChargingProfiles.req.chargingProfile.stackLevel` — integer, optional — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `getChargingProfiles.req.chargingProfile.chargingProfileId` — array, optional, minItems 1 — List of all the chargingProfileIds requested. Any ChargingProfile that matches one of these profiles will be reported. If omitted, the Charging Station SHALL not filter on chargingProfileId. This field SHALL NOT contain more ids than set in &lt;&lt;configkey-charging-profile-entries,ChargingProfileEntries.maxLimit&gt;&gt;
- `getChargingProfiles.req.chargingProfile.chargingProfileId[]` — integer
- `getChargingProfiles.req.chargingProfile.chargingLimitSource` — array, optional, maxItems 4, minItems 1 — For which charging limit sources, charging profiles SHALL be reported. If omitted, the Charging Station SHALL not filter on chargingLimitSource.
- `getChargingProfiles.req.chargingProfile.chargingLimitSource[]` — ChargingLimitSourceEnumType, enum: EMS | Other | SO | CSO

## getChargingProfiles response

- schema: [`GetChargingProfilesResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetChargingProfilesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getChargingProfiles.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.resp.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.resp.status` — GetChargingProfileStatusEnumType (string), required, enum: Accepted | NoProfiles — This indicates whether the Charging Station is able to process this request and will send &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; messages.
- `getChargingProfiles.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getChargingProfiles.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getChargingProfiles.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### getChargingProfiles enumerations

- `getChargingProfiles` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO
- `getChargingProfiles` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `getChargingProfiles` `GetChargingProfileStatusEnumType`: Accepted | NoProfiles — This indicates whether the Charging Station is able to process this request and will send &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; messages.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
