package fr.simatix.cs.simulator.api.model.getvariables

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.common.enumeration.AttributeEnumType
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType

data class GetVariableResultType(
    val attributeStatus: GetVariableStatusEnumType,
    val component: ComponentType,
    val variable: VariableType,
    val attributeType: AttributeEnumType = AttributeEnumType.Actual,
    val readonly: Boolean? = null,
    val attributeValue: String? = null,
    val attributeStatusInfo: StatusInfoType? = null
)