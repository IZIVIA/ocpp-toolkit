package fr.simatix.cs.simulator.core20.model.getbasereport

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetBaseReportResp(
    val status: GenericDeviceModelStatusEnumType,
    val statusInfo: StatusInfoType? = null
)