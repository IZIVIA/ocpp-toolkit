package com.izivia.ocpp.core16.model.sendlocallist

import com.izivia.ocpp.core16.model.common.IdTagInfo

data class AuthorizationData(
    val idTag: String,
    val idTagInfo: IdTagInfo? = null
)