package fr.simatix.cs.simulator.api.model.setdisplaymessage

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType


data class SetDisplayMessageResp(
        val status : DisplayMessageStatusEnumType,
        val statusInfo : StatusInfoType?=null
) : Response
