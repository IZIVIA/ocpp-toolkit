package com.izivia.ocpp.core15.model.remotestart


data class RemoteStartTransactionReq(
    val connectorId: Int? = null,
    val idTag: String
)