package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.reportchargingprofiles.ReportChargingProfilesReq
import fr.simatix.cs.simulator.core20.model.reportchargingprofiles.ReportChargingProfilesResp
import org.mapstruct.Mapper
import fr.simatix.cs.simulator.api.model.reportchargingprofiles.ReportChargingProfilesReq as ReportChargingProfilesReqGen
import fr.simatix.cs.simulator.api.model.reportchargingprofiles.ReportChargingProfilesResp as ReportChargingProfilesRespGen
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface ReportChargingProfilesMapper {
    fun genToCoreReq(reportChargingProfileReq: ReportChargingProfilesReqGen?): ReportChargingProfilesReq

    fun coreToGenResp(reportChargingProfileResp: ReportChargingProfilesResp?): ReportChargingProfilesRespGen
}