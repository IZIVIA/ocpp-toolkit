package com.izivia.ocpp.api.model.common.enumeration

enum class ChargingProfilePurposeEnumType(val value: String) {
    ChargingStationExternalConstraints("ChargingStationExternalConstraints"),

    ChargingStationMaxProfile("ChargingStationMaxProfile"),

    TxDefaultProfile("TxDefaultProfile"),

    TxProfile("TxProfile");
}