package com.izivia.ocpp.json

/**
 * Classpath folder holding the JSON schemas of one OCPP version.
 *
 * Every version module ships schemas under the same action names, and the classpath returns the
 * first match when several versions are present, so each module keeps its schemas under its own
 * entry here. Adding a version means adding an entry, which keeps the folders distinct by construction.
 */
enum class OcppSchemaFolder(val path: String) {
    OCPP_1_2("ocpp12"),
    OCPP_1_5("ocpp15"),
    OCPP_1_6("ocpp16"),
    OCPP_2_0("ocpp20")
}
