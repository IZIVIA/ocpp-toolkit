package fr.simatix.cs.simulator.api.model.customerinformation

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.customerinformation.enumeration.CustomerInformationStatusEnumType

data class CustomerInformationResp(
        val status : CustomerInformationStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
