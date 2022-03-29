package fr.simatix.cs.simulator.api.model.getreport

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericDeviceModelStatusEnumType

data class GetReportResp(
    val status: GenericDeviceModelStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response