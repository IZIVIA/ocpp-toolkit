package com.izivia.ocpp.adapter16.test

import com.izivia.ocpp.adapter16.Ocpp16SecurityCSApiAdapter
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedReq as GenCertificateSignedReq
import com.izivia.ocpp.api.model.certificateSigned.CertificateSignedResp as GenCertificateSignedResp
import com.izivia.ocpp.api.model.certificateSigned.enumeration.CertificateSignedStatusEnumType as GenCertificateSignedStatusEnumType
import com.izivia.ocpp.api.model.common.CertificateHashDataType as GenCertificateHashDataType
import com.izivia.ocpp.api.model.common.enumeration.HashAlgorithmEnumType as GenHashAlgorithmEnumType
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateReq as GenDeleteCertificateReq
import com.izivia.ocpp.api.model.deletecertificate.DeleteCertificateResp as GenDeleteCertificateResp
import com.izivia.ocpp.api.model.deletecertificate.enumerations.DeleteCertificateStatusEnumType as GenDeleteCertificateStatusEnumType
import com.izivia.ocpp.api.model.getinstalledcertificateids.CertificateHashDataChainType as GenCertificateHashDataChainType
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as GenGetInstalledCertificateIdsReq
import com.izivia.ocpp.api.model.getinstalledcertificateids.GetInstalledCertificateIdsResp as GenGetInstalledCertificateIdsResp
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetCertificateIdUseEnumType as GenGetCertificateIdUseEnumType
import com.izivia.ocpp.api.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType as GenGetInstalledCertificateStatusEnumType
import com.izivia.ocpp.api.model.getlog.GetLogReq as GenGetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp as GenGetLogResp
import com.izivia.ocpp.api.model.getlog.enumeration.LogEnumType as GenLogEnumType
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType as GenLogStatusEnumType
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateReq as GenInstallCertificateReq
import com.izivia.ocpp.api.model.installcertificate.InstallCertificateResp as GenInstallCertificateResp
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateStatusEnumType as GenInstallCertificateStatusEnumType
import com.izivia.ocpp.api.model.installcertificate.enumeration.InstallCertificateUseEnumType as GenInstallCertificateUseEnumType
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareReq as GenUpdateFirmwareReq
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp as GenUpdateFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType as GenUpdateFirmwareStatusEnumType
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq as CoreCertificateSignedReq
import com.izivia.ocpp.core16.model.certificatesigned.enumeration.CertificateSignedStatusEnumType as CoreCertificateSignedStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.CertificateHashDataType as CoreCertificateHashDataType
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateReq as CoreDeleteCertificateReq
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType as CoreDeleteCertificateStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.HashAlgorithmEnumType as CoreHashAlgorithmEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsReq as CoreGetInstalledCertificateIdsReq
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.CertificateUseEnumType as CoreCertificateUseEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType as CoreGetInstalledCertificateStatusEnumType
import com.izivia.ocpp.core16.model.getlog.GetLogReq as CoreGetLogReq
import com.izivia.ocpp.core16.model.getlog.LogParametersType as CoreLogParametersType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogEnumType as CoreLogEnumType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogStatusEnumType as CoreLogStatusEnumType
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateReq as CoreInstallCertificateReq
import com.izivia.ocpp.core16.model.installcertificate.enumeration.CertificateStatusEnumType as CoreCertificateStatusEnumType
import com.izivia.ocpp.core16.model.signedupdatefirmware.FirmwareType as CoreFirmwareType
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq as CoreSignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.enumeration.UpdateFirmwareStatusEnumType as CoreUpdateFirmwareStatusEnumType

/**
 * Covers the CSMS -> charge point direction of the OCPP 1.6 security whitepaper: each core 1.6
 * request is mapped onto its generic counterpart, handed to the application [CSApi], and the
 * generic answer mapped back onto the core 1.6 response.
 */
class SecurityCSApiAdapterTest {

    private val meta = RequestMetadata("chargePoint")
    private val csApi = mockk<CSApi>()
    private val adapter = Ocpp16SecurityCSApiAdapter(csApi)

    private fun <Q, S> execution(req: Q, resp: S) =
        OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, resp)

    @Test
    fun `certificateSigned forwards the chain and maps the status back`() {
        val forwarded = slot<GenCertificateSignedReq>()
        every { csApi.certificateSigned(any(), capture(forwarded)) } answers {
            execution(forwarded.captured, GenCertificateSignedResp(GenCertificateSignedStatusEnumType.Rejected))
        }

        val response = adapter.certificateSigned(meta, CoreCertificateSignedReq("chain"))

        expectThat(forwarded.captured.certificateChain).isEqualTo("chain")
        expectThat(response.response.status).isEqualTo(CoreCertificateSignedStatusEnumType.Rejected)
    }

    @Test
    fun `deleteCertificate forwards the certificate hash data and maps NotFound back`() {
        val forwarded = slot<GenDeleteCertificateReq>()
        every { csApi.deleteCertificate(any(), capture(forwarded)) } answers {
            execution(forwarded.captured, GenDeleteCertificateResp(GenDeleteCertificateStatusEnumType.NotFound))
        }

        val response = adapter.deleteCertificate(
            meta,
            CoreDeleteCertificateReq(
                CoreCertificateHashDataType(
                    hashAlgorithm = CoreHashAlgorithmEnumType.SHA512,
                    issuerNameHash = "issuer",
                    issuerKeyHash = "key",
                    serialNumber = "serial"
                )
            )
        )

        expectThat(forwarded.captured.certificateHashData).isEqualTo(
            GenCertificateHashDataType(GenHashAlgorithmEnumType.SHA512, "issuer", "key", "serial")
        )
        expectThat(response.response.status).isEqualTo(CoreDeleteCertificateStatusEnumType.NotFound)
    }

    @Test
    fun `getInstalledCertificateIds wraps the single 1-6 type and flattens the answered chain`() {
        val forwarded = slot<GenGetInstalledCertificateIdsReq>()
        every { csApi.getInstalledCertificateIds(any(), capture(forwarded)) } answers {
            execution(
                forwarded.captured,
                GenGetInstalledCertificateIdsResp(
                    status = GenGetInstalledCertificateStatusEnumType.Accepted,
                    certificateHashDataChain = listOf(
                        GenCertificateHashDataChainType(
                            certificateType = GenGetCertificateIdUseEnumType.CSMSRootCertificate,
                            certificateHashData = GenCertificateHashDataType(
                                GenHashAlgorithmEnumType.SHA256, "issuer-1", "key-1", "serial-1"
                            ),
                            childCertificateHashData = listOf(
                                GenCertificateHashDataType(
                                    GenHashAlgorithmEnumType.SHA256, "child-issuer", "child-key", "child-serial"
                                )
                            )
                        ),
                        GenCertificateHashDataChainType(
                            certificateType = GenGetCertificateIdUseEnumType.ManufacturerRootCertificate,
                            certificateHashData = GenCertificateHashDataType(
                                GenHashAlgorithmEnumType.SHA384, "issuer-2", "key-2", "serial-2"
                            )
                        )
                    )
                )
            )
        }

        val response = adapter.getInstalledCertificateIds(
            meta,
            CoreGetInstalledCertificateIdsReq(CoreCertificateUseEnumType.CentralSystemRootCertificate)
        )

        // 1.6 carries a single type, 2.x a list.
        expectThat(forwarded.captured.certificateType)
            .isEqualTo(listOf(GenGetCertificateIdUseEnumType.CSMSRootCertificate))
        expectThat(response.response.status).isEqualTo(CoreGetInstalledCertificateStatusEnumType.Accepted)
        // The chain is flattened: only the top hash of each link survives, children are dropped.
        expectThat(response.response.certificateHashData).isNotNull().isEqualTo(
            listOf(
                CoreCertificateHashDataType(CoreHashAlgorithmEnumType.SHA256, "issuer-1", "key-1", "serial-1"),
                CoreCertificateHashDataType(CoreHashAlgorithmEnumType.SHA384, "issuer-2", "key-2", "serial-2")
            )
        )
    }

    @Test
    fun `getLog forwards the log parameters and maps the filename back`() {
        val forwarded = slot<GenGetLogReq>()
        every { csApi.getLog(any(), capture(forwarded)) } answers {
            execution(forwarded.captured, GenGetLogResp(GenLogStatusEnumType.AcceptedCanceled, "security.log"))
        }

        val response = adapter.getLog(
            meta,
            CoreGetLogReq(
                logType = CoreLogEnumType.SecurityLog,
                requestId = 42,
                retries = 3,
                retryInterval = 60,
                log = CoreLogParametersType(
                    remoteLocation = "https://example.test/logs",
                    oldestTimestamp = Instant.parse("2022-02-15T00:00:00Z"),
                    latestTimestamp = Instant.parse("2022-02-16T00:00:00Z")
                )
            )
        )

        expectThat(forwarded.captured) {
            get { requestId }.isEqualTo(42)
            get { logType }.isEqualTo(GenLogEnumType.SecurityLog)
            get { retries }.isEqualTo(3)
            get { retryInterval }.isEqualTo(60)
            get { log.remoteLocation }.isEqualTo("https://example.test/logs")
            get { log.oldestTimestamp }.isEqualTo(Instant.parse("2022-02-15T00:00:00Z"))
            get { log.latestTimestamp }.isEqualTo(Instant.parse("2022-02-16T00:00:00Z"))
        }
        expectThat(response.response.status).isEqualTo(CoreLogStatusEnumType.AcceptedCanceled)
        expectThat(response.response.filename).isEqualTo("security.log")
    }

    @Test
    fun `installCertificate maps the certificate use both ways`() {
        val forwarded = slot<GenInstallCertificateReq>()
        every { csApi.installCertificate(any(), capture(forwarded)) } answers {
            execution(forwarded.captured, GenInstallCertificateResp(GenInstallCertificateStatusEnumType.Failed))
        }

        val response = adapter.installCertificate(
            meta,
            CoreInstallCertificateReq(CoreCertificateUseEnumType.CentralSystemRootCertificate, "pem")
        )

        expectThat(forwarded.captured) {
            get { certificateType }.isEqualTo(GenInstallCertificateUseEnumType.CSMSRootCertificate)
            get { certificate }.isEqualTo("pem")
        }
        expectThat(response.response.status).isEqualTo(CoreCertificateStatusEnumType.Failed)
    }

    @Test
    fun `signedUpdateFirmware reaches updateFirmware carrying the signature that marks it signed`() {
        val forwarded = slot<GenUpdateFirmwareReq>()
        every { csApi.updateFirmware(any(), capture(forwarded)) } answers {
            execution(forwarded.captured, GenUpdateFirmwareResp(GenUpdateFirmwareStatusEnumType.RevokedCertificate))
        }

        val response = adapter.signedUpdateFirmware(
            meta,
            CoreSignedUpdateFirmwareReq(
                firmware = CoreFirmwareType(
                    location = "https://example.test/firmware.bin",
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00Z"),
                    installDateTime = Instant.parse("2022-02-16T00:00:00Z"),
                    signingCertificate = "cert",
                    signature = "signature"
                ),
                retries = 2,
                requestId = 7,
                retryInterval = 30
            )
        )

        // Signed and unsigned firmware updates share CSApi.updateFirmware: the pair
        // signingCertificate / signature is what tells them apart on the application side.
        expectThat(forwarded.captured) {
            get { requestId }.isEqualTo(7)
            get { retries }.isEqualTo(2)
            get { retryInterval }.isEqualTo(30)
            get { firmware.location }.isEqualTo("https://example.test/firmware.bin")
            get { firmware.retrieveDateTime }.isEqualTo(Instant.parse("2022-02-15T00:00:00Z"))
            get { firmware.installDateTime }.isEqualTo(Instant.parse("2022-02-16T00:00:00Z"))
            get { firmware.signingCertificate }.isEqualTo("cert")
            get { firmware.signature }.isEqualTo("signature")
        }
        expectThat(response.response.status).isEqualTo(CoreUpdateFirmwareStatusEnumType.RevokedCertificate)
    }

    @Test
    fun `the application execution status is not propagated, only the transport success is`() {
        every { csApi.certificateSigned(any(), any()) } answers {
            OperationExecution(
                ExecutionMetadata(meta, RequestStatus.NOT_SEND),
                secondArg(),
                GenCertificateSignedResp(GenCertificateSignedStatusEnumType.Accepted)
            )
        }

        val response = adapter.certificateSigned(meta, CoreCertificateSignedReq("chain"))

        // Documented on Ocpp16SecurityCSApiAdapter.ok(), and the same behaviour as Ocpp16CSApiAdapter.
        expectThat(response.executionMeta.status).isEqualTo(RequestStatus.SUCCESS)
    }
}
