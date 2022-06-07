package com.izivia.ocpp.api.model.getlocallistversion

import com.izivia.ocpp.api.model.Response

data class GetLocalListVersionResp(
    val versionNumber: Int
) : Response
