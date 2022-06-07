package com.izivia.ocpp.adapter20.mapper

import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.UnitOfMeasure
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CommonMapper {

    @Named("meterValue")
    @Mapping(target = "sampledValue", source = "sampledValue", qualifiedByName = ["sampledValue"])
    abstract fun meterValue(meterValue: MeterValueType): com.izivia.ocpp.core20.model.common.MeterValueType

    @Named("sampledValue")
    @Mapping(target = "measurand", source = "measurand", qualifiedByName = ["measurandEnum"])
    @Mapping(target = "unitOfMeasure", source = "unitOfMeasure", qualifiedByName = ["unitOfMeasure"])
    abstract fun sampledValue(sampleValue: SampledValueType): com.izivia.ocpp.core20.model.common.SampledValueType

    @Named("unitOfMeasure")
    abstract fun unitOfMeasure(unitOfMeasure: UnitOfMeasure): com.izivia.ocpp.core20.model.common.UnitOfMeasure

    @Named("measurandEnum")
    fun measurandEnum(measurand: MeasurandEnumType): com.izivia.ocpp.core20.model.common.enumeration.MeasurandEnumType =
        when (measurand) {
            MeasurandEnumType.Temperature, MeasurandEnumType.RPM -> throw IllegalStateException("INVALID REQUEST : MeasurandEnumType.${measurand.name} doesn't exists in OCPP 2.0.1")
            else -> com.izivia.ocpp.core20.model.common.enumeration.MeasurandEnumType.valueOf(measurand.name)
        }
}