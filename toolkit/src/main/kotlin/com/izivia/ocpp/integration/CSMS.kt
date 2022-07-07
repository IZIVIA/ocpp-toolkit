package com.izivia.ocpp.integration

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.operation.information.CSCallbacks
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.OcppVersion.*
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.core15.ChargePointOperations as ChargePointOperations15
import com.izivia.ocpp.core15.impl.RealCSMSOperations as RealCSMSOperations15
import com.izivia.ocpp.core16.CSMSOperations as CSMSOperations16
import com.izivia.ocpp.core15.CSMSOperations as CSMSOperations15
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
import com.izivia.ocpp.core16.impl.RealCSMSOperations as RealCSMSOperations16
import com.izivia.ocpp.core20.CSMSOperations as CSMSOperations20
import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
import com.izivia.ocpp.core20.impl.RealCSMSOperations as RealCSMSOperations20


class CSMS(
    private val transports: Map<ServerTransport, Set<OcppVersion>>,
    csmsApis: Set<CSMSCallbacks>,
    fn: (String) -> ChargingStationConfig
) {

    private val transportByVersion: Map<OcppVersion, Set<ServerTransport>> =
        mutableMapOf<OcppVersion, Set<ServerTransport>>().also { map ->
            transports.forEach { transport ->
                transport.value.forEach {
                    map[it] = ((map[it]?.toList() ?: listOf()) + transport.key).toSet()
                }
            }
        }

    private val csApi: Map<CsApiType, CSCallbacks> = mutableMapOf<CsApiType, CSCallbacks>().also { csApis ->
        csmsApis.forEach {
            when (it) {
                is ChargePointOperations16 -> transportByVersion[OCPP_1_6]?.let { transports16 ->
                    csApis[CsApiType.OcppCsApiType(OCPP_1_6)] = RealCSMSOperations16(transports16, fn, it)
                }
                is ChargePointOperations15 -> transportByVersion[OCPP_1_5]?.let { transports15 ->
                    csApis[CsApiType.OcppCsApiType(OCPP_1_5)] = RealCSMSOperations15(transports15, fn, it)
                }
                is ChargePointOperations20 -> transportByVersion[OCPP_2_0]?.let { transports20 ->
                    csApis[CsApiType.OcppCsApiType(OCPP_2_0)] = RealCSMSOperations20(transports20, fn, it)
                }
                else -> throw IllegalStateException("Unknow csms callbacks")
            }
        }
    }

    fun start() {
        transports.forEach { server -> server.key.start() }
    }

    fun stop() {
        transports.forEach { server -> server.key.stop() }
    }

    fun getCSApiGeneric(): CSApi =
        csApi[CsApiType.GenericCsApiType()] as CSApi? ?: throw IllegalStateException("No generic api is available")

    fun getCSApi15(): CSMSOperations15 =
        csApi[CsApiType.OcppCsApiType(OCPP_1_5)] as CSMSOperations15? ?: throw IllegalStateException("No 1.5 api is available")

    fun getCSApi16(): CSMSOperations16 =
        csApi[CsApiType.OcppCsApiType(OCPP_1_6)] as CSMSOperations16? ?: throw IllegalStateException("No 1.6 api is available")

    fun getCSApi20(): CSMSOperations20 =
        csApi[CsApiType.OcppCsApiType(OCPP_2_0)] as CSMSOperations20? ?: throw IllegalStateException("No 2.0.1 api is available")

}

sealed class CsApiType {
    class GenericCsApiType: CsApiType()
    data class OcppCsApiType(val ocppVersion:OcppVersion): CsApiType()
}
