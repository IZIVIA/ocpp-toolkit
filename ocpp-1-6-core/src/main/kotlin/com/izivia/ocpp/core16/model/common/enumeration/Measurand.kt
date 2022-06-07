package com.izivia.ocpp.core16.model.common.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class Measurand(val value: String) {
    EnergyActiveExportRegister("Energy.Active.Export.Register"),

    EnergyActiveImportRegister("Energy.Active.Import.Register"),

    EnergyReactiveExportRegister("Energy.Reactive.Export.Register"),

    EnergyReactiveImportRegister("Energy.Reactive.Import.Register"),

    EnergyActiveExportInterval("Energy.Active.Export.Interval"),

    EnergyActiveImportInterval("Energy.Active.Import.Interval"),

    EnergyReactiveExportInterval("Energy.Reactive.Export.Interval"),

    EnergyReactiveImportInterval("Energy.Reactive.Import.Interval"),

    PowerActiveExport("Power.Active.Export"),

    PowerActiveImport("Power.Active.Import"),

    PowerOffered("Power.Offered"),

    PowerReactiveExport("Power.Reactive.Export"),

    PowerReactiveImport("Power.Reactive.Import"),

    PowerFactor("Power.Factor"),

    CurrentImport("Current.Import"),

    CurrentExport("Current.Export"),

    CurrentOffered("Current.Offered"),

    Voltage("Voltage"),

    Frequency("Frequency"),

    Temperature("Temperature"),

    SoC("SoC"),

    RPM("RPM");

    @JsonValue
    fun getEnumValue(): String = value
}