package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.core16.impl.RealCSMSOperations
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.transport.Transport

interface CSMSOperations {

    companion object {
        fun newCSMSOperations(transport: Transport) = RealCSMSOperations(transport)
    }

    fun reset(fn: (ResetReq) -> ResetResp)
}