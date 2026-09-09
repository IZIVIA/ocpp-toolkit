# Security Model and Scope

This document states what the toolkit does and does not do about security, because the boundary
is unusually explicit here and getting it wrong is a deployment risk rather than a code smell.

## The core stance

The toolkit is a **strict protocol implementation with no business logic**. Authentication,
authorisation and trust decisions are the consumer's responsibility. The library will faithfully
encode and decode whatever it is asked to, including messages a hostile peer sends.

## What is supported

- **HTTP Basic authentication** on the OCPP-J WebSocket handshake. Credentials are passed as
  `RequestHeaders` into the client, and forwarded by `ocpp-wamp` on connect.
- **Connection admission control.** `ServerTransport` exposes an `accept(ocppId)` callback,
  invoked before a charging station's connection is registered. This is the hook for rejecting
  unknown or unauthorised station identifiers — it is the primary authorisation point on the
  server side, and it is the consumer's job to implement it meaningfully. Accepting everything
  is the default only because the library has no identity store.
- **OCPP 1.6 Security Whitepaper operations.** The message models (`certificatesigned`,
  `installcertificate`, `deletecertificate`, `getinstalledcertificateids`, `signcertificate`,
  `securityeventnotification`, `getlog`, `logstatusnotification`, signed-firmware variants) live in
  `ocpp-1-6-core`; the typed operations live in the separate
  [`ocpp-1-6-security`](../ocpp-1-6-security/CLAUDE.md) module, **not** on `core16`'s
  `CSMSOperations`/`ChargePointOperations`. Two consequences matter operationally: client-side they
  are only registered when `Settings.ocpp16SecurityExtensions` is `true`, and CSMS-side only when
  the callback object implements `SecurityChargePointOperations`. The toolkit models and transports
  these messages — it does **not** validate certificates, verify firmware signatures, or manage a
  trust store. In particular, an implementation of `updateFirmware`/`signedUpdateFirmware` must
  verify the signature itself.
- **OCPP 2.0.1 certificate and ISO 15118 data structures** in `ocpp-2-0-core` (`AuthorizeReq`
  carries `certificate` and `iso15118CertificateHashData`).

## What is explicitly out of scope

Per the project README:

- **TLS / HTTPS / WSS termination.** Not implemented. The stated intent is that it be handled by
  a reverse proxy such as Envoy in front of the toolkit's server.
- **Mutual TLS / client certificates.** Same — proxy responsibility.

The README records this as a deliberate design decision "under discussion", not an oversight.
**A deployment that exposes this toolkit's server directly to the internet without a terminating
proxy is transporting OCPP credentials in the clear.** That is the single most important
operational consequence of the module boundary.

## Threat-relevant behaviours worth knowing

These are properties of the implementation that matter when a peer is untrusted:

- **Parsers do not throw on hostile input.** Both wire-format parsers convert malformed input into
  an error *value* — a `CALL_ERROR` on the JSON side, a `SoapFault` payload on the SOAP side. This
  is good for availability, but it means a peer can generate unlimited error responses without
  ever tripping an exception handler. Rate limiting is the consumer's concern.
- **Schema validation is the input-validation layer, and it is optional.** Every JSON parser takes
  `enableValidation: Boolean = true`. Constructing a parser with `false`, or over-broad use of
  `ignoredValidationCodes` / `ignoredNullRestrictions` / `forcedFieldTypes`, disables the only
  systematic check that inbound payloads match the specification. Use those escape hatches to
  accommodate one known-broken peer, not as a global default.
- **`ocpp-soap` uses a regex-based salvage parser** (`parseSoapFaulted`) for XML that Jackson
  cannot parse. Treat its output as untrusted, best-effort data.
- **Adapters hold per-transaction state, unbounded.** `ocpp-1-6-api-adapter`'s
  `RealTransactionRepository` correlates transaction ids in an in-memory `ConcurrentHashMap` whose
  `TransactionRepository` interface has **no removal operation at all** — `saveTransactionIds`
  writes, nothing ever deletes. A peer that opens transactions and never closes them grows that map
  without bound, and its backing `hashMap` field is `public`, so nothing stops a consumer relying on
  the mutable internal state. `getLocalIdByTransactionId` also copies the whole map
  (`toList().find { … }`) on every reverse lookup, so cost grows with the leak. Consider both when
  exposing a long-lived CSMS.
- **Server connection maps replace on reconnect.** `ocpp-wamp`'s server keys connections by OCPP
  id and replaces an existing entry when the same id reconnects. Without a real `accept(ocppId)`
  check, anyone who knows a station id can displace that station's connection.
- **Logging.** The toolkit logs through SLF4J. Message payloads can contain identifiers
  (`idTag`, `idToken`) that are personal data under GDPR. Review log levels before production.

## Supply chain

- Dependencies are centrally pinned in [`versions.properties`](../versions.properties) via
  refreshVersions — with one exception: `ocpp-wamp` pins OkHttp inline with a literal version, so
  it is outside that update flow and will go stale silently.
- GitHub Actions are pinned to commit SHAs rather than mutable tags in both workflows. Keep that.
- Release artifacts are GPG-signed, but **only when `GPG_PRIVATE_KEY` is present in the
  environment** — signing is skipped silently otherwise. See [DEPLOYMENT.md](DEPLOYMENT.md).
- There is no dependency scanning, SAST or secret scanning configured in CI.

## Reporting

No `SECURITY.md` policy file or disclosure contact exists at the repository root. Issues go
through the [GitHub repository](https://github.com/IZIVIA/ocpp-toolkit) — maintainers may want to
add a coordinated-disclosure policy.

## See also

- [ARCHITECTURE.md](ARCHITECTURE.md) — where the transport boundary sits
- [`../ocpp-transport/CLAUDE.md`](../ocpp-transport/CLAUDE.md) — the `accept` admission hook
- [JSON.guidelines.md](JSON.guidelines.md) — validation and its escape hatches
