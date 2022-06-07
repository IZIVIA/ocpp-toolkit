package com.izivia.ocpp.core20.model.updatefirmware

import com.izivia.ocpp.core20.model.common.StatusInfoType
import com.izivia.ocpp.core20.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType

data class UpdateFirmwareResp(
    val status: UpdateFirmwareStatusEnumType,
    val statusInfo: StatusInfoType? = null
)
