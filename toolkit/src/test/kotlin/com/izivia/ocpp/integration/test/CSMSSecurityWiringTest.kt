package com.izivia.ocpp.integration.test

import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedReq
import com.izivia.ocpp.core16.model.certificatesigned.CertificateSignedResp
import com.izivia.ocpp.core16.model.certificatesigned.enumeration.CertificateSignedStatusEnumType
import com.izivia.ocpp.core16.model.common.enumeration.GenericStatusEnumType
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core16.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core16.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core16.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.signedfirmwarestatusnotification.SignedFirmwareStatusNotificationResp
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.operation.information.CSMSCallbacks
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.security16.SecurityChargePointOperations
import com.izivia.ocpp.transport.OcppVersion
import com.izivia.ocpp.transport.ServerTransport
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import kotlin.reflect.KClass

class CSMSSecurityWiringTest {

    private val meta = RequestMetadata("CP001")

    private fun buildCsms(vararg apis: CSMSCallbacks): CSMS {
        val server = mockk<ServerTransport>()
        every { server.receiveMessageClass<Any, Any>(any(), any(), any(), any(), any()) } returns Unit
        every { server.canSendToChargingStation(any()) } returns true
        every { server.sendMessageClass<Any, Any>(any(), any(), any(), any()) } answers {
            responseFor(firstArg())
        }
        return CSMS(
            transports = mapOf(server to (9_999 to setOf(OcppVersion.OCPP_1_6))),
            csmsApis = apis.toSet(),
            fn = { ChargingStationConfig(true, null) }
        )
    }

    @Test
    fun `getSecurityCSApi16 round-trips a CSMS-initiated security message when a security callback is registered`() {
        val csms = buildCsms(securityCallback())

        val response = csms.getSecurityCSApi16()
            .certificateSigned(meta, CertificateSignedReq("cert"))
            .response

        expectThat(response.status).isEqualTo(CertificateSignedStatusEnumType.Accepted)
    }

    @Test
    fun `getCSApi16 throws when only a security callback is registered`() {
        val csms = buildCsms(securityCallback())

        expectThrows<IllegalStateException> { csms.getCSApi16() }
    }

    @Test
    fun `getSecurityCSApi16 throws when no security callback is registered`() {
        val csms = buildCsms(mockk<ChargePointOperations>(relaxed = true))

        expectThrows<IllegalStateException> { csms.getSecurityCSApi16() }
    }

    @Test
    fun `a single callback implementing both core and security interfaces exposes both apis`() {
        val core = mockk<ChargePointOperations>(relaxed = true)
        val combined = object :
            ChargePointOperations by core,
            SecurityChargePointOperations by securityCallback() {
            override fun connect() = Unit
            override fun close() = Unit
        }

        val csms = buildCsms(combined)

        // Security facet is registered despite the object also being a core callback...
        expectThat(
            csms.getSecurityCSApi16()
                .certificateSigned(meta, CertificateSignedReq("cert"))
                .response.status
        ).isEqualTo(CertificateSignedStatusEnumType.Accepted)
        // ...and the core facet is still available (would throw if it had been dropped).
        csms.getCSApi16()
    }

    private fun responseFor(clazz: KClass<*>): Any =
        when (clazz) {
            CertificateSignedResp::class -> CertificateSignedResp(CertificateSignedStatusEnumType.Accepted)
            else -> throw IllegalArgumentException("Unexpected response class $clazz")
        }

    private fun securityCallback(): SecurityChargePointOperations =
        object : SecurityChargePointOperations {
            override fun connect() = Unit
            override fun close() = Unit

            override fun logStatusNotification(meta: RequestMetadata, req: LogStatusNotificationReq) =
                execution(meta, req, LogStatusNotificationResp())

            override fun securityEventNotification(meta: RequestMetadata, req: SecurityEventNotificationReq) =
                execution(meta, req, SecurityEventNotificationResp())

            override fun signCertificate(meta: RequestMetadata, req: SignCertificateReq) =
                execution(meta, req, SignCertificateResp(GenericStatusEnumType.Accepted))

            override fun signedFirmwareStatusNotification(
                meta: RequestMetadata,
                req: SignedFirmwareStatusNotificationReq
            ) = execution(meta, req, SignedFirmwareStatusNotificationResp())
        }

    private fun <T, P> execution(meta: RequestMetadata, req: T, resp: P) =
        OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, resp)
}
