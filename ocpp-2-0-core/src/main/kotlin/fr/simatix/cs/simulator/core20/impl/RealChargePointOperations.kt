package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.api.model.RequestStatus
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.*
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core20.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core20.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core20.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventReq
import fr.simatix.cs.simulator.core20.model.transactionevent.TransactionEventResp
import fr.simatix.cs.simulator.transport.Transport
import fr.simatix.cs.simulator.transport.sendMessage
import kotlinx.datetime.Clock
import java.net.ConnectException

class RealChargePointOperations(private val client: Transport) : ChargePointOperations {

    private inline fun <T, reified P> sendMessage(meta: RequestMetadata, action: String, request: T): CoreExecution<P> {
        val requestTime = Clock.System.now()
        val response: P = client.sendMessage(action, request)
        val responseTime = Clock.System.now()
        return CoreExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime), response)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(
        meta: RequestMetadata,
        request: HeartbeatReq
    ): CoreExecution<HeartbeatResp> {
        return sendMessage(meta, "Heartbeat", request)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun authorize(meta: RequestMetadata, request: AuthorizeReq): CoreExecution<AuthorizeResp> {
        return sendMessage(meta, "Authorize", request)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun meterValues(meta: RequestMetadata, request: MeterValuesReq): CoreExecution<MeterValuesResp> {
        return sendMessage(meta, "MeterValues", request)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun transactionEvent(
        meta: RequestMetadata,
        request: TransactionEventReq
    ): CoreExecution<TransactionEventResp> {
        return sendMessage(meta, "TransactionEvent", request)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun statusNotification(
        meta: RequestMetadata,
        request: StatusNotificationReq
    ): CoreExecution<StatusNotificationResp> {
        return sendMessage(meta, "StatusNotification", request)
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun dataTransfer(meta: RequestMetadata, request: DataTransferReq): CoreExecution<DataTransferResp> {
        return sendMessage(meta, "DataTransfer", request)
    }
}
