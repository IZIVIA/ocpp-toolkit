package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.api.model.HeartbeatRequestGeneric
import fr.simatix.cs.simulator.websocket16.WebsocketClient
import org.junit.jupiter.api.Test

class AdapterTest {
    @Test
    fun `heartbeat request`() {
        val operations = Ocpp16Adapter.newOcpp16AdapterImpl(WebsocketClient("chargePoint2"))
        println(operations.heartbeat(HeartbeatRequestGeneric()))
    }
}