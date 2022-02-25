package fr.simatix.cs.simulator.core20.model.common.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class MeasurandEnumType(val value: String) {

    EnergyActiveExportRegister("Energy.Active.Export.Register"),

    EnergyActiveImportRegister("Energy.Active.Import.Register"),

    EnergyReactiveExportRegister("Energy.Reactive.Export.Register"),

    EnergyReactiveImportRegister("Energy.Reactive.Import.Register"),

    EnergyActiveExportInterval("Energy.Active.Export.Interval"),

    EnergyActiveImportInterval("Energy.Active.Import.Interval"),

    EnergyActiveNet("Energy.Active.Net"),

    EnergyReactiveExportInterval("Energy.Reactive.Export.Interval"),

    EnergyReactiveImportInterval("Energy.Reactive.Import.Interval"),

    EnergyReactiveNet("Energy.Reactive.Net"),

    EnergyApparentNet("Energy.Apparent.Net"),

    EnergyApparentImport("Energy.Apparent.Import"),

    EnergyApparentExport("Energy.Apparent.Export"),

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

    SoC("SoC");

    @JsonValue
    fun getEnumValue(): String{
        return value
    }

}
