package fr.simatix.cs.simulator.core

import fr.simatix.cs.simulator.core.impl.ChargePointOperationsImpl
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(ocppId: String) = ChargePointOperationsImpl(ocppId)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     * @return current time of the Central System.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(): String
}