package fr.simatix.cs.simulator.core.impl

import fr.simatix.cs.simulator.core.ChargePointOperations
import fr.simatix.cs.simulator.core.model.HeartbeatRequest
import fr.simatix.cs.simulator.websocket.WebsocketClient
import java.util.*

class ChargePointOperationsImpl(target: String, ocppId: String) : ChargePointOperations {

    private val client = WebsocketClient(target, ocppId)

    override fun heartbeat(): Date {
        val heartbeatRequest = HeartbeatRequest()
        val responsePayload = client.sendMessage("Heartbeat", heartbeatRequest.toString())
        //TODO parse and handle responsePayload
        return Date(0)
    }
}

