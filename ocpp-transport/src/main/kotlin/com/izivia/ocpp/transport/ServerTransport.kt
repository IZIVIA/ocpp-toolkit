package com.izivia.ocpp.transport

import com.izivia.ocpp.operation.information.RequestMetadata
import kotlin.reflect.KClass

interface ServerTransport {

    fun start()

    fun stop()

    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, csOcppId: String, action: String, message: T): P

    fun <T : Any, P> receiveMessageClass(clazz: KClass<T>,
                                         action: String,
                                         ocppVersion: OcppVersion,
                                         onAction: (RequestMetadata, T) -> P,
                                         accept: (String) -> Boolean)

}

inline fun <T, reified P : Any> ServerTransport.sendMessage(csOcppId: String, action: String, message: T): P =
    sendMessageClass(P::class, csOcppId, action, message)

inline fun <reified T : Any, P> ServerTransport.receiveMessage(
    action: String,
    ocppVersion: OcppVersion,
    noinline onAction: (RequestMetadata, T) -> P,
    noinline accept: (String) -> Boolean
) = receiveMessageClass(T::class, action, ocppVersion, onAction, accept)