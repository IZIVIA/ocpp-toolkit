package com.izivia.ocpp.wamp.client.impl

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.classic.spi.ThrowableProxyUtil
import ch.qos.logback.core.read.ListAppender
import com.izivia.ocpp.OcppVersion.OCPP_1_6
import com.izivia.ocpp.wamp.client.ConnectionListener
import com.izivia.ocpp.wamp.client.ConnectionState
import com.izivia.ocpp.wamp.client.OcppWampClient
import com.izivia.ocpp.wamp.client.WampOnActionHandler
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectConfig
import com.izivia.ocpp.wamp.client.autoreconnect.AutoReconnectOcppWampClient
import com.izivia.ocpp.wamp.getFreePort
import com.izivia.ocpp.wamp.messages.WampMessage
import io.undertow.Handlers
import io.undertow.Undertow
import io.undertow.server.HttpHandler
import io.undertow.websockets.WebSocketConnectionCallback
import io.undertow.websockets.core.AbstractReceiveListener
import io.undertow.websockets.core.BufferedTextMessage
import io.undertow.websockets.core.WebSocketChannel
import io.undertow.websockets.core.WebSockets
import org.http4k.core.Uri
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.slf4j.LoggerFactory
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.all
import strikt.assertions.contains
import strikt.assertions.isNotEmpty
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import java.io.IOException
import java.util.UUID
import java.util.concurrent.CompletableFuture
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.TimeUnit
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * Runs a real [OkHttpOcppWampClient] against a raw Undertow WebSocket peer, which sends the frames each test chooses,
 * with every logger at TRACE, and checks that no captured log event, message or rendered throwable, holds a sentinel
 * carried by a frame, a credential or an exception message.
 *
 * Each test first waits for the metadata event of the path it exercises, so that it cannot pass on a missing line.
 */
class OkHttpOcppWampClientLogLeakTest {

    @Test
    fun `an inbound call and its answer are logged without their payloads`() = withLogs { logs ->
        val sentinel = sentinel()
        withPeer { peer ->
            withClient(peer, answering(sentinel)) {
                peer.send("""[2,"m-call","DataTransfer",{"data":"$sentinel"}]""")
                expectThat(peer.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains("$sentinel-resp")
                logs.awaitEvent("inbound call") {
                    it.formattedMessage.contains("[IN][CALL][REQ]") && it.formattedMessage.contains("[m-call]")
                }
                logs.awaitEvent("answer") {
                    it.formattedMessage.contains("[IN][CALL][RESP]") && it.formattedMessage.contains("[m-call]")
                }
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `an unparsable frame is logged without its content`() = withLogs { logs ->
        val sentinel = sentinel()
        withPeer { peer ->
            withClient(peer) {
                peer.send("""[2,"m-bad" $sentinel""")
                logs.awaitEvent("parse failure") { it.formattedMessage.contains("can't parse wamp message") }
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a throwing handler is logged without the messages of its exception chain`() = withLogs { logs ->
        val sentinel = sentinel()
        val failure = IllegalStateException("top $sentinel", IllegalArgumentException("cause $sentinel"))
        withPeer { peer ->
            withClient(peer, { _, _ -> throw failure }) {
                peer.send("""[2,"m-throw","Heartbeat",{}]""")
                logs.awaitEvent("call failure") {
                    it.formattedMessage.contains("[m-throw] [Heartbeat] call failed") &&
                        it.formattedMessage.contains(IllegalStateException::class.java.name) &&
                        it.throwableProxy != null
                }
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a handler failing with an error is logged without its message and answers nothing`() = withLogs { logs ->
        val sentinel = sentinel()
        withPeer { peer ->
            withClient(peer, { _, _ -> TODO(sentinel) }) {
                peer.send("""[2,"m-todo","Heartbeat",{}]""")
                logs.awaitEvent("call failure") {
                    it.formattedMessage.contains("[m-todo] [Heartbeat] call failed") &&
                        it.formattedMessage.contains(NotImplementedError::class.java.name) &&
                        it.throwableProxy != null
                }
                expectThat(peer.received.poll(200, TimeUnit.MILLISECONDS)).isNull()
            }
        }
        logs.expectNoLeak(sentinel)
    }

    @Test
    fun `a refused upgrade fails without the server reason in the logs nor the exception message`() =
        withLogs { logs ->
            val sentinel = sentinel()
            withPeer(rejectReason = "Forbidden $sentinel") { peer ->
                val client = client(peer, "/reject")
                expectThrows<IOException> { client.connect() }.and {
                    get { message }.isNotNull().and {
                        not().contains(sentinel)
                        contains("connection failed")
                        contains("ProtocolException - HTTP 403")
                    }
                    // the cause is kept for the caller, its message included
                    get { cause }.isNotNull().get { message }.isNotNull().contains(sentinel)
                }
                logs.awaitEvent("connection failure") {
                    it.formattedMessage.contains("error when connecting") &&
                        it.formattedMessage.contains("ProtocolException")
                }
            }
            logs.expectNoLeak(sentinel)
        }

    @Test
    fun `the credentials and the query string of the server uri are not logged`() = withLogs { logs ->
        val password = sentinel()
        val token = sentinel()
        withPeer { peer ->
            val target = Uri.of("ws://user:$password@localhost:${peer.port}/ws?token=$token")
            val client = OkHttpOcppWampClient(target, OCPP_ID, OCPP_1_6, timeoutInMs = 2_000)
            try {
                client.connect()
                logs.awaitEvent("connected") { it.formattedMessage.contains("connected") }
                expectThat(client.debugContext) {
                    not().contains(password)
                    not().contains(token)
                    contains("localhost:${peer.port}/ws/$OCPP_ID")
                }
            } finally {
                client.close()
            }
        }
        logs.expectNoLeak(password)
        logs.expectNoLeak(token)
    }

    @Test
    fun `a failed reconnection attempt is logged without the exception message`() =
        withLogs { logs ->
            val sentinel = sentinel()
            val client = AutoReconnectOcppWampClient(
                RefusingClient(IOException("refused $sentinel", IllegalStateException("cause $sentinel"))),
                OCPP_ID,
                AutoReconnectConfig(5.seconds, 10.milliseconds, 10.milliseconds)
            )
            try {
                client.connect()
                logs.awaitEvent("failed attempt") {
                    it.formattedMessage.contains("attempt updated") &&
                        it.formattedMessage.contains("FailedConnectionAttemptState") &&
                        it.formattedMessage.contains(IOException::class.java.name)
                }
            } finally {
                client.close()
            }
            logs.expectNoLeak(sentinel)
        }

    @Test
    fun `the frames logger at debug logs the inbound call and its answer only through itself`() =
        withLogs(framesLevel = Level.DEBUG) { logs ->
            val sentinel = sentinel()
            withPeer { peer ->
                withClient(peer, answering(sentinel)) {
                    peer.send("""[2,"m-frames","DataTransfer",{"data":"$sentinel"}]""")
                    expectThat(peer.received.poll(5, TimeUnit.SECONDS)).isNotNull().contains("$sentinel-resp")
                    logs.awaitEvent("inbound frame") {
                        it.loggerName == FRAMES && it.formattedMessage.contains("""{"data":"$sentinel"}""")
                    }
                    logs.awaitEvent("outbound frame") {
                        it.loggerName == FRAMES && it.formattedMessage.contains("$sentinel-resp")
                    }
                }
            }
            logs.expectNoLeak(sentinel, except = FRAMES)
        }

    private fun sentinel() = "sentinel-168-${UUID.randomUUID()}"

    private fun answering(sentinel: String): WampOnActionHandler = { _, msg ->
        WampMessage.CallResult(msg.msgId, """{"status":"Accepted","data":"$sentinel-resp"}""")
    }

    /** The events every logger emitted while a test ran. */
    private class Logs(private val appender: ListAppender<ILoggingEvent>) {
        fun events(): List<ILoggingEvent> = synchronized(appender) { appender.list.toList() }

        /** Waits for an event matching [predicate], failing loudly when none shows up within 5 s. */
        fun awaitEvent(description: String, predicate: (ILoggingEvent) -> Boolean) {
            val deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5)
            while (System.nanoTime() < deadline) {
                if (events().any(predicate)) return
                Thread.sleep(10)
            }
            fail("no '$description' log event within 5 s - events: ${events().map { it.formattedMessage }}")
        }

        /** Expects that no event, except those of the logger [except], holds [sentinel]. */
        fun expectNoLeak(sentinel: String, except: String? = null) {
            val checked = events().filter { it.loggerName != except }
            expectThat(checked).isNotEmpty().all {
                get { formattedMessage }.not().contains(sentinel)
                get { throwableProxy?.let { ThrowableProxyUtil.asString(it) }.orEmpty() }.not().contains(sentinel)
            }
        }
    }

    /**
     * Captures every logger, `com.izivia` and the root at TRACE, the frames logger at [framesLevel], and restores the
     * levels afterwards.
     */
    private fun withLogs(framesLevel: Level = Level.OFF, block: (Logs) -> Unit) {
        val root = LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME) as Logger
        val izivia = LoggerFactory.getLogger("com.izivia") as Logger
        val frames = LoggerFactory.getLogger(FRAMES) as Logger
        val previous = listOf(root, izivia, frames).associateWith { it.level }
        val appender = ListAppender<ILoggingEvent>().apply { start() }
        root.level = Level.TRACE
        izivia.level = Level.TRACE
        frames.level = framesLevel
        root.addAppender(appender)
        try {
            block(Logs(appender))
        } finally {
            root.detachAppender(appender)
            previous.forEach { (logger, level) -> logger.level = level }
            appender.stop()
        }
    }

    /** A raw WebSocket peer: it sends the frames a test chooses and collects the ones it receives. */
    private class Peer(val port: Int) {
        val received = LinkedBlockingQueue<String>()
        val channel = CompletableFuture<WebSocketChannel>()

        fun send(frame: String) = WebSockets.sendTextBlocking(frame, channel.get(5, TimeUnit.SECONDS))
    }

    /**
     * Starts a peer accepting WebSocket connections under `/ws`, and answering an upgrade under `/reject` with a 403
     * whose reason phrase is [rejectReason], then runs [block].
     */
    private fun withPeer(rejectReason: String = "Forbidden", block: (Peer) -> Unit) {
        val peer = Peer(getFreePort())
        val webSocket = Handlers.websocket(
            WebSocketConnectionCallback { _, channel ->
                channel.receiveSetter.set(object : AbstractReceiveListener() {
                    override fun onFullTextMessage(channel: WebSocketChannel, message: BufferedTextMessage) {
                        peer.received.add(message.data)
                    }
                })
                channel.resumeReceives()
                peer.channel.complete(channel)
            }
        )
        val reject = HttpHandler { exchange ->
            exchange.statusCode = 403
            exchange.reasonPhrase = rejectReason
            exchange.endExchange()
        }
        val server = Undertow.builder()
            .addHttpListener(peer.port, "localhost")
            .setHandler(Handlers.path().addPrefixPath("/ws", webSocket).addPrefixPath("/reject", reject))
            .build()
        server.start()
        try {
            block(peer)
        } finally {
            server.stop()
        }
    }

    private fun client(peer: Peer, path: String = "/ws") =
        OkHttpOcppWampClient(Uri.of("ws://localhost:${peer.port}$path"), OCPP_ID, OCPP_1_6, timeoutInMs = 2_000)

    /** Connects a client with [handler] to [peer], runs [block] and closes the client. */
    private fun withClient(peer: Peer, handler: WampOnActionHandler = { _, _ -> null }, block: () -> Unit) {
        val client = client(peer)
        client.onAction(handler)
        try {
            client.connect()
            block()
        } finally {
            client.close()
        }
    }

    /** A client whose every connection attempt fails with [failure]. */
    private class RefusingClient(private val failure: Exception) : OcppWampClient {
        override val state = ConnectionState.DISCONNECTED
        override fun connect(listener: ConnectionListener?) = throw failure
        override fun close() {}
        override fun sendBlocking(message: WampMessage): WampMessage = throw failure
        override fun onAction(fn: WampOnActionHandler) {}
    }

    private companion object {
        const val OCPP_ID = "CS1"
        const val FRAMES = "com.izivia.ocpp.wamp.frames"
    }
}
