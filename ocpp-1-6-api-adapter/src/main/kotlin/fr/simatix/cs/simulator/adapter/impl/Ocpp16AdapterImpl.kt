package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.core.ChargePointOperations

class Ocpp16AdapterImpl(target: String, ocppId: String) : Ocpp16Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(target, ocppId)

    override fun heartbeat(): String {
        return operations.heartbeat()
    }
}