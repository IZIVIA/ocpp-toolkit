package com.izivia.ocpp

typealias CSOcppId = String

enum class OcppVersion(val subprotocol:String) {
    OCPP_1_5("ocpp1.5"), OCPP_1_6("ocpp1.6"), OCPP_2_0("ocpp2.0")
}
