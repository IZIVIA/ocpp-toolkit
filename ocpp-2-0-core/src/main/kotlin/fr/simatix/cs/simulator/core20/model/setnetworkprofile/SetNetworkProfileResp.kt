package fr.simatix.cs.simulator.core20.model.setnetworkprofile

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType

data class SetNetworkProfileResp(
        val status : SetNetworkProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
