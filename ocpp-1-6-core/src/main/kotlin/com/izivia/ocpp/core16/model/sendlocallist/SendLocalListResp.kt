package com.izivia.ocpp.core16.model.sendlocallist

import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateStatus

data class SendLocalListResp(
    val status: UpdateStatus
)