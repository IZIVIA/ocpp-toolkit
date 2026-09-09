package com.izivia.ocpp.core12.model.statusnotification.enumeration

enum class ChargePointErrorCode(val value: String) {
    ConnectorLockFailure("ConnectorLockFailure"),
    HighTemperature("HighTemperature"),
    Mode3Error("Mode3Error"),
    NoError("NoError"),
    PowerMeterFailure("PowerMeterFailure"),
    PowerSwitchFailure("PowerSwitchFailure"),
    ReaderFailure("ReaderFailure"),
    ResetFailure("ResetFailure");
}
