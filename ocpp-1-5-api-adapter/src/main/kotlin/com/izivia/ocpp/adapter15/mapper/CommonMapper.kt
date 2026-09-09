package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.SignedMeterValueType
import com.izivia.ocpp.api.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.LocationEnumType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.common.getTypeByIdToken
import com.izivia.ocpp.core15.model.common.IdTagInfo
import com.izivia.ocpp.core15.model.common.SampledValue
import com.izivia.ocpp.core15.model.common.enumeration.*
import org.mapstruct.Mapper
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy
import org.slf4j.LoggerFactory
import com.izivia.ocpp.api.model.common.UnitOfMeasure as UnitOfMeasureGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class CommonMapper {

    companion object {

        fun convertIdTagInfo(idTag: IdTagInfo): IdTokenInfoType {
            val status = AuthorizationStatusEnumType.valueOf(idTag.status.name)
            val groupIdToken = idTag.parentIdTag?.let { parentIdTag ->
                IdTokenType(parentIdTag, parentIdTag.getTypeByIdToken())
            }
            return IdTokenInfoType(
                status = status, cacheExpiryDateTime = idTag.expiryDate, groupIdToken = groupIdToken
            )
        }

        fun filterMeterValues(meterValues: List<MeterValueType>?, action: String, context: ReadingContextEnumType): Int {
            val values = meterValues
                ?: throw IllegalArgumentException("Argument meterValue is required in OCPP 1.5 to $action a transaction")

            val filteredValues = values.map { (sampledValues, timestamp) ->
                val filteredSampledValues = sampledValues
                    .filter { it.context == context && it.measurand == MeasurandEnumType.EnergyActiveImportRegister }
                    .also {
                        when {
                            it.size <= 1 -> Unit
                            else -> throw IllegalArgumentException("A meter value MUST have at most 1 sampled value with the context ${context.value} : ${it.size} > 1")
                        }
                    }
                MeterValueType(sampledValue = filteredSampledValues, timestamp = timestamp)
            }.filter { it.sampledValue.isNotEmpty() }

            return when {
                filteredValues.size == 1 -> filteredValues[0].sampledValue[0].value.toInt()
                filteredValues.isEmpty() -> throw IllegalArgumentException("At least 1 sampled value with the context ${context.value} MUST be given in property meterValues")
                else -> throw IllegalArgumentException("Multiple meter values have a sampled value with the context ${context.value} : ${filteredValues.size} > 1")
            }
        }

        private val logger = LoggerFactory.getLogger(CommonMapper::class.java)

        /**
         * Units by their OCPP 1.5 wire value. OCPP 1.5 spells a few of them differently from the
         * generic (2.0-shaped) model, which uses the SI symbols, so those are aliased explicitly:
         * an ampere reading must not be relabelled as Wh.
         */
        private val unitsByWireValue: Map<String, UnitOfMeasure> =
            UnitOfMeasure.entries.associateBy { it.value } +
                mapOf("A" to UnitOfMeasure.Amp, "V" to UnitOfMeasure.Volt)

        private fun absentFromOcpp15(kind: String, value: Enum<*>): Nothing =
            throw IllegalArgumentException("INVALID REQUEST : $kind.${value.name} doesn't exists in OCPP 1.5")

        private fun convertReadingContext(value: ReadingContextEnumType?): ReadingContext =
            when (value) {
                null -> ReadingContext.SamplePeriodic
                ReadingContextEnumType.InterruptionBegin -> ReadingContext.InterruptionBegin
                ReadingContextEnumType.InterruptionEnd -> ReadingContext.InterruptionEnd
                ReadingContextEnumType.SampleClock -> ReadingContext.SampleClock
                ReadingContextEnumType.SamplePeriodic -> ReadingContext.SamplePeriodic
                ReadingContextEnumType.TransactionBegin -> ReadingContext.TransactionBegin
                ReadingContextEnumType.TransactionEnd -> ReadingContext.TransactionEnd

                // OCPP 1.5 only models the six contexts above; there is no reasonable stand-in for these,
                // so reject rather than mislabel the reading.
                ReadingContextEnumType.Trigger,
                ReadingContextEnumType.Other ->
                    absentFromOcpp15("ReadingContext", value)
            }

        private fun convertLocation(value: LocationEnumType?): Location =
            when (value) {
                null -> Location.Outlet
                LocationEnumType.Inlet -> Location.Inlet
                LocationEnumType.Outlet -> Location.Outlet
                LocationEnumType.Body -> Location.Body

                // OCPP 1.5 only knows Inlet/Outlet/Body: a measurement taken on the cable or in the vehicle
                // has no equivalent, and mapping it to Outlet would misreport where it was taken.
                LocationEnumType.Cable,
                LocationEnumType.EV ->
                    absentFromOcpp15("Location", value)
            }

        @Throws(IllegalArgumentException::class)
        private fun convertMeasurand(value: MeasurandEnumType?): Measurand =
            when (value) {
                null -> Measurand.EnergyActiveImportRegister
                MeasurandEnumType.EnergyActiveExportRegister -> Measurand.EnergyActiveExportRegister
                MeasurandEnumType.EnergyActiveImportRegister -> Measurand.EnergyActiveImportRegister
                MeasurandEnumType.EnergyReactiveExportRegister -> Measurand.EnergyReactiveExportRegister
                MeasurandEnumType.EnergyReactiveImportRegister -> Measurand.EnergyReactiveImportRegister
                MeasurandEnumType.EnergyActiveExportInterval -> Measurand.EnergyActiveExportInterval
                MeasurandEnumType.EnergyActiveImportInterval -> Measurand.EnergyActiveImportInterval
                MeasurandEnumType.EnergyReactiveExportInterval -> Measurand.EnergyReactiveExportInterval
                MeasurandEnumType.EnergyReactiveImportInterval -> Measurand.EnergyReactiveImportInterval
                MeasurandEnumType.PowerActiveExport -> Measurand.PowerActiveExport
                MeasurandEnumType.PowerActiveImport -> Measurand.PowerActiveImport
                MeasurandEnumType.PowerReactiveExport -> Measurand.PowerReactiveExport
                MeasurandEnumType.PowerReactiveImport -> Measurand.PowerReactiveImport
                MeasurandEnumType.CurrentImport -> Measurand.CurrentImport
                MeasurandEnumType.CurrentExport -> Measurand.CurrentExport
                MeasurandEnumType.Voltage -> Measurand.Voltage
                MeasurandEnumType.Temperature -> Measurand.Temperature

                // Measurands introduced after OCPP 1.5: no equivalent, and no catch-all to degrade to.
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
                MeasurandEnumType.RPM ->
                    absentFromOcpp15("Measurand", value)
            }

        private fun convertUnit(value: UnitOfMeasureGen?): UnitOfMeasure {
            val unit = value?.unit ?: return UnitOfMeasure.Wh
            return unitsByWireValue[unit] ?: run {
                logger.warn("UnitOfMeasure $unit has no OCPP 1.5 equivalent, defaulted to Wh")
                UnitOfMeasure.Wh
            }
        }

        private fun convertFormat(value: SignedMeterValueType?): ValueFormat =
            if (value != null) {
                ValueFormat.SignedData
            } else {
                ValueFormat.Raw
            }

        fun convertSampledValue(sampleValue: SampledValueType): SampledValue =
            SampledValue(
                value = sampleValue.value.toString(),
                context = convertReadingContext(sampleValue.context),
                format = convertFormat(sampleValue.signedMeterValue),
                location = convertLocation(sampleValue.location),
                measurand = convertMeasurand(sampleValue.measurand),
                unit = convertUnit(sampleValue.unitOfMeasure)
            )
    }

    @Named("convertIdTokenType")
    fun convertIdTokenType(idToken: IdTokenType?): String =
        idToken?.idToken
            ?: throw IllegalArgumentException("Argument idToken is required in OCPP 1.5 to start/update a transaction")

    @Named("convertEVSEType")
    fun convertEVSEType(evse: EVSEType?): Int {
        val knownEvse = evse
            ?: throw IllegalArgumentException("Argument evse is required in OCPP 1.5 to start a transaction")
        return knownEvse.connectorId ?: knownEvse.id
    }

    @Named("convertIdTag")
    fun convertIdTag(idTag: String): IdTokenType = IdTokenType(idTag, idTag.getTypeByIdToken())

}
