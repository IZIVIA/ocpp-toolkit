# OCPP 2.0.1 — protocol reference

**Generated — do not edit.** Run `python3 docs/protocol/generate.py` to refresh.

Derived from the official OCPP JSON schemas in `ocpp-2-0-json/src/main/resources/` and the action registry in `ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/common/enumeration/Actions.kt`. For the normative prose, see [SPECS.md](SPECS.md).

Every field is one line, keyed by its dotted JSON path, so a grep for a field name lands on a line that names its action, direction and constraints:

```bash
grep -n 'idTag' docs/protocol/OCPP-2.0.1.md
```

64 actions registered.

## Specification documents

Each action below cites the section and PDF page of the normative document. The extracted text is grep-able too:

| document | role | pages | extracted text |
|---|---|--:|---|
| OCPP 2.0.1 Part 0 — Introduction | spec | 15 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part0-introduction.txt` |
| OCPP 2.0.1 Part 1 — Architecture & Topology | spec | 27 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part1-architecture.txt` |
| OCPP 2.0.1 Part 2 — Specification (use cases & requirements) | spec | 459 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt` |
| OCPP 2.0.1 Part 2 — Appendices | spec | 38 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-appendices.txt` |
| OCPP 2.0.1 Part 4 — OCPP-J | transport | 27 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part4-ocpp-j.txt` |
| OCPP 2.0.1 Part 2 errata | errata | 75 | `docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt` |
| Changelog OCPP 2.0 -> 2.0.1 | changelog | 21 | `docs/protocol/spec/2.0.1/changelog-2.0-to-2.0.1.txt` |
| OCPP 2.0 Part 1 errata (superseded by 2.0.1) | errata | 5 | `docs/protocol/spec/2.0.1/ocpp-2.0-part1-errata.txt` |
| OCPP 2.0 Part 2 errata (superseded by 2.0.1) | errata | 119 | `docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt` |
| OCPP 2.0 Part 4 errata (superseded by 2.0.1) | errata | 6 | `docs/protocol/spec/2.0.1/ocpp-2.0-part4-errata.txt` |

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| `authorize` | Charging Station -> CSMS | `AuthorizeRequest.json` | `AuthorizeReq` / `AuthorizeResp` |
| `bootNotification` | Charging Station -> CSMS | `BootNotificationRequest.json` | `BootNotificationReq` / `BootNotificationResp` |
| `cancelReservation` | CSMS -> Charging Station | `CancelReservationRequest.json` | `CancelReservationReq` / `CancelReservationResp` |
| `certificateSigned` | CSMS -> Charging Station | `CertificateSignedRequest.json` | `CertificateSignedReq` / `CertificateSignedResp` |
| `changeAvailability` | CSMS -> Charging Station | `ChangeAvailabilityRequest.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| `clearCache` | CSMS -> Charging Station | `ClearCacheRequest.json` | `ClearCacheReq` / `ClearCacheResp` |
| `clearChargingProfile` | CSMS -> Charging Station | `ClearChargingProfileRequest.json` | `ClearChargingProfileReq` / `ClearChargingProfileResp` |
| `clearDisplayMessage` | CSMS -> Charging Station | `ClearDisplayMessageRequest.json` | `ClearDisplayMessageReq` / `ClearDisplayMessageResp` |
| `clearVariableMonitoring` | CSMS -> Charging Station | `ClearVariableMonitoringRequest.json` | `ClearVariableMonitoringReq` / `ClearVariableMonitoringResp` |
| `clearedChargingLimit` | Charging Station -> CSMS | `ClearedChargingLimitRequest.json` | `ClearedChargingLimitReq` / `ClearedChargingLimitResp` |
| `costUpdated` | CSMS -> Charging Station | `CostUpdatedRequest.json` | `CostUpdatedReq` / `CostUpdatedResp` |
| `customerInformation` | CSMS -> Charging Station | `CustomerInformationRequest.json` | `CustomerInformationReq` / `CustomerInformationResp` |
| `dataTransfer` | either side | `DataTransferRequest.json` | `DataTransferReq` / `DataTransferResp` |
| `deleteCertificate` | CSMS -> Charging Station | `DeleteCertificateRequest.json` | `DeleteCertificateReq` / `DeleteCertificateResp` |
| `firmwareStatusNotification` | Charging Station -> CSMS | `FirmwareStatusNotificationRequest.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| `get15118EVCertificate` | Charging Station -> CSMS | **missing** | `Get15118EVCertificateReq` / `Get15118EVCertificateResp` |
| `getBaseReport` | CSMS -> Charging Station | `GetBaseReportRequest.json` | `GetBaseReportReq` / `GetBaseReportResp` |
| `getCertificateStatus` | Charging Station -> CSMS | `GetCertificateStatusRequest.json` | `GetCertificateStatusReq` / `GetCertificateStatusResp` |
| `getChargingProfiles` | CSMS -> Charging Station | `GetChargingProfilesRequest.json` | `GetChargingProfilesReq` / `GetChargingProfilesResp` |
| `getCompositeSchedule` | CSMS -> Charging Station | `GetCompositeScheduleRequest.json` | `GetCompositeScheduleReq` / `GetCompositeScheduleResp` |
| `getDisplayMessages` | CSMS -> Charging Station | `GetDisplayMessagesRequest.json` | `GetDisplayMessagesReq` / `GetDisplayMessagesResp` |
| `getInstalledCertificateIds` | CSMS -> Charging Station | `GetInstalledCertificateIdsRequest.json` | `GetInstalledCertificateIdsReq` / `GetInstalledCertificateIdsResp` |
| `getLocalListVersion` | CSMS -> Charging Station | `GetLocalListVersionRequest.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| `getLog` | CSMS -> Charging Station | `GetLogRequest.json` | `GetLogReq` / `GetLogResp` |
| `getMonitoringReport` | CSMS -> Charging Station | `GetMonitoringReportRequest.json` | `GetMonitoringReportReq` / `GetMonitoringReportResp` |
| `getReport` | CSMS -> Charging Station | `GetReportRequest.json` | `GetReportReq` / `GetReportResp` |
| `getTransactionStatus` | CSMS -> Charging Station | `GetTransactionStatusRequest.json` | `GetTransactionStatusReq` / `GetTransactionStatusResp` |
| `getVariables` | CSMS -> Charging Station | `GetVariablesRequest.json` | `GetVariablesReq` / `GetVariablesResp` |
| `heartbeat` | Charging Station -> CSMS | `HeartbeatRequest.json` | `HeartbeatReq` / `HeartbeatResp` |
| `installCertificate` | CSMS -> Charging Station | `InstallCertificateRequest.json` | `InstallCertificateReq` / `InstallCertificateResp` |
| `logStatusNotification` | Charging Station -> CSMS | `LogStatusNotificationRequest.json` | `LogStatusNotificationReq` / `LogStatusNotificationResp` |
| `meterValues` | Charging Station -> CSMS | `MeterValuesRequest.json` | `MeterValuesReq` / `MeterValuesResp` |
| `notifyChargingLimit` | Charging Station -> CSMS | `NotifyChargingLimitRequest.json` | `NotifyChargingLimitReq` / `NotifyChargingLimitResp` |
| `notifyCustomerInformation` | Charging Station -> CSMS | `NotifyCustomerInformationRequest.json` | `NotifyCustomerInformationReq` / `NotifyCustomerInformationResp` |
| `notifyDisplayMessages` | Charging Station -> CSMS | `NotifyDisplayMessagesRequest.json` | `NotifyDisplayMessagesReq` / `NotifyDisplayMessagesResp` |
| `notifyEVChargingNeeds` | Charging Station -> CSMS | `NotifyEVChargingNeedsRequest.json` | `NotifyEVChargingNeedsReq` / `NotifyEVChargingNeedsResp` |
| `notifyEVChargingSchedule` | Charging Station -> CSMS | `NotifyEVChargingScheduleRequest.json` | `NotifyEVChargingScheduleReq` / `NotifyEVChargingScheduleResp` |
| `notifyEvent` | Charging Station -> CSMS | `NotifyEventRequest.json` | `NotifyEventReq` / `NotifyEventResp` |
| `notifyMonitoringReport` | Charging Station -> CSMS | `NotifyMonitoringReportRequest.json` | `NotifyMonitoringReportReq` / `NotifyMonitoringReportResp` |
| `notifyReport` | Charging Station -> CSMS | `NotifyReportRequest.json` | `NotifyReportReq` / `NotifyReportResp` |
| `publishFirmware` | CSMS -> Charging Station | `PublishFirmwareRequest.json` | `PublishFirmwareReq` / `PublishFirmwareResp` |
| `publishFirmwareStatusNotification` | Charging Station -> CSMS | `PublishFirmwareStatusNotificationRequest.json` | `PublishFirmwareStatusNotificationReq` / `PublishFirmwareStatusNotificationResp` |
| `reportChargingProfiles` | CSMS -> Charging Station | `ReportChargingProfilesRequest.json` | `ReportChargingProfilesReq` / `ReportChargingProfilesResp` |
| `requestStartTransaction` | CSMS -> Charging Station | `RequestStartTransactionRequest.json` | `RequestStartTransactionReq` / `RequestStartTransactionResp` |
| `requestStopTransaction` | CSMS -> Charging Station | `RequestStopTransactionRequest.json` | `RequestStopTransactionReq` / `RequestStopTransactionResp` |
| `reservationStatusUpdate` | Charging Station -> CSMS | `ReservationStatusUpdateRequest.json` | `ReservationStatusUpdateReq` / `ReservationStatusUpdateResp` |
| `reserveNow` | CSMS -> Charging Station | `ReserveNowRequest.json` | `ReserveNowReq` / `ReserveNowResp` |
| `reset` | CSMS -> Charging Station | `ResetRequest.json` | `ResetReq` / `ResetResp` |
| `securityEventNotification` | Charging Station -> CSMS | `SecurityEventNotificationRequest.json` | `SecurityEventNotificationReq` / `SecurityEventNotificationResp` |
| `sendLocalList` | CSMS -> Charging Station | `SendLocalListRequest.json` | `SendLocalListReq` / `SendLocalListResp` |
| `setChargingProfile` | CSMS -> Charging Station | `SetChargingProfileRequest.json` | `SetChargingProfileReq` / `SetChargingProfileResp` |
| `setDisplayMessage` | CSMS -> Charging Station | `SetDisplayMessageRequest.json` | `SetDisplayMessageReq` / `SetDisplayMessageResp` |
| `setMonitoringBase` | CSMS -> Charging Station | `SetMonitoringBaseRequest.json` | `SetMonitoringBaseReq` / `SetMonitoringBaseResp` |
| `setMonitoringLevel` | CSMS -> Charging Station | `SetMonitoringLevelRequest.json` | `SetMonitoringLevelReq` / `SetMonitoringLevelResp` |
| `setNetworkProfile` | CSMS -> Charging Station | `SetNetworkProfileRequest.json` | `SetNetworkProfileReq` / `SetNetworkProfileResp` |
| `setVariableMonitoring` | CSMS -> Charging Station | `SetVariableMonitoringRequest.json` | `SetVariableMonitoringReq` / `SetVariableMonitoringResp` |
| `setVariables` | CSMS -> Charging Station | `SetVariablesRequest.json` | `SetVariablesReq` / `SetVariablesResp` |
| `signCertificate` | Charging Station -> CSMS | `SignCertificateRequest.json` | `SignCertificateReq` / `SignCertificateResp` |
| `statusNotification` | CSMS -> Charging Station | `StatusNotificationRequest.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| `transactionEvent` | Charging Station -> CSMS | `TransactionEventRequest.json` | `TransactionEventReq` / `TransactionEventResp` |
| `triggerMessage` | CSMS -> Charging Station | `TriggerMessageRequest.json` | `TriggerMessageReq` / `TriggerMessageResp` |
| `unlockConnector` | CSMS -> Charging Station | `UnlockConnectorRequest.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| `unpublishFirmware` | CSMS -> Charging Station | `UnpublishFirmwareRequest.json` | `UnpublishFirmwareReq` / `UnpublishFirmwareResp` |
| `updateFirmware` | CSMS -> Charging Station | `UpdateFirmwareRequest.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

## authorize

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/authorize/AuthorizeResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.1 Authorize — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.1.1 AuthorizeRequest — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.1.2 AuthorizeResponse — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 18 (`grep -n 'Authorize' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### authorize request

- schema: [`AuthorizeRequest.json`](../../ocpp-2-0-json/src/main/resources/AuthorizeRequest.json) · OCPP 2.0.1 FINAL
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

### authorize response

- schema: [`AuthorizeResponse.json`](../../ocpp-2-0-json/src/main/resources/AuthorizeResponse.json) · OCPP 2.0.1 FINAL
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

#### authorize enumerations

- `authorize` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `authorize` `AuthorizeCertificateStatusEnumType`: Accepted | SignatureError | CertificateExpired | CertificateRevoked | NoCertificateAvailable | CertChainError | ContractCancelled — Certificate status information. - if all certificates are valid: return 'Accepted'. - if one of the certificates was revoked, return 'CertificateRevoked'.
- `authorize` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `authorize` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `authorize` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.

## bootNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.2 BootNotification — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.2.1 BootNotificationRequest — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.2.2 BootNotificationResponse — pdf-page 348 (`grep -n 'pdf-page 348]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 10, 11, 69 (`grep -n 'BootNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 14, 22, 48, 99, 109 (`grep -n 'BootNotification' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### bootNotification request

- schema: [`BootNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/BootNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation` — ChargingStationType, required — Charge_ Point urn:x-oca:ocpp:uid:2:233122 The physical system where an Electrical Vehicle (EV) can be charged.
- `bootNotification.req.chargingStation.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.chargingStation.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation.serialNumber` — string, optional, maxLength 25 — Device. Serial_ Number. Serial_ Number urn:x-oca:ocpp:uid:1:569324 Vendor-specific device identifier.
- `bootNotification.req.chargingStation.model` — string, required, maxLength 20 — Device. Model. CI20_ Text urn:x-oca:ocpp:uid:1:569325 Defines the model of the device.
- `bootNotification.req.chargingStation.modem` — ModemType, optional — Wireless_ Communication_ Module urn:x-oca:ocpp:uid:2:233306 Defines parameters required for initiating and maintaining wireless communication with other devices.
- `bootNotification.req.chargingStation.modem.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.chargingStation.modem.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation.modem.iccid` — string, optional, maxLength 20 — Wireless_ Communication_ Module. ICCID. CI20_ Text urn:x-oca:ocpp:uid:1:569327 This contains the ICCID of the modem’s SIM card.
- `bootNotification.req.chargingStation.modem.imsi` — string, optional, maxLength 20 — Wireless_ Communication_ Module. IMSI. CI20_ Text urn:x-oca:ocpp:uid:1:569328 This contains the IMSI of the modem’s SIM card.
- `bootNotification.req.chargingStation.vendorName` — string, required, maxLength 50 — Identifies the vendor (not necessarily in a unique manner).
- `bootNotification.req.chargingStation.firmwareVersion` — string, optional, maxLength 50 — This contains the firmware version of the Charging Station.
- `bootNotification.req.reason` — BootReasonEnumType (string), required, enum: ApplicationReset | FirmwareUpdate | LocalReset | PowerUp | RemoteReset | ScheduledReset | Triggered | Unknown | Watchdog — This contains the reason for sending this message to the CSMS.

### bootNotification response

- schema: [`BootNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/BootNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.resp.customData.vendorId` — string, required, maxLength 255
- `bootNotification.resp.currentTime` — string, required, format date-time — This contains the CSMS’s current time.
- `bootNotification.resp.interval` — integer, required — When &lt;&lt;cmn_registrationstatusenumtype,Status&gt;&gt; is Accepted, this contains the heartbeat interval in seconds. If the CSMS returns something other than Accepted, the value of the interval field indicates the minimum wait time before sending a next BootNotification request.
- `bootNotification.resp.status` — RegistrationStatusEnumType (string), required, enum: Accepted | Pending | Rejected — This contains whether the Charging Station has been registered within the CSMS.
- `bootNotification.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `bootNotification.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `bootNotification.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `bootNotification.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### bootNotification enumerations

- `bootNotification` `BootReasonEnumType`: ApplicationReset | FirmwareUpdate | LocalReset | PowerUp | RemoteReset | ScheduledReset | Triggered | Unknown | Watchdog — This contains the reason for sending this message to the CSMS.
- `bootNotification` `RegistrationStatusEnumType`: Accepted | Pending | Rejected — This contains whether the Charging Station has been registered within the CSMS.

## cancelReservation

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cancelreservation/CancelReservationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §H02 Cancel Reservation — pdf-page 209 (`grep -n 'pdf-page 209]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.3 CancelReservation — pdf-page 349 (`grep -n 'pdf-page 349]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.3.1 CancelReservationRequest — pdf-page 349 (`grep -n 'pdf-page 349]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.3.2 CancelReservationResponse — pdf-page 349 (`grep -n 'pdf-page 349]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### cancelReservation request

- schema: [`CancelReservationRequest.json`](../../ocpp-2-0-json/src/main/resources/CancelReservationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.req.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.req.reservationId` — integer, required — Id of the reservation to cancel.

### cancelReservation response

- schema: [`CancelReservationResponse.json`](../../ocpp-2-0-json/src/main/resources/CancelReservationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.resp.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.resp.status` — CancelReservationStatusEnumType (string), required, enum: Accepted | Rejected — This indicates the success or failure of the canceling of a reservation by CSMS.
- `cancelReservation.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `cancelReservation.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `cancelReservation.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `cancelReservation.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `cancelReservation.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### cancelReservation enumerations

- `cancelReservation` `CancelReservationStatusEnumType`: Accepted | Rejected — This indicates the success or failure of the canceling of a reservation by CSMS.

## certificateSigned

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CERTIFICATESIGNED`
- Kotlin `CertificateSignedReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedReq.kt)
- Kotlin `CertificateSignedResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/certificateSigned/CertificateSignedResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.4 CertificateSigned — pdf-page 349 (`grep -n 'pdf-page 349]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.4.1 CertificateSignedRequest — pdf-page 349 (`grep -n 'pdf-page 349]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.4.2 CertificateSignedResponse — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### certificateSigned request

- schema: [`CertificateSignedRequest.json`](../../ocpp-2-0-json/src/main/resources/CertificateSignedRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.req.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.req.certificateChain` — string, required, maxLength 10000 — The signed PEM encoded X.509 certificate. This can also contain the necessary sub CA certificates. In that case, the order of the bundle should follow the certificate chain, starting from the leaf certificate. The Configuration Variable &lt;&lt;configkey-max-certificate-chain-size,MaxCertificateChainSize&gt;&gt; can be used to limit the maximum size of this field.
- `certificateSigned.req.certificateType` — CertificateSigningUseEnumType (string), optional, enum: ChargingStationCertificate | V2GCertificate — Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.

### certificateSigned response

- schema: [`CertificateSignedResponse.json`](../../ocpp-2-0-json/src/main/resources/CertificateSignedResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.resp.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.resp.status` — CertificateSignedStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether certificate signing has been accepted, otherwise rejected.
- `certificateSigned.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `certificateSigned.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `certificateSigned.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `certificateSigned.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `certificateSigned.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### certificateSigned enumerations

- `certificateSigned` `CertificateSignedStatusEnumType`: Accepted | Rejected — Returns whether certificate signing has been accepted, otherwise rejected.
- `certificateSigned` `CertificateSigningUseEnumType`: ChargingStationCertificate | V2GCertificate — Indicates the type of the signed certificate that is returned. When omitted the certificate is used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection. This field is required when a typeOfCertificate was included in the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; that requested this certificate to be signed AND both the 15118 connection and the Charging Station connection are implemented.

## changeAvailability

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.5 ChangeAvailability — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.5.1 ChangeAvailabilityRequest — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.5.2 ChangeAvailabilityResponse — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 49 (`grep -n 'ChangeAvailability' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### changeAvailability request

- schema: [`ChangeAvailabilityRequest.json`](../../ocpp-2-0-json/src/main/resources/ChangeAvailabilityRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.req.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `changeAvailability.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.req.evse.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `changeAvailability.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `changeAvailability.req.operationalStatus` — OperationalStatusEnumType (string), required, enum: Inoperative | Operative — This contains the type of availability change that the Charging Station should perform.

### changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../ocpp-2-0-json/src/main/resources/ChangeAvailabilityResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.resp.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.resp.status` — ChangeAvailabilityStatusEnumType (string), required, enum: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the availability change.
- `changeAvailability.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `changeAvailability.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `changeAvailability.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `changeAvailability.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `changeAvailability.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### changeAvailability enumerations

- `changeAvailability` `ChangeAvailabilityStatusEnumType`: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the availability change.
- `changeAvailability` `OperationalStatusEnumType`: Inoperative | Operative — This contains the type of availability change that the Charging Station should perform.

## clearCache

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearcache/ClearCacheResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.6 ClearCache — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.6.1 ClearCacheRequest — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.6.2 ClearCacheResponse — pdf-page 350 (`grep -n 'pdf-page 350]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### clearCache request

- schema: [`ClearCacheRequest.json`](../../ocpp-2-0-json/src/main/resources/ClearCacheRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.req.customData.vendorId` — string, required, maxLength 255

### clearCache response

- schema: [`ClearCacheResponse.json`](../../ocpp-2-0-json/src/main/resources/ClearCacheResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.resp.customData.vendorId` — string, required, maxLength 255
- `clearCache.resp.status` — ClearCacheStatusEnumType (string), required, enum: Accepted | Rejected — Accepted if the Charging Station has executed the request, otherwise rejected.
- `clearCache.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearCache.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearCache.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearCache.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearCache.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### clearCache enumerations

- `clearCache` `ClearCacheStatusEnumType`: Accepted | Rejected — Accepted if the Charging Station has executed the request, otherwise rejected.

## clearChargingProfile

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCHARGINGPROFILE`
- Kotlin `ClearChargingProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileReq.kt)
- Kotlin `ClearChargingProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearchargingprofile/ClearChargingProfileResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §K10 Clear Charging Profile — pdf-page 261 (`grep -n 'pdf-page 261]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.7 ClearChargingProfile — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.7.1 ClearChargingProfileRequest — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.7.2 ClearChargingProfileResponse — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §2.14 ClearChargingProfileType — pdf-page 384 (`grep -n 'pdf-page 384]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 38 (`grep -n 'ClearChargingProfile' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 91 (`grep -n 'ClearChargingProfile' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### clearChargingProfile request

- schema: [`ClearChargingProfileRequest.json`](../../ocpp-2-0-json/src/main/resources/ClearChargingProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.req.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.req.chargingProfileId` — integer, optional — The Id of the charging profile to clear.
- `clearChargingProfile.req.chargingProfileCriteria` — ClearChargingProfileType, optional — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of a ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `clearChargingProfile.req.chargingProfileCriteria.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.req.chargingProfileCriteria.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.req.chargingProfileCriteria.evseId` — integer, optional — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Specifies the id of the EVSE for which to clear charging profiles. An evseId of zero (0) specifies the charging profile for the overall Charging Station. Absence of this parameter means the clearing applies to all charging profiles that match the other criteria in the request.
- `clearChargingProfile.req.chargingProfileCriteria.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), optional, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Specifies to purpose of the charging profiles that will be cleared, if they meet the other criteria in the request.
- `clearChargingProfile.req.chargingProfileCriteria.stackLevel` — integer, optional — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Specifies the stackLevel for which charging profiles will be cleared, if they meet the other criteria in the request.

### clearChargingProfile response

- schema: [`ClearChargingProfileResponse.json`](../../ocpp-2-0-json/src/main/resources/ClearChargingProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.resp.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.resp.status` — ClearChargingProfileStatusEnumType (string), required, enum: Accepted | Unknown — Indicates if the Charging Station was able to execute the request.
- `clearChargingProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearChargingProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearChargingProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearChargingProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearChargingProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### clearChargingProfile enumerations

- `clearChargingProfile` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Specifies to purpose of the charging profiles that will be cleared, if they meet the other criteria in the request.
- `clearChargingProfile` `ClearChargingProfileStatusEnumType`: Accepted | Unknown — Indicates if the Charging Station was able to execute the request.

## clearDisplayMessage

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARDISPLAYMESSAGE`
- Kotlin `ClearDisplayMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageReq.kt)
- Kotlin `ClearDisplayMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/cleardisplaymessage/ClearDisplayMessageResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.8 ClearDisplayMessage — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.8.1 ClearDisplayMessageRequest — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.8.2 ClearDisplayMessageResponse — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### clearDisplayMessage request

- schema: [`ClearDisplayMessageRequest.json`](../../ocpp-2-0-json/src/main/resources/ClearDisplayMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearDisplayMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.req.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.req.id` — integer, required — Id of the message that SHALL be removed from the Charging Station.

### clearDisplayMessage response

- schema: [`ClearDisplayMessageResponse.json`](../../ocpp-2-0-json/src/main/resources/ClearDisplayMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearDisplayMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.resp.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.resp.status` — ClearMessageStatusEnumType (string), required, enum: Accepted | Unknown — Returns whether the Charging Station has been able to remove the message.
- `clearDisplayMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearDisplayMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearDisplayMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearDisplayMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearDisplayMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### clearDisplayMessage enumerations

- `clearDisplayMessage` `ClearMessageStatusEnumType`: Accepted | Unknown — Returns whether the Charging Station has been able to remove the message.

## clearVariableMonitoring

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARVARIABLEMONITORING`
- Kotlin `ClearVariableMonitoringReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringReq.kt)
- Kotlin `ClearVariableMonitoringResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearvariablemonitoring/ClearVariableMonitoringResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.10 ClearVariableMonitoring — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.10.1 ClearVariableMonitoringRequest — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.10.2 ClearVariableMonitoringResponse — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 72 (`grep -n 'ClearVariableMonitoring' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### clearVariableMonitoring request

- schema: [`ClearVariableMonitoringRequest.json`](../../ocpp-2-0-json/src/main/resources/ClearVariableMonitoringRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearVariableMonitoring.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.req.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.req.ids` — array, required, minItems 1 — List of the monitors to be cleared, identified by there Id.
- `clearVariableMonitoring.req.ids[]` — integer

### clearVariableMonitoring response

- schema: [`ClearVariableMonitoringResponse.json`](../../ocpp-2-0-json/src/main/resources/ClearVariableMonitoringResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearVariableMonitoring.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults` — array, required, minItems 1
- `clearVariableMonitoring.resp.clearMonitoringResults[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.clearMonitoringResults[].customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults[].status` — ClearMonitoringStatusEnumType (string), required, enum: Accepted | Rejected | NotFound — Result of the clear request for this monitor, identified by its Id.
- `clearVariableMonitoring.resp.clearMonitoringResults[].id` — integer, required — Id of the monitor of which a clear was requested.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.customData.vendorId` — string, required, maxLength 255
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `clearVariableMonitoring.resp.clearMonitoringResults[].statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### clearVariableMonitoring enumerations

- `clearVariableMonitoring` `ClearMonitoringStatusEnumType`: Accepted | Rejected | NotFound — Result of the clear request for this monitor, identified by its Id.

## clearedChargingLimit

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.CLEAREDCHARGINGLIMIT`
- Kotlin `ClearedChargingLimitReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitReq.kt)
- Kotlin `ClearedChargingLimitResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/clearedcharginglimit/ClearedChargingLimitResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.9 ClearedChargingLimit — pdf-page 351 (`grep -n 'pdf-page 351]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.9.1 ClearedChargingLimitRequest — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.9.2 ClearedChargingLimitResponse — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### clearedChargingLimit request

- schema: [`ClearedChargingLimitRequest.json`](../../ocpp-2-0-json/src/main/resources/ClearedChargingLimitRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearedChargingLimit.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearedChargingLimit.req.customData.vendorId` — string, required, maxLength 255
- `clearedChargingLimit.req.chargingLimitSource` — ChargingLimitSourceEnumType (string), required, enum: EMS | Other | SO | CSO — Source of the charging limit.
- `clearedChargingLimit.req.evseId` — integer, optional — EVSE Identifier.

### clearedChargingLimit response

- schema: [`ClearedChargingLimitResponse.json`](../../ocpp-2-0-json/src/main/resources/ClearedChargingLimitResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `clearedChargingLimit.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `clearedChargingLimit.resp.customData.vendorId` — string, required, maxLength 255

#### clearedChargingLimit enumerations

- `clearedChargingLimit` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO — Source of the charging limit.

## costUpdated

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.COSTUPDATED`
- Kotlin `CostUpdatedReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedReq.kt)
- Kotlin `CostUpdatedResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.11 CostUpdated — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.11.1 CostUpdatedRequest — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.11.2 CostUpdatedResponse — pdf-page 352 (`grep -n 'pdf-page 352]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### costUpdated request

- schema: [`CostUpdatedRequest.json`](../../ocpp-2-0-json/src/main/resources/CostUpdatedRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `costUpdated.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `costUpdated.req.customData.vendorId` — string, required, maxLength 255
- `costUpdated.req.totalCost` — number, required — Current total cost, based on the information known by the CSMS, of the transaction including taxes. In the currency configured with the configuration Variable: [&lt;&lt;configkey-currency, Currency&gt;&gt;]
- `costUpdated.req.transactionId` — string, required, maxLength 36 — Transaction Id of the transaction the current cost are asked for.

### costUpdated response

- schema: [`CostUpdatedResponse.json`](../../ocpp-2-0-json/src/main/resources/CostUpdatedResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `costUpdated.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `costUpdated.resp.customData.vendorId` — string, required, maxLength 255

## customerInformation

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CUSTOMERINFORMATION`
- Kotlin `CustomerInformationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationReq.kt)
- Kotlin `CustomerInformationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/customerinformation/CustomerInformationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §2.4 Customer Information — pdf-page 325 (`grep -n 'pdf-page 325]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.12 CustomerInformation — pdf-page 353 (`grep -n 'pdf-page 353]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.12.1 CustomerInformationRequest — pdf-page 353 (`grep -n 'pdf-page 353]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.12.2 CustomerInformationResponse — pdf-page 353 (`grep -n 'pdf-page 353]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'CustomerInformation' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### customerInformation request

- schema: [`CustomerInformationRequest.json`](../../ocpp-2-0-json/src/main/resources/CustomerInformationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `customerInformation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.customerCertificate` — CertificateHashDataType, optional
- `customerInformation.req.customerCertificate.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.customerCertificate.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.customerCertificate.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `customerInformation.req.customerCertificate.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `customerInformation.req.customerCertificate.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `customerInformation.req.customerCertificate.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `customerInformation.req.idToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `customerInformation.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.idToken.customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.idToken.additionalInfo` — array, optional, minItems 1
- `customerInformation.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `customerInformation.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `customerInformation.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `customerInformation.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `customerInformation.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `customerInformation.req.requestId` — integer, required — The Id of the request.
- `customerInformation.req.report` — boolean, required — Flag indicating whether the Charging Station should return NotifyCustomerInformationRequest messages containing information about the customer referred to.
- `customerInformation.req.clear` — boolean, required — Flag indicating whether the Charging Station should clear all information about the customer referred to.
- `customerInformation.req.customerIdentifier` — string, optional, maxLength 64 — A (e.g. vendor specific) identifier of the customer this request refers to. This field contains a custom identifier other than IdToken and Certificate. One of the possible identifiers (customerIdentifier, customerIdToken or customerCertificate) should be in the request message.

### customerInformation response

- schema: [`CustomerInformationResponse.json`](../../ocpp-2-0-json/src/main/resources/CustomerInformationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `customerInformation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.resp.customData.vendorId` — string, required, maxLength 255
- `customerInformation.resp.status` — CustomerInformationStatusEnumType (string), required, enum: Accepted | Rejected | Invalid — Indicates whether the request was accepted.
- `customerInformation.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `customerInformation.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `customerInformation.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `customerInformation.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `customerInformation.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### customerInformation enumerations

- `customerInformation` `CustomerInformationStatusEnumType`: Accepted | Rejected | Invalid — Indicates whether the request was accepted.
- `customerInformation` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `customerInformation` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

## dataTransfer

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/datatransfer/DataTransferResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.13 DataTransfer — pdf-page 353 (`grep -n 'pdf-page 353]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.13.1 DataTransferRequest — pdf-page 353 (`grep -n 'pdf-page 353]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.13.2 DataTransferResponse — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- changelog mentions: `changelog-2.0-to-2.0.1` pdf-page 2, 17 (`grep -n 'DataTransfer' docs/protocol/spec/2.0.1/changelog-2.0-to-2.0.1.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 88, 117, 118 (`grep -n 'DataTransfer' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part4-errata` pdf-page 6 (`grep -n 'DataTransfer' docs/protocol/spec/2.0.1/ocpp-2.0-part4-errata.txt`)

### dataTransfer request

- schema: [`DataTransferRequest.json`](../../ocpp-2-0-json/src/main/resources/DataTransferRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.req.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50 — May be used to indicate a specific message or implementation.
- `dataTransfer.req.data` — object, optional — Data without specified length or format. This needs to be decided by both parties (Open to implementation).
- `dataTransfer.req.vendorId` — string, required, maxLength 255 — This identifies the Vendor specific implementation

### dataTransfer response

- schema: [`DataTransferResponse.json`](../../ocpp-2-0-json/src/main/resources/DataTransferResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.resp.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.resp.status` — DataTransferStatusEnumType (string), required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId — This indicates the success or failure of the data transfer.
- `dataTransfer.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `dataTransfer.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `dataTransfer.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `dataTransfer.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `dataTransfer.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `dataTransfer.resp.data` — object, optional — Data without specified length or format, in response to request.

#### dataTransfer enumerations

- `dataTransfer` `DataTransferStatusEnumType`: Accepted | Rejected | UnknownMessageId | UnknownVendorId — This indicates the success or failure of the data transfer.

## deleteCertificate

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.DELETECERTIFICATE`
- Kotlin `DeleteCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateReq.kt)
- Kotlin `DeleteCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/deletecertificate/DeleteCertificateResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.14 DeleteCertificate — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.14.1 DeleteCertificateRequest — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.14.2 DeleteCertificateResponse — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 77 (`grep -n 'DeleteCertificate' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### deleteCertificate request

- schema: [`DeleteCertificateRequest.json`](../../ocpp-2-0-json/src/main/resources/DeleteCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.req.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.req.certificateHashData` — CertificateHashDataType, required
- `deleteCertificate.req.certificateHashData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.req.certificateHashData.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.req.certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `deleteCertificate.req.certificateHashData.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `deleteCertificate.req.certificateHashData.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `deleteCertificate.req.certificateHashData.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.

### deleteCertificate response

- schema: [`DeleteCertificateResponse.json`](../../ocpp-2-0-json/src/main/resources/DeleteCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.resp.status` — DeleteCertificateStatusEnumType (string), required, enum: Accepted | Failed | NotFound — Charging Station indicates if it can process the request.
- `deleteCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `deleteCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `deleteCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `deleteCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `deleteCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### deleteCertificate enumerations

- `deleteCertificate` `DeleteCertificateStatusEnumType`: Accepted | Failed | NotFound — Charging Station indicates if it can process the request.
- `deleteCertificate` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

## firmwareStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.15 FirmwareStatusNotification — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.15.1 FirmwareStatusNotificationRequest — pdf-page 354 (`grep -n 'pdf-page 354]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.15.2 FirmwareStatusNotificationResponse — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'FirmwareStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 72, 92, 93 (`grep -n 'FirmwareStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### firmwareStatusNotification request

- schema: [`FirmwareStatusNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/FirmwareStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `firmwareStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `firmwareStatusNotification.req.status` — FirmwareStatusEnumType (string), required, enum: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified — This contains the progress status of the firmware installation.
- `firmwareStatusNotification.req.requestId` — integer, optional — The request id that was provided in the UpdateFirmwareRequest that started this firmware update. This field is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no firmware update ongoing.

### firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/FirmwareStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `firmwareStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

#### firmwareStatusNotification enumerations

- `firmwareStatusNotification` `FirmwareStatusEnumType`: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified — This contains the progress status of the firmware installation.

## get15118EVCertificate

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.GET15118EVCERTIFICATE`
- Kotlin `Get15118EVCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/get15118evcertificate/Get15118EVCertificateReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/get15118evcertificate/Get15118EVCertificateReq.kt)
- Kotlin `Get15118EVCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/get15118evcertificate/Get15118EVCertificateResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/get15118evcertificate/Get15118EVCertificateResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.16 Get15118EVCertificate — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.16.1 Get15118EVCertificateRequest — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.16.2 Get15118EVCertificateResponse — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 75, 76 (`grep -n 'Get15118EVCertificate' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### get15118EVCertificate request

No schema file `Get15118EVCertificateRequest.json` — payloads of this direction are **not schema-validated**.

### get15118EVCertificate response

No schema file `Get15118EVCertificateResponse.json` — payloads of this direction are **not schema-validated**.

## getBaseReport

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETBASEREPORT`
- Kotlin `GetBaseReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportReq.kt)
- Kotlin `GetBaseReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getbasereport/GetBaseReportResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §B07 Get Base Report — pdf-page 66 (`grep -n 'pdf-page 66]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.17 GetBaseReport — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.17.1 GetBaseReportRequest — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.17.2 GetBaseReportResponse — pdf-page 355 (`grep -n 'pdf-page 355]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'GetBaseReport' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 118 (`grep -n 'GetBaseReport' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getBaseReport request

- schema: [`GetBaseReportRequest.json`](../../ocpp-2-0-json/src/main/resources/GetBaseReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getBaseReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.req.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.req.requestId` — integer, required — The Id of the request.
- `getBaseReport.req.reportBase` — ReportBaseEnumType (string), required, enum: ConfigurationInventory | FullInventory | SummaryInventory — This field specifies the report base.

### getBaseReport response

- schema: [`GetBaseReportResponse.json`](../../ocpp-2-0-json/src/main/resources/GetBaseReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getBaseReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.resp.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This indicates whether the Charging Station is able to accept this request.
- `getBaseReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getBaseReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getBaseReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getBaseReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getBaseReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### getBaseReport enumerations

- `getBaseReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This indicates whether the Charging Station is able to accept this request.
- `getBaseReport` `ReportBaseEnumType`: ConfigurationInventory | FullInventory | SummaryInventory — This field specifies the report base.

## getCertificateStatus

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.GETCERTIFICATESTATUS`
- Kotlin `GetCertificateStatusReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusReq.kt)
- Kotlin `GetCertificateStatusResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcertificatestatus/GetCertificateStatusResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.18 GetCertificateStatus — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.18.1 GetCertificateStatusRequest — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.18.2 GetCertificateStatusResponse — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §3.37 GetCertificateStatusEnumType — pdf-page 412 (`grep -n 'pdf-page 412]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 103 (`grep -n 'GetCertificateStatus' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getCertificateStatus request

- schema: [`GetCertificateStatusRequest.json`](../../ocpp-2-0-json/src/main/resources/GetCertificateStatusRequest.json) · OCPP 2.0.1 FINAL
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

### getCertificateStatus response

- schema: [`GetCertificateStatusResponse.json`](../../ocpp-2-0-json/src/main/resources/GetCertificateStatusResponse.json) · OCPP 2.0.1 FINAL
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

#### getCertificateStatus enumerations

- `getCertificateStatus` `GetCertificateStatusEnumType`: Accepted | Failed — This indicates whether the charging station was able to retrieve the OCSP certificate status.
- `getCertificateStatus` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

## getChargingProfiles

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCHARGINGPROFILES`
- Kotlin `GetChargingProfilesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesReq.kt)
- Kotlin `GetChargingProfilesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getchargingprofiles/GetChargingProfilesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §K09 Get Charging Profiles — pdf-page 260 (`grep -n 'pdf-page 260]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.19 GetChargingProfiles — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.19.1 GetChargingProfilesRequest — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.19.2 GetChargingProfilesResponse — pdf-page 356 (`grep -n 'pdf-page 356]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 3, 7, 36 (`grep -n 'GetChargingProfiles' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### getChargingProfiles request

- schema: [`GetChargingProfilesRequest.json`](../../ocpp-2-0-json/src/main/resources/GetChargingProfilesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getChargingProfiles.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.req.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.req.requestId` — integer, required — Reference identification that is to be used by the Charging Station in the &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; when provided.
- `getChargingProfiles.req.evseId` — integer, optional — For which EVSE installed charging profiles SHALL be reported. If 0, only charging profiles installed on the Charging Station itself (the grid connection) SHALL be reported. If omitted, all installed charging profiles SHALL be reported.
- `getChargingProfiles.req.chargingProfile` — ChargingProfileCriterionType, required — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `getChargingProfiles.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), optional, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `getChargingProfiles.req.chargingProfile.stackLevel` — integer, optional — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `getChargingProfiles.req.chargingProfile.chargingProfileId` — array, optional, minItems 1 — List of all the chargingProfileIds requested. Any ChargingProfile that matches one of these profiles will be reported. If omitted, the Charging Station SHALL not filter on chargingProfileId. This field SHALL NOT contain more ids than set in &lt;&lt;configkey-charging-profile-entries,ChargingProfileEntries.maxLimit&gt;&gt;
- `getChargingProfiles.req.chargingProfile.chargingProfileId[]` — integer
- `getChargingProfiles.req.chargingProfile.chargingLimitSource` — array, optional, maxItems 4, minItems 1 — For which charging limit sources, charging profiles SHALL be reported. If omitted, the Charging Station SHALL not filter on chargingLimitSource.
- `getChargingProfiles.req.chargingProfile.chargingLimitSource[]` — ChargingLimitSourceEnumType, enum: EMS | Other | SO | CSO

### getChargingProfiles response

- schema: [`GetChargingProfilesResponse.json`](../../ocpp-2-0-json/src/main/resources/GetChargingProfilesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getChargingProfiles.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.resp.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.resp.status` — GetChargingProfileStatusEnumType (string), required, enum: Accepted | NoProfiles — This indicates whether the Charging Station is able to process this request and will send &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; messages.
- `getChargingProfiles.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getChargingProfiles.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getChargingProfiles.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getChargingProfiles.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getChargingProfiles.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### getChargingProfiles enumerations

- `getChargingProfiles` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO
- `getChargingProfiles` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `getChargingProfiles` `GetChargingProfileStatusEnumType`: Accepted | NoProfiles — This indicates whether the Charging Station is able to process this request and will send &lt;&lt;reportchargingprofilesrequest, ReportChargingProfilesRequest&gt;&gt; messages.

## getCompositeSchedule

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCOMPOSITESCHEDULE`
- Kotlin `GetCompositeScheduleReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleReq.kt)
- Kotlin `GetCompositeScheduleResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getcompositeschedule/GetCompositeScheduleResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §K08 Get Composite Schedule — pdf-page 258 (`grep -n 'pdf-page 258]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.20 GetCompositeSchedule — pdf-page 357 (`grep -n 'pdf-page 357]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.20.1 GetCompositeScheduleRequest — pdf-page 357 (`grep -n 'pdf-page 357]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.20.2 GetCompositeScheduleResponse — pdf-page 357 (`grep -n 'pdf-page 357]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 62 (`grep -n 'GetCompositeSchedule' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getCompositeSchedule request

- schema: [`GetCompositeScheduleRequest.json`](../../ocpp-2-0-json/src/main/resources/GetCompositeScheduleRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.req.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.req.duration` — integer, required — Length of the requested schedule in seconds.
- `getCompositeSchedule.req.chargingRateUnit` — ChargingRateUnitEnumType (string), optional, enum: W | A — Can be used to force a power or current profile.
- `getCompositeSchedule.req.evseId` — integer, required — The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station will calculate the expected consumption for the grid connection.

### getCompositeSchedule response

- schema: [`GetCompositeScheduleResponse.json`](../../ocpp-2-0-json/src/main/resources/GetCompositeScheduleResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — The Charging Station will indicate if it was able to process the request
- `getCompositeSchedule.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getCompositeSchedule.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getCompositeSchedule.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getCompositeSchedule.resp.schedule` — CompositeScheduleType, optional — Composite_ Schedule urn:x-oca:ocpp:uid:2:233362
- `getCompositeSchedule.resp.schedule.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.schedule.customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod` — array, required, minItems 1
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `getCompositeSchedule.resp.schedule.chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `getCompositeSchedule.resp.schedule.evseId` — integer, required — The ID of the EVSE for which the schedule is requested. When evseid=0, the Charging Station calculated the expected consumption for the grid connection.
- `getCompositeSchedule.resp.schedule.duration` — integer, required — Duration of the schedule in seconds.
- `getCompositeSchedule.resp.schedule.scheduleStart` — string, required, format date-time — Composite_ Schedule. Start. Date_ Time urn:x-oca:ocpp:uid:1:569456 Date and time at which the schedule becomes active. All time measurements within the schedule are relative to this timestamp.
- `getCompositeSchedule.resp.schedule.chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — The unit of measure Limit is expressed in.

#### getCompositeSchedule enumerations

- `getCompositeSchedule` `ChargingRateUnitEnumType`: W | A — Can be used to force a power or current profile.
- `getCompositeSchedule` `ChargingRateUnitEnumType`: W | A — The unit of measure Limit is expressed in.
- `getCompositeSchedule` `GenericStatusEnumType`: Accepted | Rejected — The Charging Station will indicate if it was able to process the request

## getDisplayMessages

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETDISPLAYMESSAGES`
- Kotlin `GetDisplayMessagesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesReq.kt)
- Kotlin `GetDisplayMessagesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.21 GetDisplayMessages — pdf-page 357 (`grep -n 'pdf-page 357]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.21.1 GetDisplayMessagesRequest — pdf-page 357 (`grep -n 'pdf-page 357]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.21.2 GetDisplayMessagesResponse — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'GetDisplayMessages' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### getDisplayMessages request

- schema: [`GetDisplayMessagesRequest.json`](../../ocpp-2-0-json/src/main/resources/GetDisplayMessagesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getDisplayMessages.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.req.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.req.id` — array, optional, minItems 1 — If provided the Charging Station shall return Display Messages of the given ids. This field SHALL NOT contain more ids than set in &lt;&lt;configkey-number-of-display-messages,NumberOfDisplayMessages.maxLimit&gt;&gt;
- `getDisplayMessages.req.id[]` — integer
- `getDisplayMessages.req.requestId` — integer, required — The Id of this request.
- `getDisplayMessages.req.priority` — MessagePriorityEnumType (string), optional, enum: AlwaysFront | InFront | NormalCycle — If provided the Charging Station shall return Display Messages with the given priority only.
- `getDisplayMessages.req.state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — If provided the Charging Station shall return Display Messages with the given state only.

### getDisplayMessages response

- schema: [`GetDisplayMessagesResponse.json`](../../ocpp-2-0-json/src/main/resources/GetDisplayMessagesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getDisplayMessages.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.resp.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.resp.status` — GetDisplayMessagesStatusEnumType (string), required, enum: Accepted | Unknown — Indicates if the Charging Station has Display Messages that match the request criteria in the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt;
- `getDisplayMessages.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getDisplayMessages.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getDisplayMessages.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### getDisplayMessages enumerations

- `getDisplayMessages` `GetDisplayMessagesStatusEnumType`: Accepted | Unknown — Indicates if the Charging Station has Display Messages that match the request criteria in the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt;
- `getDisplayMessages` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — If provided the Charging Station shall return Display Messages with the given priority only.
- `getDisplayMessages` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — If provided the Charging Station shall return Display Messages with the given state only.

## getInstalledCertificateIds

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETINSTALLEDCERTIFICATEIDS`
- Kotlin `GetInstalledCertificateIdsReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt)
- Kotlin `GetInstalledCertificateIdsResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.22 GetInstalledCertificateIds — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.22.1 GetInstalledCertificateIdsRequest — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.22.2 GetInstalledCertificateIdsResponse — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 14, 16 (`grep -n 'GetInstalledCertificateIds' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getInstalledCertificateIds request

- schema: [`GetInstalledCertificateIdsRequest.json`](../../ocpp-2-0-json/src/main/resources/GetInstalledCertificateIdsRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.req.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.req.certificateType` — array, optional, minItems 1 — Indicates the type of certificates requested. When omitted, all certificate types are requested.
- `getInstalledCertificateIds.req.certificateType[]` — GetCertificateIdUseEnumType, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate

### getInstalledCertificateIds response

- schema: [`GetInstalledCertificateIdsResponse.json`](../../ocpp-2-0-json/src/main/resources/GetInstalledCertificateIdsResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.status` — GetInstalledCertificateStatusEnumType (string), required, enum: Accepted | NotFound — Charging Station indicates if it can process the request.
- `getInstalledCertificateIds.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getInstalledCertificateIds.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getInstalledCertificateIds.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getInstalledCertificateIds.resp.certificateHashDataChain` — array, optional, minItems 1
- `getInstalledCertificateIds.resp.certificateHashDataChain[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData` — CertificateHashDataType, required
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateHashData.serialNumber` — string, required, maxLength 40 — The serial number of the certificate.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].certificateType` — GetCertificateIdUseEnumType (string), required, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate — Indicates the type of the requested certificate(s).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData` — array, optional, maxItems 4, minItems 1
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].customData.vendorId` — string, required, maxLength 255
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].issuerNameHash` — string, required, maxLength 128 — Hashed value of the Issuer DN (Distinguished Name).
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].issuerKeyHash` — string, required, maxLength 128 — Hashed value of the issuers public key
- `getInstalledCertificateIds.resp.certificateHashDataChain[].childCertificateHashData[].serialNumber` — string, required, maxLength 40 — The serial number of the certificate.

#### getInstalledCertificateIds enumerations

- `getInstalledCertificateIds` `GetCertificateIdUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate
- `getInstalledCertificateIds` `GetCertificateIdUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | V2GCertificateChain | ManufacturerRootCertificate — Indicates the type of the requested certificate(s).
- `getInstalledCertificateIds` `GetInstalledCertificateStatusEnumType`: Accepted | NotFound — Charging Station indicates if it can process the request.
- `getInstalledCertificateIds` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512 — Used algorithms for the hashes provided.

## getLocalListVersion

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §D02 Get Local List Version — pdf-page 123 (`grep -n 'pdf-page 123]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.23 GetLocalListVersion — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.23.1 GetLocalListVersionRequest — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.23.2 GetLocalListVersionResponse — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### getLocalListVersion request

- schema: [`GetLocalListVersionRequest.json`](../../ocpp-2-0-json/src/main/resources/GetLocalListVersionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLocalListVersion.req.customData.vendorId` — string, required, maxLength 255

### getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../ocpp-2-0-json/src/main/resources/GetLocalListVersionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLocalListVersion.resp.customData.vendorId` — string, required, maxLength 255
- `getLocalListVersion.resp.versionNumber` — integer, required — This contains the current version number of the local authorization list in the Charging Station.

## getLog

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOG`
- Kotlin `GetLogReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogReq.kt)
- Kotlin `GetLogResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getlog/GetLogResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.24 GetLog — pdf-page 358 (`grep -n 'pdf-page 358]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.24.1 GetLogRequest — pdf-page 359 (`grep -n 'pdf-page 359]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.24.2 GetLogResponse — pdf-page 359 (`grep -n 'pdf-page 359]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'GetLog' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### getLog request

- schema: [`GetLogRequest.json`](../../ocpp-2-0-json/src/main/resources/GetLogRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLog.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.req.customData.vendorId` — string, required, maxLength 255
- `getLog.req.log` — LogParametersType, required — Log urn:x-enexis:ecdm:uid:2:233373 Generic class for the configuration of logging entries.
- `getLog.req.log.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.req.log.customData.vendorId` — string, required, maxLength 255
- `getLog.req.log.remoteLocation` — string, required, maxLength 512 — Log. Remote_ Location. URI urn:x-enexis:ecdm:uid:1:569484 The URL of the location at the remote system where the log should be stored.
- `getLog.req.log.oldestTimestamp` — string, optional, format date-time — Log. Oldest_ Timestamp. Date_ Time urn:x-enexis:ecdm:uid:1:569477 This contains the date and time of the oldest logging information to include in the diagnostics.
- `getLog.req.log.latestTimestamp` — string, optional, format date-time — Log. Latest_ Timestamp. Date_ Time urn:x-enexis:ecdm:uid:1:569482 This contains the date and time of the latest logging information to include in the diagnostics.
- `getLog.req.logType` — LogEnumType (string), required, enum: DiagnosticsLog | SecurityLog — This contains the type of log file that the Charging Station should send.
- `getLog.req.requestId` — integer, required — The Id of this request
- `getLog.req.retries` — integer, optional — This specifies how many times the Charging Station must try to upload the log before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `getLog.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.

### getLog response

- schema: [`GetLogResponse.json`](../../ocpp-2-0-json/src/main/resources/GetLogResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getLog.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.resp.customData.vendorId` — string, required, maxLength 255
- `getLog.resp.status` — LogStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled — This field indicates whether the Charging Station was able to accept the request.
- `getLog.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getLog.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getLog.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getLog.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getLog.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getLog.resp.filename` — string, optional, maxLength 255 — This contains the name of the log file that will be uploaded. This field is not present when no logging information is available.

#### getLog enumerations

- `getLog` `LogEnumType`: DiagnosticsLog | SecurityLog — This contains the type of log file that the Charging Station should send.
- `getLog` `LogStatusEnumType`: Accepted | Rejected | AcceptedCanceled — This field indicates whether the Charging Station was able to accept the request.

## getMonitoringReport

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETMONITORINGREPORT`
- Kotlin `GetMonitoringReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportReq.kt)
- Kotlin `GetMonitoringReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getmonitoringreport/GetMonitoringReportResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §N02 Get Monitoring report — pdf-page 314 (`grep -n 'pdf-page 314]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.25 GetMonitoringReport — pdf-page 359 (`grep -n 'pdf-page 359]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.25.1 GetMonitoringReportRequest — pdf-page 359 (`grep -n 'pdf-page 359]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.25.2 GetMonitoringReportResponse — pdf-page 359 (`grep -n 'pdf-page 359]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'GetMonitoringReport' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 99 (`grep -n 'GetMonitoringReport' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getMonitoringReport request

- schema: [`GetMonitoringReportRequest.json`](../../ocpp-2-0-json/src/main/resources/GetMonitoringReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getMonitoringReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable` — array, optional, minItems 1
- `getMonitoringReport.req.componentVariable[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component` — ComponentType, required — A physical or logical component
- `getMonitoringReport.req.componentVariable[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].component.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getMonitoringReport.req.componentVariable[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getMonitoringReport.req.componentVariable[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getMonitoringReport.req.componentVariable[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].variable` — VariableType, optional — Reference key to a component-variable.
- `getMonitoringReport.req.componentVariable[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.req.componentVariable[].variable.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.req.componentVariable[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.componentVariable[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getMonitoringReport.req.requestId` — integer, required — The Id of the request.
- `getMonitoringReport.req.monitoringCriteria` — array, optional, maxItems 3, minItems 1 — This field contains criteria for components for which a monitoring report is requested
- `getMonitoringReport.req.monitoringCriteria[]` — MonitoringCriterionEnumType, enum: ThresholdMonitoring | DeltaMonitoring | PeriodicMonitoring

### getMonitoringReport response

- schema: [`GetMonitoringReportResponse.json`](../../ocpp-2-0-json/src/main/resources/GetMonitoringReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getMonitoringReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.resp.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getMonitoringReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getMonitoringReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getMonitoringReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getMonitoringReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getMonitoringReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### getMonitoringReport enumerations

- `getMonitoringReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getMonitoringReport` `MonitoringCriterionEnumType`: ThresholdMonitoring | DeltaMonitoring | PeriodicMonitoring

## getReport

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETREPORT`
- Kotlin `GetReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportReq.kt)
- Kotlin `GetReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getreport/GetReportResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.26 GetReport — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.26.1 GetReportRequest — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.26.2 GetReportResponse — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7, 70 (`grep -n 'GetReport' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 99 (`grep -n 'GetReport' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### getReport request

- schema: [`GetReportRequest.json`](../../ocpp-2-0-json/src/main/resources/GetReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable` — array, optional, minItems 1
- `getReport.req.componentVariable[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component` — ComponentType, required — A physical or logical component
- `getReport.req.componentVariable[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].component.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getReport.req.componentVariable[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getReport.req.componentVariable[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getReport.req.componentVariable[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].variable` — VariableType, optional — Reference key to a component-variable.
- `getReport.req.componentVariable[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.req.componentVariable[].variable.customData.vendorId` — string, required, maxLength 255
- `getReport.req.componentVariable[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.componentVariable[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getReport.req.requestId` — integer, required — The Id of the request.
- `getReport.req.componentCriteria` — array, optional, maxItems 4, minItems 1 — This field contains criteria for components for which a report is requested
- `getReport.req.componentCriteria[]` — ComponentCriterionEnumType, enum: Active | Available | Enabled | Problem

### getReport response

- schema: [`GetReportResponse.json`](../../ocpp-2-0-json/src/main/resources/GetReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.resp.customData.vendorId` — string, required, maxLength 255
- `getReport.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.
- `getReport.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getReport.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getReport.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getReport.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getReport.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### getReport enumerations

- `getReport` `ComponentCriterionEnumType`: Active | Available | Enabled | Problem
- `getReport` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — This field indicates whether the Charging Station was able to accept the request.

## getTransactionStatus

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETTRANSACTIONSTATUS`
- Kotlin `GetTransactionStatusReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusReq.kt)
- Kotlin `GetTransactionStatusResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/gettransactionstatus/GetTransactionStatusResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.27 GetTransactionStatus — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.27.1 GetTransactionStatusRequest — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.27.2 GetTransactionStatusResponse — pdf-page 360 (`grep -n 'pdf-page 360]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### getTransactionStatus request

- schema: [`GetTransactionStatusRequest.json`](../../ocpp-2-0-json/src/main/resources/GetTransactionStatusRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getTransactionStatus.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getTransactionStatus.req.customData.vendorId` — string, required, maxLength 255
- `getTransactionStatus.req.transactionId` — string, optional, maxLength 36 — The Id of the transaction for which the status is requested.

### getTransactionStatus response

- schema: [`GetTransactionStatusResponse.json`](../../ocpp-2-0-json/src/main/resources/GetTransactionStatusResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getTransactionStatus.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getTransactionStatus.resp.customData.vendorId` — string, required, maxLength 255
- `getTransactionStatus.resp.ongoingIndicator` — boolean, optional — Whether the transaction is still ongoing.
- `getTransactionStatus.resp.messagesInQueue` — boolean, required — Whether there are still message to be delivered.

## getVariables

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETVARIABLES`
- Kotlin `GetVariablesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesReq.kt)
- Kotlin `GetVariablesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getvariables/GetVariablesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §B06 Get Variables — pdf-page 64 (`grep -n 'pdf-page 64]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.28 GetVariables — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.28.1 GetVariablesRequest — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.28.2 GetVariablesResponse — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 14, 70 (`grep -n 'GetVariables' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### getVariables request

- schema: [`GetVariablesRequest.json`](../../ocpp-2-0-json/src/main/resources/GetVariablesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getVariables.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData` — array, required, minItems 1
- `getVariables.req.getVariableData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables.req.getVariableData[].component` — ComponentType, required — A physical or logical component
- `getVariables.req.getVariableData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].component.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getVariables.req.getVariableData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getVariables.req.getVariableData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getVariables.req.getVariableData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].variable` — VariableType, required — Reference key to a component-variable.
- `getVariables.req.getVariableData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.req.getVariableData[].variable.customData.vendorId` — string, required, maxLength 255
- `getVariables.req.getVariableData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.req.getVariableData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### getVariables response

- schema: [`GetVariablesResponse.json`](../../ocpp-2-0-json/src/main/resources/GetVariablesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getVariables.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult` — array, required, minItems 1
- `getVariables.resp.getVariableResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].attributeStatusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].attributeStatusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getVariables.resp.getVariableResult[].attributeStatusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `getVariables.resp.getVariableResult[].attributeStatus` — GetVariableStatusEnumType (string), required, enum: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType — Result status of getting the variable.
- `getVariables.resp.getVariableResult[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables.resp.getVariableResult[].attributeValue` — string, optional, maxLength 2500 — Value of requested attribute type of component-variable. This field can only be empty when the given status is NOT accepted. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `getVariables.resp.getVariableResult[].component` — ComponentType, required — A physical or logical component
- `getVariables.resp.getVariableResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].component.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `getVariables.resp.getVariableResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `getVariables.resp.getVariableResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `getVariables.resp.getVariableResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].variable` — VariableType, required — Reference key to a component-variable.
- `getVariables.resp.getVariableResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getVariables.resp.getVariableResult[].variable.customData.vendorId` — string, required, maxLength 255
- `getVariables.resp.getVariableResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `getVariables.resp.getVariableResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

#### getVariables enumerations

- `getVariables` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Attribute type for which value is requested. When absent, default Actual is assumed.
- `getVariables` `GetVariableStatusEnumType`: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType — Result status of getting the variable.

## heartbeat

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/heartbeat/HeartbeatResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §G02 Heartbeat — pdf-page 195 (`grep -n 'pdf-page 195]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.29 Heartbeat — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.29.1 HeartbeatRequest — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.29.2 HeartbeatResponse — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §2.1.5 HeartbeatInterval — pdf-page 431 (`grep -n 'pdf-page 431]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### heartbeat request

- schema: [`HeartbeatRequest.json`](../../ocpp-2-0-json/src/main/resources/HeartbeatRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `heartbeat.req.customData.vendorId` — string, required, maxLength 255

### heartbeat response

- schema: [`HeartbeatResponse.json`](../../ocpp-2-0-json/src/main/resources/HeartbeatResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `heartbeat.resp.customData.vendorId` — string, required, maxLength 255
- `heartbeat.resp.currentTime` — string, required, format date-time — Contains the current time of the CSMS.

## installCertificate

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.INSTALLCERTIFICATE`
- Kotlin `InstallCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateReq.kt)
- Kotlin `InstallCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/installcertificate/InstallCertificateResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.30 InstallCertificate — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.30.1 InstallCertificateRequest — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.30.2 InstallCertificateResponse — pdf-page 361 (`grep -n 'pdf-page 361]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### installCertificate request

- schema: [`InstallCertificateRequest.json`](../../ocpp-2-0-json/src/main/resources/InstallCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.req.customData.vendorId` — string, required, maxLength 255
- `installCertificate.req.certificateType` — InstallCertificateUseEnumType (string), required, enum: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | ManufacturerRootCertificate — Indicates the certificate type that is sent.
- `installCertificate.req.certificate` — string, required, maxLength 5500 — A PEM encoded X.509 certificate.

### installCertificate response

- schema: [`InstallCertificateResponse.json`](../../ocpp-2-0-json/src/main/resources/InstallCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `installCertificate.resp.status` — InstallCertificateStatusEnumType (string), required, enum: Accepted | Rejected | Failed — Charging Station indicates if installation was successful.
- `installCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `installCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `installCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `installCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `installCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### installCertificate enumerations

- `installCertificate` `InstallCertificateStatusEnumType`: Accepted | Rejected | Failed — Charging Station indicates if installation was successful.
- `installCertificate` `InstallCertificateUseEnumType`: V2GRootCertificate | MORootCertificate | CSMSRootCertificate | ManufacturerRootCertificate — Indicates the certificate type that is sent.

## logStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.LOGSTATUSNOTIFICATION`
- Kotlin `LogStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationReq.kt)
- Kotlin `LogStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/logstatusnotification/LogStatusNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.31 LogStatusNotification — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.31.1 LogStatusNotificationRequest — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.31.2 LogStatusNotificationResponse — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'LogStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 95 (`grep -n 'LogStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### logStatusNotification request

- schema: [`LogStatusNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/LogStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `logStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `logStatusNotification.req.status` — UploadLogStatusEnumType (string), required, enum: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading | AcceptedCanceled — This contains the status of the log upload.
- `logStatusNotification.req.requestId` — integer, optional — The request id that was provided in GetLogRequest that started this log upload. This field is mandatory, unless the message was triggered by a TriggerMessageRequest AND there is no log upload ongoing.

### logStatusNotification response

- schema: [`LogStatusNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/LogStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `logStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

#### logStatusNotification enumerations

- `logStatusNotification` `UploadLogStatusEnumType`: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading | AcceptedCanceled — This contains the status of the log upload.

## meterValues

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/metervalues/MeterValuesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §3.1 MeterValues — pdf-page 227 (`grep -n 'pdf-page 227]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.32 MeterValues — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.32.1 MeterValuesRequest — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.32.2 MeterValuesResponse — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 54, 60 (`grep -n 'MeterValues' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### meterValues request

- schema: [`MeterValuesRequest.json`](../../ocpp-2-0-json/src/main/resources/MeterValuesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.evseId` — integer, required — Request_ Body. EVSEID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:571101 This contains a number (&gt;0) designating an EVSE of the Charging Station. ‘0’ (zero) is used to designate the main power meter.
- `meterValues.req.meterValue` — array, required, minItems 1
- `meterValues.req.meterValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue` — array, required, minItems 1
- `meterValues.req.meterValue[].sampledValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].value` — number, required — Sampled_ Value. Value. Measure urn:x-oca:ocpp:uid:1:569260 Indicates the measured value.
- `meterValues.req.meterValue[].sampledValue[].context` — ReadingContextEnumType (string), optional, enum: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `meterValues.req.meterValue[].sampledValue[].measurand` — MeasurandEnumType (string), optional, enum: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `meterValues.req.meterValue[].sampledValue[].phase` — PhaseEnumType (string), optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `meterValues.req.meterValue[].sampledValue[].location` — LocationEnumType (string), optional, enum: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue` — SignedMeterValueType, optional — Represent a signed version of the meter value.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.signedMeterData` — string, required, maxLength 2500 — Base64 encoded, contains the signed data which might contain more then just the meter value. It can contain information like timestamps, reference to a customer etc.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.signingMethod` — string, required, maxLength 50 — Method used to create the digital signature.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.encodingMethod` — string, required, maxLength 50 — Method used to encode the meter values before applying the digital signature algorithm.
- `meterValues.req.meterValue[].sampledValue[].signedMeterValue.publicKey` — string, required, maxLength 2500 — Base64 encoded, sending depends on configuration variable _PublicKeyWithSignedMeterValue_.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure` — UnitOfMeasureType, optional — Represents a UnitOfMeasure with a multiplier
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.customData.vendorId` — string, required, maxLength 255
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.unit` — string, optional, maxLength 20 — Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices. If an applicable unit is available in that list, otherwise a "custom" unit might be used.
- `meterValues.req.meterValue[].sampledValue[].unitOfMeasure.multiplier` — integer, optional — Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to the 3rd power. Default is 0.
- `meterValues.req.meterValue[].timestamp` — string, required, format date-time — Meter_ Value. Timestamp. Date_ Time urn:x-oca:ocpp:uid:1:569259 Timestamp for measured value(s).

### meterValues response

- schema: [`MeterValuesResponse.json`](../../ocpp-2-0-json/src/main/resources/MeterValuesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `meterValues.resp.customData.vendorId` — string, required, maxLength 255

#### meterValues enumerations

- `meterValues` `LocationEnumType`: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `meterValues` `MeasurandEnumType`: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `meterValues` `PhaseEnumType`: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `meterValues` `ReadingContextEnumType`: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"

## notifyChargingLimit

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYCHARGINGLIMIT`
- Kotlin `NotifyChargingLimitReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycharginglimit/NotifyChargingLimitReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycharginglimit/NotifyChargingLimitReq.kt)
- Kotlin `NotifyChargingLimitResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycharginglimit/NotifyChargingLimitResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycharginglimit/NotifyChargingLimitResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §3 NotifyChargingLimitRequest — pdf-page 242 (`grep -n 'pdf-page 242]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.33 NotifyChargingLimit — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.33.1 NotifyChargingLimitRequest — pdf-page 362 (`grep -n 'pdf-page 362]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.33.2 NotifyChargingLimitResponse — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### notifyChargingLimit request

- schema: [`NotifyChargingLimitRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyChargingLimitRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyChargingLimit.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule` — array, optional, minItems 1
- `notifyChargingLimit.req.chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `notifyChargingLimit.req.chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `notifyChargingLimit.req.chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `notifyChargingLimit.req.chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `notifyChargingLimit.req.chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `notifyChargingLimit.req.chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `notifyChargingLimit.req.chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `notifyChargingLimit.req.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `notifyChargingLimit.req.evseId` — integer, optional — The charging schedule contained in this notification applies to an EVSE. evseId must be &gt; 0.
- `notifyChargingLimit.req.chargingLimit` — ChargingLimitType, required — Charging_ Limit urn:x-enexis:ecdm:uid:2:234489
- `notifyChargingLimit.req.chargingLimit.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.req.chargingLimit.customData.vendorId` — string, required, maxLength 255
- `notifyChargingLimit.req.chargingLimit.chargingLimitSource` — ChargingLimitSourceEnumType (string), required, enum: EMS | Other | SO | CSO — Charging_ Limit. Charging_ Limit_ Source. Charging_ Limit_ Source_ Code urn:x-enexis:ecdm:uid:1:570845 Represents the source of the charging limit.
- `notifyChargingLimit.req.chargingLimit.isGridCritical` — boolean, optional — Charging_ Limit. Is_ Grid_ Critical. Indicator urn:x-enexis:ecdm:uid:1:570847 Indicates whether the charging limit is critical for the grid.

### notifyChargingLimit response

- schema: [`NotifyChargingLimitResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyChargingLimitResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyChargingLimit.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyChargingLimit.resp.customData.vendorId` — string, required, maxLength 255

#### notifyChargingLimit enumerations

- `notifyChargingLimit` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO — Charging_ Limit. Charging_ Limit_ Source. Charging_ Limit_ Source_ Code urn:x-enexis:ecdm:uid:1:570845 Represents the source of the charging limit.
- `notifyChargingLimit` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyChargingLimit` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount

## notifyCustomerInformation

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYCUSTOMERINFORMATION`
- Kotlin `NotifyCustomerInformationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationReq.kt)
- Kotlin `NotifyCustomerInformationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifycustomerinformation/NotifyCustomerInformationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.34 NotifyCustomerInformation — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.34.1 NotifyCustomerInformationRequest — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.34.2 NotifyCustomerInformationResponse — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'NotifyCustomerInformation' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### notifyCustomerInformation request

- schema: [`NotifyCustomerInformationRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyCustomerInformationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyCustomerInformation.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyCustomerInformation.req.customData.vendorId` — string, required, maxLength 255
- `notifyCustomerInformation.req.data` — string, required, maxLength 512 — (Part of) the requested data. No format specified in which the data is returned. Should be human readable.
- `notifyCustomerInformation.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the monitoringData follows in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
- `notifyCustomerInformation.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyCustomerInformation.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyCustomerInformation.req.requestId` — integer, required — The Id of the request.

### notifyCustomerInformation response

- schema: [`NotifyCustomerInformationResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyCustomerInformationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyCustomerInformation.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyCustomerInformation.resp.customData.vendorId` — string, required, maxLength 255

## notifyDisplayMessages

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYDISPLAYMESSAGES`
- Kotlin `NotifyDisplayMessagesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesReq.kt)
- Kotlin `NotifyDisplayMessagesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifydisplaymessages/NotifyDisplayMessagesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.35 NotifyDisplayMessages — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.35.1 NotifyDisplayMessagesRequest — pdf-page 363 (`grep -n 'pdf-page 363]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.35.2 NotifyDisplayMessagesResponse — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### notifyDisplayMessages request

- schema: [`NotifyDisplayMessagesRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyDisplayMessagesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyDisplayMessages.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo` — array, optional, minItems 1
- `notifyDisplayMessages.req.messageInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display` — ComponentType, optional — A physical or logical component
- `notifyDisplayMessages.req.messageInfo[].display.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].display.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyDisplayMessages.req.messageInfo[].display.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].display.evse.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].display.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyDisplayMessages.req.messageInfo[].display.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyDisplayMessages.req.messageInfo[].display.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyDisplayMessages.req.messageInfo[].display.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyDisplayMessages.req.messageInfo[].id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Master resource identifier, unique within an exchange context. It is defined within the OCPP context as a positive Integer value (greater or equal to zero).
- `notifyDisplayMessages.req.messageInfo[].priority` — MessagePriorityEnumType (string), required, enum: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `notifyDisplayMessages.req.messageInfo[].state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.
- `notifyDisplayMessages.req.messageInfo[].startDateTime` — string, optional, format date-time — Message_ Info. Start. Date_ Time urn:x-enexis:ecdm:uid:1:569256 From what date-time should this message be shown. If omitted: directly.
- `notifyDisplayMessages.req.messageInfo[].endDateTime` — string, optional, format date-time — Message_ Info. End. Date_ Time urn:x-enexis:ecdm:uid:1:569257 Until what date-time should this message be shown, after this date/time this message SHALL be removed.
- `notifyDisplayMessages.req.messageInfo[].transactionId` — string, optional, maxLength 36 — During which transaction shall this message be shown. Message SHALL be removed by the Charging Station after transaction has ended.
- `notifyDisplayMessages.req.messageInfo[].message` — MessageContentType, required — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `notifyDisplayMessages.req.messageInfo[].message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.req.messageInfo[].message.customData.vendorId` — string, required, maxLength 255
- `notifyDisplayMessages.req.messageInfo[].message.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `notifyDisplayMessages.req.messageInfo[].message.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `notifyDisplayMessages.req.messageInfo[].message.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `notifyDisplayMessages.req.requestId` — integer, required — The id of the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt; that requested this message.
- `notifyDisplayMessages.req.tbc` — boolean, optional — "to be continued" indicator. Indicates whether another part of the report follows in an upcoming NotifyDisplayMessagesRequest message. Default value when omitted is false.

### notifyDisplayMessages response

- schema: [`NotifyDisplayMessagesResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyDisplayMessagesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyDisplayMessages.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyDisplayMessages.resp.customData.vendorId` — string, required, maxLength 255

#### notifyDisplayMessages enumerations

- `notifyDisplayMessages` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `notifyDisplayMessages` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `notifyDisplayMessages` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.

## notifyEVChargingNeeds

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVCHARGINGNEEDS`
- Kotlin `NotifyEVChargingNeedsReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsReq.kt)
- Kotlin `NotifyEVChargingNeedsResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingneeds/NotifyEVChargingNeedsResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.36 NotifyEVChargingNeeds — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.36.1 NotifyEVChargingNeedsRequest — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.36.2 NotifyEVChargingNeedsResponse — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 39, 41, 42 (`grep -n 'NotifyEVChargingNeeds' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 63 (`grep -n 'NotifyEVChargingNeeds' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### notifyEVChargingNeeds request

- schema: [`NotifyEVChargingNeedsRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyEVChargingNeedsRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingNeeds.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.maxScheduleTuples` — integer, optional — Contains the maximum schedule tuples the car supports per schedule.
- `notifyEVChargingNeeds.req.chargingNeeds` — ChargingNeedsType, required — Charging_ Needs urn:x-oca:ocpp:uid:2:233249
- `notifyEVChargingNeeds.req.chargingNeeds.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters` — ACChargingParametersType, optional — AC_ Charging_ Parameters urn:x-oca:ocpp:uid:2:233250 EV AC charging parameters.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.energyAmount` — integer, required — AC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount urn:x-oca:ocpp:uid:1:569211 Amount of energy requested (in Wh). This includes energy required for preconditioning.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMinCurrent` — integer, required — AC_ Charging_ Parameters. EV_ Min. Current urn:x-oca:ocpp:uid:1:569212 Minimum current (amps) supported by the electric vehicle (per phase).
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMaxCurrent` — integer, required — AC_ Charging_ Parameters. EV_ Max. Current urn:x-oca:ocpp:uid:1:569213 Maximum current (amps) supported by the electric vehicle (per phase). Includes cable capacity.
- `notifyEVChargingNeeds.req.chargingNeeds.acChargingParameters.evMaxVoltage` — integer, required — AC_ Charging_ Parameters. EV_ Max. Voltage urn:x-oca:ocpp:uid:1:569214 Maximum voltage supported by the electric vehicle
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters` — DCChargingParametersType, optional — DC_ Charging_ Parameters urn:x-oca:ocpp:uid:2:233251 EV DC charging parameters
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxCurrent` — integer, required — DC_ Charging_ Parameters. EV_ Max. Current urn:x-oca:ocpp:uid:1:569215 Maximum current (amps) supported by the electric vehicle. Includes cable capacity.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxVoltage` — integer, required — DC_ Charging_ Parameters. EV_ Max. Voltage urn:x-oca:ocpp:uid:1:569216 Maximum voltage supported by the electric vehicle
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.energyAmount` — integer, optional — DC_ Charging_ Parameters. Energy_ Amount. Energy_ Amount urn:x-oca:ocpp:uid:1:569217 Amount of energy requested (in Wh). This inludes energy required for preconditioning.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evMaxPower` — integer, optional — DC_ Charging_ Parameters. EV_ Max. Power urn:x-oca:ocpp:uid:1:569218 Maximum power (in W) supported by the electric vehicle. Required for DC charging.
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.stateOfCharge` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. State_ Of_ Charge. Numeric urn:x-oca:ocpp:uid:1:569219 Energy available in the battery (in percent of the battery capacity)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.evEnergyCapacity` — integer, optional — DC_ Charging_ Parameters. EV_ Energy_ Capacity. Numeric urn:x-oca:ocpp:uid:1:569220 Capacity of the electric vehicle battery (in Wh)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.fullSoC` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. Full_ SOC. Percentage urn:x-oca:ocpp:uid:1:569221 Percentage of SoC at which the EV considers the battery fully charged. (possible values: 0 - 100)
- `notifyEVChargingNeeds.req.chargingNeeds.dcChargingParameters.bulkSoC` — integer, optional, max 100.0, min 0.0 — DC_ Charging_ Parameters. Bulk_ SOC. Percentage urn:x-oca:ocpp:uid:1:569222 Percentage of SoC at which the EV considers a fast charging process to end. (possible values: 0 - 100)
- `notifyEVChargingNeeds.req.chargingNeeds.requestedEnergyTransfer` — EnergyTransferModeEnumType (string), required, enum: DC | AC_single_phase | AC_two_phase | AC_three_phase — Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code urn:x-oca:ocpp:uid:1:569209 Mode of energy transfer requested by the EV.
- `notifyEVChargingNeeds.req.chargingNeeds.departureTime` — string, optional, format date-time — Charging_ Needs. Departure_ Time. Date_ Time urn:x-oca:ocpp:uid:1:569223 Estimated departure time of the EV.
- `notifyEVChargingNeeds.req.evseId` — integer, required — Defines the EVSE and connector to which the EV is connected. EvseId may not be 0.

### notifyEVChargingNeeds response

- schema: [`NotifyEVChargingNeedsResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyEVChargingNeedsResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingNeeds.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.resp.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.resp.status` — NotifyEVChargingNeedsStatusEnumType (string), required, enum: Accepted | Rejected | Processing — Returns whether the CSMS has been able to process the message successfully. It does not imply that the evChargingNeeds can be met with the current charging profile.
- `notifyEVChargingNeeds.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `notifyEVChargingNeeds.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingNeeds.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingNeeds.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `notifyEVChargingNeeds.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### notifyEVChargingNeeds enumerations

- `notifyEVChargingNeeds` `EnergyTransferModeEnumType`: DC | AC_single_phase | AC_two_phase | AC_three_phase — Charging_ Needs. Requested. Energy_ Transfer_ Mode_ Code urn:x-oca:ocpp:uid:1:569209 Mode of energy transfer requested by the EV.
- `notifyEVChargingNeeds` `NotifyEVChargingNeedsStatusEnumType`: Accepted | Rejected | Processing — Returns whether the CSMS has been able to process the message successfully. It does not imply that the evChargingNeeds can be met with the current charging profile.

## notifyEVChargingSchedule

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVCHARGINGSCHEDULE`
- Kotlin `NotifyEVChargingScheduleReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleReq.kt)
- Kotlin `NotifyEVChargingScheduleResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevchargingschedule/NotifyEVChargingScheduleResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §2 NotifyEVChargingScheduleRequest — pdf-page 242 (`grep -n 'pdf-page 242]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.37 NotifyEVChargingSchedule — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.37.1 NotifyEVChargingScheduleRequest — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.37.2 NotifyEVChargingScheduleResponse — pdf-page 364 (`grep -n 'pdf-page 364]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 40 (`grep -n 'NotifyEVChargingSchedule' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### notifyEVChargingSchedule request

- schema: [`NotifyEVChargingScheduleRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyEVChargingScheduleRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingSchedule.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.timeBase` — string, required, format date-time — Periods contained in the charging profile are relative to this point in time.
- `notifyEVChargingSchedule.req.chargingSchedule` — ChargingScheduleType, required — Charging_ Schedule urn:x-oca:ocpp:uid:2:233256 Charging schedule structure defines a list of charging periods, as used in: GetCompositeSchedule.conf and ChargingProfile.
- `notifyEVChargingSchedule.req.chargingSchedule.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.id` — integer, required — Identifies the ChargingSchedule.
- `notifyEVChargingSchedule.req.chargingSchedule.startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `notifyEVChargingSchedule.req.chargingSchedule.duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `notifyEVChargingSchedule.req.chargingSchedule.chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `notifyEVChargingSchedule.req.chargingSchedule.minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `notifyEVChargingSchedule.req.chargingSchedule.salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `notifyEVChargingSchedule.req.evseId` — integer, required — The charging schedule contained in this notification applies to an EVSE. EvseId must be &gt; 0.

### notifyEVChargingSchedule response

- schema: [`NotifyEVChargingScheduleResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyEVChargingScheduleResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEVChargingSchedule.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.resp.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether the CSMS has been able to process the message successfully. It does not imply any approval of the charging schedule.
- `notifyEVChargingSchedule.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `notifyEVChargingSchedule.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEVChargingSchedule.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `notifyEVChargingSchedule.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `notifyEVChargingSchedule.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### notifyEVChargingSchedule enumerations

- `notifyEVChargingSchedule` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `notifyEVChargingSchedule` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `notifyEVChargingSchedule` `GenericStatusEnumType`: Accepted | Rejected — Returns whether the CSMS has been able to process the message successfully. It does not imply any approval of the charging schedule.

## notifyEvent

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVENT`
- Kotlin `NotifyEventReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventReq.kt)
- Kotlin `NotifyEventResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.38 NotifyEvent — pdf-page 365 (`grep -n 'pdf-page 365]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.38.1 NotifyEventRequest — pdf-page 365 (`grep -n 'pdf-page 365]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.38.2 NotifyEventResponse — pdf-page 365 (`grep -n 'pdf-page 365]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 2, 19 (`grep -n 'NotifyEvent' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### notifyEvent request

- schema: [`NotifyEventRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyEventRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEvent.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyEvent.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the report follows in an upcoming notifyEventRequest message. Default value when omitted is false.
- `notifyEvent.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyEvent.req.eventData` — array, required, minItems 1
- `notifyEvent.req.eventData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].eventId` — integer, required — Identifies the event. This field can be referred to as a cause by other events.
- `notifyEvent.req.eventData[].timestamp` — string, required, format date-time — Timestamp of the moment the report was generated.
- `notifyEvent.req.eventData[].trigger` — EventTriggerEnumType (string), required, enum: Alerting | Delta | Periodic — Type of monitor that triggered this event, e.g. exceeding a threshold value.
- `notifyEvent.req.eventData[].cause` — integer, optional — Refers to the Id of an event that is considered to be the cause for this event.
- `notifyEvent.req.eventData[].actualValue` — string, required, maxLength 2500 — Actual value (_attributeType_ Actual) of the variable. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `notifyEvent.req.eventData[].techCode` — string, optional, maxLength 50 — Technical (error) code as reported by component.
- `notifyEvent.req.eventData[].techInfo` — string, optional, maxLength 500 — Technical detail information as reported by component.
- `notifyEvent.req.eventData[].cleared` — boolean, optional — _Cleared_ is set to true to report the clearing of a monitored situation, i.e. a 'return to normal'.
- `notifyEvent.req.eventData[].transactionId` — string, optional, maxLength 36 — If an event notification is linked to a specific transaction, this field can be used to specify its transactionId.
- `notifyEvent.req.eventData[].component` — ComponentType, required — A physical or logical component
- `notifyEvent.req.eventData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].component.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyEvent.req.eventData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyEvent.req.eventData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyEvent.req.eventData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].variableMonitoringId` — integer, optional — Identifies the VariableMonitoring which triggered the event.
- `notifyEvent.req.eventData[].eventNotificationType` — EventNotificationEnumType (string), required, enum: HardWiredNotification | HardWiredMonitor | PreconfiguredMonitor | CustomMonitor — Specifies the event notification type of the message.
- `notifyEvent.req.eventData[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyEvent.req.eventData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### notifyEvent response

- schema: [`NotifyEventResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyEventResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEvent.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.resp.customData.vendorId` — string, required, maxLength 255

#### notifyEvent enumerations

- `notifyEvent` `EventNotificationEnumType`: HardWiredNotification | HardWiredMonitor | PreconfiguredMonitor | CustomMonitor — Specifies the event notification type of the message.
- `notifyEvent` `EventTriggerEnumType`: Alerting | Delta | Periodic — Type of monitor that triggered this event, e.g. exceeding a threshold value.

## notifyMonitoringReport

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYMONITORINGREPORT`
- Kotlin `NotifyMonitoringReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportReq.kt)
- Kotlin `NotifyMonitoringReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifymonitoringreport/NotifyMonitoringReportResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.39 NotifyMonitoringReport — pdf-page 365 (`grep -n 'pdf-page 365]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.39.1 NotifyMonitoringReportRequest — pdf-page 365 (`grep -n 'pdf-page 365]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.39.2 NotifyMonitoringReportResponse — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'NotifyMonitoringReport' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### notifyMonitoringReport request

- schema: [`NotifyMonitoringReportRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyMonitoringReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyMonitoringReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor` — array, optional, minItems 1
- `notifyMonitoringReport.req.monitor[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component` — ComponentType, required — A physical or logical component
- `notifyMonitoringReport.req.monitor[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].component.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyMonitoringReport.req.monitor[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyMonitoringReport.req.monitor[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyMonitoringReport.req.monitor[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyMonitoringReport.req.monitor[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyMonitoringReport.req.monitor[].variableMonitoring` — array, required, minItems 1
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].customData.vendorId` — string, required, maxLength 255
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].id` — integer, required — Identifies the monitor.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].transaction` — boolean, required — Monitor only active when a transaction is ongoing on a component relevant to this transaction.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].value` — number, required — Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the interval in seconds.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `notifyMonitoringReport.req.monitor[].variableMonitoring[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.
- `notifyMonitoringReport.req.requestId` — integer, required — The id of the GetMonitoringRequest that requested this report.
- `notifyMonitoringReport.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the monitoringData follows in an upcoming notifyMonitoringReportRequest message. Default value when omitted is false.
- `notifyMonitoringReport.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyMonitoringReport.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.

### notifyMonitoringReport response

- schema: [`NotifyMonitoringReportResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyMonitoringReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyMonitoringReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyMonitoringReport.resp.customData.vendorId` — string, required, maxLength 255

#### notifyMonitoringReport enumerations

- `notifyMonitoringReport` `MonitorEnumType`: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.

## notifyReport

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYREPORT`
- Kotlin `NotifyReportReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportReq.kt)
- Kotlin `NotifyReportResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyreport/NotifyReportResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.40 NotifyReport — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.40.1 NotifyReportRequest — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.40.2 NotifyReportResponse — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'NotifyReport' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### notifyReport request

- schema: [`NotifyReportRequest.json`](../../ocpp-2-0-json/src/main/resources/NotifyReportRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyReport.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.requestId` — integer, required — The id of the GetReportRequest or GetBaseReportRequest that requested this report
- `notifyReport.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyReport.req.reportData` — array, optional, minItems 1
- `notifyReport.req.reportData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component` — ComponentType, required — A physical or logical component
- `notifyReport.req.reportData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].component.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyReport.req.reportData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyReport.req.reportData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyReport.req.reportData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyReport.req.reportData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyReport.req.reportData[].variableAttribute` — array, required, maxItems 4, minItems 1
- `notifyReport.req.reportData[].variableAttribute[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variableAttribute[].customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variableAttribute[].type` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
- `notifyReport.req.reportData[].variableAttribute[].value` — string, optional, maxLength 2500 — Value of the attribute. May only be omitted when mutability is set to 'WriteOnly'. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `notifyReport.req.reportData[].variableAttribute[].mutability` — MutabilityEnumType (string), optional, enum: ReadOnly | WriteOnly | ReadWrite — Defines the mutability of this attribute. Default is ReadWrite when omitted.
- `notifyReport.req.reportData[].variableAttribute[].persistent` — boolean, optional — If true, value will be persistent across system reboots or power down. Default when omitted is false.
- `notifyReport.req.reportData[].variableAttribute[].constant` — boolean, optional — If true, value that will never be changed by the Charging Station at runtime. Default when omitted is false.
- `notifyReport.req.reportData[].variableCharacteristics` — VariableCharacteristicsType, optional — Fixed read-only parameters of a variable.
- `notifyReport.req.reportData[].variableCharacteristics.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.req.reportData[].variableCharacteristics.customData.vendorId` — string, required, maxLength 255
- `notifyReport.req.reportData[].variableCharacteristics.unit` — string, optional, maxLength 16 — Unit of the variable. When the transmitted value has a unit, this field SHALL be included.
- `notifyReport.req.reportData[].variableCharacteristics.dataType` — DataEnumType (string), required, enum: string | decimal | integer | dateTime | boolean | OptionList | SequenceList | MemberList — Data type of this variable.
- `notifyReport.req.reportData[].variableCharacteristics.minLimit` — number, optional — Minimum possible value of this variable.
- `notifyReport.req.reportData[].variableCharacteristics.maxLimit` — number, optional — Maximum possible value of this variable. When the datatype of this Variable is String, OptionList, SequenceList or MemberList, this field defines the maximum length of the (CSV) string.
- `notifyReport.req.reportData[].variableCharacteristics.valuesList` — string, optional, maxLength 1000 — Allowed values when variable is Option/Member/SequenceList. * OptionList: The (Actual) Variable value must be a single value from the reported (CSV) enumeration list. * MemberList: The (Actual) Variable value may be an (unordered) (sub-)set of the reported (CSV) valid values list. * SequenceList: The (Actual) Variable value may be an ordered (priority, etc) (sub-)set of the reported (CSV) valid values. This is a comma separated list. The Configuration Variable &lt;&lt;configkey-configuration-value-size,ConfigurationValueSize&gt;&gt; can be used to limit SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these values will always remain equal.
- `notifyReport.req.reportData[].variableCharacteristics.supportsMonitoring` — boolean, required — Flag indicating if this variable supports monitoring.
- `notifyReport.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the report follows in an upcoming notifyReportRequest message. Default value when omitted is false.
- `notifyReport.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.

### notifyReport response

- schema: [`NotifyReportResponse.json`](../../ocpp-2-0-json/src/main/resources/NotifyReportResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyReport.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyReport.resp.customData.vendorId` — string, required, maxLength 255

#### notifyReport enumerations

- `notifyReport` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Attribute: Actual, MinSet, MaxSet, etc. Defaults to Actual if absent.
- `notifyReport` `DataEnumType`: string | decimal | integer | dateTime | boolean | OptionList | SequenceList | MemberList — Data type of this variable.
- `notifyReport` `MutabilityEnumType`: ReadOnly | WriteOnly | ReadWrite — Defines the mutability of this attribute. Default is ReadWrite when omitted.

## publishFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.PUBLISHFIRMWARE`
- Kotlin `PublishFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareReq.kt)
- Kotlin `PublishFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmware/PublishFirmwareResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.41 PublishFirmware — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.41.1 PublishFirmwareRequest — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.41.2 PublishFirmwareResponse — pdf-page 366 (`grep -n 'pdf-page 366]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'PublishFirmware' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### publishFirmware request

- schema: [`PublishFirmwareRequest.json`](../../ocpp-2-0-json/src/main/resources/PublishFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.req.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.req.location` — string, required, maxLength 512 — This contains a string containing a URI pointing to a location from which to retrieve the firmware.
- `publishFirmware.req.retries` — integer, optional — This specifies how many times Charging Station must try to download the firmware before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `publishFirmware.req.checksum` — string, required, maxLength 32 — The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.
- `publishFirmware.req.requestId` — integer, required — The Id of the request.
- `publishFirmware.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.

### publishFirmware response

- schema: [`PublishFirmwareResponse.json`](../../ocpp-2-0-json/src/main/resources/PublishFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Indicates whether the request was accepted.
- `publishFirmware.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `publishFirmware.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmware.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `publishFirmware.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `publishFirmware.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### publishFirmware enumerations

- `publishFirmware` `GenericStatusEnumType`: Accepted | Rejected — Indicates whether the request was accepted.

## publishFirmwareStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.PUBLISHFIRMWARESTATUSNOTIFICATION`
- Kotlin `PublishFirmwareStatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationReq.kt)
- Kotlin `PublishFirmwareStatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/publishfirmwarestatusnotification/PublishFirmwareStatusNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.42 PublishFirmwareStatusNotification — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.42.1 PublishFirmwareStatusNotificationRequest — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.42.2 PublishFirmwareStatusNotificationResponse — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'PublishFirmwareStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 73, 93, 102 (`grep -n 'PublishFirmwareStatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### publishFirmwareStatusNotification request

- schema: [`PublishFirmwareStatusNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/PublishFirmwareStatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmwareStatusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmwareStatusNotification.req.customData.vendorId` — string, required, maxLength 255
- `publishFirmwareStatusNotification.req.status` — PublishFirmwareStatusEnumType (string), required, enum: Idle | DownloadScheduled | Downloading | Downloaded | Published | DownloadFailed | DownloadPaused | InvalidChecksum | ChecksumVerified | PublishFailed — This contains the progress status of the publishfirmware installation.
- `publishFirmwareStatusNotification.req.location` — array, optional, minItems 1 — Required if status is Published. Can be multiple URI’s, if the Local Controller supports e.g. HTTP, HTTPS, and FTP.
- `publishFirmwareStatusNotification.req.location[]` — string, maxLength 512
- `publishFirmwareStatusNotification.req.requestId` — integer, optional — The request id that was provided in the PublishFirmwareRequest which triggered this action.

### publishFirmwareStatusNotification response

- schema: [`PublishFirmwareStatusNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/PublishFirmwareStatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `publishFirmwareStatusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `publishFirmwareStatusNotification.resp.customData.vendorId` — string, required, maxLength 255

#### publishFirmwareStatusNotification enumerations

- `publishFirmwareStatusNotification` `PublishFirmwareStatusEnumType`: Idle | DownloadScheduled | Downloading | Downloaded | Published | DownloadFailed | DownloadPaused | InvalidChecksum | ChecksumVerified | PublishFailed — This contains the progress status of the publishfirmware installation.

## reportChargingProfiles

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REPORTCHARGINGPROFILES`
- Kotlin `ReportChargingProfilesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reportchargingprofiles/ReportChargingProfilesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reportchargingprofiles/ReportChargingProfilesReq.kt)
- Kotlin `ReportChargingProfilesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reportchargingprofiles/ReportChargingProfilesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reportchargingprofiles/ReportChargingProfilesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.43 ReportChargingProfiles — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.43.1 ReportChargingProfilesRequest — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.43.2 ReportChargingProfilesResponse — pdf-page 367 (`grep -n 'pdf-page 367]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7, 35 (`grep -n 'ReportChargingProfiles' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### reportChargingProfiles request

- schema: [`ReportChargingProfilesRequest.json`](../../ocpp-2-0-json/src/main/resources/ReportChargingProfilesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reportChargingProfiles.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.requestId` — integer, required — Id used to match the &lt;&lt;getchargingprofilesrequest, GetChargingProfilesRequest&gt;&gt; message with the resulting ReportChargingProfilesRequest messages. When the CSMS provided a requestId in the &lt;&lt;getchargingprofilesrequest, GetChargingProfilesRequest&gt;&gt;, this field SHALL contain the same value.
- `reportChargingProfiles.req.chargingLimitSource` — ChargingLimitSourceEnumType (string), required, enum: EMS | Other | SO | CSO — Source that has installed this charging profile.
- `reportChargingProfiles.req.chargingProfile` — array, required, minItems 1
- `reportChargingProfiles.req.chargingProfile[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Id of ChargingProfile.
- `reportChargingProfiles.req.chargingProfile[].stackLevel` — integer, required — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `reportChargingProfiles.req.chargingProfile[].chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), required, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `reportChargingProfiles.req.chargingProfile[].chargingProfileKind` — ChargingProfileKindEnumType (string), required, enum: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `reportChargingProfiles.req.chargingProfile[].recurrencyKind` — RecurrencyKindEnumType (string), optional, enum: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `reportChargingProfiles.req.chargingProfile[].validFrom` — string, optional, format date-time — Charging_ Profile. Valid_ From. Date_ Time urn:x-oca:ocpp:uid:1:569234 Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
- `reportChargingProfiles.req.chargingProfile[].validTo` — string, optional, format date-time — Charging_ Profile. Valid_ To. Date_ Time urn:x-oca:ocpp:uid:1:569235 Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule` — array, required, maxItems 3, minItems 1
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `reportChargingProfiles.req.chargingProfile[].chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `reportChargingProfiles.req.chargingProfile[].transactionId` — string, optional, maxLength 36 — SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.
- `reportChargingProfiles.req.tbc` — boolean, optional — To Be Continued. Default value when omitted: false. false indicates that there are no further messages as part of this report.
- `reportChargingProfiles.req.evseId` — integer, required — The evse to which the charging profile applies. If evseId = 0, the message contains an overall limit for the Charging Station.

### reportChargingProfiles response

- schema: [`ReportChargingProfilesResponse.json`](../../ocpp-2-0-json/src/main/resources/ReportChargingProfilesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reportChargingProfiles.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reportChargingProfiles.resp.customData.vendorId` — string, required, maxLength 255

#### reportChargingProfiles enumerations

- `reportChargingProfiles` `ChargingLimitSourceEnumType`: EMS | Other | SO | CSO — Source that has installed this charging profile.
- `reportChargingProfiles` `ChargingProfileKindEnumType`: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `reportChargingProfiles` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `reportChargingProfiles` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `reportChargingProfiles` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `reportChargingProfiles` `RecurrencyKindEnumType`: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.

## requestStartTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REQUESTSTARTTRANSACTION`
- Kotlin `RequestStartTransactionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionReq.kt)
- Kotlin `RequestStartTransactionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestart/RequestStartTransactionResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.44 RequestStartTransaction — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.44.1 RequestStartTransactionRequest — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.44.2 RequestStartTransactionResponse — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 16, 25, 26 (`grep -n 'RequestStartTransaction' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 96 (`grep -n 'RequestStartTransaction' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### requestStartTransaction request

- schema: [`RequestStartTransactionRequest.json`](../../ocpp-2-0-json/src/main/resources/RequestStartTransactionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStartTransaction.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.evseId` — integer, optional — Number of the EVSE on which to start the transaction. EvseId SHALL be &gt; 0
- `requestStartTransaction.req.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `requestStartTransaction.req.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.groupIdToken.additionalInfo` — array, optional, minItems 1
- `requestStartTransaction.req.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `requestStartTransaction.req.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `requestStartTransaction.req.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `requestStartTransaction.req.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction.req.idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `requestStartTransaction.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.idToken.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.idToken.additionalInfo` — array, optional, minItems 1
- `requestStartTransaction.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `requestStartTransaction.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `requestStartTransaction.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `requestStartTransaction.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction.req.remoteStartId` — integer, required — Id given by the server to this start request. The Charging Station might return this in the &lt;&lt;transactioneventrequest, TransactionEventRequest&gt;&gt;, letting the server know which transaction was started for this request. Use to start a transaction.
- `requestStartTransaction.req.chargingProfile` — ChargingProfileType, optional — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `requestStartTransaction.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Id of ChargingProfile.
- `requestStartTransaction.req.chargingProfile.stackLevel` — integer, required — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `requestStartTransaction.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), required, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `requestStartTransaction.req.chargingProfile.chargingProfileKind` — ChargingProfileKindEnumType (string), required, enum: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `requestStartTransaction.req.chargingProfile.recurrencyKind` — RecurrencyKindEnumType (string), optional, enum: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `requestStartTransaction.req.chargingProfile.validFrom` — string, optional, format date-time — Charging_ Profile. Valid_ From. Date_ Time urn:x-oca:ocpp:uid:1:569234 Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
- `requestStartTransaction.req.chargingProfile.validTo` — string, optional, format date-time — Charging_ Profile. Valid_ To. Date_ Time urn:x-oca:ocpp:uid:1:569235 Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
- `requestStartTransaction.req.chargingProfile.chargingSchedule` — array, required, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `requestStartTransaction.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `requestStartTransaction.req.chargingProfile.transactionId` — string, optional, maxLength 36 — SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.

### requestStartTransaction response

- schema: [`RequestStartTransactionResponse.json`](../../ocpp-2-0-json/src/main/resources/RequestStartTransactionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStartTransaction.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.resp.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.resp.status` — RequestStartStopStatusEnumType (string), required, enum: Accepted | Rejected — Status indicating whether the Charging Station accepts the request to start a transaction.
- `requestStartTransaction.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `requestStartTransaction.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStartTransaction.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `requestStartTransaction.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `requestStartTransaction.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `requestStartTransaction.resp.transactionId` — string, optional, maxLength 36 — When the transaction was already started by the Charging Station before the RequestStartTransactionRequest was received, for example: cable plugged in first. This contains the transactionId of the already started transaction.

#### requestStartTransaction enumerations

- `requestStartTransaction` `ChargingProfileKindEnumType`: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `requestStartTransaction` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `requestStartTransaction` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `requestStartTransaction` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `requestStartTransaction` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `requestStartTransaction` `RecurrencyKindEnumType`: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `requestStartTransaction` `RequestStartStopStatusEnumType`: Accepted | Rejected — Status indicating whether the Charging Station accepts the request to start a transaction.

## requestStopTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REQUESTSTOPTRANSACTION`
- Kotlin `RequestStopTransactionReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionReq.kt)
- Kotlin `RequestStopTransactionResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/remotestop/RequestStopTransactionResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.45 RequestStopTransaction — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.45.1 RequestStopTransactionRequest — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.45.2 RequestStopTransactionResponse — pdf-page 368 (`grep -n 'pdf-page 368]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### requestStopTransaction request

- schema: [`RequestStopTransactionRequest.json`](../../ocpp-2-0-json/src/main/resources/RequestStopTransactionRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStopTransaction.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.req.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.req.transactionId` — string, required, maxLength 36 — The identifier of the transaction which the Charging Station is requested to stop.

### requestStopTransaction response

- schema: [`RequestStopTransactionResponse.json`](../../ocpp-2-0-json/src/main/resources/RequestStopTransactionResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `requestStopTransaction.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.resp.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.resp.status` — RequestStartStopStatusEnumType (string), required, enum: Accepted | Rejected — Status indicating whether Charging Station accepts the request to stop a transaction.
- `requestStopTransaction.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `requestStopTransaction.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `requestStopTransaction.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `requestStopTransaction.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `requestStopTransaction.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### requestStopTransaction enumerations

- `requestStopTransaction` `RequestStartStopStatusEnumType`: Accepted | Rejected — Status indicating whether Charging Station accepts the request to stop a transaction.

## reservationStatusUpdate

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.RESERVATIONSTATUSUPDATE`
- Kotlin `ReservationStatusUpdateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateReq.kt)
- Kotlin `ReservationStatusUpdateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservationstatusupdate/ReservationStatusUpdateResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.46 ReservationStatusUpdate — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.46.1 ReservationStatusUpdateRequest — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.46.2 ReservationStatusUpdateResponse — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 51 (`grep -n 'ReservationStatusUpdate' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### reservationStatusUpdate request

- schema: [`ReservationStatusUpdateRequest.json`](../../ocpp-2-0-json/src/main/resources/ReservationStatusUpdateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reservationStatusUpdate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reservationStatusUpdate.req.customData.vendorId` — string, required, maxLength 255
- `reservationStatusUpdate.req.reservationId` — integer, required — The ID of the reservation.
- `reservationStatusUpdate.req.reservationUpdateStatus` — ReservationUpdateStatusEnumType (string), required, enum: Expired | Removed — The updated reservation status.

### reservationStatusUpdate response

- schema: [`ReservationStatusUpdateResponse.json`](../../ocpp-2-0-json/src/main/resources/ReservationStatusUpdateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reservationStatusUpdate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reservationStatusUpdate.resp.customData.vendorId` — string, required, maxLength 255

#### reservationStatusUpdate enumerations

- `reservationStatusUpdate` `ReservationUpdateStatusEnumType`: Expired | Removed — The updated reservation status.

## reserveNow

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reservenow/ReserveNowResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.47 ReserveNow — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.47.1 ReserveNowRequest — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.47.2 ReserveNowResponse — pdf-page 369 (`grep -n 'pdf-page 369]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 96 (`grep -n 'ReserveNow' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### reserveNow request

- schema: [`ReserveNowRequest.json`](../../ocpp-2-0-json/src/main/resources/ReserveNowRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.id` — integer, required — Id of reservation.
- `reserveNow.req.expiryDateTime` — string, required, format date-time — Date and time at which the reservation expires.
- `reserveNow.req.connectorType` — ConnectorEnumType (string), optional, enum: cCCS1 | cCCS2 | cG105 | cTesla | cType1 | cType2 | s309-1P-16A | s309-1P-32A | s309-3P-16A | s309-3P-32A | sBS1361 | sCEE-7-7 | sType2 | sType3 | Other1PhMax16A | Other1PhOver16A | Other3Ph | Pan | wInductive | wResonant | Undetermined | Unknown — This field specifies the connector type.
- `reserveNow.req.idToken` — IdTokenType, required — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `reserveNow.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.idToken.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.idToken.additionalInfo` — array, optional, minItems 1
- `reserveNow.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `reserveNow.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `reserveNow.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `reserveNow.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `reserveNow.req.evseId` — integer, optional — This contains ID of the evse to be reserved.
- `reserveNow.req.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `reserveNow.req.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.groupIdToken.additionalInfo` — array, optional, minItems 1
- `reserveNow.req.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.req.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `reserveNow.req.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `reserveNow.req.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `reserveNow.req.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `reserveNow.req.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

### reserveNow response

- schema: [`ReserveNowResponse.json`](../../ocpp-2-0-json/src/main/resources/ReserveNowResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.resp.customData.vendorId` — string, required, maxLength 255
- `reserveNow.resp.status` — ReserveNowStatusEnumType (string), required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable — This indicates the success or failure of the reservation.
- `reserveNow.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `reserveNow.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reserveNow.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `reserveNow.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `reserveNow.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### reserveNow enumerations

- `reserveNow` `ConnectorEnumType`: cCCS1 | cCCS2 | cG105 | cTesla | cType1 | cType2 | s309-1P-16A | s309-1P-32A | s309-3P-16A | s309-3P-32A | sBS1361 | sCEE-7-7 | sType2 | sType3 | Other1PhMax16A | Other1PhOver16A | Other3Ph | Pan | wInductive | wResonant | Undetermined | Unknown — This field specifies the connector type.
- `reserveNow` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `reserveNow` `ReserveNowStatusEnumType`: Accepted | Faulted | Occupied | Rejected | Unavailable — This indicates the success or failure of the reservation.

## reset

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/reset/ResetResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.48 Reset — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.48.1 ResetRequest — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.48.2 ResetResponse — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §3.74 ResetEnumType — pdf-page 423 (`grep -n 'pdf-page 423]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §2.1.14 ResetRetries — pdf-page 433 (`grep -n 'pdf-page 433]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- changelog mentions: `changelog-2.0-to-2.0.1` pdf-page 8 (`grep -n 'Reset' docs/protocol/spec/2.0.1/changelog-2.0-to-2.0.1.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 28, 29, 30, 110 (`grep -n 'Reset' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### reset request

- schema: [`ResetRequest.json`](../../ocpp-2-0-json/src/main/resources/ResetRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.req.customData.vendorId` — string, required, maxLength 255
- `reset.req.type` — ResetEnumType (string), required, enum: Immediate | OnIdle — This contains the type of reset that the Charging Station or EVSE should perform.
- `reset.req.evseId` — integer, optional — This contains the ID of a specific EVSE that needs to be reset, instead of the entire Charging Station.

### reset response

- schema: [`ResetResponse.json`](../../ocpp-2-0-json/src/main/resources/ResetResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.resp.customData.vendorId` — string, required, maxLength 255
- `reset.resp.status` — ResetStatusEnumType (string), required, enum: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the reset.
- `reset.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `reset.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `reset.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `reset.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `reset.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### reset enumerations

- `reset` `ResetEnumType`: Immediate | OnIdle — This contains the type of reset that the Charging Station or EVSE should perform.
- `reset` `ResetStatusEnumType`: Accepted | Rejected | Scheduled — This indicates whether the Charging Station is able to perform the reset.

## securityEventNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SECURITYEVENTNOTIFICATION`
- Kotlin `SecurityEventNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationReq.kt)
- Kotlin `SecurityEventNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/securityeventnotification/SecurityEventNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §A04 Security Event Notification — pdf-page 48 (`grep -n 'pdf-page 48]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.49 SecurityEventNotification — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.49.1 SecurityEventNotificationRequest — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.49.2 SecurityEventNotificationResponse — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 54 (`grep -n 'SecurityEventNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### securityEventNotification request

- schema: [`SecurityEventNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/SecurityEventNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `securityEventNotification.req.customData.vendorId` — string, required, maxLength 255
- `securityEventNotification.req.type` — string, required, maxLength 50 — Type of the security event. This value should be taken from the Security events list.
- `securityEventNotification.req.timestamp` — string, required, format date-time — Date and time at which the event occurred.
- `securityEventNotification.req.techInfo` — string, optional, maxLength 255 — Additional information about the occurred security event.

### securityEventNotification response

- schema: [`SecurityEventNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/SecurityEventNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `securityEventNotification.resp.customData.vendorId` — string, required, maxLength 255

## sendLocalList

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/sendlocallist/SendLocalListResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.50 SendLocalList — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.50.1 SendLocalListRequest — pdf-page 370 (`grep -n 'pdf-page 370]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.50.2 SendLocalListResponse — pdf-page 371 (`grep -n 'pdf-page 371]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 71 (`grep -n 'SendLocalList' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 114 (`grep -n 'SendLocalList' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### sendLocalList request

- schema: [`SendLocalListRequest.json`](../../ocpp-2-0-json/src/main/resources/SendLocalListRequest.json) · OCPP 2.0.1 FINAL
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

### sendLocalList response

- schema: [`SendLocalListResponse.json`](../../ocpp-2-0-json/src/main/resources/SendLocalListResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.resp.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.resp.status` — SendLocalListStatusEnumType (string), required, enum: Accepted | Failed | VersionMismatch — This indicates whether the Charging Station has successfully received and applied the update of the Local Authorization List.
- `sendLocalList.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `sendLocalList.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `sendLocalList.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `sendLocalList.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `sendLocalList.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### sendLocalList enumerations

- `sendLocalList` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `sendLocalList` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `sendLocalList` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `sendLocalList` `SendLocalListStatusEnumType`: Accepted | Failed | VersionMismatch — This indicates whether the Charging Station has successfully received and applied the update of the Local Authorization List.
- `sendLocalList` `UpdateEnumType`: Differential | Full — This contains the type of update (full or differential) of this request.

## setChargingProfile

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETCHARGINGPROFILE`
- Kotlin `SetChargingProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileReq.kt)
- Kotlin `SetChargingProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setchargingprofile/SetChargingProfileResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §K01 SetChargingProfile — pdf-page 243 (`grep -n 'pdf-page 243]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.51 SetChargingProfile — pdf-page 371 (`grep -n 'pdf-page 371]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.51.1 SetChargingProfileRequest — pdf-page 371 (`grep -n 'pdf-page 371]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.51.2 SetChargingProfileResponse — pdf-page 371 (`grep -n 'pdf-page 371]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 3, 35, 41 (`grep -n 'SetChargingProfile' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 57, 58, 59, 71 (`grep -n 'SetChargingProfile' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### setChargingProfile request

- schema: [`SetChargingProfileRequest.json`](../../ocpp-2-0-json/src/main/resources/SetChargingProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.evseId` — integer, required — For TxDefaultProfile an evseId=0 applies the profile to each individual evse. For ChargingStationMaxProfile and ChargingStationExternalConstraints an evseId=0 contains an overal limit for the whole Charging Station.
- `setChargingProfile.req.chargingProfile` — ChargingProfileType, required — Charging_ Profile urn:x-oca:ocpp:uid:2:233255 A ChargingProfile consists of ChargingSchedule, describing the amount of power or current that can be delivered per time interval.
- `setChargingProfile.req.chargingProfile.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Id of ChargingProfile.
- `setChargingProfile.req.chargingProfile.stackLevel` — integer, required — Charging_ Profile. Stack_ Level. Counter urn:x-oca:ocpp:uid:1:569230 Value determining level in hierarchy stack of profiles. Higher values have precedence over lower values. Lowest level is 0.
- `setChargingProfile.req.chargingProfile.chargingProfilePurpose` — ChargingProfilePurposeEnumType (string), required, enum: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `setChargingProfile.req.chargingProfile.chargingProfileKind` — ChargingProfileKindEnumType (string), required, enum: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `setChargingProfile.req.chargingProfile.recurrencyKind` — RecurrencyKindEnumType (string), optional, enum: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.
- `setChargingProfile.req.chargingProfile.validFrom` — string, optional, format date-time — Charging_ Profile. Valid_ From. Date_ Time urn:x-oca:ocpp:uid:1:569234 Point in time at which the profile starts to be valid. If absent, the profile is valid as soon as it is received by the Charging Station.
- `setChargingProfile.req.chargingProfile.validTo` — string, optional, format date-time — Charging_ Profile. Valid_ To. Date_ Time urn:x-oca:ocpp:uid:1:569235 Point in time at which the profile stops to be valid. If absent, the profile is valid until it is replaced by another profile.
- `setChargingProfile.req.chargingProfile.chargingSchedule` — array, required, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].id` — integer, required — Identifies the ChargingSchedule.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].startSchedule` — string, optional, format date-time — Charging_ Schedule. Start_ Schedule. Date_ Time urn:x-oca:ocpp:uid:1:569237 Starting point of an absolute schedule. If absent the schedule will be relative to start of charging.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].duration` — integer, optional — Charging_ Schedule. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569236 Duration of the charging schedule in seconds. If the duration is left empty, the last period will continue indefinitely or until end of the transaction if chargingProfilePurpose = TxProfile.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingRateUnit` — ChargingRateUnitEnumType (string), required, enum: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod` — array, required, maxItems 1024, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].startPeriod` — integer, required — Charging_ Schedule_ Period. Start_ Period. Elapsed_ Time urn:x-oca:ocpp:uid:1:569240 Start of the period, in seconds from the start of schedule. The value of StartPeriod also defines the stop time of the previous period.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].limit` — number, required — Charging_ Schedule_ Period. Limit. Measure urn:x-oca:ocpp:uid:1:569241 Charging rate limit during the schedule period, in the applicable chargingRateUnit, for example in Amperes (A) or Watts (W). Accepts at most one digit fraction (e.g. 8.1).
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].numberPhases` — integer, optional — Charging_ Schedule_ Period. Number_ Phases. Counter urn:x-oca:ocpp:uid:1:569242 The number of phases that can be used for charging. If a number of phases is needed, numberPhases=3 will be assumed unless another number is given.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].chargingSchedulePeriod[].phaseToUse` — integer, optional — Values: 1..3, Used if numberPhases=1 and if the EVSE is capable of switching the phase connected to the EV, i.e. ACPhaseSwitchingSupported is defined and true. It’s not allowed unless both conditions above are true. If both conditions are true, and phaseToUse is omitted, the Charging Station / EVSE will make the selection on its own.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].minChargingRate` — number, optional — Charging_ Schedule. Min_ Charging_ Rate. Numeric urn:x-oca:ocpp:uid:1:569239 Minimum charging rate supported by the EV. The unit of measure is defined by the chargingRateUnit. This parameter is intended to be used by a local smart charging algorithm to optimize the power allocation for in the case a charging process is inefficient at lower charging rates. Accepts at most one digit fraction (e.g. 8.1)
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff` — SalesTariffType, optional — Sales_ Tariff urn:x-oca:ocpp:uid:2:233272 NOTE: This dataType is based on dataTypes from &lt;&lt;ref-ISOIEC15118-2,ISO 15118-2&gt;&gt;.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 SalesTariff identifier used to identify one sales tariff. An SAID remains a unique identifier for one schedule throughout a charging session.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffDescription` — string, optional, maxLength 32 — Sales_ Tariff. Sales. Tariff_ Description urn:x-oca:ocpp:uid:1:569283 A human readable title/short description of the sales tariff e.g. for HMI display purposes.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.numEPriceLevels` — integer, optional — Sales_ Tariff. Num_ E_ Price_ Levels. Counter urn:x-oca:ocpp:uid:1:569284 Defines the overall number of distinct price levels used across all provided SalesTariff elements.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry` — array, required, maxItems 1024, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval` — RelativeTimeIntervalType, required — Relative_ Timer_ Interval urn:x-oca:ocpp:uid:2:233270
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.start` — integer, required — Relative_ Timer_ Interval. Start. Elapsed_ Time urn:x-oca:ocpp:uid:1:569279 Start of the interval, in seconds from NOW.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].relativeTimeInterval.duration` — integer, optional — Relative_ Timer_ Interval. Duration. Elapsed_ Time urn:x-oca:ocpp:uid:1:569280 Duration of the interval, in seconds.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].ePriceLevel` — integer, optional, min 0.0 — Sales_ Tariff_ Entry. E_ Price_ Level. Unsigned_ Integer urn:x-oca:ocpp:uid:1:569281 Defines the price level of this SalesTariffEntry (referring to NumEPriceLevels). Small values for the EPriceLevel represent a cheaper TariffEntry. Large values for the EPriceLevel represent a more expensive TariffEntry.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost` — array, optional, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].startValue` — number, required — Consumption_ Cost. Start_ Value. Numeric urn:x-oca:ocpp:uid:1:569246 The lowest level of consumption that defines the starting point of this consumption block. The block interval extends to the start of the next interval.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost` — array, required, maxItems 3, minItems 1
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].costKind` — CostKindEnumType (string), required, enum: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amount` — integer, required — Cost. Amount. Amount urn:x-oca:ocpp:uid:1:569244 The estimated or actual cost per kWh
- `setChargingProfile.req.chargingProfile.chargingSchedule[].salesTariff.salesTariffEntry[].consumptionCost[].cost[].amountMultiplier` — integer, optional — Cost. Amount_ Multiplier. Integer urn:x-oca:ocpp:uid:1:569245 Values: -3..3, The amountMultiplier defines the exponent to base 10 (dec). The final value is determined by: amount * 10 ^ amountMultiplier
- `setChargingProfile.req.chargingProfile.transactionId` — string, optional, maxLength 36 — SHALL only be included if ChargingProfilePurpose is set to TxProfile. The transactionId is used to match the profile to a specific transaction.

### setChargingProfile response

- schema: [`SetChargingProfileResponse.json`](../../ocpp-2-0-json/src/main/resources/SetChargingProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.resp.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.resp.status` — ChargingProfileStatusEnumType (string), required, enum: Accepted | Rejected — Returns whether the Charging Station has been able to process the message successfully. This does not guarantee the schedule will be followed to the letter. There might be other constraints the Charging Station may need to take into account.
- `setChargingProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setChargingProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setChargingProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setChargingProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setChargingProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### setChargingProfile enumerations

- `setChargingProfile` `ChargingProfileKindEnumType`: Absolute | Recurring | Relative — Charging_ Profile. Charging_ Profile_ Kind. Charging_ Profile_ Kind_ Code urn:x-oca:ocpp:uid:1:569232 Indicates the kind of schedule.
- `setChargingProfile` `ChargingProfilePurposeEnumType`: ChargingStationExternalConstraints | ChargingStationMaxProfile | TxDefaultProfile | TxProfile — Charging_ Profile. Charging_ Profile_ Purpose. Charging_ Profile_ Purpose_ Code urn:x-oca:ocpp:uid:1:569231 Defines the purpose of the schedule transferred by this profile
- `setChargingProfile` `ChargingProfileStatusEnumType`: Accepted | Rejected — Returns whether the Charging Station has been able to process the message successfully. This does not guarantee the schedule will be followed to the letter. There might be other constraints the Charging Station may need to take into account.
- `setChargingProfile` `ChargingRateUnitEnumType`: W | A — Charging_ Schedule. Charging_ Rate_ Unit. Charging_ Rate_ Unit_ Code urn:x-oca:ocpp:uid:1:569238 The unit of measure Limit is expressed in.
- `setChargingProfile` `CostKindEnumType`: CarbonDioxideEmission | RelativePricePercentage | RenewableGenerationPercentage — Cost. Cost_ Kind. Cost_ Kind_ Code urn:x-oca:ocpp:uid:1:569243 The kind of cost referred to in the message element amount
- `setChargingProfile` `RecurrencyKindEnumType`: Daily | Weekly — Charging_ Profile. Recurrency_ Kind. Recurrency_ Kind_ Code urn:x-oca:ocpp:uid:1:569233 Indicates the start point of a recurrence.

## setDisplayMessage

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETDISPLAYMESSAGE`
- Kotlin `SetDisplayMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageReq.kt)
- Kotlin `SetDisplayMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setdisplaymessage/SetDisplayMessageResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §O01 Set DisplayMessage — pdf-page 331 (`grep -n 'pdf-page 331]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.52 SetDisplayMessage — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.52.1 SetDisplayMessageRequest — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.52.2 SetDisplayMessageResponse — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### setDisplayMessage request

- schema: [`SetDisplayMessageRequest.json`](../../ocpp-2-0-json/src/main/resources/SetDisplayMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setDisplayMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message` — MessageInfoType, required — Message_ Info urn:x-enexis:ecdm:uid:2:233264 Contains message details, for a message to be displayed on a Charging Station.
- `setDisplayMessage.req.message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display` — ComponentType, optional — A physical or logical component
- `setDisplayMessage.req.message.display.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.display.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setDisplayMessage.req.message.display.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.display.evse.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.display.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setDisplayMessage.req.message.display.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setDisplayMessage.req.message.display.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setDisplayMessage.req.message.display.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setDisplayMessage.req.message.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 Master resource identifier, unique within an exchange context. It is defined within the OCPP context as a positive Integer value (greater or equal to zero).
- `setDisplayMessage.req.message.priority` — MessagePriorityEnumType (string), required, enum: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `setDisplayMessage.req.message.state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.
- `setDisplayMessage.req.message.startDateTime` — string, optional, format date-time — Message_ Info. Start. Date_ Time urn:x-enexis:ecdm:uid:1:569256 From what date-time should this message be shown. If omitted: directly.
- `setDisplayMessage.req.message.endDateTime` — string, optional, format date-time — Message_ Info. End. Date_ Time urn:x-enexis:ecdm:uid:1:569257 Until what date-time should this message be shown, after this date/time this message SHALL be removed.
- `setDisplayMessage.req.message.transactionId` — string, optional, maxLength 36 — During which transaction shall this message be shown. Message SHALL be removed by the Charging Station after transaction has ended.
- `setDisplayMessage.req.message.message` — MessageContentType, required — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `setDisplayMessage.req.message.message.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.req.message.message.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.req.message.message.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `setDisplayMessage.req.message.message.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `setDisplayMessage.req.message.message.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.

### setDisplayMessage response

- schema: [`SetDisplayMessageResponse.json`](../../ocpp-2-0-json/src/main/resources/SetDisplayMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setDisplayMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.resp.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.resp.status` — DisplayMessageStatusEnumType (string), required, enum: Accepted | NotSupportedMessageFormat | Rejected | NotSupportedPriority | NotSupportedState | UnknownTransaction — This indicates whether the Charging Station is able to display the message.
- `setDisplayMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setDisplayMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setDisplayMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setDisplayMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setDisplayMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### setDisplayMessage enumerations

- `setDisplayMessage` `DisplayMessageStatusEnumType`: Accepted | NotSupportedMessageFormat | Rejected | NotSupportedPriority | NotSupportedState | UnknownTransaction — This indicates whether the Charging Station is able to display the message.
- `setDisplayMessage` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `setDisplayMessage` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — Message_ Info. Priority. Message_ Priority_ Code urn:x-enexis:ecdm:uid:1:569253 With what priority should this message be shown
- `setDisplayMessage` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — Message_ Info. State. Message_ State_ Code urn:x-enexis:ecdm:uid:1:569254 During what state should this message be shown. When omitted this message should be shown in any state of the Charging Station.

## setMonitoringBase

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETMONITORINGBASE`
- Kotlin `SetMonitoringBaseReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseReq.kt)
- Kotlin `SetMonitoringBaseResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringbase/SetMonitoringBaseResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §N03 Set Monitoring Base — pdf-page 315 (`grep -n 'pdf-page 315]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.53 SetMonitoringBase — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.53.1 SetMonitoringBaseRequest — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.53.2 SetMonitoringBaseResponse — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 108, 109 (`grep -n 'SetMonitoringBase' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### setMonitoringBase request

- schema: [`SetMonitoringBaseRequest.json`](../../ocpp-2-0-json/src/main/resources/SetMonitoringBaseRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringBase.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.req.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.req.monitoringBase` — MonitoringBaseEnumType (string), required, enum: All | FactoryDefault | HardWiredOnly — Specify which monitoring base will be set

### setMonitoringBase response

- schema: [`SetMonitoringBaseResponse.json`](../../ocpp-2-0-json/src/main/resources/SetMonitoringBaseResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringBase.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.resp.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.resp.status` — GenericDeviceModelStatusEnumType (string), required, enum: Accepted | Rejected | NotSupported | EmptyResultSet — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringBase.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setMonitoringBase.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringBase.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setMonitoringBase.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setMonitoringBase.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### setMonitoringBase enumerations

- `setMonitoringBase` `GenericDeviceModelStatusEnumType`: Accepted | Rejected | NotSupported | EmptyResultSet — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringBase` `MonitoringBaseEnumType`: All | FactoryDefault | HardWiredOnly — Specify which monitoring base will be set

## setMonitoringLevel

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETMONITORINGLEVEL`
- Kotlin `SetMonitoringLevelReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelReq.kt)
- Kotlin `SetMonitoringLevelResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setmonitoringlevel/SetMonitoringLevelResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §N05 Set Monitoring Level — pdf-page 319 (`grep -n 'pdf-page 319]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.54 SetMonitoringLevel — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.54.1 SetMonitoringLevelRequest — pdf-page 372 (`grep -n 'pdf-page 372]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.54.2 SetMonitoringLevelResponse — pdf-page 373 (`grep -n 'pdf-page 373]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec **(errata)**: `ocpp-2.0-part2-errata` §N05 Set Monitoring Level) — pdf-page 86 (`grep -n 'pdf-page 86]]' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### setMonitoringLevel request

- schema: [`SetMonitoringLevelRequest.json`](../../ocpp-2-0-json/src/main/resources/SetMonitoringLevelRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringLevel.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.req.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.req.severity` — integer, required — The Charging Station SHALL only report events with a severity number lower than or equal to this severity. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.

### setMonitoringLevel response

- schema: [`SetMonitoringLevelResponse.json`](../../ocpp-2-0-json/src/main/resources/SetMonitoringLevelResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setMonitoringLevel.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.resp.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Indicates whether the Charging Station was able to accept the request.
- `setMonitoringLevel.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setMonitoringLevel.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setMonitoringLevel.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setMonitoringLevel.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setMonitoringLevel.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### setMonitoringLevel enumerations

- `setMonitoringLevel` `GenericStatusEnumType`: Accepted | Rejected — Indicates whether the Charging Station was able to accept the request.

## setNetworkProfile

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETNETWORKPROFILE`
- Kotlin `SetNetworkProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileReq.kt)
- Kotlin `SetNetworkProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.55 SetNetworkProfile — pdf-page 373 (`grep -n 'pdf-page 373]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.55.1 SetNetworkProfileRequest — pdf-page 373 (`grep -n 'pdf-page 373]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.55.2 SetNetworkProfileResponse — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### setNetworkProfile request

- schema: [`SetNetworkProfileRequest.json`](../../ocpp-2-0-json/src/main/resources/SetNetworkProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setNetworkProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.configurationSlot` — integer, required — Slot in which the configuration should be stored.
- `setNetworkProfile.req.connectionData` — NetworkConnectionProfileType, required — Communication_ Function urn:x-oca:ocpp:uid:2:233304 The NetworkConnectionProfile defines the functional and technical parameters of a communication link.
- `setNetworkProfile.req.connectionData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.apn` — APNType, optional — APN urn:x-oca:ocpp:uid:2:233134 Collection of configuration data needed to make a data-connection over a cellular network. NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator should be used. This can be done with the mobile country code (MCC) in combination with a mobile network code (MNC). Example: If your preferred network is Vodafone Netherlands, the MCC=204 and the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to specify a preferred network, which means, if this network is not available, a different network is used. If you specify UseOnlyPreferredNetwork and this network is not available, the modem will not dial in.
- `setNetworkProfile.req.connectionData.apn.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.apn.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.apn.apn` — string, required, maxLength 512 — APN. APN. URI urn:x-oca:ocpp:uid:1:568814 The Access Point Name as an URL.
- `setNetworkProfile.req.connectionData.apn.apnUserName` — string, optional, maxLength 20 — APN. APN. User_ Name urn:x-oca:ocpp:uid:1:568818 APN username.
- `setNetworkProfile.req.connectionData.apn.apnPassword` — string, optional, maxLength 20 — APN. APN. Password urn:x-oca:ocpp:uid:1:568819 APN Password.
- `setNetworkProfile.req.connectionData.apn.simPin` — integer, optional — APN. SIMPIN. PIN_ Code urn:x-oca:ocpp:uid:1:568821 SIM card pin code.
- `setNetworkProfile.req.connectionData.apn.preferredNetwork` — string, optional, maxLength 6 — APN. Preferred_ Network. Mobile_ Network_ ID urn:x-oca:ocpp:uid:1:568822 Preferred network, written as MCC and MNC concatenated. See note.
- `setNetworkProfile.req.connectionData.apn.useOnlyPreferredNetwork` — boolean, optional — APN. Use_ Only_ Preferred_ Network. Indicator urn:x-oca:ocpp:uid:1:568824 Default: false. Use only the preferred Network, do not dial in when not available. See Note.
- `setNetworkProfile.req.connectionData.apn.apnAuthentication` — APNAuthenticationEnumType (string), required, enum: CHAP | NONE | PAP | AUTO — APN. APN_ Authentication. APN_ Authentication_ Code urn:x-oca:ocpp:uid:1:568828 Authentication method.
- `setNetworkProfile.req.connectionData.ocppVersion` — OCPPVersionEnumType (string), required, enum: OCPP12 | OCPP15 | OCPP16 | OCPP20 — Communication_ Function. OCPP_ Version. OCPP_ Version_ Code urn:x-oca:ocpp:uid:1:569355 Defines the OCPP version used for this communication function.
- `setNetworkProfile.req.connectionData.ocppTransport` — OCPPTransportEnumType (string), required, enum: JSON | SOAP — Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code urn:x-oca:ocpp:uid:1:569356 Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
- `setNetworkProfile.req.connectionData.ocppCsmsUrl` — string, required, maxLength 512 — Communication_ Function. OCPP_ Central_ System_ URL. URI urn:x-oca:ocpp:uid:1:569357 URL of the CSMS(s) that this Charging Station communicates with.
- `setNetworkProfile.req.connectionData.messageTimeout` — integer, required — Duration in seconds before a message send by the Charging Station via this network connection times-out. The best setting depends on the underlying network and response times of the CSMS. If you are looking for a some guideline: use 30 seconds as a starting point.
- `setNetworkProfile.req.connectionData.securityProfile` — integer, required — This field specifies the security profile used when connecting to the CSMS with this NetworkConnectionProfile.
- `setNetworkProfile.req.connectionData.ocppInterface` — OCPPInterfaceEnumType (string), required, enum: Wired0 | Wired1 | Wired2 | Wired3 | Wireless0 | Wireless1 | Wireless2 | Wireless3 — Applicable Network Interface.
- `setNetworkProfile.req.connectionData.vpn` — VPNType, optional — VPN urn:x-oca:ocpp:uid:2:233268 VPN Configuration settings
- `setNetworkProfile.req.connectionData.vpn.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.vpn.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.vpn.server` — string, required, maxLength 512 — VPN. Server. URI urn:x-oca:ocpp:uid:1:569272 VPN Server Address
- `setNetworkProfile.req.connectionData.vpn.user` — string, required, maxLength 20 — VPN. User. User_ Name urn:x-oca:ocpp:uid:1:569273 VPN User
- `setNetworkProfile.req.connectionData.vpn.group` — string, optional, maxLength 20 — VPN. Group. Group_ Name urn:x-oca:ocpp:uid:1:569274 VPN group.
- `setNetworkProfile.req.connectionData.vpn.password` — string, required, maxLength 20 — VPN. Password. Password urn:x-oca:ocpp:uid:1:569275 VPN Password.
- `setNetworkProfile.req.connectionData.vpn.key` — string, required, maxLength 255 — VPN. Key. VPN_ Key urn:x-oca:ocpp:uid:1:569276 VPN shared secret.
- `setNetworkProfile.req.connectionData.vpn.type` — VPNEnumType (string), required, enum: IKEv2 | IPSec | L2TP | PPTP — VPN. Type. VPN_ Code urn:x-oca:ocpp:uid:1:569277 Type of VPN

### setNetworkProfile response

- schema: [`SetNetworkProfileResponse.json`](../../ocpp-2-0-json/src/main/resources/SetNetworkProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setNetworkProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.resp.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.resp.status` — SetNetworkProfileStatusEnumType (string), required, enum: Accepted | Rejected | Failed — Result of operation.
- `setNetworkProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setNetworkProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setNetworkProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### setNetworkProfile enumerations

- `setNetworkProfile` `APNAuthenticationEnumType`: CHAP | NONE | PAP | AUTO — APN. APN_ Authentication. APN_ Authentication_ Code urn:x-oca:ocpp:uid:1:568828 Authentication method.
- `setNetworkProfile` `OCPPInterfaceEnumType`: Wired0 | Wired1 | Wired2 | Wired3 | Wireless0 | Wireless1 | Wireless2 | Wireless3 — Applicable Network Interface.
- `setNetworkProfile` `OCPPTransportEnumType`: JSON | SOAP — Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code urn:x-oca:ocpp:uid:1:569356 Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
- `setNetworkProfile` `OCPPVersionEnumType`: OCPP12 | OCPP15 | OCPP16 | OCPP20 — Communication_ Function. OCPP_ Version. OCPP_ Version_ Code urn:x-oca:ocpp:uid:1:569355 Defines the OCPP version used for this communication function.
- `setNetworkProfile` `SetNetworkProfileStatusEnumType`: Accepted | Rejected | Failed — Result of operation.
- `setNetworkProfile` `VPNEnumType`: IKEv2 | IPSec | L2TP | PPTP — VPN. Type. VPN_ Code urn:x-oca:ocpp:uid:1:569277 Type of VPN

## setVariableMonitoring

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETVARIABLEMONITORING`
- Kotlin `SetVariableMonitoringReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringReq.kt)
- Kotlin `SetVariableMonitoringResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariablemonitoring/SetVariableMonitoringResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §N04 Set Variable Monitoring — pdf-page 316 (`grep -n 'pdf-page 316]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.56 SetVariableMonitoring — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.56.1 SetVariableMonitoringRequest — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.56.2 SetVariableMonitoringResponse — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 72 (`grep -n 'SetVariableMonitoring' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### setVariableMonitoring request

- schema: [`SetVariableMonitoringRequest.json`](../../ocpp-2-0-json/src/main/resources/SetVariableMonitoringRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariableMonitoring.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData` — array, required, minItems 1
- `setVariableMonitoring.req.setMonitoringData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].id` — integer, optional — An id SHALL only be given to replace an existing monitor. The Charging Station handles the generation of id's for new monitors.
- `setVariableMonitoring.req.setMonitoringData[].transaction` — boolean, optional — Monitor only active when a transaction is ongoing on a component relevant to this transaction. Default = false.
- `setVariableMonitoring.req.setMonitoringData[].value` — number, required — Value for threshold or delta monitoring. For Periodic or PeriodicClockAligned this is the interval in seconds.
- `setVariableMonitoring.req.setMonitoringData[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring.req.setMonitoringData[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.
- `setVariableMonitoring.req.setMonitoringData[].component` — ComponentType, required — A physical or logical component
- `setVariableMonitoring.req.setMonitoringData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].component.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariableMonitoring.req.setMonitoringData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariableMonitoring.req.setMonitoringData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariableMonitoring.req.setMonitoringData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariableMonitoring.req.setMonitoringData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.req.setMonitoringData[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.req.setMonitoringData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.req.setMonitoringData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### setVariableMonitoring response

- schema: [`SetVariableMonitoringResponse.json`](../../ocpp-2-0-json/src/main/resources/SetVariableMonitoringResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariableMonitoring.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult` — array, required, minItems 1
- `setVariableMonitoring.resp.setMonitoringResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].id` — integer, optional — Id given to the VariableMonitor by the Charging Station. The Id is only returned when status is accepted. Installed VariableMonitors should have unique id's but the id's of removed Installed monitors should have unique id's but the id's of removed monitors MAY be reused.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setVariableMonitoring.resp.setMonitoringResult[].statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `setVariableMonitoring.resp.setMonitoringResult[].status` — SetMonitoringStatusEnumType (string), required, enum: Accepted | UnknownComponent | UnknownVariable | UnsupportedMonitorType | Rejected | Duplicate — Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.
- `setVariableMonitoring.resp.setMonitoringResult[].type` — MonitorEnumType (string), required, enum: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring.resp.setMonitoringResult[].component` — ComponentType, required — A physical or logical component
- `setVariableMonitoring.resp.setMonitoringResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].component.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariableMonitoring.resp.setMonitoringResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariableMonitoring.resp.setMonitoringResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariableMonitoring.resp.setMonitoringResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariableMonitoring.resp.setMonitoringResult[].severity` — integer, required — The severity that will be assigned to an event that is triggered by this monitor. The severity range is 0-9, with 0 as the highest and 9 as the lowest severity level. The severity levels have the following meaning: + *0-Danger* + Indicates lives are potentially in danger. Urgent attention is needed and action should be taken immediately. + *1-Hardware Failure* + Indicates that the Charging Station is unable to continue regular operations due to Hardware issues. Action is required. + *2-System Failure* + Indicates that the Charging Station is unable to continue regular operations due to software or minor hardware issues. Action is required. + *3-Critical* + Indicates a critical error. Action is required. + *4-Error* + Indicates a non-urgent error. Action is required. + *5-Alert* + Indicates an alert event. Default severity for any type of monitoring event. + *6-Warning* + Indicates a warning event. Action may be required. + *7-Notice* + Indicates an unusual event. No immediate action is required. + *8-Informational* + Indicates a regular operational event. May be used for reporting, measuring throughput, etc. No action is required. + *9-Debug* + Indicates information useful to developers for debugging, not useful during operations.

#### setVariableMonitoring enumerations

- `setVariableMonitoring` `MonitorEnumType`: UpperThreshold | LowerThreshold | Delta | Periodic | PeriodicClockAligned — The type of this monitor, e.g. a threshold, delta or periodic monitor.
- `setVariableMonitoring` `SetMonitoringStatusEnumType`: Accepted | UnknownComponent | UnknownVariable | UnsupportedMonitorType | Rejected | Duplicate — Status is OK if a value could be returned. Otherwise this will indicate the reason why a value could not be returned.

## setVariables

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETVARIABLES`
- Kotlin `SetVariablesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesReq.kt)
- Kotlin `SetVariablesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setvariables/SetVariablesResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §B05 Set Variables — pdf-page 62 (`grep -n 'pdf-page 62]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.57 SetVariables — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.57.1 SetVariablesRequest — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.57.2 SetVariablesResponse — pdf-page 374 (`grep -n 'pdf-page 374]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 14, 70 (`grep -n 'SetVariables' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)

### setVariables request

- schema: [`SetVariablesRequest.json`](../../ocpp-2-0-json/src/main/resources/SetVariablesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariables.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData` — array, required, minItems 1
- `setVariables.req.setVariableData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables.req.setVariableData[].attributeValue` — string, required, maxLength 1000 — Value to be assigned to attribute of variable. The Configuration Variable &lt;&lt;configkey-configuration-value-size,ConfigurationValueSize&gt;&gt; can be used to limit SetVariableData.attributeValue and VariableCharacteristics.valueList. The max size of these values will always remain equal.
- `setVariables.req.setVariableData[].component` — ComponentType, required — A physical or logical component
- `setVariables.req.setVariableData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].component.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariables.req.setVariableData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariables.req.setVariableData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariables.req.setVariableData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariables.req.setVariableData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.req.setVariableData[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariables.req.setVariableData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.req.setVariableData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

### setVariables response

- schema: [`SetVariablesResponse.json`](../../ocpp-2-0-json/src/main/resources/SetVariablesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setVariables.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult` — array, required, minItems 1
- `setVariables.resp.setVariableResult[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].attributeType` — AttributeEnumType (string), optional, enum: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables.resp.setVariableResult[].attributeStatus` — SetVariableStatusEnumType (string), required, enum: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType | RebootRequired — Result status of setting the variable.
- `setVariables.resp.setVariableResult[].attributeStatusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].attributeStatusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setVariables.resp.setVariableResult[].attributeStatusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.
- `setVariables.resp.setVariableResult[].component` — ComponentType, required — A physical or logical component
- `setVariables.resp.setVariableResult[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].component.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `setVariables.resp.setVariableResult[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].component.evse.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `setVariables.resp.setVariableResult[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `setVariables.resp.setVariableResult[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].variable` — VariableType, required — Reference key to a component-variable.
- `setVariables.resp.setVariableResult[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setVariables.resp.setVariableResult[].variable.customData.vendorId` — string, required, maxLength 255
- `setVariables.resp.setVariableResult[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `setVariables.resp.setVariableResult[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

#### setVariables enumerations

- `setVariables` `AttributeEnumType`: Actual | Target | MinSet | MaxSet — Type of attribute: Actual, Target, MinSet, MaxSet. Default is Actual when omitted.
- `setVariables` `SetVariableStatusEnumType`: Accepted | Rejected | UnknownComponent | UnknownVariable | NotSupportedAttributeType | RebootRequired — Result status of setting the variable.

## signCertificate

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNCERTIFICATE`
- Kotlin `SignCertificateReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateReq.kt)
- Kotlin `SignCertificateResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/signcertificate/SignCertificateResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.58 SignCertificate — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.58.1 SignCertificateRequest — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.58.2 SignCertificateResponse — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 108 (`grep -n 'SignCertificate' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### signCertificate request

- schema: [`SignCertificateRequest.json`](../../ocpp-2-0-json/src/main/resources/SignCertificateRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.req.customData.vendorId` — string, required, maxLength 255
- `signCertificate.req.csr` — string, required, maxLength 5500 — The Charging Station SHALL send the public key in form of a Certificate Signing Request (CSR) as described in RFC 2986 [22] and then PEM encoded, using the &lt;&lt;signcertificaterequest,SignCertificateRequest&gt;&gt; message.
- `signCertificate.req.certificateType` — CertificateSigningUseEnumType (string), optional, enum: ChargingStationCertificate | V2GCertificate — Indicates the type of certificate that is to be signed. When omitted the certificate is to be used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.

### signCertificate response

- schema: [`SignCertificateResponse.json`](../../ocpp-2-0-json/src/main/resources/SignCertificateResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.resp.customData.vendorId` — string, required, maxLength 255
- `signCertificate.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected — Specifies whether the CSMS can process the request.
- `signCertificate.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `signCertificate.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `signCertificate.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `signCertificate.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `signCertificate.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### signCertificate enumerations

- `signCertificate` `CertificateSigningUseEnumType`: ChargingStationCertificate | V2GCertificate — Indicates the type of certificate that is to be signed. When omitted the certificate is to be used for both the 15118 connection (if implemented) and the Charging Station to CSMS connection.
- `signCertificate` `GenericStatusEnumType`: Accepted | Rejected — Specifies whether the CSMS can process the request.

## statusNotification

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/statusnotification/StatusNotificationResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §G01 Status Notification — pdf-page 194 (`grep -n 'pdf-page 194]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.59 StatusNotification — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.59.1 StatusNotificationRequest — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.59.2 StatusNotificationResponse — pdf-page 375 (`grep -n 'pdf-page 375]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 16, 26, 28, 69 (`grep -n 'StatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 118 (`grep -n 'StatusNotification' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### statusNotification request

- schema: [`StatusNotificationRequest.json`](../../ocpp-2-0-json/src/main/resources/StatusNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `statusNotification.req.customData.vendorId` — string, required, maxLength 255
- `statusNotification.req.timestamp` — string, required, format date-time — The time for which the status is reported. If absent time of receipt of the message will be assumed.
- `statusNotification.req.connectorStatus` — ConnectorStatusEnumType (string), required, enum: Available | Occupied | Reserved | Unavailable | Faulted — This contains the current status of the Connector.
- `statusNotification.req.evseId` — integer, required — The id of the EVSE to which the connector belongs for which the the status is reported.
- `statusNotification.req.connectorId` — integer, required — The id of the connector within the EVSE for which the status is reported.

### statusNotification response

- schema: [`StatusNotificationResponse.json`](../../ocpp-2-0-json/src/main/resources/StatusNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `statusNotification.resp.customData.vendorId` — string, required, maxLength 255

#### statusNotification enumerations

- `statusNotification` `ConnectorStatusEnumType`: Available | Occupied | Reserved | Unavailable | Faulted — This contains the current status of the Connector.

## transactionEvent

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.TRANSACTIONEVENT`
- Kotlin `TransactionEventReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventReq.kt)
- Kotlin `TransactionEventResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1 TransactionEventRequest — pdf-page 136 (`grep -n 'pdf-page 136]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §4 TransactionEventRequest — pdf-page 242 (`grep -n 'pdf-page 242]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.60 TransactionEvent — pdf-page 376 (`grep -n 'pdf-page 376]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.60.1 TransactionEventRequest — pdf-page 376 (`grep -n 'pdf-page 376]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.60.2 TransactionEventResponse — pdf-page 376 (`grep -n 'pdf-page 376]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §3.80 TransactionEventEnumType — pdf-page 424 (`grep -n 'pdf-page 424]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 3, 17, 19, 26, 28, 31, 72 (`grep -n 'TransactionEvent' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 66 (`grep -n 'TransactionEvent' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### transactionEvent request

- schema: [`TransactionEventRequest.json`](../../ocpp-2-0-json/src/main/resources/TransactionEventRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `transactionEvent.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.eventType` — TransactionEventEnumType (string), required, enum: Ended | Started | Updated — This contains the type of this event. The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
- `transactionEvent.req.meterValue` — array, optional, minItems 1
- `transactionEvent.req.meterValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue` — array, required, minItems 1
- `transactionEvent.req.meterValue[].sampledValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].value` — number, required — Sampled_ Value. Value. Measure urn:x-oca:ocpp:uid:1:569260 Indicates the measured value.
- `transactionEvent.req.meterValue[].sampledValue[].context` — ReadingContextEnumType (string), optional, enum: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `transactionEvent.req.meterValue[].sampledValue[].measurand` — MeasurandEnumType (string), optional, enum: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `transactionEvent.req.meterValue[].sampledValue[].phase` — PhaseEnumType (string), optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `transactionEvent.req.meterValue[].sampledValue[].location` — LocationEnumType (string), optional, enum: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue` — SignedMeterValueType, optional — Represent a signed version of the meter value.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.signedMeterData` — string, required, maxLength 2500 — Base64 encoded, contains the signed data which might contain more then just the meter value. It can contain information like timestamps, reference to a customer etc.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.signingMethod` — string, required, maxLength 50 — Method used to create the digital signature.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.encodingMethod` — string, required, maxLength 50 — Method used to encode the meter values before applying the digital signature algorithm.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.publicKey` — string, required, maxLength 2500 — Base64 encoded, sending depends on configuration variable _PublicKeyWithSignedMeterValue_.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure` — UnitOfMeasureType, optional — Represents a UnitOfMeasure with a multiplier
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.unit` — string, optional, maxLength 20 — Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices. If an applicable unit is available in that list, otherwise a "custom" unit might be used.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.multiplier` — integer, optional — Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to the 3rd power. Default is 0.
- `transactionEvent.req.meterValue[].timestamp` — string, required, format date-time — Meter_ Value. Timestamp. Date_ Time urn:x-oca:ocpp:uid:1:569259 Timestamp for measured value(s).
- `transactionEvent.req.timestamp` — string, required, format date-time — The date and time at which this transaction event occurred.
- `transactionEvent.req.triggerReason` — TriggerReasonEnumType (string), required, enum: Authorized | CablePluggedIn | ChargingRateChanged | ChargingStateChanged | Deauthorized | EnergyLimitReached | EVCommunicationLost | EVConnectTimeout | MeterValueClock | MeterValuePeriodic | TimeLimitReached | Trigger | UnlockCommand | StopAuthorized | EVDeparted | EVDetected | RemoteStop | RemoteStart | AbnormalCondition | SignedDataReceived | ResetCommand — Reason the Charging Station sends this message to the CSMS
- `transactionEvent.req.seqNo` — integer, required — Incremental sequence number, helps with determining if all messages of a transaction have been received.
- `transactionEvent.req.offline` — boolean, optional — Indication that this transaction event happened when the Charging Station was offline. Default = false, meaning: the event occurred when the Charging Station was online.
- `transactionEvent.req.numberOfPhasesUsed` — integer, optional — If the Charging Station is able to report the number of phases used, then it SHALL provide it. When omitted the CSMS may be able to determine the number of phases used via device management.
- `transactionEvent.req.cableMaxCurrent` — integer, optional — The maximum current of the connected cable in Ampere (A).
- `transactionEvent.req.reservationId` — integer, optional — This contains the Id of the reservation that terminates as a result of this transaction.
- `transactionEvent.req.transactionInfo` — TransactionType, required — Transaction urn:x-oca:ocpp:uid:2:233318
- `transactionEvent.req.transactionInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.transactionInfo.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.transactionInfo.transactionId` — string, required, maxLength 36 — This contains the Id of the transaction.
- `transactionEvent.req.transactionInfo.chargingState` — ChargingStateEnumType (string), optional, enum: Charging | EVConnected | SuspendedEV | SuspendedEVSE | Idle — Transaction. State. Transaction_ State_ Code urn:x-oca:ocpp:uid:1:569419 Current charging state, is required when state has changed.
- `transactionEvent.req.transactionInfo.timeSpentCharging` — integer, optional — Transaction. Time_ Spent_ Charging. Elapsed_ Time urn:x-oca:ocpp:uid:1:569415 Contains the total time that energy flowed from EVSE to EV during the transaction (in seconds). Note that timeSpentCharging is smaller or equal to the duration of the transaction.
- `transactionEvent.req.transactionInfo.stoppedReason` — ReasonEnumType (string), optional, enum: DeAuthorized | EmergencyStop | EnergyLimitReached | EVDisconnected | GroundFault | ImmediateReset | Local | LocalOutOfCredit | MasterPass | Other | OvercurrentFault | PowerLoss | PowerQuality | Reboot | Remote | SOCLimitReached | StoppedByEV | TimeLimitReached | Timeout — Transaction. Stopped_ Reason. EOT_ Reason_ Code urn:x-oca:ocpp:uid:1:569413 This contains the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
- `transactionEvent.req.transactionInfo.remoteStartId` — integer, optional — The ID given to remote start request (&lt;&lt;requeststarttransactionrequest, RequestStartTransactionRequest&gt;&gt;. This enables to CSMS to match the started transaction to the given start request.
- `transactionEvent.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `transactionEvent.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.evse.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `transactionEvent.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `transactionEvent.req.idToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `transactionEvent.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.idToken.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.idToken.additionalInfo` — array, optional, minItems 1
- `transactionEvent.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `transactionEvent.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `transactionEvent.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `transactionEvent.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

### transactionEvent response

- schema: [`TransactionEventResponse.json`](../../ocpp-2-0-json/src/main/resources/TransactionEventResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `transactionEvent.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.totalCost` — number, optional — SHALL only be sent when charging has ended. Final total cost of this transaction, including taxes. In the currency configured with the Configuration Variable: &lt;&lt;configkey-currency,`Currency`&gt;&gt;. When omitted, the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send 0.00.
- `transactionEvent.resp.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; is temporarily, so it may not be set in the &lt;&lt;cmn_idtokeninfotype,IdTokenInfoType&gt;&gt; afterwards. Also the chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules the one in &lt;&lt;cmn_idtokeninfotype,IdTokenInfoType&gt;&gt;.
- `transactionEvent.resp.idTokenInfo` — IdTokenInfoType, optional — ID_ Token urn:x-oca:ocpp:uid:2:233247 Contains status information about an identifier. It is advised to not stop charging for a token that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not given, the status has no end date.
- `transactionEvent.resp.idTokenInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.status` — AuthorizationStatusEnumType (string), required, enum: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `transactionEvent.resp.idTokenInfo.cacheExpiryDateTime` — string, optional, format date-time — ID_ Token. Expiry. Date_ Time urn:x-oca:ocpp:uid:1:569373 Date and Time after which the token must be considered invalid.
- `transactionEvent.resp.idTokenInfo.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules this one.
- `transactionEvent.resp.idTokenInfo.language1` — string, optional, maxLength 8 — ID_ Token. Language1. Language_ Code urn:x-oca:ocpp:uid:1:569374 Preferred user interface language of identifier user. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.evseId` — array, optional, minItems 1 — Only used when the IdToken is only valid for one or more specific EVSEs, not for the entire Charging Station.
- `transactionEvent.resp.idTokenInfo.evseId[]` — integer
- `transactionEvent.resp.idTokenInfo.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `transactionEvent.resp.idTokenInfo.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo` — array, optional, minItems 1
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `transactionEvent.resp.idTokenInfo.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `transactionEvent.resp.idTokenInfo.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `transactionEvent.resp.idTokenInfo.language2` — string, optional, maxLength 8 — ID_ Token. Language2. Language_ Code urn:x-oca:ocpp:uid:1:569375 Second preferred user interface language of identifier user. Don’t use when language1 is omitted, has to be different from language1. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.personalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `transactionEvent.resp.idTokenInfo.personalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.personalMessage.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.personalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent.resp.idTokenInfo.personalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.personalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `transactionEvent.resp.updatedPersonalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `transactionEvent.resp.updatedPersonalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.updatedPersonalMessage.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.updatedPersonalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent.resp.updatedPersonalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.updatedPersonalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.

#### transactionEvent enumerations

- `transactionEvent` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `transactionEvent` `ChargingStateEnumType`: Charging | EVConnected | SuspendedEV | SuspendedEVSE | Idle — Transaction. State. Transaction_ State_ Code urn:x-oca:ocpp:uid:1:569419 Current charging state, is required when state has changed.
- `transactionEvent` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `transactionEvent` `LocationEnumType`: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `transactionEvent` `MeasurandEnumType`: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `transactionEvent` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent` `PhaseEnumType`: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `transactionEvent` `ReadingContextEnumType`: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `transactionEvent` `ReasonEnumType`: DeAuthorized | EmergencyStop | EnergyLimitReached | EVDisconnected | GroundFault | ImmediateReset | Local | LocalOutOfCredit | MasterPass | Other | OvercurrentFault | PowerLoss | PowerQuality | Reboot | Remote | SOCLimitReached | StoppedByEV | TimeLimitReached | Timeout — Transaction. Stopped_ Reason. EOT_ Reason_ Code urn:x-oca:ocpp:uid:1:569413 This contains the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
- `transactionEvent` `TransactionEventEnumType`: Ended | Started | Updated — This contains the type of this event. The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
- `transactionEvent` `TriggerReasonEnumType`: Authorized | CablePluggedIn | ChargingRateChanged | ChargingStateChanged | Deauthorized | EnergyLimitReached | EVCommunicationLost | EVConnectTimeout | MeterValueClock | MeterValuePeriodic | TimeLimitReached | Trigger | UnlockCommand | StopAuthorized | EVDeparted | EVDetected | RemoteStop | RemoteStart | AbnormalCondition | SignedDataReceived | ResetCommand — Reason the Charging Station sends this message to the CSMS

## triggerMessage

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.TRIGGERMESSAGE`
- Kotlin `TriggerMessageReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageReq.kt)
- Kotlin `TriggerMessageResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/triggermessage/TriggerMessageResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §F06 Trigger Message — pdf-page 189 (`grep -n 'pdf-page 189]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.61 TriggerMessage — pdf-page 377 (`grep -n 'pdf-page 377]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.61.1 TriggerMessageRequest — pdf-page 377 (`grep -n 'pdf-page 377]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.61.2 TriggerMessageResponse — pdf-page 377 (`grep -n 'pdf-page 377]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'TriggerMessage' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 48 (`grep -n 'TriggerMessage' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### triggerMessage request

- schema: [`TriggerMessageRequest.json`](../../ocpp-2-0-json/src/main/resources/TriggerMessageRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.req.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `triggerMessage.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.req.evse.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `triggerMessage.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `triggerMessage.req.requestedMessage` — MessageTriggerEnumType (string), required, enum: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargingStationCertificate | SignV2GCertificate | StatusNotification | TransactionEvent | SignCombinedCertificate | PublishFirmwareStatusNotification — Type of message to be triggered.

### triggerMessage response

- schema: [`TriggerMessageResponse.json`](../../ocpp-2-0-json/src/main/resources/TriggerMessageResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.resp.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.resp.status` — TriggerMessageStatusEnumType (string), required, enum: Accepted | Rejected | NotImplemented — Indicates whether the Charging Station will send the requested notification or not.
- `triggerMessage.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `triggerMessage.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `triggerMessage.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `triggerMessage.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `triggerMessage.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### triggerMessage enumerations

- `triggerMessage` `MessageTriggerEnumType`: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargingStationCertificate | SignV2GCertificate | StatusNotification | TransactionEvent | SignCombinedCertificate | PublishFirmwareStatusNotification — Type of message to be triggered.
- `triggerMessage` `TriggerMessageStatusEnumType`: Accepted | Rejected | NotImplemented — Indicates whether the Charging Station will send the requested notification or not.

## unlockConnector

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §2.2 Unlock Connector — pdf-page 187 (`grep -n 'pdf-page 187]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.62 UnlockConnector — pdf-page 377 (`grep -n 'pdf-page 377]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.62.1 UnlockConnectorRequest — pdf-page 377 (`grep -n 'pdf-page 377]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.62.2 UnlockConnectorResponse — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### unlockConnector request

- schema: [`UnlockConnectorRequest.json`](../../ocpp-2-0-json/src/main/resources/UnlockConnectorRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.req.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.req.evseId` — integer, required — This contains the identifier of the EVSE for which a connector needs to be unlocked.
- `unlockConnector.req.connectorId` — integer, required — This contains the identifier of the connector that needs to be unlocked.

### unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../ocpp-2-0-json/src/main/resources/UnlockConnectorResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.resp.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.resp.status` — UnlockStatusEnumType (string), required, enum: Unlocked | UnlockFailed | OngoingAuthorizedTransaction | UnknownConnector — This indicates whether the Charging Station has unlocked the connector.
- `unlockConnector.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `unlockConnector.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `unlockConnector.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### unlockConnector enumerations

- `unlockConnector` `UnlockStatusEnumType`: Unlocked | UnlockFailed | OngoingAuthorizedTransaction | UnknownConnector — This indicates whether the Charging Station has unlocked the connector.

## unpublishFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNPUBLISHFIRMWARE`
- Kotlin `UnpublishFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareReq.kt)
- Kotlin `UnpublishFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unpublishfirmware/UnpublishFirmwareResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.63 UnpublishFirmware — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.63.1 UnpublishFirmwareRequest — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.63.2 UnpublishFirmwareResponse — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)

### unpublishFirmware request

- schema: [`UnpublishFirmwareRequest.json`](../../ocpp-2-0-json/src/main/resources/UnpublishFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unpublishFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unpublishFirmware.req.customData.vendorId` — string, required, maxLength 255
- `unpublishFirmware.req.checksum` — string, required, maxLength 32 — The MD5 checksum over the entire firmware file as a hexadecimal string of length 32.

### unpublishFirmware response

- schema: [`UnpublishFirmwareResponse.json`](../../ocpp-2-0-json/src/main/resources/UnpublishFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unpublishFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unpublishFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `unpublishFirmware.resp.status` — UnpublishFirmwareStatusEnumType (string), required, enum: DownloadOngoing | NoFirmware | Unpublished — Indicates whether the Local Controller succeeded in unpublishing the firmware.

#### unpublishFirmware enumerations

- `unpublishFirmware` `UnpublishFirmwareStatusEnumType`: DownloadOngoing | NoFirmware | Unpublished — Indicates whether the Local Controller succeeded in unpublishing the firmware.

## updateFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareReq.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareResp.kt`](../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: `ocpp-2.0.1-part2-specification` §1.64 UpdateFirmware — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.64.1 UpdateFirmwareRequest — pdf-page 378 (`grep -n 'pdf-page 378]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- spec: `ocpp-2.0.1-part2-specification` §1.64.2 UpdateFirmwareResponse — pdf-page 379 (`grep -n 'pdf-page 379]]' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-specification.txt`)
- errata mentions: `ocpp-2.0.1-part2-errata` pdf-page 7 (`grep -n 'UpdateFirmware' docs/protocol/spec/2.0.1/ocpp-2.0.1-part2-errata.txt`)
- errata mentions: `ocpp-2.0-part2-errata` pdf-page 74 (`grep -n 'UpdateFirmware' docs/protocol/spec/2.0.1/ocpp-2.0-part2-errata.txt`)

### updateFirmware request

- schema: [`UpdateFirmwareRequest.json`](../../ocpp-2-0-json/src/main/resources/UpdateFirmwareRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.req.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.req.retries` — integer, optional — This specifies how many times Charging Station must try to download the firmware before giving up. If this field is not present, it is left to Charging Station to decide how many times it wants to retry.
- `updateFirmware.req.retryInterval` — integer, optional — The interval in seconds after which a retry may be attempted. If this field is not present, it is left to Charging Station to decide how long to wait between attempts.
- `updateFirmware.req.requestId` — integer, required — The Id of this request
- `updateFirmware.req.firmware` — FirmwareType, required — Firmware urn:x-enexis:ecdm:uid:2:233291 Represents a copy of the firmware that can be loaded/updated on the Charging Station.
- `updateFirmware.req.firmware.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.req.firmware.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.req.firmware.location` — string, required, maxLength 512 — Firmware. Location. URI urn:x-enexis:ecdm:uid:1:569460 URI defining the origin of the firmware.
- `updateFirmware.req.firmware.retrieveDateTime` — string, required, format date-time — Firmware. Retrieve. Date_ Time urn:x-enexis:ecdm:uid:1:569461 Date and time at which the firmware shall be retrieved.
- `updateFirmware.req.firmware.installDateTime` — string, optional, format date-time — Firmware. Install. Date_ Time urn:x-enexis:ecdm:uid:1:569462 Date and time at which the firmware shall be installed.
- `updateFirmware.req.firmware.signingCertificate` — string, optional, maxLength 5500 — Certificate with which the firmware was signed. PEM encoded X.509 certificate.
- `updateFirmware.req.firmware.signature` — string, optional, maxLength 800 — Firmware. Signature. Signature urn:x-enexis:ecdm:uid:1:569464 Base64 encoded firmware signature.

### updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../ocpp-2-0-json/src/main/resources/UpdateFirmwareResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.resp.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.resp.status` — UpdateFirmwareStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate — This field indicates whether the Charging Station was able to accept the request.
- `updateFirmware.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `updateFirmware.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `updateFirmware.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `updateFirmware.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `updateFirmware.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

#### updateFirmware enumerations

- `updateFirmware` `UpdateFirmwareStatusEnumType`: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate — This field indicates whether the Charging Station was able to accept the request.

## Schema coverage

- 126 of 126 shipped schema files are reachable from `Actions`.
- **2 actions missing a schema:**
  - get15118EVCertificate request: expected `ocpp-2-0-json/src/main/resources/Get15118EVCertificateRequest.json`, not found
  - get15118EVCertificate response: expected `ocpp-2-0-json/src/main/resources/Get15118EVCertificateResponse.json`, not found

## Declared JSON Schema draft

`ocpp-2-0-json`'s parser validates every payload with `SpecVersion.VersionFlag.V6`. What the files themselves declare:

- `http://json-schema.org/draft-06/schema#` — 126 file(s)

