# certificateSigned — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CERTIFICATESIGNED`
- Kotlin `CertificateSignedReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedReq.kt)
- Kotlin `CertificateSignedResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.4 CertificateSigned — pdf-page 349
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.4.1 CertificateSignedRequest — pdf-page 349
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.4.2 CertificateSignedResponse — pdf-page 350

## certificateSigned request

- schema: [`CertificateSignedRequest.json`](../../../ocpp-2-0-json/src/main/resources/CertificateSignedRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.req.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.req.certificateChain` — string, required, maxLength 10000 — The signed PEM encoded X.509 certificate. This can also contain the necessary sub CA certificates. In that case, the order of the bundle should follow the certificate chain, starting from the leaf certificate. The Configuration Variable &lt;&lt;configkey-max-certificate-chain-size,MaxCertificateChainSize&gt;&gt; can be used to limit the maximum size of this field.
- `certificateSigned.req.certificateType` — CertificateSigningUseEnumType (string), optional, enum: ChargingStationCertificate | V2GCertificate — Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.

## certificateSigned response

- schema: [`CertificateSignedResponse.json`](../../../ocpp-2-0-json/src/main/resources/CertificateSignedResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.resp.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.resp.status` — CertificateSignedStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether certificate signing has been accepted, otherwise rejected.
- `certificateSigned.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `certificateSigned.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `certificateSigned.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### certificateSigned enumerations

- `certificateSigned` `CertificateSignedStatusEnumType`: Accepted | Rejected — Returns whether certificate signing has been accepted, otherwise rejected.
- `certificateSigned` `CertificateSigningUseEnumType`: ChargingStationCertificate | V2GCertificate — Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
