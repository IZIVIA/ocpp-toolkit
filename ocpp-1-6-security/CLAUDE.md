# ocpp-1-6-security

## Purpose
Typed operation interfaces for the **OCPP 1.6 Security Whitepaper** extension. The wire models live in `ocpp-1-6-core` (`model/getlog`, `model/certificatesigned`, `model/signcertificate`, …) and are registered in that module's `Actions` enum; this module supplies the send/receive operation layer for them, which `ocpp-1-6-core`'s own `CSMSOperations`/`ChargePointOperations` deliberately do **not** cover.

## Key Patterns

- **Same mirrored shape as `ocpp-1-6-core`, split by initiator.** Each interface is the *outbound* API of one role, and its `Real*` implementation takes the *other* role's interface in its constructor to serve inbound traffic:
  - `SecurityChargePointOperations : CSMSCallbacks` — what a **charge point sends**: `logStatusNotification`, `securityEventNotification`, `signCertificate`, `signedFirmwareStatusNotification`. `RealSecurityChargePointOperations(chargingStationId, client, csmsOperations)` registers `client.receiveMessage(...)` handlers for the seven CSMS-initiated actions in its `init` block and delegates them to `csmsOperations`.
  - `SecurityCSMSOperations : CSCallbacks` — what a **CSMS sends**: `certificateSigned`, `deleteCertificate`, `extendedTriggerMessage`, `getInstalledCertificateIds`, `getLog`, `installCertificate`, `signedUpdateFirmware`. `RealSecurityCSMSOperations(servers, acceptConnection, chargePointOperations)` registers `server.receiveMessage(action, OcppVersion.OCPP_1_6, handler, acceptConnection)` for the four charge-point-initiated actions.
  - The direction split matches each action's `OcppInitiator` in `core16`'s `Actions` enum — check there, not here, when adding an operation.
- **Construct through the companion factories**, never the `Real*` classes directly: `SecurityChargePointOperations.newSecurityChargePointOperations(...)` / `SecurityCSMSOperations.newSecurityCSMSOperations(...)`. This matches `ocpp-1-6-core`'s `newChargePointOperations` convention.
- **Handlers register in `init`, so constructing the object *is* the subscription.** Nothing subscribes lazily on first call — building a `Real*` instance against a transport immediately attaches every inbound handler for that role.
- **Action names come from `operation-information`'s `ActionOcpp`**, imported as individual constants (`ActionOcpp.GET_LOG`, …) and used via `.value`. Never hard-code the wire string.
- **`OperationExecution` is assembled by a local private `sendMessage` helper** in each `Real*` class: it stamps `Clock.System.now()` before and after the transport call and always reports `RequestStatus.SUCCESS`. A transport exception therefore propagates rather than producing a failed `OperationExecution` — callers must handle it.

## Conventions

- Timestamps use `kotlin.time.Clock` / `kotlin.time.Instant` (not `java.time`, and no longer `kotlinx.datetime`).
- The module depends on `ocpp-1-6-core` and `utils` as `api(...)`, `ocpp-transport` and `operation-information` as `implementation(...)`. It has no JSON or SOAP dependency — serialisation stays in `ocpp-1-6-json` / `ocpp-1-6-soap`, driven by `core16`'s `Actions`.
- Published as its own Maven artifact (`ocpp-1-6-security`), so it is an opt-in dependency for consumers that do not speak the whitepaper.

## Wiring

`toolkit` treats security as an **independent facet**: `CSMS` matches `SecurityChargePointOperations16` in a separate `if`, not as another `when (csmsApi)` branch, because one callback object may implement both a core `ChargePointOperations` and this interface — a `when` branch would shadow and silently drop the security facet. On the client side, `Settings.ocpp16SecurityExtensions` is the opt-in that decides whether `Ocpp16Adapter` registers these handlers at all. See [../toolkit/CLAUDE.md](../toolkit/CLAUDE.md).

## Tests

`SecurityOperationsTest.kt` covers all four axes with `mockk` transports and `strikt` assertions: that the charge point registers the CSMS-initiated handlers, that the CSMS registers the charge-point-initiated ones, and that each side sends under the correct whitepaper action name. Adding an operation means adding it to the matching registration test *and* the matching send test.

## See Also

- [../ocpp-1-6-core/CLAUDE.md](../ocpp-1-6-core/CLAUDE.md) — the models and the `Actions` registry this module operates on.
- [../docs/API.guidelines.md](../docs/API.guidelines.md) — the typed-operations layer conventions this module follows.
- [../operation-information/CLAUDE.md](../operation-information/CLAUDE.md) — `ActionOcpp`, `RequestMetadata`, `OperationExecution`, `CSCallbacks`/`CSMSCallbacks`.
