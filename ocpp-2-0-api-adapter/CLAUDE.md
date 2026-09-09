# ocpp-2-0-api-adapter

## Purpose
Bridges the version-agnostic `generic-api` model to the `ocpp-2-0-core` (OCPP 2.0.1) model via MapStruct, for both CS-initiated and CSMS-initiated operations.

## Layer Guidelines
This is one of two implemented `api-adapter` instances (`ocpp-1-6-api-adapter`, `ocpp-2-0-api-adapter`; `ocpp-1-5-api-adapter` is declared in `settings.gradle.kts` but has no sources yet). Shared conventions (mapper naming, MapStruct wiring, adding-a-new-operation checklist, test structure) are centralized in `../docs/API-ADAPTER.guidelines.md`. This file only covers what is specific to OCPP 2.0.1.

## What's Distinctive About This Adapter

- **`generic-api` already models 2.0.1-shaped concepts directly.** Unlike the 1.x adapters, `generic-api` carries a first-class `TransactionEventReq/Resp` (matching 2.0.1's unified transaction-event model, not the older split StartTransaction/StopTransaction) and a first-class device-model report shape (`ReportDataType`, `VariableAttributeType` under `notifyreport`). As a result, mapping here is largely a faithful 1:1 field mapping rather than a lossy reconciliation between two very different shapes — the complexity is nested-object composition (see `NotifyReportMapper`), not semantic gaps.
- **Remote start/stop stay separate from transaction events.** `RequestStartTransactionMapper` / `RequestStopTransactionMapper` map the CSMS-initiated remote-control operations (`RequestStartTransactionReq/Resp`, `RequestStopTransactionReq/Resp`); `TransactionEventMapper` maps the CS-initiated `TransactionEvent` notification. Don't conflate the two when adding transaction-related fields.
- **Enum mapping is exhaustive-with-fail-fast, not silently lossy.** `CommonMapper.measurandEnum` explicitly enumerates `generic-api` enum values that don't exist in the OCPP 2.0.1 target enum (e.g. `Temperature`, `RPM`) and throws `IllegalStateException` for them instead of defaulting or dropping silently — consistent with the project's recent push toward exhaustive enum mapping. Follow this pattern (explicit `when` branch + `throw IllegalStateException`) rather than relying on MapStruct's default enum-name matching when a target enum is a strict subset of the generic one.
- **Cross-field business validation lives in the adapter class, not the mapper.** `Ocpp20Adapter` performs OCPP-2.0.1-specific business rules before delegating to the mapper/operations layer, e.g.:
  - `notifyEVChargingNeeds`: rejects if `stateOfCharge`/`fullSoC`/`bulkSoC` are outside `0..100`.
  - `notifyMonitoringReport`: rejects if any `variableMonitoring.severity` is outside `0..9`.
  - `publishFirmwareStatusNotification`: rejects if `status == Published` and `location` is empty (private extension function `checkBusinessPublishFirmwareStatusNotificationRequest`).
  - `meterValues`: catches `IllegalStateException` from the operation layer and degrades to `OperationExecution(..., RequestStatus.NOT_SEND, ..., MeterValuesResp())` instead of propagating.
  These checks throw before any mapper is invoked — keep new 2.0.1-specific business constraints here, not inside a `Mapper`.

## Two Entry Points, Opposite Mapper Directions

- **`Ocpp20Adapter`** (implements `CSMSApi`) handles CS → CSMS operations (heartbeat, authorize, transactionEvent, statusNotification, notifyReport, etc.). Its mappers are invoked as `mapper.genToCoreReq(request)` then `mapper.coreToGenResp(response)`.
- **`Ocpp20CSApiAdapter`** (wraps a `CSApi`, backs `CSMSOperations`) handles CSMS → CS operations (reset, getVariables, setVariables, requestStartTransaction, reserveNow, etc.). Its mappers are invoked with the roles reversed: `coreToGenReq` / `genToCoreResp`.
When adding a mapper, keep both method names on the interface even if only one direction is currently exercised by its adapter — this matches the existing 2.0.1 mapper set (e.g. `RequestStartTransactionMapper` only exposes `coreToGenReq` + `genToCoreResp`, `TransactionEventMapper` only exposes `genToCoreReq` + `coreToGenResp`).

## MapStruct Conventions Observed Here

- Every mapper: `@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)`.
- Plain field-for-field mappers are Kotlin `interface`s (e.g. `BootNotificationMapper`, `RequestStartTransactionMapper`).
- Mappers needing custom composition (manual response assembly, `@Named` qualifiers for nested lists/enums) are `abstract class`es (e.g. `TransactionEventMapper`, `NotifyReportMapper`, `CommonMapper`).
- Nested/renamed fields use `@Mapping(target = "...", source = "...", qualifiedByName = [...])` paired with a `@Named("...")` helper method on the same or a shared mapper.
- `CommonMapper` centralizes cross-operation nested types (`MeterValueType`, `SampledValueType`, `UnitOfMeasure`, `MeasurandEnumType`) and is pulled into other mappers via `@Mapper(..., uses = [CommonMapper::class])` (see `TransactionEventMapper`). Reuse it instead of re-declaring meter-value mapping logic in a new mapper.
- List-of-nested-object mapping is done via a paired `@Named` singular converter + a `@Named` list converter that `.map { }`s the singular one (see `NotifyReportMapper.convertVariableAttributeType` / `convertVariableAttributeTypeList`).

## Adding a New Mapper

Follow the generic checklist in `../docs/API-ADAPTER.guidelines.md`, plus for this module:
1. Decide direction: is it a CS-initiated op (add to `Ocpp20Adapter`, mapper exposes `genToCoreReq`/`coreToGenResp`) or CSMS-initiated (add to `Ocpp20CSApiAdapter`, mapper exposes `coreToGenReq`/`genToCoreResp`)?
2. If the operation carries a device-model report or a meter-value/measurand payload, reuse `CommonMapper` / follow the `NotifyReportMapper` nested-list pattern rather than hand-rolling conversion.
3. If a generic-api enum has values with no OCPP 2.0.1 counterpart, add an explicit `when` branch throwing `IllegalStateException` for those values (mirror `CommonMapper.measurandEnum`) instead of leaving MapStruct's implicit enum mapping to fail at runtime.
4. If the new operation needs cross-field validation beyond what MapStruct/nullability can express, add it as a guard in the adapter method (see the `Ocpp20Adapter` examples above), not inside the mapper.

## Tests
`src/test/kotlin/.../test/` contains `AdapterTest.kt` (`Ocpp20Adapter`, CS→CSMS direction), `CSApiAdapterTest.kt` (`Ocpp20CSApiAdapter`, CSMS→CS direction), and `MapperTest.kt` (direct per-mapper unit tests instantiating `core20` and `generic-api` model objects and asserting round-trip mapping). See `../docs/TESTING.md` for project-wide testing conventions.

## See Also
- [../docs/API-ADAPTER.guidelines.md](../docs/API-ADAPTER.guidelines.md) — shared api-adapter layer conventions
- [../generic-api/CLAUDE.md](../generic-api/CLAUDE.md) — version-agnostic source model
- [../ocpp-2-0-core/CLAUDE.md](../ocpp-2-0-core/CLAUDE.md) — OCPP 2.0.1 target model
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
