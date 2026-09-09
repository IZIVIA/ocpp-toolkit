# getCertificateStatus — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.GETCERTIFICATESTATUS`
- Kotlin `GetCertificateStatusReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusReq.kt)
- Kotlin `GetCertificateStatusResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.18 GetCertificateStatus — pdf-page 356
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.18.1 GetCertificateStatusRequest — pdf-page 356
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.18.2 GetCertificateStatusResponse — pdf-page 356
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §3.37 GetCertificateStatusEnumType — pdf-page 412
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 103

## getCertificateStatus request

- schema: [`GetCertificateStatusRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetCertificateStatusRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCertificateStatus.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCertificateStatus.req.customData.vendorId` — string, required, maxLength 255
- `getCertificateStatus.req.ocspRequestData` — OCSPRequestDataType, required
- `getCertificateStatus.req.ocspRequestData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCertificateStatus.req.ocspRequestData.customData.vendorId` — string, required, maxLength 255
- `getCertificateStatus.req.ocspRequestData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `getCertificateStatus.req.ocspRequestData.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `getCertificateStatus.req.ocspRequestData.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `getCertificateStatus.req.ocspRequestData.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `getCertificateStatus.req.ocspRequestData.responderURL` — string, required, maxLength 512 — This contains the responder URL (Case insensitive).

## getCertificateStatus response

- schema: [`GetCertificateStatusResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetCertificateStatusResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCertificateStatus.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCertificateStatus.resp.customData.vendorId` — string, required, maxLength 255
- `getCertificateStatus.resp.status` — GetCertificateStatusEnumType (string), required, enum: Accepted | Failed — This indicates whether the charging station was able to retrieve the OCSP certificate status.
- `getCertificateStatus.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getCertificateStatus.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCertificateStatus.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getCertificateStatus.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getCertificateStatus.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getCertificateStatus.resp.ocspResult` — string, optional, maxLength 5500 — OCSPResponse class as defined in &lt;&lt;ref-ocpp_security_24, IETF RFC 6960&gt;&gt;. DER encoded (as defined in &lt;&lt;ref-ocpp_security_24, IETF RFC 6960&gt;&gt;), and then base64 encoded. MAY only be omitted when status is not Accepted.

### getCertificateStatus enumerations

- `getCertificateStatus` `GetCertificateStatusEnumType`: Accepted | Failed — This indicates whether the charging station was able to retrieve the OCSP certificate status.
- `getCertificateStatus` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
