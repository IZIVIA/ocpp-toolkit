package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.updatefirmware.FirmwareType
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareReq
import fr.simatix.cs.simulator.core16.model.updatefirmware.UpdateFirmwareResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareReq as UpdateFirmwareReqGen
import fr.simatix.cs.simulator.api.model.updatefirmware.UpdateFirmwareResp as UpdateFirmwareRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UpdateFirmwareMapper {

    fun coreToGenReq(updateFirmwareReq: UpdateFirmwareReq): UpdateFirmwareReqGen{
        val firmware: FirmwareType = FirmwareType(
            location = updateFirmwareReq.location,
            retrieveDateTime = updateFirmwareReq.retrieveDate
        )

        return UpdateFirmwareReqGen(retries = updateFirmwareReq.retries, retryInterval = updateFirmwareReq.retryInterval,
            requestId = 0, firmware = firmware)
    }

    abstract fun genToCoreResp(updateFirmwareResp: UpdateFirmwareRespGen?): UpdateFirmwareResp
}
