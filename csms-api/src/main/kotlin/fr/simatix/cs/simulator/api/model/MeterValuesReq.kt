package fr.simatix.cs.simulator.api.model

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
)