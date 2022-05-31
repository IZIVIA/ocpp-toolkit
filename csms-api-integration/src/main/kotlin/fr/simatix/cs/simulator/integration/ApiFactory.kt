package fr.simatix.cs.simulator.integration

import fr.simatix.cs.simulator.adapter16.Ocpp16Adapter
import fr.simatix.cs.simulator.adapter16.impl.RealTransactionRepository
import fr.simatix.cs.simulator.adapter20.Ocpp20Adapter
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api16.DefaultCSMSOperations as DefaultCSMSOperations16
import fr.simatix.cs.simulator.api20.DefaultCSMSOperations as DefaultCSMSOperations20
import fr.simatix.cs.simulator.api16.IOcppCSCallbacks as IOcppCSCallbacks16
import fr.simatix.cs.simulator.api20.IOcppCSCallbacks as IOcppCSCallbacks20
import fr.simatix.cs.simulator.core16.ChargePointOperations as ChargePointOperations16
import fr.simatix.cs.simulator.core20.ChargePointOperations as ChargePointOperations20
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations as RealChargePointOperations16
import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations as RealChargePointOperations20
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.simatix.ev.ocpp.OcppVersion

class ApiFactory {
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

        fun getCSMSApi(settings: Settings, ocppId: String, csApi: CSApi): CSMSApi {
            val transport: Transport =
                createTransport(settings.ocppVersion, ocppId, settings.transportType, settings.target)
            return if (settings.ocppVersion == OcppVersion.OCPP_1_6) {
                Ocpp16Adapter(ocppId, transport, csApi, RealTransactionRepository())
            } else {
                Ocpp20Adapter(ocppId, transport, csApi)
            }
        }

        fun Ocpp16ConnectionToCSMS(chargePointId: String, csmsUrl: String, transportType: TransportEnum, ocppCSCallbacks : IOcppCSCallbacks16): ChargePointOperations16 =
             RealChargePointOperations16(
                chargeStationId = chargePointId,
                client= createTransport(
                    ocppVersion = OcppVersion.OCPP_1_6,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl
                ),
                csmsOperations = DefaultCSMSOperations16(ocppCSCallbacks)
            )

        fun Ocpp20ConnectionToCSMS(chargePointId: String, csmsUrl: String, transportType: TransportEnum, ocppCSCallbacks : IOcppCSCallbacks20): ChargePointOperations20 =
            RealChargePointOperations20(
                chargeStationId = chargePointId,
                client= createTransport(
                    ocppVersion = OcppVersion.OCPP_2_0,
                    ocppId = chargePointId,
                    transportType = transportType,
                    target = csmsUrl
                ),
                csmsOperations = DefaultCSMSOperations20(ocppCSCallbacks)
            )
    }
}