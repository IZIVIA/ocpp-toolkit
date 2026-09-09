# ocpp-1-6-api-adapter

## Purpose

Bridges the version-agnostic `generic-api` model (`CSApi`/`CSMSApi`, "gen" types) to the OCPP 1.6 `ocpp-1-6-core` model (`ChargePointOperations`/`CSMSOperations`, "core" types), so a `ClientTransport` speaking OCPP-J 1.6 can be driven through the generic API.

## Layer Guidelines

This directory is an instance of the `api-adapter` scattered layer. Common conventions shared with the other adapter (`ocpp-2-0-api-adapter`) — MapStruct wiring, `Mappers.getMapper` usage, `OperationExecution`/`ExecutionMetadata` conventions, general "add a new operation" checklist — belong in [../docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md). This file only documents what is specific to OCPP 1.6.

## OCPP-1.6-Specific Structure

- `Ocpp16Adapter` implements `CSMSApi` (generic-API-facing, CSMS -> Charging Station calls originated from generic model): converts each generic request to a 1.6 core request via a per-operation `*Mapper`, invokes `ChargePointOperations`, converts the response back.
- `Ocpp16CSApiAdapter` implements `CSMSOperations` (core-facing, incoming OCPP 1.6 charge-point-initiated calls dispatched by `ChargePointOperations`): converts 1.6 core requests to generic requests, calls the injected `CSApi`, converts the generic response back to core.
- `mapper/CommonMapper.kt` centralizes shared conversions reused across operation mappers (id token/idTag handling, charging profile/schedule mapping, meter-value filtering, enum coercions) and is pulled into other mappers via `@Mapper(uses = [CommonMapper::class])`.
- One `<Operation>Mapper.kt` per OCPP 1.6 operation in `mapper/`, each an abstract MapStruct `@Mapper` class with `genToCoreReq`/`coreToGenResp` (or `coreToGenReq`/`genToCoreResp` for CS-API-adapter direction) methods.
- Several `CSMSApi`/`CSApi` operations that exist in the generic model but have no OCPP 1.6 equivalent (`notifyReport`, `clearedChargingLimit`, `getCertificateStatus`, `notifyEvent`, `securityEventNotification`, `signCertificate`, `reportChargingProfiles`, etc.) simply `throw IllegalStateException("<Operation> can't be call in OCPP 1.6")` in `Ocpp16Adapter` — this is expected and intentional, not a TODO.

## Transaction ID State Machine (OCPP-1.6-specific)

OCPP 1.6 identifies transactions with a CSMS-assigned `Int` (`transactionId`, only known after `StartTransaction.conf`), while the generic API (aligned with 2.0.1) uses a charge-point-chosen `String` local id present from the very first `TransactionEventReq(eventType = Started)`. This adapter must correlate the two, so it is **stateful**, unlike the pure mappers:

- `Ocpp16TransactionIds(localId: String, csmsId: Int)` — the correlation record.
- `TransactionRepository` — the interface: `saveTransactionIds`, `getTransactionIdsByLocalId` (throws `IllegalStateException` if unknown), `getLocalIdByTransactionId` (nullable).
- `impl/RealTransactionRepository` — the only implementation, an in-memory `ConcurrentHashMap<String, Int>` keyed by local id (reverse lookup does a linear scan). It is injected into both `Ocpp16Adapter` and `Ocpp16CSApiAdapter` and must be the **same instance** shared by both so ids saved on transaction start are visible to CSMS-initiated operations (`RemoteStopTransaction`, `SetChargingProfile`).

Where this matters in the code:
- `Ocpp16Adapter.startTransactionEvent`: after a successful `StartTransaction`, saves `Ocpp16TransactionIds(request.transactionInfo.transactionId, response.response.transactionId)`.
- `Ocpp16Adapter.stopTransactionEvent` / `meterValues`: resolve the CSMS int id from the local string id via `getTransactionIdsByLocalId` before delegating to the core operation. `meterValues` treats a missing correlation as non-fatal (catches `IllegalStateException`, logs a warning, and returns `RequestStatus.NOT_SEND` instead of propagating) since meter values may legitimately arrive before/without a matching start.
- `Ocpp16CSApiAdapter.remoteStopTransaction` / `setChargingProfile`: go the other direction, resolving the local string id from the CSMS int id via `getLocalIdByTransactionId`, falling back to `transactionId.toString()` when no correlation is known (e.g., remote stop for a transaction this adapter never started).
- `transactionEvent` dispatches on `TransactionEventEnumType` (`Started` / `Updated` / `Ended`) to `startTransactionEvent` / `updateTransactionEvent` / `stopTransactionEvent`. `updateStatusEvent` additionally fires a synthetic `StatusNotification` (bumping the timestamp by 1ms) whenever `chargingState` changes, since OCPP 1.6 has no direct equivalent of a combined transaction+status event.

When adding new stateful behavior here, extend `TransactionRepository` rather than reaching into `RealTransactionRepository`'s map directly, and be mindful that lookups can throw or return null — callers must decide the right fallback (fail closed for `meterValues`, fall back to the raw int-as-string for CSMS-initiated calls).

## OCPP-1.6-Specific Mapping Notes

- `CommonMapper.convertMeasurand` explicitly rejects `EnergyActiveNet`, `EnergyReactiveNet`, `EnergyApparentExport`, `EnergyApparentImport`, `EnergyApparentNet` with `IllegalStateException` — these measurands don't exist in OCPP 1.6's `Measurand` enum; everything else maps by `.name`.
- `CommonMapper.filterMeterValues` implements the 1.6-specific rule that `meterStart`/`meterStop` (plain `Int`) must be derived from exactly one `EnergyActiveImportRegister` sampled value with the matching `ReadingContext` (`TransactionBegin`/`TransactionEnd`) — it throws `IllegalArgumentException` on 0 or >1 matches, reflecting a lossy Int-vs-list-of-samples mismatch between the two models.
- `StopTransactionMapper.convertStopReason` maps several generic 1.6-inapplicable `ReasonEnumType` values (`EnergyLimitReached`, `GroundFault`, `LocalOutOfCredit`, `MasterPass`, `OvercurrentFault`, `PowerQuality`, `SOCLimitReached`, `StoppedByEV`, `TimeLimitReached`, `Timeout`) down to `Reason.Other`, and treats `TriggerReasonEnumType.UnlockCommand` as an override producing `Reason.UnlockCommand` regardless of `stoppedReason`.
- `Ocpp16CSApiAdapter.remoteStartTransaction` generates a random `Int` remote-start id (`Random.nextInt()`) since OCPP 1.6's `RemoteStartTransaction` has no such field in the generic request; it's synthesized purely to satisfy the generic `requestStartTransaction` signature.
- `Ocpp16CSApiAdapter.getConfiguration` branches on whether `req.key` is empty to call either `csApi.getVariables` or `csApi.getAllVariables`, since 1.6's single `GetConfiguration` covers both generic operations.
- All `Ocpp16CSApiAdapter` responses are wrapped with `RequestStatus.SUCCESS` unconditionally (no round-trip execution metadata from `csApi` calls is propagated for that field), unlike `Ocpp16Adapter` which forwards `response.executionMeta` from `ChargePointOperations`.

## Tests

- `AdapterTest.kt` / `CSApiAdapterTest.kt`: exercise `Ocpp16Adapter` / `Ocpp16CSApiAdapter` end-to-end against mocked `ChargePointOperations`/`CSApi` (MockK) plus a real or mocked `TransactionRepository`, asserting the full request/response translation and transaction-id correlation.
- `MapperTest.kt`: exercises individual MapStruct mappers directly (`Mappers.getMapper(XMapper::class.java)`), using `testFixtures` builders from `generic-api` (e.g., `transactionEventReq`) to construct generic requests and asserting the resulting core model (and vice versa) with Strikt.

## Adding a New OCPP 1.6 Mapper

1. Create `mapper/<Operation>Mapper.kt` following the existing MapStruct abstract-class pattern (`@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])` when common conversions are needed).
2. Wire it into `Ocpp16Adapter` (generic -> core, CSMS-facing) or `Ocpp16CSApiAdapter` (core -> generic, CS-facing) via `Mappers.getMapper(...)`, following the general adapter conventions in [../docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md).
3. If the operation involves a transaction id, go through `TransactionRepository` for the local-id/CSMS-id correlation rather than passing the raw id through — see the state machine section above.
4. Add coverage in `MapperTest.kt` for the mapper and in `AdapterTest.kt`/`CSApiAdapterTest.kt` for the adapter wiring.

## See Also

- [../docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md) — shared api-adapter layer conventions
- [../generic-api/CLAUDE.md](../generic-api/CLAUDE.md) — version-agnostic model this adapter targets
- [../ocpp-1-6-core/CLAUDE.md](../ocpp-1-6-core/CLAUDE.md) — OCPP 1.6 core model/operations this adapter wraps
