package com.izivia.ocpp.core15.model.getlocallistversion

import com.izivia.ocpp.core15.model.Response

data class GetLocalListVersionResp(
    val listVersion: Int
): Response
