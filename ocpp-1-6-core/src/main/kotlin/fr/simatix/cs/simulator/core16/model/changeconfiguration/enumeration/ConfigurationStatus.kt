package fr.simatix.cs.simulator.core16.model.changeconfiguration.enumeration

enum class ConfigurationStatus(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    RebootRequired("RebootRequired"),

    NotSupported("NotSupported");
}