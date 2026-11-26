package com.izivia.ocpp.operation.information

/**
 * What the CSMS allows for a charging station.
 *
 * @property acceptConnection whether the station may connect and be sent requests. A station refused on
 * WebSocket fails its handshake with an HTTP 404 and never has a connection. To keep a station connected
 * while refusing it, accept the connection and answer its BootNotification with Rejected or Pending.
 */
data class ChargingStationConfig(
    val acceptConnection: Boolean,
    val soapUrl: String?,
)
