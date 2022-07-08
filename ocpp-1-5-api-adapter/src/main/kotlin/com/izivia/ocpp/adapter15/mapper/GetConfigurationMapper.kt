package com.izivia.ocpp.adapter15.mapper

import com.izivia.ocpp.api.model.common.ComponentType
import com.izivia.ocpp.api.model.common.VariableType
import com.izivia.ocpp.api.model.getvariables.GetVariableDataType
import com.izivia.ocpp.api.model.getvariables.enumeration.GetVariableStatusEnumType
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationReq
import com.izivia.ocpp.core15.model.getconfiguration.GetConfigurationResp
import com.izivia.ocpp.core15.model.getconfiguration.KeyValue
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesReq as GetAllVariablesReqGen
import com.izivia.ocpp.api.model.getallvariables.GetAllVariablesResp as GetAllVariablesRespGen
import com.izivia.ocpp.api.model.getvariables.GetVariablesReq as GetVariablesReqGen
import com.izivia.ocpp.api.model.getvariables.GetVariablesResp as GetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class GetConfigurationMapper {

    fun genToCoreGetAllVariablesResp(getConfigResp: GetAllVariablesRespGen): GetConfigurationResp =
        GetConfigurationResp(
            getConfigResp.configurationKey?.map { KeyValue(it.key, it.readonly, it.value) }
        )

    fun coreToGenGetAllVariablesReq(): GetAllVariablesReqGen =
        GetAllVariablesReqGen()

    fun genToCoreGetVariablesResp(getConfigResp: GetVariablesRespGen): GetConfigurationResp {
        val knownKeys = mutableListOf<KeyValue>()
        val unknownKeys = mutableListOf<String>()
        getConfigResp.getVariableResult.map {
            val instance = if (it.variable.instance == null) {
                ""
            } else {
                it.variable.instance
            }
            when (it.attributeStatus) {
                GetVariableStatusEnumType.Accepted -> {
                    if (it.readonly != null) {
                        knownKeys.add(
                            KeyValue(
                                it.variable.name + instance,
                                it.readonly!!,
                                it.attributeValue
                            )
                        )
                    } else {
                        throw IllegalArgumentException("Readonly attribute is required for every GetVariableResultType in OCPP 1.6")
                    }
                }
                else -> {
                    unknownKeys.add(
                        it.variable.name + instance
                    )
                }
            }
        }
        return GetConfigurationResp(knownKeys, unknownKeys)
    }

    fun coreToGenGetVariablesReq(getConfigReq: GetConfigurationReq): GetVariablesReqGen =
        if (getConfigReq.key != null && getConfigReq.key!!.isNotEmpty()) {
            GetVariablesReqGen(getConfigReq.key!!.map { GetVariableDataType(ComponentType(it), VariableType(it)) })
        } else {
            throw IllegalArgumentException("key attribute can't be null or empty")
        }

}
