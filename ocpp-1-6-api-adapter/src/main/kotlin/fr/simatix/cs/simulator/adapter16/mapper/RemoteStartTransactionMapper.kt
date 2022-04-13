package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionReq
import fr.simatix.cs.simulator.api.model.remotestart.RequestStartTransactionResp
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionReq
import fr.simatix.cs.simulator.core16.model.remotestart.RemoteStartTransactionResp
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