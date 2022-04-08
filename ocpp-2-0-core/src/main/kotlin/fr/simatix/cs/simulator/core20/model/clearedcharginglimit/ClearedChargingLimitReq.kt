package fr.simatix.cs.simulator.api.model.clearedcharginglimit

import fr.simatix.cs.simulator.core20.model.common.enumeration.ChargingLimitSourceEnumType


data class ClearedChargingLimitReq (
    val chargingLimitSource: ChargingLimitSourceEnumType,
    val evseId: Int? = null
)
