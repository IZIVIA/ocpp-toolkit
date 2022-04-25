package fr.simatix.cs.simulator.core20.model.setnetworkprofile

import fr.simatix.cs.simulator.core20.model.setnetworkprofile.enumeration.OCPPInterfaceEnumType
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.enumeration.OCPPTransportEnumType
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.enumeration.OCPPVersionEnumType

data class NetworkConnectionProfileType(
        val ocppVersion : OCPPVersionEnumType,
        val ocppTransport : OCPPTransportEnumType,
        val ocppCsmsUrl : String,
        val messageTimeout : Int,
        val securityProfile : Int,
        val ocppInterface: OCPPInterfaceEnumType,
        val vpn : VPNType?=null,
        val apn : APNType?=null
)
