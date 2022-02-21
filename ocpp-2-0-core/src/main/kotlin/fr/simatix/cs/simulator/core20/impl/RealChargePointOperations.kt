package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.HeartbeatResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import java.net.ConnectException

class RealChargePointOperations(private val client: Transport) : ChargePointOperations {

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatReq): HeartbeatResp {
        return client.sendMessage("Heartbeat", request)
    }
}
