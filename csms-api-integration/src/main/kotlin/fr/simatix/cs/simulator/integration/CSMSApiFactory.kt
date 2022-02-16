package fr.simatix.cs.simulator.integration

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.core16.Transport
import fr.simatix.cs.simulator.integration.model.TransportEnum
import io.simatix.ev.ocpp.OcppVersion
import fr.simatix.cs.simulator.websocket16.WebsocketClient as WebsocketClient16
import fr.simatix.cs.simulator.websocket20.WebsocketClient as WebsocketClient20

class CSMSApiFactory {
    companion object {
        fun getCSMSApi(version: OcppVersion, ocppId: String, transportType: TransportEnum): CSMSApi {
            return if (version == OcppVersion.OCPP_1_6) {
                val transport: Transport = when (transportType) {
                    TransportEnum.WEBSOCKET -> WebsocketClient16(ocppId)
                    TransportEnum.SOAP -> WebsocketClient16(ocppId)
                }
                Ocpp16Adapter.newOcpp16AdapterImpl(transport)
            } else {
                val transport = when (transportType) {
                    TransportEnum.WEBSOCKET -> WebsocketClient20(ocppId)
                    TransportEnum.SOAP -> WebsocketClient20(ocppId)
                }
                Ocpp20Adapter.newOcpp20AdapterImpl(transport)
            }
        }
    }
}