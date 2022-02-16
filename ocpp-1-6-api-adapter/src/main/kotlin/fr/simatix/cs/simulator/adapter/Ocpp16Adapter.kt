package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.adapter.impl.Ocpp16AdapterImpl
import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core16.Transport
import java.net.ConnectException

interface Ocpp16Adapter : CSMSApi {
    companion object {
        fun newOcpp16AdapterImpl(transport: Transport) = Ocpp16AdapterImpl(transport)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric
}