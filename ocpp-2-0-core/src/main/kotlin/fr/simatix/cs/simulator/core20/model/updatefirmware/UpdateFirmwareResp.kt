package fr.simatix.cs.simulator.core20.model.updatefirmware

import fr.simatix.cs.simulator.core20.model.common.StatusInfoType
import fr.simatix.cs.simulator.core20.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType

data class UpdateFirmwareResp(
    val status: UpdateFirmwareStatusEnumType,
    val statusInfo: StatusInfoType? = null
)
