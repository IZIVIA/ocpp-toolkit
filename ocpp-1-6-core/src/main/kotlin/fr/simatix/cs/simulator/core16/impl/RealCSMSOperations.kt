package fr.simatix.cs.simulator.core16.impl

import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage

class RealCSMSOperations(private val client: Transport): CSMSOperations {

    override fun reset(fn: (ResetReq) -> ResetResp) {
        client.receiveMessage("reset",fn)
    }
}