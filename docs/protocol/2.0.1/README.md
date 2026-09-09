# OCPP 2.0.1 — protocol reference

Derived from the official OCPP JSON schemas in `ocpp-2-0-json/src/main/resources/`, the action registry in
`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/common/enumeration/Actions.kt`, and the OCA specification documents. **Maintained by hand:** an action
added to `Actions`, or a schema change, belongs here in the same commit.

For the normative prose, see [SPECS.md](../SPECS.md) and the
[document tables of contents](../spec/2.0.1.md).

64 actions registered.

One file per action. Each field is one line keyed by its dotted JSON path:

```bash
grep -rn 'idTag' docs/protocol/2.0.1/
cat docs/protocol/2.0.1/authorize.md
```

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| [`authorize`](authorize.md) | Charging Station -> CSMS | `AuthorizeRequest.json` | `AuthorizeReq` / `AuthorizeResp` |
| [`bootNotification`](bootNotification.md) | Charging Station -> CSMS | `BootNotificationRequest.json` | `BootNotificationReq` / `BootNotificationResp` |
| [`cancelReservation`](cancelReservation.md) | CSMS -> Charging Station | `CancelReservationRequest.json` | `CancelReservationReq` / `CancelReservationResp` |
| [`certificateSigned`](certificateSigned.md) | CSMS -> Charging Station | `CertificateSignedRequest.json` | `CertificateSignedReq` / `CertificateSignedResp` |
| [`changeAvailability`](changeAvailability.md) | CSMS -> Charging Station | `ChangeAvailabilityRequest.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| [`clearCache`](clearCache.md) | CSMS -> Charging Station | `ClearCacheRequest.json` | `ClearCacheReq` / `ClearCacheResp` |
| [`clearChargingProfile`](clearChargingProfile.md) | CSMS -> Charging Station | `ClearChargingProfileRequest.json` | `ClearChargingProfileReq` / `ClearChargingProfileResp` |
| [`clearDisplayMessage`](clearDisplayMessage.md) | CSMS -> Charging Station | `ClearDisplayMessageRequest.json` | `ClearDisplayMessageReq` / `ClearDisplayMessageResp` |
| [`clearVariableMonitoring`](clearVariableMonitoring.md) | CSMS -> Charging Station | `ClearVariableMonitoringRequest.json` | `ClearVariableMonitoringReq` / `ClearVariableMonitoringResp` |
| [`clearedChargingLimit`](clearedChargingLimit.md) | Charging Station -> CSMS | `ClearedChargingLimitRequest.json` | `ClearedChargingLimitReq` / `ClearedChargingLimitResp` |
| [`costUpdated`](costUpdated.md) | CSMS -> Charging Station | `CostUpdatedRequest.json` | `CostUpdatedReq` / `CostUpdatedResp` |
| [`customerInformation`](customerInformation.md) | CSMS -> Charging Station | `CustomerInformationRequest.json` | `CustomerInformationReq` / `CustomerInformationResp` |
| [`dataTransfer`](dataTransfer.md) | either side | `DataTransferRequest.json` | `DataTransferReq` / `DataTransferResp` |
| [`deleteCertificate`](deleteCertificate.md) | CSMS -> Charging Station | `DeleteCertificateRequest.json` | `DeleteCertificateReq` / `DeleteCertificateResp` |
| [`firmwareStatusNotification`](firmwareStatusNotification.md) | Charging Station -> CSMS | `FirmwareStatusNotificationRequest.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| [`get15118EVCertificate`](get15118EVCertificate.md) | Charging Station -> CSMS | **missing** | `Get15118EVCertificateReq` / `Get15118EVCertificateResp` |
| [`getBaseReport`](getBaseReport.md) | CSMS -> Charging Station | `GetBaseReportRequest.json` | `GetBaseReportReq` / `GetBaseReportResp` |
| [`getCertificateStatus`](getCertificateStatus.md) | Charging Station -> CSMS | `GetCertificateStatusRequest.json` | `GetCertificateStatusReq` / `GetCertificateStatusResp` |
| [`getChargingProfiles`](getChargingProfiles.md) | CSMS -> Charging Station | `GetChargingProfilesRequest.json` | `GetChargingProfilesReq` / `GetChargingProfilesResp` |
| [`getCompositeSchedule`](getCompositeSchedule.md) | CSMS -> Charging Station | `GetCompositeScheduleRequest.json` | `GetCompositeScheduleReq` / `GetCompositeScheduleResp` |
| [`getDisplayMessages`](getDisplayMessages.md) | CSMS -> Charging Station | `GetDisplayMessagesRequest.json` | `GetDisplayMessagesReq` / `GetDisplayMessagesResp` |
| [`getInstalledCertificateIds`](getInstalledCertificateIds.md) | CSMS -> Charging Station | `GetInstalledCertificateIdsRequest.json` | `GetInstalledCertificateIdsReq` / `GetInstalledCertificateIdsResp` |
| [`getLocalListVersion`](getLocalListVersion.md) | CSMS -> Charging Station | `GetLocalListVersionRequest.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| [`getLog`](getLog.md) | CSMS -> Charging Station | `GetLogRequest.json` | `GetLogReq` / `GetLogResp` |
| [`getMonitoringReport`](getMonitoringReport.md) | CSMS -> Charging Station | `GetMonitoringReportRequest.json` | `GetMonitoringReportReq` / `GetMonitoringReportResp` |
| [`getReport`](getReport.md) | CSMS -> Charging Station | `GetReportRequest.json` | `GetReportReq` / `GetReportResp` |
| [`getTransactionStatus`](getTransactionStatus.md) | CSMS -> Charging Station | `GetTransactionStatusRequest.json` | `GetTransactionStatusReq` / `GetTransactionStatusResp` |
| [`getVariables`](getVariables.md) | CSMS -> Charging Station | `GetVariablesRequest.json` | `GetVariablesReq` / `GetVariablesResp` |
| [`heartbeat`](heartbeat.md) | Charging Station -> CSMS | `HeartbeatRequest.json` | `HeartbeatReq` / `HeartbeatResp` |
| [`installCertificate`](installCertificate.md) | CSMS -> Charging Station | `InstallCertificateRequest.json` | `InstallCertificateReq` / `InstallCertificateResp` |
| [`logStatusNotification`](logStatusNotification.md) | Charging Station -> CSMS | `LogStatusNotificationRequest.json` | `LogStatusNotificationReq` / `LogStatusNotificationResp` |
| [`meterValues`](meterValues.md) | Charging Station -> CSMS | `MeterValuesRequest.json` | `MeterValuesReq` / `MeterValuesResp` |
| [`notifyChargingLimit`](notifyChargingLimit.md) | Charging Station -> CSMS | `NotifyChargingLimitRequest.json` | `NotifyChargingLimitReq` / `NotifyChargingLimitResp` |
| [`notifyCustomerInformation`](notifyCustomerInformation.md) | Charging Station -> CSMS | `NotifyCustomerInformationRequest.json` | `NotifyCustomerInformationReq` / `NotifyCustomerInformationResp` |
| [`notifyDisplayMessages`](notifyDisplayMessages.md) | Charging Station -> CSMS | `NotifyDisplayMessagesRequest.json` | `NotifyDisplayMessagesReq` / `NotifyDisplayMessagesResp` |
| [`notifyEVChargingNeeds`](notifyEVChargingNeeds.md) | Charging Station -> CSMS | `NotifyEVChargingNeedsRequest.json` | `NotifyEVChargingNeedsReq` / `NotifyEVChargingNeedsResp` |
| [`notifyEVChargingSchedule`](notifyEVChargingSchedule.md) | Charging Station -> CSMS | `NotifyEVChargingScheduleRequest.json` | `NotifyEVChargingScheduleReq` / `NotifyEVChargingScheduleResp` |
| [`notifyEvent`](notifyEvent.md) | Charging Station -> CSMS | `NotifyEventRequest.json` | `NotifyEventReq` / `NotifyEventResp` |
| [`notifyMonitoringReport`](notifyMonitoringReport.md) | Charging Station -> CSMS | `NotifyMonitoringReportRequest.json` | `NotifyMonitoringReportReq` / `NotifyMonitoringReportResp` |
| [`notifyReport`](notifyReport.md) | Charging Station -> CSMS | `NotifyReportRequest.json` | `NotifyReportReq` / `NotifyReportResp` |
| [`publishFirmware`](publishFirmware.md) | CSMS -> Charging Station | `PublishFirmwareRequest.json` | `PublishFirmwareReq` / `PublishFirmwareResp` |
| [`publishFirmwareStatusNotification`](publishFirmwareStatusNotification.md) | Charging Station -> CSMS | `PublishFirmwareStatusNotificationRequest.json` | `PublishFirmwareStatusNotificationReq` / `PublishFirmwareStatusNotificationResp` |
| [`reportChargingProfiles`](reportChargingProfiles.md) | CSMS -> Charging Station | `ReportChargingProfilesRequest.json` | `ReportChargingProfilesReq` / `ReportChargingProfilesResp` |
| [`requestStartTransaction`](requestStartTransaction.md) | CSMS -> Charging Station | `RequestStartTransactionRequest.json` | `RequestStartTransactionReq` / `RequestStartTransactionResp` |
| [`requestStopTransaction`](requestStopTransaction.md) | CSMS -> Charging Station | `RequestStopTransactionRequest.json` | `RequestStopTransactionReq` / `RequestStopTransactionResp` |
| [`reservationStatusUpdate`](reservationStatusUpdate.md) | Charging Station -> CSMS | `ReservationStatusUpdateRequest.json` | `ReservationStatusUpdateReq` / `ReservationStatusUpdateResp` |
| [`reserveNow`](reserveNow.md) | CSMS -> Charging Station | `ReserveNowRequest.json` | `ReserveNowReq` / `ReserveNowResp` |
| [`reset`](reset.md) | CSMS -> Charging Station | `ResetRequest.json` | `ResetReq` / `ResetResp` |
| [`securityEventNotification`](securityEventNotification.md) | Charging Station -> CSMS | `SecurityEventNotificationRequest.json` | `SecurityEventNotificationReq` / `SecurityEventNotificationResp` |
| [`sendLocalList`](sendLocalList.md) | CSMS -> Charging Station | `SendLocalListRequest.json` | `SendLocalListReq` / `SendLocalListResp` |
| [`setChargingProfile`](setChargingProfile.md) | CSMS -> Charging Station | `SetChargingProfileRequest.json` | `SetChargingProfileReq` / `SetChargingProfileResp` |
| [`setDisplayMessage`](setDisplayMessage.md) | CSMS -> Charging Station | `SetDisplayMessageRequest.json` | `SetDisplayMessageReq` / `SetDisplayMessageResp` |
| [`setMonitoringBase`](setMonitoringBase.md) | CSMS -> Charging Station | `SetMonitoringBaseRequest.json` | `SetMonitoringBaseReq` / `SetMonitoringBaseResp` |
| [`setMonitoringLevel`](setMonitoringLevel.md) | CSMS -> Charging Station | `SetMonitoringLevelRequest.json` | `SetMonitoringLevelReq` / `SetMonitoringLevelResp` |
| [`setNetworkProfile`](setNetworkProfile.md) | CSMS -> Charging Station | `SetNetworkProfileRequest.json` | `SetNetworkProfileReq` / `SetNetworkProfileResp` |
| [`setVariableMonitoring`](setVariableMonitoring.md) | CSMS -> Charging Station | `SetVariableMonitoringRequest.json` | `SetVariableMonitoringReq` / `SetVariableMonitoringResp` |
| [`setVariables`](setVariables.md) | CSMS -> Charging Station | `SetVariablesRequest.json` | `SetVariablesReq` / `SetVariablesResp` |
| [`signCertificate`](signCertificate.md) | Charging Station -> CSMS | `SignCertificateRequest.json` | `SignCertificateReq` / `SignCertificateResp` |
| [`statusNotification`](statusNotification.md) | CSMS -> Charging Station | `StatusNotificationRequest.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| [`transactionEvent`](transactionEvent.md) | Charging Station -> CSMS | `TransactionEventRequest.json` | `TransactionEventReq` / `TransactionEventResp` |
| [`triggerMessage`](triggerMessage.md) | CSMS -> Charging Station | `TriggerMessageRequest.json` | `TriggerMessageReq` / `TriggerMessageResp` |
| [`unlockConnector`](unlockConnector.md) | CSMS -> Charging Station | `UnlockConnectorRequest.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| [`unpublishFirmware`](unpublishFirmware.md) | CSMS -> Charging Station | `UnpublishFirmwareRequest.json` | `UnpublishFirmwareReq` / `UnpublishFirmwareResp` |
| [`updateFirmware`](updateFirmware.md) | CSMS -> Charging Station | `UpdateFirmwareRequest.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

## Specification documents

Each action below cites the section and PDF page of the normative document. Page numbers are
PDF page positions in these documents, which OCA distributes at
<https://www.openchargealliance.org/downloads/>:

| document | role | pages |
|---|---|--:|
| OCPP 2.0.1 Part 0 — Introduction | spec | 15 |
| OCPP 2.0.1 Part 1 — Architecture & Topology | spec | 27 |
| OCPP 2.0.1 Part 2 — Specification (use cases & requirements) | spec | 459 |
| OCPP 2.0.1 Part 2 — Appendices | spec | 38 |
| OCPP 2.0.1 Part 4 — OCPP-J | transport | 27 |
| OCPP 2.0.1 Part 2 errata | errata | 75 |
| Changelog OCPP 2.0 -> 2.0.1 | changelog | 21 |
| OCPP 2.0 Part 1 errata (superseded by 2.0.1) | errata | 5 |
| OCPP 2.0 Part 2 errata (superseded by 2.0.1) | errata | 119 |
| OCPP 2.0 Part 4 errata (superseded by 2.0.1) | errata | 6 |

## Schema coverage

- 126 of 126 shipped schema files are reachable from `Actions`.
- **2 actions missing a schema:**
  - get15118EVCertificate request: expected `ocpp-2-0-json/src/main/resources/Get15118EVCertificateRequest.json`, not found
  - get15118EVCertificate response: expected `ocpp-2-0-json/src/main/resources/Get15118EVCertificateResponse.json`, not found

## Declared JSON Schema draft

`ocpp-2-0-json`'s parser validates every payload with `SpecVersion.VersionFlag.V6`. What the files themselves declare:

- `http://json-schema.org/draft-06/schema#` — 126 file(s)

---

[cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
