package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.core.ChargePointOperations

class Ocpp20AdapterImpl(target: String, ocppId: String) : Ocpp20Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(target, ocppId)

    override fun heartbeat(): String {
        return operations.heartbeat()
    }
}