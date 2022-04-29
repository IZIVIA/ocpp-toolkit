package fr.simatix.cs.simulator.core20.model.publishfirmware

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.common.enumeration.GenericStatusEnumType

data class PublishFirmwareResp(
        val status : GenericStatusEnumType,
        val statusInfo : StatusInfoType?=null
)
