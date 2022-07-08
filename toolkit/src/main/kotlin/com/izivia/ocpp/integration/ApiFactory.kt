package com.izivia.ocpp.integration

import com.izivia.ocpp.OcppVersion
import com.izivia.ocpp.adapter16.Ocpp16Adapter
import com.izivia.ocpp.adapter16.impl.RealTransactionRepository
import com.izivia.ocpp.adapter20.Ocpp20Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.http.OcppSoapClientTransport
import com.izivia.ocpp.http.OcppSoapServerTransport
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.integration.model.TransportEnum.SOAP
import com.izivia.ocpp.integration.model.TransportEnum.WEBSOCKET
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.soap16.Ocpp16SoapParser
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.websocket.WebsocketServer
import java.util.*
import com.izivia.ocpp.api16.DefaultCSMSOperations as DefaultCSMSOperations16
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.api20.DefaultCSMSOperations as DefaultCSMSOperations20
import com.izivia.ocpp.api20.OcppCSCallbacks as OcppCSCallbacks20
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
import com.izivia.ocpp.core16.impl.RealChargePointOperations as RealChargePointOperations16
import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
import com.izivia.ocpp.core20.impl.RealChargePointOperations as RealChargePointOperations20
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
            newMessageId: () -> String = { UUID.randomUUID().toString() }
        ): ClientTransport =
            when (transportType) {
                WEBSOCKET -> createClientTransportWebsocket(
                    ocppVersion,
                    ocppId,
                    target
                )
                SOAP -> createClientTransportSoap(
                    clientPath!!,
                    clientPort!!,
                    ocppId,
                    ocppVersion,
                    target,
                    newMessageId
                )
            }

        private fun createClientTransportWebsocket(
            ocppVersion: OcppVersionTransport,
            ocppId: String,
            target: String
        ): ClientTransport =
            WebsocketClient(ocppId, OcppVersion.valueOf(ocppVersion.name), target)

        private fun createClientTransportSoap(
            path: String,
            port: Int,
            ocppId: String,
            ocppVersion: OcppVersionTransport,
            target: String,
            newMessageId: () -> String
        ): ClientTransport =
            when (ocppVersion) {
                OcppVersionTransport.OCPP_1_6 -> Ocpp16SoapParser()
                else -> TODO("Not yet implemented")
            }
                .let { parser -> OcppSoapClientTransport(path, port, ocppId, target, parser, newMessageId) }

        private fun createServerTransportWebsocket(
            port: Int,
            path: String,
            ocppVersion: Set<OcppVersionTransport>,
            newMessageId: () -> String
        ): ServerTransport =
            WebsocketServer(port, ocppVersion, path, newMessageId)

        private fun createServerTransportSoap(
            port: Int,
            path: String,
            ocppVersion: OcppVersionTransport,
            newMessageId: () -> String
        ): ServerTransport =
            when (ocppVersion) {
                OcppVersionTransport.OCPP_1_6 -> Ocpp16SoapParser()
                else -> TODO("Not yet implemented")
            }
                .let { parser -> OcppSoapServerTransport(ocppVersion, port, path, parser, newMessageId) }

        fun getCSMSApi(settings: Settings, ocppId: String, csApi: CSApi): CSMSApi {
            val transport: ClientTransport = createClientTransport(
                settings.clientPath,
                settings.clientPort,
                settings.transportType,
                ocppId,
                settings.ocppVersion,
                settings.target
            )
            return if (settings.ocppVersion == OcppVersionTransport.OCPP_1_6) {
                Ocpp16Adapter(ocppId, transport, csApi, RealTransactionRepository())
            } else {
                Ocpp20Adapter(ocppId, transport, csApi)
            }
        }

        fun ocpp16ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
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
                    target = csmsUrl
                ),
                csmsOperations = DefaultCSMSOperations16(ocppCSCallbacks)
            )

        fun ocpp20ConnectionToCSMS(
            chargePointId: String,
            csmsUrl: String,
            transportType: TransportEnum,
            clientPath: String?,
            clientPort: Int?,
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
                    target = csmsUrl
                ),
                csmsOperations = DefaultCSMSOperations20(ocppCSCallbacks)
            )

        fun csmsOcppServer(
            csmsSettings: CSMSSettings,
            csmsApiCallbacks: List<CSMSCallbacks>,
            fn: (String) -> ChargingStationConfig
        ): CSMS {
            val transports: Map<ServerTransport, Set<OcppVersionTransport>> =
                mutableMapOf<ServerTransport, Set<OcppVersionTransport>>().also { map ->
                    csmsSettings.servers.map {
                        when (it.transportType) {
                            WEBSOCKET -> map.put(
                                createServerTransportWebsocket(
                                    it.port,
                                    it.path,
                                    it.ocppVersion,
                                    it.newMessageId
                                ), it.ocppVersion
                            )
                            SOAP -> it.ocppVersion.forEach { version ->
                                map[createServerTransportSoap(it.port, it.path, version, it.newMessageId)] =
                                    setOf(version)
                            }
                        }
                    }
                }
            return CSMS(transports, csmsApiCallbacks.toSet(), fn)
        }
    }
}
