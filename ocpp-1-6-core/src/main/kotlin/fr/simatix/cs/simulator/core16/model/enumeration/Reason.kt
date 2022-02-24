package fr.simatix.cs.simulator.core16.model.enumeration

enum class Reason(val value: String) {
    EmergencyStop("EmergencyStop"),

    EVDisconnected("EVDisconnected"),

    HardReset("HardReset"),

    Local("Local"),

    Other("Other"),

    PowerLoss("PowerLoss"),

    Reboot("Reboot"),

    Remote("Remote"),

    SoftReset("SoftReset"),

    UnlockCommand("UnlockCommand"),

    DeAuthorized("DeAuthorized");
}