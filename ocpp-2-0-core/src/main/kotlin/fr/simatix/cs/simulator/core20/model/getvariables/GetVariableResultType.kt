package fr.simatix.cs.simulator.core20.model.getvariables

import fr.simatix.cs.simulator.core20.model.common.ComponentType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.VariableType
import fr.simatix.cs.simulator.core20.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.core20.model.getvariables.enumeration.GetVariableStatusEnumType

data class GetVariableResultType(
    val attributeStatus: GetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val attributeValue: String? = null,
    val attributeStatusInfo: StatusInfoType? = null
)