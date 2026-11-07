package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.ChargingStationConfig
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import kotlin.reflect.KClass
import kotlin.time.Clock

interface ServerTransport {

    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P

    fun <T : Any, P> receiveMessageClass(clazz: KClass<T>,
                                         action: String,
                                         ocppVersion: OcppVersion,
                                         onAction: (RequestMetadata, T) -> P,
                                         accept: (String) -> ChargingStationConfig)

    fun canSendToChargingStation(chargingStationConfig: ChargingStationConfig): Boolean

}

inline fun <T, reified P : Any> ServerTransport.sendMessage(csOcppId: String, action: String, message: T): P =
    sendMessageClass(P::class, csOcppId, action, message)

inline fun <T, reified P : Any> ServerTransport.execute(
    meta: RequestMetadata,
    csOcppId: String,
    action: String,
    request: T
): OperationExecution<T, P> {
    val requestTime = Clock.System.now()
    val response: P = sendMessage(csOcppId, action, request)
    val responseTime = Clock.System.now()
    return OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
        request,
        response
    )
}

fun Iterable<ServerTransport>.transportFor(
    csOcppId: String,
    accept: (String) -> ChargingStationConfig
): ServerTransport {
    val config = accept(csOcppId)
    return firstOrNull { it.canSendToChargingStation(config) }
        ?: throw IllegalStateException("No transport to send a message to $csOcppId")
}

inline fun <reified T : Any, P> ServerTransport.receiveMessage(
    action: String,
    ocppVersion: OcppVersion,
    noinline onAction: (RequestMetadata, T) -> P,
    noinline accept: (String) -> ChargingStationConfig
) = receiveMessageClass(T::class, action, ocppVersion, onAction, accept)
