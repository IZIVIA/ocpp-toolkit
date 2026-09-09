# ocpp-transport

## Purpose
Defines the transport-agnostic contract (`ClientTransport`, `ServerTransport`) that OCPP-J and OCPP-S implementations must satisfy, plus the shared value types (`OcppVersion`, `RequestHeader`, `OcppCallError*`) used across all transport and core modules.

This module contains **no implementation** — only interfaces, enums, typealiases, and small data/exception types. It depends solely on `:operation-information` (for `ChargingStationConfig` and `RequestMetadata`).

## Key Patterns

### Class-token + reified-wrapper pattern
Every send/receive method has two forms:
- A "Class" variant taking an explicit `KClass<T>` — this is the actual interface method implementers must override (interfaces can't have `reified` type parameters).
- A top-level `inline fun ... reified` extension (e.g. `ClientTransport.sendMessage`, `ServerTransport.sendMessage`) that callers use ergonomically without passing `T::class` manually.

When implementing a new transport, implement the `*Class` methods; callers (core modules) use the reified extensions.

### Charging-station-centric addressing
`ServerTransport` is inherently multiplexed: every send/receive call is keyed by `csOcppId` (charging station identifier) since a CSMS-side server handles many concurrent charging station connections. `ClientTransport` has no such identifier — a client instance represents a single connection to one CSMS.

### Connection acceptance via callback
`ServerTransport.receiveMessageClass` takes an `accept: (String) -> ChargingStationConfig` callback, invoked with the incoming charging station id to decide (via the returned `ChargingStationConfig.acceptConnection`) whether the connection/message should be accepted. This lets the caller plug in station registration/whitelisting logic without the transport knowing about it.

## The Contracts

### `ClientTransport` (charging-station side)
```kotlin
interface ClientTransport {
    fun connect()                          // may throw ConnectException
    fun close()
    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P
    fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P)
}
```
- `connect()` / `close()` manage the underlying connection lifecycle (e.g. WebSocket open/close, SOAP client setup).
- `sendMessageClass` performs a request/response call for `action` (an OCPP operation name, e.g. `"BootNotification"`) and blocks/returns the deserialized response of type `P`.
- `receiveMessageClass` registers a handler `fn` invoked when the CSMS sends `action` to this client; `fn` returns the response payload `P` to be sent back.

### `ServerTransport` (CSMS side)
```kotlin
interface ServerTransport {
    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P
    fun <T : Any, P> receiveMessageClass(
        clazz: KClass<T>, action: String, ocppVersion: OcppVersion,
        onAction: (RequestMetadata, T) -> P,
        accept: (String) -> ChargingStationConfig
    )
    fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean
}
```
- `sendMessageClass` sends `action` to the specific charging station identified by `csOcppId` and returns the response `P`.
- `receiveMessageClass` registers a handler for incoming `action` requests, scoped to a given `ocppVersion` (since the same action can have different payload shapes per OCPP version). `onAction` receives `RequestMetadata` (charging station id + optional message id) alongside the deserialized payload `T`.
- `canSendToChargingStation` lets callers pre-check reachability (e.g. SOAP requires a known `soapUrl`; WebSocket requires an open socket) before attempting a send.

## Supporting Types
- `OcppVersion` — enum of supported protocol versions (`OCPP_1_2`, `OCPP_1_5`, `OCPP_1_6`, `OCPP_2_0`), each carrying its wire `subprotocol` string (e.g. `"ocpp1.6"`) used for WebSocket subprotocol negotiation.
- `RequestHeader` / `RequestHeaders` — typealiases (`Pair<String, String?>` / `List<RequestHeader>`) for transport-level headers (e.g. SOAP/HTTP headers), kept generic so any transport can carry them without a transport-specific type.
- `OcppCallErrorException` / `OcppCallErrorPayload` — represent an OCPP CALLERROR: the exception wraps a raw `payload` string; `OcppCallErrorPayload` is the structured shape (`exception: String?`) transports can serialize/deserialize when propagating protocol-level errors back across the wire.

## Adding a New Transport Implementation
1. Create a sibling module (pattern: `ocpp-transport-<protocol>`, e.g. `ocpp-transport-websocket`, `ocpp-transport-soap`) depending on `:ocpp-transport` (plus `:operation-information` and any underlying protocol library).
2. Implement `ClientTransport` and/or `ServerTransport`, providing the `*Class` methods (not the reified helpers, which are free extensions).
3. Map the underlying protocol's connection semantics to `connect()`/`close()` (client) and to `canSendToChargingStation` (server) — e.g. socket open state or a configured URL.
4. On the server side, use the `accept` callback to resolve/validate a charging station before dispatching to `onAction`, and populate `RequestMetadata` with the resolved charging station id and any correlation/message id from the wire protocol.
5. On error, translate protocol-level faults into `OcppCallErrorException`/`OcppCallErrorPayload` so callers get a consistent error shape regardless of transport.
6. Select payload encoding per `OcppVersion.subprotocol` where relevant (e.g. WebSocket subprotocol negotiation, SOAP action namespace per version).

## See Also
- `../operation-information` — defines `ChargingStationConfig` and `RequestMetadata` consumed here.
- `../ocpp-transport-websocket` — OCPP-J implementation of these contracts over WebSocket (via `ocpp-wamp`).
- `../ocpp-transport-soap` — OCPP-S implementation of these contracts over HTTP/SOAP (via `ocpp-soap`).
