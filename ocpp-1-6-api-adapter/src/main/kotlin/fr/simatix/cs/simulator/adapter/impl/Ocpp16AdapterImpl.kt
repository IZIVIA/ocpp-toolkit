package fr.simatix.cs.simulator.adapter.impl

import fr.simatix.cs.simulator.adapter.Ocpp16Adapter
import fr.simatix.cs.simulator.api.model.HeartbeatRequestGeneric
import fr.simatix.cs.simulator.api.model.HeartbeatResponseGeneric
import fr.simatix.cs.simulator.core16.ChargePointOperations
import fr.simatix.cs.simulator.core16.Transport

class Ocpp16AdapterImpl(transport: Transport) : Ocpp16Adapter {

    private val operations = ChargePointOperations.newChargePointOperations(transport)

    override fun heartbeat(request: HeartbeatRequestGeneric): HeartbeatResponseGeneric {
        return HeartbeatResponseGeneric(operations.heartbeat().currentTime)
    }
}