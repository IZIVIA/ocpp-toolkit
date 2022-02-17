package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.HeartbeatRequest as HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponse as HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.Transport
import fr.simatix.cs.simulator.core20.model.HeartbeatRequest
import java.net.ConnectException

class Ocpp20Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric {
        val time =operations.heartbeat(HeartbeatRequest())
        return HeartbeatResponseGeneric(time.currentTime)
    }
}