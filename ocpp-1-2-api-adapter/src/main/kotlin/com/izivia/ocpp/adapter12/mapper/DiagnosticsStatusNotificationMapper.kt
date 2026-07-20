package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationReq
import com.izivia.ocpp.api.model.logstatusnotification.LogStatusNotificationResp
import com.izivia.ocpp.api.model.logstatusnotification.enumeration.UploadLogStatusEnumType
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
    private val unsupportedStatuses = setOf(UploadLogStatusEnumType.Idle, UploadLogStatusEnumType.Uploading)

    /**
     * OCPP 1.2 only models terminal diagnostics states; transient states are filtered out by the adapter
     * (logged and not forwarded).
     */
    fun isSupported(status: UploadLogStatusEnumType): Boolean =
        status !in unsupportedStatuses

    @Named("convertDiagnosticsStatus")
    fun convertDiagnosticsStatus(status: UploadLogStatusEnumType): DiagnosticsStatus =
        when (status) {
            UploadLogStatusEnumType.Uploaded -> DiagnosticsStatus.Uploaded

            UploadLogStatusEnumType.BadMessage,
            UploadLogStatusEnumType.NotSupportedOperation,
            UploadLogStatusEnumType.PermissionDenied,
            UploadLogStatusEnumType.UploadFailure,
            UploadLogStatusEnumType.AcceptedCanceled -> DiagnosticsStatus.UploadFailed

            // Transient states have no OCPP 1.2 equivalent; the adapter filters them out (isSupported) before mapping.
            UploadLogStatusEnumType.Idle,
            UploadLogStatusEnumType.Uploading ->
                throw IllegalArgumentException("UploadLogStatus $status has no OCPP 1.2 equivalent and must be filtered before mapping")
        }

    @Mapping(target = "status", source = "status", qualifiedByName = ["convertDiagnosticsStatus"])
    abstract fun genToCoreReq(statusReq: LogStatusNotificationReq?): DiagnosticsStatusNotificationReq

    abstract fun coreToGenResp(statusResp: DiagnosticsStatusNotificationResp?): LogStatusNotificationResp
}