package fr.simatix.cs.simulator.core16.model.remotestart

import fr.simatix.cs.simulator.core16.model.common.enumeration.RemoteStartStopStatus

data class RemoteStartTransactionResp(
    val status: RemoteStartStopStatus
)