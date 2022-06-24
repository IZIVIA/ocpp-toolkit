package com.izivia.ocpp.integration.test

import com.izivia.ocpp.transport.OcppVersion as OcppVersionTransport
import com.izivia.ocpp.core16.ChargePointOperations
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core16.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core16.model.common.IdTagInfo
import com.izivia.ocpp.core16.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core16.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationReq
import com.izivia.ocpp.core16.model.firmwarestatusnotification.FirmwareStatusNotificationResp
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core16.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core16.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core16.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionReq
import com.izivia.ocpp.core16.model.starttransaction.StartTransactionResp
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core16.model.statusnotification.StatusNotificationResp
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core16.model.stoptransaction.StopTransactionResp
import com.izivia.ocpp.integration.ApiFactory.Companion.CSMSOcppServer
import com.izivia.ocpp.integration.ApiFactory.Companion.Ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.CSMS
import com.izivia.ocpp.integration.model.CSMSSettings
import com.izivia.ocpp.integration.model.ServerSetting
import com.izivia.ocpp.integration.model.TransportEnum
import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.lang.Thread.sleep
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq as AuthorizeReq16
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp as AuthorizeResp16
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus as AuthorizationStatus16
import com.izivia.ocpp.core16.model.common.enumeration.RemoteStartStopStatus as RemoteStartStopStatus16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq as RemoteStartTransactionReq16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp as RemoteStartTransactionResp16

class ExampleCSApiTest {

    @Test
    fun `csApi of CSMS test`() {
        val port = 12345
        val idTag = "Tag2"

        // Create and start the CSMS server
        val csmsApi16 = object : ChargePointOperations {

            override fun connect() = throw NotImplementedError()

            override fun close() = throw NotImplementedError()

            override fun heartbeat(meta: RequestMetadata, request: HeartbeatReq)
                    : OperationExecution<HeartbeatReq, HeartbeatResp> = throw NotImplementedError()

            override fun authorize(
                meta: RequestMetadata,
                request: AuthorizeReq16
            ): OperationExecution<AuthorizeReq16, AuthorizeResp16> =
                OperationExecution(
                    ExecutionMetadata(meta, RequestStatus.SUCCESS),
                    request,
                    AuthorizeResp16(
                        idTagInfo = IdTagInfo(
                            if (request.idTag == idTag)
                                AuthorizationStatus16.Accepted
                            else AuthorizationStatus16.Invalid
                        )
                    )
                )

            override fun meterValues(meta: RequestMetadata, request: MeterValuesReq)
                    : OperationExecution<MeterValuesReq, MeterValuesResp> = throw NotImplementedError()

            override fun startTransaction(meta: RequestMetadata, request: StartTransactionReq)
                    : OperationExecution<StartTransactionReq, StartTransactionResp> = throw NotImplementedError()

            override fun stopTransaction(meta: RequestMetadata, request: StopTransactionReq)
                    : OperationExecution<StopTransactionReq, StopTransactionResp> = throw NotImplementedError()

            override fun statusNotification(meta: RequestMetadata, request: StatusNotificationReq)
                    : OperationExecution<StatusNotificationReq, StatusNotificationResp> = throw NotImplementedError()

            override fun dataTransfer(meta: RequestMetadata, request: DataTransferReq)
                    : OperationExecution<DataTransferReq, DataTransferResp> = throw NotImplementedError()

            override fun bootNotification(meta: RequestMetadata, request: BootNotificationReq)
                    : OperationExecution<BootNotificationReq, BootNotificationResp> = throw NotImplementedError()

            override fun firmwareStatusNotification(meta: RequestMetadata, request: FirmwareStatusNotificationReq)
                    : OperationExecution<FirmwareStatusNotificationReq, FirmwareStatusNotificationResp> =
                throw NotImplementedError()

            override fun diagnosticsStatusNotification(meta: RequestMetadata, request: DiagnosticsStatusNotificationReq)
                    : OperationExecution<DiagnosticsStatusNotificationReq, DiagnosticsStatusNotificationResp> =
                throw NotImplementedError()
        }

        val path = "ws"
        val server: CSMS = CSMSOcppServer(
            CSMSSettings(port = port,
                servers = listOf(
                    ServerSetting(
                        path = path,
                        ocppVersion = setOf(OcppVersionTransport.OCPP_1_6),
                        transportType = TransportEnum.WEBSOCKET
                    )
                )
            ),
            csmsApiCallbacks = listOf(csmsApi16),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        server.start()

        sleep(1000)

        //Create a CS in ocpp16 and connect to the CSMS
        val ocppCSCallbacks = object : OcppCSCallbacks16 {
            override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 =
                RemoteStartTransactionResp16(
                    status = if (req.idTag == idTag) RemoteStartStopStatus16.Accepted
                    else RemoteStartStopStatus16.Rejected
                )
        }


        val chargePointId = "my-chargepoint1"

        //establish a connection to the CSMS
        val connection = Ocpp16ConnectionToCSMS(
            chargePointId = chargePointId,
            csmsUrl = "ws://localhost:$port/$path",
            transportType = TransportEnum.WEBSOCKET,
            ocppCSCallbacks = ocppCSCallbacks
        )
        connection.connect()

        sleep(1000)
        //send an authorize request to the CSMS
        val responseAuthorize: AuthorizeResp16 =
            connection.authorize(RequestMetadata(chargePointId), AuthorizeReq16(idTag = idTag)).response

        //We're checking if the Authorization request has been accepted by the CSMS.
        expectThat(responseAuthorize.idTagInfo.status).isEqualTo(AuthorizationStatus16.Accepted)

        val api16 = server.getCSApi16()
        val responseRemote: RemoteStartTransactionResp16 = api16.remoteStartTransaction(
            RequestMetadata(chargePointId),
            RemoteStartTransactionReq16(
                idTag = idTag
            )
        ).response

        expectThat(responseRemote.status).isEqualTo(RemoteStartStopStatus16.Accepted)

        connection.close()
        server.stop()
    }
}





