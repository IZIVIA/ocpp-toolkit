package com.izivia.ocpp.core15.model.datatransfer.enumeration

enum class DataTransferStatus(val value: String) {
    Accepted("Accepted"),

    Rejected("Rejected"),

    UnknownMessageId("UnknownMessageId"),

    UnknownVendorId("UnknownVendorId");
}