# ocpp-wamp

## Purpose
Implements the OCPP-J "WAMP-like" message framing (`[msgTypeId, messageId, ...]` arrays over WebSocket) and provides the concrete client (OkHttp) and server (Undertow/http4k) transports used to send/receive OCPP calls, independent of any specific OCPP version's payload schema.

## Message Framing (`messages/WampMessage.kt`)
- `WampMessageType`: `CALL(2)`, `CALL_RESULT(3)`, `CALL_ERROR(4)` — the raw OCPP-J array type ids.
- `WampMessage` is a flat data class covering all three message shapes (`action` only set for CALL, `errorCode`/`errorDescription` only for CALL_ERROR). `payload` is always a raw JSON string (not parsed here — that's the caller's job via `ocpp-json`).
- `toJson()` builds the `[type,"id",...]` wire format by hand (string templates, not a JSON serializer) — payload is spliced in as-is.
- `WampMessageParser.parse(str)` uses hand-built regexes (`Patterns` object) to split the array without a real JSON parser, because payload may itself contain commas/braces. `removeSingleDoubleQuoteInPayload()` is a workaround for a specific malformed-message case (a single stray `"` before the payload object) seen from some charge points — do not remove it without checking `WampParserTest`/`listeWampMsgTest.txt`.
- Parsing failures return `null` and log an error rather than throwing; callers must handle `null`.
- `WampMessageMeta` (ocppVersion, ocppId, headers) is threaded alongside each incoming CALL so handlers know who/what version sent it.

## Correlation & Blocking Calls (`core/WampCallManager.kt`)
- One `WampCallManager` per live connection (client-side: created in `onConnectedTo`; server-side: one per `ChargingStationConnection`).
- **Single-in-flight-call design**: a manager holds at most one `currentCall` at a time (`msgId` = the pending call's id). A second `callBlocking` while one is pending busy-waits (`Thread.sleep(10)`) until the current one clears or the *caller's own* timeout elapses, then throws `IllegalStateException` — it does **not** queue multiple calls. This mirrors OCPP's one-call-at-a-time rule per connection.
- `callBlocking` sends via the injected `send: (String) -> Unit`, then blocks on a `CountDownLatch` until `handleResult` is called with a matching `msgId` or until timeout.
- `handleResult` is invoked from the message-receiving path (websocket `onMessage`) and correlates purely by `message.msgId == pending.msg.msgId`; mismatched or unexpected results are logged and discarded, never throw.
- `await()` / `close()` support graceful shutdown: wait for the in-flight call to finish (bounded by its timeout) before tearing down.
- Timeout is per-call (`specificTimeoutInMs` overrides the manager default), enforced via the latch's `await(timeout, ...)`.

## Client (`client/`)
- `OcppWampClient` interface: `connect(listener)`, `close()`, `sendBlocking(message)`, `onAction(handler)`, `state: ConnectionState` (`CONNECTING/CONNECTED/DISCONNECTING/DISCONNECTED`).
- `OcppWampClient.newClient(...)` is the factory entry point: builds an `OkHttpOcppWampClient` and, unless `autoReconnect = false`, wraps it in `AutoReconnectOcppWampClient`. Prefer this factory over instantiating impls directly.
- **`impl/OkHttpOcppWampClient`**: uses OkHttp's async `WebSocketListener`. `connect()` is a synchronous wrapper (`tryToConnect`) around the async `asyncConnect`, blocking on a `CountDownLatch` bounded by `timeoutInMs`.
  - Incoming websocket frames (`onMessage`) are dispatched onto `CoroutineScope(Dispatchers.Default).launch { ... }` — a **new coroutine per message**, so message handling is concurrent and NOT ordered relative to other inbound messages. CALL_RESULT/CALL_ERROR are routed to `WampCallManager.handleResult`; CALL is routed through registered `WampOnActionHandler`s (first non-null response wins), each guarded by `withTimeout(timeoutInMs)`.
  - `handlers` (registered via `onAction`) is a plain `MutableList`, not thread-safe against concurrent registration — register handlers before connecting.
  - Reconnection to a still-registering websocket is guarded: if a new websocket connects while an old `wampConnection` is still set, the old one is closed with a distinct `CLEANUP_CLOSURE_STATUS` (1001) so its `onClosing` doesn't trigger a spurious `onConnectionLost`.
  - `closeSafely` remaps invalid/reserved WebSocket close codes (e.g. OkHttp's synthetic 1005 "no status") to `NORMAL_CLOSURE_STATUS` (1000) before echoing them back, since OkHttp throws on reserved codes.
- **`autoreconnect/`**: `AutoReconnectOcppWampClient` decorates any `OcppWampClient` with a state machine (`AutoReconnectIdleState` / `Connecting` / `Connected`, see `AutoReconnectState.kt`) plus `AutoReconnectHandler`, which schedules reconnection attempts on a dedicated single-thread `ScheduledExecutorService` with exponential backoff (`baseAutoReconnectDelayInMs`, doubled after each failure) and a `minDelayBetweenAttempts` floor. `connect()` here never throws for connection failures — you must observe `ConnectionListener` for `onConnectionFailure`/`onConnectionLost`/`onConnected`. `close()` is the only way to stop retrying.

## Server (`server/`)
- `OcppWampServer` interface (`config()`, `shutdown()`, `sendBlocking(ocppId, message)`, `register(handler)`, `getChargingStationOcppVersion`) is implemented by `impl/UndertowOcppWampServer`, which wires `impl/OcppWampServerApp` (http4k websocket routing + connection/call bookkeeping) onto an Undertow (`impl/Undertow.kt`) HTTP server exposing the OCPP-J subprotocol.
- `OcppWampServerApp` keeps one `ChargingStationConnection` per `ocppId` in a `ConnectionsMap` (ocppId lowercased as key). A new connection for an already-connected `ocppId` **replaces** the previous one (logged as a reconnect) and force-closes the old socket without firing the normal `onWsCloseHandler`.
- Each `ChargingStationConnection` owns its own `WampCallManager`; incoming CALL_RESULT/CALL_ERROR route to it, incoming CALL is dispatched onto `settings.buildCallsExecutor()` (default: cached thread pool — one thread borrowed per inbound call, not bounded).
- `sendBlocking(ocppId, ...)` looks up the connection with bounded exponential backoff retry (`getChargingStationConnection`, 5 attempts starting at 10ms) before throwing `NoConnectionException`, to tolerate races right after a charge point (re)connects.
- `shutdown()` flips an `AtomicBoolean` (checked both on new connection accept and on new inbound CALL, both then rejected), then for each existing connection awaits any in-flight call (`callManager.await()`) before closing the socket — a best-effort graceful drain, not instantaneous.
- `EventsListeners` (in `server/impl/EventsListeners.kt`) exposes hooks for connect/reconnect/close so callers can track charge point presence without touching connection internals.
- Registered `OcppWampServerHandler`s (`register`) are tried in registration order per action; the first non-null response wins (mirrors client-side handler dispatch).

## Threading Model — Key Gotchas
- Client inbound messages: one coroutine per message on `Dispatchers.Default` — no ordering guarantee across concurrent messages for the same connection.
- Server inbound CALLs: one task per call on a cached thread pool — same lack of ordering, and no backpressure (a burst of calls spawns a burst of threads).
- Both client and server enforce **one pending outgoing call at a time** per connection via `WampCallManager`; sending a second call while one is pending will busy-wait then throw, it never gets queued.
- `WampMessage.parse` returning `null` must always be checked — never assume a non-null result.
- Close codes: reserved/invalid WebSocket close codes must go through `closeSafely`-style remapping when echoing a peer's close code back; a raw reserved code throws in OkHttp.
- `WampCallManager.close()`/`await()` only wait for the *current* call, not for concurrently-launched coroutines/handler tasks — full graceful shutdown at the server relies on `AtomicBoolean shutdown` being checked at each new-connection/new-call entry point, not on cancelling in-flight work.

## Adding / Changing Behavior
1. Framing changes (new message shape, parser edge case): `messages/WampMessage.kt` + regression test in `src/test/kotlin/.../WampParserTest.kt` (uses fixtures like `src/test/resources/listeWampMsgTest.txt`).
2. Call-correlation/timeout changes: `core/WampCallManager.kt` — keep the single-in-flight-call invariant unless you also revisit the OCPP spec's one-call-per-connection assumption downstream.
3. Client transport/reconnection changes: `client/impl/OkHttpOcppWampClient.kt` for socket handling, `client/autoreconnect/` for retry/backoff policy — cover with `WampIntegrationTest.kt` / `WampLoadTest.kt`.
4. Server transport changes: `server/impl/OcppWampServerApp.kt` (connection map, dispatch) or `server/impl/UndertowOcppWampServer.kt` / `Undertow.kt` (HTTP/WS server config) — cover with `OcppWsEndPointTest.kt`.
5. New settings (executor, timeouts): extend `OcppWampServerSettings` (it's `open`, designed for subclassing to swap the calls executor).

## See Also
- Consumed by `ocpp-transport-websocket`, which adapts this module's client/server API to the version-agnostic `ocpp-transport` `ClientTransport`/`ServerTransport` abstractions.
- Depends on `ocpp-json` (api dependency) for the JSON payload contracts carried inside `WampMessage.payload`.
