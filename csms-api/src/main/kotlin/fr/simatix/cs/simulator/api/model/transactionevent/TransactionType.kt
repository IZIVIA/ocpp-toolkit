package fr.simatix.cs.simulator.api.model.transactionevent

import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ReasonEnumType

data class TransactionType(
    val transactionId: String,
    val chargingState: ChargingStateEnumType? = null,
    val timeSpentCharging: Int? = null,
    val stoppedReason: ReasonEnumType? = null,
    val remoteStartId: Int? = null
)
