package fr.simatix.cs.simulator.adapter20

import fr.simatix.cs.simulator.adapter20.mapper.*
import fr.simatix.cs.simulator.api.CSApi
import fr.simatix.cs.simulator.core20.CSMSOperations
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationReq
import fr.simatix.cs.simulator.core20.model.cancelreservation.CancelReservationResp
import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedReq
import fr.simatix.cs.simulator.core20.model.certificateSigned.CertificateSignedResp
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityReq
import fr.simatix.cs.simulator.core20.model.changeavailability.ChangeAvailabilityResp
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheReq
import fr.simatix.cs.simulator.core20.model.clearcache.ClearCacheResp
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileReq
import fr.simatix.cs.simulator.core20.model.clearchargingprofile.ClearChargingProfileResp
import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import fr.simatix.cs.simulator.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationReq
import fr.simatix.cs.simulator.core20.model.customerinformation.CustomerInformationResp
import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedReq
import fr.simatix.cs.simulator.core20.model.costupdated.CostUpdatedResp
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferReq
import fr.simatix.cs.simulator.core20.model.datatransfer.DataTransferResp
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateReq
import fr.simatix.cs.simulator.core20.model.deletecertificate.DeleteCertificateResp
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportReq
import fr.simatix.cs.simulator.core20.model.getbasereport.GetBaseReportResp
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesReq
import fr.simatix.cs.simulator.core20.model.getchargingprofiles.GetChargingProfilesResp
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleReq
import fr.simatix.cs.simulator.core20.model.getcompositeschedule.GetCompositeScheduleResp
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import fr.simatix.cs.simulator.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesReq
import fr.simatix.cs.simulator.core20.model.getdisplaymessages.GetDisplayMessagesResp
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionReq
import fr.simatix.cs.simulator.core20.model.getlocallistversion.GetLocalListVersionResp
import fr.simatix.cs.simulator.core20.model.getlog.GetLogReq
import fr.simatix.cs.simulator.core20.model.getlog.GetLogResp
import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportReq
import fr.simatix.cs.simulator.core20.model.getmonitoringreport.GetMonitoringReportResp
import fr.simatix.cs.simulator.core20.model.getreport.GetReportReq
import fr.simatix.cs.simulator.core20.model.getreport.GetReportResp
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusReq
import fr.simatix.cs.simulator.core20.model.gettransactionstatus.GetTransactionStatusResp
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesReq
import fr.simatix.cs.simulator.core20.model.getvariables.GetVariablesResp
import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareReq
import fr.simatix.cs.simulator.core20.model.publishfirmware.PublishFirmwareResp
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateReq
import fr.simatix.cs.simulator.core20.model.installcertificate.InstallCertificateResp
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestop.RequestStopTransactionResp
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowReq
import fr.simatix.cs.simulator.core20.model.reservenow.ReserveNowResp
import fr.simatix.cs.simulator.core20.model.reset.ResetReq
import fr.simatix.cs.simulator.core20.model.reset.ResetResp
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListReq
import fr.simatix.cs.simulator.core20.model.sendlocallist.SendLocalListResp
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileReq
import fr.simatix.cs.simulator.core20.model.setchargingprofile.SetChargingProfileResp
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import fr.simatix.cs.simulator.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import fr.simatix.cs.simulator.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileReq
import fr.simatix.cs.simulator.core20.model.setnetworkprofile.SetNetworkProfileResp
import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseReq
import fr.simatix.cs.simulator.core20.model.setmonitoringbase.SetMonitoringBaseResp
import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageReq
import fr.simatix.cs.simulator.core20.model.setdisplaymessage.SetDisplayMessageResp
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesReq
import fr.simatix.cs.simulator.core20.model.setvariables.SetVariablesResp
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageReq
import fr.simatix.cs.simulator.core20.model.triggermessage.TriggerMessageResp
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorReq
import fr.simatix.cs.simulator.core20.model.unlockconnector.UnlockConnectorResp
import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareReq
import fr.simatix.cs.simulator.core20.model.unpublishfirmware.UnpublishFirmwareResp
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareResp
import fr.simatix.cs.simulator.operation.information.ExecutionMetadata
import fr.simatix.cs.simulator.operation.information.OperationExecution
import fr.simatix.cs.simulator.operation.information.RequestMetadata
import fr.simatix.cs.simulator.operation.information.RequestStatus
import org.mapstruct.factory.Mappers
import java.net.ConnectException

class Ocpp20CSApiAdapter(private val csApi: CSApi) : CSMSOperations {

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

    override fun requestStartTransaction(
        meta: RequestMetadata,
        req: RequestStartTransactionReq
    ): OperationExecution<RequestStartTransactionReq, RequestStartTransactionResp> {
        val mapper: RequestStartTransactionMapper = Mappers.getMapper(RequestStartTransactionMapper::class.java)
        val response = csApi.requestStartTransaction(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun requestStopTransaction(
        meta: RequestMetadata,
        req: RequestStopTransactionReq
    ): OperationExecution<RequestStopTransactionReq, RequestStopTransactionResp> {
        val mapper: RequestStopTransactionMapper = Mappers.getMapper(RequestStopTransactionMapper::class.java)
        val response = csApi.requestStopTransaction(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun setVariables(
        meta: RequestMetadata,
        req: SetVariablesReq
    ): OperationExecution<SetVariablesReq, SetVariablesResp> {
        val mapper: SetVariablesMapper = Mappers.getMapper(SetVariablesMapper::class.java)
        val response = csApi.setVariables(meta, mapper.coreToGenReq(req))
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

    override fun getVariables(
        meta: RequestMetadata,
        req: GetVariablesReq
    ): OperationExecution<GetVariablesReq, GetVariablesResp> {
        val mapper: GetVariablesMapper = Mappers.getMapper(GetVariablesMapper::class.java)
        val response = csApi.getVariables(meta, mapper.coreToGenReq(req))
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

    override fun getBaseReport(
        meta: RequestMetadata,
        req: GetBaseReportReq
    ): OperationExecution<GetBaseReportReq, GetBaseReportResp> {
        val mapper: GetBaseReportMapper = Mappers.getMapper(GetBaseReportMapper::class.java)
        val response = csApi.getBaseReport(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun getReport(meta: RequestMetadata, req: GetReportReq): OperationExecution<GetReportReq, GetReportResp> {
        val mapper: GetReportMapper = Mappers.getMapper(GetReportMapper::class.java)
        val response = csApi.getReport(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
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

    override fun certificateSigned(
        meta: RequestMetadata,
        req: CertificateSignedReq
    ): OperationExecution<CertificateSignedReq, CertificateSignedResp> {
        val mapper: CertificateSignedMapper = Mappers.getMapper(CertificateSignedMapper::class.java)
        val response = csApi.certificateSigned(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS), req, mapper.genToCoreResp(response.response)
        )
    }

    override fun getLog(
        meta: RequestMetadata,
        req: GetLogReq
    ): OperationExecution<GetLogReq, GetLogResp> {
        val mapper: GetLogMapper = Mappers.getMapper(GetLogMapper::class.java)
        val response = csApi.getLog(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }
    override fun costUpdated(
            meta: RequestMetadata,
            req: CostUpdatedReq
    ): OperationExecution<CostUpdatedReq, CostUpdatedResp> {
        val mapper: CostUpdatedMapper = Mappers.getMapper(CostUpdatedMapper::class.java)
        val response = csApi.costUpdated(meta, mapper.coreToGenReq(req))

        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun clearDisplayMessage(
        meta: RequestMetadata,
        req: ClearDisplayMessageReq
    ): OperationExecution<ClearDisplayMessageReq, ClearDisplayMessageResp> {
        val mapper: ClearDisplayMessageMapper = Mappers.getMapper(ClearDisplayMessageMapper::class.java)
        val response = csApi.clearDisplayMessage(meta, mapper.coreToGenReq(req))
        return OperationExecution(
            ExecutionMetadata(meta, RequestStatus.SUCCESS),
            req,
            mapper.genToCoreResp(response.response)
        )
    }

    override fun publishFirmware(
            meta: RequestMetadata,
            req: PublishFirmwareReq
    ): OperationExecution<PublishFirmwareReq, PublishFirmwareResp> {
        val mapper: PublishFirmwareMapper = Mappers.getMapper(PublishFirmwareMapper::class.java)
        val response = csApi.publishFirmware(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun getChargingProfiles(
            meta: RequestMetadata,
            req: GetChargingProfilesReq
    ): OperationExecution<GetChargingProfilesReq, GetChargingProfilesResp> {
        val mapper: GetChargingProfilesMapper = Mappers.getMapper(GetChargingProfilesMapper::class.java)
        val response = csApi.getChargingProfiles(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun getInstalledCertificateIds(
            meta: RequestMetadata,
            req: GetInstalledCertificateIdsReq
    ): OperationExecution<GetInstalledCertificateIdsReq, GetInstalledCertificateIdsResp> {
        val mapper: GetInstalledCertificateIdsMapper = Mappers.getMapper(GetInstalledCertificateIdsMapper::class.java)
        val response = csApi.getInstalledCertificateIds(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun installCertificate(
            meta: RequestMetadata,
            req: InstallCertificateReq
    ): OperationExecution<InstallCertificateReq, InstallCertificateResp> {
        val mapper: InstallCertificateMapper = Mappers.getMapper(InstallCertificateMapper::class.java)
        val response = csApi.installCertificate(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun customerInformation(
            meta: RequestMetadata,
            req: CustomerInformationReq
    ): OperationExecution<CustomerInformationReq, CustomerInformationResp> {
        val mapper: CustomerInformationMapper = Mappers.getMapper(CustomerInformationMapper::class.java)
        val response = csApi.customerInformation(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun unpublishFirmware(
            meta: RequestMetadata,
            req: UnpublishFirmwareReq
    ): OperationExecution<UnpublishFirmwareReq, UnpublishFirmwareResp> {
        val mapper: UnpublishFirmwareMapper = Mappers.getMapper(UnpublishFirmwareMapper::class.java)
        val response = csApi.unpublishFirmware(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun setVariableMonitoring(
            meta: RequestMetadata,
            req: SetVariableMonitoringReq
    ): OperationExecution<SetVariableMonitoringReq, SetVariableMonitoringResp> {
        val mapper: SetVariableMonitoringMapper = Mappers.getMapper(SetVariableMonitoringMapper::class.java)
        val response = csApi.setVariableMonitoring(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    @Throws(IllegalStateException::class, ConnectException::class)
    override fun setMonitoringLevel(
            meta: RequestMetadata,
            req: SetMonitoringLevelReq
    ): OperationExecution<SetMonitoringLevelReq, SetMonitoringLevelResp> {
        if(req.severity  < 0 || req.severity > 9) {
            throw IllegalStateException("Severity should be an integer between 0 and 9")
        } else {
            val mapper: SetMonitoringLevelMapper = Mappers.getMapper(SetMonitoringLevelMapper::class.java)
            val response = csApi.setMonitoringLevel(meta, mapper.coreToGenReq(req))
            return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
            )
        }
    }

    override fun setNetworkProfile(
            meta: RequestMetadata,
            req: SetNetworkProfileReq
    ): OperationExecution<SetNetworkProfileReq, SetNetworkProfileResp> {
        val mapper: SetNetworkProfileMapper = Mappers.getMapper(SetNetworkProfileMapper::class.java)
        val response = csApi.setNetworkProfile(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun getTransactionStatus(
            meta: RequestMetadata,
            req: GetTransactionStatusReq
    ): OperationExecution<GetTransactionStatusReq, GetTransactionStatusResp> {
        val mapper: GetTransactionStatusMapper = Mappers.getMapper(GetTransactionStatusMapper::class.java)
        val response = csApi.getTransactionStatus(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun setMonitoringBase(
            meta: RequestMetadata,
            req: SetMonitoringBaseReq
    ): OperationExecution<SetMonitoringBaseReq, SetMonitoringBaseResp> {
        val mapper: SetMonitoringBaseMapper = Mappers.getMapper(SetMonitoringBaseMapper::class.java)
        val response = csApi.setMonitoringBase(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun getDisplayMessages(
            meta: RequestMetadata,
            req: GetDisplayMessagesReq
    ): OperationExecution<GetDisplayMessagesReq, GetDisplayMessagesResp> {
        val mapper: GetDisplayMessagesMapper = Mappers.getMapper(GetDisplayMessagesMapper::class.java)
        val response = csApi.getDisplayMessages(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun setDisplayMessage(
            meta: RequestMetadata,
            req: SetDisplayMessageReq
    ): OperationExecution<SetDisplayMessageReq, SetDisplayMessageResp> {
        val mapper: SetDisplayMessageMapper = Mappers.getMapper(SetDisplayMessageMapper::class.java)
        val response = csApi.setDisplayMessage(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun deleteCertificate(
            meta: RequestMetadata,
            req: DeleteCertificateReq
    ): OperationExecution<DeleteCertificateReq, DeleteCertificateResp> {
        val mapper: DeleteCertificateMapper = Mappers.getMapper(DeleteCertificateMapper::class.java)
        val response = csApi.deleteCertificate(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }

    override fun getMonitoringReport(
            meta: RequestMetadata,
            req: GetMonitoringReportReq
    ): OperationExecution<GetMonitoringReportReq, GetMonitoringReportResp> {
        val mapper: GetMonitoringReportMapper = Mappers.getMapper(GetMonitoringReportMapper::class.java)
        val response = csApi.getMonitoringReport(meta, mapper.coreToGenReq(req))
        return OperationExecution(
                ExecutionMetadata(meta, RequestStatus.SUCCESS),
                req,
                mapper.genToCoreResp(response.response)
        )
    }
}
