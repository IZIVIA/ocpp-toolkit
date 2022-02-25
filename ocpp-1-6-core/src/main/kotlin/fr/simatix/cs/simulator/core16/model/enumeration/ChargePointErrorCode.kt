package fr.simatix.cs.simulator.core16.model.enumeration

enum class ChargePointErrorCode(val value: String) {
    ConnectorLockFailure("ConnectorLockFailure"),

    EVCommunicationError("EVCommunicationError"),

    GroundFailure("GroundFailure"),

    HighTemperature("HighTemperature"),

    InternalError("InternalError"),

    LocalListConflict("LocalListConflict"),

    NoError("NoError"),

    OtherError("OtherError"),

    OverCurrentFailure("OverCurrentFailure"),

    PowerMeterFailure("PowerMeterFailure"),

    PowerSwitchFailure("PowerSwitchFailure"),

    ReaderFailure("ReaderFailure"),

    ResetFailure("ResetFailure"),

    UnderVoltage("UnderVoltage"),

    OverVoltage("OverVoltage"),

    WeakSignal("WeakSignal");
}