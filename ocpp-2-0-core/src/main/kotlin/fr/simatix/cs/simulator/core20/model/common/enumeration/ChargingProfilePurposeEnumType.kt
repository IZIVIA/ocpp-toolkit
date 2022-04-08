package fr.simatix.cs.simulator.core20.model.common.enumeration

enum class ChargingProfilePurposeEnumType(val value: String) {
    ChargingStationExternalConstraints("ChargingStationExternalConstraints"),

    ChargingStationMaxProfile("ChargingStationMaxProfile"),

    TxDefaultProfile("TxDefaultProfile"),

    TxProfile("TxProfile");
}