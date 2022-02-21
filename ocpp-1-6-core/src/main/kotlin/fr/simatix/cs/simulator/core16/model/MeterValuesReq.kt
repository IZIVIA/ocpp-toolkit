package fr.simatix.cs.simulator.core16.model

data class MeterValuesReq(
    val connectorId: Int,
    val meterValue: List<MeterValue>,
    val transactionId: Int? = null
)