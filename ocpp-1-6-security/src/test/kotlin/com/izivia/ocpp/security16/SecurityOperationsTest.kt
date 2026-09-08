package com.izivia.ocpp.security16

import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedResp
import com.izivia.ocpp.core16.model.certificatesigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.CertificateHashDataType
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core16.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.DeleteCertificateStatusEnumType
import com.izivia.ocpp.core16.model.deletecertificate.enumeration.HashAlgorithmEnumType
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageReq
import com.izivia.ocpp.core16.model.extendedtriggermessage.ExtendedTriggerMessageResp
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core16.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.CertificateUseEnumType
import com.izivia.ocpp.core16.model.getinstalledcertificateids.enumeration.GetInstalledCertificateStatusEnumType
import com.izivia.ocpp.core16.model.getlog.GetLogReq
import com.izivia.ocpp.core16.model.getlog.GetLogResp
import com.izivia.ocpp.core16.model.getlog.LogParametersType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogEnumType
import com.izivia.ocpp.core16.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core16.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core16.model.installcertificate.enumeration.CertificateStatusEnumType
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core16.model.logstatusnotification.enumeration.UpdateLogStatusEnumType
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.signedupdatefirmware.FirmwareType
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareReq
import com.izivia.ocpp.core16.model.signedupdatefirmware.SignedUpdateFirmwareResp
import com.izivia.ocpp.core16.model.signedupdatefirmware.enumeration.UpdateFirmwareStatusEnumType
import com.izivia.ocpp.core16.model.extendedtriggermessage.enumeration.ExtendedMessageTriggerEnumType
import com.izivia.ocpp.core16.model.triggermessage.enumeration.TriggerMessageStatus
import com.izivia.ocpp.core16.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.core16.model.firmwarestatusnotification.enumeration.SignedFirmwareStatus
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import io.mockk.every
import io.mockk.mockk
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.containsExactly
import strikt.assertions.isEqualTo
import kotlin.reflect.KClass

class SecurityOperationsTest {
    private val hash = CertificateHashDataType(HashAlgorithmEnumType.SHA256, "issuer", "key", "serial")
    private val meta = RequestMetadata("CP001")

    @Test
    fun `charge point registers csms initiated security handlers`() {
        val actions = mutableListOf<String>()
        val client = mockk<ClientTransport>()
        every { client.receiveMessageClass<Any, Any>(any(), any(), any()) } answers {
            actions += secondArg<String>()
        }

        SecurityChargePointOperations.newSecurityChargePointOperations("CP001", client, stubCSMSOperations())

        expectThat(actions).containsExactly(
            "CertificateSigned",
            "DeleteCertificate",
            "ExtendedTriggerMessage",
            "GetInstalledCertificateIds",
            "GetLog",
            "InstallCertificate",
            "SignedUpdateFirmware"
        )
    }

    @Test
    fun `csms registers charge point initiated security handlers`() {
        val actions = mutableListOf<String>()
        val server = mockk<ServerTransport>()
        every {
            server.receiveMessageClass<Any, Any>(any(), any(), any(), any(), any())
        } answers {
            actions += secondArg<String>()
        }

        SecurityCSMSOperations.newSecurityCSMSOperations(
            setOf(server),
            { ChargingStationConfig(true, null) },
            stubChargePointOperations()
        )

        expectThat(actions).containsExactly(
            "LogStatusNotification",
            "SecurityEventNotification",
            "SignCertificate",
            "SignedFirmwareStatusNotification"
        )
    }

    @Test
    fun `charge point sends security messages with whitepaper actions`() {
        val sentActions = mutableListOf<String>()
        val client = mockk<ClientTransport>()
        every { client.receiveMessageClass<Any, Any>(any(), any(), any()) } returns Unit
        every { client.sendMessageClass<Any, Any>(any(), any(), any()) } answers {
            sentActions += secondArg<String>()
            responseFor(firstArg())
        }
        val operations = SecurityChargePointOperations.newSecurityChargePointOperations("CP001", client, stubCSMSOperations())

        operations.logStatusNotification(meta, LogStatusNotificationReq(UpdateLogStatusEnumType.Uploaded, 1))
        operations.securityEventNotification(meta, SecurityEventNotificationReq("InvalidTLSCipherSuite", Instant.parse("2022-02-15T00:00:00Z")))
        operations.signCertificate(meta, SignCertificateReq("csr"))
        operations.signedFirmwareStatusNotification(meta, SignedFirmwareStatusNotificationReq(SignedFirmwareStatus.SignatureVerified, 1))

        expectThat(sentActions).containsExactly(
            "LogStatusNotification",
            "SecurityEventNotification",
            "SignCertificate",
            "SignedFirmwareStatusNotification"
        )
    }

    @Test
    fun `csms sends security messages with whitepaper actions`() {
        val sentActions = mutableListOf<String>()
        val server = mockk<ServerTransport>()
        every { server.receiveMessageClass<Any, Any>(any(), any(), any(), any(), any()) } returns Unit
        every { server.canSendToChargingStation(any()) } returns true
        every { server.sendMessageClass<Any, Any>(any(), any(), any(), any()) } answers {
            sentActions += thirdArg<String>()
            responseFor(firstArg())
        }
        val operations = SecurityCSMSOperations.newSecurityCSMSOperations(
            setOf(server),
            { ChargingStationConfig(true, null) },
            stubChargePointOperations()
        )

        operations.certificateSigned(meta, CertificateSignedReq("cert"))
        operations.deleteCertificate(meta, DeleteCertificateReq(hash))
        operations.extendedTriggerMessage(meta, ExtendedTriggerMessageReq(ExtendedMessageTriggerEnumType.FirmwareStatusNotification))
        operations.getInstalledCertificateIds(meta, GetInstalledCertificateIdsReq(CertificateUseEnumType.CentralSystemRootCertificate))
        operations.getLog(meta, GetLogReq(LogEnumType.SecurityLog, 1, null, null, LogParametersType("https://example.test", null, null)))
        operations.installCertificate(meta, InstallCertificateReq(CertificateUseEnumType.ManufacturerRootCertificate, "cert"))
        operations.signedUpdateFirmware(
            meta,
            SignedUpdateFirmwareReq(
                firmware = FirmwareType(
                    location = "https://example.test/firmware.bin",
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00Z"),
                    installDateTime = null,
                    signingCertificate = "cert",
                    signature = "signature"
                ),
                retries = null,
                requestId = 1,
                retryInterval = null
            )
        )

        expectThat(sentActions).containsExactly(
            "CertificateSigned",
            "DeleteCertificate",
            "ExtendedTriggerMessage",
            "GetInstalledCertificateIds",
            "GetLog",
            "InstallCertificate",
            "SignedUpdateFirmware"
        )
    }

    private fun responseFor(clazz: KClass<*>): Any =
        when (clazz) {
            CertificateSignedResp::class -> CertificateSignedResp(CertificateSignedStatusEnumType.Accepted)
            DeleteCertificateResp::class -> DeleteCertificateResp(DeleteCertificateStatusEnumType.Accepted)
            ExtendedTriggerMessageResp::class -> ExtendedTriggerMessageResp(TriggerMessageStatus.Accepted)
            GetInstalledCertificateIdsResp::class -> GetInstalledCertificateIdsResp(GetInstalledCertificateStatusEnumType.Accepted, listOf(hash))
            GetLogResp::class -> GetLogResp(LogStatusEnumType.Accepted, "security.log")
            InstallCertificateResp::class -> InstallCertificateResp(CertificateStatusEnumType.Accepted)
            LogStatusNotificationResp::class -> LogStatusNotificationResp()
            SecurityEventNotificationResp::class -> SecurityEventNotificationResp()
            SignCertificateResp::class -> SignCertificateResp(GenericStatusEnumType.Accepted)
            SignedFirmwareStatusNotificationResp::class -> SignedFirmwareStatusNotificationResp()
            SignedUpdateFirmwareResp::class -> SignedUpdateFirmwareResp(UpdateFirmwareStatusEnumType.Accepted)
            else -> throw IllegalArgumentException("Unexpected response class $clazz")
        }

    private fun stubCSMSOperations(): SecurityCSMSOperations =
        object : SecurityCSMSOperations {
            override fun certificateSigned(meta: RequestMetadata, req: CertificateSignedReq) =
                execution(meta, req, CertificateSignedResp(CertificateSignedStatusEnumType.Accepted))

            override fun deleteCertificate(meta: RequestMetadata, req: DeleteCertificateReq) =
                execution(meta, req, DeleteCertificateResp(DeleteCertificateStatusEnumType.Accepted))

            override fun extendedTriggerMessage(meta: RequestMetadata, req: ExtendedTriggerMessageReq) =
                execution(meta, req, ExtendedTriggerMessageResp(TriggerMessageStatus.Accepted))

            override fun getInstalledCertificateIds(meta: RequestMetadata, req: GetInstalledCertificateIdsReq) =
                execution(meta, req, GetInstalledCertificateIdsResp(GetInstalledCertificateStatusEnumType.Accepted, listOf(hash)))

            override fun getLog(meta: RequestMetadata, req: GetLogReq) =
                execution(meta, req, GetLogResp(LogStatusEnumType.Accepted, "security.log"))

            override fun installCertificate(meta: RequestMetadata, req: InstallCertificateReq) =
                execution(meta, req, InstallCertificateResp(CertificateStatusEnumType.Accepted))

            override fun signedUpdateFirmware(meta: RequestMetadata, req: SignedUpdateFirmwareReq) =
                execution(meta, req, SignedUpdateFirmwareResp(UpdateFirmwareStatusEnumType.Accepted))
        }

    private fun stubChargePointOperations(): SecurityChargePointOperations =
        object : SecurityChargePointOperations {
            override fun logStatusNotification(meta: RequestMetadata, req: LogStatusNotificationReq) =
                execution(meta, req, LogStatusNotificationResp())

            override fun securityEventNotification(meta: RequestMetadata, req: SecurityEventNotificationReq) =
                execution(meta, req, SecurityEventNotificationResp())

            override fun signCertificate(meta: RequestMetadata, req: SignCertificateReq) =
                execution(meta, req, SignCertificateResp(GenericStatusEnumType.Accepted))

            override fun signedFirmwareStatusNotification(meta: RequestMetadata, req: SignedFirmwareStatusNotificationReq) =
                execution(meta, req, SignedFirmwareStatusNotificationResp())
        }

    private fun <T, P> execution(meta: RequestMetadata, req: T, resp: P) =
        OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, resp)
}
