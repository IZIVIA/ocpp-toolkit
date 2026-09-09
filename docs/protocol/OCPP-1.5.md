# OCPP 1.5 — protocol reference

**Derived from the official OCPP JSON schemas, the version's `Actions` registry and the OCA
specification documents.** Maintained by hand: an action added to `Actions` or a schema change
belongs in this file in the same commit.

Derived from the official OCPP JSON schemas in `ocpp-1-5-json/src/main/resources/` and the action registry in `ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/common/enumeration/Actions.kt`. For the normative prose, see [SPECS.md](SPECS.md).

Every field is one line, keyed by its dotted JSON path, so a grep for a field name lands on a line that names its action, direction and constraints:

```bash
grep -n 'idTag' docs/protocol/OCPP-1.5.md
```

24 actions registered.

## Specification documents

Each action below cites the section and PDF page of the normative document. Page numbers are
PDF page positions in these documents, which OCA distributes at
<https://www.openchargealliance.org/downloads/>:

| document | role | pages |
|---|---|--:|
| OCPP 1.5 Specification | spec | 81 |
| OCPP 1.5 — a functional description | spec | 7 |

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| `authorize` | Charging Station -> CSMS | `Authorize.json` | `AuthorizeReq` / `AuthorizeResp` |
| `bootNotification` | Charging Station -> CSMS | `BootNotification.json` | `BootNotificationReq` / `BootNotificationResp` |
| `cancelReservation` | CSMS -> Charging Station | `CancelReservation.json` | `CancelReservationReq` / `CancelReservationResp` |
| `changeAvailability` | CSMS -> Charging Station | `ChangeAvailability.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| `changeConfiguration` | CSMS -> Charging Station | `ChangeConfiguration.json` | `ChangeConfigurationReq` / `ChangeConfigurationResp` |
| `clearCache` | CSMS -> Charging Station | `ClearCache.json` | `ClearCacheReq` / `ClearCacheResp` |
| `dataTransfer` | either side | `DataTransfer.json` | `DataTransferReq` / `DataTransferResp` |
| `diagnosticsStatusNotification` | Charging Station -> CSMS | `DiagnosticsStatusNotification.json` | `DiagnosticsStatusNotificationReq` / `DiagnosticsStatusNotificationResp` |
| `firmwareStatusNotification` | Charging Station -> CSMS | `FirmwareStatusNotification.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| `getConfiguration` | CSMS -> Charging Station | `GetConfiguration.json` | `GetConfigurationReq` / `GetConfigurationResp` |
| `getDiagnostics` | CSMS -> Charging Station | `GetDiagnostics.json` | `GetDiagnosticsReq` / `GetDiagnosticsResp` |
| `getLocalListVersion` | CSMS -> Charging Station | `GetLocalListVersion.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| `heartbeat` | Charging Station -> CSMS | `Heartbeat.json` | `HeartbeatReq` / `HeartbeatResp` |
| `meterValues` | Charging Station -> CSMS | `MeterValues.json` | `MeterValuesReq` / `MeterValuesResp` |
| `remoteStartTransaction` | CSMS -> Charging Station | `RemoteStartTransaction.json` | `RemoteStartTransactionReq` / `RemoteStartTransactionResp` |
| `remoteStopTransaction` | CSMS -> Charging Station | `RemoteStopTransaction.json` | `RemoteStopTransactionReq` / `RemoteStopTransactionResp` |
| `reserveNow` | CSMS -> Charging Station | `ReserveNow.json` | `ReserveNowReq` / `ReserveNowResp` |
| `reset` | CSMS -> Charging Station | `Reset.json` | `ResetReq` / `ResetResp` |
| `sendLocalList` | CSMS -> Charging Station | `SendLocalList.json` | `SendLocalListReq` / `SendLocalListResp` |
| `startTransaction` | Charging Station -> CSMS | `StartTransaction.json` | `StartTransactionReq` / `StartTransactionResp` |
| `statusNotification` | Charging Station -> CSMS | `StatusNotification.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| `stopTransaction` | Charging Station -> CSMS | `StopTransaction.json` | `StopTransactionReq` / `StopTransactionResp` |
| `unlockConnector` | CSMS -> Charging Station | `UnlockConnector.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| `updateFirmware` | CSMS -> Charging Station | `UpdateFirmware.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

## authorize

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeReq.kt)
- Kotlin `AuthorizeResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/authorize/AuthorizeResp.kt)
- spec: `ocpp-1.5-specification` §4.1 Authorize — pdf-page 18
- spec: `ocpp-1.5-specification` §6.1 Authorize.req — pdf-page 41
- spec: `ocpp-1.5-specification` §6.2 Authorize.conf — pdf-page 41

### authorize request

- schema: [`Authorize.json`](../../ocpp-1-5-json/src/main/resources/Authorize.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.req.idTag` — string, required, maxLength 20

### authorize response

- schema: [`AuthorizeResponse.json`](../../ocpp-1-5-json/src/main/resources/AuthorizeResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `authorize.resp.idTagInfo` — object, required
- `authorize.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `authorize.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

## bootNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/bootnotification/BootNotificationResp.kt)
- spec: `ocpp-1.5-specification` §4.2 Boot Notification — pdf-page 19
- spec: `ocpp-1.5-specification` §6.3 BootNotification.req — pdf-page 41
- spec: `ocpp-1.5-specification` §6.4 BootNotification.conf — pdf-page 42

### bootNotification request

- schema: [`BootNotification.json`](../../ocpp-1-5-json/src/main/resources/BootNotification.json)
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

- schema: [`BootNotificationResponse.json`](../../ocpp-1-5-json/src/main/resources/BootNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.status` — string, required, enum: Accepted | Rejected
- `bootNotification.resp.currentTime` — string, required, format date-time
- `bootNotification.resp.heartbeatInterval` — integer, required

## cancelReservation

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CANCELRESERVATION`
- Kotlin `CancelReservationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationReq.kt)
- Kotlin `CancelReservationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/cancelreservation/CancelReservationResp.kt)
- spec: `ocpp-1.5-specification` §5.1 Cancel Reservation — pdf-page 27
- spec: `ocpp-1.5-specification` §6.5 CancelReservation.req — pdf-page 43
- spec: `ocpp-1.5-specification` §6.6 CancelReservation.conf — pdf-page 43
- spec: `ocpp-1.5-specification` §7.5 CancelReservationStatus — pdf-page 63

### cancelReservation request

- schema: [`CancelReservation.json`](../../ocpp-1-5-json/src/main/resources/CancelReservation.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.req.reservationId` — integer, required

### cancelReservation response

- schema: [`CancelReservationResponse.json`](../../ocpp-1-5-json/src/main/resources/CancelReservationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `cancelReservation.resp.status` — string, required, enum: Accepted | Rejected

## changeAvailability

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGEAVAILABILITY`
- Kotlin `ChangeAvailabilityReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityReq.kt)
- Kotlin `ChangeAvailabilityResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeavailability/ChangeAvailabilityResp.kt)
- spec: `ocpp-1.5-specification` §5.2 Change Availability — pdf-page 28
- spec: `ocpp-1.5-specification` §6.7 ChangeAvailability.req — pdf-page 43
- spec: `ocpp-1.5-specification` §6.8 ChangeAvailability.conf — pdf-page 44

### changeAvailability request

- schema: [`ChangeAvailability.json`](../../ocpp-1-5-json/src/main/resources/ChangeAvailability.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.req.connectorId` — integer, required
- `changeAvailability.req.type` — string, required, enum: Inoperative | Operative

### changeAvailability response

- schema: [`ChangeAvailabilityResponse.json`](../../ocpp-1-5-json/src/main/resources/ChangeAvailabilityResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeAvailability.resp.status` — string, required, enum: Accepted | Rejected | Scheduled

## changeConfiguration

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CHANGECONFIGURATION`
- Kotlin `ChangeConfigurationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationReq.kt)
- Kotlin `ChangeConfigurationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/changeconfiguration/ChangeConfigurationResp.kt)
- spec: `ocpp-1.5-specification` §5.3 Change Configuration — pdf-page 29
- spec: `ocpp-1.5-specification` §6.9 ChangeConfiguration.req — pdf-page 44
- spec: `ocpp-1.5-specification` §6.10 ChangeConfiguration.conf — pdf-page 47

### changeConfiguration request

- schema: [`ChangeConfiguration.json`](../../ocpp-1-5-json/src/main/resources/ChangeConfiguration.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.req.key` — string, required, maxLength 50
- `changeConfiguration.req.value` — string, required, maxLength 500

### changeConfiguration response

- schema: [`ChangeConfigurationResponse.json`](../../ocpp-1-5-json/src/main/resources/ChangeConfigurationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `changeConfiguration.resp.status` — string, required, enum: Accepted | Rejected | NotSupported

## clearCache

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.CLEARCACHE`
- Kotlin `ClearCacheReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheReq.kt)
- Kotlin `ClearCacheResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/clearcache/ClearCacheResp.kt)
- spec: `ocpp-1.5-specification` §5.4 Clear Cache — pdf-page 30
- spec: `ocpp-1.5-specification` §6.11 ClearCache.req — pdf-page 47
- spec: `ocpp-1.5-specification` §6.12 ClearCache.conf — pdf-page 47
- spec: `ocpp-1.5-specification` §7.12 ClearCacheStatus — pdf-page 66

### clearCache request

- schema: [`ClearCache.json`](../../ocpp-1-5-json/src/main/resources/ClearCache.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### clearCache response

- schema: [`ClearCacheResponse.json`](../../ocpp-1-5-json/src/main/resources/ClearCacheResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `clearCache.resp.status` — string, required, enum: Accepted | Rejected

## dataTransfer

- direction: **either side** (`OcppInitiator.ALL`)
- registry entry: `Actions.DATATRANSFER`
- Kotlin `DataTransferReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferReq.kt)
- Kotlin `DataTransferResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/datatransfer/DataTransferResp.kt)
- spec: `ocpp-1.5-specification` §4.3 Data Transfer — pdf-page 20
- spec: `ocpp-1.5-specification` §5.5 Data Transfer — pdf-page 31
- spec: `ocpp-1.5-specification` §6.13 DataTransfer.req — pdf-page 47
- spec: `ocpp-1.5-specification` §6.14 DataTransfer.conf — pdf-page 48
- spec: `ocpp-1.5-specification` §7.14 DataTransferStatus — pdf-page 66

### dataTransfer request

- schema: [`DataTransfer.json`](../../ocpp-1-5-json/src/main/resources/DataTransfer.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.req.vendorId` — string, required, maxLength 255
- `dataTransfer.req.messageId` — string, optional, maxLength 50
- `dataTransfer.req.data` — string, optional

### dataTransfer response

- schema: [`DataTransferResponse.json`](../../ocpp-1-5-json/src/main/resources/DataTransferResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `dataTransfer.resp.status` — string, required, enum: Accepted | Rejected | UnknownMessageId | UnknownVendorId
- `dataTransfer.resp.data` — string, optional

## diagnosticsStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.DIAGNOSTICSSTATUSNOTIFICATION`
- Kotlin `DiagnosticsStatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationReq.kt)
- Kotlin `DiagnosticsStatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/diagnosticsstatusnotification/DiagnosticsStatusNotificationResp.kt)
- spec: `ocpp-1.5-specification` §4.4 Diagnostics Status Notification — pdf-page 21
- spec: `ocpp-1.5-specification` §6.15 DiagnosticsStatusNotification.req — pdf-page 48
- spec: `ocpp-1.5-specification` §6.16 DiagnosticsStatusNotification.conf — pdf-page 48

### diagnosticsStatusNotification request

- schema: [`DiagnosticsStatusNotification.json`](../../ocpp-1-5-json/src/main/resources/DiagnosticsStatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `diagnosticsStatusNotification.req.status` — string, required, enum: Idle | Uploaded | UploadFailed | Uploading

### diagnosticsStatusNotification response

- schema: [`DiagnosticsStatusNotificationResponse.json`](../../ocpp-1-5-json/src/main/resources/DiagnosticsStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## firmwareStatusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.FIRMWARESTATUSNOTIFICATION`
- Kotlin `FirmwareStatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationReq.kt)
- Kotlin `FirmwareStatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/firmwarestatusnotification/FirmwareStatusNotificationResp.kt)
- spec: `ocpp-1.5-specification` §4.5 Firmware Status Notification — pdf-page 21
- spec: `ocpp-1.5-specification` §6.17 FirmwareStatusNotification.req — pdf-page 49
- spec: `ocpp-1.5-specification` §6.18 FirmwareStatusNotification.conf — pdf-page 49

### firmwareStatusNotification request

- schema: [`FirmwareStatusNotification.json`](../../ocpp-1-5-json/src/main/resources/FirmwareStatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `firmwareStatusNotification.req.status` — string, required, enum: Downloaded | DownloadFailed | Downloading | Idle | InstallationFailed | Installing | Installed

### firmwareStatusNotification response

- schema: [`FirmwareStatusNotificationResponse.json`](../../ocpp-1-5-json/src/main/resources/FirmwareStatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## getConfiguration

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETCONFIGURATION`
- Kotlin `GetConfigurationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationReq.kt)
- Kotlin `GetConfigurationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getconfiguration/GetConfigurationResp.kt)
- spec: `ocpp-1.5-specification` §5.6 Get Configuration — pdf-page 31
- spec: `ocpp-1.5-specification` §6.19 GetConfiguration.req — pdf-page 49
- spec: `ocpp-1.5-specification` §6.20 GetConfiguration.conf — pdf-page 49

### getConfiguration request

- schema: [`GetConfiguration.json`](../../ocpp-1-5-json/src/main/resources/GetConfiguration.json)
- `additionalProperties: false` — an unknown field fails validation

- `getConfiguration.req.key` — array, optional
- `getConfiguration.req.key[]` — string, maxLength 50

### getConfiguration response

- schema: [`GetConfigurationResponse.json`](../../ocpp-1-5-json/src/main/resources/GetConfigurationResponse.json)
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
- Kotlin `GetDiagnosticsReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsReq.kt)
- Kotlin `GetDiagnosticsResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getdiagnostics/GetDiagnosticsResp.kt)
- spec: `ocpp-1.5-specification` §5.7 Get Diagnostics — pdf-page 32
- spec: `ocpp-1.5-specification` §6.21 GetDiagnostics.req — pdf-page 50
- spec: `ocpp-1.5-specification` §6.22 GetDiagnostics.conf — pdf-page 50

### getDiagnostics request

- schema: [`GetDiagnostics.json`](../../ocpp-1-5-json/src/main/resources/GetDiagnostics.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.req.location` — string, required, format uri
- `getDiagnostics.req.retries` — integer, optional
- `getDiagnostics.req.retryInterval` — integer, optional
- `getDiagnostics.req.startTime` — string, optional, format date-time
- `getDiagnostics.req.stopTime` — string, optional, format date-time

### getDiagnostics response

- schema: [`GetDiagnosticsResponse.json`](../../ocpp-1-5-json/src/main/resources/GetDiagnosticsResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getDiagnostics.resp.fileName` — string, optional, maxLength 255

## getLocalListVersion

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETLOCALLISTVERSION`
- Kotlin `GetLocalListVersionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionReq.kt)
- Kotlin `GetLocalListVersionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/getlocallistversion/GetLocalListVersionResp.kt)
- spec: `ocpp-1.5-specification` §5.8 Get Local List Version — pdf-page 33
- spec: `ocpp-1.5-specification` §6.23 GetLocalListVersion.req — pdf-page 51
- spec: `ocpp-1.5-specification` §6.24 GetLocalListVersion.conf — pdf-page 51

### getLocalListVersion request

- schema: [`GetLocalListVersion.json`](../../ocpp-1-5-json/src/main/resources/GetLocalListVersion.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### getLocalListVersion response

- schema: [`GetLocalListVersionResponse.json`](../../ocpp-1-5-json/src/main/resources/GetLocalListVersionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `getLocalListVersion.resp.listVersion` — integer, required

## heartbeat

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.HEARTBEAT`
- Kotlin `HeartbeatReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatReq.kt)
- Kotlin `HeartbeatResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/heartbeat/HeartbeatResp.kt)
- spec: `ocpp-1.5-specification` §4.6 Heartbeat — pdf-page 22
- spec: `ocpp-1.5-specification` §6.25 Heartbeat.req — pdf-page 51
- spec: `ocpp-1.5-specification` §6.26 Heartbeat.conf — pdf-page 51

### heartbeat request

- schema: [`Heartbeat.json`](../../ocpp-1-5-json/src/main/resources/Heartbeat.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### heartbeat response

- schema: [`HeartbeatResponse.json`](../../ocpp-1-5-json/src/main/resources/HeartbeatResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `heartbeat.resp.currentTime` — string, required, format date-time

## meterValues

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.METERVALUES`
- Kotlin `MeterValuesReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesReq.kt)
- Kotlin `MeterValuesResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/metervalues/MeterValuesResp.kt)
- spec: `ocpp-1.5-specification` §4.7 Meter Values — pdf-page 23
- spec: `ocpp-1.5-specification` §6.27 MeterValues.req — pdf-page 52
- spec: `ocpp-1.5-specification` §6.28 MeterValues.conf — pdf-page 52

### meterValues request

- schema: [`MeterValues.json`](../../ocpp-1-5-json/src/main/resources/MeterValues.json)
- `additionalProperties: false` — an unknown field fails validation

- `meterValues.req.connectorId` — integer, required
- `meterValues.req.transactionId` — integer, optional
- `meterValues.req.values` — array, optional
- `meterValues.req.values[].timestamp` — string, required, format date-time
- `meterValues.req.values[].value` — array, required
- `meterValues.req.values[].value[].value` — string, required
- `meterValues.req.values[].value[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End
- `meterValues.req.values[].value[].format` — string, optional, enum: Raw | SignedData
- `meterValues.req.values[].value[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Reactive.Export | Power.Reactive.Import | Current.Import | Current.Export | Voltage | Temperature
- `meterValues.req.values[].value[].location` — string, optional, enum: Inlet | Outlet | Body
- `meterValues.req.values[].value[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | var | kvar | Amp | Volt | Celsius

### meterValues response

- schema: [`MeterValuesResponse.json`](../../ocpp-1-5-json/src/main/resources/MeterValuesResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## remoteStartTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTARTTRANSACTION`
- Kotlin `RemoteStartTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionReq.kt)
- Kotlin `RemoteStartTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestart/RemoteStartTransactionResp.kt)
- spec: `ocpp-1.5-specification` §5.9 Remote Start Transaction — pdf-page 34
- spec: `ocpp-1.5-specification` §6.29 RemoteStartTransaction.req — pdf-page 52
- spec: `ocpp-1.5-specification` §6.30 RemoteStartTransaction.conf — pdf-page 53

### remoteStartTransaction request

- schema: [`RemoteStartTransaction.json`](../../ocpp-1-5-json/src/main/resources/RemoteStartTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.req.connectorId` — integer, optional
- `remoteStartTransaction.req.idTag` — string, required, maxLength 20

### remoteStartTransaction response

- schema: [`RemoteStartTransactionResponse.json`](../../ocpp-1-5-json/src/main/resources/RemoteStartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStartTransaction.resp.status` — string, required, enum: Accepted | Rejected

## remoteStopTransaction

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.REMOTESTOPTRANSACTION`
- Kotlin `RemoteStopTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionReq.kt)
- Kotlin `RemoteStopTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/remotestop/RemoteStopTransactionResp.kt)
- spec: `ocpp-1.5-specification` §5.10 Remote Stop Transaction — pdf-page 35
- spec: `ocpp-1.5-specification` §6.31 RemoteStopTransaction.req — pdf-page 53
- spec: `ocpp-1.5-specification` §6.32 RemoteStopTransaction.conf — pdf-page 53

### remoteStopTransaction request

- schema: [`RemoteStopTransaction.json`](../../ocpp-1-5-json/src/main/resources/RemoteStopTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.req.transactionId` — integer, required

### remoteStopTransaction response

- schema: [`RemoteStopTransactionResponse.json`](../../ocpp-1-5-json/src/main/resources/RemoteStopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `remoteStopTransaction.resp.status` — string, required, enum: Accepted | Rejected

## reserveNow

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESERVENOW`
- Kotlin `ReserveNowReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowReq.kt)
- Kotlin `ReserveNowResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reservenow/ReserveNowResp.kt)
- spec: `ocpp-1.5-specification` §5.11 Reserve Now — pdf-page 35
- spec: `ocpp-1.5-specification` §6.33 ReserveNow.req — pdf-page 54
- spec: `ocpp-1.5-specification` §6.34 ReserveNow.conf — pdf-page 54

### reserveNow request

- schema: [`ReserveNow.json`](../../ocpp-1-5-json/src/main/resources/ReserveNow.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.req.connectorId` — integer, required
- `reserveNow.req.expiryDate` — string, required, format date-time
- `reserveNow.req.idTag` — string, required, maxLength 20
- `reserveNow.req.parentIdTag` — string, optional, maxLength 20
- `reserveNow.req.reservationId` — integer, required

### reserveNow response

- schema: [`ReserveNowResponse.json`](../../ocpp-1-5-json/src/main/resources/ReserveNowResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reserveNow.resp.status` — string, required, enum: Accepted | Faulted | Occupied | Rejected | Unavailable

## reset

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.RESET`
- Kotlin `ResetReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetReq.kt)
- Kotlin `ResetResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/reset/ResetResp.kt)
- spec: `ocpp-1.5-specification` §5.12 Reset — pdf-page 37
- spec: `ocpp-1.5-specification` §6.35 Reset.req — pdf-page 54
- spec: `ocpp-1.5-specification` §6.36 Reset.conf — pdf-page 55
- spec: `ocpp-1.5-specification` §7.33 ResetStatus — pdf-page 74
- spec: `ocpp-1.5-specification` §7.34 ResetType — pdf-page 74

### reset request

- schema: [`Reset.json`](../../ocpp-1-5-json/src/main/resources/Reset.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.req.type` — string, required, enum: Hard | Soft

### reset response

- schema: [`ResetResponse.json`](../../ocpp-1-5-json/src/main/resources/ResetResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `reset.resp.status` — string, required, enum: Accepted | Rejected

## sendLocalList

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SENDLOCALLIST`
- Kotlin `SendLocalListReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListReq.kt)
- Kotlin `SendLocalListResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/sendlocallist/SendLocalListResp.kt)
- spec: `ocpp-1.5-specification` §5.13 Send Local List — pdf-page 38
- spec: `ocpp-1.5-specification` §6.37 SendLocalList.req — pdf-page 55
- spec: `ocpp-1.5-specification` §6.38 SendLocalList.conf — pdf-page 56

### sendLocalList request

- schema: [`SendLocalList.json`](../../ocpp-1-5-json/src/main/resources/SendLocalList.json)
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

- schema: [`SendLocalListResponse.json`](../../ocpp-1-5-json/src/main/resources/SendLocalListResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `sendLocalList.resp.hash` — string, optional, maxLength 64
- `sendLocalList.resp.status` — string, required, enum: Accepted | Failed | HashError | NotSupported | VersionMismatch

## startTransaction

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STARTTRANSACTION`
- Kotlin `StartTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionReq.kt)
- Kotlin `StartTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/starttransaction/StartTransactionResp.kt)
- spec: `ocpp-1.5-specification` §4.8 Start Transaction — pdf-page 24
- spec: `ocpp-1.5-specification` §6.39 StartTransaction.req — pdf-page 56
- spec: `ocpp-1.5-specification` §6.40 StartTransaction.conf — pdf-page 57

### startTransaction request

- schema: [`StartTransaction.json`](../../ocpp-1-5-json/src/main/resources/StartTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.req.connectorId` — integer, required
- `startTransaction.req.idTag` — string, required, maxLength 20
- `startTransaction.req.meterStart` — integer, required
- `startTransaction.req.reservationId` — integer, optional
- `startTransaction.req.timestamp` — string, required, format date-time

### startTransaction response

- schema: [`StartTransactionResponse.json`](../../ocpp-1-5-json/src/main/resources/StartTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `startTransaction.resp.idTagInfo` — object, required
- `startTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `startTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `startTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
- `startTransaction.resp.transactionId` — integer, required

## statusNotification

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STATUSNOTIFICATION`
- Kotlin `StatusNotificationReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationReq.kt)
- Kotlin `StatusNotificationResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/statusnotification/StatusNotificationResp.kt)
- spec: `ocpp-1.5-specification` §4.9 Status Notification — pdf-page 25
- spec: `ocpp-1.5-specification` §6.41 StatusNotification.req — pdf-page 57
- spec: `ocpp-1.5-specification` §6.42 StatusNotification.conf — pdf-page 58

### statusNotification request

- schema: [`StatusNotification.json`](../../ocpp-1-5-json/src/main/resources/StatusNotification.json)
- `additionalProperties: false` — an unknown field fails validation

- `statusNotification.req.connectorId` — integer, required
- `statusNotification.req.errorCode` — string, required, enum: ConnectorLockFailure | GroundFailure | HighTemperature | Mode3Error | NoError | OtherError | OverCurrentFailure | PowerMeterFailure | PowerSwitchFailure | ReaderFailure | ResetFailure | UnderVoltage | WeakSignal
- `statusNotification.req.info` — string, optional, maxLength 50
- `statusNotification.req.status` — string, required, enum: Available | Occupied | Reserved | Unavailable | Faulted
- `statusNotification.req.timestamp` — string, optional, format date-time
- `statusNotification.req.vendorId` — string, optional, maxLength 255
- `statusNotification.req.vendorErrorCode` — string, optional, maxLength 50

### statusNotification response

- schema: [`StatusNotificationResponse.json`](../../ocpp-1-5-json/src/main/resources/StatusNotificationResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## stopTransaction

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.STOPTRANSACTION`
- Kotlin `StopTransactionReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionReq.kt)
- Kotlin `StopTransactionResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/stoptransaction/StopTransactionResp.kt)
- spec: `ocpp-1.5-specification` §4.10 Stop Transaction — pdf-page 26
- spec: `ocpp-1.5-specification` §6.43 StopTransaction.req — pdf-page 58
- spec: `ocpp-1.5-specification` §6.44 StopTransaction.conf — pdf-page 59

### stopTransaction request

- schema: [`StopTransaction.json`](../../ocpp-1-5-json/src/main/resources/StopTransaction.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.req.idTag` — string, optional, maxLength 20
- `stopTransaction.req.meterStop` — integer, required
- `stopTransaction.req.timestamp` — string, required, format date-time
- `stopTransaction.req.transactionId` — integer, required
- `stopTransaction.req.transactionData` — array, optional
- `stopTransaction.req.transactionData[].values` — array, optional
- `stopTransaction.req.transactionData[].values[].timestamp` — string, required, format date-time
- `stopTransaction.req.transactionData[].values[].value` — array, required
- `stopTransaction.req.transactionData[].values[].value[].value` — string, required
- `stopTransaction.req.transactionData[].values[].value[].context` — string, optional, enum: Interruption.Begin | Interruption.End | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End
- `stopTransaction.req.transactionData[].values[].value[].format` — string, optional, enum: Raw | SignedData
- `stopTransaction.req.transactionData[].values[].value[].measurand` — string, optional, enum: Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Power.Active.Export | Power.Active.Import | Power.Reactive.Export | Power.Reactive.Import | Current.Import | Current.Export | Voltage | Temperature
- `stopTransaction.req.transactionData[].values[].value[].location` — string, optional, enum: Inlet | Outlet | Body
- `stopTransaction.req.transactionData[].values[].value[].unit` — string, optional, enum: Wh | kWh | varh | kvarh | W | kW | var | kvar | Amp | Volt | Celsius

### stopTransaction response

- schema: [`StopTransactionResponse.json`](../../ocpp-1-5-json/src/main/resources/StopTransactionResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `stopTransaction.resp.idTagInfo` — object, optional
- `stopTransaction.resp.idTagInfo.expiryDate` — string, optional, format date-time
- `stopTransaction.resp.idTagInfo.parentIdTag` — string, optional, maxLength 20
- `stopTransaction.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx

## unlockConnector

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/unlockconnector/UnlockConnectorResp.kt)
- spec: `ocpp-1.5-specification` §5.14 Unlock Connector — pdf-page 39
- spec: `ocpp-1.5-specification` §6.45 UnlockConnector.req — pdf-page 60
- spec: `ocpp-1.5-specification` §6.46 UnlockConnector.conf — pdf-page 60

### unlockConnector request

- schema: [`UnlockConnector.json`](../../ocpp-1-5-json/src/main/resources/UnlockConnector.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.connectorId` — integer, required

### unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../ocpp-1-5-json/src/main/resources/UnlockConnectorResponse.json)
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.status` — string, required, enum: Accepted | Rejected

## updateFirmware

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UPDATEFIRMWARE`
- Kotlin `UpdateFirmwareReq`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareReq.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareReq.kt)
- Kotlin `UpdateFirmwareResp`: [`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareResp.kt`](../../ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/updatefirmware/UpdateFirmwareResp.kt)
- spec: `ocpp-1.5-specification` §5.15 Update Firmware — pdf-page 40
- spec: `ocpp-1.5-specification` §6.47 UpdateFirmware.req — pdf-page 60
- spec: `ocpp-1.5-specification` §6.48 UpdateFirmware.conf — pdf-page 61

### updateFirmware request

- schema: [`UpdateFirmware.json`](../../ocpp-1-5-json/src/main/resources/UpdateFirmware.json)
- `additionalProperties: false` — an unknown field fails validation

- `updateFirmware.req.location` — string, required, format uri
- `updateFirmware.req.retries` — integer, optional
- `updateFirmware.req.retrieveDate` — string, required, format date-time
- `updateFirmware.req.retryInterval` — integer, optional

### updateFirmware response

- schema: [`UpdateFirmwareResponse.json`](../../ocpp-1-5-json/src/main/resources/UpdateFirmwareResponse.json)
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

## Schema coverage

- 48 of 48 shipped schema files are reachable from `Actions`.
- Actions and schema files correspond exactly.

## Declared JSON Schema draft

`ocpp-1-5-json`'s parser validates every payload with `SpecVersion.VersionFlag.V4`. What the files themselves declare:

- `http://json-schema.org/draft-04/schema#` — 48 file(s)

