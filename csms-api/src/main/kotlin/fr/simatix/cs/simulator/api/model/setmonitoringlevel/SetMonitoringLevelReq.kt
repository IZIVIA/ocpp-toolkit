package fr.simatix.cs.simulator.api.model.setmonitoringlevel

import fr.simatix.cs.simulator.api.model.Request

data class SetMonitoringLevelReq(
        val severity : Int
) : Request
