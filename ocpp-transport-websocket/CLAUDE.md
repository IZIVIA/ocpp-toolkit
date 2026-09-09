# ocpp-transport-websocket

## Purpose
Implements the OCPP-J (WebSocket/JSON) transport by adapting `ClientTransport` / `ServerTransport`
(from `ocpp-transport`) onto the WAMP-like RPC layer provided by `ocpp-wamp`, dispatching JSON
(de)serialization to the correct per-OCPP-version parser.

## Key Patterns

### Contract implementation over `ocpp-wamp`
- `WebsocketClient` implements `ClientTransport` for the charging-station side: it wraps a single
  `OcppWampClient` connection (`OcppWampClient.newClient(uri, ocppId, ocppVersion, headers)`).
- `WebsocketServer` implements `ServerTransport` for the CSMS side: it wraps a single
  `OcppWampServer` that multiplexes many charging station connections, routed by `csOcppId`/`ocppId`.
- Both classes translate this module's request/response calls into `WampMessage.Call` /
  `WampMessage.CallResult` / `WampMessage.CallError` and correlate responses via `msgId`
  (mismatched `msgId` on a response is treated as `IllegalStateException`, not silently ignored).

### Per-version JSON parser dispatch
- `getJsonMapper(OcppVersion)` (`Utils.kt`) is the single dispatch point selecting
  `Ocpp15JsonParser`, `Ocpp16JsonParser`, or `Ocpp20JsonParser` for a given `OcppVersion`.
  Its trailing `else -> throw IllegalStateException(...)` is currently unreachable — the enum has
  exactly those three entries — and its message names the wrong version. Adding a SOAP-only
  version to the enum is what would make that branch live, so fix the message at the same time.
- **Two distinct `OcppVersion` enums exist** with identical entries: `com.izivia.ocpp.OcppVersion`
  (declared in `ocpp-wamp`, the one this module's `Utils.kt` imports) and
  `com.izivia.ocpp.transport.OcppVersion` (declared in `ocpp-transport`). A new protocol version
  has to be added to both, or dispatch silently diverges between the WAMP and transport layers.
- The client resolves its parser once, at construction time, from the `ocppVersion` passed to its
  constructor (one client = one connection = one version).
- The server has no single fixed version: `sendMessageClass` looks up the charging station's
  negotiated version via `server.getChargingStationOcppVersion(csOcppId)` before picking a parser,
  and `receiveMessageClass` is registered per `ocppVersion` (matched against the incoming
  `WampMessageMeta.ocppVersion` before a handler fires), since the same `action` name can carry a
  different payload shape per OCPP version.
- `OcppCallErrorPayload.toJson(parser)` (extension in `Utils.kt`) reuses the resolved parser to
  serialize protocol-level error payloads back onto the wire.

### Subprotocol negotiation and auth
- The WebSocket subprotocol string (e.g. `ocpp1.6`) is not decided in this module — it comes from
  `OcppVersion.subprotocol` (defined in `ocpp-transport`) and is applied inside `ocpp-wamp`'s
  client/server when the connection/route is set up.
- HTTP Basic Auth is the only supported authentication mechanism: `WebsocketClient` accepts
  `headers: RequestHeaders` (a list of header pairs) and forwards them unmodified to
  `OcppWampClient.newClient(...)`. There is no TLS handling in this module or in `ocpp-wamp`
  (transport-level TLS is expected to be terminated outside the toolkit, e.g. by a reverse proxy).

### Server routing by charging station id
- `WebsocketServer` does not start/own an HTTP server itself; it builds an `OcppWampServer` and
  exposes its route configuration as `serverConfig` (`server.config()`) for the caller to embed
  into their own http4k server instance.
- Incoming connections are accepted/rejected per charging station id via the `accept` callback
  passed to `receiveMessageClass`: it is wrapped into an `OcppWampServerHandler.accept(ocppId)`
  that delegates to the caller-supplied `accept(CSOcppId): ChargingStationConfig` and returns
  `acceptConnection`.
- `canSendToChargingStation` is a direct passthrough to `chargingStationConfig.acceptConnection` —
  this module does not itself track live socket state; that liveness/reachability is owned by
  `ocpp-wamp`'s server-side connection registry, keyed by charging station id.

## Conventions
- Both `sendMessageClass` implementations wrap the whole call in a `try { ... } catch (e: Exception)
  { throw e }` — kept as an explicit rethrow point (candidates for future error mapping, see the
  `IDEV-497` TODO in `receiveMessageClass` on both client and server for structured exception
  mapping into `OcppCallErrorPayload`).
- `newMessageId: () -> String` is an injectable id generator (defaults to `UUID.randomUUID()`),
  overridden in tests to assert exact `msgId` correlation behavior.
- Everything in `Utils.kt` is `internal` — parser selection is an implementation detail, not part
  of this module's public API surface.

## Adding Support for a New OCPP Version (OCPP-J)
1. Add the version's case to `getJsonMapper` in `Utils.kt`, mapping to its `Ocpp<N>JsonParser`
   from the corresponding `ocpp-<version>-json` module.
2. Add a `project(":ocpp-<version>-json")` (and `-core` if needed) dependency in
   `build.gradle.kts`.
3. No other change is required in `WebsocketClient`/`WebsocketServer` — both already dispatch
   purely off `OcppVersion` and are otherwise version-agnostic.

## Testing
`WebsocketTest.kt` covers both layers:
- **Client, mocked transport** (`sendMessageClass success`, `wrong msgId`): mocks
  `OkHttpOcppWampClient` via MockK and stubs `OcppWampClient.Companion.newClient(...)`, so no real
  socket is opened. Verifies successful response deserialization and that a `msgId` mismatch
  between request and response raises `IllegalStateException`.
- **End-to-end over a real socket** (`receiveMessageClass success`): starts a real
  `OcppWampServer` bound to a free local port (`asServer(port).start()`), connects a real
  `WebsocketClient` to `ws://localhost:<port>/ws`, registers `receiveMessageClass` handlers for
  `heartbeat` and `authorize`, then drives server-initiated `WampMessage.Call`s through
  `transport.sendBlocking(...)`. Because the client connects asynchronously, the test polls
  (`awaitConnected`, retrying on `IllegalStateException`) instead of using a fixed sleep, to avoid
  flakiness in CI.

## See Also
- [../ocpp-transport/CLAUDE.md](../ocpp-transport/CLAUDE.md) — the `ClientTransport` /
  `ServerTransport` contracts implemented here, and the "Adding a New Transport Implementation"
  checklist this module already follows.
- [../README.md](../README.md) — overall protocol/version support matrix, including the note that
  HTTP Basic Auth is supported but TLS/security requirements beyond it are out of scope.
