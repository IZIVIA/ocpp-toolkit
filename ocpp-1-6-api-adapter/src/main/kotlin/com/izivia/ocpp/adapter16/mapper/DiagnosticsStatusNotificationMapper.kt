package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp as DiagnosticsStatusNotificationRespGen
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq as DiagnosticsStatusNotificationReqGen
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatusEnumType
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core16.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class DiagnosticsStatusNotificationMapper {

    /** OCPP 1.6 models every diagnostics status of the generic model. */
    @Named("convertDiagnosticsStatus")
    fun convertDiagnosticsStatus(status: DiagnosticsStatusEnumType): DiagnosticsStatus =
        when (status) {
            DiagnosticsStatusEnumType.Idle -> DiagnosticsStatus.Idle
            DiagnosticsStatusEnumType.Uploaded -> DiagnosticsStatus.Uploaded
            DiagnosticsStatusEnumType.UploadFailed -> DiagnosticsStatus.UploadFailed
            DiagnosticsStatusEnumType.Uploading -> DiagnosticsStatus.Uploading
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertDiagnosticsStatus"])
    abstract fun genToCoreReq(statusReq: DiagnosticsStatusNotificationReqGen?): DiagnosticsStatusNotificationReq

    abstract fun coreToGenResp(statusResp: DiagnosticsStatusNotificationResp?): DiagnosticsStatusNotificationRespGen
}
