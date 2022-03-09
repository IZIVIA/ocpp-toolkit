package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp

interface OperationsHandler {

    val reset: (ResetReq) -> ResetResp

}