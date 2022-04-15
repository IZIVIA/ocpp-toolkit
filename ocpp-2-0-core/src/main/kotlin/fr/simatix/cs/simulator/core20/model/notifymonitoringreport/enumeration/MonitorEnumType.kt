package fr.simatix.cs.simulator.core20.model.notifymonitoringreport.enumeration

enum class MonitorEnumType(val value: String) {
    UpperThreshold("UpperThreshold"),

    LowerThreshold("LowerThreshold"),

    Delta("Delta"),

    Periodic("Periodic"),

    PeriodicClockAligne("PeriodicClockAligne")
}