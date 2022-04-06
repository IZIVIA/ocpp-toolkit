package fr.simatix.cs.simulator.core16.model.firmwarestatusnotification

import fr.simatix.cs.simulator.core16.model.firmwarestatusnotification.enumeration.FirmwareStatus

data class FirmwareStatusNotificationReq(
        val status: FirmwareStatus
)