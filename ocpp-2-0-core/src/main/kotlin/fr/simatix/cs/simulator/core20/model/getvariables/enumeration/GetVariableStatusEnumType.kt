package fr.simatix.cs.simulator.core20.model.getvariables.enumeration

enum class GetVariableStatusEnumType(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    UnknownComponent("UnknownComponent"),

    UnknownVariable("UnknownVariable"),

    NotSupportedAttributeType("NotSupportedAttributeType");
}