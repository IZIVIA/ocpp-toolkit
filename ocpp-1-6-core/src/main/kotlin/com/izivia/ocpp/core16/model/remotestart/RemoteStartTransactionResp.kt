package com.izivia.ocpp.core16.model.remotestart

import com.izivia.ocpp.core16.model.common.enumeration.RemoteStartStopStatus

data class RemoteStartTransactionResp(
    val status: RemoteStartStopStatus
)