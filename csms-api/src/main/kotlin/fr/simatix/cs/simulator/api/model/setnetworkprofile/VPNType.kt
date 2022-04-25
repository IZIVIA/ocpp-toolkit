package fr.simatix.cs.simulator.api.model.setnetworkprofile

import fr.simatix.cs.simulator.api.model.setnetworkprofile.enumeration.VPNEnumType

data class VPNType(
        val server : String,
        val user : String,
        val password : String,
        val key : String,
        val type : VPNEnumType,
        val group : String?=null
)
