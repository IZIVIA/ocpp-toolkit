package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.core20.model.common.MeterValueType
import fr.simatix.cs.simulator.core20.model.common.SampledValueType
import fr.simatix.cs.simulator.core20.model.common.UnitOfMeasure
import fr.simatix.cs.simulator.core20.model.common.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core20.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.common.MeterValueType as MeterValueTypeGen
import fr.simatix.cs.simulator.api.model.common.SampledValueType as SampledValueTypeGen
import fr.simatix.cs.simulator.api.model.common.UnitOfMeasure as UnitOfMeasureGen
import fr.simatix.cs.simulator.api.model.common.enumeration.MeasurandEnumType as MeasurandEnumTypeGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp as MeterValuesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class MeterValuesMapper {

    @Mapping(target = "meterValue", source = "meterValue", qualifiedByName = ["meterValue"])
    abstract fun genToCoreReq(meterValuesReq: MeterValuesReqGen?): MeterValuesReq

    @Named("meterValue")
    @Mapping(target = "sampledValue", source = "sampledValue", qualifiedByName = ["sampledValue"])
    abstract fun meterValue(meterValue: MeterValueTypeGen): MeterValueType

    @Named("sampledValue")
    @Mapping(target = "measurand", source = "measurand", qualifiedByName = ["measurandEnum"])
    @Mapping(target = "unitOfMeasure", source = "unitOfMeasure", qualifiedByName = ["unitOfMeasure"])
    abstract fun sampledValue(sampleValue: SampledValueTypeGen): SampledValueType

    @Named("unitOfMeasure")
    abstract fun unitOfMeasure(unitOfMeasure: UnitOfMeasureGen): UnitOfMeasure

    @Named("measurandEnum")
    fun measurandEnum(measurand: MeasurandEnumTypeGen): MeasurandEnumType =
        when (measurand) {
            MeasurandEnumTypeGen.Temperature, MeasurandEnumTypeGen.RPM -> throw IllegalStateException("INVALID REQUEST : MeasurandEnumType.${measurand.name} doesn't exists in OCPP 2.0.1")
            else -> MeasurandEnumType.valueOf(measurand.name)
        }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}