package fr.simatix.cs.simulator.api.model.updatefirmware

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.common.StatusInfoType
import fr.simatix.cs.simulator.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType

data class UpdateFirmwareResp(
    val status: UpdateFirmwareStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
