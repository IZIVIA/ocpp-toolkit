package com.izivia.ocpp.integration

import com.izivia.ocpp.adapter16.Ocpp16Adapter
import com.izivia.ocpp.adapter16.impl.RealTransactionRepository
import com.izivia.ocpp.adapter20.Ocpp20Adapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.CSMSApi
import com.izivia.ocpp.api16.DefaultCSMSOperations as DefaultCSMSOperations16
import com.izivia.ocpp.api20.DefaultCSMSOperations as DefaultCSMSOperations20
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.api20.OcppCSCallbacks as OcppCSCallbacks20
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
import com.izivia.ocpp.core16.impl.RealChargePointOperations as RealChargePointOperations16
import com.izivia.ocpp.core20.impl.RealChargePointOperations as RealChargePointOperations20
import com.izivia.ocpp.integration.model.Settings
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.websocket.WebsocketClient
import com.izivia.ocpp.OcppVersion

class ApiFactory {
    companion object {
        private fun createTransport(
            ocppVersion: OcppVersion,
            ocppId: String,
            transportType: TransportEnum,
            target: String
        ): ClientTransport =
            when (transportType) {
                TransportEnum.WEBSOCKET -> WebsocketClient(ocppId, ocppVersion, target)
                TransportEnum.SOAP -> WebsocketClient(ocppId, ocppVersion, target)
            }

        fun getCSMSApi(settings: Settings, ocppId: String, csApi: CSApi): CSMSApi {
            val transport: ClientTransport =
                createTransport(settings.ocppVersion, ocppId, settings.transportType, settings.target)
            return if (settings.ocppVersion == OcppVersion.OCPP_1_6) {
                Ocpp16Adapter(ocppId, transport, csApi, RealTransactionRepository())
            } else {
                Ocpp20Adapter(ocppId, transport, csApi)
            }
        }

        fun Ocpp16ConnectionToCSMS(chargePointId: String, csmsUrl: String, transportType: TransportEnum, ocppCSCallbacks : OcppCSCallbacks16): ChargePointOperations16 =
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

        fun Ocpp20ConnectionToCSMS(chargePointId: String, csmsUrl: String, transportType: TransportEnum, ocppCSCallbacks : OcppCSCallbacks20): ChargePointOperations20 =
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
