package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.core.ChargePointOperations

class Ocpp20AdapterImpl(ocppId: String) : Ocpp20Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(ocppId)

    override fun heartbeat(): String {
        return operations.heartbeat()
    }
}