# notifyCustomerInformation — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYCUSTOMERINFORMATION`
- Kotlin `NotifyCustomerInformationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationReq.kt)
- Kotlin `NotifyCustomerInformationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.34 NotifyCustomerInformation — pdf-page 363
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.34.1 NotifyCustomerInformationRequest — pdf-page 363
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.34.2 NotifyCustomerInformationResponse — pdf-page 363
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## notifyCustomerInformation request

- schema: [`NotifyCustomerInformationRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyCustomerInformationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyCustomerInformation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyCustomerInformation.req.customData.vendorId` — string, required, maxLength 255
- `notifyCustomerInformation.req.data` — string, required, maxLength 512 — (Part of) the requested data. No format specified in which the data is returned. Should be human readable.
- `notifyCustomerInformation.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the monitoringData follows in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
- `notifyCustomerInformation.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyCustomerInformation.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyCustomerInformation.req.requestId` — integer, required — The Id of the request.

## notifyCustomerInformation response

- schema: [`NotifyCustomerInformationResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyCustomerInformationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyCustomerInformation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyCustomerInformation.resp.customData.vendorId` — string, required, maxLength 255

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
