package com.izivia.ocpp.adapter15.test

import com.izivia.ocpp.adapter15.Ocpp15CSApiAdapter
import com.izivia.ocpp.adapter15.impl.RealTransactionRepository
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
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesResp
import com.izivia.ocpp.api.model.getallvariables.KeyValue
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getlog.enumeration.LogStatusEnumType
import com.izivia.ocpp.api.model.getvariables.GetVariableResultType
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.api.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.api.model.reset.ResetResp
import com.izivia.ocpp.api.model.reset.enumeration.ResetStatusEnumType
import com.izivia.ocpp.api.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.api.model.sendlocallist.enumeration.SendLocalListStatusEnumType
import com.izivia.ocpp.api.model.setvariables.SetVariableResultType
import com.izivia.ocpp.api.model.setvariables.SetVariablesResp
import com.izivia.ocpp.api.model.setvariables.enumeration.SetVariableStatusEnumType
import com.izivia.ocpp.api.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import com.izivia.ocpp.api.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.api.model.updatefirmware.enumeration.UpdateFirmwareStatusEnumType
import com.izivia.ocpp.core15.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core15.model.cancelreservation.enumeration.CancelReservationStatus
import com.izivia.ocpp.core15.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityStatus
import com.izivia.ocpp.core15.model.changeavailability.enumeration.AvailabilityType
import com.izivia.ocpp.core15.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core15.model.changeconfiguration.enumeration.ConfigurationStatus
import com.izivia.ocpp.core15.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core15.model.clearcache.enumeration.ClearCacheStatus
import com.izivia.ocpp.core15.model.common.enumeration.RemoteStartStopStatus
import com.izivia.ocpp.core15.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core15.model.datatransfer.enumeration.DataTransferStatus
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core15.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core15.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core15.model.reservenow.enumeration.ReservationStatus
import com.izivia.ocpp.core15.model.reset.ResetReq
import com.izivia.ocpp.core15.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core15.model.reset.enumeration.ResetType
import com.izivia.ocpp.core15.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateStatus
import com.izivia.ocpp.core15.model.sendlocallist.enumeration.UpdateType
import com.izivia.ocpp.core15.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core15.model.unlockconnector.enumeration.UnlockStatus
import com.izivia.ocpp.core15.model.updatefirmware.UpdateFirmwareReq
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
import com.izivia.ocpp.api.model.cancelreservation.CancelReservationResp as CancelReservationRespGen
import com.izivia.ocpp.api.model.cancelreservation.enumeration.CancelReservationStatusEnumType as CancelReservationStatusEnumTypeGen
import com.izivia.ocpp.api.model.datatransfer.DataTransferResp as DataTransferRespGen
import com.izivia.ocpp.api.model.datatransfer.enumeration.DataTransferStatusEnumType as DataTransferStatusEnumTypeGen
import com.izivia.ocpp.api.model.getlocallistversion.GetLocalListVersionResp as GetLocalListVersionRespGen
import com.izivia.ocpp.api.model.reservenow.ReserveNowResp as ReserveNowRespGen
import com.izivia.ocpp.api.model.reservenow.enumeration.ReserveNowStatusEnumType as ReserveNowStatusEnumTypeGen

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

        val request = RemoteStartTransactionReq(idTag = "Tag1")
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
    fun `get configuration request with keys`() {
        every { csApi.getVariables(any(), any()) } answers {
            success(
                secondArg(),
                GetVariablesResp(
                    listOf(
                        GetVariableResultType(
                            GetVariableStatusEnumType.Accepted,
                            ComponentType("MeterValuesSampleInterval"),
                            VariableType("MeterValuesSampleInterval"),
                            readonly = false,
                            attributeValue = "60"
                        )
                    )
                )
            )
        }

        val request = GetConfigurationReq(listOf("MeterValuesSampleInterval"))
        val response = adapter().getConfiguration(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.configurationKey }.isEqualTo(
                listOf(com.izivia.ocpp.core15.model.getconfiguration.KeyValue("MeterValuesSampleInterval", false, "60"))
            )
        }
    }

    @Test
    fun `get configuration request without keys`() {
        every { csApi.getAllVariables(any(), any()) } answers {
            success(secondArg(), GetAllVariablesResp(listOf(KeyValue("AllowOfflineTxForUnknownId", true, "true"))))
        }

        val request = GetConfigurationReq()
        val response = adapter().getConfiguration(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.configurationKey }.isEqualTo(
                listOf(com.izivia.ocpp.core15.model.getconfiguration.KeyValue("AllowOfflineTxForUnknownId", true, "true"))
            )
        }
    }

    @Test
    fun `cancel reservation request`() {
        every { csApi.cancelReservation(any(), any()) } answers {
            success(secondArg(), CancelReservationRespGen(CancelReservationStatusEnumTypeGen.Accepted))
        }

        val request = CancelReservationReq(10)
        val response = adapter().cancelReservation(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(CancelReservationStatus.Accepted)
        }
    }

    @Test
    fun `get local list version request`() {
        every { csApi.getLocalListVersion(any(), any()) } answers {
            success(secondArg(), GetLocalListVersionRespGen(7))
        }

        val request = GetLocalListVersionReq()
        val response = adapter().getLocalListVersion(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.listVersion }.isEqualTo(7)
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
    fun `send local list request`() {
        every { csApi.sendLocalList(any(), any()) } answers {
            success(secondArg(), SendLocalListResp(SendLocalListStatusEnumType.Accepted))
        }

        val request = SendLocalListReq(listVersion = 2, updateType = UpdateType.Full)
        val response = adapter().sendLocalList(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(UpdateStatus.Accepted)
        }
    }

    @Test
    fun `reserve now request`() {
        every { csApi.reserveNow(any(), any()) } answers {
            success(secondArg(), ReserveNowRespGen(ReserveNowStatusEnumTypeGen.Accepted))
        }

        val request = ReserveNowReq(1, timestamp, "Tag1", reservationId = 10)
        val response = adapter().reserveNow(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(ReservationStatus.Accepted)
        }
    }

    @Test
    fun `data transfer request`() {
        every { csApi.dataTransfer(any(), any()) } answers {
            success(secondArg(), DataTransferRespGen(DataTransferStatusEnumTypeGen.Accepted, data = "response"))
        }

        val request = DataTransferReq("vendor", messageId = "message", data = "request")
        val response = adapter().dataTransfer(requestMetadata, request)

        expectThat(response) {
            get { this.request }.isEqualTo(request)
            get { this.response.status }.isEqualTo(DataTransferStatus.Accepted)
            get { this.response.data }.isEqualTo("response")
        }
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

    private fun adapter() = Ocpp15CSApiAdapter(csApi, RealTransactionRepository())

    private fun <Req, Resp> success(request: Req, response: Resp): OperationExecution<Req, Resp> =
        OperationExecution(
            executionMeta = ExecutionMetadata(requestMetadata, RequestStatus.SUCCESS),
            request = request,
            response = response
        )
}
