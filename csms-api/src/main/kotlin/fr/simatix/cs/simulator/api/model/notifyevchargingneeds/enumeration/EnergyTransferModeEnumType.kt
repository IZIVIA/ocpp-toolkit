package fr.simatix.cs.simulator.api.model.notifyevchargingneeds.enumeration

enum class EnergyTransferModeEnumType(val value: String) {
    DC("DC"),

    AC_single_phase("AC_single_phase"),

    AC_two_phase("AC_two_phase"),

    AC_three_phase("AC_three_phase")
}
