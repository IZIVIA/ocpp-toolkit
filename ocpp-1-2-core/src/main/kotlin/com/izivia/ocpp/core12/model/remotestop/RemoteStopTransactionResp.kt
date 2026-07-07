package com.izivia.ocpp.core12.model.remotestop

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus

data class RemoteStopTransactionResp(
    val status: RemoteStartStopStatus
): Response
