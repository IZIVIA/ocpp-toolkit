package fr.simatix.cs.simulator.api.model.clearcache

import fr.simatix.cs.simulator.api.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class ClearCacheResp(
    val status: ClearCacheStatusEnumType,
    val statusInfo: StatusInfoType? = null
)