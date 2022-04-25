package fr.simatix.cs.simulator.api.model.setnetworkprofile

import fr.simatix.cs.simulator.api.model.Request

data class SetNetworkProfileReq(
        val configurationSlot : Int,
        val connectionData : NetworkConnectionProfileType
) : Request
