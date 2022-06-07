package com.izivia.ocpp.api.model.updatefirmware

import com.izivia.ocpp.api.model.Response
import com.izivia.ocpp.api.model.common.StatusInfoType
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType

data class UpdateFirmwareResp(
    val status: UpdateFirmwareStatusEnumType,
    val statusInfo: StatusInfoType? = null
): Response
