package fr.simatix.cs.simulator.api.model.reservenow.enumeration

enum class ReserveNowStatusEnumType(val value: String) {
    Accepted("Accepted"),
    Faulted("Faulted"),
    Occupied("Occupied"),
    Rejected("Rejected"),
    Unavailable("Unavailable");
}
