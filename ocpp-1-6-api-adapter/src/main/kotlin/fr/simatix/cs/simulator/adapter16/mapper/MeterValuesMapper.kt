package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.SampledValueType
import fr.simatix.cs.simulator.api.model.SignedMeterValueType
import fr.simatix.cs.simulator.api.model.UnitOfMeasure
import fr.simatix.cs.simulator.api.model.enumeration.LocationEnumType
import fr.simatix.cs.simulator.api.model.enumeration.MeasurandEnumType
import fr.simatix.cs.simulator.api.model.enumeration.PhaseEnumType
import fr.simatix.cs.simulator.api.model.enumeration.ReadingContextEnumType
import fr.simatix.cs.simulator.core16.model.MeterValue
import fr.simatix.cs.simulator.core16.model.MeterValuesReq
import fr.simatix.cs.simulator.core16.model.MeterValuesResp
import fr.simatix.cs.simulator.core16.model.SampledValue
import fr.simatix.cs.simulator.core16.model.enumeration.*
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.MeterValuesReq as MeterValuesReqGen
import fr.simatix.cs.simulator.api.model.MeterValuesResp as MeterValuesRespGen
import fr.simatix.cs.simulator.core16.model.enumeration.UnitOfMeasure as UnitOfMeasure16

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class MeterValuesMapper {

    private fun convertReadingContext(value: ReadingContextEnumType?): ReadingContext {
        return if (value != null) {
            ReadingContext.valueOf(value.name)
        } else ReadingContext.SamplePeriodic
    }

    private fun convertPhase(value: PhaseEnumType?): Phase? {
        return if (value != null) {
            Phase.valueOf(value.name)
        } else null
    }

    private fun convertLocation(value: LocationEnumType?): Location {
        return if (value != null) {
            Location.valueOf(value.name)
        } else Location.Outlet
    }

    @Throws(IllegalStateException::class)
    private fun convertMeasurand(value: MeasurandEnumType?): Measurand {
        return when (value) {
            null -> Measurand.EnergyActiveImportRegister
            MeasurandEnumType.EnergyActiveNet,
            MeasurandEnumType.EnergyReactiveNet,
            MeasurandEnumType.EnergyApparentExport,
            MeasurandEnumType.EnergyApparentImport,
            MeasurandEnumType.EnergyApparentNet -> throw IllegalStateException("INVALID REQUEST : Measurand.${value.name} doesn't exists in OCPP 1.6")
            else -> Measurand.valueOf(value.name)
        }
    }

    private fun convertUnit(value: UnitOfMeasure?): UnitOfMeasure16 {
        return if (value != null && enumValues<UnitOfMeasure16>().any { it.value == value.unit }) {
            UnitOfMeasure16.valueOf(value.unit!!)
        } else {
            UnitOfMeasure16.Wh
        }
    }

    private fun convertFormat(value: SignedMeterValueType?): ValueFormat {
        return if (value != null) {
            ValueFormat.SignedData
        } else {
            ValueFormat.Raw
        }
    }

    private fun convertSampledValue(sampleValue: SampledValueType): SampledValue {
        return SampledValue(
            value = sampleValue.value.toString(),
            context = convertReadingContext(sampleValue.context),
            format = convertFormat(sampleValue.signedMeterValue),
            location = convertLocation(sampleValue.location),
            measurand = convertMeasurand(sampleValue.measurand),
            phase = convertPhase(sampleValue.phase),
            unit = convertUnit(sampleValue.unitOfMeasure)
        )
    }

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