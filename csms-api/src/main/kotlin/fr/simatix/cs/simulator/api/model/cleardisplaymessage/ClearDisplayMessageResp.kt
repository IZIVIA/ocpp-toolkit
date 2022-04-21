package fr.simatix.cs.simulator.api.model.cleardisplaymessage

import fr.simatix.cs.simulator.api.model.Response
import fr.simatix.cs.simulator.api.model.cleardisplaymessage.enumeration.ClearMessageStatusEnumType
import fr.simatix.cs.simulator.api.model.common.StatusInfoType

data class ClearDisplayMessageResp(
        val status: ClearMessageStatusEnumType,
        val statusInfo: StatusInfoType? = null
): Response