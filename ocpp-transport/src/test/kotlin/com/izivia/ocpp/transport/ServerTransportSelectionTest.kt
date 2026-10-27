package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo
import strikt.assertions.isSameInstanceAs
import kotlin.reflect.KClass

class ServerTransportSelectionTest {

    private class SelectiveServerTransport(private val canSend: (ChargingStationConfig) -> Boolean) :
        ServerTransport {
        override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P =
            throw UnsupportedOperationException()

        override fun <T : Any, P> receiveMessageClass(
            clazz: KClass<T>,
            action: String,
            ocppVersion: OcppVersion,
            onAction: (RequestMetadata, T) -> P,
            accept: (String) -> ChargingStationConfig
        ) = throw UnsupportedOperationException()

        override fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig) =
            canSend(chargingStationConfig)
    }

    private val soapConfig = ChargingStationConfig(acceptConnection = true, soapUrl = "http://cs-1/ocpp")
    private val soapOnly = SelectiveServerTransport { it.soapUrl != null }
    private val websocketOnly = SelectiveServerTransport { it.soapUrl == null }

    @Test
    fun `transportFor picks the first transport able to reach the charging station`() {
        val otherSoap = SelectiveServerTransport { it.soapUrl != null }

        val transport = listOf(websocketOnly, soapOnly, otherSoap).transportFor("cs-1") { soapConfig }

        expectThat(transport).isSameInstanceAs(soapOnly)
    }

    @Test
    fun `transportFor resolves the charging station config once`() {
        val accepted = mutableListOf<String>()

        listOf(websocketOnly, websocketOnly, soapOnly).transportFor("cs-1") {
            accepted += it
            soapConfig
        }

        expectThat(accepted).containsExactly("cs-1")
    }

    @Test
    fun `transportFor fails when no transport can reach the charging station`() {
        expectThrows<IllegalStateException> {
            listOf(websocketOnly).transportFor("cs-1") { soapConfig }
        }.get { message }.isEqualTo("No transport to send a message to cs-1")
    }

    @Test
    fun `transportFor fails when there is no transport`() {
        expectThrows<IllegalStateException> {
            emptyList<ServerTransport>().transportFor("cs-1") { soapConfig }
        }.get { message }.isEqualTo("No transport to send a message to cs-1")
    }
}
