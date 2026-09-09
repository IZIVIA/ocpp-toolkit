# toolkit

## Purpose
Aggregator/facade module: exposes `ApiFactory` and `CSMS`, the single entry points a consumer uses to open an OCPP-J or OCPP-S connection (charge point side) or run an OCPP server (CSMS side) across OCPP 1.5/1.6/2.0.1, without depending on each version's modules directly.

## Key Patterns

- **Companion-object factory functions** (`com.izivia.ocpp.integration.ApiFactory`) — `ocpp15ConnectionToCSMS` / `ocpp16ConnectionToCSMS` / `ocpp20ConnectionToCSMS` each build a version-specific `ClientTransport` (via `createClientTransport`) then wrap it in that version's `RealChargePointOperations<Version>`, injecting a `DefaultCSMSOperations<Version>(ocppCSCallbacks)`. The returned `ChargePointOperations<Version>` is the object a consumer calls `.connect()`, operation methods (e.g. `.authorize(...)`, `.statusNotification(...)`), and `.close()` on.
- **Generic (version-agnostic) path** — `ApiFactory.getCSMSApi(settings, ocppId, csApi, headers, newMessageId)` picks an adapter from `settings.ocppVersion` for callers working against the shared `generic-api` (`CSApi`/`CSMSApi`) instead of a version's typed API. **The `when` is not uniformly implemented**: `OCPP_1_6` builds `Ocpp16Adapter(ocppId, transport, csApi, RealTransactionRepository(), settings.ocpp16SecurityExtensions)`, `OCPP_2_0` builds `Ocpp20Adapter(ocppId, transport, csApi)`, and `OCPP_1_5` throws `NotImplementedError("Ocpp 1.5 api adapted not yet implemented")` — `ocpp-1-5-api-adapter` is declared in `settings.gradle.kts` and `api(...)`'d here but has no sources yet.
- **Transport selection is a single `when` on `TransportEnum`** (`WEBSOCKET` / `SOAP`) inside `createClientTransport` / `createServerTransportWebsocket` / `createServerTransportSoap`. SOAP requires `clientPath`/`clientPort` (non-null, enforced with `!!`); WebSocket does not.
- **SOAP is 1.5/1.6 only.** The private `getSoapParser(version)` at the bottom of `ApiFactory.kt` maps `OCPP_1_5`/`OCPP_1_6` to their parsers and falls through to `else -> TODO("Not yet implemented")`. Configuring a SOAP server for `OCPP_2_0` therefore throws `NotImplementedError` at wiring time, not a validation error.
- **`CSMS` (server side)** — built via `ApiFactory.csmsOcppServer(csmsSettings, csmsApiCallbacks, fn)`. It groups server transports by port, builds one `PolyHandler` (http4k) per port combining the SOAP routes and WebSocket routes present on that port (an empty side is left `null` since http4k 6 rejects empty route lists), and serves it with a custom `Undertow` server. `fn: (String) -> ChargingStationConfig` resolves per-charge-point config (e.g. accepted connection, SOAP callback URL) at connection time. Call `start()`/`stop()` to control the underlying servers, and `getCSApi15()/16()/20()/getCSApiGeneric()` to retrieve the per-version (or generic) `CSMSOperations` view for CSMS-initiated calls (e.g. remote start) once connections are established.
- **Callback dispatch in `CSMS` is by runtime type, and security is a separate facet.** Each element of `csmsApiCallbacks: List<CSMSCallbacks>` is matched with `when (csmsApi) { is ChargePointOperations16/15/20 -> ... }` to wire the matching `RealCSMSOperations<Version>`. `SecurityChargePointOperations16` is then checked in a *separate* `if`, not as another `when` branch — one callback object may implement both a core `ChargePointOperations` and the security interface, and an extra `when` branch would silently shadow and drop the security facet. Preserve that shape when adding a facet. A callback matching nothing raises `error("Unknown csms callbacks: ...")`, and a version with no transport on any configured port raises `error("No transport found for csmsApi ...")`.
- **Callback wiring (charge-point-side inbound requests)** — a consumer passes an `OcppCSCallbacks<Version>` implementation (override only the operations they need, e.g. `remoteStartTransaction`, `requestStartTransaction`) into the `ocpp<Version>ConnectionToCSMS` factory; it is wrapped in `DefaultCSMSOperations<Version>` and invoked when the CSMS sends a request over the transport.
- **Settings data classes** (`com.izivia.ocpp.integration.model.Settings`) — `Settings` (single client connection: version, transport, `target` derived from `domain`/`port`/`path` unless overridden, plus the `ocpp16SecurityExtensions` opt-in flag) for `getCSMSApi`; `CSMSSettings`/`ServerSetting` (list of servers, each with its own port/path/`ocppVersion` set/transport type/`OcppWampServerSettings`/`EventsListeners`) for `csmsOcppServer`.

## Conventions

- Version-specific imports are aliased per OCPP version (e.g. `... as DefaultCSMSOperations16`, `... as ChargePointOperations20`) since `ApiFactory`/`CSMS` reference every version's identically-named types side by side — follow this pattern when touching these files.
- All factory functions are `companion object` members of `ApiFactory` (not top-level functions), called as `ApiFactory.ocpp16ConnectionToCSMS(...)` or via the `Companion` import shown in tests.
- `newMessageId` defaults to `{ UUID.randomUUID().toString() }` wherever a transport needs to generate WAMP/message IDs (a `getCSMSApi` parameter, a `ServerSetting` field); override it only for deterministic tests.

## Adding Support for a New OCPP Version Here

1. Add `api(project(":ocpp-X-Y-core"))`, `api(project(":ocpp-X-Y-api"))`, `api(project(":ocpp-X-Y-api-adapter"))`, and (if applicable) `...-soap` to `dependencies` in `build.gradle.kts` — this is the aggregation contract: every module a consumer needs transitively must be `api(...)`'d here, not `implementation(...)`.
2. Add the version to **both** `OcppVersion` enums first — `com.izivia.ocpp.transport.OcppVersion` (module `ocpp-transport`, what this module consumes) and `com.izivia.ocpp.OcppVersion` (module `ocpp-wamp`). They carry identical entries and are not derived from one another.
3. In `ApiFactory`, add a branch in `getCSMSApi`'s `when (settings.ocppVersion)`, an `ocpp<Version>ConnectionToCSMS(...)` factory mirroring the existing ones (build `ClientTransport`, wrap in `RealChargePointOperations<Version>` + `DefaultCSMSOperations<Version>`), and a `getSoapParser` branch if the version speaks OCPP-S.
4. In `CSMS`, add a `ChargePointOperations<Version>` branch in the `csApi` map's `when` and a `getCSApi<Version>()` accessor.

## Tests

Tests under `src/test` split into two groups:

- **Run by default** — `ServerSettingSettingsTest.kt` (per-server `ServerSetting` wiring), `CSMSSecurityWiringTest.kt` (that the 1.6 security facet registers independently of the core one, i.e. the separate-`if` behaviour above), and `utils/KotlinInstantModuleTest.kt`.
- **Opt-in integration examples** — `ExampleTest.kt`, `ExampleCSApiTest.kt`, `IntegrationTest.kt`, `IntegrationTestCSApi.kt`, `SteveTest.kt`, gated by `@EnabledIfSystemProperty` against a real CSMS (e.g. SteVe). They are not run by default and double as the **trustworthy usage documentation** for this module — prefer them over the root `README.md`, whose OCPP 1.6/2.0 examples reference `Ocpp16ConnectionToCSMS`-style classes that do not exist (the real entry points are the `ApiFactory` companion functions).

## See Also

- `docs/CORE.guidelines.md`, `docs/API.guidelines.md`, `docs/API-ADAPTER.guidelines.md`, `docs/SOAP.guidelines.md` — patterns for the version-specific modules this facade aggregates.
- `ocpp-transport/` — shared `ClientTransport`/`ServerTransport`/`OcppVersion` abstractions consumed here.
- `ocpp-1-6-security/` — the 1.6 Security Whitepaper operations wired as a separate facet by `CSMS`.
- `operation-information/` — `RequestMetadata`, `CSMSCallbacks`, `ChargingStationConfig` types used throughout `ApiFactory`/`CSMS`.
