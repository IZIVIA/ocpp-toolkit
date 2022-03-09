package fr.simatix.cs.simulator.core20

import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp

interface OperationsHandler {

    val reset: (ResetReq) -> ResetResp

}