package fr.simatix.cs.simulator.core16.impl

import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import fr.simatix.cs.simulator.core16.model.HeartbeatResponse
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import java.net.ConnectException

class ChargePointOperationsImpl(private val client: Transport) : ChargePointOperations {

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatRequest): HeartbeatResponse {
        return client.sendMessage("Heartbeat", request)
    }
}

