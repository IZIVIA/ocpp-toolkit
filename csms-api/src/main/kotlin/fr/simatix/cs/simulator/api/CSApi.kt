package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp

interface CSApi {
    fun connect()

    fun close()

    fun reset(fn: (ResetReq) -> ResetResp)

}