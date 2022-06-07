package com.izivia.ocpp.api.model.notifyreport

import com.izivia.ocpp.api.model.common.enumeration.AttributeEnumType
import com.izivia.ocpp.api.model.notifyreport.enumeration.MutabilityEnumType

data class VariableAttributeType(
    val value: String? = null,
    val type: AttributeEnumType = AttributeEnumType.Actual,
    val constant: Boolean = false,
    val mutability: MutabilityEnumType = MutabilityEnumType.ReadWrite,
    val persistent: Boolean = false
)