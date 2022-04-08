package fr.simatix.cs.simulator.api.model.updatefirmware.enumeration

enum class UpdateFirmwareStatusEnumType(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    AcceptedCanceled("AcceptedCanceled"),

    InvalidCertificate("InvalidCertificate"),

    RevokedCertificate("RevokedCertificate");
}