package fr.simatix.cs.simulator.core20.model

import fr.simatix.cs.simulator.core20.model.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.core20.model.enumeration.ReasonEnumType

data class TransactionType(
    val transactionId: String,
    val chargingState: ChargingStateEnumType? = null,
    val timeSpentCharging: Int? = null,
    val stoppedReason: ReasonEnumType? = null,
    val remoteStartId: Int? = null
)
