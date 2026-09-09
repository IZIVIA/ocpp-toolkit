# dataTransfer — OCPP 2.0.1

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.13 DataTransfer — pdf-page 353
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.13.1 DataTransferRequest — pdf-page 353
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.13.2 DataTransferResponse — pdf-page 354
- changelog mentions: [`changelog-2.0-to-2.0.1`](../spec/2.0.1/changelog-2.0-to-2.0.1.md) pdf-page 2, 17
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 88, 117, 118
- errata mentions: [`ocpp-2.0-part4-errata`](../spec/2.0.1/ocpp-2.0-part4-errata.md) pdf-page 6

## dataTransfer request

- schema: [`DataTransferRequest.json`](../../../ocpp-2-0-json/src/main/resources/DataTransferRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.req.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50 — May be used to indicate a specific message or implementation.
- `dataTransfer.req.data` — object, optional — Data without specified length or format. This needs to be decided by both parties (Open to implementation).
- `dataTransfer.req.vendorId` — string, required, maxLength 255 — This identifies the Vendor specific implementation

## dataTransfer response

- schema: [`DataTransferResponse.json`](../../../ocpp-2-0-json/src/main/resources/DataTransferResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.resp.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.resp.status` — DataTransferStatusEnumType (string), required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId — This indicates the success or failure of the data transfer.
- `dataTransfer.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `dataTransfer.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `dataTransfer.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `dataTransfer.resp.data` — object, optional — Data without specified length or format, in response to request.

### dataTransfer enumerations

- `dataTransfer` `DataTransferStatusEnumType`: Accepted | Rejected | UnknownMessageId | UnknownVendorId — This indicates the success or failure of the data transfer.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
