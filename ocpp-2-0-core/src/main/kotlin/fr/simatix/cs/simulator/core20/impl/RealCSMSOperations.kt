package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.receiveMessage

class RealCSMSOperations(private val client: Transport): CSMSOperations {

    override fun reset(fn: (ResetReq) -> ResetResp) {
        client.receiveMessage("reset",fn)
    }
}