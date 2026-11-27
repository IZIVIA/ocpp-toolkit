package com.izivia.ocpp.wamp.server.impl

import org.http4k.core.UriTemplate

class OcppWsEndpoint(path: String) {
    // extract alone searches the template anywhere in the uri, so matches first requires the whole uri to fit it
    fun extractChargingStationOcppId(uri: String): String? =
        uri.replace("""//""", """/""")
            .takeIf { uriTemplate.matches(it) }
            ?.let { decodeChargingStationOcppId(it) }

    // the id is percent-decoded by URLDecoder, which throws IllegalArgumentException on a malformed escape (%ZZ)
    private fun decodeChargingStationOcppId(uri: String): String? =
        try {
            uriTemplate.extract(uri)["chargingStationOcppId"]
        } catch (_: IllegalArgumentException) {
            null
        }

    val uriTemplate = UriTemplate.from("/$path/{chargingStationOcppId}")
}
