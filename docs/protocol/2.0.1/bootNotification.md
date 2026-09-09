# bootNotification — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.BOOTNOTIFICATION`
- Kotlin `BootNotificationReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationReq.kt)
- Kotlin `BootNotificationResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/bootnotification/BootNotificationResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.2 BootNotification — pdf-page 348
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.2.1 BootNotificationRequest — pdf-page 348
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.2.2 BootNotificationResponse — pdf-page 348
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 10, 11, 69
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 14, 22, 48, 99, 109

## bootNotification request

- schema: [`BootNotificationRequest.json`](../../../ocpp-2-0-json/src/main/resources/BootNotificationRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation` — ChargingStationType, required — Charge_ Point urn:x-oca:ocpp:uid:2:233122 The physical system where an Electrical Vehicle (EV) can be charged.
- `bootNotification.req.chargingStation.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.chargingStation.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation.serialNumber` — string, optional, maxLength 25 — Device. Serial_ Number. Serial_ Number urn:x-oca:ocpp:uid:1:569324 Vendor-specific device identifier.
- `bootNotification.req.chargingStation.model` — string, required, maxLength 20 — Device. Model. CI20_ Text urn:x-oca:ocpp:uid:1:569325 Defines the model of the device.
- `bootNotification.req.chargingStation.modem` — ModemType, optional — Wireless_ Communication_ Module urn:x-oca:ocpp:uid:2:233306 Defines parameters required for initiating and maintaining wireless communication with other devices.
- `bootNotification.req.chargingStation.modem.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.req.chargingStation.modem.customData.vendorId` — string, required, maxLength 255
- `bootNotification.req.chargingStation.modem.iccid` — string, optional, maxLength 20 — Wireless_ Communication_ Module. ICCID. CI20_ Text urn:x-oca:ocpp:uid:1:569327 This contains the ICCID of the modem’s SIM card.
- `bootNotification.req.chargingStation.modem.imsi` — string, optional, maxLength 20 — Wireless_ Communication_ Module. IMSI. CI20_ Text urn:x-oca:ocpp:uid:1:569328 This contains the IMSI of the modem’s SIM card.
- `bootNotification.req.chargingStation.vendorName` — string, required, maxLength 50 — Identifies the vendor (not necessarily in a unique manner).
- `bootNotification.req.chargingStation.firmwareVersion` — string, optional, maxLength 50 — This contains the firmware version of the Charging Station.
- `bootNotification.req.reason` — BootReasonEnumType (string), required, enum: ApplicationReset | FirmwareUpdate | LocalReset | PowerUp | RemoteReset | ScheduledReset | Triggered | Unknown | Watchdog — This contains the reason for sending this message to the CSMS.

## bootNotification response

- schema: [`BootNotificationResponse.json`](../../../ocpp-2-0-json/src/main/resources/BootNotificationResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `bootNotification.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.resp.customData.vendorId` — string, required, maxLength 255
- `bootNotification.resp.currentTime` — string, required, format date-time — This contains the CSMS’s current time.
- `bootNotification.resp.interval` — integer, required — When &lt;&lt;cmn_registrationstatusenumtype,Status&gt;&gt; is Accepted, this contains the heartbeat interval in seconds. If the CSMS returns something other than Accepted, the value of the interval field indicates the minimum wait time before sending a next BootNotification request.
- `bootNotification.resp.status` — RegistrationStatusEnumType (string), required, enum: Accepted | Pending | Rejected — This contains whether the Charging Station has been registered within the CSMS.
- `bootNotification.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `bootNotification.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `bootNotification.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `bootNotification.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `bootNotification.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### bootNotification enumerations

- `bootNotification` `BootReasonEnumType`: ApplicationReset | FirmwareUpdate | LocalReset | PowerUp | RemoteReset | ScheduledReset | Triggered | Unknown | Watchdog — This contains the reason for sending this message to the CSMS.
- `bootNotification` `RegistrationStatusEnumType`: Accepted | Pending | Rejected — This contains whether the Charging Station has been registered within the CSMS.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
