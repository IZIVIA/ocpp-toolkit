package com.izivia.ocpp.core15.model.sendlocallist

import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateStatus

data class SendLocalListResp(
    val hash: String? = null,
    val status: UpdateStatus
)