package fr.simatix.cs.simulator.api.model.reset.enumeration

enum class ResetEnumType(val value: String) {
    Immediate("Immediate"), // Hard

    OnIdle("OnIdle"); // Soft
}