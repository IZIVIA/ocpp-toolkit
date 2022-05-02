package fr.simatix.cs.simulator.core20.model.setdisplaymessage

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.setdisplaymessage.enumeration.DisplayMessageStatusEnumType

data class SetDisplayMessageResp(
        val status : DisplayMessageStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
