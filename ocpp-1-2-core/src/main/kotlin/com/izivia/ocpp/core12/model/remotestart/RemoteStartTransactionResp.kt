package com.izivia.ocpp.core12.model.remotestart

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus

data class RemoteStartTransactionResp(
    val status: RemoteStartStopStatus
): Response
