# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/).
Starting with the next release, this project adheres to
[Semantic Versioning](https://semver.org/spec/v2.0.0.html). Earlier releases did not always
do so: several minor and patch releases broke the public API (1.1.0, 1.1.1, 1.2.0,
1.2.2, 1.3.0, 1.3.1, 1.3.3, 1.3.8, 1.3.10, 1.3.11 and 2.0.3/2.0.4).
Every change that breaks consumers is marked **BREAKING** below, whatever the version that
shipped it.

Each version `x.y.z` is published to Maven Central under the `com.izivia` group from the
`R-x.y.z` tag.

## [Unreleased]

### Added

- `ocpp-wamp`: `EventsListeners` gains `onWsPingHandler` and `onWsPongHandler`, which receive a `WsControlFrameEvent` (charging station id, WebSocket connection id, upgrade headers, reception time) for the station's current connection. A frame is always reported before the reconnection or close event that ends its connection (#124)
- `ocpp-transport`: `ServerTransport.shutdown()` (no-op by default) for a graceful shutdown. `WebsocketServer` delegates it to `OcppWampServer.shutdown()`, which now also waits, up to `timeoutInMs`, for inbound calls still in flight (#122)
- `ocpp-transport`: `ClientTransport.execute`, `ServerTransport.execute` and `Iterable<ServerTransport>.transportFor` replace the `sendMessage` boilerplate that was copied in every `Real*Operations` class (#121)
- `transport-soap`, `toolkit`: `SoapClientSettings` takes `host`, `scheme`, `advertisedPort` and `advertisedUrl`, validates its values at construction, and `OcppSoapClientTransport.callbackUrl()` exposes the URL the charge point advertises. `scheme = "https"` with `advertisedPort`, or `advertisedUrl`, covers a TLS terminator or reverse proxy in front of the charge point (#114)
- `utils`, `ocpp-wamp`, `ocpp-transport-websocket`, `ocpp-transport-soap`, `toolkit`: an optional `OcppErrorListener` observes the failures the toolkit handles itself, which used to reach only the logs and the `CALLERROR` sent back. Each `OcppErrorEvent` has a category (`UNHANDLED_ACTION`, `PROTOCOL_ERROR`, `HANDLER_ERROR`, `TRANSPORT_ERROR`), the station id, OCPP version, action, message id, error code and cause where known, and never the payload. Set it on `ServerSetting.errorListener` for the WebSocket and SOAP servers (#128), and on `Settings.errorListener`, `OcppWampClient.newClient`, `WebsocketClient` and `OcppSoapClientTransport.createClient` for the clients (#139). It only observes: answers to the other side do not change, and anything the listener throws is logged and ignored. Network failures are not reported, and neither is a station refused over SOAP. `ocppId` and `action` come from the peer: bound them before using them as a metric dimension
- `ocpp-wamp`, `ocpp-transport-websocket`, `toolkit`: a server-level `ConnectionAuthorizer` receives a `ConnectionRequest` (station id and upgrade request headers) before any handler's `accept`. A refused or throwing check answers a 401 with a `WWW-Authenticate: Basic` challenge, and the station is never registered nor notified. Pass it to `OcppWampServer.newServer`, `WebsocketServer` or `ServerSetting.authorizer`. The upgrade request is no longer logged with its `Authorization` header (#154)
- `ocpp-wamp`: `SecurityProfileAuthorizer`, a `ConnectionAuthorizer` that enforces the HTTP Basic authentication of security profiles 1 and 2 against the `StationSecurity` your application returns for each station: a `BasicAuthPassword` (OCPP 2.0.1, compared as UTF-8), an `AuthorizationKey` (OCPP 1.6, accepted as its bytes or its hexadecimal form) or `Unsecured` for a legacy station. Each secret carries an `Enforcement`: `BLOCK` refuses a failed check, `REPORT` admits the station anyway during a migration. `onFailure` receives an `AuthenticationFailure` with its reason, never the credentials. Client certificates and TLS are not covered (#157)
- `ocpp-wamp`: `NotConnectedException` (a subtype of `IllegalStateException`) is thrown by `OcppWampClient.sendBlocking` when there is no connection; nothing was sent, so the call can be retried (#147)
- `ocpp-wamp`, `ocpp-transport-websocket`, `toolkit`: `OcppWampClientSettings` (call timeout, auto-reconnect, base reconnect delay) is the client-side counterpart of `OcppWampServerSettings`. Pass it to the new `OcppWampClient.newClient` overload, `WebsocketClient`, or `Settings.wampSettings` and the `ocppNNConnectionToCSMS` factories. `timeoutInMs = 0` is now rejected by the legacy `newClient` overload too (#144)
- `ocpp-transport-websocket`, `toolkit`: `WebsocketServer` and `ServerSetting` take `timestampZoneFallback: (CSOcppId) -> ZoneId?`, which chooses per charging station the zone of timestamps sent without timezone, in inbound calls and in the results of calls you send. The default is UTC, as before; returning `null` refuses them (a CallError inbound, an exception from `sendMessageClass`). SOAP still reads them as UTC (#138)

### Changed

- **BREAKING:** `generic-api`: `CSApi` gains an abstract `getDiagnostics(meta, GetDiagnosticsReq)` with its own `GetDiagnosticsReq` / `GetDiagnosticsResp`. This is a source and binary break: add the method to every `CSApi` implementation and recompile, otherwise the first `GetDiagnostics` a station receives throws `AbstractMethodError`. On OCPP 1.2, 1.5 and 1.6, `GetDiagnostics` now reaches `getDiagnostics` instead of `getLog` (which no longer receives it with `requestId = 1` and `logType = DiagnosticsLog`): move any diagnostics-upload code from `getLog` to `getDiagnostics`. `getLog` is now only used on OCPP 2.0.1 and on 1.6 with the security whitepaper enabled (#113)
- **BREAKING:** `toolkit`, `transport-soap`: `Settings.clientPath` / `clientPort` and the matching parameters of `ocpp12ConnectionToCSMS`, `ocpp15ConnectionToCSMS`, `ocpp16ConnectionToCSMS` and `ocpp20ConnectionToCSMS` are replaced by `soapClient: SoapClientSettings?`. `SoapClientSettings(path, port)` becomes `SoapClientSettings(port, host, path = "", scheme = "http", advertisedPort = null, advertisedUrl = null)`, and `host` is mandatory. Migrate `Settings(…, clientPath = "/cp", clientPort = 8081)` to `Settings(…, soapClient = SoapClientSettings(port = 8081, path = "/cp", host = "192.168.0.3"))`. A SOAP connection without `soapClient` now fails with an `IllegalArgumentException` instead of a `NullPointerException` (#114)
- **BREAKING:** `ocpp-json`: the `OcppJsonValidator(specVersion)` constructor no longer compiles. Pass the schema folder as `OcppJsonValidator(SpecVersion.VersionFlag.V4, OcppSchemaFolder.OCPP_1_6)`. The bundled parsers are already updated, so only code that instantiates the validator directly is affected. A schema missing from the classpath now yields a call error carrying `ErrorDetail(code="schema", …)` instead of a bare internal error (#112)
- The JSON schemas of `ocpp-1-5-json`, `ocpp-1-6-json` and `ocpp-2-0-json` now live in `ocpp15/`, `ocpp16/` and `ocpp20/` inside their jars instead of at the classpath root. Code that read them as bare classpath resources must use the new paths (#112)
- `ocpp-wamp`: `EventsListeners` gains constructor parameters with default values. This is source compatible but not binary compatible: recompile against this version (#124)
- `ocpp-wamp`: `action` and `errorDescription` of a parsed message are now decoded, so handlers read the unescaped text instead of the wire form. Nothing changes for values without an escape. `msgId` and `payload` stay verbatim (#127)
- `ocpp-transport`, `ocpp-transport-websocket`, `ocpp-transport-soap`, `ocpp-wamp`: the dependencies whose types appear in their public signatures (`ocpp-wamp`, `ocpp-transport`, `ocpp-soap`, `operation-information`, `utils`, `http4k-core`, `undertow-core`, `http4k-realtime-core`) are now declared as `api`, so they move from runtime to compile scope in the published POMs and consumers no longer need to declare them. `utils` is exposed by `ocpp-transport-websocket` and `ocpp-transport-soap` since `OcppErrorListener` is part of their signatures (#120, #121, #128)
- `ocpp-transport`: `CSMS.stop()` now drains every transport (waiting for in-flight inbound calls) before stopping the HTTP servers, so it blocks and cannot be undone. When no transport serves a charging station, `acceptConnection` now runs before the "No transport to send a message to" error (#121, #122)
- **BREAKING:** `ocpp-wamp`, `toolkit`: a WebSocket upgrade is refused (400) unless its URL is exactly `/<path>/{id}`, instead of connecting when the URL merely contains the endpoint. `/ws/OK/extra` and `/ocpp/ws/OK` used to connect as station `OK`. A reverse proxy that publishes the server under a prefix must strip it before forwarding; a trailing or double slash is still accepted. Two WebSocket paths on one port (`/ws16/OK` next to `/ws20/OK`) now both connect, where one of them failed with a 500 (#145)
- **BREAKING:** `ocpp-wamp`, `toolkit`: `WsServerConfig.acceptWebSocketPredicate` and the `acceptWebSocketPredicate` parameter of `Undertow` are replaced by `admitWebSocket`, which returns a `WebSocketUpgradeDecision` (`Accept`, `Refuse(status, headers)`) or `null` when the request is not for that endpoint, and `CSMS` lets the first endpoint that claims a request decide. Rename them in your `WsServerConfig` implementations and `Undertow` calls; a request no endpoint claims now answers 404 (#154)
- **BREAKING:** `ocpp-wamp`, `ocpp-transport-websocket`, `ocpp-transport-soap`, `toolkit`: recompile against this version, and pass `newMessageId` as a named argument where you passed it as a trailing lambda. New trailing parameters with default values (`errorListener` and `authorizer` on `OcppWampServer.newServer`, `UndertowOcppWampServer`, `WebsocketServer`, `ServerSetting` and `OcppSoapServerTransport.create`; `timestampZoneFallback` on `WebsocketServer` and `ServerSetting`; `errorListener` and `wampSettings` on `WebsocketClient`, `Settings`, `OcppWampClient.newClient`, `OcppSoapClientTransport.createClient` and the `ocppNNConnectionToCSMS` factories). They break binary compatibility, and a call that passes `newMessageId` as a trailing lambda to `WebsocketClient` or the factories no longer compiles. A test stubbing `OcppWampClient.newClient(any(), any(), any(), any())` is now ambiguous and must target the `OcppWampClientSettings` overload (#128, #138, #139, #144)
- `ocpp-wamp`: `AutoReconnectConfig.callTimeout` now bounds the whole wait for the connection, where it could reach about twice its value (#147)
- `ocpp-wamp`: `onWsConnectHandler`, `onWsReconnectHandler` and `onWsCloseHandler` now run holding a lock of their station, so a listener that blocks holds up the connection, reconnection and close of that station, and `shutdown()` waits for it. Listeners must not block (#150)

### Fixed

- `ocpp-wamp`: the server answers a call with no matching action handler with a valid `[4,"<id>","InternalError","No action handler found",{}]` frame instead of invalid JSON, and no longer echoes the call payload (#127)
- `ocpp-wamp`: `toJson()` escapes `action` and `errorDescription`, so an exception message used as a call error description can no longer produce an invalid frame; `parse(message.toJson())` returns an equal message (#127)
- `ocpp-wamp`: a call error whose description contains a brace (for example `bad { thing`) is parsed instead of returning `null`, which made the sender's call time out (#127)
- `ocpp-1-5-json`, `ocpp-1-6-json`, `ocpp-2-0-json`: a valid OCPP 2.0.1 request was rejected against the OCPP 1.6 schema of the same name when several JSON modules were on the classpath, as with `toolkit`. Schemas are now resolved per OCPP version (#112)
- `ocpp-2-0-json`: ship the missing `Get15118EVCertificate` request and response schemas, so that action no longer fails with validation enabled (#112)
- `ocpp-wamp`: a station's close is notified exactly once, including on shutdown; a replaced connection closing late, or a reset handled as an error then a close, used to notify it twice (#124)
- `toolkit`, `utils`: the default `Settings.target` for an IPv6 host is bracketed (#114)
- `ocpp-wamp`: the client runs inbound call handlers on `Dispatchers.IO` instead of `Dispatchers.Default`, and handles call results and errors on the reader thread. Blocking `CSApi` handlers no longer starve the pool that must deliver the results they wait for, and a malformed message is logged and dropped without stopping the reader. `Dispatchers.IO` is shared with your application, and `timeoutInMs` does not interrupt a handler that blocks (#146)
- `ocpp-wamp`: `AutoReconnectOcppWampClient` waits for the reconnection when a call races a dropped connection, instead of failing with `IllegalStateException` (#147)
- `ocpp-wamp`: a failed connection attempt is reported with `onConnectionFailure`, not `onConnectionLost`, and `connect()` notifies its listener once, including on its own timeout. With auto-reconnect, a failed attempt no longer emits a spurious `onConnectionLost` first (#151)
- `ocpp-wamp`: connections and closes of a station are serialized, so concurrent connections of one station each replace and close a distinct connection, and a close is never notified after the connection that follows it (#150)
- `ocpp-wamp`: an exception thrown by `onWsConnectHandler` or `onWsReconnectHandler` is now logged and the station stays connected, instead of leaving it registered without a socket handler: its calls timed out, its close was never notified and its next connection was taken for a reconnection. Throwing from these listeners never refused a station: use a `ConnectionAuthorizer` for that. The close listener still propagates what it throws (#153)
- `ocpp-wamp`, `ocpp-transport-websocket`, `toolkit`: a station that every handler refuses (`acceptConnection` returning `false`, or no handler registered) now gets the HTTP 404 that OCPP-J asks for, instead of completing the handshake and firing `onWsConnectHandler`. Code that relied on such a station connecting must accept it in a handler (#134)

## [3.0.0] - 2026-09-09

### Added

- OCPP 1.2 support, end to end: core model, SOAP parser, JSON/WebSocket parser with schemas, and a generic-API adapter. New modules `ocpp-1-2-core`, `ocpp-1-2-api`, `ocpp-1-2-soap`, `ocpp-1-2-json` and `ocpp-1-2-api-adapter`, plus `ApiFactory.ocpp12ConnectionToCSMS` for the charge-point side (#98, #107)
- OCPP 1.2 over WebSocket: the JSON/WebSocket transport is independent of message content and `ocpp1.2` is a registered subprotocol. SOAP remains available for all 1.x versions, and OCPP 2.0.1 is WebSocket only (#107)
- `ocpp-1-5-api-adapter`: the OCPP 1.5 generic-API adapter is implemented, with exhaustive enum mapping onto the 1.5 targets, so `ApiFactory.chargingStationConnectionToCSMS` no longer throws `NotImplementedError` for 1.5 (#98)
- `ocpp-1-6-security`: the OCPP 1.6 Security Whitepaper operations `certificateSigned`, `deleteCertificate`, `extendedTriggerMessage`, `getInstalledCertificateIds`, `getLog`, `installCertificate` and `signedUpdateFirmware`, plus the charge-point side, wired into the CSMS server as an independent facet. They are gated by `Settings.ocpp16SecurityExtensions`, which defaults to `false`, so existing 1.6 deployments keep their behaviour; `securityEventNotification` and `signCertificate` fail fast while the flag is off (#97)
- `toolkit`: `ServerSetting.wampSettings: OcppWampServerSettings` is passed from the high-level CSMS API down to `WebsocketServer`. The WebSocket transport was locked to an unbounded `newCachedThreadPool` and a fixed 30 s call timeout. `WebsocketServer.asServer(port)` mirrors the existing `OcppWampServer` and `OcppSoapServerTransport` extensions (#101)
- `toolkit`: `Settings.newMessageId: () -> String` is carried on `Settings` and used by both the client and server transports
- New artifacts on Maven Central: `ocpp-1-2-core`, `ocpp-1-2-api`, `ocpp-1-2-json`, `ocpp-1-2-soap`, `ocpp-1-2-api-adapter` and `ocpp-1-6-security`

### Changed

- **BREAKING:** the public time API moved from `kotlinx.datetime` to `kotlin.time`: all published models and APIs expose `kotlin.time.Instant` and `kotlin.time.Clock`, and `kotlinx-datetime` is no longer a dependency of the toolkit. Compiling against these types needs Kotlin 2.3 or newer, and `kotlin-stdlib` 2.3 or newer at runtime. With `kotlinx-datetime` 0.7 or newer, `kotlinx.datetime.Instant` is a typealias of `kotlin.time.Instant`, so call sites keep compiling; with an older one, migrate them to `kotlin.time`. The `ocpp-utils` Jackson module `KotlinxInstantModule` is renamed `KotlinInstantModule` (#100)
- **BREAKING:** `generic-api`: `CSMSApi` gains `diagnosticsStatusNotification`, with its own request, response and `DiagnosticsStatusEnumType` modelled on OCPP 1.6, because `LogStatusNotification` (2.0.1 and the 1.6 whitepaper) and `DiagnosticsStatusNotification` (1.2, 1.5, 1.6) are different messages. `CSMSApi` has no default method bodies, so out-of-tree implementations must add the method. On OCPP 1.6, an implementation that used `logStatusNotification` to answer `GetDiagnostics` must switch to `diagnosticsStatusNotification`: `logStatusNotification` now takes the whitepaper path and fails unless `ocpp16SecurityExtensions = true`. On 1.5 and 1.2 `logStatusNotification` is rejected, and the transient `Idle` and `Uploading` statuses are filtered out of `diagnosticsStatusNotification`; on 2.0.1 `diagnosticsStatusNotification` is rejected
- **BREAKING:** both `com.izivia.ocpp.transport.OcppVersion` and `com.izivia.ocpp.OcppVersion` (WAMP) gain `OCPP_1_2("ocpp1.2")` as their first entry. Exhaustive `when` expressions over these enums no longer compile until you add the branch
- **BREAKING:** `ocpp-transport-websocket`: `WebsocketServer` takes a new `settings: OcppWampServerSettings = OcppWampServerSettings()` parameter before `listeners`. Positional callers of `WebsocketServer(versions, path, newMessageId, listeners)` must switch to named arguments (#101)
- **BREAKING:** `toolkit`: `ApiFactory` throws `IllegalArgumentException("OCPP 2.0 has no SOAP transport")` for OCPP 2.0 over SOAP instead of failing on a `TODO()` with `NotImplementedError`. WebSocket is available for every version
- Timestamps are serialized in canonical UTC ISO-8601 truncated to milliseconds. Parsing stays tolerant: it accepts `Z`, full offsets (`+02:00`), incomplete offsets (`+02`) and timestamps without a timezone. A timestamp without a timezone is read as UTC and logged as a warning, so a charge point that sends local time without an offset can still produce shifted transaction timestamps
- `generic-api`: `CSApi.updateFirmware` receives both the core OCPP 1.6 `UpdateFirmware` and the whitepaper `SignedUpdateFirmware`. The toolkit verifies no signature: implementations must validate `firmware.signingCertificate` against a trust anchor and check `firmware.signature` over the downloaded artifact before installing, and set their own policy for unsigned updates
- The OCPP 1.2 and 1.5 modules are built on `kotlin.time`

### Fixed

- `ocpp-1-6-api-adapter`: `remoteStartId` is generated in `[1, Int.MAX_VALUE)`; `Random.nextInt()` could return a negative value, which OCPP does not allow
- `toolkit`: the CSMS server registers the core and security facets independently, so enabling one no longer disturbs the other
- `ocpp-1-6-api-adapter`: the security handlers are not registered while the whitepaper is disabled
- `ocpp-1-2-api-adapter`, `ocpp-1-5-api-adapter`: a narrower `meterValues` guard and an exhaustive sampled-value enum mapping
- `ocpp-1-5-api-adapter`: the `notifyEVChargingSchedule` rejection message names the right operation

## [2.0.4] - 2026-07-07

### Changed

- **BREAKING:** first published release carrying the changes of 2.0.3 (Java 21, Kotlin 2.4, http4k 6): 2.0.3 was tagged on the same day but never reached Maven Central because its publish workflow still ran with the old Java and Gradle setup, which this release fixes. Consumers moving from 2.0.2 must read the 2.0.3 entry

## [2.0.3] - 2026-07-07

_Not published to Maven Central._

### Changed

- **BREAKING:** the toolkit is now compiled against http4k 6.53 and requires Java 21 (JVM target 17 to 21, built with Kotlin 2.4). Applications on Java 17 or on http4k 5.x must upgrade; applications on http4k 6.x, which removed `org.http4k.routing.WsRouter`, no longer fail with a `NoClassDefFoundError` (#95)
- `ocpp-transport-soap`: depends on `http4k-api-openapi` instead of `http4k-contract` (#95)
- Dependencies upgraded to their latest versions: Jackson 2.22, Kotlin coroutines 1.11, kotlinx-datetime 0.4.1, commons-lang3, logback, undertow 2.4.1 and json-schema-validator 1.5.9

### Fixed

- `ocpp-wamp`: the WebSocket client reconnects in a loop, and no longer fails when the peer closes with a reserved close code such as 1005 (`CLOSE_NO_STATUS_CODE`): such codes are mapped to a normal closure (#93)
- `ocpp-1-6-api-adapter`: an unknown transaction id keeps the request's transaction id instead of being dropped (#94)

## [2.0.2] - 2025-12-19

### Fixed

- `toolkit`: expose the `utils` module as an `api` dependency, so consumers no longer have to declare it themselves (the 2.0.1 fix, repeated)

## [2.0.1] - 2025-12-17

### Fixed

- `toolkit`: expose the `utils` module as an `api` dependency, so consumers no longer have to declare it themselves

## [2.0.0] - 2025-12-17

### Added

- `ocpp-wamp`, `ocpp-transport-soap`: `OcppWampServer.asServer(port)` and `OcppSoapServerTransport.asServer(port)` start a server on a port, and `OcppSoapServerTransport` / `OcppWampServer` expose their http4k handler, so the JSON/WebSocket and SOAP transports can share one port. The toolkit's `CSMS` serves every transport configured on the same port from a single server (#86)
- `ocpp-transport-websocket`: `WebsocketClient` takes a `newMessageId: () -> String` parameter to generate custom message ids, defaulting to `UUID.randomUUID()` (#86)
- `toolkit`: every module is exposed with `api` instead of `implementation`, so the types of its sub-dependencies are available to consumers without declaring them (#78)

### Changed

- **BREAKING:** `ocpp-transport`, `ocpp-wamp`, `ocpp-transport-websocket`, `ocpp-transport-soap`: transports no longer own a server or a port. `ServerTransport.start()` and `stop()` are removed; the `port` parameter is removed from `WebsocketServer`, `OcppWampServer.newServer` and `UndertowOcppWampServer`; `OcppWampServer.start()` and `stop()` are removed (`shutdown()` stays); and `OcppSoapServerTransport.createServer(…, port, …)` is replaced by `OcppSoapServerTransport.create(ocppVersion, path, parser, newMessageId)`. To run a transport on its own, call `asServer(port)` and start the returned `Http4kServer`; through the toolkit, the `CSMS` starts and stops the servers (#86)
- **BREAKING:** `ocpp-transport-soap`: `OcppSoapClientTransport` no longer has a public constructor; create it with `OcppSoapClientTransport.createClient(clientSettings, ocppId, target, parser, …)` (#86)
- **BREAKING:** `ocpp-transport`, `ocpp-wamp`: the OCPP 2.0 WebSocket subprotocol is now `ocpp2.0.1` instead of `ocpp2.0`, since 2.0 is only a draft. A charging station that negotiates `ocpp2.0` is no longer accepted and must use `ocpp2.0.1`, and a client built with `OCPP_2_0` offers `ocpp2.0.1` (#79)
- **BREAKING:** `toolkit`: the `CSMS` constructor takes `Map<ServerTransport, Pair<Int, Set<OcppVersion>>>` (port and versions per transport) instead of `Map<ServerTransport, Set<OcppVersion>>`. Code that uses `ApiFactory` and `ServerSetting` is unaffected (#86)
- Java compilation targets Java 17, matching the existing Kotlin JVM target (#85)

## [1.3.15] - 2025-12-04

### Added

- `ocpp-1-5-core`: OCPP 1.5 request and response classes now implement the `Request` and `Response` marker interfaces, as OCPP 1.6 classes already do (#92)

## [1.3.14] - 2025-11-14

### Fixed

- `ocpp-1-6-api-adapter`: the OCPP 1.6 `StatusNotification` built from a transaction update maps the `EVConnected` charging state according to the event type: `Preparing` when the transaction starts, `Finishing` when it ends, `Charging` otherwise (#91)

## [1.3.13] - 2025-09-30

### Added

- `generic-api`: `MeterValuesReq` has an optional `connectorId` field, used by the OCPP 1.6 adapter when set (falls back to `evseId`) (#87)

### Fixed

- `ocpp-1-6-api-adapter`: the connector id of OCPP 1.6 `MeterValues` is no longer always taken from the EVSE id (#87)

## [1.3.12] - 2025-01-30

### Fixed

- `ocpp-transport-soap`: the route served by the SOAP client is now `/` instead of the client `path` setting, which was applied twice (#81)

## [1.3.11] - 2025-01-14

### Changed

- **BREAKING:** `ocpp-wamp`: a blocking call that times out now throws `java.util.concurrent.TimeoutException` instead of `IllegalStateException`. Code that catches `IllegalStateException` to detect a timeout must catch `TimeoutException` (#80)

## [1.3.10] - 2024-09-17

### Added

- `ocpp-wamp`: `OcppWampServer.sendBlocking` accepts an optional `timeoutInMs` that overrides the server's default timeout for one call (#77)

### Changed

- **BREAKING:** `ocpp-wamp`: `OcppWampServer.sendBlocking` has a new `timeoutInMs: Long? = null` parameter. Callers are unaffected, but custom `OcppWampServer` implementations must add it to their override. `WampCallManager.timeoutInMs` is renamed `defaultTimeoutInMs`, and `callBlocking` takes an optional `specificTimeoutInMs` (#77)

### Removed

- `ocpp-wamp`: the bundled `logback.xml` is removed from the jar, so the library no longer imposes a logging configuration on applications. Applications that relied on it must configure logging themselves (#73)

## [1.3.9] - 2024-07-08

### Added

- `toolkit`: server settings accept `EventsListeners` (connect, reconnect and close handlers), so applications built with `ApiFactory` can react to WebSocket events. `WebsocketServer` takes the same `listeners` parameter (#74)
- `ocpp-wamp`: `OcppWampServer.sendBlocking` throws the new `NoConnectionException` (a subclass of `IllegalStateException`) when no charging station is connected under the given id (#75)

## [1.3.8] - 2024-07-01

### Added

- `ocpp-1-6-core`: every OCPP 1.6 request and response class implements the new `Request` or `Response` marker interface (#72)

### Changed

- **BREAKING:** `ocpp-2-0-core` and `generic-api`: the enum constant `MonitorEnumType.PeriodicClockAligne` is renamed `PeriodicClockAligned`, and its JSON value is now the spec-compliant `PeriodicClockAligned`. Update references to the constant (#71)
- **BREAKING:** `ocpp-1-6-core`: five classes moved to the package matching their operation. Update imports: `DeleteCertificateResp` from `…model.certificatesigned` to `…model.deletecertificate`; `ExtendedTriggerMessageReq` and `ExtendedTriggerMessageResp` from `…model.triggermessage` to `…model.extendedtriggermessage`; the signed firmware status notification classes from `…model.firmwarestatusnotification` to `…model.signedfirmwarestatusnotification`; `SignedUpdateFirmwareReq` and `SignedUpdateFirmwareResp` from `…model.updatefirmware` to `…model.signedupdatefirmware` (#72)

## [1.3.7] - 2024-04-26

### Added

- `operation-information`: `RequestMetadata` has an optional `messageId`, filled by the WebSocket and SOAP servers with the id of the incoming message

### Fixed

- `ocpp-transport-soap`: the SOAP server no longer puts the message id in `RequestMetadata.chargingStationId`

## [1.3.6] - 2024-04-24

### Fixed

- `ocpp-1-5-json`, `ocpp-1-5-soap`, `ocpp-1-6-json`, `ocpp-1-6-soap`, `ocpp-2-0-json`: an empty value in `ChangeConfiguration` (1.5, 1.6) or `SetVariables` (2.0) is serialized instead of being omitted (#69)

## [1.3.5] - 2024-03-28

### Fixed

- `ocpp-1-6-soap`: the `phase` field of sampled values (for example `L1-N`) is now parsed instead of being dropped (#67)

## [1.3.4] - 2024-03-20

### Fixed

- `ocpp-1-6-api-adapter`: the EVSE mapping falls back to the EVSE id when no connector id is given, which 1.3.2 had broken (#66)

## [1.3.3] - 2024-03-14

### Changed

- **BREAKING:** `ocpp-json`: `OcppJsonParser.getRequestPayloadClass` and `getResponsePayloadClass` are public instead of protected. This makes them callable by consumers, but a subclass that overrides them with `protected` visibility must now declare them `public` (#65)

## [1.3.2] - 2024-03-14

### Fixed

- `ocpp-1-6-api-adapter`: the mapping of an EVSE to an OCPP 1.6 connector uses the EVSE's connector id and no longer rejects an EVSE whose id and connector id differ (#63). It throws when the connector id is missing; 1.3.4 fixes that

## [1.3.1] - 2024-03-13

### Added

- `ocpp-1-5-core`, `ocpp-1-6-core`, `ocpp-2-0-core`: each `Actions` entry exposes its response class through `classResponse`, and the JSON parsers implement `getResponsePayloadClass` (#62)

### Changed

- **BREAKING:** `utils`: the `IActions` interface has a new abstract member `classResponse`, and `OcppJsonParser` has a new abstract method `getResponsePayloadClass`. Custom implementations of `IActions` or subclasses of `OcppJsonParser` must provide them (#62)

### Fixed

- `ocpp-json`: `parseAnyFromString` called without a response class on a `CALLRESULT` now reports a clear `NOT_IMPLEMENTED` fault (`ActionResponseNotSpecifiedException`) instead of failing obscurely (#62)

## [1.3.0] - 2024-03-06

### Added

- `ocpp-1-6-api-adapter`: the `transactionId` of a `SetChargingProfile` request is mapped to the local transaction id (#57)

### Changed

- **BREAKING:** the library is built with Kotlin 1.9 (language and API level 1.9, up from 1.6). Consumers need a Kotlin compiler able to read 1.9 metadata (1.8 or later) (#59)
- **BREAKING:** http4k is upgraded from 4.33 to 5.13, a major version used in the public API of `ocpp-wamp` (`Uri`, `Http4kServer`, `WsHandler`). Consumers that use http4k alongside the toolkit must move to http4k 5 (#59)
- Jackson 2.14.0 to 2.16.1, Undertow 2.3.7 to 2.3.12 (#59)

## [1.2.3] - 2024-03-05

### Fixed

- `ocpp-wamp`: a CALL or CALLRESULT payload with carriage returns or line breaks is parsed without altering it (#60)
- `ocpp-wamp`: a CALLERROR whose description contains a comma or a quote is parsed correctly (#60)
- `ocpp-wamp`: closing a client now also closes the underlying connection (#56)

## [1.2.2] - 2023-12-05

### Changed

- **BREAKING:** `ocpp-wamp`: the WebSocket client is reworked around a new auto-reconnect implementation (#52). `OcppWampClient` has a new abstract property `state` and `connect` takes an optional `ConnectionListener`; custom `OcppWampClient` implementations must adapt. `OcppWampClient.newClient` now returns `OcppWampClient` instead of `OkHttpOcppWampClient`. The `OkHttpOcppWampClient` constructor no longer takes `autoReconnect` and `baseAutoReconnectDelayInMs` (use `newClient`), and its nested `ConnectionState` enum is now the top-level `com.izivia.ocpp.wamp.client.ConnectionState`

### Fixed

- `ocpp-transport-soap`: the SOAP client no longer appends the charging station id to the target URL and sends a `From` address that includes the port (#46)
- `ocpp-wamp`: more stable client reconnection, and a thread-safe connection registry on the server, matching charging station ids case-insensitively (#52)

## [1.2.1] - 2023-10-06

### Fixed

- `utils`: serialized instants are truncated to milliseconds, as required by OCPP, instead of carrying micro or nanoseconds (#50)

## [1.2.0] - 2023-09-21

### Added

- `ocpp-wamp`: calls to a charging station run on a dedicated executor, customizable by overriding `OcppWampServerSettings.buildCallsExecutor()`, and call logs include queueing time and latency (#49)

### Changed

- **BREAKING:** `ocpp-wamp`: `OcppWampServer.newServer` and the `UndertowOcppWampServer` constructor replace `timeoutInMs`, `onWsConnectHandler`, `onWsReconnectHandler`, `onWsCloseHandler` and `ignoreCorruptMessageByHeaders` with two parameters: `settings: OcppWampServerSettings` (holds `timeoutInMs`) and `listeners: EventsListeners` (holds the three handlers). `UndertowOcppWampServer.timeoutInMs` is now `settings.timeoutInMs`. The default timeout is 30 s instead of 60 s (#49)

### Removed

- **BREAKING:** `ocpp-wamp`: the `ignoreCorruptMessageByHeaders` option, added in 1.1.1, is removed together with its tolerant handshake, and `OkHttpOcppWampClient.wampConnection` is private again. Applications that used the option must drop it (#49)

### Fixed

- `ocpp-wamp`: a CALLERROR with an empty error description is parsed instead of being dropped
- `ocpp-soap`, `ocpp-transport-soap`: the SOAP client also accepts a response without a SOAP header

## [1.1.1] - 2023-09-14

_Pre-releases `1.1.1-TEST.1` and `1.1.1-TEST.2` were published before this release._

### Added

- `ocpp-wamp`: `OcppWampServer.newServer` accepts `ignoreCorruptMessageByHeaders`, a list of header name and value pairs; for matching charging stations the server tolerates corrupt WebSocket frames (#47)

### Changed

- **BREAKING:** `ocpp-soap`: `SoapHeader.action` is nullable (`ValueText?`), and every `SoapHeader` field has a default value, so that a message without a SOAP header can be read. Code that reads `header.action` must handle `null` (#48)

### Fixed

- `ocpp-soap`, `ocpp-transport-soap`: a charging station that omits the SOAP header in its response no longer breaks parsing (#48)
- `ocpp-wamp`: fixes the handling of partial WebSocket frames on the server (#47)

## [1.1.0] - 2023-06-30

### Added

- `ocpp-wamp`: the server calls a new `onWsReconnectHandler` when a charging station reconnects over a still-open connection (#45)
- `ocpp-wamp`: the client can disable auto-reconnect with `autoReconnect = false` (#45)

### Changed

- **BREAKING:** `ocpp-wamp`: the new parameters were inserted in the middle of existing signatures. `OcppWampClient.newClient` and `OkHttpOcppWampClient` take `autoReconnect` before `baseAutoReconnectDelayInMs`; `OcppWampServer.newServer` and `UndertowOcppWampServer` take `onWsReconnectHandler` between `onWsConnectHandler` and `onWsCloseHandler`. Callers that pass these arguments positionally must use named arguments (#45)

### Fixed

- `ocpp-wamp`: a connection reset on the server is handled as a disconnection, so the close event is no longer missed (#45)

## [1.0.1] - 2023-06-27

### Fixed

- `ocpp-wamp`: a charging station reconnecting while its previous connection is still registered no longer triggers `onWsConnectHandler` again nor a spurious `onWsCloseHandler` for the replaced connection, and the server ignores empty messages (#44)

## [1.0.0] - 2023-06-26

First 1.x release. It brings robustness fixes to the WAMP WebSocket layer and one changed default; the public API is otherwise unchanged from 0.1.19.

### Changed

- **BREAKING:** `ocpp-wamp`: the default `timeoutInMs` of `OcppWampServer.newServer` is 60 s instead of 10 s. Applications that relied on the 10 s default must pass it explicitly (#43)
- `ocpp-wamp`: Undertow (`undertow-core` and `undertow-servlet` 2.3.7) is now a direct dependency (#43)

### Fixed

- `ocpp-wamp`: more robust handling of closed WebSocket connections and of messages that cannot be parsed. The server answers a message it cannot parse with a CALLERROR instead of dropping it, and logs more context (#43)

## [0.1.20] - 2023-05-25

_Not published to Maven Central._

### Changed

- Internal changes only; no consumer-visible change.

## [0.1.19] - 2023-05-25

### Added

- `ocpp-soap`: the SOAP envelope message id now has a default value, so messages sent by a charging station without a `MessageID` header no longer fail to marshal (#41)
- `ocpp-soap`: the OCPP namespace used when serializing SOAP messages can be forced through a new argument (#42)

### Fixed

- `ocpp-soap`: use the correct OCPP namespace in SOAP messages (#42)

## [0.1.18] - 2023-05-24

### Fixed

- `ocpp-wamp`: the connections map is now thread-safe (#40)
- `ocpp-wamp`: further fixes to WAMP message parsing observed in production (#40)

## [0.1.17] - 2023-04-07

### Fixed

- `ocpp-wamp`: parse WAMP messages with a regular expression chosen by message type, which accepts the malformed frames seen in production (#34)
- `ocpp-wamp`: connection ids are matched case-insensitively and a duplicate slash in the charge point id is removed (#34)
- `ocpp-wamp`: a new connection from a charge point now closes the previous one, and errors while opening a connection are caught and logged (#34)
- `ocpp-1-6-soap`: parse the 1.6 `TriggerMessage` SOAP message correctly (#38)
- `ocpp-soap`: a SOAP response without `relatesTo` now logs a warning instead of throwing (#39)
- `ocpp-soap`: the values `0` and `1` are no longer wrongly mapped to booleans (#39)

## [0.1.16] - 2023-03-30

### Added

- `ocpp-wamp`: the hostname verifier of the WebSocket client can be specified (#32)
- `ocpp-transport-soap`: `OcppSoapClientTransport` now sends a `Content-Type` header (#33)
- Requests that carry an `actionTimestamp` now implement a `HasActionTimestamp` interface, and a mixin lets the parser leave that field unparsed (#36)

## [0.1.15] - 2023-03-17

### Fixed

- The JSON and SOAP parsers apply the forced field type conversion only to the actions they were configured for (#31)

## [0.1.14] - 2023-03-17

### Added

- The OCPP 1.5 and 1.6 JSON/SOAP parsers accept options (`forcedFieldTypes`, e.g. `Ocpp16ForcedFieldType`) that force the conversion of a given field to an expected type, for charging stations that send a wrong type such as a non-string `DataTransfer.data`. A field already of the expected type is left untouched (#30)

### Fixed

- Parse the JSON fault response correctly (#30)
- Fix some OCPP 1.6 and 2.0 action names and classes (#30)

## [0.1.13] - 2023-02-28

### Added

- The OCPP 1.5 and 1.6 JSON and SOAP parsers accept a list of null restrictions to ignore (`ignoredNullRestrictions`), and validation errors can be ignored by code (#29)

### Removed

- `ocpp-1-6-soap`: remove the `Security` messages from the SOAP actions, which do not exist in the 1.6 SOAP specification (#29)

## [0.1.12] - 2023-02-20

_Versions `0.1.12.1` and `0.12.1` were also published to Maven Central from an untagged build._

### Fixed

- Fix the regular expression and the JSON schema lookup used by the JSON validation introduced in 0.1.11 (#28)

## [0.1.11] - 2023-02-17

### Added

- The JSON parsers validate messages against the OCPP JSON schemas. Validation can be disabled with a flag, or limited by passing validation error codes to ignore (#27)

### Changed

- **BREAKING:** The JSON and SOAP parsers no longer throw when a message is invalid or cannot be parsed: they return a message whose payload is a fault (with an OCPP error code such as `FormatViolation` or `ProtocolError`). Callers that relied on catching an exception must now check for the fault payload (#27)

## [0.1.10] - 2023-01-19

### Fixed

- `ocpp-soap`: fix the namespace of OCPP 1.5 and 1.6 SOAP messages, the default values of the `From` and `To` headers, and the serialization of `0` values (#24)
- `ocpp-1-6-soap`: `numberOfPhases` has a default value and the charging profile limit is an `Int` (#24)

## [0.1.9] - 2023-01-18

### Fixed

- `ocpp-transport-soap`: more fixes to the SOAP client transport

## [0.1.8] - 2023-01-17

### Fixed

- `ocpp-transport-soap`: more fixes to the SOAP client transport

## [0.1.7] - 2023-01-17

### Fixed

- `ocpp-transport-soap`: more fixes to the SOAP client transport

## [0.1.6] - 2023-01-17

### Fixed

- `ocpp-transport-soap`: fix issues in the SOAP client transport

## [0.1.5] - 2023-01-04

_Preview build `20221110-1` was published before this release._

### Changed

- **BREAKING:** `ocpp-1-5-core`: the `transactionData` of `StopTransactionReq` is now a `List<TransactionData>` (each holding a list of `MeterValue`) instead of a `List<MeterValue>`, to match the OCPP 1.5 structure. Update calls and mappings that build or read `transactionData` (#25)
- The SOAP serializer and the namespace handling were reworked (#23)

### Fixed

- The JSON and SOAP object mappers no longer fail on unknown properties (#26)

## [0.1.4] - 2022-11-07

### Added

- `ocpp-wamp`: the headers of the WebSocket connection are given to the connection and disconnection handlers (#22)

## [0.1.3] - 2022-11-07

_Preview build `20221107-1` was published before this release._

### Added

- Headers are supported in SOAP and JSON-over-WebSocket exchanges (#21)

## [0.1.2] - 2022-11-03

### Added

- `ocpp-json`: new `parseAnyFromJson` parsing method on `OcppJsonParser`, which parses a message whose action is not known in advance (#20)

## [0.1.1] - 2022-10-28

### Added

- Support of SOAP faults, and of OCPP-J `CALLERROR` with an error code and a description (#16)
- `generic-api`: `MeterValuesReq` has a `transactionId` (#19)
- `MeterValues` with no parameters can be deserialized (#17)
- WAMP payloads can span several lines (#16)

### Changed

- **BREAKING:** `ocpp-json`, `ocpp-wamp`: `JsonMessage.CallError` and `WampMessage.CallError` now take an error code (new `JsonMessageErrorCode` enum) and an error description in addition to the message id and the payload. `JsonMessage` and `WampMessage` gain `errorCode` and `errorDescription` fields. Pass the error code and description when building a call error (#16)

### Fixed

- A remote stop transaction request now carries the CSMS transaction id (#18)

## [0.1.0] - 2022-10-11

### Added

- `ocpp-wamp`: the WebSocket client is more resilient: it reconnects automatically after a disconnection, and a send attempt forces an immediate reconnection attempt (#14)

### Changed

- `ocpp-wamp`: the http4k-based WebSocket client implementation was removed as unused (#14)

### Fixed

- Fix the parsing of the SOAP envelope header (#15)

## [0.0.12] - 2022-10-10

### Fixed

- Fix the `MeterValues` mapping for OCPP 1.5 (#13)

## [0.0.11.1] - 2022-10-11

Hotfix tagged after 0.0.12 on a separate branch; it does not contain the 0.0.12 fix.

### Added

- `ocpp-wamp`: the WebSocket client reconnects automatically after a disconnection, and a send attempt forces an immediate reconnection attempt

### Removed

- `ocpp-wamp`: the http4k-based WebSocket client implementation (unused)

### Fixed

- `ocpp-wamp`: fix an ordering problem with the connection latch on connect

## [0.0.11] - 2022-10-07

### Added

- `ocpp-wamp`: connection and disconnection handlers can be passed to the `newServer()` function

### Fixed

- Fix the SOAP client transport target

## [0.0.10] - 2022-10-07

### Fixed

- Fix the SOAP client transport target

## [0.0.9] - 2022-10-07

### Added

- `ocpp-wamp`: connection and disconnection handlers

### Changed

- `ocpp-wamp`: a new WebSocket connection from the same charge point now replaces the previous one instead of being refused

## [0.0.8] - 2022-08-01

### Fixed

- SOAP headers `From` and `To` are now nullable (#10)

## [0.0.7] - 2022-07-26

### Changed

- **BREAKING:** The JSON and SOAP object mappers (`Ocpp15JsonObjectMapper`, `Ocpp16JsonObjectMapper`, `Ocpp20JsonObjectMapper`, `Ocpp15SoapMapper`, `Ocpp16SoapObjectMapper`, `OcppJsonMapper`, `OcppSoapMapper`) are now classes instead of Kotlin `object` singletons. Instantiate them instead of referencing the singleton (#9)

## [0.0.6] - 2022-07-22

### Fixed

- The action of a `JsonMessage` is null when the parsed value is blank (#8)
- Remove the `Response` suffix of SOAP message names (#8)

## [0.0.5] - 2022-07-19

### Added

- CSMS side: `CSMSOperations` for OCPP 1.6 and 2.0.1, and a `CSMSApiCallbacks` interface in the generic API
- OCPP 1.5 support: new `ocpp-1-5-*` modules (api, adapter, core, json, soap), with CSMS and charge point operations, an `ocpp15ConnectionToCSMS` function and its JSON and SOAP parsers
- SOAP transport: `ocpp-transport-soap` with an HTTP server transport and a client transport, factory methods in `ApiFactory`, and the OCPP 1.6 SOAP parser covering the messages a charging station sends
- JSON parsers for OCPP 1.5, 1.6 and 2.0 (like the SOAP ones), with a method to parse only the payload; the WebSocket client and server use them
- Common JSON mappers for OCPP 1.5, 1.6 and 2.0, with a Kotlinx `Instant` module
- Maven Central publication settings

### Changed

- Jackson annotations were removed from the `ocpp-XX-core` models; serialization is configured in the mappers
- `Transport` was renamed `ClientTransport`

## [0.0.4] - 2022-06-28

### Changed

- First version published to Maven Central: the publish workflow was fixed, with no change to the library itself

## [0.0.3] - 2022-06-28

_Not published to Maven Central._

### Changed

- Internal changes only; no consumer-visible change.

## [0.0.2] - 2022-06-28

_Not published to Maven Central._

### Changed

- Internal changes only; no consumer-visible change.

## [0.0.1] - 2022-06-28

_Not published to Maven Central._

Initial release.

### Added

- OCPP 1.6 and 2.0.1 support on the charging station side, in OCPP-J (WebSocket/JSON), with all the data structures of the specification and JSON serialization verified against the specification JSON schemas
- `generic-api`: a version-independent API for sending requests to a CSMS and receiving responses, with operation implementations for BootNotification, Heartbeat, Authorize, StatusNotification, StartTransaction, StopTransaction, MeterValues, DataTransfer and TransactionEvent
- `ocpp-wamp`: a WAMP/OCPP-J WebSocket client and server, with calls in both directions, error handling for unknown ids, and an OkHttp-based client
- `ocpp-transport` and `ocpp-transport-websocket`: transport abstraction and WebSocket client
- `ocpp-1-6-*` and `ocpp-2-0-*` modules (api, adapter, core) with MapStruct mapping between the generic API and each OCPP version

[Unreleased]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-3.0.0...dev
[3.0.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-2.0.4...R-3.0.0
[2.0.4]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-2.0.3...R-2.0.4
[2.0.3]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-2.0.2...R-2.0.3
[2.0.2]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-2.0.1...R-2.0.2
[2.0.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-2.0.0...R-2.0.1
[2.0.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.15...R-2.0.0
[1.3.15]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.14...R-1.3.15
[1.3.14]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.13...R-1.3.14
[1.3.13]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.12...R-1.3.13
[1.3.12]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.11...R-1.3.12
[1.3.11]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.10...R-1.3.11
[1.3.10]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.9...R-1.3.10
[1.3.9]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.8...R-1.3.9
[1.3.8]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.7...R-1.3.8
[1.3.7]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.6...R-1.3.7
[1.3.6]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.5...R-1.3.6
[1.3.5]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.4...R-1.3.5
[1.3.4]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.3...R-1.3.4
[1.3.3]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.2...R-1.3.3
[1.3.2]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.1...R-1.3.2
[1.3.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.3.0...R-1.3.1
[1.3.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.2.3...R-1.3.0
[1.2.3]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.2.2...R-1.2.3
[1.2.2]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.2.1...R-1.2.2
[1.2.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.2.0...R-1.2.1
[1.2.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.1.1...R-1.2.0
[1.1.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.1.0...R-1.1.1
[1.1.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.0.1...R-1.1.0
[1.0.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-1.0.0...R-1.0.1
[1.0.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.20...R-1.0.0
[0.1.20]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.19...R-0.1.20
[0.1.19]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.18...R-0.1.19
[0.1.18]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.17...R-0.1.18
[0.1.17]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.16...R-0.1.17
[0.1.16]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.15...R-0.1.16
[0.1.15]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.14...R-0.1.15
[0.1.14]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.13...R-0.1.14
[0.1.13]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.12...R-0.1.13
[0.1.12]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.11...R-0.1.12
[0.1.11]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.10...R-0.1.11
[0.1.10]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.9...R-0.1.10
[0.1.9]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.8...R-0.1.9
[0.1.8]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.7...R-0.1.8
[0.1.7]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.6...R-0.1.7
[0.1.6]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.5...R-0.1.6
[0.1.5]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.4...R-0.1.5
[0.1.4]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.3...R-0.1.4
[0.1.3]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.2...R-0.1.3
[0.1.2]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.1...R-0.1.2
[0.1.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.1.0...R-0.1.1
[0.1.0]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.12...R-0.1.0
[0.0.12]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.11...R-0.0.12
[0.0.11.1]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.11...R-0.0.11.1
[0.0.11]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.10...R-0.0.11
[0.0.10]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.9...R-0.0.10
[0.0.9]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.8...R-0.0.9
[0.0.8]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.7...R-0.0.8
[0.0.7]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.6...R-0.0.7
[0.0.6]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.5...R-0.0.6
[0.0.5]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.4...R-0.0.5
[0.0.4]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.3...R-0.0.4
[0.0.3]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.2...R-0.0.3
[0.0.2]: https://github.com/IZIVIA/ocpp-toolkit/compare/R-0.0.1...R-0.0.2
[0.0.1]: https://github.com/IZIVIA/ocpp-toolkit/releases/tag/R-0.0.1
