# installCertificate — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.INSTALLCERTIFICATE`
- Kotlin `InstallCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateReq.kt)
- Kotlin `InstallCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.30 InstallCertificate — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.30.1 InstallCertificateRequest — pdf-page 361
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.30.2 InstallCertificateResponse — pdf-page 361

## installCertificate request

- schema: [`InstallCertificateRequest.json`](../../../ocpp-2-0-json/src/main/resources/InstallCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.req.customData.vendorId` — string, required, maxLength 255
- `installCertificate.req.certificateType` — InstallCertificateUseEnumType (string), required, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | ManufacturerRootCertificate — Indicates the certificate type that is sent.
- `installCertificate.req.certificate` — string, required, maxLength 5500 — A PEM encoded X.509 certificate.

## installCertificate response

- schema: [`InstallCertificateResponse.json`](../../../ocpp-2-0-json/src/main/resources/InstallCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `installCertificate.resp.status` — InstallCertificateStatusEnumType (string), required, enum: Accepted | Rejected | Failed — Charging Station indicates if installation was successful.
- `installCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `installCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `installCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `installCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### installCertificate enumerations

- `installCertificate` `InstallCertificateStatusEnumType`: Accepted | Rejected | Failed — Charging Station indicates if installation was successful.
- `installCertificate` `InstallCertificateUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | ManufacturerRootCertificate — Indicates the certificate type that is sent.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
