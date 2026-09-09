# getInstalledCertificateIds — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETINSTALLEDCERTIFICATEIDS`
- Kotlin `GetInstalledCertificateIdsReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt)
- Kotlin `GetInstalledCertificateIdsResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.22 GetInstalledCertificateIds — pdf-page 358
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.22.1 GetInstalledCertificateIdsRequest — pdf-page 358
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.22.2 GetInstalledCertificateIdsResponse — pdf-page 358
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 14, 16

## getInstalledCertificateIds request

- schema: [`GetInstalledCertificateIdsRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetInstalledCertificateIdsRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.req.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.req.certificateType` — array, optional, minItems 1 — Indicates the type of certificates requested. When omitted, all certificate types are requested.
- `getInstalledCertificateIds.req.certificateType[]` — GetCertificateIdUseEnumType, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate

## getInstalledCertificateIds response

- schema: [`GetInstalledCertificateIdsResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetInstalledCertificateIdsResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.status` — GetInstalledCertificateStatusEnumType (string), required, enum: Accepted | NotFound — Charging Station indicates if it can process the request.
- `getInstalledCertificateIds.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getInstalledCertificateIds.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getInstalledCertificateIds.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getInstalledCertificateIds.resp.certificateHashDataChain` — array, optional, minItems 1
- `getInstalledCertificateIds.resp.certificateHashDataChain[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData` — CertificateHashDataType, required
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateType` — GetCertificateIdUseEnumType (string), required, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate — Indicates the type of the requested certificate(s).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData` — array, optional, maxItems 4, minItems 1
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].serialNumber` — string, required, maxLength 40 — The serial number of the certificate.

### getInstalledCertificateIds enumerations

- `getInstalledCertificateIds` `GetCertificateIdUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate
- `getInstalledCertificateIds` `GetCertificateIdUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate — Indicates the type of the requested certificate(s).
- `getInstalledCertificateIds` `GetInstalledCertificateStatusEnumType`: Accepted | NotFound — Charging Station indicates if it can process the request.
- `getInstalledCertificateIds` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
