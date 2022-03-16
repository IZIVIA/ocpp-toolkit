package fr.simatix.cs.simulator.core16.model.datatransfer.enumeration

enum class DataTransferStatus(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    UnknownMessageId("UnknownMessageId"),

    UnknownVendorId("UnknownVendorId");
}