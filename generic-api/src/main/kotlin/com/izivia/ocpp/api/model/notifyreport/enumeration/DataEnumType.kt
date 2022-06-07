package com.izivia.ocpp.api.model.notifyreport.enumeration

enum class DataEnumType(val value: String) {
    STRING("string"),

    DECIMAL("decimal"),

    INTEGER("integer"),

    DATETIME("dateTime"),

    BOOLEAN("boolean"),

    OPTIONLIST("OptionList"),

    SEQUENCELIST("SequenceList"),

    MEMBERLIST("MemberList");
}