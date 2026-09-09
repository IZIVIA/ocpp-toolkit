# installCertificate — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.INSTALLCERTIFICATE`
- Kotlin `InstallCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateReq.kt)
- Kotlin `InstallCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.11 InstallCertificate.req — pdf-page 52
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.12 InstallCertificate.conf — pdf-page 52

## installCertificate request

- schema: [`InstallCertificateRequest.json`](../../../ocpp-1-6-json/src/main/resources/InstallCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:InstallCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.req.certificateType` — CertificateUseEnumType (string), required, enum: CentralSystemRootCertificate | ManufacturerRootCertificate
- `installCertificate.req.certificate` — string, required, maxLength 5500

## installCertificate response

- schema: [`InstallCertificateResponse.json`](../../../ocpp-1-6-json/src/main/resources/InstallCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:InstallCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.resp.status` — InstallCertificateStatusEnumType (string), required, enum: Accepted | Failed | Rejected

### installCertificate enumerations

- `installCertificate` `CertificateUseEnumType`: CentralSystemRootCertificate | ManufacturerRootCertificate
- `installCertificate` `InstallCertificateStatusEnumType`: Accepted | Failed | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
