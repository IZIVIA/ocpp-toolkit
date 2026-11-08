package com.izivia.ocpp.wamp.server.impl

import org.http4k.core.UriTemplate

class OcppWsEndpoint(path: String) {
    // extract alone searches the template anywhere in the uri, and throws when it is not there
    fun extractChargingStationOcppId(uri: String): String? =
        uri.replace("""//""", """/""")
            .takeIf { uriTemplate.matches(it) }
            ?.let { runCatching { uriTemplate.extract(it)["chargingStationOcppId"] }.getOrNull() }

    val uriTemplate = UriTemplate.from("/$path/{chargingStationOcppId}")
}
