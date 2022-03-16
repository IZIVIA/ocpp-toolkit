package fr.simatix.cs.simulator.core16.model.metervalues

import fr.simatix.cs.simulator.core16.model.common.MeterValue

data class MeterValuesReq(
    val connectorId: Int,
    val meterValue: List<MeterValue>,
    val transactionId: Int? = null
)