package fr.simatix.cs.simulator.api.model.setvariables

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType

data class SetVariableResultType(
    val attributeStatus: SetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeStatusInfo: StatusInfoType? = null
)