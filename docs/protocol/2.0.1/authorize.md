# authorize — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.1 Authorize — pdf-page 348
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.1.1 AuthorizeRequest — pdf-page 348
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.1.2 AuthorizeResponse — pdf-page 348
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 18

## authorize request

- schema: [`AuthorizeRequest.json`](../../../ocpp-2-0-json/src/main/resources/AuthorizeRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `authorize.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.req.customData.vendorId` — string, required, maxLength 255
- `authorize.req.idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `authorize.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.req.idToken.customData.vendorId` — string, required, maxLength 255
- `authorize.req.idToken.additionalInfo` — array, optional, minItems 1
- `authorize.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `authorize.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `authorize.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `authorize.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `authorize.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `authorize.req.certificate` — string, optional, maxLength 5500 — The X.509 certificated presented by EV and encoded in PEM format.
- `authorize.req.iso15118CertificateHashData` — array, optional, maxItems 4, minItems 1
- `authorize.req.iso15118CertificateHashData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.req.iso15118CertificateHashData[].customData.vendorId` — string, required, maxLength 255
- `authorize.req.iso15118CertificateHashData[].hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `authorize.req.iso15118CertificateHashData[].issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `authorize.req.iso15118CertificateHashData[].issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `authorize.req.iso15118CertificateHashData[].serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `authorize.req.iso15118CertificateHashData[].responderURL` — string, required, maxLength 512 — This contains the responder URL (Case insensitive).

## authorize response

- schema: [`AuthorizeResponse.json`](../../../ocpp-2-0-json/src/main/resources/AuthorizeResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `authorize.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.resp.customData.vendorId` — string, required, maxLength 255
- `authorize.resp.idTokenInfo` — IdTokenInfoType, required — ID_ Token urn:x-oca:ocpp:uid:2:233247 Contains status information about an identifier. It is advised to not stop charging for a token that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not given, the status has no end date.
- `authorize.resp.idTokenInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.resp.idTokenInfo.customData.vendorId` — string, required, maxLength 255
- `authorize.resp.idTokenInfo.status` — AuthorizationStatusEnumType (string), required, enum: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `authorize.resp.idTokenInfo.cacheExpiryDateTime` — string, optional, format date-time — ID_ Token. Expiry. Date_ Time urn:x-oca:ocpp:uid:1:569373 Date and Time after which the token must be considered invalid.
- `authorize.resp.idTokenInfo.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules this one.
- `authorize.resp.idTokenInfo.language1` — string, optional, maxLength 8 — ID_ Token. Language1. Language_ Code urn:x-oca:ocpp:uid:1:569374 Preferred user interface language of identifier user. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `authorize.resp.idTokenInfo.evseId` — array, optional, minItems 1 — Only used when the IdToken is only valid for one or more specific EVSEs, not for the entire Charging Station.
- `authorize.resp.idTokenInfo.evseId[]` — integer
- `authorize.resp.idTokenInfo.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `authorize.resp.idTokenInfo.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.resp.idTokenInfo.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `authorize.resp.idTokenInfo.groupIdToken.additionalInfo` — array, optional, minItems 1
- `authorize.resp.idTokenInfo.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.resp.idTokenInfo.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `authorize.resp.idTokenInfo.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `authorize.resp.idTokenInfo.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `authorize.resp.idTokenInfo.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `authorize.resp.idTokenInfo.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `authorize.resp.idTokenInfo.language2` — string, optional, maxLength 8 — ID_ Token. Language2. Language_ Code urn:x-oca:ocpp:uid:1:569375 Second preferred user interface language of identifier user. Don’t use when language1 is omitted, has to be different from language1. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `authorize.resp.idTokenInfo.personalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `authorize.resp.idTokenInfo.personalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `authorize.resp.idTokenInfo.personalMessage.customData.vendorId` — string, required, maxLength 255
- `authorize.resp.idTokenInfo.personalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `authorize.resp.idTokenInfo.personalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `authorize.resp.idTokenInfo.personalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `authorize.resp.certificateStatus` — AuthorizeCertificateStatusEnumType (string), optional, enum: Accepted | SignatureError | CertificateExpired | CertificateRevoked | NoCertificateAvailable | CertChainError | ContractCancelled — Certificate status information. - if all certificates are valid: return 'Accepted'. - if one of the certificates was revoked, return 'CertificateRevoked'.

### authorize enumerations

- `authorize` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `authorize` `AuthorizeCertificateStatusEnumType`: Accepted | SignatureError | CertificateExpired | CertificateRevoked | NoCertificateAvailable | CertChainError | ContractCancelled — Certificate status information. - if all certificates are valid: return 'Accepted'. - if one of the certificates was revoked, return 'CertificateRevoked'.
- `authorize` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `authorize` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `authorize` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
