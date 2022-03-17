package fr.simatix.cs.simulator.core20.model.clearcache

import fr.simatix.cs.simulator.core20.model.clearcache.enumeration.ClearCacheStatusEnumType
import fr.simatix.cs.simulator.core20.model.common.StatusInfoType

data class ClearCacheResp(
    val status: ClearCacheStatusEnumType,
    val statusInfo: StatusInfoType? = null
)