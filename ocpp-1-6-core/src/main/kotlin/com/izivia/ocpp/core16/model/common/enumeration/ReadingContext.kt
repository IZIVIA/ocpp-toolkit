package com.izivia.ocpp.core16.model.common.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class ReadingContext(val value: String) {
    InterruptionBegin("Interruption.Begin"),

    InterruptionEnd("Interruption.End"),

    SampleClock("Sample.Clock"),

    SamplePeriodic("Sample.Periodic"),

    TransactionBegin("Transaction.Begin"),

    TransactionEnd("Transaction.End"),

    Trigger("Trigger"),

    Other("Other");

    @JsonValue
    fun getEnumValue(): String = value
}