package fr.simatix.cs.simulator.api.model.updatefirmware

import fr.simatix.cs.simulator.api.model.Request

data class UpdateFirmwareReq(
    val retries: Int? = null,
    val retryInterval: Int? = null,
    val requestId: Int,
    val firmware: FirmwareType
): Request
