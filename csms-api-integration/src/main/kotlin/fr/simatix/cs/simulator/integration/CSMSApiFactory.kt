package fr.simatix.cs.simulator.integration

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.simatix.ev.ocpp.OcppVersion

class CSMSApiFactory {
    companion object {
        fun getCSMSApi(ocppVersion: OcppVersion, ocppId: String, transportType: TransportEnum): CSMSApi {
            val transport: Transport = when (transportType) {
                TransportEnum.WEBSOCKET -> WebsocketClient(ocppId, ocppVersion)
                TransportEnum.SOAP -> WebsocketClient(ocppId, ocppVersion)
            }
            return if (ocppVersion == OcppVersion.OCPP_1_6) {
                Ocpp16Adapter(transport)
            } else {
                Ocpp20Adapter(transport)
            }
        }
    }
}