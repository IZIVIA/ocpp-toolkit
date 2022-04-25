package fr.simatix.cs.simulator.api.model.setvariablemonitoring

import fr.simatix.cs.simulator.api.model.Request

data class SetVariableMonitoringReq(
        val setMonitoringData : List<SetMonitoringDataType>
) : Request
