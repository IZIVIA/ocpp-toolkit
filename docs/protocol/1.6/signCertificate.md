# signCertificate — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNCERTIFICATE`
- Kotlin `SignCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateReq.kt)
- Kotlin `SignCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.17 SignCertificate.req — pdf-page 53
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.18 SignCertificate.conf — pdf-page 54

## signCertificate request

- schema: [`SignCertificateRequest.json`](../../../ocpp-1-6-json/src/main/resources/SignCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.req.csr` — string, required, maxLength 5500

## signCertificate response

- schema: [`SignCertificateResponse.json`](../../../ocpp-1-6-json/src/main/resources/SignCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected

### signCertificate enumerations

- `signCertificate` `GenericStatusEnumType`: Accepted | Rejected

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
