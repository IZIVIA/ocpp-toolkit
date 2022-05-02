package fr.simatix.cs.simulator.core20.model.getmonitoringreport

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetMonitoringReportResp(
    val status : GenericDeviceModelStatusEnumType,
    val statusInfo : StatusInfoType?=null
)
