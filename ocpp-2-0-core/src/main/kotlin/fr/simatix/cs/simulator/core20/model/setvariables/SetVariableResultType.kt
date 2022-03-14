package fr.simatix.cs.simulator.core20.model.setvariables

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.VariableType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.core20.model.setvariables.enumeration.SetVariableStatusEnumType

data class SetVariableResultType(
    val attributeStatus: SetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeStatusInfo: StatusInfoType? = null
)