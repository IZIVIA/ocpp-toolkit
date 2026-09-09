# requestStopTransaction — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REQUESTSTOPTRANSACTION`
- Kotlin `RequestStopTransactionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionReq.kt)
- Kotlin `RequestStopTransactionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.45 RequestStopTransaction — pdf-page 368
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.45.1 RequestStopTransactionRequest — pdf-page 368
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.45.2 RequestStopTransactionResponse — pdf-page 368

## requestStopTransaction request

- schema: [`RequestStopTransactionRequest.json`](../../../ocpp-2-0-json/src/main/resources/RequestStopTransactionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStopTransaction.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.req.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.req.transactionId` — string, required, maxLength 36 — The identifier of the transaction which the Charging Station is requested to stop.

## requestStopTransaction response

- schema: [`RequestStopTransactionResponse.json`](../../../ocpp-2-0-json/src/main/resources/RequestStopTransactionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStopTransaction.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.resp.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.resp.status` — RequestStartStopStatusEnumType (string), required, enum: Accepted | Rejected — Status indicating whether Charging Station accepts the request to stop a transaction.
- `requestStopTransaction.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `requestStopTransaction.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `requestStopTransaction.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### requestStopTransaction enumerations

- `requestStopTransaction` `RequestStartStopStatusEnumType`: Accepted | Rejected — Status indicating whether Charging Station accepts the request to stop a transaction.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
