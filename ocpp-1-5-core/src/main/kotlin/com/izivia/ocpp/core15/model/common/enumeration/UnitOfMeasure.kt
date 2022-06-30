package com.izivia.ocpp.core15.model.common.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class UnitOfMeasure(val value: String) {
    Wh("Wh"),

    kWh("kWh"),

    varh("varh"),

    kvarh("kvarh"),

    W("W"),

    kW("kW"),

    Var("var"),

    kvar("kvar"),

    Amp("Amp"),

    Volt("Volt"),

    Celsius("Celsius");

    @JsonValue
    fun getEnumValue(): String = value
}