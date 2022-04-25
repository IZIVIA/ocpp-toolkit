package fr.simatix.cs.simulator.api.model.setnetworkprofile

import fr.simatix.cs.simulator.api.model.setnetworkprofile.enumeration.APNAuthenticationEnumType

data class APNType(
        val apn : String,
        val apnAuthentication : APNAuthenticationEnumType,
        val apnUserName : String?=null,
        val apnPassword : String?=null,
        val simPin : Int?=null,
        val preferredNetwork : String?=null,
        val useOnlyPreferredNetwork : Boolean?=false
)
