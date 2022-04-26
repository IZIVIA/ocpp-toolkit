package fr.simatix.cs.simulator.core20.model.customerinformation

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.customerinformation.enumeration.CustomerInformationStatusEnumType

data class CustomerInformationResp(
        val status : CustomerInformationStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
