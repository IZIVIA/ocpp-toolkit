package com.izivia.ocpp.api.model.authorize.enumeration

enum class AuthorizeCertificateStatusEnumType(val value: String) {
    Accepted("Accepted"),
    SignatureError("SignatureError"),
    CertificateExpired("CertificateExpired"),
    CertificateRevoked("CertificateRevoked"),
    NoCertificateAvailable("NoCertificateAvailable"),
    CertChainError("CertChainError"),
    ContractCancelled("ContractCancelled")
}
