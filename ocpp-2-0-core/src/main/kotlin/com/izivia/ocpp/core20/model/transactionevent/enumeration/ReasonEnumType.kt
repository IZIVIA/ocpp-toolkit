package com.izivia.ocpp.core20.model.transactionevent.enumeration

enum class ReasonEnumType(val value: String) {
    DeAuthorized("DeAuthorized"),
    EmergencyStop("EmergencyStop"),
    EnergyLimitReached("EnergyLimitReached"),
    EVDisconnected("EVDisconnected"),
    GroundFault("GroundFault"),
    ImmediateReset("ImmediateReset"),
    Local("Local"),
    LocalOutOfCredit("LocalOutOfCredit"),
    MasterPass("MasterPass"),
    Other("Other"),
    OvercurrentFault("OvercurrentFault"),
    PowerLoss("PowerLoss"),
    PowerQuality("PowerQuality"),
    Reboot("Reboot"),
    Remote("Remote"),
    SOCLimitReached("SOCLimitReached"),
    StoppedByEV("StoppedByEV"),
    TimeLimitReached("TimeLimitReached"),
    Timeout("Timeout")
}
