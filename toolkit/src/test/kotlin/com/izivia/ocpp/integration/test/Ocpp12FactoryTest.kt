package com.izivia.ocpp.integration.test

import com.izivia.ocpp.adapter12.Ocpp12Adapter
import com.izivia.ocpp.adapter15.Ocpp15Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.api12.OcppCSCallbacks
import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.integration.ApiFactory
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.soap.OcppSoapParser
import com.izivia.ocpp.soap.ResponseSoapMessage
import com.izivia.ocpp.soap12.Ocpp12SoapParser
import com.izivia.ocpp.soap15.Ocpp15SoapParser
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.mockk
import kotlinx.datetime.Instant
import org.http4k.core.Method
import org.http4k.core.Response
import org.http4k.core.Status
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp as HeartbeatResp12
import com.izivia.ocpp.core15.model.heartbeat.HeartbeatResp as HeartbeatResp15

class Ocpp12FactoryTest {
    private val messageId = "a727d144-82bb-497a-a0c7-4ef2295910d4"
    private val currentTime = Instant.parse("2022-02-15T00:00:00.000Z")

    @Test
    fun `creates OCPP 1-2 SOAP charge point connection`() {
        val operations = ApiFactory.ocpp12ConnectionToCSMS(
            chargePointId = "CP001",
            csmsUrl = "http://localhost:8080/ocpp",
            transportType = TransportEnum.SOAP,
            clientPath = "/cp",
            clientPort = 8081,
            ocppCSCallbacks = object : OcppCSCallbacks {}
        )

        expectThat(operations).isA<ChargePointOperations>()
    }

    @Test
    fun `creates CSMS API for OCPP 1-2 SOAP server`() {
        val chargePointOperations = ApiFactory.ocpp12ConnectionToCSMS(
            chargePointId = "CP001",
            csmsUrl = "http://localhost:8080/ocpp",
            transportType = TransportEnum.SOAP,
            clientPath = "/cp",
            clientPort = 8081,
            ocppCSCallbacks = object : OcppCSCallbacks {}
        )
        val csms = ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = 0,
                        path = "/ocpp",
                        ocppVersion = setOf(OcppVersion.OCPP_1_2),
                        transportType = TransportEnum.SOAP
                    )
                )
            ),
            csmsApiCallbacks = listOf(chargePointOperations),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        expectThat(csms.getCSApi12()).isA<CSMSOperations>()
    }

    @Test
    fun `creates generic API adapter for OCPP 1-5`() {
        val api = ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = OcppVersion.OCPP_1_5,
                transportType = TransportEnum.SOAP,
                clientPath = "/cp",
                clientPort = 8081
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

        expectThat(api).isA<Ocpp15Adapter>()
    }

    @Test
    fun `creates generic API adapter for OCPP 1-2`() {
        val api = ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = OcppVersion.OCPP_1_2,
                transportType = TransportEnum.SOAP,
                clientPath = "/cp",
                clientPort = 8081
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

        expectThat(api).isA<Ocpp12Adapter>()
    }

    @Test
    fun `sends heartbeat through OCPP 1-5 SOAP generic adapter`() {
        val receivedActions = mutableListOf<String>()
        val server = soapServer(
            parser = Ocpp15SoapParser(),
            payload = HeartbeatResp15(currentTime),
            receivedActions = receivedActions
        )

        try {
            val api = soapApi(OcppVersion.OCPP_1_5, server.port())
            try {
                api.connect()
                val response = api.heartbeat(RequestMetadata("CP001"), HeartbeatReq())

                expectThat(response.response.currentTime).isEqualTo(currentTime)
                expectThat(receivedActions.toList()).isEqualTo(listOf("Heartbeat"))
            } finally {
                api.close()
            }
        } finally {
            server.close()
        }
    }

    @Test
    fun `sends heartbeat through OCPP 1-2 SOAP generic adapter`() {
        val receivedActions = mutableListOf<String>()
        val server = soapServer(
            parser = Ocpp12SoapParser(),
            payload = HeartbeatResp12(currentTime),
            receivedActions = receivedActions
        )

        try {
            val api = soapApi(OcppVersion.OCPP_1_2, server.port())
            try {
                api.connect()
                val response = api.heartbeat(RequestMetadata("CP001"), HeartbeatReq())

                expectThat(response.response.currentTime).isEqualTo(currentTime)
                expectThat(receivedActions.toList()).isEqualTo(listOf("Heartbeat"))
            } finally {
                api.close()
            }
        } finally {
            server.close()
        }
    }

    private fun soapApi(ocppVersion: OcppVersion, port: Int) =
        ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = ocppVersion,
                transportType = TransportEnum.SOAP,
                target = "http://localhost:$port/ocpp",
                clientPath = "/cp",
                clientPort = 0,
                newMessageId = { messageId }
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

    private fun soapServer(
        parser: OcppSoapParser,
        payload: Any,
        receivedActions: MutableList<String>
    ) = routes(
        "/ocpp/" bind Method.POST to { request ->
            val soapRequest = parser.parseAnyRequestFromSoap(request.bodyString())
            receivedActions += soapRequest.action
            Response(Status.OK).body(
                parser.mapResponseToSoap(
                    ResponseSoapMessage(
                        action = soapRequest.action,
                        messageId = "response-$messageId",
                        relatesTo = soapRequest.messageId,
                        to = soapRequest.from,
                        from = soapRequest.to,
                        chargeBoxIdentity = soapRequest.chargingStationId,
                        payload = payload
                    )
                )
            )
        }
    ).asServer(Undertow(0)).start()
}
