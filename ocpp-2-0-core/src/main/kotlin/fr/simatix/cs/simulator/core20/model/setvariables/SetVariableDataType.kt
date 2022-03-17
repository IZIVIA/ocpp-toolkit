package fr.simatix.cs.simulator.core20.model.setvariables

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.VariableType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AttributeEnumType

data class SetVariableDataType(
    val attributeValue: String,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual
)