package fr.simatix.cs.simulator.api.model.datatransfer.enumeration

enum class DataTransferStatusEnumType(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    UnknownMessageId("UnknownMessageId"),

    UnknownVendorId("UnknownVendorId");
}