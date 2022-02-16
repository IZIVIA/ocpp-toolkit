package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp20Adapter
import fr.simatix.cs.simulator.api.model.HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.Transport

class Ocpp20AdapterImpl(transport: Transport) : Ocpp20Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric {
        return HeartbeatResponseGeneric(operations.heartbeat().currentTime)
    }
}