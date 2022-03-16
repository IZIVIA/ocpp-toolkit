package fr.simatix.cs.simulator.integration

import fr.simatix.cs.simulator.adapter16.Ocpp16Adapter
import fr.simatix.cs.simulator.adapter16.impl.RealTransactionRepository
import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.simatix.ev.ocpp.OcppVersion

class CSMSApiFactory {
    companion object {
        private fun createTransport(
            ocppVersion: OcppVersion,
            ocppId: String,
            transportType: TransportEnum,
            target: String
        ): Transport =
            when (transportType) {
                TransportEnum.WEBSOCKET -> WebsocketClient(ocppId, ocppVersion, target)
                TransportEnum.SOAP -> WebsocketClient(ocppId, ocppVersion, target)
            }

        fun getCSMSApi(settings: Settings, ocppId: String): CSMSApi {
            val transport: Transport =
                createTransport(settings.ocppVersion, ocppId, settings.transportType, settings.target)
            return if (settings.ocppVersion == OcppVersion.OCPP_1_6) {
                Ocpp16Adapter(transport, RealTransactionRepository())
            } else {
                Ocpp20Adapter(transport)
            }
        }
    }
}