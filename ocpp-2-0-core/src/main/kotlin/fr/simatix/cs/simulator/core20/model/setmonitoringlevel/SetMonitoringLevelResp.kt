package fr.simatix.cs.simulator.core20.model.setmonitoringlevel

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericStatusEnumType

data class SetMonitoringLevelResp(
        val status : GenericStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
