# ocpp-1-5-core

## Purpose
Transport-agnostic Kotlin model and operation contracts for OCPP 1.5: the Req/Resp data classes, enums, and the two operation interfaces (`CSMSOperations`, `ChargePointOperations`) that `ocpp-1-5-json`, `ocpp-1-5-soap`, `ocpp-1-5-api`, and `ocpp-1-5-api-adapter` build on.

## Layer Guidelines

This is one of three `core` instances (`ocpp-1-5-core`, `ocpp-1-6-core`, `ocpp-2-0-core`) sharing an identical shape. Common conventions (package layout, `model/<operation>/enumeration` structure, `Request`/`Response` markers, `Actions` enum pattern, `impl/` split, how to add a new operation) are centralized in **[../docs/CORE.guidelines.md](../docs/CORE.guidelines.md)** — read that first. This file only covers what is specific to OCPP 1.5.

## OCPP 1.5-Specific Notes

- Root package is `com.izivia.ocpp.core15`.
- The operation set matches the OCPP 1.5 Core Profile: 24 actions total, enumerated exhaustively in `model/common/enumeration/Actions.kt`. `CSMSOperations` declares the 15 CSMS-initiated actions (`reset`, `changeAvailability`, `changeConfiguration`, `clearCache`, `remoteStartTransaction`, `remoteStopTransaction`, `unlockConnector`, `getConfiguration`, `cancelReservation`, `getLocalListVersion`, `updateFirmware`, `sendLocalList`, `reserveNow`, `dataTransfer`, `getDiagnostics`); `ChargePointOperations` declares the 10 Charge Point-initiated actions (`heartbeat`, `authorize`, `meterValues`, `startTransaction`, `stopTransaction`, `statusNotification`, `dataTransfer`, `bootNotification`, `firmwareStatusNotification`, `diagnosticsStatusNotification`). `dataTransfer` is bidirectional and appears in both interfaces, tagged `OcppInitiator.ALL` in `Actions`.
- Compared to the OCPP 1.6 core operation set (see `ocpp-1-6-core/src/.../model/`), this module has **no** smart-charging operations (`SetChargingProfile`, `ClearChargingProfile`, `GetCompositeSchedule`), **no** security/certificate operations (`CertificateSigned`, `DeleteCertificate`, `GetInstalledCertificateIds`, `InstallCertificate`, `SignCertificate`, `SecurityEventNotification`), **no** signed-firmware variants (`SignedUpdateFirmware`, `SignedFirmwareStatusNotification`), **no** `TriggerMessage`/`ExtendedTriggerMessage`, and **no** `GetLog`/`LogStatusNotification`. Diagnostics and firmware update are handled only via the plain `GetDiagnostics`/`UpdateFirmware`/`DiagnosticsStatusNotification`/`FirmwareStatusNotification` actions present here.
- This is the last core version with a genuine dual-transport story in this repo: OCPP 1.5 is consumed by both `ocpp-1-5-json` (OCPP-J/WebSocket) and `ocpp-1-5-soap` (OCPP-S), reflecting that 1.5 was historically SOAP-first before the OCPP-J transport was standardized. Model classes here stay transport-neutral so both bindings can (de)serialize them; do not add JSON- or XML-specific annotations in this module.
- `ChargePointOperations` carries transport lifecycle methods (`connect()`, `close()`) and a companion factory `newChargePointOperations(chargingStationId, transport: ClientTransport, csmsOperations)` returning `RealChargePointOperations` — this factory is the entry point consumers (e.g. `ocpp-transport-websocket`, `ocpp-1-5-soap`) use to obtain a Charge Point-side client, so any new Charge Point-initiated action added here must also be wired into `impl/RealChargePointOperations.kt`.
- `model/common/` holds the OCPP 1.5 shared payload types (`IdTagInfo`, `MeterValue`, `SampledValue`) plus their enumerations (e.g. `AuthorizationStatus`, `Measurand`, `UnitOfMeasure`, `ChargingProfilePurposeType`, `ChargingRateUnitType`) — these are reused across multiple operations (e.g. `IdTagInfo` in `authorize`, `startTransaction`, `stopTransaction`).
- `getconfiguration/KeyValue.kt` and `sendlocallist/AuthorisationData.kt` are operation-scoped payload types (not in `common`) because they are only referenced by their own Req/Resp pair.

## See Also
- [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md) — shared conventions for all core modules
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [../docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
