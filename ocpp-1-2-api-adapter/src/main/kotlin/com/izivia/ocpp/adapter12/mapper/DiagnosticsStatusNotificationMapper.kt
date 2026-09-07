package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp as DiagnosticsStatusNotificationRespGen
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq as DiagnosticsStatusNotificationReqGen
import com.izivia.ocpp.api.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatusEnumType
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationReq
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.DiagnosticsStatusNotificationResp
import com.izivia.ocpp.core12.model.diagnosticsstatusnotification.enumeration.DiagnosticsStatus
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class DiagnosticsStatusNotificationMapper {

    /**
     * Transient states with no OCPP 1.2 equivalent; kept in sync with the throw branch of convertDiagnosticsStatus.
     */
    private val unsupportedStatuses = setOf(DiagnosticsStatusEnumType.Idle, DiagnosticsStatusEnumType.Uploading)

    /**
     * OCPP 1.2 only models terminal diagnostics states; transient states are filtered out by the adapter
     * (logged and not forwarded).
     */
    fun isSupported(status: DiagnosticsStatusEnumType): Boolean =
        status !in unsupportedStatuses

    @Named("convertDiagnosticsStatus")
    fun convertDiagnosticsStatus(status: DiagnosticsStatusEnumType): DiagnosticsStatus =
        when (status) {
            DiagnosticsStatusEnumType.Uploaded -> DiagnosticsStatus.Uploaded
            DiagnosticsStatusEnumType.UploadFailed -> DiagnosticsStatus.UploadFailed

            // Transient states have no OCPP 1.2 equivalent; the adapter filters them out (isSupported) before mapping.
            DiagnosticsStatusEnumType.Idle,
            DiagnosticsStatusEnumType.Uploading ->
                throw IllegalArgumentException("DiagnosticsStatus $status has no OCPP 1.2 equivalent and must be filtered before mapping")
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertDiagnosticsStatus"])
    abstract fun genToCoreReq(statusReq: DiagnosticsStatusNotificationReqGen?): DiagnosticsStatusNotificationReq

    abstract fun coreToGenResp(statusResp: DiagnosticsStatusNotificationResp?): DiagnosticsStatusNotificationRespGen
}
