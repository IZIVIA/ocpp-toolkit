package fr.simatix.cs.simulator.api.model.transactionevent.enumeration

enum class ChargingStateEnumType(val value: String) {
    Charging("Charging"),
    EVConnected("EVConnected"),
    SuspendedEV("SuspendedEV"),
    SuspendedEVSE("SuspendedEVSE"),
    Idle("Idle")
}
