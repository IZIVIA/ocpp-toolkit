package fr.simatix.cs.simulator.core20

import fr.simatix.cs.simulator.core20.model.HeartbeatRequest
import fr.simatix.cs.simulator.core20.model.HeartbeatResponse
import java.net.ConnectException

interface Transport {

    @Throws(ConnectException::class)
    fun connect()

    fun close()

    fun sendMessage(payload: HeartbeatRequest): HeartbeatResponse
}
