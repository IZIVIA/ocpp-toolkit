package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.core16.model.getdiagnostics.GetDiagnosticsReq
import fr.simatix.cs.simulator.core16.model.getdiagnostics.GetDiagnosticsResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getlog.GetLogReq
import fr.simatix.cs.simulator.api.model.getlog.GetLogResp
import fr.simatix.cs.simulator.api.model.getlog.LogParametersType
import fr.simatix.cs.simulator.api.model.getlog.enumeration.LogEnumType

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