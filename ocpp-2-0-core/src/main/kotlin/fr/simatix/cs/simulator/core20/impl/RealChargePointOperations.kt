package fr.simatix.cs.simulator.core20.impl

import fr.simatix.cs.simulator.api.model.ExecutionMetadata
import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.api.model.enumeration.RequestStatus
import fr.simatix.cs.simulator.core20.ChargePointOperations
import fr.simatix.cs.simulator.core20.model.*
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
}
