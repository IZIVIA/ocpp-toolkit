package com.izivia.ocpp.core15.model.common.enumeration

enum class ReadingContext(val value: String) {
    InterruptionBegin("Interruption.Begin"),

    InterruptionEnd("Interruption.End"),

    SampleClock("Sample.Clock"),

    SamplePeriodic("Sample.Periodic"),

    TransactionBegin("Transaction.Begin"),

    TransactionEnd("Transaction.End");

    fun getEnumValue(): String = value
}
