package fr.simatix.cs.simulator.core16.model.sendlocallist.enumeration

enum class UpdateStatus(val value: String) {
    Accepted("Accepted"),

    Failed("Failed"),

    NotSupported("NotSupported"),

    VersionMismatch("VersionMismatch")
}
