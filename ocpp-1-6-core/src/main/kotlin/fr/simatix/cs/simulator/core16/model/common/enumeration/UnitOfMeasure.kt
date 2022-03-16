package fr.simatix.cs.simulator.core16.model.common.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class UnitOfMeasure(val value: String) {
    Wh("Wh"),

    kWh("kWh"),

    varh("varh"),

    kvarh("kvarh"),

    W("W"),

    kW("kW"),

    VA("VA"),

    kVA("kVA"),

    Var("var"),

    kvar("kvar"),

    A("A"),

    V("V"),

    K("K"),

    Celcius("Celcius"),

    Celsius("Celsius"),

    Fahrenheit("Fahrenheit"),

    Percent("Percent");

    @JsonValue
    fun getEnumValue(): String = value
}