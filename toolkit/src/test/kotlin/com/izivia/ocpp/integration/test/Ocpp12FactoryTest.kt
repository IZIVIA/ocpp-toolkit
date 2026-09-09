package com.izivia.ocpp.integration.test

import com.izivia.ocpp.adapter12.Ocpp12Adapter
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
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.soap12.Ocpp12SoapParser
import com.izivia.ocpp.transport.OcppVersion
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA
import strikt.assertions.isEqualTo
import java.net.ServerSocket
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq as AuthorizeReq12
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp as AuthorizeResp12
import com.izivia.ocpp.core12.model.common.IdTagInfo as IdTagInfo12
import com.izivia.ocpp.core12.model.common.enumeration.AuthorizationStatus as AuthorizationStatus12
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus as RemoteStartStopStatus12
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp as HeartbeatResp12
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq as RemoteStartTransactionReq12
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp as RemoteStartTransactionResp12

class Ocpp12FactoryTest {
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
    fun `sends heartbeat through OCPP 1-2 SOAP generic adapter`() {
        val receivedActions = mutableListOf<String>()
        val server = soapServer(
            parser = Ocpp12SoapParser(),
            payload = HeartbeatResp12(TEST_CURRENT_TIME),
            receivedActions = receivedActions
        )

        try {
            val api = soapApi(OcppVersion.OCPP_1_2, server.port())
            try {
                api.connect()
                val response = api.heartbeat(RequestMetadata("CP001"), HeartbeatReq())

                expectThat(response.response.currentTime).isEqualTo(TEST_CURRENT_TIME)
                expectThat(receivedActions.toList()).isEqualTo(listOf("Heartbeat"))
            } finally {
                api.close()
            }
        } finally {
            server.close()
        }
    }

    @Test
    fun `creates generic API adapter for OCPP 1-2 over websocket`() {
        val api = ApiFactory.getCSMSApi(
            settings = Settings(
                ocppVersion = OcppVersion.OCPP_1_2,
                transportType = TransportEnum.WEBSOCKET,
                target = "ws://localhost:8080/ocpp"
            ),
            ocppId = "CP001",
            csApi = mockk<CSApi>(relaxed = true)
        )

        expectThat(api).isA<Ocpp12Adapter>()
    }

    @Test
    fun `creates CSMS API for OCPP 1-2 websocket server`() {
        val csms = ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = 0,
                        path = "/ocpp",
                        ocppVersion = setOf(OcppVersion.OCPP_1_2),
                        transportType = TransportEnum.WEBSOCKET
                    )
                )
            ),
            csmsApiCallbacks = listOf(mockk<ChargePointOperations>(relaxed = true)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        expectThat(csms.getCSApi12()).isA<CSMSOperations>()
    }

    @Test
    fun `exchanges OCPP 1-2 messages over a websocket connection`() {
        val port = ServerSocket(0).use { it.localPort }
        val path = "ws"
        val chargePointId = "CP001"
        val idTag = "Tag1"

        val server = ApiFactory.csmsOcppServer(
            csmsSettings = CSMSSettings(
                listOf(
                    ServerSetting(
                        port = port,
                        path = path,
                        ocppVersion = setOf(OcppVersion.OCPP_1_2),
                        transportType = TransportEnum.WEBSOCKET
                    )
                )
            ),
            csmsApiCallbacks = listOf(authorizingCsms(idTag)),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )
        server.start()

        try {
            val connection = ApiFactory.ocpp12ConnectionToCSMS(
                chargePointId = chargePointId,
                csmsUrl = "ws://localhost:$port/$path",
                transportType = TransportEnum.WEBSOCKET,
                clientPath = null,
                clientPort = null,
                ocppCSCallbacks = object : OcppCSCallbacks {
                    override fun remoteStartTransaction(req: RemoteStartTransactionReq12) =
                        RemoteStartTransactionResp12(RemoteStartStopStatus12.Accepted)
                }
            )
            connection.connect()

            try {
                /* Charge point to central system */
                val authorize = connection
                    .authorize(RequestMetadata(chargePointId), AuthorizeReq12(idTag = idTag))
                    .response
                expectThat(authorize.idTagInfo.status).isEqualTo(AuthorizationStatus12.Accepted)

                /* Central system to charge point */
                val remoteStart = server.getCSApi12()
                    .remoteStartTransaction(
                        RequestMetadata(chargePointId),
                        RemoteStartTransactionReq12(idTag = idTag)
                    )
                    .response
                expectThat(remoteStart.status).isEqualTo(RemoteStartStopStatus12.Accepted)
            } finally {
                connection.close()
            }
        } finally {
            server.stop()
        }
    }

    /**
     * Central system side of the websocket round trip. Only [ChargePointOperations.authorize] is
     * stubbed, and only for [acceptedIdTag]: any other call fails the test instead of answering
     * silently.
     */
    private fun authorizingCsms(acceptedIdTag: String) = mockk<ChargePointOperations>().also { csms ->
        every { csms.authorize(any(), match { it.idTag == acceptedIdTag }) } answers {
            OperationExecution(
                ExecutionMetadata(firstArg(), RequestStatus.SUCCESS),
                secondArg(),
                AuthorizeResp12(idTagInfo = IdTagInfo12(status = AuthorizationStatus12.Accepted))
            )
        }
    }
}
