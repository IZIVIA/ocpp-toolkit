package com.izivia.ocpp.core20.model.setnetworkprofile

import com.izivia.ocpp.core20.model.setnetworkprofile.enumeration.VPNEnumType

data class VPNType(
        val server : String,
        val user : String,
        val password : String,
        val key : String,
        val type : VPNEnumType,
        val group : String?=null
)
