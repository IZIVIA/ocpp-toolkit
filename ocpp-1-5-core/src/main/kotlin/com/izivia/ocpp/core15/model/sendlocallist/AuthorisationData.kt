package com.izivia.ocpp.core15.model.sendlocallist

import com.izivia.ocpp.core15.model.common.IdTagInfo
// AuthorisationData and not AuthorizationData in ocpp specification 1 .5 documentation
data class AuthorisationData(
    val idTag: String,
    val idTagInfo: IdTagInfo? = null
)