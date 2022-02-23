package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.CoreExecution
import fr.simatix.cs.simulator.core16.model.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.HeartbeatResp
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
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): CoreExecution<HeartbeatResp>
}