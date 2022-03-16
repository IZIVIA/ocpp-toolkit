package fr.simatix.cs.simulator.core20.model.metervalues

import fr.simatix.cs.simulator.core20.model.common.MeterValueType

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
)