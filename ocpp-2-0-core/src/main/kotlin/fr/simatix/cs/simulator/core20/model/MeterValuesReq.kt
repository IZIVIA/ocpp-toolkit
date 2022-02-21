package fr.simatix.cs.simulator.core20.model

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
)