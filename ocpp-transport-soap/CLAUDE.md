# ocpp-transport-soap

## Purpose
Implements the `ClientTransport` / `ServerTransport` abstractions from `ocpp-transport` over plain HTTP (using http4k), turning the OCPP-S SOAP envelope machinery from `ocpp-soap` into a working request/response transport for both the Charging Station side and the CSMS side.

## Layer Guidelines
This module *consumes* the `soap` scattered layer (envelope building/parsing lives in `ocpp-soap` and the version-specific `ocpp-1-5-soap` / `ocpp-1-6-soap` modules). See [../docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) for the SOAP envelope conventions those modules follow. This directory only implements the HTTP transport that carries those envelopes; it does not build or parse XML itself — every marshaling call goes through the injected `OcppSoapParser`.

## Key Patterns

- **No persistent connection.** Unlike OCPP-J/WebSocket, SOAP has no duplex channel, so each request/response is an independent HTTP POST. Correlation between a request and its response is carried inside the SOAP envelope (WS-Addressing `MessageID` / `RelatesTo` headers, generated via `newMessageId()`), not by an in-memory pending-call map.
- **`OcppSoapClientTransport` is dual-role.** It represents a Charging Station: it is an outbound HTTP *client* (`JavaHttpClient`) for CS → CSMS calls (`sendMessageClass`), and it also embeds its own Undertow HTTP *server* on a single `POST /` route to receive CSMS → CS calls (`receiveMessageClass`). This mirrors real OCPP-S charge points, which must expose an HTTP endpoint of their own since the CSMS cannot push over the outbound connection.
- **Action routing differs by direction.**
  - On the client's inbound endpoint, there is no URL routing at all: every request lands on `POST /`, and the target action is recovered by parsing the SOAP body (`ocppSoapParser.readToEnvelop(payload).header.action`, with a leading `/` stripped). Registered handlers are tried in registration order via `handlers.firstNotNullOfOrNull { it(message) }`, matching on `action` case-insensitively.
  - On `OcppSoapServerTransport` (the CSMS side), routing is done at the HTTP layer via http4k's `Path` lens: the URL is `{path}/{action}/{ocppId}`. Both `action` and `ocppId` come from the URL, not from parsing the body first.
- **CSMS-side handler filtering is two-stage.** A registered `OcppHttpServerHandler` is only considered if `accept(ocppId)` (backed by the caller-supplied `accept: (String) -> ChargingStationConfig` returning `.acceptConnection`) is true, then it must match on `ocppVersion` (exact match against the transport's configured `OcppVersion`) and `action` (case-insensitive). This lets one server instance reject unknown/disabled charge point IDs and ignore actions registered for a different OCPP version.
- **Correlation and addressing on response.** When answering a request, both `OcppSoapClientTransport.receiveMessageClass` and `OcppSoapServerTransport.receiveMessageClass` build a `ResponseSoapMessage` with a freshly generated `messageId` (`urn:uuid:...`) and `relatesTo = <inbound message's messageId>`, swapping `from`/`to` relative to the inbound message.
- **Errors are not decoded, only wrapped.** A non-200 HTTP response on the client's `sendMessageClass` path throws `OcppCallErrorException(response.bodyString())` — the raw response body (which may be a SOAP fault) becomes the exception message; there is no structured SOAP-fault-to-`OcppCallErrorException` mapping in this module. Both the client's inbound endpoint and the server return `404 NOT_FOUND` (with a warning log) when no handler matches, rather than a SOAP fault.
- **`ServerTransport.sendMessageClass` is unimplemented** (`TODO`). `OcppSoapServerTransport` can currently only *receive* CS-initiated calls and answer them; it cannot act as an HTTP client to push CSMS-initiated requests to a charging station.

## Conventions

- `HttpMessage` (`ocppId`, `action?`, `payload`, `headers`) is the internal DTO passed between the HTTP layer and the SOAP marshaling calls inside handler closures; it never crosses the public transport API.
- `OcppHttpServerHandler` (`accept(ocppId)`, `onAction(msg)`) is the internal per-registration unit used by `OcppSoapServerTransport`; each call to `receiveMessageClass` appends one anonymous implementation to `handlers`.
- Both transports accept `newMessageId: () -> String` (default `UUID.randomUUID().toString()`), which tests override for deterministic assertions.
- `SoapClientSettings(path, port)` configures the embedded server side of `OcppSoapClientTransport` (its own address/port, used both to bind Undertow and to populate the `from` address sent to the CSMS).
- `SoapClientSettings.kt` also defines the `OcppSoapServerTransport.asServer(port)` extension, the standard way tests and consumers turn a configured `OcppSoapServerTransport` into a running `Http4kServer`.

## Adding New Action Support

1. On the CS/client side, call `OcppSoapClientTransport.sendMessageClass`/`sendMessage` (from `ocpp-transport`) for outbound calls, and register inbound handling via `receiveMessageClass`/`receiveMessage` — no routing table changes are needed here since dispatch is by parsed action name.
2. On the CSMS/server side, call `OcppSoapServerTransport.receiveMessageClass`/`receiveMessage`, supplying the `OcppVersion` this handler is for and an `accept` callback resolving `ChargingStationConfig` for the incoming `ocppId`.
3. Ensure the `OcppSoapParser` implementation passed in (from the relevant version's `ocpp-x-soap` module, e.g. `Ocpp16SoapParser`) knows how to (de)serialize the new action's request/response payload — this module only orchestrates HTTP and delegates all envelope work to that parser.
4. Do not add per-action branching inside `OcppSoapClientTransport`/`OcppSoapServerTransport` themselves; new actions are purely a matter of registering another handler with a matching `action` string.

## Differences vs. `ocpp-transport-websocket`

- WebSocket transport keeps one persistent duplex connection per charging station and correlates request/response via an in-memory pending-call map keyed by message id; this module has no persistent connection and correlates via SOAP/WS-Addressing headers (`MessageID` / `RelatesTo`) inside each independent HTTP exchange.
- Because SOAP has no server-push channel, the CS-side transport here must run its own embedded HTTP server (`OcppSoapClientTransport`'s Undertow instance) purely so the CSMS has somewhere to POST CSMS-initiated calls; the WebSocket client transport needs no such embedded server since the CSMS can write directly to the open socket.
- Server-side routing is path-based (`/{action}/{ocppId}`) here versus frame/message-based dispatch over the single socket in the WebSocket transport.

## See Also
- [../docs/SOAP.guidelines.md](../docs/SOAP.guidelines.md) — SOAP envelope conventions used by `ocpp-soap` and the `ocpp-x-soap` parser modules this transport depends on.
- [../docs/ARCHITECTURE.md](../docs/ARCHITECTURE.md)
- [../docs/DEVELOPMENT.md](../docs/DEVELOPMENT.md)
- `ocpp-transport` — defines the `ClientTransport`/`ServerTransport` contracts implemented here.
- `ocpp-transport-websocket` — sibling OCPP-J implementation of the same contracts.
