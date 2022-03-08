package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api.CSMSApi
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.api.model.bootnotification.ChargingStationType
import fr.simatix.cs.simulator.api.model.bootnotification.enumeration.BootReasonEnumType
import fr.simatix.cs.simulator.api.model.common.IdTokenType
import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.common.SampledValueType
import fr.simatix.cs.simulator.api.model.common.SignedMeterValueType
import fr.simatix.cs.simulator.api.model.common.enumeration.*
import fr.simatix.cs.simulator.api.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ChargePointErrorCode
import fr.simatix.cs.simulator.api.model.statusnotification.enumeration.ConnectorStatusEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.EVSEType
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.api.model.transactionevent.TransactionType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.ChargingStateEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TransactionEventEnumType
import fr.simatix.cs.simulator.api.model.transactionevent.enumeration.TriggerReasonEnumType
import fr.simatix.cs.simulator.integration.ApiFactory
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import io.simatix.ev.ocpp.OcppVersion
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import java.lang.Thread.sleep

fun heartbeat(csmsApi: CSMSApi, ocppId: String, request: HeartbeatReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.heartbeat(requestMetadata, request)
    println("Heartbeat: $response\n")
}

fun authorize(csmsApi: CSMSApi, ocppId: String, request: AuthorizeReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.authorize(requestMetadata, request)
    println("Authorize: $response\n")
}

fun meterValues(csmsApi: CSMSApi, ocppId: String, request: MeterValuesReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.meterValues(requestMetadata, request)
    println("MeterValues : $response\n")
}

fun dataTransfer(csmsApi: CSMSApi, ocppId: String, request: DataTransferReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.dataTransfer(requestMetadata, request)
    println("DataTransfer: $response\n")
}

fun bootNotification(csmsApi: CSMSApi, ocppId: String, request: BootNotificationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.bootNotification(requestMetadata, request)
    println("BootNotification: $response\n")
}

fun transactionEvent(csmsApi: CSMSApi, ocppId: String, request: TransactionEventReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.transactionEvent(requestMetadata, request)
    println("TransactionEvent: $response\n")
}

fun statusNotification(csmsApi: CSMSApi, ocppId: String, request: StatusNotificationReq) {
    val requestMetadata = RequestMetadata(ocppId)
    val response = csmsApi.statusNotification(requestMetadata, request)
    println("StatusNotification: $response\n")
}
/*
fun main(args: Array<String>) {
    if (args.isEmpty()) {
        throw IllegalArgumentException("1 argument is required")
    }

    val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = args[0])
    val ocppId = "chargePoint2"
    val csmsApi = ApiFactory.getCSMSApi(settings, ocppId)

    heartbeat(csmsApi, ocppId, HeartbeatReq())

    bootNotification(
        csmsApi,
        ocppId,
        BootNotificationReq(ChargingStationType("model", "vendor", "firmware"), BootReasonEnumType.ApplicationReset)
    )

    authorize(csmsApi, ocppId, AuthorizeReq(idToken = IdTokenType("Tag1", IdTokenEnumType.Central)))

    meterValues(
        csmsApi, ocppId, MeterValuesReq(
            3, listOf(
                MeterValueType(
                    listOf(
                        SampledValueType(0.6), SampledValueType(
                            2.4,
                            ReadingContextEnumType.SampleClock,
                            MeasurandEnumType.Temperature,
                            PhaseEnumType.L1,
                            LocationEnumType.Body,
                            signedMeterValue = SignedMeterValueType("Hello", "Bye", "Welcome", "Nice")
                        )
                    ),
                    Instant.parse("2022-02-15T00:00:00.000Z"),
                )
            )
        )
    )

    dataTransfer(csmsApi, ocppId, DataTransferReq("vendor", "msgId12", "Hello"))

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Started,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2", chargingState = ChargingStateEnumType.EVConnected),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionBegin)),
                    Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            evse = EVSEType(1, 1)
        )
    )

    sleep(8000)

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Updated,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2", chargingState = ChargingStateEnumType.Charging),
            evse = EVSEType(1)
        )
    )

    sleep(8000)

    statusNotification(
        csmsApi,
        ocppId,
        StatusNotificationReq(
            connectorId = 1,
            connectorStatus = ConnectorStatusEnumType.Faulted,
            evseId = 1,
            timestamp = Clock.System.now(),
            errorCode = ChargePointErrorCode.EVCommunicationError
        )
    )
    sleep(8000)

    transactionEvent(
        csmsApi, ocppId, TransactionEventReq(
            eventType = TransactionEventEnumType.Ended,
            timestamp = Clock.System.now(),
            triggerReason = TriggerReasonEnumType.Authorized,
            seqNo = 0,
            transactionInfo = TransactionType("2",ChargingStateEnumType.SuspendedEVSE),
            meterValue = listOf(
                MeterValueType(
                    listOf(SampledValueType(10.0, ReadingContextEnumType.TransactionEnd)),
                    Instant.parse("2022-02-15T00:00:00.000Z")
                )
            ),
            idToken = IdTokenType("Tag1", IdTokenEnumType.Central),
            evse = EVSEType(1, 1)
        )
    )
}
*/