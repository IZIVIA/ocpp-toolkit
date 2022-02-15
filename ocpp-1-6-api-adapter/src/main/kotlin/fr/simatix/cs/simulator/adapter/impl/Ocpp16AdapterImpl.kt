package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.core.ChargePointOperations

class Ocpp16AdapterImpl(ocppId: String) : Ocpp16Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(ocppId)

    override fun heartbeat(): String {
        return operations.heartbeat()
    }
}