package com.izivia.ocpp.adapter15.test

import com.izivia.ocpp.adapter15.mapper.CommonMapper
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.UnitOfMeasure
import com.izivia.ocpp.api.model.common.enumeration.LocationEnumType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.core15.model.common.enumeration.Location
import com.izivia.ocpp.core15.model.common.enumeration.Measurand
import com.izivia.ocpp.core15.model.common.enumeration.ReadingContext
import com.izivia.ocpp.core15.model.common.enumeration.UnitOfMeasure as UnitOfMeasureCore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import strikt.api.expectThat
import strikt.assertions.isEqualTo

/**
 * Guards the generic -> OCPP 1.5 narrowing of every sampled-value enum: no conversion may fail with an
 * opaque "No enum constant" error, and every value must either map to a 1.5 target or be rejected
 * explicitly.
 */
class CommonMapperConversionTest {

    private val readingContextsAbsentFrom15 = setOf(
        ReadingContextEnumType.Trigger,
        ReadingContextEnumType.Other
    )

    private val locationsAbsentFrom15 = setOf(
        LocationEnumType.Cable,
        LocationEnumType.EV
    )

    private val measurandsAbsentFrom15 = setOf(
        MeasurandEnumType.EnergyActiveNet,
        MeasurandEnumType.EnergyReactiveNet,
        MeasurandEnumType.EnergyApparentNet,
        MeasurandEnumType.EnergyApparentImport,
        MeasurandEnumType.EnergyApparentExport,
        MeasurandEnumType.PowerOffered,
        MeasurandEnumType.PowerFactor,
        MeasurandEnumType.CurrentOffered,
        MeasurandEnumType.Frequency,
        MeasurandEnumType.SoC,
        MeasurandEnumType.RPM
    )

    private fun convert(sampledValue: SampledValueType) = CommonMapper.convertSampledValue(sampledValue)

    @Test
    fun `every generic reading context either maps to OCPP 1_5 or is rejected explicitly`() {
        ReadingContextEnumType.entries.forEach { context ->
            val sampledValue = SampledValueType(10.0, context = context)
            if (context in readingContextsAbsentFrom15) {
                val error = assertThrows<IllegalArgumentException> { convert(sampledValue) }
                expectThat(error.message.orEmpty().contains("doesn't exists in OCPP 1.5")).isEqualTo(true)
            } else {
                expectThat(convert(sampledValue).context).isEqualTo(ReadingContext.valueOf(context.name))
            }
        }
    }

    @Test
    fun `every generic location either maps to OCPP 1_5 or is rejected explicitly`() {
        LocationEnumType.entries.forEach { location ->
            val sampledValue = SampledValueType(10.0, location = location)
            if (location in locationsAbsentFrom15) {
                val error = assertThrows<IllegalArgumentException> { convert(sampledValue) }
                expectThat(error.message.orEmpty().contains("doesn't exists in OCPP 1.5")).isEqualTo(true)
            } else {
                expectThat(convert(sampledValue).location).isEqualTo(Location.valueOf(location.name))
            }
        }
    }

    @Test
    fun `every generic measurand either maps to OCPP 1_5 or is rejected explicitly`() {
        MeasurandEnumType.entries.forEach { measurand ->
            val sampledValue = SampledValueType(10.0, measurand = measurand)
            if (measurand in measurandsAbsentFrom15) {
                val error = assertThrows<IllegalArgumentException> { convert(sampledValue) }
                expectThat(error.message.orEmpty().contains("doesn't exists in OCPP 1.5")).isEqualTo(true)
            } else {
                expectThat(convert(sampledValue).measurand).isEqualTo(Measurand.valueOf(measurand.name))
            }
        }
    }

    @Test
    fun `unit is resolved on the OCPP 1_5 wire value, not on the enum name`() {
        // core15 spells this one Var("var"): resolving by name used to throw on the canonical "var".
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = UnitOfMeasure(unit = "var"))).unit)
            .isEqualTo(UnitOfMeasureCore.Var)
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = UnitOfMeasure(unit = "Wh"))).unit)
            .isEqualTo(UnitOfMeasureCore.Wh)
    }

    @Test
    fun `SI unit symbols are aliased to their OCPP 1_5 spelling`() {
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = UnitOfMeasure(unit = "A"))).unit)
            .isEqualTo(UnitOfMeasureCore.Amp)
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = UnitOfMeasure(unit = "V"))).unit)
            .isEqualTo(UnitOfMeasureCore.Volt)
    }

    @Test
    fun `unknown and missing units fall back to Wh`() {
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = UnitOfMeasure(unit = "Var"))).unit)
            .isEqualTo(UnitOfMeasureCore.Wh)
        expectThat(convert(SampledValueType(10.0, unitOfMeasure = null)).unit)
            .isEqualTo(UnitOfMeasureCore.Wh)
    }
}
