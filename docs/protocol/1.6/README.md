# OCPP 1.6 — protocol reference

Derived from the official OCPP JSON schemas in `ocpp-1-6-json/src/main/resources/`, the action registry in
`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/common/enumeration/Actions.kt`, and the OCA specification documents. **Maintained by hand:** an action
added to `Actions`, or a schema change, belongs here in the same commit.

For the normative prose, see [SPECS.md](../SPECS.md) and the
[document tables of contents](../spec/1.6.md).

39 actions registered.

One file per action. Each field is one line keyed by its dotted JSON path:

```bash
grep -rn 'idTag' docs/protocol/1.6/
cat docs/protocol/1.6/authorize.md
```

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| [`authorize`](authorize.md) | Charging Station -> CSMS | `AuthorizeRequest.json` | `AuthorizeReq` / `AuthorizeResp` |
| [`bootNotification`](bootNotification.md) | Charging Station -> CSMS | `BootNotificationRequest.json` | `BootNotificationReq` / `BootNotificationResp` |
| [`cancelReservation`](cancelReservation.md) | CSMS -> Charging Station | `CancelReservationRequest.json` | `CancelReservationReq` / `CancelReservationResp` |
| [`certificateSigned`](certificateSigned.md) | CSMS -> Charging Station | `CertificateSignedRequest.json` | `CertificateSignedReq` / `CertificateSignedResp` |
| [`changeAvailability`](changeAvailability.md) | CSMS -> Charging Station | `ChangeAvailabilityRequest.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| [`changeConfiguration`](changeConfiguration.md) | CSMS -> Charging Station | `ChangeConfigurationRequest.json` | `ChangeConfigurationReq` / `ChangeConfigurationResp` |
| [`clearCache`](clearCache.md) | CSMS -> Charging Station | `ClearCacheRequest.json` | `ClearCacheReq` / `ClearCacheResp` |
| [`clearChargingProfile`](clearChargingProfile.md) | CSMS -> Charging Station | `ClearChargingProfileRequest.json` | `ClearChargingProfileReq` / `ClearChargingProfileResp` |
| [`dataTransfer`](dataTransfer.md) | either side | `DataTransferRequest.json` | `DataTransferReq` / `DataTransferResp` |
| [`deleteCertificate`](deleteCertificate.md) | CSMS -> Charging Station | `DeleteCertificateRequest.json` | `DeleteCertificateReq` / `DeleteCertificateResp` |
| [`diagnosticsStatusNotification`](diagnosticsStatusNotification.md) | Charging Station -> CSMS | `DiagnosticsStatusNotificationRequest.json` | `DiagnosticsStatusNotificationReq` / `DiagnosticsStatusNotificationResp` |
| [`extendedTriggerMessage`](extendedTriggerMessage.md) | CSMS -> Charging Station | `ExtendedTriggerMessageRequest.json` | `ExtendedTriggerMessageReq` / `ExtendedTriggerMessageResp` |
| [`firmwareStatusNotification`](firmwareStatusNotification.md) | Charging Station -> CSMS | `FirmwareStatusNotificationRequest.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| [`getCompositeSchedule`](getCompositeSchedule.md) | CSMS -> Charging Station | `GetCompositeScheduleRequest.json` | `GetCompositeScheduleReq` / `GetCompositeScheduleResp` |
| [`getConfiguration`](getConfiguration.md) | CSMS -> Charging Station | `GetConfigurationRequest.json` | `GetConfigurationReq` / `GetConfigurationResp` |
| [`getDiagnostics`](getDiagnostics.md) | CSMS -> Charging Station | `GetDiagnosticsRequest.json` | `GetDiagnosticsReq` / `GetDiagnosticsResp` |
| [`getInstalledCertificateIds`](getInstalledCertificateIds.md) | CSMS -> Charging Station | `GetInstalledCertificateIdsRequest.json` | `GetInstalledCertificateIdsReq` / `GetInstalledCertificateIdsResp` |
| [`getLocalListVersion`](getLocalListVersion.md) | CSMS -> Charging Station | `GetLocalListVersionRequest.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| [`getLog`](getLog.md) | CSMS -> Charging Station | `GetLogRequest.json` | `GetLogReq` / `GetLogResp` |
| [`heartbeat`](heartbeat.md) | Charging Station -> CSMS | `HeartbeatRequest.json` | `HeartbeatReq` / `HeartbeatResp` |
| [`installCertificate`](installCertificate.md) | CSMS -> Charging Station | `InstallCertificateRequest.json` | `InstallCertificateReq` / `InstallCertificateResp` |
| [`logStatusNotification`](logStatusNotification.md) | Charging Station -> CSMS | `LogStatusNotificationRequest.json` | `LogStatusNotificationReq` / `LogStatusNotificationResp` |
| [`meterValues`](meterValues.md) | Charging Station -> CSMS | `MeterValuesRequest.json` | `MeterValuesReq` / `MeterValuesResp` |
| [`remoteStartTransaction`](remoteStartTransaction.md) | CSMS -> Charging Station | `RemoteStartTransactionRequest.json` | `RemoteStartTransactionReq` / `RemoteStartTransactionResp` |
| [`remoteStopTransaction`](remoteStopTransaction.md) | CSMS -> Charging Station | `RemoteStopTransactionRequest.json` | `RemoteStopTransactionReq` / `RemoteStopTransactionResp` |
| [`reserveNow`](reserveNow.md) | CSMS -> Charging Station | `ReserveNowRequest.json` | `ReserveNowReq` / `ReserveNowResp` |
| [`reset`](reset.md) | CSMS -> Charging Station | `ResetRequest.json` | `ResetReq` / `ResetResp` |
| [`securityEventNotification`](securityEventNotification.md) | Charging Station -> CSMS | `SecurityEventNotificationRequest.json` | `SecurityEventNotificationReq` / `SecurityEventNotificationResp` |
| [`sendLocalList`](sendLocalList.md) | CSMS -> Charging Station | `SendLocalListRequest.json` | `SendLocalListReq` / `SendLocalListResp` |
| [`setChargingProfile`](setChargingProfile.md) | CSMS -> Charging Station | `SetChargingProfileRequest.json` | `SetChargingProfileReq` / `SetChargingProfileResp` |
| [`signCertificate`](signCertificate.md) | Charging Station -> CSMS | `SignCertificateRequest.json` | `SignCertificateReq` / `SignCertificateResp` |
| [`signedFirmwareStatusNotification`](signedFirmwareStatusNotification.md) | Charging Station -> CSMS | `SignedFirmwareStatusNotificationRequest.json` | `SignedFirmwareStatusNotificationReq` / `SignedFirmwareStatusNotificationResp` |
| [`signedUpdateFirmware`](signedUpdateFirmware.md) | CSMS -> Charging Station | `SignedUpdateFirmwareRequest.json` | `SignedUpdateFirmwareReq` / `SignedUpdateFirmwareResp` |
| [`startTransaction`](startTransaction.md) | Charging Station -> CSMS | `StartTransactionRequest.json` | `StartTransactionReq` / `StartTransactionResp` |
| [`statusNotification`](statusNotification.md) | Charging Station -> CSMS | `StatusNotificationRequest.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| [`stopTransaction`](stopTransaction.md) | Charging Station -> CSMS | `StopTransactionRequest.json` | `StopTransactionReq` / `StopTransactionResp` |
| [`triggerMessage`](triggerMessage.md) | CSMS -> Charging Station | `TriggerMessageRequest.json` | `TriggerMessageReq` / `TriggerMessageResp` |
| [`unlockConnector`](unlockConnector.md) | CSMS -> Charging Station | `UnlockConnectorRequest.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| [`updateFirmware`](updateFirmware.md) | CSMS -> Charging Station | `UpdateFirmwareRequest.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

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

## Schema coverage

- 78 of 78 shipped schema files are reachable from `Actions`.
- Actions and schema files correspond exactly.

## Declared JSON Schema draft

`ocpp-1-6-json`'s parser validates every payload with `SpecVersion.VersionFlag.V4`. What the files themselves declare:

- `http://json-schema.org/draft-04/schema#` — 56 file(s)
- `http://json-schema.org/draft-06/schema#` — 22 file(s)

**Mismatch:** the validator is fixed at `V4` (`http://json-schema.org/draft-04/schema#`), so these files are validated against a different draft than they declare:

- `http://json-schema.org/draft-06/schema#`: `CertificateSignedRequest.json`, `CertificateSignedResponse.json`, `DeleteCertificateRequest.json`, `DeleteCertificateResponse.json`, `ExtendedTriggerMessageRequest.json`, `ExtendedTriggerMessageResponse.json`, `GetInstalledCertificateIdsRequest.json`, `GetInstalledCertificateIdsResponse.json`, `GetLogRequest.json`, `GetLogResponse.json`, `InstallCertificateRequest.json`, `InstallCertificateResponse.json`, `LogStatusNotificationRequest.json`, `LogStatusNotificationResponse.json`, `SecurityEventNotificationRequest.json`, `SecurityEventNotificationResponse.json`, `SignCertificateRequest.json`, `SignCertificateResponse.json`, `SignedFirmwareStatusNotificationRequest.json`, `SignedFirmwareStatusNotificationResponse.json`, `SignedUpdateFirmwareRequest.json`, `SignedUpdateFirmwareResponse.json`

---

[cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
