# ocpp-2-0-api

## Purpose

Charging-station-side callback surface for OCPP 2.0.1. Turns a set of user-supplied
callbacks into a working `CSMSOperations` implementation, so a consumer that wants to
*simulate or implement a charging station* only writes the handlers it cares about.

This module is an instance of the `api` layer — see
[../docs/API.guidelines.md](../docs/API.guidelines.md) for the conventions shared by
`ocpp-1-5-api`, `ocpp-1-6-api` and `ocpp-2-0-api`.

Published as `ocpp-2-0-api`. Depends only on `ocpp-2-0-core` and `operation-information`.

## Contents

Package `com.izivia.ocpp.api20`, two files:

- `OcppCSCallbacks` — the interface a consumer implements. Every method has a default body
  of `throw NotImplementedError()`, so implementers override only the operations they support.
- `DefaultCSMSOperations` — implements `com.izivia.ocpp.core20.CSMSOperations` by delegating
  each operation to the matching callback.

## Key patterns

- **Opt-in callbacks.** `NotImplementedError` is the "not supported" signal. There is no
  registry or capability negotiation — an unimplemented operation throws at call time.
- **Uniform delegation body.** Every `DefaultCSMSOperations` override has the identical shape:

  ```kotlin
  OperationExecution(
      ExecutionMetadata(meta, RequestStatus.SUCCESS, now()),
      req,
      ocppCSCallbacks.<operation>(req)
  )
  ```

- **`RequestStatus.SUCCESS` is hardcoded.** The metadata reports success as soon as the
  callback returns; failures surface as thrown exceptions, not as a `FAILED` status. Anything
  that needs richer status handling must wrap the call itself.
- **Timestamp is the response time**, taken with `kotlin.time.Clock.System.now()` at
  delegation time — not the time the request was received.
- This module covers **CSMS→charging-station** operations only. Charging-station→CSMS calls go
  out through `ocpp-2-0-core`'s `ChargePointOperations`, wired up by `toolkit`'s `ApiFactory`.

## OCPP 2.0.1 specifics

`OcppCSCallbacks` declares **40 callbacks**, matching the CSMS-initiated operation set of
`ocpp-2-0-core`'s `CSMSOperations`. The two must stay in lockstep: adding an operation to the
core interface without adding the callback here breaks compilation of `DefaultCSMSOperations`.

## Adding an operation

1. Add the Req/Resp model and the `CSMSOperations` method in `ocpp-2-0-core`.
2. Add the callback to `OcppCSCallbacks` with a `throw NotImplementedError()` default.
3. Add the delegating override to `DefaultCSMSOperations` using the uniform body above.
4. Update the matching mapper in `ocpp-2-0-api-adapter` so the generic API can reach it.

## See also

- [../ocpp-2-0-core/CLAUDE.md](../ocpp-2-0-core/CLAUDE.md) — the models and operation interfaces
- [../ocpp-2-0-api-adapter/CLAUDE.md](../ocpp-2-0-api-adapter/CLAUDE.md) — bridge to `generic-api`
- [../operation-information/CLAUDE.md](../operation-information/CLAUDE.md) — `OperationExecution`, `RequestStatus`
