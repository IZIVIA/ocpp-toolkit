package fr.simatix.cs.simulator.api.model.unpublishfirmware

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType

data class UnpublishFirmwareResp(
        val status : UnpublishFirmwareStatusEnumType
) : Response
