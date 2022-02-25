package fr.simatix.cs.simulator.api

import fr.simatix.cs.simulator.api.model.*
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeReq
import fr.simatix.cs.simulator.api.model.authorize.AuthorizeResp
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatReq
import fr.simatix.cs.simulator.api.model.heartbeat.HeartbeatResp
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp

interface CSMSApi {

    fun heartbeat(meta: RequestMetadata, request: HeartbeatReq): OperationExecution<HeartbeatReq, HeartbeatResp>

    fun authorize(meta: RequestMetadata, request: AuthorizeReq): OperationExecution<AuthorizeReq, AuthorizeResp>

    fun meterValues(meta: RequestMetadata, request: MeterValuesReq): OperationExecution<MeterValuesReq, MeterValuesResp>
}
