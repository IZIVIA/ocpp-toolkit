package com.izivia.ocpp.adapter12.test

import com.izivia.ocpp.adapter12.Ocpp12CSApiAdapter
import com.izivia.ocpp.adapter12.impl.RealTransactionRepository
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.api.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import com.izivia.ocpp.api.model.clearcache.ClearCacheResp
import com.izivia.ocpp.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.api.model.common.enumeration.RequestStartStopStatusEnumType
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.api.model.setvariables.SetVariableResultType
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core12.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core12.model.common.enumeration.RemoteStartStopStatus
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core12.model.reset.enumeration.ResetType
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.mockk.verify
import kotlin.time.Instant
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CSApiAdapterTest {
    private lateinit var csApi: CSApi
    private val requestMetadata = RequestMetadata("CP001")
    private val timestamp = Instant.parse("2022-02-15T00:00:00.000Z")

    @BeforeEach
    fun init() {
        csApi = mockk()
    }

    @AfterEach
    fun destroy() {
        unmockkAll()
    }

    @Test
    fun `reset request`() {
        every { csApi.reset(any(), any()) } answers {
            success(secondArg(), ResetResp(ResetStatusEnumType.Accepted))
        }

        val request = ResetReq(ResetType.Hard)
        val response = adapter().reset(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(ResetStatus.Accepted)
        }
    }

    @Test
    fun `change availability request`() {
        every { csApi.changeAvailability(any(), any()) } answers {
            success(secondArg(), ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Scheduled))
        }

        val request = ChangeAvailabilityReq(1, AvailabilityType.Inoperative)
        val response = adapter().changeAvailability(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(AvailabilityStatus.Scheduled)
        }
    }

    @Test
    fun `change configuration request`() {
        every { csApi.setVariables(any(), any()) } answers {
            success(
                secondArg(),
                SetVariablesResp(
                    listOf(
                        SetVariableResultType(
                            SetVariableStatusEnumType.Accepted,
                            ComponentType("AuthorizeRemoteTxRequests"),
                            VariableType("AuthorizeRemoteTxRequests")
                        )
                    )
                )
            )
        }

        val request = ChangeConfigurationReq("AuthorizeRemoteTxRequests", "true")
        val response = adapter().changeConfiguration(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(ConfigurationStatus.Accepted)
        }
    }

    @Test
    fun `clear cache request`() {
        every { csApi.clearCache(any(), any()) } answers {
            success(secondArg(), ClearCacheResp(ClearCacheStatusEnumType.Accepted))
        }

        val request = ClearCacheReq()
        val response = adapter().clearCache(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(ClearCacheStatus.Accepted)
        }
    }

    @Test
    fun `remote start transaction request`() {
        every { csApi.requestStartTransaction(any(), any()) } answers {
            success(secondArg(), RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted))
        }

        val request = RemoteStartTransactionReq("Tag1")
        val response = adapter().remoteStartTransaction(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(RemoteStartStopStatus.Accepted)
        }
        verify {
            csApi.requestStartTransaction(any(), match { it.idToken == IdTokenType("Tag1", IdTokenEnumType.Central) })
        }
    }

    @Test
    fun `remote stop transaction request`() {
        every { csApi.requestStopTransaction(any(), any()) } answers {
            success(secondArg(), RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted))
        }

        val request = RemoteStopTransactionReq(42)
        val response = adapter().remoteStopTransaction(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(RemoteStartStopStatus.Accepted)
        }
        verify {
            csApi.requestStopTransaction(any(), match<RequestStopTransactionReq> { it.transactionId == "42" })
        }
    }

    @Test
    fun `unlock connector request`() {
        every { csApi.unlockConnector(any(), any()) } answers {
            success(secondArg(), UnlockConnectorResp(UnlockStatusEnumType.Unlocked))
        }

        val request = UnlockConnectorReq(1)
        val response = adapter().unlockConnector(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(UnlockStatus.Accepted)
        }
    }

    @Test
    fun `update firmware request`() {
        every { csApi.updateFirmware(any(), any()) } answers {
            success(secondArg(), UpdateFirmwareResp(UpdateFirmwareStatusEnumType.Accepted))
        }

        val request = UpdateFirmwareReq("https://example.test/firmware.bin", retrieveDate = timestamp)
        val response = adapter().updateFirmware(requestMetadata, request)

        expectThat(response.request).isEqualTo(request)
    }

    @Test
    fun `get diagnostics request`() {
        every { csApi.getLog(any(), any()) } answers {
            success(secondArg(), GetLogResp(LogStatusEnumType.Accepted, filename = "diagnostics.log"))
        }

        val request = GetDiagnosticsReq("https://example.test/diagnostics", startTime = timestamp)
        val response = adapter().getDiagnostics(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.fileName }.isEqualTo("diagnostics.log")
        }
    }

    private fun adapter() = Ocpp12CSApiAdapter(csApi, RealTransactionRepository())

    private fun <Req, Resp> success(request: Req, response: Resp): OperationExecution<Req, Resp> =
        OperationExecution(
            executionMeta = ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS),
            request = request,
            response = response
        )
}
