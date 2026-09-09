# OCPP 1.5 — protocol reference

Derived from the official OCPP JSON schemas in `ocpp-1-5-json/src/main/resources/`, the action registry in
`ocpp-1-5-core/src/main/kotlin/com/izivia/ocpp/core15/model/common/enumeration/Actions.kt`, and the OCA specification documents. **Maintained by hand:** an action
added to `Actions`, or a schema change, belongs here in the same commit.

For the normative prose, see [SPECS.md](../SPECS.md) and the
[document tables of contents](../spec/1.5.md).

24 actions registered.

One file per action. Each field is one line keyed by its dotted JSON path:

```bash
grep -rn 'idTag' docs/protocol/1.5/
cat docs/protocol/1.5/authorize.md
```

## Actions at a glance

| action | direction | request schema | Kotlin |
|---|---|---|---|
| [`authorize`](authorize.md) | Charging Station -> CSMS | `Authorize.json` | `AuthorizeReq` / `AuthorizeResp` |
| [`bootNotification`](bootNotification.md) | Charging Station -> CSMS | `BootNotification.json` | `BootNotificationReq` / `BootNotificationResp` |
| [`cancelReservation`](cancelReservation.md) | CSMS -> Charging Station | `CancelReservation.json` | `CancelReservationReq` / `CancelReservationResp` |
| [`changeAvailability`](changeAvailability.md) | CSMS -> Charging Station | `ChangeAvailability.json` | `ChangeAvailabilityReq` / `ChangeAvailabilityResp` |
| [`changeConfiguration`](changeConfiguration.md) | CSMS -> Charging Station | `ChangeConfiguration.json` | `ChangeConfigurationReq` / `ChangeConfigurationResp` |
| [`clearCache`](clearCache.md) | CSMS -> Charging Station | `ClearCache.json` | `ClearCacheReq` / `ClearCacheResp` |
| [`dataTransfer`](dataTransfer.md) | either side | `DataTransfer.json` | `DataTransferReq` / `DataTransferResp` |
| [`diagnosticsStatusNotification`](diagnosticsStatusNotification.md) | Charging Station -> CSMS | `DiagnosticsStatusNotification.json` | `DiagnosticsStatusNotificationReq` / `DiagnosticsStatusNotificationResp` |
| [`firmwareStatusNotification`](firmwareStatusNotification.md) | Charging Station -> CSMS | `FirmwareStatusNotification.json` | `FirmwareStatusNotificationReq` / `FirmwareStatusNotificationResp` |
| [`getConfiguration`](getConfiguration.md) | CSMS -> Charging Station | `GetConfiguration.json` | `GetConfigurationReq` / `GetConfigurationResp` |
| [`getDiagnostics`](getDiagnostics.md) | CSMS -> Charging Station | `GetDiagnostics.json` | `GetDiagnosticsReq` / `GetDiagnosticsResp` |
| [`getLocalListVersion`](getLocalListVersion.md) | CSMS -> Charging Station | `GetLocalListVersion.json` | `GetLocalListVersionReq` / `GetLocalListVersionResp` |
| [`heartbeat`](heartbeat.md) | Charging Station -> CSMS | `Heartbeat.json` | `HeartbeatReq` / `HeartbeatResp` |
| [`meterValues`](meterValues.md) | Charging Station -> CSMS | `MeterValues.json` | `MeterValuesReq` / `MeterValuesResp` |
| [`remoteStartTransaction`](remoteStartTransaction.md) | CSMS -> Charging Station | `RemoteStartTransaction.json` | `RemoteStartTransactionReq` / `RemoteStartTransactionResp` |
| [`remoteStopTransaction`](remoteStopTransaction.md) | CSMS -> Charging Station | `RemoteStopTransaction.json` | `RemoteStopTransactionReq` / `RemoteStopTransactionResp` |
| [`reserveNow`](reserveNow.md) | CSMS -> Charging Station | `ReserveNow.json` | `ReserveNowReq` / `ReserveNowResp` |
| [`reset`](reset.md) | CSMS -> Charging Station | `Reset.json` | `ResetReq` / `ResetResp` |
| [`sendLocalList`](sendLocalList.md) | CSMS -> Charging Station | `SendLocalList.json` | `SendLocalListReq` / `SendLocalListResp` |
| [`startTransaction`](startTransaction.md) | Charging Station -> CSMS | `StartTransaction.json` | `StartTransactionReq` / `StartTransactionResp` |
| [`statusNotification`](statusNotification.md) | Charging Station -> CSMS | `StatusNotification.json` | `StatusNotificationReq` / `StatusNotificationResp` |
| [`stopTransaction`](stopTransaction.md) | Charging Station -> CSMS | `StopTransaction.json` | `StopTransactionReq` / `StopTransactionResp` |
| [`unlockConnector`](unlockConnector.md) | CSMS -> Charging Station | `UnlockConnector.json` | `UnlockConnectorReq` / `UnlockConnectorResp` |
| [`updateFirmware`](updateFirmware.md) | CSMS -> Charging Station | `UpdateFirmware.json` | `UpdateFirmwareReq` / `UpdateFirmwareResp` |

## Specification documents

Each action below cites the section and PDF page of the normative document. Page numbers are
PDF page positions in these documents, which OCA distributes at
<https://www.openchargealliance.org/downloads/>:

| document | role | pages |
|---|---|--:|
| OCPP 1.5 Specification | spec | 81 |
| OCPP 1.5 — a functional description | spec | 7 |

## Schema coverage

- 48 of 48 shipped schema files are reachable from `Actions`.
- Actions and schema files correspond exactly.

## Declared JSON Schema draft

`ocpp-1-5-json`'s parser validates every payload with `SpecVersion.VersionFlag.V4`. What the files themselves declare:

- `http://json-schema.org/draft-04/schema#` — 48 file(s)

---

[cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
