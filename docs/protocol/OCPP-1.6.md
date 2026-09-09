# OCPP 1.6 — protocol reference

**Derived from the official OCPP JSON schemas, the version's `Actions` registry and the OCA
specification documents.** Maintained by hand: an action added to `Actions` or a schema change
belongs in this file in the same commit.

Derived from the official OCPP JSON schemas in `ocpp-1-6-json/src/main/resources/` and the action registry in `ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/common/enumeration/Actions.kt`. For the normative prose, see [SPECS.md](SPECS.md).

Every field is one line, keyed by its dotted JSON path, so a grep for a field name lands on a line that names its action, direction and constraints:

```bash
grep -n 'idTag' docs/protocol/OCPP-1.6.md
```

39 actions registered.

## Specification documents

Each action below cites the section and PDF page of the normative document. Page numbers are
PDF page positions in these documents, which OCA distributes at
<https://www.openchargealliance.org/downloads/>:

| document | role | pages |
|---|---|--:|
| OCPP 1.6 edition 2 (FINAL, 2017-09-28) | spec | 116 |
| OCPP 1.6 Security Whitepaper | spec | 67 |
| OCPP-J 1.6 (JSON over WebSocket) | transport | 22 |
| OCPP-S 1.6 (SOAP) | transport | 14 |
| OCPP 1.6 errata sheet | errata | 46 |
| OCPP-J 1.6 errata sheet | errata | 13 |
| OCPP-S 1.6 errata sheet | errata | 4 |

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| `authorize` | Charging Station -> CSMS | `AuthorizeRequest.json` | `AuthorizeReq` / `AuthorizeResp` |
| `bootNotification` | Charging Station -> CSMS | `BootNotificationRequest.json` | `BootNotificationReq` / `BootNotificationResp` |
| `cancelReservation` | CSMS -> Charging Station | `CancelReservationRequest.json` | `CancelReservationReq` / `CancelReservationResp` |
| `certificateSigned` | CSMS -> Charging Station | `CertificateSignedRequest.json` | `CertificateSignedReq` / `CertificateSignedResp` |
| `changeAvailability` | CSMS -> Charging Station | `ChangeAvailabilityRequest.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| `changeConfiguration` | CSMS -> Charging Station | `ChangeConfigurationRequest.json` | `ChangeConfigurationReq` / `ChangeConfigurationResp` |
| `clearCache` | CSMS -> Charging Station | `ClearCacheRequest.json` | `ClearCacheReq` / `ClearCacheResp` |
| `clearChargingProfile` | CSMS -> Charging Station | `ClearChargingProfileRequest.json` | `ClearChargingProfileReq` / `ClearChargingProfileResp` |
| `dataTransfer` | either side | `DataTransferRequest.json` | `DataTransferReq` / `DataTransferResp` |
| `deleteCertificate` | CSMS -> Charging Station | `DeleteCertificateRequest.json` | `DeleteCertificateReq` / `DeleteCertificateResp` |
| `diagnosticsStatusNotification` | Charging Station -> CSMS | `DiagnosticsStatusNotificationRequest.json` | `DiagnosticsStatusNotificationReq` / `DiagnosticsStatusNotificationResp` |
| `extendedTriggerMessage` | CSMS -> Charging Station | `ExtendedTriggerMessageRequest.json` | `ExtendedTriggerMessageReq` / `ExtendedTriggerMessageResp` |
| `firmwareStatusNotification` | Charging Station -> CSMS | `FirmwareStatusNotificationRequest.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| `getCompositeSchedule` | CSMS -> Charging Station | `GetCompositeScheduleRequest.json` | `GetCompositeScheduleReq` / `GetCompositeScheduleResp` |
| `getConfiguration` | CSMS -> Charging Station | `GetConfigurationRequest.json` | `GetConfigurationReq` / `GetConfigurationResp` |
| `getDiagnostics` | CSMS -> Charging Station | `GetDiagnosticsRequest.json` | `GetDiagnosticsReq` / `GetDiagnosticsResp` |
| `getInstalledCertificateIds` | CSMS -> Charging Station | `GetInstalledCertificateIdsRequest.json` | `GetInstalledCertificateIdsReq` / `GetInstalledCertificateIdsResp` |
| `getLocalListVersion` | CSMS -> Charging Station | `GetLocalListVersionRequest.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| `getLog` | CSMS -> Charging Station | `GetLogRequest.json` | `GetLogReq` / `GetLogResp` |
| `heartbeat` | Charging Station -> CSMS | `HeartbeatRequest.json` | `HeartbeatReq` / `HeartbeatResp` |
| `installCertificate` | CSMS -> Charging Station | `InstallCertificateRequest.json` | `InstallCertificateReq` / `InstallCertificateResp` |
| `logStatusNotification` | Charging Station -> CSMS | `LogStatusNotificationRequest.json` | `LogStatusNotificationReq` / `LogStatusNotificationResp` |
| `meterValues` | Charging Station -> CSMS | `MeterValuesRequest.json` | `MeterValuesReq` / `MeterValuesResp` |
| `remoteStartTransaction` | CSMS -> Charging Station | `RemoteStartTransactionRequest.json` | `RemoteStartTransactionReq` / `RemoteStartTransactionResp` |
| `remoteStopTransaction` | CSMS -> Charging Station | `RemoteStopTransactionRequest.json` | `RemoteStopTransactionReq` / `RemoteStopTransactionResp` |
| `reserveNow` | CSMS -> Charging Station | `ReserveNowRequest.json` | `ReserveNowReq` / `ReserveNowResp` |
| `reset` | CSMS -> Charging Station | `ResetRequest.json` | `ResetReq` / `ResetResp` |
| `securityEventNotification` | Charging Station -> CSMS | `SecurityEventNotificationRequest.json` | `SecurityEventNotificationReq` / `SecurityEventNotificationResp` |
| `sendLocalList` | CSMS -> Charging Station | `SendLocalListRequest.json` | `SendLocalListReq` / `SendLocalListResp` |
| `setChargingProfile` | CSMS -> Charging Station | `SetChargingProfileRequest.json` | `SetChargingProfileReq` / `SetChargingProfileResp` |
| `signCertificate` | Charging Station -> CSMS | `SignCertificateRequest.json` | `SignCertificateReq` / `SignCertificateResp` |
| `signedFirmwareStatusNotification` | Charging Station -> CSMS | `SignedFirmwareStatusNotificationRequest.json` | `SignedFirmwareStatusNotificationReq` / `SignedFirmwareStatusNotificationResp` |
| `signedUpdateFirmware` | CSMS -> Charging Station | `SignedUpdateFirmwareRequest.json` | `SignedUpdateFirmwareReq` / `SignedUpdateFirmwareResp` |
| `startTransaction` | Charging Station -> CSMS | `StartTransactionRequest.json` | `StartTransactionReq` / `StartTransactionResp` |
| `statusNotification` | Charging Station -> CSMS | `StatusNotificationRequest.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| `stopTransaction` | Charging Station -> CSMS | `StopTransactionRequest.json` | `StopTransactionReq` / `StopTransactionResp` |
| `triggerMessage` | CSMS -> Charging Station | `TriggerMessageRequest.json` | `TriggerMessageReq` / `TriggerMessageResp` |
| `unlockConnector` | CSMS -> Charging Station | `UnlockConnectorRequest.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| `updateFirmware` | CSMS -> Charging Station | `UpdateFirmwareRequest.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

## authorize

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/authorize/AuthorizeResp.kt)
- spec: `ocpp-1.6-edition-2` §4.1 Authorize — pdf-page 37
- spec: `ocpp-1.6-edition-2` §6.1 Authorize.req — pdf-page 65
- spec: `ocpp-1.6-edition-2` §6.2 Authorize.conf — pdf-page 65
- errata mentions: `ocpp-1.6-errata` pdf-page 28, 42, 43

### authorize request

- schema: [`AuthorizeRequest.json`](../../ocpp-1-6-json/src/main/resources/AuthorizeRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.req.idTag` — string, required, maxLength 20

### authorize response

- schema: [`AuthorizeResponse.json`](../../ocpp-1-6-json/src/main/resources/AuthorizeResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.resp.idTagInfo` — object, required
- `authorize.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `authorize.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

## bootNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/bootnotification/BootNotificationResp.kt)
- spec: `ocpp-1.6-edition-2` §4.2 Boot Notification — pdf-page 37
- spec: `ocpp-1.6-edition-2` §6.3 BootNotification.req — pdf-page 65
- spec: `ocpp-1.6-edition-2` §6.4 BootNotification.conf — pdf-page 66
- spec: `ocpp-s-1.6-specification` §7.1 BootNotification — pdf-page 13
- errata mentions: `ocpp-1.6-errata` pdf-page 6, 12, 16, 17, 20, 33, 41
- errata mentions: `ocpp-j-1.6-errata` pdf-page 10, 11, 12

### bootNotification request

- schema: [`BootNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/BootNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.req.chargePointVendor` — string, required, maxLength 20
- `bootNotification.req.chargePointModel` — string, required, maxLength 20
- `bootNotification.req.chargePointSerialNumber` — string, optional, maxLength 25
- `bootNotification.req.chargeBoxSerialNumber` — string, optional, maxLength 25
- `bootNotification.req.firmwareVersion` — string, optional, maxLength 50
- `bootNotification.req.iccid` — string, optional, maxLength 20
- `bootNotification.req.imsi` — string, optional, maxLength 20
- `bootNotification.req.meterType` — string, optional, maxLength 25
- `bootNotification.req.meterSerialNumber` — string, optional, maxLength 25

### bootNotification response

- schema: [`BootNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/BootNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.status` — string, required, enum: Accepted | Pending | Rejected
- `bootNotification.resp.currentTime` — string, required, format date-time
- `bootNotification.resp.interval` — integer, required

## cancelReservation

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/cancelreservation/CancelReservationResp.kt)
- spec: `ocpp-1.6-edition-2` §5.1 Cancel Reservation — pdf-page 50
- spec: `ocpp-1.6-edition-2` §6.5 CancelReservation.req — pdf-page 66
- spec: `ocpp-1.6-edition-2` §6.6 CancelReservation.conf — pdf-page 66
- spec: `ocpp-1.6-edition-2` §7.5 CancelReservationStatus — pdf-page 81

### cancelReservation request

- schema: [`CancelReservationRequest.json`](../../ocpp-1-6-json/src/main/resources/CancelReservationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.reservationId` — integer, required

### cancelReservation response

- schema: [`CancelReservationResponse.json`](../../ocpp-1-6-json/src/main/resources/CancelReservationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.status` — string, required, enum: Accepted | Rejected

## certificateSigned

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CERTIFICATESIGNED`
- Kotlin `CertificateSignedReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedReq.kt)
- Kotlin `CertificateSignedResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/certificatesigned/CertificateSignedResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.1 CertificateSigned.req — pdf-page 49
- spec: `ocpp-1.6-security-whitepaper` §5.2 CertificateSigned.conf — pdf-page 49

### certificateSigned request

- schema: [`CertificateSignedRequest.json`](../../ocpp-1-6-json/src/main/resources/CertificateSignedRequest.json) · `urn:OCPP:Cp:1.6:2020:3:CertificateSigned.req`
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.req.certificateChain` — string, required, maxLength 10000

### certificateSigned response

- schema: [`CertificateSignedResponse.json`](../../ocpp-1-6-json/src/main/resources/CertificateSignedResponse.json) · `urn:OCPP:Cp:1.6:2020:3:CertificateSigned.conf`
- `additionalProperties: false` — an unknown field fails validation

- `certificateSigned.resp.status` — CertificateSignedStatusEnumType (string), required, enum: Accepted | Rejected

#### certificateSigned enumerations

- `certificateSigned` `CertificateSignedStatusEnumType`: Accepted | Rejected

## changeAvailability

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: `ocpp-1.6-edition-2` §5.2 Change Availability — pdf-page 50
- spec: `ocpp-1.6-edition-2` §6.7 ChangeAvailability.req — pdf-page 66
- spec: `ocpp-1.6-edition-2` §6.8 ChangeAvailability.conf — pdf-page 67

### changeAvailability request

- schema: [`ChangeAvailabilityRequest.json`](../../ocpp-1-6-json/src/main/resources/ChangeAvailabilityRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.connectorId` — integer, required
- `changeAvailability.req.type` — string, required, enum: Inoperative | Operative

### changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../ocpp-1-6-json/src/main/resources/ChangeAvailabilityResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.status` — string, required, enum: Accepted | Rejected | Scheduled

## changeConfiguration

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGECONFIGURATION`
- Kotlin `ChangeConfigurationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationReq.kt)
- Kotlin `ChangeConfigurationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/changeconfiguration/ChangeConfigurationResp.kt)
- spec: `ocpp-1.6-edition-2` §5.3 Change Configuration — pdf-page 50
- spec: `ocpp-1.6-edition-2` §6.9 ChangeConfiguration.req — pdf-page 67
- spec: `ocpp-1.6-edition-2` §6.10 ChangeConfiguration.conf — pdf-page 67
- errata mentions: `ocpp-1.6-errata` pdf-page 9, 46

### changeConfiguration request

- schema: [`ChangeConfigurationRequest.json`](../../ocpp-1-6-json/src/main/resources/ChangeConfigurationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.req.key` — string, required, maxLength 50
- `changeConfiguration.req.value` — string, required, maxLength 500

### changeConfiguration response

- schema: [`ChangeConfigurationResponse.json`](../../ocpp-1-6-json/src/main/resources/ChangeConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.resp.status` — string, required, enum: Accepted | Rejected | RebootRequired | NotSupported

## clearCache

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearcache/ClearCacheResp.kt)
- spec: `ocpp-1.6-edition-2` §5.4 Clear Cache — pdf-page 51
- spec: `ocpp-1.6-edition-2` §6.11 ClearCache.req — pdf-page 67
- spec: `ocpp-1.6-edition-2` §6.12 ClearCache.conf — pdf-page 68
- spec: `ocpp-1.6-edition-2` §7.20 ClearCacheStatus — pdf-page 87
- errata mentions: `ocpp-1.6-errata` pdf-page 12, 42

### clearCache request

- schema: [`ClearCacheRequest.json`](../../ocpp-1-6-json/src/main/resources/ClearCacheRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### clearCache response

- schema: [`ClearCacheResponse.json`](../../ocpp-1-6-json/src/main/resources/ClearCacheResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.status` — string, required, enum: Accepted | Rejected

## clearChargingProfile

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCHARGINGPROFILE`
- Kotlin `ClearChargingProfileReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileReq.kt)
- Kotlin `ClearChargingProfileResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/clearchargingprofile/ClearChargingProfileResp.kt)
- spec: `ocpp-1.6-edition-2` §5.5 Clear Charging Profile — pdf-page 52
- spec: `ocpp-1.6-edition-2` §6.13 ClearChargingProfile.req — pdf-page 68
- spec: `ocpp-1.6-edition-2` §6.14 ClearChargingProfile.conf — pdf-page 68
- spec: `ocpp-1.6-edition-2` §7.21 ClearChargingProfileStatus — pdf-page 88
- errata mentions: `ocpp-1.6-errata` pdf-page 13, 42

### clearChargingProfile request

- schema: [`ClearChargingProfileRequest.json`](../../ocpp-1-6-json/src/main/resources/ClearChargingProfileRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.req.id` — integer, optional
- `clearChargingProfile.req.connectorId` — integer, optional
- `clearChargingProfile.req.chargingProfilePurpose` — string, optional, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `clearChargingProfile.req.stackLevel` — integer, optional

### clearChargingProfile response

- schema: [`ClearChargingProfileResponse.json`](../../ocpp-1-6-json/src/main/resources/ClearChargingProfileResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearChargingProfile.resp.status` — string, required, enum: Accepted | Unknown

## dataTransfer

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/datatransfer/DataTransferResp.kt)
- spec: `ocpp-1.6-edition-2` §4.3 Data Transfer — pdf-page 39
- spec: `ocpp-1.6-edition-2` §5.6 Data Transfer — pdf-page 52
- spec: `ocpp-1.6-edition-2` §6.15 DataTransfer.req — pdf-page 68
- spec: `ocpp-1.6-edition-2` §6.16 DataTransfer.conf — pdf-page 69
- spec: `ocpp-1.6-edition-2` §7.23 DataTransferStatus — pdf-page 88

### dataTransfer request

- schema: [`DataTransferRequest.json`](../../ocpp-1-6-json/src/main/resources/DataTransferRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50
- `dataTransfer.req.data` — string, optional

### dataTransfer response

- schema: [`DataTransferResponse.json`](../../ocpp-1-6-json/src/main/resources/DataTransferResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.status` — string, required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId
- `dataTransfer.resp.data` — string, optional

## deleteCertificate

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.DELETECERTIFICATE`
- Kotlin `DeleteCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateReq.kt)
- Kotlin `DeleteCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/deletecertificate/DeleteCertificateResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.3 DeleteCertificate.req — pdf-page 50
- spec: `ocpp-1.6-security-whitepaper` §5.4 DeleteCertificate.conf — pdf-page 50

### deleteCertificate request

- schema: [`DeleteCertificateRequest.json`](../../ocpp-1-6-json/src/main/resources/DeleteCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:DeleteCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.req.certificateHashData` — CertificateHashDataType, required
- `deleteCertificate.req.certificateHashData.hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512
- `deleteCertificate.req.certificateHashData.issuerNameHash` — string, required, maxLength 128
- `deleteCertificate.req.certificateHashData.issuerKeyHash` — string, required, maxLength 128
- `deleteCertificate.req.certificateHashData.serialNumber` — string, required, maxLength 40

### deleteCertificate response

- schema: [`DeleteCertificateResponse.json`](../../ocpp-1-6-json/src/main/resources/DeleteCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:DeleteCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `deleteCertificate.resp.status` — DeleteCertificateStatusEnumType (string), required, enum: Accepted | Failed | NotFound

#### deleteCertificate enumerations

- `deleteCertificate` `DeleteCertificateStatusEnumType`: Accepted | Failed | NotFound
- `deleteCertificate` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512

## diagnosticsStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.DIAGNOSTICSSTATUSNOTIFICATION`
- Kotlin `DiagnosticsStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt)
- Kotlin `DiagnosticsStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt)
- spec: `ocpp-1.6-edition-2` §4.4 Diagnostics Status Notification — pdf-page 39
- spec: `ocpp-1.6-edition-2` §6.17 DiagnosticsStatusNotification.req — pdf-page 69
- spec: `ocpp-1.6-edition-2` §6.18 DiagnosticsStatusNotification.conf — pdf-page 69
- errata mentions: `ocpp-1.6-errata` pdf-page 14, 15

### diagnosticsStatusNotification request

- schema: [`DiagnosticsStatusNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/DiagnosticsStatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `diagnosticsStatusNotification.req.status` — string, required, enum: Idle | Uploaded | UploadFailed | Uploading

### diagnosticsStatusNotification response

- schema: [`DiagnosticsStatusNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/DiagnosticsStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## extendedTriggerMessage

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.EXTENDEDTRIGGERMESSAGE`
- Kotlin `ExtendedTriggerMessageReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageReq.kt)
- Kotlin `ExtendedTriggerMessageResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/extendedtriggermessage/ExtendedTriggerMessageResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.5 ExtendedTriggerMessage.req — pdf-page 50
- spec: `ocpp-1.6-security-whitepaper` §5.6 ExtendedTriggerMessage.conf — pdf-page 50

### extendedTriggerMessage request

- schema: [`ExtendedTriggerMessageRequest.json`](../../ocpp-1-6-json/src/main/resources/ExtendedTriggerMessageRequest.json) · `urn:OCPP:Cp:1.6:2020:3:ExtendedTriggerMessage.req`
- `additionalProperties: false` — an unknown field fails validation

- `extendedTriggerMessage.req.requestedMessage` — MessageTriggerEnumType (string), required, enum: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargePointCertificate | StatusNotification
- `extendedTriggerMessage.req.connectorId` — integer, optional

### extendedTriggerMessage response

- schema: [`ExtendedTriggerMessageResponse.json`](../../ocpp-1-6-json/src/main/resources/ExtendedTriggerMessageResponse.json) · `urn:OCPP:Cp:1.6:2020:3:ExtendedTriggerMessage.conf`
- `additionalProperties: false` — an unknown field fails validation

- `extendedTriggerMessage.resp.status` — TriggerMessageStatusEnumType (string), required, enum: Accepted | Rejected | NotImplemented

#### extendedTriggerMessage enumerations

- `extendedTriggerMessage` `MessageTriggerEnumType`: BootNotification | LogStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | SignChargePointCertificate | StatusNotification
- `extendedTriggerMessage` `TriggerMessageStatusEnumType`: Accepted | Rejected | NotImplemented

## firmwareStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: `ocpp-1.6-edition-2` §4.5 Firmware Status Notification — pdf-page 39
- spec: `ocpp-1.6-edition-2` §6.19 FirmwareStatusNotification.req — pdf-page 69
- spec: `ocpp-1.6-edition-2` §6.20 FirmwareStatusNotification.conf — pdf-page 70
- errata mentions: `ocpp-1.6-errata` pdf-page 6, 19, 20

### firmwareStatusNotification request

- schema: [`FirmwareStatusNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/FirmwareStatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.status` — string, required, enum: Downloaded | DownloadFailed | Downloading | Idle | InstallationFailed | Installing | Installed

### firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/FirmwareStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## getCompositeSchedule

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCOMPOSITESCHEDULE`
- Kotlin `GetCompositeScheduleReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleReq.kt)
- Kotlin `GetCompositeScheduleResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getcompositeschedule/GetCompositeScheduleResp.kt)
- spec: `ocpp-1.6-edition-2` §5.7 Get Composite Schedule — pdf-page 52
- spec: `ocpp-1.6-edition-2` §6.21 GetCompositeSchedule.req — pdf-page 70
- spec: `ocpp-1.6-edition-2` §6.22 GetCompositeSchedule.conf — pdf-page 70
- spec: `ocpp-1.6-edition-2` §7.26 GetCompositeScheduleStatus — pdf-page 89
- errata mentions: `ocpp-1.6-errata` pdf-page 13, 14, 21, 23, 36, 44

### getCompositeSchedule request

- schema: [`GetCompositeScheduleRequest.json`](../../ocpp-1-6-json/src/main/resources/GetCompositeScheduleRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.req.connectorId` — integer, required
- `getCompositeSchedule.req.duration` — integer, required
- `getCompositeSchedule.req.chargingRateUnit` — string, optional, enum: A | W

### getCompositeSchedule response

- schema: [`GetCompositeScheduleResponse.json`](../../ocpp-1-6-json/src/main/resources/GetCompositeScheduleResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getCompositeSchedule.resp.status` — string, required, enum: Accepted | Rejected
- `getCompositeSchedule.resp.connectorId` — integer, optional
- `getCompositeSchedule.resp.scheduleStart` — string, optional, format date-time
- `getCompositeSchedule.resp.chargingSchedule` — object, optional
- `getCompositeSchedule.resp.chargingSchedule.duration` — integer, optional
- `getCompositeSchedule.resp.chargingSchedule.startSchedule` — string, optional, format date-time
- `getCompositeSchedule.resp.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod` — array, required
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `getCompositeSchedule.resp.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `getCompositeSchedule.resp.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

## getConfiguration

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCONFIGURATION`
- Kotlin `GetConfigurationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationReq.kt)
- Kotlin `GetConfigurationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getconfiguration/GetConfigurationResp.kt)
- spec: `ocpp-1.6-edition-2` §5.8 Get Configuration — pdf-page 53
- spec: `ocpp-1.6-edition-2` §6.23 GetConfiguration.req — pdf-page 70
- spec: `ocpp-1.6-edition-2` §6.24 GetConfiguration.conf — pdf-page 71
- spec: `ocpp-1.6-edition-2` §9.1.9 GetConfigurationMaxKeys — pdf-page 104
- errata mentions: `ocpp-1.6-errata` pdf-page 43

### getConfiguration request

- schema: [`GetConfigurationRequest.json`](../../ocpp-1-6-json/src/main/resources/GetConfigurationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.req.key` — array, optional
- `getConfiguration.req.key[]` — string, maxLength 50

### getConfiguration response

- schema: [`GetConfigurationResponse.json`](../../ocpp-1-6-json/src/main/resources/GetConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.resp.configurationKey` — array, optional
- `getConfiguration.resp.configurationKey[].key` — string, required, maxLength 50
- `getConfiguration.resp.configurationKey[].readonly` — boolean, required
- `getConfiguration.resp.configurationKey[].value` — string, optional, maxLength 500
- `getConfiguration.resp.unknownKey` — array, optional
- `getConfiguration.resp.unknownKey[]` — string, maxLength 50

## getDiagnostics

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETDIAGNOSTICS`
- Kotlin `GetDiagnosticsReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsReq.kt)
- Kotlin `GetDiagnosticsResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getdiagnostics/GetDiagnosticsResp.kt)
- spec: `ocpp-1.6-edition-2` §5.9 Get Diagnostics — pdf-page 53
- spec: `ocpp-1.6-edition-2` §6.25 GetDiagnostics.req — pdf-page 71
- spec: `ocpp-1.6-edition-2` §6.26 GetDiagnostics.conf — pdf-page 71
- errata mentions: `ocpp-1.6-errata` pdf-page 14, 15

### getDiagnostics request

- schema: [`GetDiagnosticsRequest.json`](../../ocpp-1-6-json/src/main/resources/GetDiagnosticsRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.req.location` — string, required, format uri
- `getDiagnostics.req.retries` — integer, optional
- `getDiagnostics.req.retryInterval` — integer, optional
- `getDiagnostics.req.startTime` — string, optional, format date-time
- `getDiagnostics.req.stopTime` — string, optional, format date-time

### getDiagnostics response

- schema: [`GetDiagnosticsResponse.json`](../../ocpp-1-6-json/src/main/resources/GetDiagnosticsResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.resp.fileName` — string, optional, maxLength 255

## getInstalledCertificateIds

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETINSTALLEDCERTIFICATEIDS`
- Kotlin `GetInstalledCertificateIdsReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsReq.kt)
- Kotlin `GetInstalledCertificateIdsResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getinstalledcertificateids/GetInstalledCertificateIdsResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.7 GetInstalledCertificateIds.req — pdf-page 51
- spec: `ocpp-1.6-security-whitepaper` §5.8 GetInstalledCertificateIds.conf — pdf-page 51

### getInstalledCertificateIds request

- schema: [`GetInstalledCertificateIdsRequest.json`](../../ocpp-1-6-json/src/main/resources/GetInstalledCertificateIdsRequest.json) · `urn:OCPP:Cp:1.6:2020:3:GetInstalledCertificateIds.req`
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.req.certificateType` — CertificateUseEnumType (string), required, enum: CentralSystemRootCertificate | ManufacturerRootCertificate

### getInstalledCertificateIds response

- schema: [`GetInstalledCertificateIdsResponse.json`](../../ocpp-1-6-json/src/main/resources/GetInstalledCertificateIdsResponse.json) · `urn:OCPP:Cp:1.6:2020:3:GetInstalledCertificateIds.conf`
- `additionalProperties: false` — an unknown field fails validation

- `getInstalledCertificateIds.resp.certificateHashData` — array, optional, minItems 1
- `getInstalledCertificateIds.resp.certificateHashData[].hashAlgorithm` — HashAlgorithmEnumType (string), required, enum: SHA256 | SHA384 | SHA512
- `getInstalledCertificateIds.resp.certificateHashData[].issuerNameHash` — string, required, maxLength 128
- `getInstalledCertificateIds.resp.certificateHashData[].issuerKeyHash` — string, required, maxLength 128
- `getInstalledCertificateIds.resp.certificateHashData[].serialNumber` — string, required, maxLength 40
- `getInstalledCertificateIds.resp.status` — GetInstalledCertificateStatusEnumType (string), required, enum: Accepted | NotFound

#### getInstalledCertificateIds enumerations

- `getInstalledCertificateIds` `CertificateUseEnumType`: CentralSystemRootCertificate | ManufacturerRootCertificate
- `getInstalledCertificateIds` `GetInstalledCertificateStatusEnumType`: Accepted | NotFound
- `getInstalledCertificateIds` `HashAlgorithmEnumType`: SHA256 | SHA384 | SHA512

## getLocalListVersion

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: `ocpp-1.6-edition-2` §5.10 Get Local List Version — pdf-page 54
- spec: `ocpp-1.6-edition-2` §6.27 GetLocalListVersion.req — pdf-page 71
- spec: `ocpp-1.6-edition-2` §6.28 GetLocalListVersion.conf — pdf-page 72
- errata mentions: `ocpp-1.6-errata` pdf-page 21, 22

### getLocalListVersion request

- schema: [`GetLocalListVersionRequest.json`](../../ocpp-1-6-json/src/main/resources/GetLocalListVersionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../ocpp-1-6-json/src/main/resources/GetLocalListVersionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.listVersion` — integer, required

## getLog

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOG`
- Kotlin `GetLogReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogReq.kt)
- Kotlin `GetLogResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/getlog/GetLogResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.9 GetLog.req — pdf-page 51
- spec: `ocpp-1.6-security-whitepaper` §5.10 GetLog.conf — pdf-page 52

### getLog request

- schema: [`GetLogRequest.json`](../../ocpp-1-6-json/src/main/resources/GetLogRequest.json) · `urn:OCPP:Cp:1.6:2020:3:GetLog.req`
- `additionalProperties: false` — an unknown field fails validation

- `getLog.req.log` — LogParametersType, required
- `getLog.req.log.remoteLocation` — string, required, maxLength 512
- `getLog.req.log.oldestTimestamp` — string, optional, format date-time
- `getLog.req.log.latestTimestamp` — string, optional, format date-time
- `getLog.req.logType` — LogEnumType (string), required, enum: DiagnosticsLog | SecurityLog
- `getLog.req.requestId` — integer, required
- `getLog.req.retries` — integer, optional
- `getLog.req.retryInterval` — integer, optional

### getLog response

- schema: [`GetLogResponse.json`](../../ocpp-1-6-json/src/main/resources/GetLogResponse.json) · `urn:OCPP:Cp:1.6:2020:3:GetLog.conf`
- `additionalProperties: false` — an unknown field fails validation

- `getLog.resp.status` — LogStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled
- `getLog.resp.filename` — string, optional, maxLength 255

#### getLog enumerations

- `getLog` `LogEnumType`: DiagnosticsLog | SecurityLog
- `getLog` `LogStatusEnumType`: Accepted | Rejected | AcceptedCanceled

## heartbeat

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/heartbeat/HeartbeatResp.kt)
- spec: `ocpp-1.6-edition-2` §4.6 Heartbeat — pdf-page 40
- spec: `ocpp-1.6-edition-2` §6.29 Heartbeat.req — pdf-page 72
- spec: `ocpp-1.6-edition-2` §6.30 Heartbeat.conf — pdf-page 72
- spec: `ocpp-1.6-edition-2` §9.1.10 HeartbeatInterval — pdf-page 104

### heartbeat request

- schema: [`HeartbeatRequest.json`](../../ocpp-1-6-json/src/main/resources/HeartbeatRequest.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### heartbeat response

- schema: [`HeartbeatResponse.json`](../../ocpp-1-6-json/src/main/resources/HeartbeatResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.currentTime` — string, required, format date-time

## installCertificate

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.INSTALLCERTIFICATE`
- Kotlin `InstallCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateReq.kt)
- Kotlin `InstallCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/installcertificate/InstallCertificateResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.11 InstallCertificate.req — pdf-page 52
- spec: `ocpp-1.6-security-whitepaper` §5.12 InstallCertificate.conf — pdf-page 52

### installCertificate request

- schema: [`InstallCertificateRequest.json`](../../ocpp-1-6-json/src/main/resources/InstallCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:InstallCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.req.certificateType` — CertificateUseEnumType (string), required, enum: CentralSystemRootCertificate | ManufacturerRootCertificate
- `installCertificate.req.certificate` — string, required, maxLength 5500

### installCertificate response

- schema: [`InstallCertificateResponse.json`](../../ocpp-1-6-json/src/main/resources/InstallCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:InstallCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `installCertificate.resp.status` — InstallCertificateStatusEnumType (string), required, enum: Accepted | Failed | Rejected

#### installCertificate enumerations

- `installCertificate` `CertificateUseEnumType`: CentralSystemRootCertificate | ManufacturerRootCertificate
- `installCertificate` `InstallCertificateStatusEnumType`: Accepted | Failed | Rejected

## logStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.LOGSTATUSNOTIFICATION`
- Kotlin `LogStatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationReq.kt)
- Kotlin `LogStatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/logstatusnotification/LogStatusNotificationResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.13 LogStatusNotification.req — pdf-page 52
- spec: `ocpp-1.6-security-whitepaper` §5.14 LogStatusNotification.conf — pdf-page 53

### logStatusNotification request

- schema: [`LogStatusNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/LogStatusNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:LogStatusNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `logStatusNotification.req.status` — UploadLogStatusEnumType (string), required, enum: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading
- `logStatusNotification.req.requestId` — integer, optional

### logStatusNotification response

- schema: [`LogStatusNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/LogStatusNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:LogStatusNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

#### logStatusNotification enumerations

- `logStatusNotification` `UploadLogStatusEnumType`: BadMessage | Idle | NotSupportedOperation | PermissionDenied | Uploaded | UploadFailure | Uploading

## meterValues

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/metervalues/MeterValuesResp.kt)
- spec: `ocpp-1.6-edition-2` §4.7 Meter Values — pdf-page 40
- spec: `ocpp-1.6-edition-2` §6.31 MeterValues.req — pdf-page 72
- spec: `ocpp-1.6-edition-2` §6.32 MeterValues.conf — pdf-page 72
- errata mentions: `ocpp-1.6-errata` pdf-page 4, 6, 7, 8, 9, 10, 19, 41, 45
- errata mentions: `ocpp-j-1.6-errata` pdf-page 3

### meterValues request

- schema: [`MeterValuesRequest.json`](../../ocpp-1-6-json/src/main/resources/MeterValuesRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.connectorId` — integer, required
- `meterValues.req.transactionId` — integer, optional
- `meterValues.req.meterValue` — array, required
- `meterValues.req.meterValue[].timestamp` — string, required, format date-time
- `meterValues.req.meterValue[].sampledValue` — array, required
- `meterValues.req.meterValue[].sampledValue[].value` — string, required
- `meterValues.req.meterValue[].sampledValue[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger | Other
- `meterValues.req.meterValue[].sampledValue[].format` — string, optional, enum: Raw | SignedData
- `meterValues.req.meterValue[].sampledValue[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | Power.Factor | Current.Import | Current.Export | Current.Offered | Voltage | Frequency | Temperature | SoC | RPM
- `meterValues.req.meterValue[].sampledValue[].phase` — string, optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1
- `meterValues.req.meterValue[].sampledValue[].location` — string, optional, enum: Cable | EV | Inlet | Outlet | Body
- `meterValues.req.meterValue[].sampledValue[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | VA | kVA | var | kvar | A | V | K | Celcius | Celsius | Fahrenheit | Percent

### meterValues response

- schema: [`MeterValuesResponse.json`](../../ocpp-1-6-json/src/main/resources/MeterValuesResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## remoteStartTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTARTTRANSACTION`
- Kotlin `RemoteStartTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionReq.kt)
- Kotlin `RemoteStartTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestart/RemoteStartTransactionResp.kt)
- spec: `ocpp-1.6-edition-2` §5.11 Remote Start Transaction — pdf-page 54
- spec: `ocpp-1.6-edition-2` §6.33 RemoteStartTransaction.req — pdf-page 73
- spec: `ocpp-1.6-edition-2` §6.34 RemoteStartTransaction.conf — pdf-page 73
- errata mentions: `ocpp-1.6-errata` pdf-page 15, 18, 26

### remoteStartTransaction request

- schema: [`RemoteStartTransactionRequest.json`](../../ocpp-1-6-json/src/main/resources/RemoteStartTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.req.connectorId` — integer, optional
- `remoteStartTransaction.req.idTag` — string, required, maxLength 20
- `remoteStartTransaction.req.chargingProfile` — object, optional
- `remoteStartTransaction.req.chargingProfile.chargingProfileId` — integer, required
- `remoteStartTransaction.req.chargingProfile.transactionId` — integer, optional
- `remoteStartTransaction.req.chargingProfile.stackLevel` — integer, required
- `remoteStartTransaction.req.chargingProfile.chargingProfilePurpose` — string, required, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `remoteStartTransaction.req.chargingProfile.chargingProfileKind` — string, required, enum: Absolute | Recurring | Relative
- `remoteStartTransaction.req.chargingProfile.recurrencyKind` — string, optional, enum: Daily | Weekly
- `remoteStartTransaction.req.chargingProfile.validFrom` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.validTo` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.chargingSchedule` — object, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.duration` — integer, optional
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.startSchedule` — string, optional, format date-time
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod` — array, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `remoteStartTransaction.req.chargingProfile.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

### remoteStartTransaction response

- schema: [`RemoteStartTransactionResponse.json`](../../ocpp-1-6-json/src/main/resources/RemoteStartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.resp.status` — string, required, enum: Accepted | Rejected

## remoteStopTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTOPTRANSACTION`
- Kotlin `RemoteStopTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionReq.kt)
- Kotlin `RemoteStopTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/remotestop/RemoteStopTransactionResp.kt)
- spec: `ocpp-1.6-edition-2` §5.12 Remote Stop Transaction — pdf-page 55
- spec: `ocpp-1.6-edition-2` §6.35 RemoteStopTransaction.req — pdf-page 73
- spec: `ocpp-1.6-edition-2` §6.36 RemoteStopTransaction.conf — pdf-page 73
- errata mentions: `ocpp-1.6-errata` pdf-page 15

### remoteStopTransaction request

- schema: [`RemoteStopTransactionRequest.json`](../../ocpp-1-6-json/src/main/resources/RemoteStopTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.req.transactionId` — integer, required

### remoteStopTransaction response

- schema: [`RemoteStopTransactionResponse.json`](../../ocpp-1-6-json/src/main/resources/RemoteStopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.resp.status` — string, required, enum: Accepted | Rejected

## reserveNow

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reservenow/ReserveNowResp.kt)
- spec: `ocpp-1.6-edition-2` §5.13 Reserve Now — pdf-page 56
- spec: `ocpp-1.6-edition-2` §6.37 ReserveNow.req — pdf-page 73
- spec: `ocpp-1.6-edition-2` §6.38 ReserveNow.conf — pdf-page 74

### reserveNow request

- schema: [`ReserveNowRequest.json`](../../ocpp-1-6-json/src/main/resources/ReserveNowRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.connectorId` — integer, required
- `reserveNow.req.expiryDate` — string, required, format date-time
- `reserveNow.req.idTag` — string, required, maxLength 20
- `reserveNow.req.parentIdTag` — string, optional, maxLength 20
- `reserveNow.req.reservationId` — integer, required

### reserveNow response

- schema: [`ReserveNowResponse.json`](../../ocpp-1-6-json/src/main/resources/ReserveNowResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.status` — string, required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable

## reset

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/reset/ResetResp.kt)
- spec: `ocpp-1.6-edition-2` §5.14 Reset — pdf-page 57
- spec: `ocpp-1.6-edition-2` §6.39 Reset.req — pdf-page 74
- spec: `ocpp-1.6-edition-2` §6.40 Reset.conf — pdf-page 74
- spec: `ocpp-1.6-edition-2` §7.41 ResetStatus — pdf-page 97
- spec: `ocpp-1.6-edition-2` §7.42 ResetType — pdf-page 97
- spec: `ocpp-1.6-edition-2` §9.1.22 ResetRetries — pdf-page 107
- errata mentions: `ocpp-1.6-errata` pdf-page 16, 20, 32, 43

### reset request

- schema: [`ResetRequest.json`](../../ocpp-1-6-json/src/main/resources/ResetRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.type` — string, required, enum: Hard | Soft

### reset response

- schema: [`ResetResponse.json`](../../ocpp-1-6-json/src/main/resources/ResetResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.status` — string, required, enum: Accepted | Rejected

## securityEventNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SECURITYEVENTNOTIFICATION`
- Kotlin `SecurityEventNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationReq.kt)
- Kotlin `SecurityEventNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/securityeventnotification/SecurityEventNotificationResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §A04 Security Event Notification — pdf-page 37
- spec: `ocpp-1.6-security-whitepaper` §5.15 SecurityEventNotification.req — pdf-page 53
- spec: `ocpp-1.6-security-whitepaper` §5.16 SecurityEventNotification.conf — pdf-page 53

### securityEventNotification request

- schema: [`SecurityEventNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/SecurityEventNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SecurityEventNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `securityEventNotification.req.type` — string, required, maxLength 50
- `securityEventNotification.req.timestamp` — string, required, format date-time
- `securityEventNotification.req.techInfo` — string, optional, maxLength 255

### securityEventNotification response

- schema: [`SecurityEventNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/SecurityEventNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SecurityEventNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## sendLocalList

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/sendlocallist/SendLocalListResp.kt)
- spec: `ocpp-1.6-edition-2` §5.15 Send Local List — pdf-page 57
- spec: `ocpp-1.6-edition-2` §6.41 SendLocalList.req — pdf-page 74
- spec: `ocpp-1.6-edition-2` §6.42 SendLocalList.conf — pdf-page 75
- errata mentions: `ocpp-1.6-errata` pdf-page 21
- errata mentions: `ocpp-j-1.6-errata` pdf-page 3, 11

### sendLocalList request

- schema: [`SendLocalListRequest.json`](../../ocpp-1-6-json/src/main/resources/SendLocalListRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.req.listVersion` — integer, required
- `sendLocalList.req.localAuthorizationList` — array, optional
- `sendLocalList.req.localAuthorizationList[].idTag` — string, required, maxLength 20
- `sendLocalList.req.localAuthorizationList[].idTagInfo` — object, optional
- `sendLocalList.req.localAuthorizationList[].idTagInfo.expiryDate` — string, optional, format date-time
- `sendLocalList.req.localAuthorizationList[].idTagInfo.parentIdTag` — string, optional, maxLength 20
- `sendLocalList.req.localAuthorizationList[].idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `sendLocalList.req.updateType` — string, required, enum: Differential | Full

### sendLocalList response

- schema: [`SendLocalListResponse.json`](../../ocpp-1-6-json/src/main/resources/SendLocalListResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.status` — string, required, enum: Accepted | Failed | NotSupported | VersionMismatch

## setChargingProfile

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETCHARGINGPROFILE`
- Kotlin `SetChargingProfileReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileReq.kt)
- Kotlin `SetChargingProfileResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/setchargingprofile/SetChargingProfileResp.kt)
- spec: `ocpp-1.6-edition-2` §5.16 Set Charging Profile — pdf-page 58
- spec: `ocpp-1.6-edition-2` §6.43 SetChargingProfile.req — pdf-page 75
- spec: `ocpp-1.6-edition-2` §6.44 SetChargingProfile.conf — pdf-page 75
- errata mentions: `ocpp-1.6-errata` pdf-page 16, 17, 22, 44

### setChargingProfile request

- schema: [`SetChargingProfileRequest.json`](../../ocpp-1-6-json/src/main/resources/SetChargingProfileRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.req.connectorId` — integer, required
- `setChargingProfile.req.csChargingProfiles` — object, required
- `setChargingProfile.req.csChargingProfiles.chargingProfileId` — integer, required
- `setChargingProfile.req.csChargingProfiles.transactionId` — integer, optional
- `setChargingProfile.req.csChargingProfiles.stackLevel` — integer, required
- `setChargingProfile.req.csChargingProfiles.chargingProfilePurpose` — string, required, enum: ChargePointMaxProfile | TxDefaultProfile | TxProfile
- `setChargingProfile.req.csChargingProfiles.chargingProfileKind` — string, required, enum: Absolute | Recurring | Relative
- `setChargingProfile.req.csChargingProfiles.recurrencyKind` — string, optional, enum: Daily | Weekly
- `setChargingProfile.req.csChargingProfiles.validFrom` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.validTo` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.chargingSchedule` — object, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.duration` — integer, optional
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.startSchedule` — string, optional, format date-time
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingRateUnit` — string, required, enum: A | W
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod` — array, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].startPeriod` — integer, required
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].limit` — number, required, multipleOf 0.1
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.chargingSchedulePeriod[].numberPhases` — integer, optional
- `setChargingProfile.req.csChargingProfiles.chargingSchedule.minChargingRate` — number, optional, multipleOf 0.1

### setChargingProfile response

- schema: [`SetChargingProfileResponse.json`](../../ocpp-1-6-json/src/main/resources/SetChargingProfileResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `setChargingProfile.resp.status` — string, required, enum: Accepted | Rejected | NotSupported

## signCertificate

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNCERTIFICATE`
- Kotlin `SignCertificateReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateReq.kt)
- Kotlin `SignCertificateResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signcertificate/SignCertificateResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.17 SignCertificate.req — pdf-page 53
- spec: `ocpp-1.6-security-whitepaper` §5.18 SignCertificate.conf — pdf-page 54

### signCertificate request

- schema: [`SignCertificateRequest.json`](../../ocpp-1-6-json/src/main/resources/SignCertificateRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignCertificate.req`
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.req.csr` — string, required, maxLength 5500

### signCertificate response

- schema: [`SignCertificateResponse.json`](../../ocpp-1-6-json/src/main/resources/SignCertificateResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignCertificate.conf`
- `additionalProperties: false` — an unknown field fails validation

- `signCertificate.resp.status` — GenericStatusEnumType (string), required, enum: Accepted | Rejected

#### signCertificate enumerations

- `signCertificate` `GenericStatusEnumType`: Accepted | Rejected

## signedFirmwareStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNEDFIRMWARESTATUSNOTIFICATION`
- Kotlin `SignedFirmwareStatusNotificationReq`: _class file not found_
- Kotlin `SignedFirmwareStatusNotificationResp`: _class file not found_
- spec: `ocpp-1.6-security-whitepaper` §5.19 SignedFirmwareStatusNotification.req — pdf-page 54
- spec: `ocpp-1.6-security-whitepaper` §5.20 SignedFirmwareStatusNotification.conf — pdf-page 54

### signedFirmwareStatusNotification request

- schema: [`SignedFirmwareStatusNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/SignedFirmwareStatusNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignedFirmwareStatusNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `signedFirmwareStatusNotification.req.status` — FirmwareStatusEnumType (string), required, enum: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified
- `signedFirmwareStatusNotification.req.requestId` — integer, optional

### signedFirmwareStatusNotification response

- schema: [`SignedFirmwareStatusNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/SignedFirmwareStatusNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignedFirmwareStatusNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

#### signedFirmwareStatusNotification enumerations

- `signedFirmwareStatusNotification` `FirmwareStatusEnumType`: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified

## signedUpdateFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SIGNEDUPDATEFIRMWARE`
- Kotlin `SignedUpdateFirmwareReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareReq.kt)
- Kotlin `SignedUpdateFirmwareResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareResp.kt)
- spec: `ocpp-1.6-security-whitepaper` §5.21 SignedUpdateFirmware.req — pdf-page 54
- spec: `ocpp-1.6-security-whitepaper` §5.22 SignedUpdateFirmware.conf — pdf-page 55

### signedUpdateFirmware request

- schema: [`SignedUpdateFirmwareRequest.json`](../../ocpp-1-6-json/src/main/resources/SignedUpdateFirmwareRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignedUpdateFirmware.req`
- `additionalProperties: false` — an unknown field fails validation

- `signedUpdateFirmware.req.retries` — integer, optional
- `signedUpdateFirmware.req.retryInterval` — integer, optional
- `signedUpdateFirmware.req.requestId` — integer, required
- `signedUpdateFirmware.req.firmware` — FirmwareType, required
- `signedUpdateFirmware.req.firmware.location` — string, required, maxLength 512
- `signedUpdateFirmware.req.firmware.retrieveDateTime` — string, required, format date-time
- `signedUpdateFirmware.req.firmware.installDateTime` — string, optional, format date-time
- `signedUpdateFirmware.req.firmware.signingCertificate` — string, required, maxLength 5500
- `signedUpdateFirmware.req.firmware.signature` — string, required, maxLength 800

### signedUpdateFirmware response

- schema: [`SignedUpdateFirmwareResponse.json`](../../ocpp-1-6-json/src/main/resources/SignedUpdateFirmwareResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignedUpdateFirmware.conf`
- `additionalProperties: false` — an unknown field fails validation

- `signedUpdateFirmware.resp.status` — UpdateFirmwareStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate

#### signedUpdateFirmware enumerations

- `signedUpdateFirmware` `UpdateFirmwareStatusEnumType`: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate

## startTransaction

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STARTTRANSACTION`
- Kotlin `StartTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionReq.kt)
- Kotlin `StartTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/starttransaction/StartTransactionResp.kt)
- spec: `ocpp-1.6-edition-2` §4.8 Start Transaction — pdf-page 42
- spec: `ocpp-1.6-edition-2` §6.45 StartTransaction.req — pdf-page 76
- spec: `ocpp-1.6-edition-2` §6.46 StartTransaction.conf — pdf-page 76
- errata mentions: `ocpp-1.6-errata` pdf-page 4, 10, 11, 22, 25

### startTransaction request

- schema: [`StartTransactionRequest.json`](../../ocpp-1-6-json/src/main/resources/StartTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.req.connectorId` — integer, required
- `startTransaction.req.idTag` — string, required, maxLength 20
- `startTransaction.req.meterStart` — integer, required
- `startTransaction.req.reservationId` — integer, optional
- `startTransaction.req.timestamp` — string, required, format date-time

### startTransaction response

- schema: [`StartTransactionResponse.json`](../../ocpp-1-6-json/src/main/resources/StartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.resp.idTagInfo` — object, required
- `startTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `startTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `startTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `startTransaction.resp.transactionId` — integer, required

## statusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/statusnotification/StatusNotificationResp.kt)
- spec: `ocpp-1.6-edition-2` §4.9 Status Notification — pdf-page 43
- spec: `ocpp-1.6-edition-2` §6.47 StatusNotification.req — pdf-page 76
- spec: `ocpp-1.6-edition-2` §6.48 StatusNotification.conf — pdf-page 77
- errata mentions: `ocpp-1.6-errata` pdf-page 11, 12

### statusNotification request

- schema: [`StatusNotificationRequest.json`](../../ocpp-1-6-json/src/main/resources/StatusNotificationRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.connectorId` — integer, required
- `statusNotification.req.errorCode` — string, required, enum: ConnectorLockFailure | EVCommunicationError | GroundFailure | HighTemperature | InternalError | LocalListConflict | NoError | OtherError | OverCurrentFailure | PowerMeterFailure | PowerSwitchFailure | ReaderFailure | ResetFailure | UnderVoltage | OverVoltage | WeakSignal
- `statusNotification.req.info` — string, optional, maxLength 50
- `statusNotification.req.status` — string, required, enum: Available | Preparing | Charging | SuspendedEVSE | SuspendedEV | Finishing | Reserved | Unavailable | Faulted
- `statusNotification.req.timestamp` — string, optional, format date-time
- `statusNotification.req.vendorId` — string, optional, maxLength 255
- `statusNotification.req.vendorErrorCode` — string, optional, maxLength 50

### statusNotification response

- schema: [`StatusNotificationResponse.json`](../../ocpp-1-6-json/src/main/resources/StatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## stopTransaction

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STOPTRANSACTION`
- Kotlin `StopTransactionReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionReq.kt)
- Kotlin `StopTransactionResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/stoptransaction/StopTransactionResp.kt)
- spec: `ocpp-1.6-edition-2` §4.10 Stop Transaction — pdf-page 48
- spec: `ocpp-1.6-edition-2` §6.49 StopTransaction.req — pdf-page 77
- spec: `ocpp-1.6-edition-2` §6.50 StopTransaction.conf — pdf-page 77
- errata mentions: `ocpp-1.6-errata` pdf-page 7, 8, 9, 16, 33, 35, 36, 41, 45
- errata mentions: `ocpp-j-1.6-errata` pdf-page 3, 9

### stopTransaction request

- schema: [`StopTransactionRequest.json`](../../ocpp-1-6-json/src/main/resources/StopTransactionRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.req.idTag` — string, optional, maxLength 20
- `stopTransaction.req.meterStop` — integer, required
- `stopTransaction.req.timestamp` — string, required, format date-time
- `stopTransaction.req.transactionId` — integer, required
- `stopTransaction.req.reason` — string, optional, enum: EmergencyStop | EVDisconnected | HardReset | Local | Other | PowerLoss | Reboot | Remote | SoftReset | UnlockCommand | DeAuthorized
- `stopTransaction.req.transactionData` — array, optional
- `stopTransaction.req.transactionData[].timestamp` — string, required, format date-time
- `stopTransaction.req.transactionData[].sampledValue` — array, required
- `stopTransaction.req.transactionData[].sampledValue[].value` — string, required
- `stopTransaction.req.transactionData[].sampledValue[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger | Other
- `stopTransaction.req.transactionData[].sampledValue[].format` — string, optional, enum: Raw | SignedData
- `stopTransaction.req.transactionData[].sampledValue[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | Power.Factor | Current.Import | Current.Export | Current.Offered | Voltage | Frequency | Temperature | SoC | RPM
- `stopTransaction.req.transactionData[].sampledValue[].phase` — string, optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1
- `stopTransaction.req.transactionData[].sampledValue[].location` — string, optional, enum: Cable | EV | Inlet | Outlet | Body
- `stopTransaction.req.transactionData[].sampledValue[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | VA | kVA | var | kvar | A | V | K | Celcius | Fahrenheit | Percent

### stopTransaction response

- schema: [`StopTransactionResponse.json`](../../ocpp-1-6-json/src/main/resources/StopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.resp.idTagInfo` — object, optional
- `stopTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `stopTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `stopTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

## triggerMessage

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.TRIGGERMESSAGE`
- Kotlin `TriggerMessageReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageReq.kt)
- Kotlin `TriggerMessageResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/triggermessage/TriggerMessageResp.kt)
- spec: `ocpp-1.6-edition-2` §5.17 Trigger Message — pdf-page 60
- spec: `ocpp-1.6-edition-2` §6.51 TriggerMessage.req — pdf-page 78
- spec: `ocpp-1.6-edition-2` §6.52 TriggerMessage.conf — pdf-page 78
- spec: `ocpp-1.6-edition-2` §7.44 TriggerMessageStatus — pdf-page 98
- errata mentions: `ocpp-1.6-errata` pdf-page 16, 17, 19

### triggerMessage request

- schema: [`TriggerMessageRequest.json`](../../ocpp-1-6-json/src/main/resources/TriggerMessageRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.req.requestedMessage` — string, required, enum: BootNotification | DiagnosticsStatusNotification | FirmwareStatusNotification | Heartbeat | MeterValues | StatusNotification
- `triggerMessage.req.connectorId` — integer, optional

### triggerMessage response

- schema: [`TriggerMessageResponse.json`](../../ocpp-1-6-json/src/main/resources/TriggerMessageResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `triggerMessage.resp.status` — string, required, enum: Accepted | Rejected | NotImplemented

## unlockConnector

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/unlockconnector/UnlockConnectorResp.kt)
- spec: `ocpp-1.6-edition-2` §5.18 Unlock Connector — pdf-page 61
- spec: `ocpp-1.6-edition-2` §6.53 UnlockConnector.req — pdf-page 78
- spec: `ocpp-1.6-edition-2` §6.54 UnlockConnector.conf — pdf-page 78
- errata mentions: `ocpp-1.6-errata` pdf-page 34

### unlockConnector request

- schema: [`UnlockConnectorRequest.json`](../../ocpp-1-6-json/src/main/resources/UnlockConnectorRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.connectorId` — integer, required

### unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../ocpp-1-6-json/src/main/resources/UnlockConnectorResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.status` — string, required, enum: Unlocked | UnlockFailed | NotSupported

## updateFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareReq.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareResp.kt`](../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: `ocpp-1.6-edition-2` §5.19 Update Firmware — pdf-page 62
- spec: `ocpp-1.6-edition-2` §6.55 UpdateFirmware.req — pdf-page 78
- spec: `ocpp-1.6-edition-2` §6.56 UpdateFirmware.conf — pdf-page 79
- errata mentions: `ocpp-1.6-errata` pdf-page 19, 20, 24, 41, 42
- errata mentions: `ocpp-j-1.6-errata` pdf-page 7

### updateFirmware request

- schema: [`UpdateFirmwareRequest.json`](../../ocpp-1-6-json/src/main/resources/UpdateFirmwareRequest.json)
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.location` — string, required, format uri
- `updateFirmware.req.retries` — integer, optional
- `updateFirmware.req.retrieveDate` — string, required, format date-time
- `updateFirmware.req.retryInterval` — integer, optional

### updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../ocpp-1-6-json/src/main/resources/UpdateFirmwareResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## Schema coverage

- 78 of 78 shipped schema files are reachable from `Actions`.
- Actions and schema files correspond exactly.

## Declared JSON Schema draft

`ocpp-1-6-json`'s parser validates every payload with `SpecVersion.VersionFlag.V4`. What the files themselves declare:

- `http://json-schema.org/draft-04/schema#` — 56 file(s)
- `http://json-schema.org/draft-06/schema#` — 22 file(s)

**Mismatch:** the validator is fixed at `V4` (`http://json-schema.org/draft-04/schema#`), so these files are validated against a different draft than they declare:

- `http://json-schema.org/draft-06/schema#`: `CertificateSignedRequest.json`, `CertificateSignedResponse.json`, `DeleteCertificateRequest.json`, `DeleteCertificateResponse.json`, `ExtendedTriggerMessageRequest.json`, `ExtendedTriggerMessageResponse.json`, `GetInstalledCertificateIdsRequest.json`, `GetInstalledCertificateIdsResponse.json`, `GetLogRequest.json`, `GetLogResponse.json`, `InstallCertificateRequest.json`, `InstallCertificateResponse.json`, `LogStatusNotificationRequest.json`, `LogStatusNotificationResponse.json`, `SecurityEventNotificationRequest.json`, `SecurityEventNotificationResponse.json`, `SignCertificateRequest.json`, `SignCertificateResponse.json`, `SignedFirmwareStatusNotificationRequest.json`, `SignedFirmwareStatusNotificationResponse.json`, `SignedUpdateFirmwareRequest.json`, `SignedUpdateFirmwareResponse.json`

