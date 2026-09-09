# customerInformation — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CUSTOMERINFORMATION`
- Kotlin `CustomerInformationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationReq.kt)
- Kotlin `CustomerInformationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2.4 Customer Information — pdf-page 325
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.12 CustomerInformation — pdf-page 353
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.12.1 CustomerInformationRequest — pdf-page 353
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.12.2 CustomerInformationResponse — pdf-page 353
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## customerInformation request

- schema: [`CustomerInformationRequest.json`](../../../ocpp-2-0-json/src/main/resources/CustomerInformationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `customerInformation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.customerCertificate` — CertificateHashDataType, optional
- `customerInformation.req.customerCertificate.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.customerCertificate.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.customerCertificate.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `customerInformation.req.customerCertificate.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `customerInformation.req.customerCertificate.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `customerInformation.req.customerCertificate.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `customerInformation.req.idToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `customerInformation.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.idToken.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.idToken.additionalInfo` — array, optional, minItems 1
- `customerInformation.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `customerInformation.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `customerInformation.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `customerInformation.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `customerInformation.req.requestId` — integer, required — The Id of the request.
- `customerInformation.req.report` — boolean, required — Flag indicating whether the Charging Station should return NotifyCustomerInformationRequest messages containing information about the customer referred to.
- `customerInformation.req.clear` — boolean, required — Flag indicating whether the Charging Station should clear all information about the customer referred to.
- `customerInformation.req.customerIdentifier` — string, optional, maxLength 64 — A (e.g. vendor specific) identifier of the customer this request refers to. This field contains a custom identifier other than IdToken and Certificate. One of the possible identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the request message.

## customerInformation response

- schema: [`CustomerInformationResponse.json`](../../../ocpp-2-0-json/src/main/resources/CustomerInformationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `customerInformation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.resp.customData.vendorId` — string, required, maxLength 255
- `customerInformation.resp.status` — CustomerInformationStatusEnumType (string), required, enum: Accepted | Rejected | Invalid — Indicates whether the request was accepted.
- `customerInformation.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `customerInformation.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `customerInformation.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `customerInformation.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### customerInformation enumerations

- `customerInformation` `CustomerInformationStatusEnumType`: Accepted | Rejected | Invalid — Indicates whether the request was accepted.
- `customerInformation` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `customerInformation` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
