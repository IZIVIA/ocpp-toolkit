package com.izivia.ocpp.core16.model.sendlocallist

import com.izivia.ocpp.core16.model.sendlocallist.enumeration.UpdateType

data class SendLocalListReq(
    val listVersion: Int,
    val updateType: UpdateType,
    val localAuthorizationList: List<AuthorizationData>? = null
)
