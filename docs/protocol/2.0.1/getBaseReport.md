# getBaseReport — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETBASEREPORT`
- Kotlin `GetBaseReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportReq.kt)
- Kotlin `GetBaseReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §B07 Get Base Report — pdf-page 66
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.17 GetBaseReport — pdf-page 355
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.17.1 GetBaseReportRequest — pdf-page 355
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.17.2 GetBaseReportResponse — pdf-page 355
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 118

## getBaseReport request

- schema: [`GetBaseReportRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetBaseReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getBaseReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.req.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.req.requestId` — integer, required — The Id of the request.
- `getBaseReport.req.reportBase` — ReportBaseEnumType (string), required, enum: ConfigurationInventory | FullInventory | SummaryInventory — This field specifies the report base.

## getBaseReport response

- schema: [`GetBaseReportResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetBaseReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getBaseReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.resp.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This indicates whether the Charging Station is able to accept this request.
- `getBaseReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getBaseReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getBaseReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### getBaseReport enumerations

- `getBaseReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This indicates whether the Charging Station is able to accept this request.
- `getBaseReport` `ReportBaseEnumType`: ConfigurationInventory | FullInventory | SummaryInventory — This field specifies the report base.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
