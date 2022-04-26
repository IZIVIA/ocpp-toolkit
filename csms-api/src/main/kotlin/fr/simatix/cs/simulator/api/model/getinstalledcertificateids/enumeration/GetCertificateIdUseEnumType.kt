package fr.simatix.cs.simulator.api.model.getinstalledcertificateids.enumeration

enum class GetCertificateIdUseEnumType(val value: String)
{
    V2GRootCertificate("V2GRootCertificate"),
    MORootCertificate("MORootCertificate"),
    CSMSRootCertificate("CSMSRootCertificate"),
    V2GCertificateChain("V2GCertificateChain"),
    ManufacturerRootCertificate("ManufacturerRootCertificate")
}