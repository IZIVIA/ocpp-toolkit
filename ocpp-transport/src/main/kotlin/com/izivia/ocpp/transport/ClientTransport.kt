package com.izivia.ocpp.transport

import java.net.ConnectException
import kotlin.reflect.KClass

interface ClientTransport {

    @Throws(ConnectException::class)
    fun connect()

    fun close()

    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P

    fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P)

}

inline fun <T, reified P : Any> ClientTransport.sendMessage(action: String, message: T): P =
    sendMessageClass(P::class, action, message)

inline fun <reified T : Any, P> ClientTransport.receiveMessage(action: String, noinline fn: (T) -> P) = receiveMessageClass(
    T::class, action, fn
)
