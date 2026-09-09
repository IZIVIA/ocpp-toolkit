# ocpp-2-0-core

## Purpose
Kotlin domain model and operation interfaces for OCPP 2.0.1: request/response types for every OCPP 2.0.1 operation, the `CSMSOperations` / `ChargePointOperations` contracts, and the default `RealCSMSOperations` / `RealChargePointOperations` dispatchers.

## Layer Guidelines
This is one of three `core` layer instances (alongside `ocpp-1-5-core` and `ocpp-1-6-core`). Shared conventions for package layout, `impl/` dispatchers, `DeserializeOptions`, and "how to add a new operation" live in [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md). This file only covers what is specific to OCPP 2.0.1.

## What Is Distinctive About 2.0.1 Here

- **Largest core by far** (~277 Kotlin files across ~70 operation packages) because OCPP 2.0.1 introduces many new operations (device model, security, display messages, monitoring, ISO 15118) absent from the 1.x line.
- **Device model / variables instead of a flat config API**: `model/common/ComponentType`, `VariableType`, `ComponentVariableType`, `StatusInfoType` back the `GetVariables`/`SetVariables`/`GetReport`/`GetBaseReport`/`GetMonitoringReport` family — replacing OCPP 1.x's single `GetConfiguration`/`ChangeConfiguration` pair. A `ComponentType` optionally carries an `EVSEType` to scope a variable to a specific EVSE/connector.
- **TransactionEvent replaces StartTransaction/StopTransaction**: charging session lifecycle is reported via a single `model/transactionevent/TransactionEventReq`/`Resp` (with `eventType` Started/Updated/Ended) instead of separate start/stop operations used in 1.x cores.
- **`IdTokenType` replaces the 1.x `idTag` string**: identifiers now carry a `type` (e.g. ISO14443, Central, KeyCode) alongside the value; `IdTokenInfoType` carries authorization result and status details. `AuthorizeReq` also supports ISO 15118 fields (`certificate`, `iso15118CertificateHashData: List<OCSPRequestDataType>`) for Plug & Charge, which have no 1.x equivalent.
- **Certificate management operations** (`certificateSigned`, `installCertificate`, `deleteCertificate`, `getInstalledCertificateIds`, `get15118evcertificate`, `getCertificateStatus`) exist only in this core — no equivalent security/PKI surface in 1.x cores.
- **Notify* family**: many CSMS-bound operations are named `notify*` (`NotifyReport`, `NotifyEvent`, `NotifyMonitoringReport`, `NotifyCustomerInformation`, `NotifyDisplayMessages`, `NotifyChargingLimit`, `NotifyEVChargingNeeds`, `NotifyEVChargingSchedule`) — a 2.0.1-specific naming pattern for asynchronous push reports, distinct from the 1.x `*StatusNotification` style (which still exists here too, e.g. `StatusNotification`, `FirmwareStatusNotification`, `LogStatusNotification`, `SecurityEventNotification`).
- **Display messages and variable monitoring** (`setDisplayMessage`, `getDisplayMessages`, `clearDisplayMessage`, `setVariableMonitoring`, `clearVariableMonitoring`, `setMonitoringBase`, `setMonitoringLevel`) are 2.0.1-only CSMS operations with no 1.x counterpart.
- One package name (`certificateSigned`) breaks the otherwise strict lowercase-package convention used by every other operation package in this core (and across the layer) — be aware of this when adding files there or referencing it.
- Some `model/common/*Type` data classes (e.g. `ComponentType`, `EVSEType`, `IdTokenType`, `ChargingProfileType`, `MeterValueType`) are shared building blocks reused across many operation Req/Resp pairs, more heavily than in the smaller 1.x cores — check `model/common/` before adding a new type that might already exist there.

## Submodules
No subdirectories are documented separately; `model/<operation>/` packages follow the shared core layout described in the layer guidelines, and `model/common/` holds the cross-operation building blocks described above.

## See Also
- [../docs/CORE.guidelines.md](../docs/CORE.guidelines.md) — shared conventions across the core modules
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [../docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
