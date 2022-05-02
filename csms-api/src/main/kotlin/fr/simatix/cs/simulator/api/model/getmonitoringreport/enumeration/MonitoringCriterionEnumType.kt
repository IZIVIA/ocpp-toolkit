package fr.simatix.cs.simulator.api.model.getmonitoringreport.enumeration

enum class MonitoringCriterionEnumType(val value: String) {
    ThresholdMonitoring("ThresholdMonitoring"),
    DeltaMonitoring("DeltaMonitoring"),
    PeriodicMonitoring("PeriodicMonitoring")
}