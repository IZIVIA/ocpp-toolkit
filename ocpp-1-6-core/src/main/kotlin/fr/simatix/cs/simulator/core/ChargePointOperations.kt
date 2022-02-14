package fr.simatix.cs.simulator.core

import fr.simatix.cs.simulator.core.impl.ChargePointOperationsImpl

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(target: String, ocppId: String) = ChargePointOperationsImpl(target, ocppId)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     * @return current time of the Central System.
     */
    fun heartbeat(): String
}