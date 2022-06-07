package com.izivia.ocpp.api.model.unpublishfirmware

import com.izivia.ocpp.api.model.Request

data class UnpublishFirmwareReq(
        val checksum : String
) : Request
