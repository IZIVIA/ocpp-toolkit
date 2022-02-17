package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.core16.impl.ChargePointOperationsImpl
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import fr.simatix.cs.simulator.core16.model.HeartbeatResponse
import fr.simatix.cs.simulator.transport.Transport
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(transport: Transport) = ChargePointOperationsImpl(transport)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     * @return current time of the Central System.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(request: HeartbeatRequest): HeartbeatResponse
}