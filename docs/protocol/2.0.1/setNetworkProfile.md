# setNetworkProfile — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SETNETWORKPROFILE`
- Kotlin `SetNetworkProfileReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileReq.kt)
- Kotlin `SetNetworkProfileResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/setnetworkprofile/SetNetworkProfileResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.55 SetNetworkProfile — pdf-page 373
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.55.1 SetNetworkProfileRequest — pdf-page 373
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.55.2 SetNetworkProfileResponse — pdf-page 374

## setNetworkProfile request

- schema: [`SetNetworkProfileRequest.json`](../../../ocpp-2-0-json/src/main/resources/SetNetworkProfileRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setNetworkProfile.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.configurationSlot` — integer, required — Slot in which the configuration should be stored.
- `setNetworkProfile.req.connectionData` — NetworkConnectionProfileType, required — Communication_ Function urn:x-oca:ocpp:uid:2:233304 The NetworkConnectionProfile defines the functional and technical parameters of a communication link.
- `setNetworkProfile.req.connectionData.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.apn` — APNType, optional — APN urn:x-oca:ocpp:uid:2:233134 Collection of configuration data needed to make a data-connection over a cellular network. NOTE: When asking a GSM modem to dial in, it is possible to specify which mobile operator should be used. This can be done with the mobile country code (MCC) in combination with a mobile network code (MNC). Example: If your preferred network is Vodafone Netherlands, the MCC=204 and the MNC=04 which means the key PreferredNetwork = 20404 Some modems allows to specify a preferred network, which means, if this network is not available, a different network is used. If you specify UseOnlyPreferredNetwork and this network is not available, the modem will not dial in.
- `setNetworkProfile.req.connectionData.apn.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.apn.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.apn.apn` — string, required, maxLength 512 — APN. APN. URI urn:x-oca:ocpp:uid:1:568814 The Access Point Name as an URL.
- `setNetworkProfile.req.connectionData.apn.apnUserName` — string, optional, maxLength 20 — APN. APN. User_ Name urn:x-oca:ocpp:uid:1:568818 APN username.
- `setNetworkProfile.req.connectionData.apn.apnPassword` — string, optional, maxLength 20 — APN. APN. Password urn:x-oca:ocpp:uid:1:568819 APN Password.
- `setNetworkProfile.req.connectionData.apn.simPin` — integer, optional — APN. SIMPIN. PIN_ Code urn:x-oca:ocpp:uid:1:568821 SIM card pin code.
- `setNetworkProfile.req.connectionData.apn.preferredNetwork` — string, optional, maxLength 6 — APN. Preferred_ Network. Mobile_ Network_ ID urn:x-oca:ocpp:uid:1:568822 Preferred network, written as MCC and MNC concatenated. See note.
- `setNetworkProfile.req.connectionData.apn.useOnlyPreferredNetwork` — boolean, optional — APN. Use_ Only_ Preferred_ Network. Indicator urn:x-oca:ocpp:uid:1:568824 Default: false. Use only the preferred Network, do not dial in when not available. See Note.
- `setNetworkProfile.req.connectionData.apn.apnAuthentication` — APNAuthenticationEnumType (string), required, enum: CHAP | NONE | PAP | AUTO — APN. APN_ Authentication. APN_ Authentication_ Code urn:x-oca:ocpp:uid:1:568828 Authentication method.
- `setNetworkProfile.req.connectionData.ocppVersion` — OCPPVersionEnumType (string), required, enum: OCPP12 | OCPP15 | OCPP16 | OCPP20 — Communication_ Function. OCPP_ Version. OCPP_ Version_ Code urn:x-oca:ocpp:uid:1:569355 Defines the OCPP version used for this communication function.
- `setNetworkProfile.req.connectionData.ocppTransport` — OCPPTransportEnumType (string), required, enum: JSON | SOAP — Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code urn:x-oca:ocpp:uid:1:569356 Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
- `setNetworkProfile.req.connectionData.ocppCsmsUrl` — string, required, maxLength 512 — Communication_ Function. OCPP_ Central_ System_ URL. URI urn:x-oca:ocpp:uid:1:569357 URL of the CSMS(s) that this Charging Station communicates with.
- `setNetworkProfile.req.connectionData.messageTimeout` — integer, required — Duration in seconds before a message send by the Charging Station via this network connection times-out. The best setting depends on the underlying network and response times of the CSMS. If you are looking for a some guideline: use 30 seconds as a starting point.
- `setNetworkProfile.req.connectionData.securityProfile` — integer, required — This field specifies the security profile used when connecting to the CSMS with this NetworkConnectionProfile.
- `setNetworkProfile.req.connectionData.ocppInterface` — OCPPInterfaceEnumType (string), required, enum: Wired0 | Wired1 | Wired2 | Wired3 | Wireless0 | Wireless1 | Wireless2 | Wireless3 — Applicable Network Interface.
- `setNetworkProfile.req.connectionData.vpn` — VPNType, optional — VPN urn:x-oca:ocpp:uid:2:233268 VPN Configuration settings
- `setNetworkProfile.req.connectionData.vpn.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.req.connectionData.vpn.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.req.connectionData.vpn.server` — string, required, maxLength 512 — VPN. Server. URI urn:x-oca:ocpp:uid:1:569272 VPN Server Address
- `setNetworkProfile.req.connectionData.vpn.user` — string, required, maxLength 20 — VPN. User. User_ Name urn:x-oca:ocpp:uid:1:569273 VPN User
- `setNetworkProfile.req.connectionData.vpn.group` — string, optional, maxLength 20 — VPN. Group. Group_ Name urn:x-oca:ocpp:uid:1:569274 VPN group.
- `setNetworkProfile.req.connectionData.vpn.password` — string, required, maxLength 20 — VPN. Password. Password urn:x-oca:ocpp:uid:1:569275 VPN Password.
- `setNetworkProfile.req.connectionData.vpn.key` — string, required, maxLength 255 — VPN. Key. VPN_ Key urn:x-oca:ocpp:uid:1:569276 VPN shared secret.
- `setNetworkProfile.req.connectionData.vpn.type` — VPNEnumType (string), required, enum: IKEv2 | IPSec | L2TP | PPTP — VPN. Type. VPN_ Code urn:x-oca:ocpp:uid:1:569277 Type of VPN

## setNetworkProfile response

- schema: [`SetNetworkProfileResponse.json`](../../../ocpp-2-0-json/src/main/resources/SetNetworkProfileResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `setNetworkProfile.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.resp.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.resp.status` — SetNetworkProfileStatusEnumType (string), required, enum: Accepted | Rejected | Failed — Result of operation.
- `setNetworkProfile.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `setNetworkProfile.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `setNetworkProfile.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `setNetworkProfile.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `setNetworkProfile.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### setNetworkProfile enumerations

- `setNetworkProfile` `APNAuthenticationEnumType`: CHAP | NONE | PAP | AUTO — APN. APN_ Authentication. APN_ Authentication_ Code urn:x-oca:ocpp:uid:1:568828 Authentication method.
- `setNetworkProfile` `OCPPInterfaceEnumType`: Wired0 | Wired1 | Wired2 | Wired3 | Wireless0 | Wireless1 | Wireless2 | Wireless3 — Applicable Network Interface.
- `setNetworkProfile` `OCPPTransportEnumType`: JSON | SOAP — Communication_ Function. OCPP_ Transport. OCPP_ Transport_ Code urn:x-oca:ocpp:uid:1:569356 Defines the transport protocol (e.g. SOAP or JSON). Note: SOAP is not supported in OCPP 2.0, but is supported by other versions of OCPP.
- `setNetworkProfile` `OCPPVersionEnumType`: OCPP12 | OCPP15 | OCPP16 | OCPP20 — Communication_ Function. OCPP_ Version. OCPP_ Version_ Code urn:x-oca:ocpp:uid:1:569355 Defines the OCPP version used for this communication function.
- `setNetworkProfile` `SetNetworkProfileStatusEnumType`: Accepted | Rejected | Failed — Result of operation.
- `setNetworkProfile` `VPNEnumType`: IKEv2 | IPSec | L2TP | PPTP — VPN. Type. VPN_ Code urn:x-oca:ocpp:uid:1:569277 Type of VPN

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
