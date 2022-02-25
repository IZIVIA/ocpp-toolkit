package fr.simatix.cs.simulator.core20.model.enumeration

enum class ConnectorStatusEnumType(val value: String) {
    Available("Available"),
    Occupied("Occupied"),
    Reserved("Reserved"),
    Unavailable("Unavailable"),
    Faulted("Faulted"),
}
