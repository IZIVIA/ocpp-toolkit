package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.getvariables.GetVariableDataType
import fr.simatix.cs.simulator.api.model.getvariables.enumeration.GetVariableStatusEnumType
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationReq
import fr.simatix.cs.simulator.core16.model.getconfiguration.GetConfigurationResp
import fr.simatix.cs.simulator.core16.model.getconfiguration.KeyValue
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesReq as GetVariablesReqGen
import fr.simatix.cs.simulator.api.model.getvariables.GetVariablesResp as GetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = [CommonMapper::class])
abstract class GetConfigurationMapper {

    fun genToCoreResp(getConfigResp: GetVariablesRespGen): GetConfigurationResp {
        val knownKeys = mutableListOf<KeyValue>()
        val unknownKeys = mutableListOf<String>()
        getConfigResp.getVariableResult.map {
            when (it.attributeStatus) {
                //TODO see how to get readonly value
                GetVariableStatusEnumType.Accepted -> {
                    knownKeys.add(KeyValue(it.variable.name + it.variable.instance, true, it.attributeValue))
                }
                GetVariableStatusEnumType.Rejected -> {
                    knownKeys.add(KeyValue(it.variable.name + it.variable.instance, false))
                }
                else -> {
                    unknownKeys.add(it.variable.name + it.variable.instance)
                }
            }
        }
        return GetConfigurationResp(knownKeys, unknownKeys)
    }

    fun coreToGenReq(getConfigReq: GetConfigurationReq): GetVariablesReqGen =
        if (getConfigReq.key != null) {
            GetVariablesReqGen(getConfigReq.key!!.map { GetVariableDataType(ComponentType(it), VariableType(it)) })
        } else {
            throw IllegalArgumentException("key attribute can't be null")
        }

}