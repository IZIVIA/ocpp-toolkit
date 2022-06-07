package com.izivia.ocpp.api.model.setdisplaymessage

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.common.MessageInfoType

data class SetDisplayMessageReq(
        val message : MessageInfoType
) : Request
