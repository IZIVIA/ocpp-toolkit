package com.izivia.ocpp.core15.model.statusnotification.enumeration

enum class ChargePointErrorCode(val value: String) {
    ConnectorLockFailure("ConnectorLockFailure"),

    GroundFailure("GroundFailure"),

    HighTemperature("HighTemperature"),

    Mode3Error("Mode3Error"),

    NoError("NoError"),

    OtherError("OtherError"),

    OverCurrentFailure("OverCurrentFailure"),

    PowerMeterFailure("PowerMeterFailure"),

    PowerSwitchFailure("PowerSwitchFailure"),

    ReaderFailure("ReaderFailure"),

    ResetFailure("ResetFailure"),

    UnderVoltage("UnderVoltage"),

    WeakSignal("WeakSignal");
}