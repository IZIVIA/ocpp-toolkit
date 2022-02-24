package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.*
import fr.simatix.cs.simulator.transport.Transport
import java.net.ConnectException

/**
 * Operations initiated by Charge Point
 */
interface ChargePointOperations {
    companion object {
        fun newChargePointOperations(transport: Transport) = RealChargePointOperations(transport)
    }

    /**
     * Sends a Heartbeat request to let the Central System know the Charge Point is still connected
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): CoreExecution<HeartbeatResp>

    /**
     * Sends an Authorize request to the Central System if the identifier is authorize to start/stop
     * to charge.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun authorize(meta: RequestMetadata, request: AuthorizeReq): CoreExecution<AuthorizeResp>

    /**
     * Sends periodic, possibly clock-aligned MeterValues
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): CoreExecution<MeterValuesResp>

    /**
     * Sends a StartTransaction request to the Central System to inform about a transaction that has been started.
     * If this transaction ends a reservation the StartTransaction.req MUST contain the reservationId.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun startTransaction(meta: RequestMetadata, request: StartTransactionReq): CoreExecution<StartTransactionResp>

    /**
     * Sends a StopTransaction request to the Central System to inform about a transaction that has been stopped.
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun stopTransaction(meta: RequestMetadata, request: StopTransactionReq): CoreExecution<StopTransactionResp>
}