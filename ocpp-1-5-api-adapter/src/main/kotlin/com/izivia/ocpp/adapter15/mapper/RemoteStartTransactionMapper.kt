package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionReq
import com.izivia.ocpp.api.model.remotestart.RequestStartTransactionResp
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionReq
import com.izivia.ocpp.core15.model.remotestart.RemoteStartTransactionResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class RemoteStartTransactionMapper {

    abstract fun genToCoreResp(remoteStartResp: RequestStartTransactionResp?): RemoteStartTransactionResp

    @Mapping(target = "remoteStartId", source = "remoteStartId")
    @Mapping(target = "evseId", source = "remoteStartReq.connectorId")
    @Mapping(target = "idToken", source = "remoteStartReq.idTag", qualifiedByName = ["convertIdTag"])
    @Mapping(
        target = "chargingProfile",
        source = "remoteStartReq.chargingProfile",
        qualifiedByName = ["convertChargingProfile"]
    )
    abstract fun coreToGenReq(remoteStartReq: RemoteStartTransactionReq, remoteStartId: Int): RequestStartTransactionReq

}
