package fr.simatix.cs.simulator.api.model.setdisplaymessage

import fr.simatix.cs.simulator.api.model.Request
import fr.simatix.cs.simulator.api.model.common.MessageInfoType

data class SetDisplayMessageReq(
        val message : MessageInfoType
) : Request
