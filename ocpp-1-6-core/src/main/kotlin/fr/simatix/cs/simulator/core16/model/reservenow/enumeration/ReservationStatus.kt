package fr.simatix.cs.simulator.core16.model.reservenow.enumeration

enum class ReservationStatus(val value: String) {
    Accepted("Accepted"),
    Faulted("Faulted"),
    Occupied("Occupied"),
    Rejected("Rejected"),
    Unavailable("Unavailable");
}
