# unlockConnector — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.UNLOCKCONNECTOR`
- Kotlin `UnlockConnectorReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorReq.kt)
- Kotlin `UnlockConnectorResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/unlockconnector/UnlockConnectorResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §2.2 Unlock Connector — pdf-page 187
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.62 UnlockConnector — pdf-page 377
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.62.1 UnlockConnectorRequest — pdf-page 377
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.62.2 UnlockConnectorResponse — pdf-page 378

## unlockConnector request

- schema: [`UnlockConnectorRequest.json`](../../../ocpp-2-0-json/src/main/resources/UnlockConnectorRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.req.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.req.evseId` — integer, required — This contains the identifier of the EVSE for which a connector needs to be unlocked.
- `unlockConnector.req.connectorId` — integer, required — This contains the identifier of the connector that needs to be unlocked.

## unlockConnector response

- schema: [`UnlockConnectorResponse.json`](../../../ocpp-2-0-json/src/main/resources/UnlockConnectorResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `unlockConnector.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.resp.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.resp.status` — UnlockStatusEnumType (string), required, enum: Unlocked | UnlockFailed | OngoingAuthorizedTransaction | UnknownConnector — This indicates whether the Charging Station has unlocked the connector.
- `unlockConnector.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `unlockConnector.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `unlockConnector.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `unlockConnector.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `unlockConnector.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### unlockConnector enumerations

- `unlockConnector` `UnlockStatusEnumType`: Unlocked | UnlockFailed | OngoingAuthorizedTransaction | UnknownConnector — This indicates whether the Charging Station has unlocked the connector.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
