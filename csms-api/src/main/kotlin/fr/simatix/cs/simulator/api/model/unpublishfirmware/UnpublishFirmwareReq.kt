package fr.simatix.cs.simulator.api.model.unpublishfirmware

import fr.simatix.cs.simulator.api.model.Request

data class UnpublishFirmwareReq(
        val checksum : String
) : Request
