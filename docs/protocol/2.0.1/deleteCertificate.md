# deleteCertificate — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.DELETECERTIFICATE`
- Kotlin `DeleteCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateReq.kt)
- Kotlin `DeleteCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.14 DeleteCertificate — pdf-page 354
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.14.1 DeleteCertificateRequest — pdf-page 354
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.14.2 DeleteCertificateResponse — pdf-page 354
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 77

## deleteCertificate request

- schema: [`DeleteCertificateRequest.json`](../../../ocpp-2-0-json/src/main/resources/DeleteCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.req.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.req.certificateHashData` — CertificateHashDataType, required
- `deleteCertificate.req.certificateHashData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.req.certificateHashData.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.req.certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `deleteCertificate.req.certificateHashData.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `deleteCertificate.req.certificateHashData.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `deleteCertificate.req.certificateHashData.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.

## deleteCertificate response

- schema: [`DeleteCertificateResponse.json`](../../../ocpp-2-0-json/src/main/resources/DeleteCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.resp.status` — DeleteCertificateStatusEnumType (string), required, enum: Accepted | Failed | NotFound — Charging Station indicates if it can process the request.
- `deleteCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `deleteCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `deleteCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### deleteCertificate enumerations

- `deleteCertificate` `DeleteCertificateStatusEnumType`: Accepted | Failed | NotFound — Charging Station indicates if it can process the request.
- `deleteCertificate` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
