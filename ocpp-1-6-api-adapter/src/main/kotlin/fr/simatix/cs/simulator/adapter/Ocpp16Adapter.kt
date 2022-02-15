package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.impl.Ocpp16AdapterImpl
import java.net.ConnectException

interface Ocpp16Adapter {
    companion object {
        fun newOcpp16AdapterImpl(ocppId: String) = Ocpp16AdapterImpl(ocppId)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(): String
}