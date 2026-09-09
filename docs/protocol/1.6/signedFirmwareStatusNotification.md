# signedFirmwareStatusNotification — OCPP 1.6

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.SIGNEDFIRMWARESTATUSNOTIFICATION`
- Kotlin `SignedFirmwareStatusNotificationReq`: _class file not found_
- Kotlin `SignedFirmwareStatusNotificationResp`: _class file not found_
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.19 SignedFirmwareStatusNotification.req — pdf-page 54
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.20 SignedFirmwareStatusNotification.conf — pdf-page 54

## signedFirmwareStatusNotification request

- schema: [`SignedFirmwareStatusNotificationRequest.json`](../../../ocpp-1-6-json/src/main/resources/SignedFirmwareStatusNotificationRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignedFirmwareStatusNotification.req`
- `additionalProperties: false` — an unknown field fails validation

- `signedFirmwareStatusNotification.req.status` — FirmwareStatusEnumType (string), required, enum: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified
- `signedFirmwareStatusNotification.req.requestId` — integer, optional

## signedFirmwareStatusNotification response

- schema: [`SignedFirmwareStatusNotificationResponse.json`](../../../ocpp-1-6-json/src/main/resources/SignedFirmwareStatusNotificationResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignedFirmwareStatusNotification.conf`
- `additionalProperties: false` — an unknown field fails validation

_No fields: empty payload._

### signedFirmwareStatusNotification enumerations

- `signedFirmwareStatusNotification` `FirmwareStatusEnumType`: Downloaded | DownloadFailed | Downloading | DownloadScheduled | DownloadPaused | Idle | InstallationFailed | Installing | Installed | InstallRebooting | InstallScheduled | InstallVerificationFailed | InvalidSignature | SignatureVerified

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
