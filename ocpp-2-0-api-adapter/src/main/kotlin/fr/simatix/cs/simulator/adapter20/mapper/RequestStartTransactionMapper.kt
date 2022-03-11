package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.core20.model.remotestart.RequestStartTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq as RequestStartTransactionReqGen
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp as RequestStartTransactionRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class RequestStartTransactionMapper {

    abstract fun genToCoreResp(reqStartResp: RequestStartTransactionRespGen?): RequestStartTransactionResp

    abstract fun coreToGenReq(reqStartReq: RequestStartTransactionReq): RequestStartTransactionReqGen

}