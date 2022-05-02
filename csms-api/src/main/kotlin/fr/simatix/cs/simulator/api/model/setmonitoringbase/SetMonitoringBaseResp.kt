package fr.simatix.cs.simulator.api.model.setmonitoringbase

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.common.enumeration.GenericDeviceModelStatusEnumType

data class SetMonitoringBaseResp(
        val status : GenericDeviceModelStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
