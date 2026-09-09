# getInstalledCertificateIds — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETINSTALLEDCERTIFICATEIDS`
- Kotlin `GetInstalledCertificateIdsReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt)
- Kotlin `GetInstalledCertificateIdsResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.7 GetInstalledCertificateIds.req — pdf-page 51
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.8 GetInstalledCertificateIds.conf — pdf-page 51

## getInstalledCertificateIds request

- schema: [`GetInstalledCertificateIdsRequest.json`](../../../ocpp-1-6-json/src/main/resources/GetInstalledCertificateIdsRequest.json) · `urn:OCPP:Cp:1.6:2020:3:GetInstalledCertificateIds.req`
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.req.certificateType` — CertificateUseEnumType (string), required, enum: CentralSystemRootCertificate | ManufacturerRootCertificate

## getInstalledCertificateIds response

- schema: [`GetInstalledCertificateIdsResponse.json`](../../../ocpp-1-6-json/src/main/resources/GetInstalledCertificateIdsResponse.json) · `urn:OCPP:Cp:1.6:2020:3:GetInstalledCertificateIds.conf`
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.resp.certificateHashData` — array, optional, minItems 1
- `getInstalledCertificateIds.resp.certificateHashData[].hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512
- `getInstalledCertificateIds.resp.certificateHashData[].issuerNameHash` — string, required, maxLength 128
- `getInstalledCertificateIds.resp.certificateHashData[].issuerKeyHash` — string, required, maxLength 128
- `getInstalledCertificateIds.resp.certificateHashData[].serialNumber` — string, required, maxLength 40
- `getInstalledCertificateIds.resp.status` — GetInstalledCertificateStatusEnumType (string), required, enum: Accepted | NotFound

### getInstalledCertificateIds enumerations

- `getInstalledCertificateIds` `CertificateUseEnumType`: CentralSystemRootCertificate | ManufacturerRootCertificate
- `getInstalledCertificateIds` `GetInstalledCertificateStatusEnumType`: Accepted | NotFound
- `getInstalledCertificateIds` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
