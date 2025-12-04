package com.izivia.ocpp.core15.model.remotestart

import com.izivia.ocpp.core15.model.Request


data class RemoteStartTransactionReq(
    val connectorId: Int? = null,
    val idTag: String
): Request
