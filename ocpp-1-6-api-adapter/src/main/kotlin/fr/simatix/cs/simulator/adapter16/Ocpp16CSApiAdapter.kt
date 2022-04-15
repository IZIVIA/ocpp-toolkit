package fr.simatix.cs.simulator.adapter16

import fr.simatix.cs.simulator.adapter16.mapper.*
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core16.CSMSOperations
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core16.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core16.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationResp
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core16.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core16.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core16.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core16.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationReq
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationResp
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core16.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestop.RemoteStopTransactionResp
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core16.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core16.model.reset.ResetReq
import fr.simatix.cs.simulator.core16.model.reset.ResetResp
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core16.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core16.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core16.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core16.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import org.mapstruct.factory.Mappers
import kotlin.random.Random

class Ocpp16CSApiAdapter(private val csApi: CSApi) : CSMSOperations {

    override fun reset(meta: RequestMetadata, req: ResetReq): OperationExecution<ResetReq, ResetResp> {
        val mapper: ResetMapper = Mappers.getMapper(ResetMapper::class.java)
        val response = csApi.reset(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun changeAvailability(
        meta: RequestMetadata,
        req: ChangeAvailabilityReq
    ): OperationExecution<ChangeAvailabilityReq, ChangeAvailabilityResp> {
        val mapper: ChangeAvailabilityMapper = Mappers.getMapper(ChangeAvailabilityMapper::class.java)
        val response = csApi.changeAvailability(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun changeConfiguration(
        meta: RequestMetadata,
        req: ChangeConfigurationReq
    ): OperationExecution<ChangeConfigurationReq, ChangeConfigurationResp> {
        val mapper: ChangeConfigurationMapper = Mappers.getMapper(ChangeConfigurationMapper::class.java)
        val response = csApi.setVariables(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun clearCache(
        meta: RequestMetadata,
        req: ClearCacheReq
    ): OperationExecution<ClearCacheReq, ClearCacheResp> {
        val mapper: ClearCacheMapper = Mappers.getMapper(ClearCacheMapper::class.java)
        val response = csApi.clearCache(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun remoteStartTransaction(
        meta: RequestMetadata,
        req: RemoteStartTransactionReq
    ): OperationExecution<RemoteStartTransactionReq, RemoteStartTransactionResp> {
        val mapper: RemoteStartTransactionMapper = Mappers.getMapper(RemoteStartTransactionMapper::class.java)
        val remoteStartId: Int = Random.nextInt()
        val response = csApi.requestStartTransaction(meta, mapper.coreToGenReq(req, remoteStartId))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun remoteStopTransaction(
        meta: RequestMetadata,
        req: RemoteStopTransactionReq
    ): OperationExecution<RemoteStopTransactionReq, RemoteStopTransactionResp> {
        val mapper: RemoteStopTransactionMapper = Mappers.getMapper(RemoteStopTransactionMapper::class.java)
        val response = csApi.requestStopTransaction(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun unlockConnector(
        meta: RequestMetadata,
        req: UnlockConnectorReq
    ): OperationExecution<UnlockConnectorReq, UnlockConnectorResp> {
        val mapper: UnlockConnectorMapper = Mappers.getMapper(UnlockConnectorMapper::class.java)
        val response = csApi.unlockConnector(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun getConfiguration(
        meta: RequestMetadata,
        req: GetConfigurationReq
    ): OperationExecution<GetConfigurationReq, GetConfigurationResp> {
        val mapper: GetConfigurationMapper = Mappers.getMapper(GetConfigurationMapper::class.java)
        return if (req.key != null && req.key!!.isNotEmpty()) {
            val response = csApi.getVariables(meta, mapper.coreToGenGetVariablesReq(req))
            OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreGetVariablesResp(response.response)
            )
        } else {
            val response = csApi.getAllVariables(meta, mapper.coreToGenGetAllVariablesReq())
            OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreGetAllVariablesResp(response.response)
            )

        }
    }

    override fun cancelReservation(
        meta: RequestMetadata,
        req: CancelReservationReq
    ): OperationExecution<CancelReservationReq, CancelReservationResp> {
        val mapper: CancelReservationMapper = Mappers.getMapper(CancelReservationMapper::class.java)
        val response = csApi.cancelReservation(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun clearChargingProfile(
        meta: RequestMetadata,
        req: ClearChargingProfileReq
    ): OperationExecution<ClearChargingProfileReq, ClearChargingProfileResp> {
        val mapper: ClearChargingProfileMapper = Mappers.getMapper(ClearChargingProfileMapper::class.java)
        val response = csApi.clearChargingProfile(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun getCompositeSchedule(
        meta: RequestMetadata,
        req: GetCompositeScheduleReq
    ): OperationExecution<GetCompositeScheduleReq, GetCompositeScheduleResp> {
        val mapper: GetCompositeScheduleMapper = Mappers.getMapper(GetCompositeScheduleMapper::class.java)
        val response = csApi.getCompositeSchedule(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun getLocalListVersion(
        meta: RequestMetadata,
        req: GetLocalListVersionReq
    ): OperationExecution<GetLocalListVersionReq, GetLocalListVersionResp> {
        val mapper: GetLocalListVersionMapper = Mappers.getMapper(GetLocalListVersionMapper::class.java)
        val response = csApi.getLocalListVersion(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun updateFirmware(
        meta: RequestMetadata,
        req: UpdateFirmwareReq
    ): OperationExecution<UpdateFirmwareReq, UpdateFirmwareResp> {
        val mapper: UpdateFirmwareMapper = Mappers.getMapper(UpdateFirmwareMapper::class.java)
        val response = csApi.updateFirmware(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun sendLocalList(
        meta: RequestMetadata,
        req: SendLocalListReq
    ): OperationExecution<SendLocalListReq, SendLocalListResp> {
        val mapper: SendLocalListMapper = Mappers.getMapper(SendLocalListMapper::class.java)
        val response = csApi.sendLocalList(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun triggerMessage(
        meta: RequestMetadata,
        req: TriggerMessageReq
    ): OperationExecution<TriggerMessageReq, TriggerMessageResp> {
        val mapper: TriggerMessageMapper = Mappers.getMapper(TriggerMessageMapper::class.java)
        val response = csApi.triggerMessage(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }


    override fun setChargingProfile(
        meta: RequestMetadata,
        req: SetChargingProfileReq
    ): OperationExecution<SetChargingProfileReq, SetChargingProfileResp> {
        val mapper: SetChargingProfileMapper = Mappers.getMapper(SetChargingProfileMapper::class.java)
        val response = csApi.setChargingProfile(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun reserveNow(
        meta: RequestMetadata,
        req: ReserveNowReq
    ): OperationExecution<ReserveNowReq, ReserveNowResp> {
        val mapper: ReserveNowMapper = Mappers.getMapper(ReserveNowMapper::class.java)
        val response = csApi.reserveNow(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun dataTransfer(
        meta: RequestMetadata,
        req: DataTransferReq
    ): OperationExecution<DataTransferReq, DataTransferResp> {
        val mapper: DataTransferMapper = Mappers.getMapper(DataTransferMapper::class.java)
        val response = csApi.dataTransfer(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }
}