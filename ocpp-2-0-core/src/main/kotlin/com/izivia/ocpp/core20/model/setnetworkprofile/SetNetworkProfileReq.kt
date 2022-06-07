package com.izivia.ocpp.core20.model.setnetworkprofile

data class SetNetworkProfileReq(
        val configurationSlot : Int,
        val connectionData : NetworkConnectionProfileType
)
