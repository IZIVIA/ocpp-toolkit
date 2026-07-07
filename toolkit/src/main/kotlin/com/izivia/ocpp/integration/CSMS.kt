package com.izivia.ocpp.integration

import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.http.OcppSoapServerTransport
import com.izivia.ocpp.operation.information.CSCallbacks
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.OcppVersion.*
import com.izivia.ocpp.transport.ServerTransport
import com.izivia.ocpp.wamp.server.impl.Undertow
import com.izivia.ocpp.websocket.WebsocketServer
import org.http4k.routing.routes
import org.http4k.routing.websockets
import org.http4k.core.PolyHandler
import org.http4k.server.asServer
import org.slf4j.LoggerFactory
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
    transports: Map<ServerTransport, Pair<Int, Set<OcppVersion>>>,
    csmsApis: Set<CSMSCallbacks>,
    fn: (String) -> ChargingStationConfig
) {
    companion object {
        private val logger = LoggerFactory.getLogger(CSMS::class.java)
    }

    private val serverByVersion: Map<OcppVersion, Set<ServerTransport>> =
        transports
            .flatMap { (server, pair) -> pair.second.map { it to server } }
            .groupBy({ it.first }, { it.second })
            .mapValues { (_, servers) -> servers.toSet() }

    private val csApi: Map<CsApiType, CSCallbacks> = csmsApis.associate { csmsApi ->
        when (csmsApi) {
            is ChargePointOperations16 -> serverByVersion[OCPP_1_6]?.let { transports16 ->
                CsApiType.OcppCsApiType(OCPP_1_6) to RealCSMSOperations16(transports16, fn, csmsApi)
            }
            is ChargePointOperations15 -> serverByVersion[OCPP_1_5]?.let { transports15 ->
                CsApiType.OcppCsApiType(OCPP_1_5) to RealCSMSOperations15(transports15, fn, csmsApi)
            }
            is ChargePointOperations20 -> serverByVersion[OCPP_2_0]?.let { transports20 ->
                CsApiType.OcppCsApiType(OCPP_2_0) to RealCSMSOperations20(transports20, fn, csmsApi)
            }
            else -> error("Unknown csms callbacks")
        } ?: error("No transport found for csmsApi ${csmsApi::class.simpleName}")
    }

    private val servers = transports
        .map { t -> t.value.first to Pair(t.key, t.value.second) }
        .groupBy { it.first }
        .mapValues { (_, servers) -> servers.map { it.second } }
        .map { t ->
            val port = t.key
            val wsServerConfigs = t.value.filter { it.first is WebsocketServer }.map { (it.first as WebsocketServer).serverConfig to it.second}
            val soapServerConfigs = t.value.filter { it.first is OcppSoapServerTransport }.map { (it.first as OcppSoapServerTransport).serverConfig to it.second }
            // http4k 6 throws "No routes added!" when routes()/websockets() get an empty
            // list, so only build a handler for a protocol that actually has routes and
            // leave the other side null (PolyHandler and the custom Undertow accept null).
            val app = PolyHandler(
                http = soapServerConfigs.map { it.first.handler }
                    .takeIf { it.isNotEmpty() }
                    ?.let { routes(it) },
                ws = wsServerConfigs.map { it.first.handler }
                    .takeIf { it.isNotEmpty() }
                    ?.let { websockets(*it.toTypedArray()) },
            )
            app.asServer(
                Undertow(
                    port,
                    enableHttp2 = wsServerConfigs.isNotEmpty(),
                    acceptWebSocketPredicate = { exch ->
                        wsServerConfigs.any { it.first.acceptWebSocketPredicate(exch) }
                    },
                    wsSubprotocols = wsServerConfigs.flatMap { c -> c.first.wsSubprotocols }.toSet(),
                )
            ) to wsServerConfigs.flatMap { it.second } + soapServerConfigs.flatMap { it.second }
        }

    fun start() {
        servers.forEach {
            server -> server.first.start()
            logger.info(
                "starting on port ${server.first.port()} -- ocpp versions=${server.second}"
            )
        }
    }

    fun stop() {
        servers.forEach {
            server -> server.first.stop()
            logger.info("stopping on port ${server.first.port()}")
        }
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
