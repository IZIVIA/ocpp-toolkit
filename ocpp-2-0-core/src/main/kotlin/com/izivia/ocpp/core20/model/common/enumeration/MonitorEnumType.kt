package com.izivia.ocpp.core20.model.common.enumeration

enum class MonitorEnumType(val value: String) {
    UpperThreshold("UpperThreshold"),

    LowerThreshold("LowerThreshold"),

    Delta("Delta"),

    Periodic("Periodic"),

    PeriodicClockAligne("PeriodicClockAligne")
}