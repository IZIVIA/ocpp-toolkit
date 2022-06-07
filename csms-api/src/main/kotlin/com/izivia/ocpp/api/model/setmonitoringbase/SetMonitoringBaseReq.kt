package com.izivia.ocpp.api.model.setmonitoringbase

import com.izivia.ocpp.api.model.Request
import com.izivia.ocpp.api.model.setmonitoringbase.enumeration.MonitoringBaseEnumType

data class SetMonitoringBaseReq(
     val monitoringBase : MonitoringBaseEnumType
) : Request
