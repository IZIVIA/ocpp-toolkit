package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import java.net.ConnectException
import kotlin.reflect.KClass
import kotlin.time.Clock

interface ClientTransport {

    @Throws(ConnectException::class)
    fun connect()

    fun close()

    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P

    fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P)

}

inline fun <T, reified P : Any> ClientTransport.sendMessage(action: String, message: T): P =
    sendMessageClass(P::class, action, message)

inline fun <T, reified P : Any> ClientTransport.execute(
    meta: RequestMetadata,
    action: String,
    request: T
): OperationExecution<T, P> {
    val requestTime = Clock.System.now()
    val response: P = sendMessage(action, request)
    val responseTime = Clock.System.now()
    return OperationExecution(
        ExecutionMetadata(meta, RequestStatus.SUCCESS, requestTime, responseTime),
        request,
        response
    )
}

inline fun <reified T : Any, P> ClientTransport.receiveMessage(action: String, noinline fn: (T) -> P) = receiveMessageClass(
    T::class, action, fn
)
