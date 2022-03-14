package fr.simatix.cs.simulator.core16.model.remotestop

import fr.simatix.cs.simulator.core16.model.common.enumeration.RemoteStartStopStatus

data class RemoteStopTransactionResp(
    val status: RemoteStartStopStatus
)