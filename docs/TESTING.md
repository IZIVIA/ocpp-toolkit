# Testing Strategy

What the tests here are actually for, and which failures mean what. For the commands to run them,
see [DEVELOPMENT.md](DEVELOPMENT.md).

## Stack

JUnit 5 (Jupiter) as the engine, **Strikt** for assertions, **MockK** for mocking — all supplied
to every module by `coreProject()` in [`buildSrc`](../buildSrc/CLAUDE.md). `toolkit` additionally
uses mockito-kotlin for historical reasons; prefer MockK in new tests.

`useJUnitPlatform()` is forced by the convention function, and `junit-platform-launcher` is
declared explicitly because Gradle 9 no longer puts it on the test runtime classpath implicitly.
If tests suddenly "pass" by finding nothing, check that declaration first.

Tests live in `<module>/src/test/kotlin/` mirroring the main package structure.

## The four kinds of test, by layer

### 1. Schema-conformance tests (`*-json` modules) — the highest-value tests here

`JsonSchemaTest` (and `JsonSerialisationTest` in `ocpp-1-6-json`) round-trip model instances
through the version's parser and validate the output against the **official OCPP JSON schemas**
shipped in `src/main/resources/`.

This is what backs the README's claim that serialisation is "verified against the json schemas
provided in the specification". **A failure here is a protocol-conformance bug, not a test-data
problem.** Fix the model or the mapper; do not relax the schema or add the failure to
`ignoredValidationCodes` unless you are deliberately accommodating a non-conforming peer.

`Ocpp*JsonParserErrorTest` covers the opposite direction: malformed input must come back as a
`CALL_ERROR` value rather than a thrown exception.

### 2. Parser round-trip tests (`*-soap` modules)

`Ocpp<NN>SoapParserTest` parses and re-serialises representative envelopes. Because
`Ocpp<NN>SoapBody` is an exhaustive nullable union, a missing operation binding produces a
**silent null** rather than a compile error — so a test case per operation is the only thing that
catches an omission. Add one whenever you add an operation.

### 3. Mapper and adapter tests (`*-api-adapter` modules)

Three files per adapter:

- `MapperTest` — the MapStruct mappers in isolation, field by field.
- `AdapterTest` — the generic → core direction (`Ocpp<NN>Adapter`).
- `CSApiAdapterTest` — the core → generic direction (`Ocpp<NN>CSApiAdapter`).

These carry the most subtle logic in the repo. What they must cover:

- **Every enum branch**, including the unsupported ones. The `isSupported(...)` pre-check list and
  the mapper's exhaustive `when` are two hand-maintained lists that must agree; only a test
  catches them drifting apart.
- **Lossy reductions** — `filterMeterValues` collapsing a list to one value, stop-reason
  collapsing, measurand rejection.
- **Fan-out operations** — `transactionEvent` expanding into several core calls.
- **Transaction-id correlation** — the stateful `TransactionRepository` path, including the
  not-found fallbacks.

Adapters consume `testFixtures(project(":generic-api"))`, which provides DSL builders such as
`TransactionEventBuilder` for constructing generic requests. Extend the fixtures rather than
hand-rolling request objects in each test.

### 4. Transport tests

`ocpp-transport-websocket` mixes mocked unit tests with a **real-socket end-to-end test** that
starts a server and connects to it. `ocpp-wamp` has `WampParserTest`, `WampIntegrationTest`,
`WampLoadTest` and `OcppWsEndPointTest`. These are the flakiness-prone ones: they poll with an
`awaitConnected`-style helper rather than sleeping. Keep that pattern; a bare `Thread.sleep` will
eventually go red on CI.

## Opt-in integration and example tests

`toolkit` contains `ExampleTest`, `ExampleCSApiTest`, `IntegrationTest*` and `SteveTest`. These
are gated by `@EnabledIfSystemProperty` and are **skipped by default**, including in CI — several
expect an external CSMS (SteVe, for instance) to talk to. Enable them by setting the property the
annotation names, e.g.:

```
./gradlew :toolkit:test -Dhas.local.steve=true
```

They double as executable documentation of the public API, and are more trustworthy than the root
README, whose OCPP 1.6/2.0 examples reference connection class names that no longer exist (the
real entry points are companion functions on `ApiFactory`). Read the tests, not the README, when
you need a working usage example.

## What is not tested

Stated plainly so nobody assumes otherwise:

- `utils` has no test sources at all.
- `OcppSoapServerTransport.sendMessageClass` is an unimplemented `TODO`, so CSMS-initiated pushes
  over SOAP are neither implemented nor tested.
- OCPP 1.5 has no `api-adapter` sources at all, so the generic-API path for 1.5 is untested
  because it is unimplemented — treat that family as less proven than 1.6 and 2.0.1.
- There is no coverage tool wired into the build, so there is no coverage figure to quote.

## Adding tests for a new operation

1. `*-core` — nothing to test directly; the model is data.
2. `*-json` — add the operation to `JsonSchemaTest` so the shipped schema is exercised.
3. `*-soap` — add a round-trip case to `Ocpp<NN>SoapParserTest`.
4. `*-api-adapter` — add cases to all three test files, covering every enum branch in both
   directions.
5. If the operation needs a new generic request shape, add a builder to `generic-api`'s
   `testFixtures` so every adapter can reuse it.

## See also

- [DEVELOPMENT.md](DEVELOPMENT.md) — how to run each of these
- [API-ADAPTER.guidelines.md](API-ADAPTER.guidelines.md) — the enum-support protocol under test
- [JSON.guidelines.md](JSON.guidelines.md) — schema naming and validation escape hatches
