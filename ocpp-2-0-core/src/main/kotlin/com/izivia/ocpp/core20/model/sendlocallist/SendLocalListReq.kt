package com.izivia.ocpp.core20.model.sendlocallist

import com.izivia.ocpp.core20.model.sendlocallist.enumeration.UpdateEnumType

data class SendLocalListReq(
    val versionNumber: Int,
    val updateType: UpdateEnumType,
    val localAuthorizationList: List<AuthorizationData>? = null
)