package com.izivia.ocpp.adapter12.mapper

import com.izivia.ocpp.api.model.common.EVSEType
import com.izivia.ocpp.api.model.common.IdTokenInfoType
import com.izivia.ocpp.api.model.common.IdTokenType
import com.izivia.ocpp.api.model.common.MeterValueType
import com.izivia.ocpp.api.model.common.SampledValueType
import com.izivia.ocpp.api.model.common.enumeration.AuthorizationStatusEnumType
import com.izivia.ocpp.api.model.common.enumeration.MeasurandEnumType
import com.izivia.ocpp.api.model.common.enumeration.ReadingContextEnumType
import com.izivia.ocpp.api.model.common.getTypeByIdToken
import com.izivia.ocpp.core12.model.common.IdTagInfo
import org.mapstruct.Mapper
import org.mapstruct.Named
import org.mapstruct.ReportingPolicy

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

        /**
         * Extracts the single EnergyActiveImportRegister reading (as OCPP 1.2 integer Wh) from a set of
         * sampled values, optionally restricted to a reading [context]. Returns null when none is present
         * and throws when more than one matches (ambiguous). Shared by the MeterValues and Start/Stop paths
         * so both select the reading with the same rule.
         */
        fun singleEnergyRegister(sampledValues: List<SampledValueType>, context: ReadingContextEnumType? = null): Int? {
            val matches = sampledValues.filter {
                it.measurand == MeasurandEnumType.EnergyActiveImportRegister && (context == null || it.context == context)
            }
            return when (matches.size) {
                0 -> null
                1 -> matches[0].value.toInt()
                else -> throw IllegalArgumentException("At most 1 EnergyActiveImportRegister sampled value expected: ${matches.size} > 1")
            }
        }

        fun filterMeterValues(meterValues: List<MeterValueType>?, action: String, context: ReadingContextEnumType): Int {
            val values = meterValues
                ?: throw IllegalArgumentException("Argument meterValue is required in OCPP 1.2 to $action a transaction")

            val readings = values.mapNotNull { (sampledValues, _) -> singleEnergyRegister(sampledValues, context) }

            return when (readings.size) {
                1 -> readings[0]
                0 -> throw IllegalArgumentException("At least 1 sampled value with the context ${context.value} MUST be given in property meterValues")
                else -> throw IllegalArgumentException("Multiple meter values have a sampled value with the context ${context.value} : ${readings.size} > 1")
            }
        }

    }

    @Named("convertIdTokenType")
    fun convertIdTokenType(idToken: IdTokenType?): String =
        idToken?.idToken
            ?: throw IllegalArgumentException("Argument idToken is required in OCPP 1.2 to start/update a transaction")

    @Named("convertEVSEType")
    fun convertEVSEType(evse: EVSEType?): Int {
        val knownEvse = evse
            ?: throw IllegalArgumentException("Argument evse is required in OCPP 1.2 to start a transaction")
        return knownEvse.connectorId ?: knownEvse.id
    }

    @Named("convertIdTag")
    fun convertIdTag(idTag: String): IdTokenType = IdTokenType(idTag, idTag.getTypeByIdToken())

}
