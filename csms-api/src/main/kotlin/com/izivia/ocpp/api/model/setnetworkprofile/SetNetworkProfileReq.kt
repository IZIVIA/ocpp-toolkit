package com.izivia.ocpp.api.model.setnetworkprofile

import com.izivia.ocpp.api.model.Request

data class SetNetworkProfileReq(
        val configurationSlot : Int,
        val connectionData : NetworkConnectionProfileType
) : Request
