package fr.simatix.cs.simulator.core20.model.setnetworkprofile

data class SetNetworkProfileReq(
        val configurationSlot : Int,
        val connectionData : NetworkConnectionProfileType
)
