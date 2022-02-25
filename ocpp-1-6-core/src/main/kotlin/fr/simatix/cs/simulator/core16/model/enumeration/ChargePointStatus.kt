package fr.simatix.cs.simulator.core16.model.enumeration

enum class ChargePointStatus(val value: String) {
    Available("Available"),

    Preparing("Preparing"),

    Charging("Charging"),

    SuspendedEVSE("SuspendedEVSE"),

    SuspendedEV("SuspendedEV"),

    Finishing("Finishing"),

    Reserved("Reserved"),

    Unavailable("Unavailable"),

    Faulted("Faulted");
}