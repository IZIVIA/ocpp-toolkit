package com.izivia.ocpp.api.model.sendlocallist

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.sendlocallist.enumeration.UpdateEnumType

data class SendLocalListReq(
    val versionNumber: Int,
    val updateType: UpdateEnumType,
    val localAuthorizationList: List<AuthorizationData>? = null
): Request