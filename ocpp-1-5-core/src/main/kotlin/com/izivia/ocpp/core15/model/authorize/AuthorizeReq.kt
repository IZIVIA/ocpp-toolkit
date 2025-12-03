package com.izivia.ocpp.core15.model.authorize

import com.izivia.ocpp.core15.model.Request

data class AuthorizeReq(
    val idTag: String
): Request
