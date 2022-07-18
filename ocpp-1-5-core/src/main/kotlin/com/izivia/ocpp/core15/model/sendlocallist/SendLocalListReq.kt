package com.izivia.ocpp.core15.model.sendlocallist

import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateType

data class SendLocalListReq(
    val hash: String? = null,
    val listVersion: Int,
    val localAuthorizationList: List<AuthorisationData>? = null,
    val updateType: UpdateType
)
