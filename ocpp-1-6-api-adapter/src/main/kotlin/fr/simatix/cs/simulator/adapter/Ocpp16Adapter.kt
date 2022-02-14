package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.impl.Ocpp16AdapterImpl

interface Ocpp16Adapter {
    companion object {
        fun newOcpp16AdapterImpl(target: String, ocppId: String) = Ocpp16AdapterImpl(target, ocppId)
    }

    fun heartbeat(): String
}