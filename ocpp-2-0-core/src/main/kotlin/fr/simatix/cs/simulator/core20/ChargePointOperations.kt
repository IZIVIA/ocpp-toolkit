package fr.simatix.cs.simulator.core20

import fr.simatix.cs.simulator.core20.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core20.model.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.HeartbeatResp
import fr.simatix.cs.simulator.transport.Transport
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(transport: Transport) = RealChargePointOperations(transport)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     * @return current time of the Central System.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(request: HeartbeatReq): HeartbeatResp
}