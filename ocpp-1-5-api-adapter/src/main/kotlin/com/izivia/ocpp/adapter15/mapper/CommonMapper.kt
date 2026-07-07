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

        private fun convertReadingContext(value: ReadingContextEnumType?): ReadingContext =
            value?.let { ReadingContext.valueOf(it.name) } ?: ReadingContext.SamplePeriodic


        private fun convertLocation(value: LocationEnumType?): Location =
            value?.let { Location.valueOf(it.name) } ?: Location.Outlet

        @Throws(IllegalStateException::class)
        private fun convertMeasurand(value: MeasurandEnumType?): Measurand =
            when (value) {
                null -> Measurand.EnergyActiveImportRegister
                MeasurandEnumType.EnergyActiveNet,
                MeasurandEnumType.EnergyReactiveNet,
                MeasurandEnumType.EnergyApparentExport,
                MeasurandEnumType.EnergyApparentImport,
                MeasurandEnumType.EnergyApparentNet -> throw IllegalStateException("INVALID REQUEST : Measurand.${value.name} doesn't exists in OCPP 1.5")
                else -> Measurand.valueOf(value.name)
            }

        private fun convertUnit(value: UnitOfMeasureGen?): UnitOfMeasure =
            if (value != null && enumValues<UnitOfMeasure>().any { it.value == value.unit }) {
                UnitOfMeasure.valueOf(value.unit!!)
            } else {
                UnitOfMeasure.Wh
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
