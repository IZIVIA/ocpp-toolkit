package com.izivia.ocpp.core12.test

import com.izivia.ocpp.core12.CSMSOperations
import com.izivia.ocpp.core12.ChargePointOperations
import com.izivia.ocpp.core12.model.authorize.AuthorizeReq
import com.izivia.ocpp.core12.model.authorize.AuthorizeResp
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationReq
import com.izivia.ocpp.core12.model.bootnotification.BootNotificationResp
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core12.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationReq
import com.izivia.ocpp.core12.model.changeconfiguration.ChangeConfigurationResp
import com.izivia.ocpp.core12.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core12.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core12.model.common.IdTagInfo
import com.izivia.ocpp.core12.model.common.enumeration.AuthorizationStatus
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core12.model.getdiagnostics.GetDiagnosticsResp
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatReq
import com.izivia.ocpp.core12.model.heartbeat.HeartbeatResp
import com.izivia.ocpp.core12.model.metervalues.MeterValuesReq
import com.izivia.ocpp.core12.model.metervalues.MeterValuesResp
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core12.model.remotestart.RemoteStartTransactionResp
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionReq
import com.izivia.ocpp.core12.model.remotestop.RemoteStopTransactionResp
import com.izivia.ocpp.core12.model.reset.ResetReq
import com.izivia.ocpp.core12.model.reset.ResetResp
import com.izivia.ocpp.core12.model.reset.enumeration.ResetStatus
import com.izivia.ocpp.core12.model.reset.enumeration.ResetType
import com.izivia.ocpp.core12.model.statusnotification.StatusNotificationReq
import com.izivia.ocpp.core12.model.stoptransaction.StopTransactionReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core12.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core12.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
import com.izivia.ocpp.transport.ClientTransport
import kotlinx.datetime.Instant
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import kotlin.reflect.KClass

class CoreTest {
    @Test
    fun `charge point sends heartbeat with OCPP 1-2 action`() {
        val transport = FakeClientTransport()
        transport.responses["Heartbeat"] = HeartbeatResp(Instant.parse("2026-01-01T00:00:00Z"))

        val operations = ChargePointOperations.newChargePointOperations("CP001", transport, unsupportedCsmsOperations())
        val response = operations.heartbeat(RequestMetadata("CP001"), HeartbeatReq())

        expectThat(transport.sentActions.toList()).isEqualTo(listOf("Heartbeat"))
        expectThat(response.response.currentTime).isEqualTo(Instant.parse("2026-01-01T00:00:00Z"))
    }

    @Test
    fun `charge point sends authorize with OCPP 1-2 action`() {
        val transport = FakeClientTransport()
        transport.responses["Authorize"] = AuthorizeResp(IdTagInfo(status = AuthorizationStatus.Accepted))

        val operations = ChargePointOperations.newChargePointOperations("CP001", transport, unsupportedCsmsOperations())
        val response = operations.authorize(RequestMetadata("CP001"), AuthorizeReq("ABC"))

        expectThat(transport.sentActions.toList()).isEqualTo(listOf("Authorize"))
        expectThat(response.response.idTagInfo.status).isEqualTo(AuthorizationStatus.Accepted)
    }

    @Test
    fun `charge point registers reset callback from central system`() {
        val transport = FakeClientTransport()
        ChargePointOperations.newChargePointOperations(
            "CP001",
            transport,
            object : CSMSOperations by unsupportedCsmsOperations() {
                override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
                    OperationExecution(
                        ExecutionMetadata(meta, RequestStatus.SUCCESS),
                        req,
                        ResetResp(ResetStatus.Accepted)
                    )
            }
        )

        expectThat(transport.handlers["Reset"]).isNotNull()
        val response = transport.handlers.getValue("Reset").invoke(ResetReq(ResetType.Hard))
        expectThat(response).isEqualTo(ResetResp(ResetStatus.Accepted))
    }

    private class FakeClientTransport : ClientTransport {
        val sentActions = mutableListOf<String>()
        val responses = mutableMapOf<String, Any>()
        val handlers = mutableMapOf<String, (Any) -> Any?>()

        override fun connect() = Unit
        override fun close() = Unit

        @Suppress("UNCHECKED_CAST")
        override fun <T, P : Any> sendMessageClass(clazz: KClass<P>, action: String, message: T): P {
            sentActions += action
            return responses.getValue(action) as P
        }

        @Suppress("UNCHECKED_CAST")
        override fun <T : Any, P> receiveMessageClass(clazz: KClass<T>, action: String, fn: (T) -> P) {
            handlers[action] = { req -> fn(req as T) }
        }
    }

    private fun unsupportedCsmsOperations(): CSMSOperations =
        object : CSMSOperations {
            override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> =
                throw NotImplementedError()

            override fun changeAvailability(
                meta: RequestMetadata,
                req: ChangeAvailabilityReq
            ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> = throw NotImplementedError()

            override fun changeConfiguration(
                meta: RequestMetadata,
                req: ChangeConfigurationReq
            ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> = throw NotImplementedError()

            override fun clearCache(
                meta: RequestMetadata,
                req: ClearCacheReq
            ): OperationExecution<ClearCacheReq, ClearCacheResp> = throw NotImplementedError()

            override fun remoteStartTransaction(
                meta: RequestMetadata,
                req: RemoteStartTransactionReq
            ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> = throw NotImplementedError()

            override fun remoteStopTransaction(
                meta: RequestMetadata,
                req: RemoteStopTransactionReq
            ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> = throw NotImplementedError()

            override fun unlockConnector(
                meta: RequestMetadata,
                req: UnlockConnectorReq
            ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> = throw NotImplementedError()

            override fun updateFirmware(
                meta: RequestMetadata,
                req: UpdateFirmwareReq
            ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> = throw NotImplementedError()

            override fun getDiagnostics(
                meta: RequestMetadata,
                req: GetDiagnosticsReq
            ): OperationExecution<GetDiagnosticsReq, GetDiagnosticsResp> = throw NotImplementedError()
        }
}
