package fr.simatix.cs.simulator.core20.model.notifyreport.enumeration

import com.fasterxml.jackson.annotation.JsonValue

enum class DataEnumType(val value: String) {
    STRING("string"),

    DECIMAL("decimal"),

    INTEGER("integer"),

    DATETIME("dateTime"),

    BOOLEAN("boolean"),

    OPTIONLIST("OptionList"),

    SEQUENCELIST("SequenceList"),

    MEMBERLIST("MemberList");

    @JsonValue
    fun getEnumValue(): String = value
}