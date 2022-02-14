package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.impl.Ocpp20AdapterImpl
import java.net.ConnectException

interface Ocpp20Adapter {
    companion object {
        fun newOcpp20AdapterImpl(target: String, ocppId: String) = Ocpp20AdapterImpl(target, ocppId)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(): String
}