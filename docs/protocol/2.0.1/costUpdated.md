# costUpdated — OCPP 2.0.1

- direction: **CSMS -> Charging Station** (`OcppInitiator.CENTRAL_SYSTEM`)
- registry entry: `Actions.COSTUPDATED`
- Kotlin `CostUpdatedReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedReq.kt)
- Kotlin `CostUpdatedResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/costupdated/CostUpdatedResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.11 CostUpdated — pdf-page 352
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.11.1 CostUpdatedRequest — pdf-page 352
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.11.2 CostUpdatedResponse — pdf-page 352

## costUpdated request

- schema: [`CostUpdatedRequest.json`](../../../ocpp-2-0-json/src/main/resources/CostUpdatedRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `costUpdated.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `costUpdated.req.customData.vendorId` — string, required, maxLength 255
- `costUpdated.req.totalCost` — number, required — Current total cost, based on the information known by the CSMS, of the transaction including taxes. In the currency configured with the configuration Variable: [&lt;&lt;configkey-currency, Currency&gt;&gt;]
- `costUpdated.req.transactionId` — string, required, maxLength 36 — Transaction Id of the transaction the current cost are asked for.

## costUpdated response

- schema: [`CostUpdatedResponse.json`](../../../ocpp-2-0-json/src/main/resources/CostUpdatedResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `costUpdated.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `costUpdated.resp.customData.vendorId` — string, required, maxLength 255

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
