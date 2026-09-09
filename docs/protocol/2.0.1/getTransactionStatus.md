# getTransactionStatus — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETTRANSACTIONSTATUS`
- Kotlin `GetTransactionStatusReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusReq.kt)
- Kotlin `GetTransactionStatusResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.27 GetTransactionStatus — pdf-page 360
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.27.1 GetTransactionStatusRequest — pdf-page 360
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.27.2 GetTransactionStatusResponse — pdf-page 360

## getTransactionStatus request

- schema: [`GetTransactionStatusRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetTransactionStatusRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getTransactionStatus.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getTransactionStatus.req.customData.vendorId` — string, required, maxLength 255
- `getTransactionStatus.req.transactionId` — string, optional, maxLength 36 — The Id of the transaction for which the status is requested.

## getTransactionStatus response

- schema: [`GetTransactionStatusResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetTransactionStatusResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getTransactionStatus.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getTransactionStatus.resp.customData.vendorId` — string, required, maxLength 255
- `getTransactionStatus.resp.ongoingIndicator` — boolean, optional — Whether the transaction is still ongoing.
- `getTransactionStatus.resp.messagesInQueue` — boolean, required — Whether there are still message to be delivered.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
