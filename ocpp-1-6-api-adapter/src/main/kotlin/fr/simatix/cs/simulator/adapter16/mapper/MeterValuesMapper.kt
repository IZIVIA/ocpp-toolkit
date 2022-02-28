package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.SampledValueType
import fr.simatix.cs.simulator.api.model.common.SignedMeterValueType
import fr.simatix.cs.simulator.api.model.common.UnitOfMeasure
import fr.simatix.cs.simulator.api.model.common.enumeration.LocationEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.api.model.common.enumeration.ReadingContextEnumType
import fr.simatix.cs.simulator.core16.model.common.MeterValue
import fr.simatix.cs.simulator.core16.model.common.SampledValue
import fr.simatix.cs.simulator.core16.model.common.enumeration.*
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.metervalues.MeterValuesResp
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.metervalues.MeterValuesResp as MeterValuesRespGen
import fr.simatix.cs.simulator.core16.model.common.enumeration.UnitOfMeasure as UnitOfMeasure16

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class MeterValuesMapper {

    private fun convertReadingContext(value: ReadingContextEnumType?): ReadingContext =
        if (value != null) {
            ReadingContext.valueOf(value.name)
        } else ReadingContext.SamplePeriodic


    private fun convertPhase(value: PhaseEnumType?): Phase? =
        if (value != null) {
            Phase.valueOf(value.name)
        } else null

    private fun convertLocation(value: LocationEnumType?): Location =
        if (value != null) {
            Location.valueOf(value.name)
        } else Location.Outlet

    @Throws(IllegalStateException::class)
    private fun convertMeasurand(value: MeasurandEnumType?): Measurand =
        when (value) {
            null -> Measurand.EnergyActiveImportRegister
            MeasurandEnumType.EnergyActiveNet,
            MeasurandEnumType.EnergyReactiveNet,
            MeasurandEnumType.EnergyApparentExport,
            MeasurandEnumType.EnergyApparentImport,
            MeasurandEnumType.EnergyApparentNet -> throw IllegalStateException("INVALID REQUEST : Measurand.${value.name} doesn't exists in OCPP 1.6")
            else -> Measurand.valueOf(value.name)
        }

    private fun convertUnit(value: UnitOfMeasure?): UnitOfMeasure16 =
        if (value != null && enumValues<UnitOfMeasure16>().any { it.value == value.unit }) {
            UnitOfMeasure16.valueOf(value.unit!!)
        } else {
            UnitOfMeasure16.Wh
        }

    private fun convertFormat(value: SignedMeterValueType?): ValueFormat =
        if (value != null) {
            ValueFormat.SignedData
        } else {
            ValueFormat.Raw
        }

    private fun convertSampledValue(sampleValue: SampledValueType): SampledValue =
        SampledValue(
            value = sampleValue.value.toString(),
            context = convertReadingContext(sampleValue.context),
            format = convertFormat(sampleValue.signedMeterValue),
            location = convertLocation(sampleValue.location),
            measurand = convertMeasurand(sampleValue.measurand),
            phase = convertPhase(sampleValue.phase),
            unit = convertUnit(sampleValue.unitOfMeasure)
        )

    @Throws(IllegalStateException::class)
    fun genToCoreReq(meterValuesReq: MeterValuesReqGen): MeterValuesReq {
        val connectorId: Int = meterValuesReq.evseId
        val meterValue = meterValuesReq.meterValue
        val meterValueList = meterValue.map { (s, t) ->
            MeterValue(sampledValue = s.map { convertSampledValue(it) }, timestamp = t)
        }
        return MeterValuesReq(connectorId = connectorId, meterValue = meterValueList)
    }

    abstract fun coreToGenResp(meterValuesResp: MeterValuesResp?): MeterValuesRespGen
}