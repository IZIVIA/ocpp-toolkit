# ocpp-1-6-core

## Purpose
Version-specific core for OCPP 1.6 (`com.izivia.ocpp.core16`): request/response models, action registry, and CSMS/ChargePoint operation contracts for every OCPP 1.6 message, including the Security Whitepaper extensions.

## Layer Guidelines
This is a `core` layer instance. Conventions shared with `ocpp-1-5-core` and `ocpp-2-0-core` (root package layout, `Req`/`Resp` naming, `model/<operation>/enumeration/` structure, `Request`/`Response` marker interfaces, the `impl/` package, `DeserializeOptions.kt` shape) are documented in [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md). This file covers only what is specific to OCPP 1.6.

## What Is Distinctive About 1.6

- **Largest action set of all cores.** In addition to the OCPP 1.6 "core profile" (Authorize, BootNotification, StatusNotification, Reset, ChangeAvailability, RemoteStart/StopTransaction, ReserveNow, SendLocalList, TriggerMessage, SetChargingProfile, GetCompositeSchedule, etc.), this module models the full **OCPP 1.6 Security Whitepaper** extension: `certificatesigned`, `deletecertificate`, `getinstalledcertificateids`, `installcertificate`, `getlog`, `logstatusnotification`, `signcertificate`, `securityeventnotification`, `signedfirmwarestatusnotification`, `signedupdatefirmware`, `extendedtriggermessage`. These packages do not exist in `ocpp-1-5-core`.
- **`model/common/enumeration/Actions.kt`** is the canonical registry for this version: an `IActions` enum where every entry maps the OCPP wire action name (e.g. `"getLog"`) to its `Req`/`Resp` classes and its `OcppInitiator` (`CENTRAL_SYSTEM` vs `CHARGING_STATION`). Every 1.6 action — core and security extension alike — is registered here, and this registry (not the typed operation interfaces) is the single source of truth for serialization/dispatch used by `ocpp-1-6-json` and `ocpp-1-6-soap`.
- **The typed operation interfaces here cover the core profile only — security lives in another module.** `CSMSOperations.kt` and `ChargePointOperations.kt` expose one method per operation for the historical OCPP 1.6 core profile. The security-extension operations (`getLog`, `deleteCertificate`, `installCertificate`, `certificateSigned`, `signCertificate`, `extendedTriggerMessage`, `signedUpdateFirmware`, `logStatusNotification`, `securityEventNotification`, `signedFirmwareStatusNotification`) have their models and `Actions` entries **here**, but their typed operations are in [`../ocpp-1-6-security`](../ocpp-1-6-security/CLAUDE.md) as `SecurityCSMSOperations`/`SecurityChargePointOperations`. Finding no `getLog` on `CSMSOperations` does not mean the operation is unimplemented — look in that module before adding anything.
- **`CSMSCallbacks`/`CSCallbacks`** (from `operation-information`) that `ChargePointOperations`/`CSMSOperations` extend are empty marker interfaces at this layer — the actual incoming-message callback signatures for 1.6 are added by `ocpp-1-6-api`, not here.
- Both transports consume this module unmodified: `ocpp-1-6-json` (OCPP-J/WebSocket) and `ocpp-1-6-soap` (OCPP-S) both serialize/deserialize using the same `model/` DTOs and the same `Actions` registry — there is no per-transport variant of the 1.6 model.

## Operation Coverage Snapshot

- `CSMSOperations` (CSMS-initiated, i.e. calls a Charging Station can execute): Reset, ChangeAvailability, ChangeConfiguration, ClearCache, RemoteStart/StopTransaction, UnlockConnector, GetConfiguration, CancelReservation, ClearChargingProfile, GetCompositeSchedule, GetLocalListVersion, UpdateFirmware, SendLocalList, TriggerMessage, SetChargingProfile, ReserveNow, DataTransfer, GetDiagnostics.
- `ChargePointOperations` (Charging-Station-initiated): `connect()`/`close()` plus Heartbeat, Authorize, MeterValues, StartTransaction, StopTransaction, StatusNotification, DataTransfer, BootNotification, FirmwareStatusNotification, DiagnosticsStatusNotification. Constructed via `ChargePointOperations.newChargePointOperations(chargingStationId, transport, csmsOperations)`, backed by `impl/RealChargePointOperations.kt`.
- `impl/RealCSMSOperations.kt` / `impl/RealChargePointOperations.kt` are the concrete implementations of the two interfaces above; they import the same `model/` req/resp types 1:1 with the interface method signatures.

## Adding a New OCPP 1.6 Operation

1. Create `model/<operationname>/<Operation>Req.kt` and `<Operation>Resp.kt` implementing `Request`/`Response`; add an `enumeration/` sub-package if the operation introduces new enum types (see [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md) for shape/naming details).
2. Register the operation in `model/common/enumeration/Actions.kt` with its wire action string, `Req`/`Resp` classes, and `OcppInitiator` — this alone makes the operation transport-serializable via `ocpp-1-6-json`/`ocpp-1-6-soap`.
3. If callers need a typed, convenience method (as opposed to only generic dispatch), add it to `CSMSOperations.kt` or `ChargePointOperations.kt` (matching the `OcppInitiator` direction) and implement it in the corresponding `impl/Real*Operations.kt`. **If the operation belongs to the Security Whitepaper, it goes in `ocpp-1-6-security` instead**, on `SecurityCSMSOperations`/`SecurityChargePointOperations`.
4. If the operation is CSMS-initiated and needs to be callable/handled from `ocpp-1-6-api`, check whether that module's callback interfaces also need updating (out of scope for this module).

## See Also
- [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md) — shared conventions across all `ocpp-X-core` modules
- [../operation-information/CLAUDE.md](../operation-information/CLAUDE.md) — `RequestMetadata`, `OperationExecution`, `CSMSCallbacks`/`CSCallbacks` marker interfaces this module builds on
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [../docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
