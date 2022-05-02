package fr.simatix.cs.simulator.api.model.setmonitoringbase

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.setmonitoringbase.enumeration.MonitoringBaseEnumType

data class SetMonitoringBaseReq(
     val monitoringBase : MonitoringBaseEnumType
) : Request
