package fr.simatix.cs.simulator.core16

import fr.simatix.cs.simulator.api.model.RequestMetadata
import fr.simatix.cs.simulator.core16.impl.RealChargePointOperations
import fr.simatix.cs.simulator.core16.model.CoreExecution
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.core16.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationReq
import fr.simatix.cs.simulator.core16.model.bootnotification.BootNotificationResp
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.core16.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionReq
import fr.simatix.cs.simulator.core16.model.starttransaction.StartTransactionResp
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationReq
import fr.simatix.cs.simulator.core16.model.statusnotification.StatusNotificationResp
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionReq
import fr.simatix.cs.simulator.core16.model.stoptransaction.StopTransactionResp
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

    /**
     * Sends a notification to the Central System about a status change or an error within the Charge Point
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun statusNotification(meta: RequestMetadata, request: StatusNotificationReq): CoreExecution<StatusNotificationResp>

    /**
     * Sends information to the Central System for a function not supported by OCPP
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun dataTransfer(meta: RequestMetadata, request: DataTransferReq): CoreExecution<DataTransferResp>

    /**
     * Sends a request to the Central System with information about its configuration
     * each time it boots or reboots
     */
    @Throws(IllegalStateException::class, ConnectException::class)
    fun bootNotification(meta: RequestMetadata, request: BootNotificationReq): CoreExecution<BootNotificationResp>
}