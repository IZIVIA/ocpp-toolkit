package fr.simatix.cs.simulator.core20.model.getreport

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetReportResp(
    val status: GenericDeviceModelStatusEnumType,
    val statusInfo: StatusInfoType? = null
)