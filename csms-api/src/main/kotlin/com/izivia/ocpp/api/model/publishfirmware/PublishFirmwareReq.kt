package com.izivia.ocpp.api.model.publishfirmware

import com.izivia.ocpp.api.model.Request

data class PublishFirmwareReq(
        val location : String,
        val checksum : String,
        val requestId : Int,
        val retries : Int?=null,
        val retryInterval : Int?=null
) : Request
