package com.izivia.ocpp.core20.model.datatransfer.enumeration

enum class DataTransferStatusEnumType(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    UnknownMessageId("UnknownMessageId"),

    UnknownVendorId("UnknownVendorId");
}