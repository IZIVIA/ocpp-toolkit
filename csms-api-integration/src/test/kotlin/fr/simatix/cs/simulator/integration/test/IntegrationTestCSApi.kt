package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetEnumType
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetStatusEnumType
import fr.simatix.cs.simulator.integration.ApiFactory
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServer
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.lang.Thread.sleep

class IntegrationTestCSApi {

    private lateinit var server: OcppWampServer
    private val port = 12345

    @BeforeEach
    fun init() {
        server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = "chargePoint2" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
        })

        server.start()
    }

    @Test
    fun `reset 1-6 test`() {

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"
        val csApi = object : CSApi {
            override fun reset(req: ResetReq): ResetResp =
                if (req.type == ResetEnumType.Immediate) {
                    ResetResp(ResetStatusEnumType.Scheduled)
                } else {
                    ResetResp(ResetStatusEnumType.Rejected)
                }
        }
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId,csApi)

        csmsApi.connect()

        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1", "reset", "{\"type\": \"Hard\"}"))
        csmsApi.heartbeat(RequestMetadata(""), HeartbeatReq())
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1", "reset", "{\"type\": \"Soft\"}"))

        csmsApi.close()

        sleep(1000)

        csmsApi.connect()

        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1", "reset", "{\"type\": \"Hard\"}"))

        csmsApi.close()


    }

    @Test
    fun `reset 2-0 test`() {

        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"
        val csApi = object : CSApi {
            override fun reset(req: ResetReq): ResetResp =
                if (req.type == ResetEnumType.Immediate) {
                    ResetResp(ResetStatusEnumType.Scheduled)
                } else {
                    ResetResp(ResetStatusEnumType.Rejected)
                }
        }
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId,csApi)

        csmsApi.connect()

        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "reset", "{\"type\": \"Immediate\"}")
        )
        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "reset", "{\"type\": \"OnIdle\"}")
        )

        csmsApi.close()
    }


    @AfterEach
    fun finalize() {
        server.stop()
    }
}