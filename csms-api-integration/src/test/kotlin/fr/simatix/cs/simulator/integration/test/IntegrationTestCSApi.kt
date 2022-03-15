package fr.simatix.cs.simulator.integration.test

import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.api.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.api.model.changeavailability.enumeration.ChangeAvailabilityStatusEnumType
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.api.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.common.enumeration.RequestStartStopStatusEnumType
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.api.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.api.model.reset.ResetReq
import fr.simatix.cs.simulator.api.model.reset.ResetResp
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetEnumType
import fr.simatix.cs.simulator.api.model.reset.enumeration.ResetStatusEnumType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableResultType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.api.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.api.model.unlockconnector.enumeration.UnlockStatusEnumType
import fr.simatix.cs.simulator.integration.ApiFactory
import fr.simatix.cs.simulator.integration.model.Settings
import fr.simatix.cs.simulator.integration.model.TransportEnum
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import io.simatix.ev.ocpp.OcppVersion
import io.simatix.ev.ocpp.wamp.messages.WampMessage
import io.simatix.ev.ocpp.wamp.messages.WampMessageMeta
import io.simatix.ev.ocpp.wamp.messages.WampMessageType
import io.simatix.ev.ocpp.wamp.server.OcppWampServer
import io.simatix.ev.ocpp.wamp.server.OcppWampServerHandler
import kotlinx.datetime.Clock
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.spy
import org.mockito.kotlin.any
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.lang.Thread.sleep

class IntegrationTestCSApi {

    private lateinit var server: OcppWampServer
    private val port = 12345
    private lateinit var csApi : CSApi

    @BeforeEach
    fun init() {
        server = OcppWampServer.newServer(port, setOf(OcppVersion.OCPP_1_6, OcppVersion.OCPP_2_0))
        server.register(object : OcppWampServerHandler {
            override fun accept(ocppId: String): Boolean = "chargePoint2" == ocppId

            override fun onAction(meta: WampMessageMeta, msg: WampMessage): WampMessage =
                when (msg.action?.lowercase()) {
                    "heartbeat" ->
                        WampMessage.CallResult(msg.msgId, """{"currentTime":"${Clock.System.now()}"}""")
                    else -> {
                        println("unhandled action for message: ${msg.toJson()}")
                        WampMessage.CallError(msg.msgId, "{}")
                    }
                }
        })

        server.start()

        csApi = object : CSApi {

            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
                val response = if (req.type == ResetEnumType.Immediate) {
                    ResetResp(ResetStatusEnumType.Scheduled)
                } else {
                    ResetResp(ResetStatusEnumType.Rejected)
                }
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun changeAvailability(
                meta: RequestMetadata,
                req: ChangeAvailabilityReq
            ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
                val response = ChangeAvailabilityResp(ChangeAvailabilityStatusEnumType.Accepted)
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun clearCache(
                meta: RequestMetadata,
                req: ClearCacheReq
            ): OperationExecution<ClearCacheReq, ClearCacheResp> {
                val response = ClearCacheResp(ClearCacheStatusEnumType.Accepted)
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun requestStartTransaction(
                meta: RequestMetadata,
                req: RequestStartTransactionReq
            ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> {
                val response = RequestStartTransactionResp(RequestStartStopStatusEnumType.Accepted)
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun requestStopTransaction(
                meta: RequestMetadata,
                req: RequestStopTransactionReq
            ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> {
                val response = RequestStopTransactionResp(RequestStartStopStatusEnumType.Accepted)
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun setVariables(
                meta: RequestMetadata,
                req: SetVariablesReq
            ): OperationExecution<SetVariablesReq, SetVariablesResp> {
                val response = SetVariablesResp(
                    listOf(
                        SetVariableResultType(
                            SetVariableStatusEnumType.Accepted,
                            ComponentType("component"),
                            VariableType("name")
                        )
                    )
                )
                return OperationExecution(ExecutionMetadata(meta, RequestStatus.SUCCESS), req, response)
            }

            override fun unlockConnector(
                meta: RequestMetadata,
                req: UnlockConnectorReq
            ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
                val response = UnlockConnectorResp(UnlockStatusEnumType.Unlocked)
                return OperationExecution(ExecutionMetadata(meta,RequestStatus.SUCCESS),req,response)
            }
        }
    }

    @Test
    fun `1-6 test`() {

        val settings = Settings(OcppVersion.OCPP_1_6, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"

        val csApiSpy = spy(csApi)
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApiSpy)

        csmsApi.connect()

        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "ChangeAvailability", "{\"connectorId\": 1,\"type\": \"Operative\"}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "ChangeConfiguration", "{\"key\": \"key\",\"value\": \"empty\"}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "ClearCache", "{}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "RemoteStartTransaction", "{\"idTag\": \"Tag1\",\"connectorId\": 2}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "RemoteStopTransaction", "{\"transactionId\": 15}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "UnlockConnector", "{\"connectorId\": 2}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "Reset", "{\"type\": \"Hard\"}"))

        verify(csApiSpy,times(1)).reset(any(),any())
        verify(csApiSpy,times(1)).changeAvailability(any(),any())
        verify(csApiSpy,times(1)).setVariables(any(),any())
        verify(csApiSpy,times(1)).clearCache(any(),any())
        verify(csApiSpy,times(1)).requestStartTransaction(any(),any())
        verify(csApiSpy,times(1)).requestStopTransaction(any(),any())
        verify(csApiSpy,times(1)).unlockConnector(any(),any())

        csmsApi.close()
    }

    @Test
    fun `2-0 test`() {
        val settings = Settings(OcppVersion.OCPP_2_0, TransportEnum.WEBSOCKET, target = "ws://localhost:$port/ws")
        val ocppId = "chargePoint2"

        val csApiSpy = spy(csApi)
        val csmsApi = ApiFactory.getCSMSApi(settings, ocppId, csApiSpy)

        csmsApi.connect()

        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "ChangeAvailability", "{\"operationalStatus\": \"Operative\"}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "SetVariables", "{\"setVariableData\": [ {\"attributeValue\": \"value\", \"component\": {\"name\": \"component1\"}, \"variable\": {\"name\":\"variable1\"}} ]}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "ClearCache", "{}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "RequestStartTransaction", "{\"remoteStartId\": 12,\"idToken\": {\"idToken\": \"Tag1\", \"type\": \"Central\"}}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "RequestStopTransaction", "{\"transactionId\": \"15\"}"))
        server.sendBlocking("chargePoint2", WampMessage(WampMessageType.CALL, "1",
            "UnlockConnector", "{\"connectorId\": 2,\"evseId\": 0}"))
        server.sendBlocking(
            "chargePoint2",
            WampMessage(WampMessageType.CALL, "1", "Reset", "{\"type\": \"OnIdle\"}")
        )

        verify(csApiSpy,times(1)).reset(any(),any())
        verify(csApiSpy,times(1)).changeAvailability(any(),any())
        verify(csApiSpy,times(1)).setVariables(any(),any())
        verify(csApiSpy,times(1)).clearCache(any(),any())
        verify(csApiSpy,times(1)).requestStartTransaction(any(),any())
        verify(csApiSpy,times(1)).requestStopTransaction(any(),any())
        verify(csApiSpy,times(1)).unlockConnector(any(),any())

        csmsApi.close()
    }


    @AfterEach
    fun finalize() {
        //Avoid closing the server while client are still connected
        sleep(50)
        server.stop()
    }
}