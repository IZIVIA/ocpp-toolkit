# sendLocalList — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.50 SendLocalList — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.50.1 SendLocalListRequest — pdf-page 370
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.50.2 SendLocalListResponse — pdf-page 371
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 71
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 114

## sendLocalList request

- schema: [`SendLocalListRequest.json`](../../../ocpp-2-0-json/src/main/resources/SendLocalListRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList` — array, optional, minItems 1
- `sendLocalList.req.localAuthorizationList[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `sendLocalList.req.localAuthorizationList[].idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idToken.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idToken.additionalInfo` — array, optional, minItems 1
- `sendLocalList.req.localAuthorizationList[].idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `sendLocalList.req.localAuthorizationList[].idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `sendLocalList.req.localAuthorizationList[].idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `sendLocalList.req.localAuthorizationList[].idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo` — IdTokenInfoType, optional — ID_ Token urn:x-oca:ocpp:uid:2:233247 Contains status information about an identifier. It is advised to not stop charging for a token that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not given, the status has no end date.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.status` — AuthorizationStatusEnumType (string), required, enum: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.cacheExpiryDateTime` — string, optional, format date-time — ID_ Token. Expiry. Date_ Time urn:x-oca:ocpp:uid:1:569373 Date and Time after which the token must be considered invalid.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules this one.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.language1` — string, optional, maxLength 8 — ID_ Token. Language1. Language_ Code urn:x-oca:ocpp:uid:1:569374 Preferred user interface language of identifier user. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.evseId` — array, optional, minItems 1 — Only used when the IdToken is only valid for one or more specific EVSEs, not for the entire Charging Station.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.evseId[]` — integer
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.additionalInfo` — array, optional, minItems 1
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.language2` — string, optional, maxLength 8 — ID_ Token. Language2. Language_ Code urn:x-oca:ocpp:uid:1:569375 Second preferred user interface language of identifier user. Don’t use when language1 is omitted, has to be different from language1. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `sendLocalList.req.localAuthorizationList[].idTokenInfo.personalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `sendLocalList.req.versionNumber` — integer, required — In case of a full update this is the version number of the full list. In case of a differential update it is the version number of the list after the update has been applied.
- `sendLocalList.req.updateType` — UpdateEnumType (string), required, enum: Differential | Full — This contains the type of update (full or differential) of this request.

## sendLocalList response

- schema: [`SendLocalListResponse.json`](../../../ocpp-2-0-json/src/main/resources/SendLocalListResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.resp.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.resp.status` — SendLocalListStatusEnumType (string), required, enum: Accepted | Failed | VersionMismatch — This indicates whether the Charging Station has successfully received and applied the update of the Local Authorization List.
- `sendLocalList.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `sendLocalList.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `sendLocalList.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### sendLocalList enumerations

- `sendLocalList` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `sendLocalList` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `sendLocalList` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `sendLocalList` `SendLocalListStatusEnumType`: Accepted | Failed | VersionMismatch — This indicates whether the Charging Station has successfully received and applied the update of the Local Authorization List.
- `sendLocalList` `UpdateEnumType`: Differential | Full — This contains the type of update (full or differential) of this request.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
