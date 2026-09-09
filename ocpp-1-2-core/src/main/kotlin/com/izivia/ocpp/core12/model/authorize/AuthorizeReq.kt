package com.izivia.ocpp.core12.model.authorize

import com.izivia.ocpp.core12.model.Request

data class AuthorizeReq(
    val idTag: String
): Request
