# signedUpdateFirmware — OCPP 1.6

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.SIGNEDUPDATEFIRMWARE`
- Kotlin `SignedUpdateFirmwareReq`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareReq.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareReq.kt)
- Kotlin `SignedUpdateFirmwareResp`: [`ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareResp.kt`](../../../ocpp-1-6-core/src/main/kotlin/com/izivia/ocpp/core16/model/signedupdatefirmware/SignedUpdateFirmwareResp.kt)
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.21 SignedUpdateFirmware.req — pdf-page 54
- spec: [`ocpp-1.6-security-whitepaper`](../spec/1.6/ocpp-1.6-security-whitepaper.md) §5.22 SignedUpdateFirmware.conf — pdf-page 55

## signedUpdateFirmware request

- schema: [`SignedUpdateFirmwareRequest.json`](../../../ocpp-1-6-json/src/main/resources/SignedUpdateFirmwareRequest.json) · `urn:OCPP:Cp:1.6:2020:3:SignedUpdateFirmware.req`
- `additionalProperties: false` — an unknown field fails validation

- `signedUpdateFirmware.req.retries` — integer, optional
- `signedUpdateFirmware.req.retryInterval` — integer, optional
- `signedUpdateFirmware.req.requestId` — integer, required
- `signedUpdateFirmware.req.firmware` — FirmwareType, required
- `signedUpdateFirmware.req.firmware.location` — string, required, maxLength 512
- `signedUpdateFirmware.req.firmware.retrieveDateTime` — string, required, format date-time
- `signedUpdateFirmware.req.firmware.installDateTime` — string, optional, format date-time
- `signedUpdateFirmware.req.firmware.signingCertificate` — string, required, maxLength 5500
- `signedUpdateFirmware.req.firmware.signature` — string, required, maxLength 800

## signedUpdateFirmware response

- schema: [`SignedUpdateFirmwareResponse.json`](../../../ocpp-1-6-json/src/main/resources/SignedUpdateFirmwareResponse.json) · `urn:OCPP:Cp:1.6:2020:3:SignedUpdateFirmware.conf`
- `additionalProperties: false` — an unknown field fails validation

- `signedUpdateFirmware.resp.status` — UpdateFirmwareStatusEnumType (string), required, enum: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate

### signedUpdateFirmware enumerations

- `signedUpdateFirmware` `UpdateFirmwareStatusEnumType`: Accepted | Rejected | AcceptedCanceled | InvalidCertificate | RevokedCertificate

---

[all OCPP 1.6 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
