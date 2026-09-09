# dataTransfer — OCPP 1.6

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferResp.kt)
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §4.3 Data Transfer — pdf-page 39
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §5.6 Data Transfer — pdf-page 52
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.15 DataTransfer.req — pdf-page 68
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §6.16 DataTransfer.conf — pdf-page 69
- spec: [`ocpp-1.6-edition-2`](../spec/1.6/ocpp-1.6-edition-2.md) §7.23 DataTransferStatus — pdf-page 88

## dataTransfer request

- schema: [`DataTransferRequest.json`](../../../ocpp-1-6-json/src/main/resources/DataTransferRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50
- `dataTransfer.req.data` — string, optional

## dataTransfer response

- schema: [`DataTransferResponse.json`](../../../ocpp-1-6-json/src/main/resources/DataTransferResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.status` — string, required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId
- `dataTransfer.resp.data` — string, optional

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
