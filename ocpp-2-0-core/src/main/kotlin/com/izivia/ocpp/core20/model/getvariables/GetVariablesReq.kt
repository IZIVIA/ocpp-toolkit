package com.izivia.ocpp.core20.model.getvariables

data class GetVariablesReq(
    val getVariableData: List<GetVariableDataType>
)