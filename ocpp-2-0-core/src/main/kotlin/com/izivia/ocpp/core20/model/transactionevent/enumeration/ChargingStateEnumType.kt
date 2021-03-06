package com.izivia.ocpp.core20.model.transactionevent.enumeration

enum class ChargingStateEnumType(val value: String) {
    Charging("Charging"),
    EVConnected("EVConnected"),
    SuspendedEV("SuspendedEV"),
    SuspendedEVSE("SuspendedEVSE"),
    Idle("Idle")
}
