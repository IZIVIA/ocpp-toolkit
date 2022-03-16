package fr.simatix.cs.simulator.core20.model.notifyreport

import fr.simatix.cs.simulator.core20.model.notifyreport.enumeration.DataEnumType

data class VariableCharacteristicsType(
    val dataType: DataEnumType,
    val supportsMonitoring: Boolean,
    val unit: String? = null,
    val minLimit: Double? = null,
    val maxLimit: Double? = null,
    val valuesList: String? = null
)