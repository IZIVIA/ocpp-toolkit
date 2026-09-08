package com.izivia.ocpp.json16

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
import com.izivia.ocpp.json.JsonMessage
import com.izivia.ocpp.json.JsonMessageType
import kotlin.time.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isA

class SecurityJsonSchemaTest {
    private val parser = Ocpp16JsonParser()
    private val certificateHashData = CertificateHashDataType(
        hashAlgorithm = HashAlgorithmEnumType.SHA256,
        issuerNameHash = "a".repeat(64),
        issuerKeyHash = "b".repeat(64),
        serialNumber = "1234"
    )

    @Test
    fun `security whitepaper requests validate against json schemas`() {
        validateObject(CertificateSignedReq("-----BEGIN CERTIFICATE-----\\n-----END CERTIFICATE-----"))
        validateObject(DeleteCertificateReq(certificateHashData))
        validateObject(ExtendedTriggerMessageReq(ExtendedMessageTriggerEnumType.FirmwareStatusNotification, connectorId = 1))
        validateObject(GetInstalledCertificateIdsReq(CertificateUseEnumType.CentralSystemRootCertificate))
        validateObject(
            GetLogReq(
                logType = LogEnumType.SecurityLog,
                requestId = 1,
                retries = 1,
                retryInterval = 10,
                log = LogParametersType(
                    remoteLocation = "https://example.test/logs",
                    oldestTimestamp = Instant.parse("2022-02-15T00:00:00Z"),
                    latestTimestamp = Instant.parse("2022-02-16T00:00:00Z")
                )
            )
        )
        validateObject(InstallCertificateReq(CertificateUseEnumType.ManufacturerRootCertificate, "pem"))
        validateObject(LogStatusNotificationReq(UpdateLogStatusEnumType.Uploaded, requestId = 1))
        validateObject(SecurityEventNotificationReq("InvalidTLSCipherSuite", Instant.parse("2022-02-15T00:00:00Z")))
        validateObject(SignCertificateReq("-----BEGIN CERTIFICATE REQUEST-----\\n-----END CERTIFICATE REQUEST-----"))
        validateObject(SignedFirmwareStatusNotificationReq(SignedFirmwareStatus.SignatureVerified, requestId = 1))
        validateObject(
            SignedUpdateFirmwareReq(
                firmware = FirmwareType(
                    location = "https://example.test/firmware.bin",
                    retrieveDateTime = Instant.parse("2022-02-15T00:00:00Z"),
                    installDateTime = Instant.parse("2022-02-16T00:00:00Z"),
                    signingCertificate = "pem",
                    signature = "signature"
                ),
                retries = 1,
                requestId = 1,
                retryInterval = 10
            )
        )
    }

    @Test
    fun `security whitepaper responses validate against json schemas`() {
        validateObject(CertificateSignedResp(CertificateSignedStatusEnumType.Accepted))
        validateObject(DeleteCertificateResp(DeleteCertificateStatusEnumType.Accepted))
        validateObject(ExtendedTriggerMessageResp(TriggerMessageStatus.Accepted))
        validateObject(GetInstalledCertificateIdsResp(GetInstalledCertificateStatusEnumType.Accepted, listOf(certificateHashData)))
        validateObject(GetLogResp(LogStatusEnumType.Accepted, filename = "security.log"))
        validateObject(InstallCertificateResp(CertificateStatusEnumType.Accepted))
        validateObject(LogStatusNotificationResp())
        validateObject(SecurityEventNotificationResp())
        validateObject(SignCertificateResp(GenericStatusEnumType.Accepted))
        validateObject(SignedFirmwareStatusNotificationResp())
        validateObject(SignedUpdateFirmwareResp(UpdateFirmwareStatusEnumType.Accepted))
    }

    private inline fun <reified T : Any> validateObject(instance: T) {
        val instanceClass = instance::class.java.simpleName
        expectThat(
            parser.parseAnyFromJson<T>(
                parser.mapToJson(
                    JsonMessage(
                        msgType = JsonMessageType.CALL.takeIf { instanceClass.endsWith("Req") }
                            ?: JsonMessageType.CALL_RESULT,
                        msgId = "123456",
                        action = instanceClass.replace(Regex("Req$"), "").replace(Regex("Resp$"), ""),
                        payload = instance
                    )
                )
            )
        ).isA<JsonMessage<T>>().get { payload }.isA<T>()
    }
}
