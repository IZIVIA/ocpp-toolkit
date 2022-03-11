package fr.simatix.cs.simulator.api.model.remotestart.enumeration

enum class ChargingProfilePurposeEnumType(val value: String) {
    ChargingStationExternalConstraints("ChargingStationExternalConstraints"),

    ChargingStationMaxProfile("ChargingStationMaxProfile"),

    TxDefaultProfile("TxDefaultProfile"),

    TxProfile("TxProfile");
}