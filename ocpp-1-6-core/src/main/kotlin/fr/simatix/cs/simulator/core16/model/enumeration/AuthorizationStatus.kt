package fr.simatix.cs.simulator.core16.model.enumeration

enum class AuthorizationStatus(val value: String) {
    Accepted("Accepted"),

    Blocked("Blocked"),

    Expired("Expired"),

    Invalid("Invalid"),

    ConcurrentTx("ConcurrentTx");
}