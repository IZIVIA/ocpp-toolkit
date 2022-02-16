package fr.simatix.cs.simulator.core16.impl

import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.Transport
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import fr.simatix.cs.simulator.core16.model.HeartbeatResponse
import java.net.ConnectException

class ChargePointOperationsImpl(private val client: Transport) : ChargePointOperations {

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(): HeartbeatResponse {
        val heartbeatRequest = HeartbeatRequest()
        return client.sendMessage(heartbeatRequest)
    }
}

