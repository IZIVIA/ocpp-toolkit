package fr.simatix.cs.simulator.core16

import java.net.ConnectException
import kotlin.reflect.KClass

interface Transport {

    @Throws(ConnectException::class)
    fun connect()

    fun close()

    fun <T, P : Any> sendMessage(clazz: KClass<P>, action: String, message: T): P

}

inline fun <T, reified P : Any> Transport.sendMessage(action: String, message: T): P {
    return sendMessage(P::class, action, message)
}
