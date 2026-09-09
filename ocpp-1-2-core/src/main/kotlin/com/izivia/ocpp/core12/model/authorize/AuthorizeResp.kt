package com.izivia.ocpp.core12.model.authorize

import com.izivia.ocpp.core12.model.Response
import com.izivia.ocpp.core12.model.common.IdTagInfo

data class AuthorizeResp(
    val idTagInfo: IdTagInfo
): Response
