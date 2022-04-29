package fr.simatix.cs.simulator.api.model.publishfirmware

import fr.simatix.cs.simulator.api.model.Request

data class PublishFirmwareReq(
        val location : String,
        val checksum : String,
        val requestId : Int,
        val retries : Int?=null,
        val retryInterval : Int?=null
) : Request
