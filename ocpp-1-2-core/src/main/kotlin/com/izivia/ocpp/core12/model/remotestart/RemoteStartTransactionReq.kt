package com.izivia.ocpp.core12.model.remotestart

import com.izivia.ocpp.core12.model.Request


data class RemoteStartTransactionReq(
    val idTag: String
): Request
