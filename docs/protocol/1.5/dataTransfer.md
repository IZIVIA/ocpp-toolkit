# dataTransfer — OCPP 1.5

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferReq.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferResp.kt`](../../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferResp.kt)
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §4.3 Data Transfer — pdf-page 20
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §5.5 Data Transfer — pdf-page 31
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.13 DataTransfer.req — pdf-page 47
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §6.14 DataTransfer.conf — pdf-page 48
- spec: [`ocpp-1.5-specification`](../spec/1.5/ocpp-1.5-specification.md) §7.14 DataTransferStatus — pdf-page 66

## dataTransfer request

- schema: [`DataTransfer.json`](../../../ocpp-1-5-json/src/main/resources/DataTransfer.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50
- `dataTransfer.req.data` — string, optional

## dataTransfer response

- schema: [`DataTransferResponse.json`](../../../ocpp-1-5-json/src/main/resources/DataTransferResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.status` — string, required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId
- `dataTransfer.resp.data` — string, optional

---

[all OCPP 1.5 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
