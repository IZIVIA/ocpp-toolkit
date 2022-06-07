package com.izivia.ocpp.api.model.setvariablemonitoring.enumeration

enum class SetMonitoringStatusEnumType(val value : String) {
    Accepted("Accepted"),
    UnknownComponent("UnknownComponent"),
    UnknownVariable("UnknownVariable"),
    UnsupportedMonitorType("UnsupportedMonitorType"),
    Rejected("Rejected"),
    Duplicate("Duplicate")
}