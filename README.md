# OCPP Toolkit

[![OCPP CI](https://github.com/IZIVIA/ocpp-toolkit/actions/workflows/ci.yml/badge.svg)](https://github.com/IZIVIA/ocpp-toolkit/actions/workflows/ci.yml)

This project aim is to provide a Kotlin library to perform OCPP operations.
For more information about OCPP (Open Charge Point Protocol), see https://www.openchargealliance.org/)

## Goal 

The aim is to support:
- both the CSMS and the Charging Station sides
- versions 1.2, 1.5, 1.6 and 2.0.1 of OCPP
- WS/JSON (OCPP-J ; 1.5 and later) and SOAP (OCPP-S ; 1.x versions only) flavor for the transport

It can be used:
- to simulate a charging station, eg to test a CSMS
- to simulate a CSMS, eg to test a Charging Station
- to implement a CSMS
- to implement a ChargingStation controller (if the use of Kotlin fit your requirements)

The aim is to be a strict implementation of OCPP protocol, with no business logic: you use it as a library, and you own the business logic.

We also attempt to provide a generic API, trying to make switching between ocpp versions transparent. The design between versions of OCPP being sometimes very different, the generic API may not cover all aspects with high fidelity.

## Requirements

Published APIs use `kotlin.time.Instant` and `kotlin.time.Clock`. Consumers need Kotlin 2.3 or newer to compile code that references these types, and `kotlin-stdlib` 2.3 or newer at runtime.

Consumers using `kotlinx-datetime` 0.7 or newer already get `kotlinx.datetime.Instant` as a typealias to `kotlin.time.Instant`. Consumers on older `kotlinx-datetime` versions must migrate their call sites to `kotlin.time`.

## Timestamp Handling

Outbound timestamps are serialized in a canonical UTC ISO-8601 form and truncated to milliseconds.

Inbound timestamp parsing is intentionally more tolerant for OCPP interoperability. The toolkit accepts canonical `Z` timestamps, full timezone offsets such as `+02:00`, incomplete offsets such as `+02`, and timestamps without timezone information. When a timestamp omits the timezone, it is interpreted as UTC and a warning is logged. This avoids depending on the CSMS JVM default timezone, but a charge point sending local time without a timezone may still produce shifted transaction timestamps.

## Status

The ChargingStation side of versions 1.6 and 2.0.1 is fully supported in OCPP-J flavor. This includes all the data structures described by the specification, with json serialisation verified against the json schemas provided in the specification.

The CSMS side is implemented: `ApiFactory.csmsOcppServer(...)` runs an OCPP server that can host several versions and transports at once, and hand back a per-version API for CSMS-initiated calls. See the example below.

The CSMS side is available through the `CSMS` entry point, and the SOAP (OCPP-S) flavor is implemented for the 1.x versions. OCPP 1.2 and 1.5 are supported over SOAP, 1.5 also over OCPP-J; OCPP 1.2 has no WebSocket binding, so it is SOAP only. All four versions are reachable through the generic API.

OCPP 1.5 is supported through its own typed API. It is **not** reachable through the generic API — the `ocpp-1-5-api-adapter` module exists but has no implementation yet, so `ApiFactory.getCSMSApi(...)` throws `NotImplementedError` for OCPP 1.5.

The OCPP 1.6 Security Whitepaper operations (`getLog`, certificate management, signed firmware, `securityEventNotification`, ...) live in the separate `ocpp-1-6-security` module. On the charge point side they are only active when `Settings.ocpp16SecurityExtensions` is set to `true`.

Support for security requirements like SSL and mutual certificates is under discussion, as it can be achieved using a proxy like Envoy. HTTP basic auth is supported.

## Usage of the API

Every entry point is a function on `ApiFactory`'s companion object:

```kotlin
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp20ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.csmsOcppServer
```

| Function | Use it to |
|---|---|
| `ocpp15ConnectionToCSMS` / `ocpp16ConnectionToCSMS` / `ocpp20ConnectionToCSMS` | act as a charge point against a CSMS, using that version's typed API |
| `getCSMSApi` | act as a charge point using the version-agnostic `generic-api` (1.6 and 2.0.1 only) |
| `csmsOcppServer` | run a CSMS server |

The examples below mirror the runnable tests in `toolkit/src/test` (`ExampleTest.kt`,
`ExampleCSApiTest.kt`), which are the reference for real usage. They are skipped by default and
need a reachable CSMS:

```bash
./gradlew :toolkit:test -Dhas.local.steve=true
```

`clientPath` and `clientPort` have no defaults: pass `null` for both when the transport is
WEBSOCKET. They are required only for SOAP, where the client is also an embedded server that has
to receive callbacks.

### OCPP 1.6 Example

With the API, you can perform instructions one after the other. In those examples, we're doing full transactions.

OCPP 1.6 Charge :

```kotlin
//establish a connection to the CSMS
val connection = ocpp16ConnectionToCSMS(
        chargePointId = chargePointId,
        csmsUrl = csmsUrl,
        transportType = TransportEnum.WEBSOCKET,
        clientPath = null,
        clientPort = null,
        ocppCSCallbacks = object : OcppCSCallbacks16 {}
)
connection.connect()

//the idTag variable is used to make unique transaction, so, we can use it to stare at who started a transaction for example
val idTag = "321"

//send an authorize request to the CSMS. We are retreiving the response from the CSMS throught the response variable.
val response: AuthorizeResp16 = connection.authorize(RequestMetadata(chargePointId), AuthorizeReq16(idTag = idTag)).response

//We're checking if the Authorization request has been accepted by the CSMS.
if (response.idTagInfo.status == AuthorizationStatus16.Accepted) {

    println("Authorization Accepted")

    //As the Authorization has been accepted, we can start a transaction, but before,
    //we need to change the Status to Preparing
    connection.statusNotification(
        meta = RequestMetadata(chargePointId),
        request = StatusNotificationReq16(
            connectorId = 1,
            errorCode = ChargePointErrorCode16.NoError,
            status = ChargePointStatus16.Preparing
        )
    )

    //We can now start a transaction by sending a StartTransaction request to the CSMS.
    //We can identify this transaction thanks to the idTag parameter
    //This function returns a response generated by the CSMS
    val startResponse: StartTransactionResp16 =
        connection.startTransaction(
            meta = RequestMetadata(chargePointId),
            request = StartTransactionReq16(
                connectorId = 1,
                idTag = idTag,
                meterStart = 0,
                timestamp = now()
            )
        ).response

    //we are retrieving the transactionId from the reponse of our previous transaction request.
    val transactionId = startResponse.transactionId

    println("TransactionId : $transactionId")

    // As the authorization has been accepted and that we have sent and receive a response for our startTransaction,
    // we can now set the charge status to Charging.
    // In order to do this, we send a statusNotification to the CSMS notifying that the status has changed to Charging
    connection.statusNotification(
        meta = RequestMetadata(chargePointId),
        request = StatusNotificationReq16(
            connectorId = 1,
            errorCode = ChargePointErrorCode16.NoError,
            status = ChargePointStatus16.Charging
        )
    )
}

connection.close()
```

OCPP 1.6 Remote Charge :
```kotlin
var remoteStartTransactionReq: RemoteStartTransactionReq16? = null

//As previously said, the idTag variable is used to make unique transaction, so, we can use it to stare at who started a transaction for example
val idTag = "Tag2"

//define the callback for the remoteTransactionRequest
//It returns a RemoteTransactionResponse
//OcppCSCallbacks is an interface whose methods all default to throwing NotImplementedError:
//override only the operations your charge point actually answers.
val ocppCSCallbacks = object : OcppCSCallbacks16 {
    override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 {
        remoteStartTransactionReq = req
        return RemoteStartTransactionResp16(status = RemoteStartStopStatus16.Accepted)
    }
}

//establish a connection to the CSMS
val connection = ocpp16ConnectionToCSMS(
        chargePointId = chargePointId,
        csmsUrl = csmsUrl,
        transportType = TransportEnum.WEBSOCKET,
        clientPath = null,
        clientPort = null,
        ocppCSCallbacks = ocppCSCallbacks
)
connection.connect()

//defining the timeout delay for receiving a remoteTransactionRequest
val waitUntil = now() + 1.toDuration(DurationUnit.MINUTES)

//We are waiting for the remote start request from the CSMS
while (remoteStartTransactionReq == null && now() < waitUntil) {
    sleep(1000)
}

//We are checking if there was a remoteTransactionRequest sent
if (remoteStartTransactionReq != null) {
    println("${remoteStartTransactionReq?.idTag}")

    //As the Authorization has been accepted, we can start a transaction, but before,
    //we need to change the Status to Preparing
    connection.statusNotification(
        meta = RequestMetadata(chargePointId),
        request = StatusNotificationReq16(
            connectorId = 1,
            errorCode = ChargePointErrorCode16.NoError,
            status = ChargePointStatus16.Preparing
        )
    )

    //We can now start a transaction by sending a StartTransaction request to the CSMS.
    //We can identify this transaction thanks to the idTag parameter
    //This function returns a response generated by the CSMS
    val response: StartTransactionResp16 =
        connection.startTransaction(
            meta = RequestMetadata(chargePointId),
            request = StartTransactionReq16(
                connectorId = 1,
                idTag = idTag,
                meterStart = 0,
                timestamp = now()
            )
        ).response

    //we are retrieving the transactionId from the reponse of our previous transaction request.
    val transactionId = response.transactionId

    // As the authorization has been accepted and that we have sent and receive a response for our startTransaction,
    // we can now set the charge status to Charging.
    // In order to do this, we send a statusNotification to the CSMS notifying that the status has changed to Charging
    connection.statusNotification(
        meta = RequestMetadata(chargePointId),
        request = StatusNotificationReq16(
            connectorId = 1,
            errorCode = ChargePointErrorCode16.NoError,
            status = ChargePointStatus16.Charging
        )
    )
}
connection.close()
```

### OCPP 2.0 Example

OCPP 2.0 Charge :
```kotlin
val connection = ocpp20ConnectionToCSMS(
        chargePointId = chargePointId,
        csmsUrl = csmsUrl,
        transportType = TransportEnum.WEBSOCKET,
        clientPath = null,
        clientPort = null,
        ocppCSCallbacks = object : OcppCSCallbacks {}
)
connection.connect()

val response: AuthorizeResp = connection.authorize(RequestMetadata(chargePointId), AuthorizeReq(IdTokenType(
        idToken = "2233223",
        type = IdTokenEnumType.Central,
))).response

if (response.idTokenInfo.status == AuthorizationStatusEnumType.Accepted) {
    connection.statusNotification(
            meta = RequestMetadata(chargePointId),
            request = StatusNotificationReq(
                    connectorId = 1,
                    connectorStatus = ConnectorStatusEnumType.Occupied,
                    evseId = 1,
                    timestamp = now()
            )
    )

    val transactionResponse: TransactionEventResp =
            connection.transactionEvent(
                    meta = RequestMetadata(chargePointId),
                    request = TransactionEventReq(
                            eventType = TransactionEventEnumType.Started,
                            timestamp = now(),
                            triggerReason = TriggerReasonEnumType.Authorized,
                            seqNo = 1,
                            transactionInfo = TransactionType(
                                    "1",
                                    ChargingStateEnumType.Charging
                            )
                    )
            ).response

    connection.statusNotification(
        meta = RequestMetadata(chargePointId),
        request = StatusNotificationReq(
                connectorId = 1,
                connectorStatus = ConnectorStatusEnumType.Occupied,
                evseId = 1,
                timestamp = now()
        )
    )
}
connection.close()
```

OCPP 2.0 Remote Charge :
```kotlin
var remoteStartTransactionReq: RequestStartTransactionReq? = null
val ocppCSCallbacks = object : OcppCSCallbacks {
    override fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp {
        remoteStartTransactionReq = req
        return RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
    }
}

val connection = ocpp20ConnectionToCSMS(
        chargePointId = chargePointId,
        csmsUrl = csmsUrl,
        transportType = TransportEnum.WEBSOCKET,
        clientPath = null,
        clientPort = null,
        ocppCSCallbacks = ocppCSCallbacks
)
connection.connect()

val waitUntil = now() + 1.toDuration(DurationUnit.MINUTES)

while (remoteStartTransactionReq == null && now() < waitUntil) {
    sleep(100)
}

if (remoteStartTransactionReq != null) {
    println("${remoteStartTransactionReq?.idToken?.idToken}")
    connection.statusNotification(
            meta = RequestMetadata(chargePointId),
            request = StatusNotificationReq(
                    connectorId = 1,
                    connectorStatus = ConnectorStatusEnumType.Occupied,
                    evseId = 1,
                    timestamp = now()
            )
    )

    val response: TransactionEventResp =
            connection.transactionEvent(
                    meta = RequestMetadata(chargePointId),
                    request = TransactionEventReq(
                            eventType = TransactionEventEnumType.Started,
                            timestamp = now(),
                            triggerReason = TriggerReasonEnumType.Authorized,
                            seqNo = 1,
                            transactionInfo = TransactionType(
                                    "1",
                                    ChargingStateEnumType.Charging
                            )
                    )
            ).response

    connection.statusNotification(
            meta = RequestMetadata(chargePointId),
            request = StatusNotificationReq(
                    connectorId = 1,
                    connectorStatus = ConnectorStatusEnumType.Occupied,
                    evseId = 1,
                    timestamp = now()
            )
    )
}
connection.close()
```

### CSMS Example

One server can host several OCPP versions on the same port and path. You supply one callback
object per version — an implementation of that version's `ChargePointOperations`, which is what
answers the requests charge points send you — and `fn`, which decides per charge point whether
the connection is accepted.

```kotlin
//one callback object per version: these answer the requests charge points send to the CSMS.
//The type is that version's core ChargePointOperations, e.g.
//  import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
//  import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
//and the version set below uses com.izivia.ocpp.transport.OcppVersion.
val csmsApi16 = object : ChargePointOperations16 { /* heartbeat, authorize, bootNotification, ... */ }
val csmsApi20 = object : ChargePointOperations20 { /* heartbeat, authorize, transactionEvent, ... */ }

val server: CSMS = csmsOcppServer(
    CSMSSettings(
        servers = listOf(
            ServerSetting(
                port = 8080,
                path = "ws",
                ocppVersion = setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0),
                transportType = TransportEnum.WEBSOCKET
            )
        )
    ),
    csmsApiCallbacks = listOf(csmsApi16, csmsApi20),
    //called on each incoming connection, keyed by ocppId: this is the authorization hook.
    //Returning acceptConnection = false rejects the charge point.
    fn = { ocppId -> ChargingStationConfig(acceptConnection = true, soapUrl = null) }
)

server.start()

//once a charge point is connected, get that version's API to send CSMS-initiated requests
val api16 = server.getCSApi16()
val response: RemoteStartTransactionResp16 = api16.remoteStartTransaction(
    RequestMetadata("my-chargepoint1"),
    RemoteStartTransactionReq16(idTag = "Tag2")
).response

server.stop()
```

To serve the OCPP 1.6 Security Whitepaper operations too, have the callback object also implement
`SecurityChargePointOperations` from `ocpp-1-6-security`. The server registers that as a separate
facet, so a single object can carry both.

## Code Organisation

The main entry point is in the module `toolkit`, which provides access to all the apis and all ocpp versions.

For each version of ocpp supported, you will find:
- a `-core` module which provides all the data structures and operations declaration
- an `-api` module which provides the opt-in callback interface a charge point implements
- an `-api-adapter` module which is used as an adapter between that version of ocpp and the generic api
- a `-json` and/or `-soap` module for that version's wire format

Not every combination exists: there is no `ocpp-2-0-soap` (2.0.1 has no SOAP binding), and `ocpp-1-5-api-adapter` is declared but not implemented yet.

Plus, you will find:
- `generic-api`, which is an ocpp independent api which can be used to switch between ocpp versions without changing your code
- `ocpp-1-6-security`, the OCPP 1.6 Security Whitepaper operations, published separately so it stays opt-in
- `ocpp-transport`, which defines an interface for different transport of operations, with the modules `-websocket` and `-soap` for implementations
- `ocpp-json` and `ocpp-soap`, the parse/serialise/validate machinery shared by the per-version wire-format modules
- `operation-information`, used to described operations in whatever version of apis
- `utils`, used to ease some common needs between apis
- `ocpp-wamp`, a client & server implementation of the WAMP-like RPC-over-websocket system defined in the OCPP-J protcols

## Documentation

- [CLAUDE.md](CLAUDE.md) — project overview and module index. Every module has its own `CLAUDE.md`.
- [docs/DEVELOPMENT.md](docs/DEVELOPMENT.md) — prerequisites, build and test commands, gotchas
- [docs/ARCHITECTURE.md](docs/ARCHITECTURE.md) — module graph, request flows, known gaps
- [docs/CONVENTIONS.md](docs/CONVENTIONS.md) · [docs/TESTING.md](docs/TESTING.md) · [docs/TECH_STACK.md](docs/TECH_STACK.md) · [docs/SECURITY.md](docs/SECURITY.md) · [docs/DEPLOYMENT.md](docs/DEPLOYMENT.md)
