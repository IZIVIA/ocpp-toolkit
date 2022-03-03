package fr.simatix.cs.simulator.api.model.statusnotification.enumeration

enum class ConnectorStatusEnumType(val value: String) {
    Available("Available"),
    Occupied("Occupied"),
    Reserved("Reserved"),
    Unavailable("Unavailable"),
    Faulted("Faulted"),
}
