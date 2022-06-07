package com.izivia.ocpp.api.model.unpublishfirmware

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.unpublishfirmware.enumeration.UnpublishFirmwareStatusEnumType

data class UnpublishFirmwareResp(
        val status : UnpublishFirmwareStatusEnumType
) : Response
