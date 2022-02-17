package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.simatix.ev.ocpp.OcppVersion
import org.junit.jupiter.api.Test

class CoreTest {
    @Test
    fun `heartbeat request`() {
        val operations =
            ChargePointOperations.newChargePointOperations(WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6))
        println(operations.heartbeat(HeartbeatRequest()))
    }
}