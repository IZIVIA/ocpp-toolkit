package fr.simatix.cs.simulator

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.api.model.HeartbeatRequest
import fr.simatix.cs.simulator.websocket.WebsocketClient
import io.simatix.ev.ocpp.OcppVersion
import org.junit.jupiter.api.Test

class AdapterTest {
    @Test
    fun `heartbeat request`() {
        val operations = Ocpp16Adapter(WebsocketClient("chargePoint2", OcppVersion.OCPP_1_6))
        println(operations.heartbeat(HeartbeatRequest()))
    }
}