package fr.simatix.cs.simulator.api.model.enumeration

enum class ReadingContextEnumType(val value: String) {
    InterruptionBegin("Interruption.Begin"),

    InterruptionEnd("Interruption.End"),

    SampleClock("Sample.Clock"),

    SamplePeriodic("Sample.Periodic"),

    TransactionBegin("Transaction.Begin"),

    TransactionEnd("Transaction.End"),

    Trigger("Trigger"),

    Other("Other")
}
