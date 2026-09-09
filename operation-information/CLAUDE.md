# operation-information

## Purpose
Defines the version-agnostic, dependency-free vocabulary types used to describe an OCPP operation's identity, execution status, and timing — the shared contract that every other module in the toolkit (core, api, api-adapter, json, soap, transport) builds on.

## Key Types

- **`ActionOcpp`** — a single flat enum listing every OCPP action name across all protocol versions (1.5 through 2.0.1), from `START_TRANSACTION` / `STOP_TRANSACTION` (OCPP-S era) to `TRANSACTION_EVENT` / `NOTIFY_EV_CHARGING_NEEDS` (OCPP 2.0.1). Each constant carries the wire-format string via `value` (e.g. `RESET("Reset")`). This is the canonical action identifier used to route/dispatch messages regardless of which OCPP version is in play.
- **`RequestMetadata`** — identifies *what request this is*: `chargingStationId` (required) and an optional `messageId` (correlation id, e.g. the OCPP-J unique message id or OCPP-S message id header).
- **`RequestStatus`** — enum: `SUCCESS`, `NOT_SEND`, `FAILED`. Represents the outcome of sending/executing a request.
- **`ExecutionMetadata`** — wraps a `RequestMetadata` with its `status`, `requestTime`, and `responseTime` (both `kotlin.time.Instant`, defaulting to the epoch-like sentinel `0000-01-01T00:00:00.000Z` when not yet set).
- **`OperationExecution<T, R>`** — the top-level envelope for one executed operation: `executionMeta` (`ExecutionMetadata`) plus the typed `request: T` and `response: R` payloads. `T`/`R` are the version-specific request/response DTOs defined in each `ocpp-X-core` module — this module has no knowledge of their shape.
- **`ChargingStationConfig`** — minimal connection config for a charging station: `acceptConnection: Boolean` and optional `soapUrl: String?` (only relevant for OCPP-S transport).
- **`CSMSCallbacks`** / **`CSCallbacks`** — empty marker interfaces. Version-specific `ocpp-X-api` modules extend these with the actual CSMS-side and Charging-Station-side callback method signatures for that protocol version. This module only fixes the naming contract, not the behavior.

## How Types Flow Through an Operation

1. A caller builds a `RequestMetadata` (station id + optional message id) to identify the request.
2. As the request is sent/received, an `ExecutionMetadata` is produced, pairing that `RequestMetadata` with a `RequestStatus` and the request/response `Instant` timestamps.
3. The version-specific layer (`ocpp-X-core` request/response DTOs) is combined with the `ExecutionMetadata` into an `OperationExecution<T, R>`, the object handed back to consumers of the toolkit (e.g. `toolkit`, transport modules) as the complete record of "this operation happened, here's its status/timing, here's the payload."
4. `ActionOcpp` is used orthogonally, wherever code needs to identify *which* OCPP action a message represents (routing, logging, dispatch tables) — it is not embedded inside `OperationExecution` itself.

## Invariants

- **`RequestStatus.combine`** implements a "worst status wins" merge, used when aggregating the status of related/sub-requests into one:
  - `SUCCESS` is the neutral element — `SUCCESS.combine(x) == x`.
  - `FAILED` is absorbing/dominant — `FAILED.combine(x) == FAILED` for any `x`.
  - Otherwise (`NOT_SEND` combined with `SUCCESS` or `NOT_SEND`) the result is `NOT_SEND`.
- **`ExecutionMetadata.combine`** merges two executions that share the same `reqMeta`/`requestTime`: it keeps the original `reqMeta` and `requestTime`, combines `status` via `RequestStatus.combine`, and takes the `responseTime` of the *argument* (the later execution). Used for scenarios where a single logical request produces multiple execution attempts/responses that must collapse into one summary.
- This module has **zero project dependencies**, and no external date-time library either — `Instant` comes from the `kotlin.time` stdlib package. Do not add dependencies on other modules here — anything placed in `operation-information` becomes a transitive dependency of nearly the entire toolkit.

## Conventions

- All types are simple, immutable `data class`es or enums — no logic beyond the two `combine` functions above. Keep it that way: this module is meant to be a stable, low-churn vocabulary layer, not a place for business logic.
- `ActionOcpp` is a single unversioned enum shared by every OCPP version; when a new action is introduced by a newer OCPP spec, add it here rather than creating a version-specific duplicate.

## Adding a New Type or Action

1. For a new OCPP action name: add a constant to `ActionOcpp` with its wire-format string value (matches the exact OCPP spec action name).
2. For a new cross-cutting concept needed by multiple version-specific modules (e.g. a new metadata field): add it here only if it truly has no dependency on version-specific types; otherwise it belongs in the relevant `ocpp-X-core`/`ocpp-X-api` module instead.
3. Keep new types dependency-free (no imports from other project modules) to preserve this module's role as the dependency-free base layer.

## See Also
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [../docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
