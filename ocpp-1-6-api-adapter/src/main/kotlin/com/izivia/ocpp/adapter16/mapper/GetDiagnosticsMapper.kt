package com.izivia.ocpp.adapter16.mapper

import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsReq
import com.izivia.ocpp.core16.model.getdiagnostics.GetDiagnosticsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getlog.GetLogReq
import com.izivia.ocpp.api.model.getlog.GetLogResp
import com.izivia.ocpp.api.model.getlog.LogParametersType
import com.izivia.ocpp.api.model.getlog.enumeration.LogEnumType

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetDiagnosticsMapper {

    fun genToCoreResp(getLogResp: GetLogResp?): GetDiagnosticsResp = GetDiagnosticsResp(getLogResp?.filename)

    fun coreToGenReq(getDiagnosticsReq: GetDiagnosticsReq): GetLogReq =
        GetLogReq(
            requestId = 1,
            logType = LogEnumType.DiagnosticsLog,
            log = LogParametersType(
                remoteLocation = getDiagnosticsReq.location,
                oldestTimestamp = getDiagnosticsReq.startTime,
                latestTimestamp = getDiagnosticsReq.stopTime
            ),
            retries = getDiagnosticsReq.retries,
            retryInterval = getDiagnosticsReq.retryInterval
        )

}