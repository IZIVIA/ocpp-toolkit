package fr.simatix.cs.simulator.core20.model.enumeration

enum class ChargingStateEnumType(val value: String) {
    Charging("Charging"),
    EVConnected("EVConnected"),
    SuspendedEV("SuspendedEV"),
    SuspendedEVSE("SuspendedEVSE"),
    Idle("Idle")
}
