package com.izivia.ocpp.core15.model.remotestart

import com.izivia.ocpp.core15.model.common.enumeration.RemoteStartStopStatus

data class RemoteStartTransactionResp(
    val status: RemoteStartStopStatus
)