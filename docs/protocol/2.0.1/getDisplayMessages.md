# getDisplayMessages — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.GETDISPLAYMESSAGES`
- Kotlin `GetDisplayMessagesReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesReq.kt)
- Kotlin `GetDisplayMessagesResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/getdisplaymessages/GetDisplayMessagesResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.21 GetDisplayMessages — pdf-page 357
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.21.1 GetDisplayMessagesRequest — pdf-page 357
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.21.2 GetDisplayMessagesResponse — pdf-page 358
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 7

## getDisplayMessages request

- schema: [`GetDisplayMessagesRequest.json`](../../../ocpp-2-0-json/src/main/resources/GetDisplayMessagesRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getDisplayMessages.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.req.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.req.id` — array, optional, minItems 1 — If provided the Charging Station shall return Display Messages of the given ids. This field SHALL NOT contain more ids than set in &lt;&lt;configkey-number-of-display-messages,NumberOfDisplayMessages.maxLimit&gt;&gt;
- `getDisplayMessages.req.id[]` — integer
- `getDisplayMessages.req.requestId` — integer, required — The Id of this request.
- `getDisplayMessages.req.priority` — MessagePriorityEnumType (string), optional, enum: AlwaysFront | InFront | NormalCycle — If provided the Charging Station shall return Display Messages with the given priority only.
- `getDisplayMessages.req.state` — MessageStateEnumType (string), optional, enum: Charging | Faulted | Idle | Unavailable — If provided the Charging Station shall return Display Messages with the given state only.

## getDisplayMessages response

- schema: [`GetDisplayMessagesResponse.json`](../../../ocpp-2-0-json/src/main/resources/GetDisplayMessagesResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `getDisplayMessages.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.resp.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.resp.status` — GetDisplayMessagesStatusEnumType (string), required, enum: Accepted | Unknown — Indicates if the Charging Station has Display Messages that match the request criteria in the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt;
- `getDisplayMessages.resp.statusInfo` — StatusInfoType, optional — Element providing more information about the status.
- `getDisplayMessages.resp.statusInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `getDisplayMessages.resp.statusInfo.customData.vendorId` — string, required, maxLength 255
- `getDisplayMessages.resp.statusInfo.reasonCode` — string, required, maxLength 20 — A predefined code for the reason why the status is returned in this response. The string is case-insensitive.
- `getDisplayMessages.resp.statusInfo.additionalInfo` — string, optional, maxLength 512 — Additional text to provide detailed information.

### getDisplayMessages enumerations

- `getDisplayMessages` `GetDisplayMessagesStatusEnumType`: Accepted | Unknown — Indicates if the Charging Station has Display Messages that match the request criteria in the &lt;&lt;getdisplaymessagesrequest,GetDisplayMessagesRequest&gt;&gt;
- `getDisplayMessages` `MessagePriorityEnumType`: AlwaysFront | InFront | NormalCycle — If provided the Charging Station shall return Display Messages with the given priority only.
- `getDisplayMessages` `MessageStateEnumType`: Charging | Faulted | Idle | Unavailable — If provided the Charging Station shall return Display Messages with the given state only.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
