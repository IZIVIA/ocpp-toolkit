package fr.simatix.cs.simulator.core.impl

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import fr.simatix.cs.simulator.core.ChargePointOperations
import fr.simatix.cs.simulator.core.model.HeartbeatRequest
import fr.simatix.cs.simulator.core.model.HeartbeatResponse
import fr.simatix.cs.simulator.websocket.WebsocketClient
import java.net.ConnectException

class ChargePointOperationsImpl(target: String, ocppId: String) : ChargePointOperations {
    private val client = WebsocketClient(target, ocppId)
    private val mapper = jacksonObjectMapper()

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun heartbeat(): String {
        val heartbeatRequest = HeartbeatRequest()
        val responsePayload = client.sendMessage("Heartbeat", heartbeatRequest.toString())
        val responseCurrentTime = mapper.readValue<HeartbeatResponse>(responsePayload).currentTime
        //TODO "Update the twin device"
        return responsePayload
    }
}
