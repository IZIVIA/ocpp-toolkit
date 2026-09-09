# certificateSigned — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CERTIFICATESIGNED`
- Kotlin `CertificateSignedReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedReq.kt)
- Kotlin `CertificateSignedResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.1 CertificateSigned.req — pdf-page 49
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.2 CertificateSigned.conf — pdf-page 49

## certificateSigned request

- schema: [`CertificateSignedRequest.json`](../../../ocpp-1-6-json/src/main/resources/CertificateSignedRequest.json) · `urn:OCPP:Cp:1.6:2020:3:CertificateSigned.req`
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.req.certificateChain` — string, required, maxLength 10000

## certificateSigned response

- schema: [`CertificateSignedResponse.json`](../../../ocpp-1-6-json/src/main/resources/CertificateSignedResponse.json) · `urn:OCPP:Cp:1.6:2020:3:CertificateSigned.conf`
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.resp.status` — CertificateSignedStatusEnumType (string), required, enum: Accepted | Rejected

### certificateSigned enumerations

- `certificateSigned` `CertificateSignedStatusEnumType`: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
