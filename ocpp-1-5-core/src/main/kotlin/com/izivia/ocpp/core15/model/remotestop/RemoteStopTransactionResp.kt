package com.izivia.ocpp.core15.model.remotestop

import com.izivia.ocpp.core15.model.common.enumeration.RemoteStartStopStatus

data class RemoteStopTransactionResp(
    val status: RemoteStartStopStatus
)