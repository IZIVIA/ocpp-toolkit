package com.izivia.ocpp.core20.model.reservenow.enumeration

enum class ReserveNowStatusEnumType(val value: String) {
    Accepted("Accepted"),
    Faulted("Faulted"),
    Occupied("Occupied"),
    Rejected("Rejected"),
    Unavailable("Unavailable");
}
