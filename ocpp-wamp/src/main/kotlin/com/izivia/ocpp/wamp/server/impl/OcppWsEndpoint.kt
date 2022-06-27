package com.izivia.ocpp.wamp.server.impl

import org.http4k.core.UriTemplate

class OcppWsEndpoint(path: String) {
    fun extractChargingStationOcppId(uri:String) = uriTemplate.extract(uri)["chargingStationOcppId"]

    val uriTemplate = UriTemplate.from("/$path/{chargingStationOcppId}")
}

