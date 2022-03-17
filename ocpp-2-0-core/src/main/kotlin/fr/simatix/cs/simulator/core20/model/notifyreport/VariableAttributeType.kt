package fr.simatix.cs.simulator.core20.model.notifyreport

import fr.simatix.cs.simulator.core20.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.core20.model.notifyreport.enumeration.MutabilityEnumType

data class VariableAttributeType(
    val value: String? = null,
    val type: AttributeEnumType = AttributeEnumType.Actual,
    val constant: Boolean = false,
    val mutability: MutabilityEnumType = MutabilityEnumType.ReadWrite,
    val persistent: Boolean = false
)