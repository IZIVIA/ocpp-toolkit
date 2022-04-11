package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core20.model.updatefirmware.UpdateFirmwareResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareReq as UpdateFirmwareReqGen
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp as UpdateFirmwareRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface UpdateFirmwareMapper {

        fun genToCoreResp(updateFirmwareResp: UpdateFirmwareRespGen): UpdateFirmwareResp

        fun coreToGenReq(updateFirmwareReq: UpdateFirmwareReq): UpdateFirmwareReqGen
}
