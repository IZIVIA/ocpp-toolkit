# signCertificate — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNCERTIFICATE`
- Kotlin `SignCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateReq.kt)
- Kotlin `SignCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.58 SignCertificate — pdf-page 375
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.58.1 SignCertificateRequest — pdf-page 375
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.58.2 SignCertificateResponse — pdf-page 375
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 108

## signCertificate request

- schema: [`SignCertificateRequest.json`](../../../ocpp-2-0-json/src/main/resources/SignCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.req.customData.vendorId` — string, required, maxLength 255
- `signCertificate.req.csr` — string, required, maxLength 5500 — The Charging Station SHALL send the public key in form of a Certificate Signing Request (CSR) as described in RFC 2986 [22] and then PEM encoded, using the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; message.
- `signCertificate.req.certificateType` — CertificateSigningUseEnumType (string), optional, enum: ChargingStationCertificate | V2GCertificate — Indicates the type of certificate that is to be signed. When omitted the certificate is to be used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.

## signCertificate response

- schema: [`SignCertificateResponse.json`](../../../ocpp-2-0-json/src/main/resources/SignCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `signCertificate.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Specifies whether the CSMS can process the request.
- `signCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `signCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `signCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `signCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### signCertificate enumerations

- `signCertificate` `CertificateSigningUseEnumType`: ChargingStationCertificate | V2GCertificate — Indicates the type of certificate that is to be signed. When omitted the certificate is to be used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
- `signCertificate` `GenericStatusEnumType`: Accepted | Rejected — Specifies whether the CSMS can process the request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
