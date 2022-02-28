package fr.simatix.cs.simulator.transport

import java.net.ConnectException
import kotlin.reflect.KClass

interface Transport {

    @Throws(ConnectException::class)
    fun connect()

    fun close()

    fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P

}

inline fun <T, reified P : Any> Transport.sendMessage(action: String, message: T): P = sendMessageClass(P::class, action, message)
