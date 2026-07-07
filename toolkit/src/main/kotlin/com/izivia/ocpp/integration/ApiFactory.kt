package com.izivia.ocpp.integration

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.adapter16.Ocpp16Adapter
import com.izivia.ocpp.adapter16.impl.RealTransactionRepository
import com.izivia.ocpp.adapter20.Ocpp20Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.http.OcppSoapClientTransport
import com.izivia.ocpp.http.OcppSoapServerTransport
import com.izivia.ocpp.http.SoapClientSettings
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.integration.model.TransportEnum.SOAP
import com.izivia.ocpp.integration.model.TransportEnum.WEBSOCKET
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.soap12.Ocpp12SoapParser
import com.izivia.ocpp.soap15.Ocpp15SoapParser
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.RequestHeaders
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.wamp.server.impl.EventsListeners
import com.izivia.ocpp.wamp.server.impl.OcppWampServerSettings
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import java.util.*
import com.izivia.ocpp.api20.DefaultCSMSOperations as DefaultCSMSOperations20
import com.izivia.ocpp.api20.OcppCSCallbacks as OcppCSCallbacks20
import com.izivia.ocpp.api16.DefaultCSMSOperations as DefaultCSMSOperations16
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.api15.DefaultCSMSOperations as DefaultCSMSOperations15
import com.izivia.ocpp.api15.OcppCSCallbacks as OcppCSCallbacks15
import com.izivia.ocpp.api12.DefaultCSMSOperations as DefaultCSMSOperations12
import com.izivia.ocpp.api12.OcppCSCallbacks as OcppCSCallbacks12
import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
import com.izivia.ocpp.core20.impl.RealChargePointOperations as RealChargePointOperations20
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
import com.izivia.ocpp.core16.impl.RealChargePointOperations as RealChargePointOperations16
import com.izivia.ocpp.core15.ChargePointOperations as ChargePointOperations15
import com.izivia.ocpp.core15.impl.RealChargePointOperations as RealChargePointOperations15
import com.izivia.ocpp.core12.ChargePointOperations as ChargePointOperations12
import com.izivia.ocpp.core12.impl.RealChargePointOperations as RealChargePointOperations12
import com.izivia.ocpp.transport.OcppVersion as OcppVersionTransport

class ApiFactory {
    companion object {

        private fun createClientTransport(
            clientPath: String?,
            clientPort: Int?,
            transportType: TransportEnum,
            ocppId: String,
            ocppVersion: OcppVersionTransport,
            target: String,
            headers: RequestHeaders = emptyList(),
            newMessageId: () -> String = { UUID.randomUUID().toString() }
        ): ClientTransport =
            when (transportType) {
                WEBSOCKET -> createClientTransportWebsocket(
                    ocppVersion,
                    ocppId,
                    target,
                    headers,
                    newMessageId
                )

                SOAP -> createClientTransportSoap(
                    clientPath!!,
                    clientPort!!,
                    ocppId,
                    ocppVersion,
                    target,
                    headers,
                    newMessageId
                )
            }

        private fun createClientTransportWebsocket(
            ocppVersion: OcppVersionTransport,
            ocppId: String,
            target: String,
            headers: RequestHeaders = emptyList(),
            newMessageId: () -> String
        ): ClientTransport =
            WebsocketClient(ocppId, OcppVersion.valueOf(ocppVersion.name), target, headers, newMessageId)

        private fun createClientTransportSoap(
            path: String,
            port: Int,
            ocppId: String,
            ocppVersion: OcppVersionTransport,
            target: String,
            headers: RequestHeaders = emptyList(),
            newMessageId: () -> String
        ): ClientTransport =
            OcppSoapClientTransport.createClient(
                SoapClientSettings(path.removeSuffix("/"), port),
                ocppId,
                target,
                getSoapParser(ocppVersion),
                headers,
                newMessageId
            )

        private fun createServerTransportWebsocket(
            path: String,
            ocppVersion: Set<OcppVersionTransport>,
            newMessageId: () -> String,
            settings: OcppWampServerSettings,
            listeners: EventsListeners = EventsListeners()
        ): ServerTransport =
            WebsocketServer(ocppVersion, path, newMessageId, settings, listeners)

        private fun createServerTransportSoap(
            path: String,
            ocppVersion: OcppVersionTransport,
            newMessageId: () -> String
        ): ServerTransport =
            OcppSoapServerTransport.create(ocppVersion, path, getSoapParser(ocppVersion), newMessageId)

        fun getCSMSApi(
            settings: Settings,
            ocppId: String,
            csApi: CSApi,
            headers: RequestHeaders = emptyList(),
            newMessageId: () -> String = { UUID.randomUUID().toString() }
        ): CSMSApi {
            val transport: ClientTransport = createClientTransport(
                settings.clientPath,
                settings.clientPort,
                settings.transportType,
                ocppId,
                settings.ocppVersion,
                settings.target,
                headers,
                newMessageId
            )
            return when (settings.ocppVersion) {
                OcppVersionTransport.OCPP_2_0 -> Ocpp20Adapter(ocppId, transport, csApi)
                OcppVersionTransport.OCPP_1_6 -> Ocpp16Adapter(
                    ocppId,
                    transport,
                    csApi,
                    RealTransactionRepository(),
                    settings.ocpp16SecurityExtensions
                )
                OcppVersionTransport.OCPP_1_5 -> throw NotImplementedError("Ocpp 1.5 api adapted not yet implemented")
                OcppVersionTransport.OCPP_1_2 -> throw NotImplementedError("Ocpp 1.2 api adapted not implemented")
            }
        }

        fun ocpp20ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
            headers: RequestHeaders = emptyList(),
            ocppCSCallbacks: OcppCSCallbacks20
        ): ChargePointOperations20 =
            RealChargePointOperations20(
                chargeStationId = chargePointId,
                client = createClientTransport(
                    clientPath = clientPath,
                    clientPort = clientPort,
                    ocppVersion = OcppVersionTransport.OCPP_2_0,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl,
                    headers = headers
                ),
                csmsOperations = DefaultCSMSOperations20(ocppCSCallbacks)
            )

        fun ocpp16ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
            headers: RequestHeaders = emptyList(),
            ocppCSCallbacks: OcppCSCallbacks16
        ): ChargePointOperations16 =
            RealChargePointOperations16(
                chargeStationId = chargePointId,
                client = createClientTransport(
                    clientPath = clientPath,
                    clientPort = clientPort,
                    ocppVersion = OcppVersionTransport.OCPP_1_6,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl,
                    headers = headers
                ),
                csmsOperations = DefaultCSMSOperations16(ocppCSCallbacks)
            )

        fun ocpp15ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
            headers: RequestHeaders = emptyList(),
            ocppCSCallbacks: OcppCSCallbacks15
        ): ChargePointOperations15 =
            RealChargePointOperations15(
                chargeStationId = chargePointId,
                client = createClientTransport(
                    clientPath = clientPath,
                    clientPort = clientPort,
                    ocppVersion = OcppVersionTransport.OCPP_1_5,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl,
                    headers = headers
                ),
                csmsOperations = DefaultCSMSOperations15(ocppCSCallbacks)
            )

        fun ocpp12ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
            headers: RequestHeaders = emptyList(),
            ocppCSCallbacks: OcppCSCallbacks12
        ): ChargePointOperations12 =
            RealChargePointOperations12(
                chargeStationId = chargePointId,
                client = createClientTransport(
                    clientPath = clientPath,
                    clientPort = clientPort,
                    ocppVersion = OcppVersionTransport.OCPP_1_2,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl,
                    headers = headers
                ),
                csmsOperations = DefaultCSMSOperations12(ocppCSCallbacks)
            )

        fun csmsOcppServer(
            csmsSettings: CSMSSettings,
            csmsApiCallbacks: List<CSMSCallbacks>,
            fn: (String) -> ChargingStationConfig
        ): CSMS {
            val transports: Map<ServerTransport, Pair<Int, Set<OcppVersionTransport>>> =
                csmsSettings.servers.flatMap { s ->
                    when (s.transportType) {
                        WEBSOCKET -> listOf(
                            createServerTransportWebsocket(
                                s.path,
                                s.ocppVersion,
                                s.newMessageId,
                                s.wampSettings,
                                s.listeners
                            ) to (s.port to s.ocppVersion)
                        )
                        SOAP -> s.ocppVersion.map { version ->
                            createServerTransportSoap(
                                s.path,
                                version,
                                s.newMessageId
                            ) to (s.port to setOf(version))
                        }
                    }
                }.toMap()
            return CSMS(transports, csmsApiCallbacks.toSet(), fn)
        }
    }
}

private fun getSoapParser(version: OcppVersionTransport) = when (version) {
    OcppVersionTransport.OCPP_1_6 -> Ocpp16SoapParser()
    OcppVersionTransport.OCPP_1_5 -> Ocpp15SoapParser()
    OcppVersionTransport.OCPP_1_2 -> Ocpp12SoapParser()
    else -> TODO("Not yet implemented")
}
