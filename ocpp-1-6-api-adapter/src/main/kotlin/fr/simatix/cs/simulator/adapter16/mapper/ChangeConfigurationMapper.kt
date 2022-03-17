package fr.simatix.cs.simulator.adapter16.mapper

import fr.simatix.cs.simulator.api.model.common.ComponentType
import fr.simatix.cs.simulator.api.model.common.VariableType
import fr.simatix.cs.simulator.api.model.setvariables.SetVariableDataType
import fr.simatix.cs.simulator.api.model.setvariables.enumeration.SetVariableStatusEnumType
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationReq
import fr.simatix.cs.simulator.core16.model.changeconfiguration.ChangeConfigurationResp
import fr.simatix.cs.simulator.core16.model.changeconfiguration.enumeration.ConfigurationStatus
import org.mapstruct.Mapper
import org.mapstruct.ReportingPolicy
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesReq as SetVariablesReqGen
import fr.simatix.cs.simulator.api.model.setvariables.SetVariablesResp as SetVariablesRespGen

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class ChangeConfigurationMapper {

    private fun convertVariableEnum(value: SetVariableStatusEnumType): ConfigurationStatus =
        when (value) {
            SetVariableStatusEnumType.UnknownComponent,
            SetVariableStatusEnumType.UnknownVariable,
            SetVariableStatusEnumType.NotSupportedAttributeType -> ConfigurationStatus.NotSupported
            else -> ConfigurationStatus.valueOf(value.name)
        }

    fun genToCoreResp(changeConfigResp: SetVariablesRespGen): ChangeConfigurationResp =
        if (changeConfigResp.setVariableResult.size != 1) {
            throw IllegalStateException("SetVariables must return exactly 1 SetVariableResultType : ${changeConfigResp.setVariableResult.size} != 1")
        } else {
            ChangeConfigurationResp(convertVariableEnum(changeConfigResp.setVariableResult[0].attributeStatus))
        }


    fun coreToGenReq(changeConfigReq: ChangeConfigurationReq): SetVariablesReqGen =
        SetVariablesReqGen(
            listOf(
                SetVariableDataType(
                    attributeValue = changeConfigReq.value,
                    component = ComponentType(changeConfigReq.key),
                    variable = VariableType(changeConfigReq.key)
                )
            )
        )
}