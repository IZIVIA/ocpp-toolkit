package com.izivia.ocpp.integration.test

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
import com.izivia.ocpp.core20.model.authorize.AuthorizeReq
import com.izivia.ocpp.core20.model.authorize.AuthorizeResp
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitReq
import com.izivia.ocpp.core20.model.clearedcharginglimit.ClearedChargingLimitResp
import com.izivia.ocpp.core20.model.common.IdTokenInfoType
import com.izivia.ocpp.core20.model.common.IdTokenType
import com.izivia.ocpp.core20.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.core20.model.common.enumeration.IdTokenEnumType
import com.izivia.ocpp.core20.model.common.enumeration.RequestStartStopStatusEnumType
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusReq
import com.izivia.ocpp.core20.model.getcertificatestatus.GetCertificateStatusResp
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.core20.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitReq
import com.izivia.ocpp.core20.model.notifycharginglimit.NotifyChargingLimitResp
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationReq
import com.izivia.ocpp.core20.model.notifycustomerinformation.NotifyCustomerInformationResp
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesReq
import com.izivia.ocpp.core20.model.notifydisplaymessages.NotifyDisplayMessagesResp
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsReq
import com.izivia.ocpp.core20.model.notifyevchargingneeds.NotifyEVChargingNeedsResp
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleReq
import com.izivia.ocpp.core20.model.notifyevchargingschedule.NotifyEVChargingScheduleResp
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventReq
import com.izivia.ocpp.core20.model.notifyevent.NotifyEventResp
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportReq
import com.izivia.ocpp.core20.model.notifymonitoringreport.NotifyMonitoringReportResp
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportReq
import com.izivia.ocpp.core20.model.notifyreport.NotifyReportResp
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationReq
import com.izivia.ocpp.core20.model.publishfirmwarestatusnotification.PublishFirmwareStatusNotificationResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import com.izivia.ocpp.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateReq
import com.izivia.ocpp.core20.model.reservationstatusupdate.ReservationStatusUpdateResp
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationReq
import com.izivia.ocpp.core20.model.securityeventnotification.SecurityEventNotificationResp
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateReq
import com.izivia.ocpp.core20.model.signcertificate.SignCertificateResp
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventReq
import com.izivia.ocpp.core20.model.transactionevent.TransactionEventResp
import com.izivia.ocpp.integration.ApiFactory.Companion.csmsOcppServer
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp16ConnectionToCSMS
import com.izivia.ocpp.integration.ApiFactory.Companion.ocpp20ConnectionToCSMS
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
import com.izivia.ocpp.api16.OcppCSCallbacks as OcppCSCallbacks16
import com.izivia.ocpp.api20.OcppCSCallbacks as OcppCSCallbacks20
import com.izivia.ocpp.core16.ChargePointOperations as ChargePointOperations16
import com.izivia.ocpp.core16.model.authorize.AuthorizeReq as AuthorizeReq16
import com.izivia.ocpp.core16.model.authorize.AuthorizeResp as AuthorizeResp16
import com.izivia.ocpp.core16.model.common.enumeration.AuthorizationStatus as AuthorizationStatus16
import com.izivia.ocpp.core16.model.common.enumeration.RemoteStartStopStatus as RemoteStartStopStatus16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionReq as RemoteStartTransactionReq16
import com.izivia.ocpp.core16.model.remotestart.RemoteStartTransactionResp as RemoteStartTransactionResp16
import com.izivia.ocpp.core20.ChargePointOperations as ChargePointOperations20
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationReq as BootNotificationReq20
import com.izivia.ocpp.core20.model.bootnotification.BootNotificationResp as BootNotificationResp20
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq as DataTransferReq20
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp as DataTransferResp20
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationReq as FirmwareStatusNotificationReq20
import com.izivia.ocpp.core20.model.firmwarestatusnotification.FirmwareStatusNotificationResp as FirmwareStatusNotificationResp20
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatReq as HeartbeatReq20
import com.izivia.ocpp.core20.model.heartbeat.HeartbeatResp as HeartbeatResp20
import com.izivia.ocpp.core20.model.metervalues.MeterValuesReq as MeterValuesReq20
import com.izivia.ocpp.core20.model.metervalues.MeterValuesResp as MeterValuesResp20
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationReq as StatusNotificationReq20
import com.izivia.ocpp.core20.model.statusnotification.StatusNotificationResp as StatusNotificationResp20
import com.izivia.ocpp.transport.OcppVersion as OcppVersionTransport

class ExampleCSApiTest {

    @Test
    fun `csms api with 2 ocpp version on 1 websocket`() {
        val port = 12345
        val idTag = "Tag2"

        // Create and start the CSMS server
        val csmsApi16 = object : ChargePointOperations16 {

            override fun connect() = throw NotImplementedError()

            override fun close() = throw NotImplementedError()

            override fun heartbeat(meta: RequestMetadata, request: HeartbeatReq)
                    : OperationExecution<HeartbeatReq, HeartbeatResp> = throw NotImplementedError()

            override fun authorize(meta: RequestMetadata, request: AuthorizeReq16)
            : OperationExecution<AuthorizeReq16, AuthorizeResp16> =
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

        val csmsApi20 = object : ChargePointOperations20 {
            override fun heartbeat(meta: RequestMetadata, request: HeartbeatReq20)
                    : OperationExecution<HeartbeatReq20, HeartbeatResp20> = throw NotImplementedError()

            override fun authorize(meta: RequestMetadata, request: AuthorizeReq)
            : OperationExecution<AuthorizeReq, AuthorizeResp> =
                OperationExecution(
                    ExecutionMetadata(meta, RequestStatus.SUCCESS),
                    request,
                    AuthorizeResp(
                        idTokenInfo = IdTokenInfoType(
                            if (request.idToken.idToken == idTag)
                                AuthorizationStatusEnumType.Invalid
                            else AuthorizationStatusEnumType.Accepted
                        )
                    )
                )

            override fun meterValues(meta: RequestMetadata, request: MeterValuesReq20)
                    : OperationExecution<MeterValuesReq20, MeterValuesResp20> = throw NotImplementedError()

            override fun transactionEvent(meta: RequestMetadata, request: TransactionEventReq)
                    : OperationExecution<TransactionEventReq, TransactionEventResp> = throw NotImplementedError()

            override fun statusNotification(meta: RequestMetadata, request: StatusNotificationReq20)
                    : OperationExecution<StatusNotificationReq20, StatusNotificationResp20> =
                throw NotImplementedError()

            override fun dataTransfer(meta: RequestMetadata, request: DataTransferReq20)
                    : OperationExecution<DataTransferReq20, DataTransferResp20> = throw NotImplementedError()

            override fun bootNotification(meta: RequestMetadata, request: BootNotificationReq20)
                    : OperationExecution<BootNotificationReq20, BootNotificationResp20> = throw NotImplementedError()

            override fun notifyReport(meta: RequestMetadata, request: NotifyReportReq)
                    : OperationExecution<NotifyReportReq, NotifyReportResp> = throw NotImplementedError()

            override fun firmwareStatusNotification(meta: RequestMetadata, request: FirmwareStatusNotificationReq20)
                    : OperationExecution<FirmwareStatusNotificationReq20, FirmwareStatusNotificationResp20> =
                throw NotImplementedError()

            override fun clearedChargingLimit(meta: RequestMetadata, request: ClearedChargingLimitReq)
                    : OperationExecution<ClearedChargingLimitReq, ClearedChargingLimitResp> =
                throw NotImplementedError()

            override fun getCertificateStatus(meta: RequestMetadata, request: GetCertificateStatusReq)
                    : OperationExecution<GetCertificateStatusReq, GetCertificateStatusResp> =
                throw NotImplementedError()

            override fun notifyCustomerInformation(meta: RequestMetadata, request: NotifyCustomerInformationReq)
                    : OperationExecution<NotifyCustomerInformationReq, NotifyCustomerInformationResp> =
                throw NotImplementedError()

            override fun notifyEvent(meta: RequestMetadata, request: NotifyEventReq)
                    : OperationExecution<NotifyEventReq, NotifyEventResp> = throw NotImplementedError()

            override fun notifyEVChargingSchedule(meta: RequestMetadata, request: NotifyEVChargingScheduleReq)
                    : OperationExecution<NotifyEVChargingScheduleReq, NotifyEVChargingScheduleResp> =
                throw NotImplementedError()

            override fun notifyChargingLimit(meta: RequestMetadata, request: NotifyChargingLimitReq)
                    : OperationExecution<NotifyChargingLimitReq, NotifyChargingLimitResp> = throw NotImplementedError()

            override fun notifyDisplayMessages(meta: RequestMetadata, request: NotifyDisplayMessagesReq)
                    : OperationExecution<NotifyDisplayMessagesReq, NotifyDisplayMessagesResp> =
                throw NotImplementedError()

            override fun notifyEVChargingNeeds(meta: RequestMetadata, request: NotifyEVChargingNeedsReq)
                    : OperationExecution<NotifyEVChargingNeedsReq, NotifyEVChargingNeedsResp> =
                throw NotImplementedError()

            override fun logStatusNotification(meta: RequestMetadata, request: LogStatusNotificationReq)
                    : OperationExecution<LogStatusNotificationReq, LogStatusNotificationResp> =
                throw NotImplementedError()

            override fun publishFirmwareStatusNotification(
                meta: RequestMetadata,
                request: PublishFirmwareStatusNotificationReq
            )
                    : OperationExecution<PublishFirmwareStatusNotificationReq, PublishFirmwareStatusNotificationResp> =
                throw NotImplementedError()

            override fun notifyMonitoringReport(meta: RequestMetadata, request: NotifyMonitoringReportReq)
                    : OperationExecution<NotifyMonitoringReportReq, NotifyMonitoringReportResp> =
                throw NotImplementedError()

            override fun reservationStatusUpdate(meta: RequestMetadata, request: ReservationStatusUpdateReq)
                    : OperationExecution<ReservationStatusUpdateReq, ReservationStatusUpdateResp> =
                throw NotImplementedError()

            override fun securityEventNotification(meta: RequestMetadata, request: SecurityEventNotificationReq)
                    : OperationExecution<SecurityEventNotificationReq, SecurityEventNotificationResp> =
                throw NotImplementedError()

            override fun signCertificate(meta: RequestMetadata, request: SignCertificateReq)
                    : OperationExecution<SignCertificateReq, SignCertificateResp> = throw NotImplementedError()

            override fun reportChargingProfiles(meta: RequestMetadata, request: ReportChargingProfilesReq)
                    : OperationExecution<ReportChargingProfilesReq, ReportChargingProfilesResp> =
                throw NotImplementedError()

            override fun connect() = throw NotImplementedError()

            override fun close() = throw NotImplementedError()

        }

        val path = "ws"
        val server: CSMS = csmsOcppServer(
            CSMSSettings(
                servers = listOf(
                    ServerSetting(
                        port = port,
                        path = path,
                        ocppVersion = setOf(OcppVersionTransport.OCPP_1_6, OcppVersionTransport.OCPP_2_0),
                        transportType = TransportEnum.WEBSOCKET
                    )
                )
            ),
            csmsApiCallbacks = listOf(csmsApi16, csmsApi20),
            fn = { ChargingStationConfig(acceptConnection = true, soapUrl = null) }
        )

        server.start()

        //Create a CS in ocpp16 and connect to the CSMS
        val ocppCSCallbacks16 = object : OcppCSCallbacks16 {
            override fun remoteStartTransaction(req: RemoteStartTransactionReq16): RemoteStartTransactionResp16 =
                RemoteStartTransactionResp16(
                    status = if (req.idTag == idTag) RemoteStartStopStatus16.Accepted
                    else RemoteStartStopStatus16.Rejected
                )
        }

        val chargePointId1 = "my-chargepoint1"

        //establish a connection to the CSMS
        val connection16 = ocpp16ConnectionToCSMS(
            chargePointId = chargePointId1,
            csmsUrl = "ws://localhost:$port/$path",
            transportType = TransportEnum.WEBSOCKET,
            clientPath = null,
            clientPort = null,
            ocppCSCallbacks = ocppCSCallbacks16
        )
        connection16.connect()

        //send an authorize request to the CSMS
        val responseAuthorize16: AuthorizeResp16 =
            connection16.authorize(RequestMetadata(chargePointId1), AuthorizeReq16(idTag = idTag)).response

        //We're checking if the Authorization request has been accepted by the CSMS.
        expectThat(responseAuthorize16.idTagInfo.status).isEqualTo(AuthorizationStatus16.Accepted)


        //Create a CS in ocpp20 and connect to the CSMS
        val ocppCSCallbacks20 = object : OcppCSCallbacks20 {
            override fun requestStartTransaction(req: RequestStartTransactionReq): RequestStartTransactionResp =
                RequestStartTransactionResp(
                    if (req.idToken.idToken == idTag) RequestStartStopStatusEnumType.Rejected
                    else RequestStartStopStatusEnumType.Accepted
                )
        }

        val chargePointId2 = "my-chargepoint2"

        //establish a connection to the CSMS
        val connection20 = ocpp20ConnectionToCSMS(
            chargePointId = chargePointId2,
            csmsUrl = "ws://localhost:$port/$path",
            transportType = TransportEnum.WEBSOCKET,
            clientPath = null,
            clientPort = null,
            ocppCSCallbacks = ocppCSCallbacks20
        )
        connection20.connect()

        //send an authorize request to the CSMS
        val responseAuthorize20: AuthorizeResp =
            connection20.authorize(
                RequestMetadata(chargePointId2),
                AuthorizeReq(idToken = IdTokenType(idTag, IdTokenEnumType.Central))
            ).response

        //We're checking if the Authorization request has been accepted by the CSMS.
        expectThat(responseAuthorize20.idTokenInfo.status).isEqualTo(AuthorizationStatusEnumType.Invalid)


        //Send a message to the first charge point in ocpp 1.6
        val api16 = server.getCSApi16()
        val responseRemote: RemoteStartTransactionResp16 = api16.remoteStartTransaction(
            RequestMetadata(chargePointId1),
            RemoteStartTransactionReq16(
                idTag = idTag
            )
        ).response

        expectThat(responseRemote.status).isEqualTo(RemoteStartStopStatus16.Accepted)

        //Send a message to the second charge point in ocpp 2.0
        val api20 = server.getCSApi20()
        val responseRequest: RequestStartTransactionResp = api20.requestStartTransaction(
            RequestMetadata(chargePointId2),
            RequestStartTransactionReq(
                remoteStartId = 1,
                idToken = IdTokenType(idTag, IdTokenEnumType.Central)
            )
        ).response

        expectThat(responseRequest.status).isEqualTo(RequestStartStopStatusEnumType.Rejected)

        connection16.close()
        connection20.close()
        server.stop()
    }
}





