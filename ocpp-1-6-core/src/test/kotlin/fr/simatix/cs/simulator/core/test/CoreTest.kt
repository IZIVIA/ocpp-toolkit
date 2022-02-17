package fr.simatix.cs.simulator.core.test

import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import fr.simatix.cs.simulator.websocket16.WebsocketClient
import org.junit.jupiter.api.Test

class CoreTest {
    @Test
    fun `heartbeat request`() {
        val operations = ChargePointOperations.newChargePointOperations(WebsocketClient("chargePoint2"))
        println(operations.heartbeat(HeartbeatRequest()))
    }
}