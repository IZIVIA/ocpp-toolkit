# Migration guide

## From `R-2.0.4` to the next release

This release contains **breaking changes**. The two large ones are the move of the public time
API to `kotlin.time` and the arrival of the OCPP 1.6 Security Whitepaper, which forced a few
model fixes in `ocpp-1-6-core`. OCPP 1.2 support and the OCPP 1.5 API adapter are new features
and require no change to existing code.

Read [Time API](#1-time-api-kotlinx-datetime--kotlintime) and
[Generic API](#3-generic-api-new-diagnosticsstatusnotification-operation) even if you do not use
OCPP 1.6 security: they affect every module.

---

### Checklist

| # | Change | Who is affected |
|---|--------|-----------------|
| 1 | `kotlinx-datetime` → `kotlin.time` | everyone |
| 2 | `KotlinxInstantModule` renamed | anyone registering the Jackson module directly |
| 3 | `CSMSApi.diagnosticsStatusNotification` added | anyone implementing `CSMSApi`; OCPP 1.6 users calling `logStatusNotification` |
| 4 | OCPP 1.6 security model fixes | users of the 1.6 security messages |
| 5 | `Settings`, `ServerSetting`, `WebsocketServer`, `CsApiType` | toolkit users |
| 6 | `OcppVersion.OCPP_1_2` and `ActionOcpp` reordering | anyone with an exhaustive `when` or persisting enum ordinals |
| 7 | New artifacts | anyone depending on individual modules |
| 8 | Behaviour fixes (timestamp parsing, `remoteStartId`) | see below |

---

### 1. Time API: `kotlinx-datetime` → `kotlin.time`

`kotlinx-datetime` has been dropped from every module. All public types that exposed
`kotlinx.datetime.Instant` / `kotlinx.datetime.Clock` now expose `kotlin.time.Instant` /
`kotlin.time.Clock`.

**Requirements**

- Kotlin **2.3 or newer** to compile against the toolkit.
- `kotlin-stdlib` **2.3 or newer** at runtime.

**If you are on `kotlinx-datetime` 0.7 or newer**, `kotlinx.datetime.Instant` is a `typealias` to
`kotlin.time.Instant`, so your call sites keep compiling as they are. Nothing to do.

**If you are on an older `kotlinx-datetime`**, either upgrade it to 0.7+ or change your imports:

```diff
-import kotlinx.datetime.Instant
-import kotlinx.datetime.Clock
+import kotlin.time.Instant
+import kotlin.time.Clock
```

Conversions moved package too:

```diff
-import kotlinx.datetime.toJavaInstant
-import kotlinx.datetime.toKotlinInstant
+import kotlin.time.toJavaInstant
+import kotlin.time.toKotlinInstant
```

Arithmetic based on `DateTimeUnit` has no `kotlin.time` equivalent; use `Duration`:

```diff
-import kotlinx.datetime.DateTimeUnit
-import kotlinx.datetime.plus
-val next = instant.plus(1, DateTimeUnit.MILLISECOND)
+import kotlin.time.Duration.Companion.milliseconds
+val next = instant + 1.milliseconds
```

**Transitive dependency removed.** `kotlinx-datetime` was previously pulled in by every core
module (`coreProject()` declared it). It is gone. If your own code uses `kotlinx.datetime` types
such as `LocalDate`, `TimeZone` or `DateTimePeriod`, declare the dependency explicitly:

```kotlin
implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
```

**One type change beyond the import swap** — `ocpp-1-6-core`, `GetLogReq`:

```diff
-import java.sql.Timestamp
-    val oldestTimestamp: Timestamp?,
-    val latestTimestamp: Timestamp?
+import kotlin.time.Instant
+    val oldestTimestamp: Instant?,
+    val latestTimestamp: Instant?
```

### 2. `KotlinxInstantModule` renamed to `KotlinInstantModule`

The Jackson module in `com.izivia.ocpp.utils` was renamed and is now `open`, so it can be
subclassed.

```diff
-import com.izivia.ocpp.utils.KotlinxInstantModule
-mapper.registerModule(KotlinxInstantModule())
+import com.izivia.ocpp.utils.KotlinInstantModule
+mapper.registerModule(KotlinInstantModule())
```

`OcppJsonMapper` and `OcppSoapMapper` register it for you; you only need this if you build your
own `ObjectMapper`.

### 3. Generic API: new `diagnosticsStatusNotification` operation

`LogStatusNotification` (OCPP 2.0.1 and the 1.6-J Security Whitepaper) and
`DiagnosticsStatusNotification` (OCPP 1.2/1.5/1.6) are two different messages. The generic API
only exposed `logStatusNotification`, so the 1.x adapters routed it onto the diagnostics wire
message and the same generic call meant different things per version.

**`CSMSApi` gains an abstract method.** It has no default bodies, so this is source-breaking for
out-of-tree implementations:

```kotlin
fun diagnosticsStatusNotification(
    meta: RequestMetadata,
    request: DiagnosticsStatusNotificationReq
): OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp>
```

New types in `com.izivia.ocpp.api.model.diagnosticsstatusnotification`:
`DiagnosticsStatusNotificationReq`, `DiagnosticsStatusNotificationResp`, and
`DiagnosticsStatusEnumType` (modelled on OCPP 1.6: `Idle`, `Uploaded`, `UploadFailed`,
`Uploading`).

`CSMSApiCallbacks` also gains `diagnosticsStatusNotification`, but with a default body
(`throw NotImplementedError()`), so implementing it is optional — override it if you use
`DefaultCSMSApi`.

**Routing change per version** — this is the part that silently changes behaviour:

| Generic call | 1.2 | 1.5 | 1.6 | 2.0.1 |
|---|---|---|---|---|
| `diagnosticsStatusNotification` | `DiagnosticsStatusNotification` (transient `Idle`/`Uploading` filtered: warn + `NOT_SEND`) | same as 1.2 | `DiagnosticsStatusNotification`, all four states mapped 1:1 | rejected |
| `logStatusNotification` | rejected | rejected | whitepaper `LogStatusNotification`, **requires the security extensions** | `LogStatusNotification` |

**If you call `logStatusNotification` on OCPP 1.6 today, you must switch to
`diagnosticsStatusNotification`.** Otherwise the call now goes through the whitepaper path and
throws `IllegalStateException` unless you enabled `ocpp16SecurityExtensions` (see below).

```diff
-csmsApi.logStatusNotification(meta, LogStatusNotificationReq(status = ..., requestId = ...))
+csmsApi.diagnosticsStatusNotification(meta, DiagnosticsStatusNotificationReq(status = ...))
```

Also documented on `CSApi.updateFirmware`: in OCPP 1.6 both core `UpdateFirmware` and whitepaper
`SignedUpdateFirmware` are routed to that single method. A signed update carries
`req.firmware.signingCertificate` and `req.firmware.signature`, both `null` otherwise. **The
toolkit performs no cryptographic verification** — your implementation must validate the
certificate against a trust anchor and check the signature before installing anything, and must
decide its own policy for unsigned updates.

### 4. OCPP 1.6 security model fixes (`ocpp-1-6-core`)

The 1.6 security messages existed but did not match the whitepaper. They were corrected against
the official JSON schemas (now shipped in `ocpp-1-6-json` and validated by
`SecurityJsonSchemaTest`). All of these are breaking:

| Before | After |
|---|---|
| `com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTriggerEnumType` | `com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration.ExtendedMessageTriggerEnumType` |
| `ExtendedTriggerMessageReq.requestedMessage: MessageTrigger` | `: ExtendedMessageTriggerEnumType` |
| `ExtendedMessageTriggerEnumType.SignedChargePointCertificate` | `.SignChargePointCertificate` |
| `...model.certificatesigned.enumeration.DeleteCertificateStatusEnumType` | `...model.deletecertificate.enumeration.DeleteCertificateStatusEnumType` |
| `CertificateSignedResp.status: DeleteCertificateStatusEnumType` | `: CertificateSignedStatusEnumType` |
| `GetInstalledCertificateIdsResp.certificateHashDataType` | `.certificateHashData` |
| `GetInstalledCertificateStatusEnumType.Rejected` | `.NotFound` |
| `CertificateHashDataType(hashAlgorithm, issuerNameHash, issuerKeyHash)` | + required `serialNumber: String` |

`MessageTrigger` (used by the core `TriggerMessage` message) is unchanged — only the *extended*
trigger message now has its own enum.

```diff
-import com.izivia.ocpp.core16.model.triggermessage.enumeration.MessageTrigger
-ExtendedTriggerMessageReq(requestedMessage = MessageTrigger.SignedChargePointCertificate)
+import com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration.ExtendedMessageTriggerEnumType
+ExtendedTriggerMessageReq(requestedMessage = ExtendedMessageTriggerEnumType.SignChargePointCertificate)
```

```diff
 CertificateHashDataType(
     hashAlgorithm = HashAlgorithmEnumType.SHA256,
     issuerNameHash = "...",
-    issuerKeyHash = "..."
+    issuerKeyHash = "...",
+    serialNumber = "..."
 )
```

#### Enabling the security extensions

The whitepaper operations are **off by default**, so a plain 1.6 charge point keeps emitting core
actions only. Turn them on per charge point with the new `Settings` flag:

```kotlin
val settings = Settings(
    ocppVersion = OcppVersion.OCPP_1_6,
    transportType = TransportEnum.WEBSOCKET,
    target = csmsUrl,
    ocpp16SecurityExtensions = true,
)
```

When it is off, whitepaper operations (`securityEventNotification`, `signCertificate`,
`logStatusNotification`) throw `IllegalStateException`, and the inbound whitepaper handlers are
not registered at all, so a CSMS driving `CertificateSigned`, `InstallCertificate` or
`SignedUpdateFirmware` gets `NotImplemented`.

On the CSMS side, a callback object implementing `com.izivia.ocpp.security16.SecurityChargePointOperations`
is registered as an independent facet — a single object can implement both a core
`ChargePointOperations` and the security one. Retrieve the CSMS-side operations with:

```kotlin
val securityApi = csms.getSecurityCSApi16()
```

`Ocpp16Adapter` gained a trailing `securityExtensions: Boolean = false` parameter if you build it
directly instead of going through `ApiFactory`.

### 5. Toolkit API changes

**`ServerSetting`** — `wampSettings` was inserted **before** `listeners`. Positional construction
breaks; use named arguments.

```diff
-ServerSetting(port, path, ocppVersions, transportType, newMessageId, myListeners)
+ServerSetting(port, path, ocppVersions, transportType, newMessageId, listeners = myListeners)
```

`OcppWampServerSettings` lets you control the call timeout and the executor used for inbound
calls, which were previously locked to the defaults (unbounded `newCachedThreadPool`, 30 s):

```kotlin
ServerSetting(
    port = 8080,
    path = "ws",
    ocppVersion = setOf(OcppVersion.OCPP_1_6),
    transportType = TransportEnum.WEBSOCKET,
    wampSettings = object : OcppWampServerSettings(timeoutInMs = 10_000) {
        override fun buildCallsExecutor() = Executors.newFixedThreadPool(32)
    },
)
```

**`WebsocketServer`** — same insertion, `settings` before `listeners`:

```diff
-WebsocketServer(ocppVersions, path, newMessageId, myListeners)
+WebsocketServer(ocppVersions, path, newMessageId, listeners = myListeners)
```

Its `server` field is now `internal` instead of `private`, and a `WebsocketServer.asServer(port)`
extension is available.

**`Settings`** gained two parameters, both appended with defaults, so existing calls keep
compiling:

- `ocpp16SecurityExtensions: Boolean = false`
- `newMessageId: () -> String = { UUID.randomUUID().toString() }`

`newMessageId` on `Settings` is now the single mechanism: it reaches both the WebSocket and the
SOAP transports. There is no `newMessageId` parameter on `ApiFactory.getCSMSApi`.

**`CsApiType`** gained `OcppSecurityCsApiType(ocppVersion)`. An exhaustive `when` over the sealed
class needs a new branch.

**`CSMS`** gained `getCSApi12()` and `getSecurityCSApi16()`. No method was removed.

**`ApiFactory`** — errors for unsupported combinations changed shape:

| Case | Before | After |
|---|---|---|
| OCPP 1.5 generic adapter | `NotImplementedError("Ocpp 1.5 api adapted not yet implemented")` | works |
| SOAP parser for 2.0.1 | `TODO()` (`NotImplementedError`) | `IllegalArgumentException("OCPP 2.0 has no SOAP transport")` |
| WebSocket for 1.2 | n/a | `IllegalArgumentException("OCPP 1.2 has no WebSocket transport")` |

### 6. Enum changes

**`OcppVersion`** (`ocpp-transport`) gained `OCPP_1_2` as its **first** constant:

```kotlin
enum class OcppVersion { OCPP_1_2, OCPP_1_5, OCPP_1_6, OCPP_2_0 }
```

Exhaustive `when` expressions over `OcppVersion` need a new branch. Ordinals shifted — if you
persist or transmit `ordinal` rather than `name`, your stored values are now wrong.

**`ActionOcpp`** (`operation-information`) was reordered and grouped by OCPP version. No constant
was removed or renamed, and three were added:

- `EXTENDED_TRIGGER_MESSAGE`
- `SIGNED_UPDATE_FIRMWARE`
- `SIGNED_FIRMWARE_STATUS_NOTIFICATION`

Same warning: ordinals shifted, `name` and the wire `value` are unchanged.

### 7. New and changed artifacts

New modules under `com.izivia`:

- `ocpp-1-2-api`, `ocpp-1-2-core`, `ocpp-1-2-soap`, `ocpp-1-2-api-adapter`
- `ocpp-1-6-security`
- `ocpp-1-5-api-adapter` (existed as an empty module, now implemented)

If you depend on the aggregate `toolkit` module, they come in transitively — nothing to do. If
you depend on individual modules and want the 1.6 security messages, add:

```kotlin
implementation("com.izivia:ocpp-1-6-security:<version>")
```

`ocpp-1-5-core` and `ocpp-1-6-core` now expose `ocpp-transport`, `operation-information` and
`utils` as `api` rather than `implementation`, so those types are visible transitively again.

### 8. Behaviour changes

**Inbound timestamp parsing is more tolerant.** `InstantDeserializer` used to reject anything
`Instant.parse` did not accept. It now also accepts:

- incomplete timezone offsets such as `+02` (accepted, logged at WARN);
- timestamps with **no** timezone at all, interpreted as **UTC** and logged at WARN.

This is deliberate for OCPP interoperability and avoids depending on the CSMS JVM default
timezone. Be aware that a charge point sending local time without a timezone will still produce
shifted transaction timestamps — watch for the warning in your logs. Malformed values now raise
`IllegalArgumentException` with an explicit message instead of the raw parser error.

Outbound serialisation is unchanged: canonical UTC ISO-8601, truncated to milliseconds.

**OCPP 1.6 `remoteStartId` is now positive.** It was generated with `Random.nextInt()`, which can
return negative values; it now uses `Random.nextInt(1, Int.MAX_VALUE)`, consistent with the 1.2
and 1.5 adapters. If you stored or matched on negative ids, they will no longer occur.

---

### New features (no migration needed)

- **OCPP 1.2** over SOAP: `ocpp-1-2-*` modules, `OcppVersion.OCPP_1_2`,
  `ApiFactory.ocpp12ConnectionToCSMS(...)`, `CSMS.getCSApi12()`. OCPP 1.2 has no WebSocket
  binding, so it is SOAP only.
- **OCPP 1.5 generic API adapter**: `ApiFactory.getCSMSApi` with `OcppVersion.OCPP_1_5` now
  returns a working `Ocpp15Adapter` instead of throwing.
- **OCPP 1.6-J Security Whitepaper**: `ocpp-1-6-security`, gated behind
  `Settings.ocpp16SecurityExtensions`.
- **Tunable WAMP server**: `OcppWampServerSettings` on `ServerSetting` / `WebsocketServer`.
