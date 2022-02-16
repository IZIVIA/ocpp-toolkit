package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.impl.Ocpp20AdapterImpl
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core20.Transport
import java.net.ConnectException

interface Ocpp20Adapter : CSMSApi {
    companion object {
        fun newOcpp20AdapterImpl(transport: Transport) = Ocpp20AdapterImpl(transport)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric
}