package fr.simatix.cs.simulator.adapter

import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.HeartbeatRequest as HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponse as HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.Transport
import fr.simatix.cs.simulator.core16.model.HeartbeatRequest
import java.net.ConnectException

class Ocpp16Adapter(transport: Transport) : CSMSApi {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric {
        return HeartbeatResponseGeneric(operations.heartbeat(HeartbeatRequest()).currentTime)
    }
}