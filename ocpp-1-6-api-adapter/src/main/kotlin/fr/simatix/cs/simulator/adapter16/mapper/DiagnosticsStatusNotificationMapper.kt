package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationReq
import fr.simatix.cs.simulator.api.model.logstatusnotification.LogStatusNotificationResp
import fr.simatix.cs.simulator.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import fr.simatix.cs.simulator.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class DiagnosticsStatusNotificationMapper {

    @Named("convertDiagnosticsStatus")
    fun convertFirmwareStatus(status: UploadLogStatusEnumType): DiagnosticsStatus =
        when(status){

            UploadLogStatusEnumType.BadMessage,
            UploadLogStatusEnumType.NotSupportedOperation,
            UploadLogStatusEnumType.PermissionDenied,
            UploadLogStatusEnumType.UploadFailure,
            UploadLogStatusEnumType.AcceptedCanceled -> DiagnosticsStatus.UploadFailed

            else -> DiagnosticsStatus.valueOf(status.name)

        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertDiagnosticsStatus"])
    abstract fun genToCoreReq(statusReq: LogStatusNotificationReq?): DiagnosticsStatusNotificationReq

    abstract fun coreToGenResp(statusResp: DiagnosticsStatusNotificationResp?): LogStatusNotificationResp
}