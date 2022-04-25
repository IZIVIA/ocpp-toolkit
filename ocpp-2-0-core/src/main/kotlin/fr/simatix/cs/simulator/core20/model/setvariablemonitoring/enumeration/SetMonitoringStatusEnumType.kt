package fr.simatix.cs.simulator.core20.model.setvariablemonitoring.enumeration

enum class SetMonitoringStatusEnumType(val value : String) {
    Accepted("Accepted"),
    UnknownComponent("UnknownComponent"),
    UnknownVariable("UnknownVariable"),
    UnsupportedMonitorType("UnsupportedMonitorType"),
    Rejected("Rejected"),
    Duplicate("Duplicate")
}