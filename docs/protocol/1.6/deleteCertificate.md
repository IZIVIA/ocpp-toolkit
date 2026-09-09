# deleteCertificate — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.DELETECERTIFICATE`
- Kotlin `DeleteCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateReq.kt)
- Kotlin `DeleteCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.3 DeleteCertificate.req — pdf-page 50
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.4 DeleteCertificate.conf — pdf-page 50

## deleteCertificate request

- schema: [`DeleteCertificateRequest.json`](../../../ocpp-1-6-json/src/main/resources/DeleteCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:DeleteCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.req.certificateHashData` — CertificateHashDataType, required
- `deleteCertificate.req.certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512
- `deleteCertificate.req.certificateHashData.issuerNameHash` — string, required, maxLength 128
- `deleteCertificate.req.certificateHashData.issuerKeyHash` — string, required, maxLength 128
- `deleteCertificate.req.certificateHashData.serialNumber` — string, required, maxLength 40

## deleteCertificate response

- schema: [`DeleteCertificateResponse.json`](../../../ocpp-1-6-json/src/main/resources/DeleteCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:DeleteCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.resp.status` — DeleteCertificateStatusEnumType (string), required, enum: Accepted | Failed | NotFound

### deleteCertificate enumerations

- `deleteCertificate` `DeleteCertificateStatusEnumType`: Accepted | Failed | NotFound
- `deleteCertificate` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
