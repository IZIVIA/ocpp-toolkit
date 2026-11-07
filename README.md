# OCPP Toolkit

[![OCPP CI](https://github.com/IZIVIA/ocpp-toolkit/actions/workflows/ci.yml/badge.svg)](https://github.com/IZIVIA/ocpp-toolkit/actions/workflows/ci.yml)

This project aim is to provide a Kotlin library to perform OCPP operations.
For more information about OCPP (Open Charge Point Protocol), see https://www.openchargealliance.org/)

## Goal 

The aim is to support:
- both the CSMS and the Charging Station sides
- versions 1.2, 1.5, 1.6 and 2.0.1 of OCPP
- WS/JSON (OCPP-J ; all versions) and SOAP (OCPP-S ; 1.x versions only) flavor for the transport

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

Currently the ChargingStation side of versions 1.6 and 2.0.1 are fully supported in OCPP-J flavor - except the security requirements besides support for http basic auth. This includes all the data structures described by the specification, with json serialisation verified against the json schemas provided in the specification.

The CSMS side is available through the `CSMS` entry point, and the SOAP (OCPP-S) flavor is implemented for the 1.x versions. OCPP 1.2 and 1.5 are supported over SOAP, and both also over OCPP-J: the JSON/WebSocket transport is defined independently of message content, and `ocpp1.2` is a registered subprotocol. All four versions are reachable through the generic API.

Support for security requirements like SSL and mutual certificates is under discussion, as it can be achieved using a proxy like Envoy.

### SOAP charge point settings

Over SOAP, the charge point embeds an HTTP server on which the CSMS sends its own requests. Its settings are given through `SoapClientSettings`, on `Settings.soapClient` or on the `ocppXXConnectionToCSMS` factories:

```kotlin
val connection = ApiFactory.ocpp16ConnectionToCSMS(
        chargePointId = chargePointId,
        csmsUrl = "http://csms.example.com/ocpp",
        transportType = TransportEnum.SOAP,
        soapClient = SoapClientSettings(host = "192.168.0.3", port = 8081, path = "/cp"),
        ocppCSCallbacks = OcppCSCallbacks16()
)
```

The embedded server listens on `path` (`/cp` here), and the resulting URL `http://192.168.0.3:8081/cp` is advertised in the WS-Addressing `From` header of every request, as required by OCPP-S: this is how the CSMS addresses the charge point. `host` is mandatory, as a default would let a charge point advertise an address the CSMS cannot reach. `port = 0` binds an ephemeral port, then advertised once connected; the resolved URL is available through `OcppSoapClientTransport.callbackUrl()` when the transport is built directly, as `ApiFactory` does not expose it. Behind a TLS terminator, set `scheme = "https"` and `advertisedPort` to the port the terminator listens on; behind a reverse proxy rewriting the URL, set `advertisedUrl` (e.g. `https://cp.example.com/ocpp/cp`) to advertise the public URL instead of the bound one. Invalid settings (host with a scheme, a port or a delimiter, relative `advertisedUrl`…) are rejected when `SoapClientSettings` is built.

## Usage of the API

With the API, you can establish a connection to a CSMS with OCPP 1.6 or OCPP 2.0.
Then, you can send requests and get responses from the CSMS. Doing so, you can simulate
a whole transaction process.

### OCPP 1.6 Example

With the API, you can perform instructions one after the other. In those examples, we're doing full transactions.

OCPP 1.6 Charge : 

```kotlin 
//establish a connection to the CSMS
val connection = Ocpp16ConnectionToCSMS(
        chargePointId = chargPointId,
        csmsUrl = csmsUrl,
        transportType = transport,
        ocppCSCallbacks = OcppCSCallbacks16()
)
connection.connect()

//the idTag variable is used to make unique transaction, so, we can use it to stare at who started a transaction for example
val idTag = "321"

//send an authorize request to the CSMS. We are retreiving the response from the CSMS throught the response variable.
val response: AuthorizeResp16 = connection.authorize(RequestMetadata(chargPointId),AuthorizeReq16(idTag = idTag )).response

//We're checking if the Authorization request has been accepted by the CSMS.
if (response.idTagInfo.status == AuthorizationStatus16.Accepted) {

    println("Authorization Accepted")

    //As the Authorization has been accepted, we can start a transaction, but before,
    //we need to change the Status to Preparing
    connection.statusNotification(
        meta = RequestMetadata(chargPointId),
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
            meta = RequestMetadata(chargPointId),
            request = StartTransactionReq16(
                connectorId = 4,
                idTag = idTag,
                meterStart = 0,
                now()
            )
        ).response

    //we are retrieving the transactionId from the reponse of our previous transaction request.
    val transactionId = response.transactionId

    println("TransactionId : $transactionId")

    // As the authorization has been accepted and that we have sent and receive a response for our startTransaction,
    // we can now set the charge status to Charging.
    // In order to do this, we send a statusNotification to the CSMS notifying that the status has changed to Charging
    connection.statusNotification(
        meta = RequestMetadata(chargPointId),
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
val ocppCSCallbacks = object : OcppCSCallbacks16() {
    override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 {
        remoteStartTransactionReq = req
        return RemoteStartTransactionResp16(status = RemoteStartStopStatus16.Accepted)
    }
}

//establish a connection to the CSMS
val connection = Ocpp16ConnectionToCSMS(
        chargePointId = chargPointId,
        csmsUrl = csmsUrl,
        transportType = transport,
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
        meta = RequestMetadata(chargPointId),
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
            meta = RequestMetadata(chargPointId),
            StartTransactionReq16(
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
        meta = RequestMetadata(chargPointId),
        StatusNotificationReq16(
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
val connection = Ocpp20ConnectionToCSMS(
        chargePointId = chargPointId,
        csmsUrl = csmsUrl,
        transportType = transport,
        ocppCSCallbacks = OcppCSCallbacks()
)
connection.connect()

val response: AuthorizeResp = connection.authorize(RequestMetadata(chargPointId), AuthorizeReq(IdTokenType(
        idToken = "2233223",
        type = IdTokenEnumType.Central,
))).response

if (response.idTokenInfo.status == AuthorizationStatusEnumType.Accepted) {
    connection.statusNotification(
            meta = RequestMetadata(chargPointId),
            request = StatusNotificationReq(
                    connectorId = 1,
                    connectorStatus = ConnectorStatusEnumType.Occupied,
                    evseId = 1,
                    timestamp = now()
            )
    )

    val response: TransactionEventResp =
            connection.transactionEvent(
                    meta = RequestMetadata(chargPointId),
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
        meta = RequestMetadata(chargPointId),
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
val ocppCSCallbacks = object : OcppCSCallbacks() {
    override fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp {
        remoteStartTransactionReq = req
        return RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
    }
}

val connection = Ocpp20ConnectionToCSMS(
        chargePointId = chargPointId,
        csmsUrl = csmsUrl,
        transportType = transport,
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
            meta = RequestMetadata(chargPointId),
            request = StatusNotificationReq(
                    connectorId = 1,
                    connectorStatus = ConnectorStatusEnumType.Occupied,
                    evseId = 1,
                    timestamp = now()
            )
    )

    val response: TransactionEventResp =
            connection.transactionEvent(
                    meta = RequestMetadata(chargPointId),
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
            meta = RequestMetadata(chargPointId),
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

### WebSocket events

On the CSMS side, `EventsListeners` notifies the WebSocket lifecycle of the charging stations: connection,
reconnection, close, and the Ping and Pong control frames they send.

A station may keep answering control frames while its OCPP application is stuck, so a recent Pong only proves
that the transport is responsive. Keep it apart from the last OCPP message when assessing a station.
The toolkit only reports the frames: recording, persisting and interpreting them is up to the application.
The server does not send Ping frames yet, so Pongs only arrive when a station sends them unsolicited.

```kotlin
import com.izivia.ocpp.CSOcppId
import com.izivia.ocpp.wamp.server.OcppWampServer
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import java.util.concurrent.ConcurrentHashMap
import kotlin.time.Instant

val lastPongs = ConcurrentHashMap<CSOcppId, Instant>()

val listeners = EventsListeners(
    onWsPongHandler = { event -> lastPongs[event.ocppId] = event.receivedAt },
    onWsCloseHandler = { ocppId, _ -> lastPongs.remove(ocppId) }
)
val server = OcppWampServer.newServer(listeners = listeners)
```

Control frames are reported only if they arrive on the current connection of the station: frames from a
replaced or closed connection are dropped, and a reported frame is always notified before the reconnection or
close event that ends its connection. `WsControlFrameEvent.wsConnectionId` tells the connections of a station
apart. The handlers run on the WebSocket IO thread and hold up the replacement of the connection, so they must
not block.

With the `toolkit` module, pass the listeners through `Settings.listeners`.

## Observing OCPP errors

The toolkit handles some failures on its own: it logs them and, over WebSocket, answers with a `CALLERROR`. To report
them to your monitoring system, give an `OcppErrorListener` to `ServerSetting`. It is optional, works the same for the
WebSocket and SOAP servers, and depends on no monitoring provider.

```kotlin
val errorListener = OcppErrorListener { event ->
    when (event.category) {
        OcppErrorCategory.UNHANDLED_ACTION ->
            metrics.incrementUnhandledAction(event.ocppVersion)

        OcppErrorCategory.PROTOCOL_ERROR,
        OcppErrorCategory.HANDLER_ERROR,
        OcppErrorCategory.TRANSPORT_ERROR ->
            Sentry.captureException(event.cause ?: IllegalStateException("OCPP failure: $event"))
    }
}

val csms = ApiFactory.csmsOcppServer(
    csmsSettings = CSMSSettings(
        listOf(
            ServerSetting(
                port = 8080,
                path = "ws",
                ocppVersion = setOf(OcppVersion.OCPP_1_6),
                transportType = TransportEnum.WEBSOCKET,
                errorListener = errorListener
            )
        )
    ),
    csmsApiCallbacks = listOf(csmsCallbacks),
    fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
)
```

| Category           | Reported when                                                                      |
|--------------------|------------------------------------------------------------------------------------|
| `UNHANDLED_ACTION` | an incoming message has no registered handler                                      |
| `PROTOCOL_ERROR`   | an incoming message or payload cannot be parsed, or a response cannot be serialized |
| `HANDLER_ERROR`    | a registered handler throws an exception                                           |
| `TRANSPORT_ERROR`  | the transport fails while processing a message, for instance when sending a response |

Each failure produces one event. Only `category` is always set: a message that cannot be parsed has no `messageId` nor
`action`, for instance.

- The listener only observes: the answer to the charging station is the same with and without it, and anything it
  throws is logged and ignored.
- It is called concurrently, possibly on the server's I/O thread, and must not block: a blocked call delays every
  charging station served by that thread. Hand the event off to your own executor.
- `ocppId` and `action` come from the charging station and are not validated. Bound them before using them as a metric
  dimension, and do not trust them as an identity.
- An event never carries a message payload, but the message of `cause` may quote a fragment of it. Sanitize it before
  sending it to a third party.

Failures that already reach your code as an exception, such as a failing `sendMessage`, are not reported. Neither is
the client side of the toolkit: only the servers report errors for now.

## Code Organisation

The main entry point is in the module `toolkit`, which provides access to all the apis and all ocpp versions.

For each version of ocpp supported, you will find:
- a `-core` module which provided all the data structures and operations declaration.
- a `-api-adapter` module which is used as an adapter between that version of ocpp and the generic api

Plus, you will find:
- `generic-api`, which is an ocpp independent api which can be used to switch between ocpp versions without changing your code
- `ocpp-transport`, which defines an interface for different transport of operations, with the modules `-websocket` and `-soap` for implementations
- `operation-information`, used to described operations in whatever version of apis
- `utils`, used to ease some common needs between apis
- `ocpp-wamp`, a client & server implementation of the WAMP-like RPC-over-websocket system defined in the OCPP-J protcols
