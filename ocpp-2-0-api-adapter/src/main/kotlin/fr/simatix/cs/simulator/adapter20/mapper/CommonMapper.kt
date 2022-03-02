package fr.simatix.cs.simulator.adapter20.mapper

import fr.simatix.cs.simulator.api.model.common.MeterValueType
import fr.simatix.cs.simulator.api.model.common.SampledValueType
import fr.simatix.cs.simulator.api.model.common.UnitOfMeasure
import fr.simatix.cs.simulator.api.model.common.enumeration.MeasurandEnumType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CommonMapper {

    @Named("meterValue")
    @Mapping(target = "sampledValue", source = "sampledValue", qualifiedByName = ["sampledValue"])
    abstract fun meterValue(meterValue: MeterValueType): fr.simatix.cs.simulator.core20.model.common.MeterValueType

    @Named("sampledValue")
    @Mapping(target = "measurand", source = "measurand", qualifiedByName = ["measurandEnum"])
    @Mapping(target = "unitOfMeasure", source = "unitOfMeasure", qualifiedByName = ["unitOfMeasure"])
    abstract fun sampledValue(sampleValue: SampledValueType): fr.simatix.cs.simulator.core20.model.common.SampledValueType

    @Named("unitOfMeasure")
    abstract fun unitOfMeasure(unitOfMeasure: UnitOfMeasure): fr.simatix.cs.simulator.core20.model.common.UnitOfMeasure

    @Named("measurandEnum")
    fun measurandEnum(measurand: MeasurandEnumType): fr.simatix.cs.simulator.core20.model.common.enumeration.MeasurandEnumType =
        when (measurand) {
            MeasurandEnumType.Temperature, MeasurandEnumType.RPM -> throw IllegalStateException("INVALID REQUEST : MeasurandEnumType.${measurand.name} doesn't exists in OCPP 2.0.1")
            else -> fr.simatix.cs.simulator.core20.model.common.enumeration.MeasurandEnumType.valueOf(measurand.name)
        }
}