package fr.simatix.cs.simulator.api.model.transactionevent.enumeration

enum class TriggerReasonEnumType(val value: String) {
    Authorized("Authorized"),
    CablePluggedIn("CablePluggedIn"),
    ChargingRateChanged("ChargingRateChanged"),
    ChargingStateChanged("ChargingStateChanged"),
    Deauthorized("Deauthorized"),
    EnergyLimitReached("EnergyLimitReached"),
    EVCommunicationLost("EVCommunicationLost"),
    EVConnectTimeout("EVConnectTimeout"),
    MeterValueClock("MeterValueClock"),
    MeterValuePeriodic("MeterValuePeriodic"),
    TimeLimitReached("TimeLimitReached"),
    Trigger("Trigger"),
    UnlockCommand("UnlockCommand"),
    StopAuthorized("StopAuthorized"),
    EVDeparted("EVDeparted"),
    EVDetected("EVDetected"),
    RemoteStop("RemoteStop"),
    RemoteStart("RemoteStart"),
    AbnormalCondition("AbnormalCondition"),
    SignedDataReceived("SignedDataReceived"),
    ResetCommand("ResetCommand")
}
