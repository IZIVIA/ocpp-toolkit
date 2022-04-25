package fr.simatix.cs.simulator.api.model.setnetworkprofile

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.setnetworkprofile.enumeration.SetNetworkProfileStatusEnumType


data class SetNetworkProfileResp(
        val status : SetNetworkProfileStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
