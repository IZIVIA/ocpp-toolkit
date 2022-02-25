package fr.simatix.cs.simulator.api.model.metervalues

import fr.simatix.cs.simulator.api.model.common.MeterValueType

data class MeterValuesReq(
    val evseId: Int,
    val meterValue: List<MeterValueType>
)