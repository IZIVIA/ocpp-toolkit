package com.izivia.ocpp.adapter20

import com.izivia.ocpp.adapter20.mapper.*
import com.izivia.ocpp.api.CSApi
import com.izivia.ocpp.core20.CSMSOperations
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationReq
import com.izivia.ocpp.core20.model.cancelreservation.CancelReservationResp
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedReq
import com.izivia.ocpp.core20.model.certificateSigned.CertificateSignedResp
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityReq
import com.izivia.ocpp.core20.model.changeavailability.ChangeAvailabilityResp
import com.izivia.ocpp.core20.model.clearcache.ClearCacheReq
import com.izivia.ocpp.core20.model.clearcache.ClearCacheResp
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileReq
import com.izivia.ocpp.core20.model.clearchargingprofile.ClearChargingProfileResp
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageReq
import com.izivia.ocpp.core20.model.cleardisplaymessage.ClearDisplayMessageResp
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringReq
import com.izivia.ocpp.core20.model.clearvariablemonitoring.ClearVariableMonitoringResp
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationReq
import com.izivia.ocpp.core20.model.customerinformation.CustomerInformationResp
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedReq
import com.izivia.ocpp.core20.model.costupdated.CostUpdatedResp
import com.izivia.ocpp.core20.model.datatransfer.DataTransferReq
import com.izivia.ocpp.core20.model.datatransfer.DataTransferResp
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateReq
import com.izivia.ocpp.core20.model.deletecertificate.DeleteCertificateResp
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportReq
import com.izivia.ocpp.core20.model.getbasereport.GetBaseReportResp
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesReq
import com.izivia.ocpp.core20.model.getchargingprofiles.GetChargingProfilesResp
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleReq
import com.izivia.ocpp.core20.model.getcompositeschedule.GetCompositeScheduleResp
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsReq
import com.izivia.ocpp.core20.model.getinstalledcertificateids.GetInstalledCertificateIdsResp
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesReq
import com.izivia.ocpp.core20.model.getdisplaymessages.GetDisplayMessagesResp
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionReq
import com.izivia.ocpp.core20.model.getlocallistversion.GetLocalListVersionResp
import com.izivia.ocpp.core20.model.getlog.GetLogReq
import com.izivia.ocpp.core20.model.getlog.GetLogResp
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportReq
import com.izivia.ocpp.core20.model.getmonitoringreport.GetMonitoringReportResp
import com.izivia.ocpp.core20.model.getreport.GetReportReq
import com.izivia.ocpp.core20.model.getreport.GetReportResp
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusReq
import com.izivia.ocpp.core20.model.gettransactionstatus.GetTransactionStatusResp
import com.izivia.ocpp.core20.model.getvariables.GetVariablesReq
import com.izivia.ocpp.core20.model.getvariables.GetVariablesResp
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareReq
import com.izivia.ocpp.core20.model.publishfirmware.PublishFirmwareResp
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateReq
import com.izivia.ocpp.core20.model.installcertificate.InstallCertificateResp
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.core20.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionReq
import com.izivia.ocpp.core20.model.remotestop.RequestStopTransactionResp
import com.izivia.ocpp.core20.model.reservenow.ReserveNowReq
import com.izivia.ocpp.core20.model.reservenow.ReserveNowResp
import com.izivia.ocpp.core20.model.reset.ResetReq
import com.izivia.ocpp.core20.model.reset.ResetResp
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListReq
import com.izivia.ocpp.core20.model.sendlocallist.SendLocalListResp
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileReq
import com.izivia.ocpp.core20.model.setchargingprofile.SetChargingProfileResp
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringReq
import com.izivia.ocpp.core20.model.setvariablemonitoring.SetVariableMonitoringResp
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelReq
import com.izivia.ocpp.core20.model.setmonitoringlevel.SetMonitoringLevelResp
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileReq
import com.izivia.ocpp.core20.model.setnetworkprofile.SetNetworkProfileResp
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseReq
import com.izivia.ocpp.core20.model.setmonitoringbase.SetMonitoringBaseResp
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageReq
import com.izivia.ocpp.core20.model.setdisplaymessage.SetDisplayMessageResp
import com.izivia.ocpp.core20.model.setvariables.SetVariablesReq
import com.izivia.ocpp.core20.model.setvariables.SetVariablesResp
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageReq
import com.izivia.ocpp.core20.model.triggermessage.TriggerMessageResp
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorReq
import com.izivia.ocpp.core20.model.unlockconnector.UnlockConnectorResp
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareReq
import com.izivia.ocpp.core20.model.unpublishfirmware.UnpublishFirmwareResp
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareReq
import com.izivia.ocpp.core20.model.updatefirmware.UpdateFirmwareResp
import com.izivia.ocpp.operation.information.ExecutionMetadata
import com.izivia.ocpp.operation.information.OperationExecution
import com.izivia.ocpp.operation.information.RequestMetadata
import com.izivia.ocpp.operation.information.RequestStatus
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

    override fun clearVariableMonitoring(
        meta: RequestMetadata,
        req: ClearVariableMonitoringReq
    ): OperationExecution<ClearVariableMonitoringReq, ClearVariableMonitoringResp> {
        val mapper: ClearVariableMonitoringMapper = Mappers.getMapper(ClearVariableMonitoringMapper::class.java)
        val response = csApi.clearVariableMonitoring(meta, mapper.coreToGenReq(req))
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
