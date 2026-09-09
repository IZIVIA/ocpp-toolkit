# transactionEvent — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.TRANSACTIONEVENT`
- Kotlin `TransactionEventReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventReq.kt)
- Kotlin `TransactionEventResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/transactionevent/TransactionEventResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1 TransactionEventRequest — pdf-page 136
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §4 TransactionEventRequest — pdf-page 242
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.60 TransactionEvent — pdf-page 376
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.60.1 TransactionEventRequest — pdf-page 376
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.60.2 TransactionEventResponse — pdf-page 376
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §3.80 TransactionEventEnumType — pdf-page 424
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 3, 17, 19, 26, 28, 31, 72
- errata mentions: [`ocpp-2.0-part2-errata`](../spec/2.0.1/ocpp-2.0-part2-errata.md) pdf-page 66

## transactionEvent request

- schema: [`TransactionEventRequest.json`](../../../ocpp-2-0-json/src/main/resources/TransactionEventRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `transactionEvent.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.eventType` — TransactionEventEnumType (string), required, enum: Ended | Started | Updated — This contains the type of this event. The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
- `transactionEvent.req.meterValue` — array, optional, minItems 1
- `transactionEvent.req.meterValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue` — array, required, minItems 1
- `transactionEvent.req.meterValue[].sampledValue[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].value` — number, required — Sampled_ Value. Value. Measure urn:x-oca:ocpp:uid:1:569260 Indicates the measured value.
- `transactionEvent.req.meterValue[].sampledValue[].context` — ReadingContextEnumType (string), optional, enum: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `transactionEvent.req.meterValue[].sampledValue[].measurand` — MeasurandEnumType (string), optional, enum: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `transactionEvent.req.meterValue[].sampledValue[].phase` — PhaseEnumType (string), optional, enum: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `transactionEvent.req.meterValue[].sampledValue[].location` — LocationEnumType (string), optional, enum: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue` — SignedMeterValueType, optional — Represent a signed version of the meter value.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.signedMeterData` — string, required, maxLength 2500 — Base64 encoded, contains the signed data which might contain more then just the meter value. It can contain information like timestamps, reference to a customer etc.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.signingMethod` — string, required, maxLength 50 — Method used to create the digital signature.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.encodingMethod` — string, required, maxLength 50 — Method used to encode the meter values before applying the digital signature algorithm.
- `transactionEvent.req.meterValue[].sampledValue[].signedMeterValue.publicKey` — string, required, maxLength 2500 — Base64 encoded, sending depends on configuration variable _PublicKeyWithSignedMeterValue_.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure` — UnitOfMeasureType, optional — Represents a UnitOfMeasure with a multiplier
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.unit` — string, optional, maxLength 20 — Unit of the value. Default = "Wh" if the (default) measurand is an "Energy" type. This field SHALL use a value from the list Standardized Units of Measurements in Part 2 Appendices. If an applicable unit is available in that list, otherwise a "custom" unit might be used.
- `transactionEvent.req.meterValue[].sampledValue[].unitOfMeasure.multiplier` — integer, optional — Multiplier, this value represents the exponent to base 10. I.e. multiplier 3 means 10 raised to the 3rd power. Default is 0.
- `transactionEvent.req.meterValue[].timestamp` — string, required, format date-time — Meter_ Value. Timestamp. Date_ Time urn:x-oca:ocpp:uid:1:569259 Timestamp for measured value(s).
- `transactionEvent.req.timestamp` — string, required, format date-time — The date and time at which this transaction event occurred.
- `transactionEvent.req.triggerReason` — TriggerReasonEnumType (string), required, enum: Authorized | CablePluggedIn | ChargingRateChanged | ChargingStateChanged | Deauthorized | EnergyLimitReached | EVCommunicationLost | EVConnectTimeout | MeterValueClock | MeterValuePeriodic | TimeLimitReached | Trigger | UnlockCommand | StopAuthorized | EVDeparted | EVDetected | RemoteStop | RemoteStart | AbnormalCondition | SignedDataReceived | ResetCommand — Reason the Charging Station sends this message to the CSMS
- `transactionEvent.req.seqNo` — integer, required — Incremental sequence number, helps with determining if all messages of a transaction have been received.
- `transactionEvent.req.offline` — boolean, optional — Indication that this transaction event happened when the Charging Station was offline. Default = false, meaning: the event occurred when the Charging Station was online.
- `transactionEvent.req.numberOfPhasesUsed` — integer, optional — If the Charging Station is able to report the number of phases used, then it SHALL provide it. When omitted the CSMS may be able to determine the number of phases used via device management.
- `transactionEvent.req.cableMaxCurrent` — integer, optional — The maximum current of the connected cable in Ampere (A).
- `transactionEvent.req.reservationId` — integer, optional — This contains the Id of the reservation that terminates as a result of this transaction.
- `transactionEvent.req.transactionInfo` — TransactionType, required — Transaction urn:x-oca:ocpp:uid:2:233318
- `transactionEvent.req.transactionInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.transactionInfo.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.transactionInfo.transactionId` — string, required, maxLength 36 — This contains the Id of the transaction.
- `transactionEvent.req.transactionInfo.chargingState` — ChargingStateEnumType (string), optional, enum: Charging | EVConnected | SuspendedEV | SuspendedEVSE | Idle — Transaction. State. Transaction_ State_ Code urn:x-oca:ocpp:uid:1:569419 Current charging state, is required when state has changed.
- `transactionEvent.req.transactionInfo.timeSpentCharging` — integer, optional — Transaction. Time_ Spent_ Charging. Elapsed_ Time urn:x-oca:ocpp:uid:1:569415 Contains the total time that energy flowed from EVSE to EV during the transaction (in seconds). Note that timeSpentCharging is smaller or equal to the duration of the transaction.
- `transactionEvent.req.transactionInfo.stoppedReason` — ReasonEnumType (string), optional, enum: DeAuthorized | EmergencyStop | EnergyLimitReached | EVDisconnected | GroundFault | ImmediateReset | Local | LocalOutOfCredit | MasterPass | Other | OvercurrentFault | PowerLoss | PowerQuality | Reboot | Remote | SOCLimitReached | StoppedByEV | TimeLimitReached | Timeout — Transaction. Stopped_ Reason. EOT_ Reason_ Code urn:x-oca:ocpp:uid:1:569413 This contains the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
- `transactionEvent.req.transactionInfo.remoteStartId` — integer, optional — The ID given to remote start request (&lt;&lt;requeststarttransactionrequest, RequestStartTransactionRequest&gt;&gt;. This enables to CSMS to match the started transaction to the given start request.
- `transactionEvent.req.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `transactionEvent.req.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.evse.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `transactionEvent.req.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `transactionEvent.req.idToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `transactionEvent.req.idToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.idToken.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.idToken.additionalInfo` — array, optional, minItems 1
- `transactionEvent.req.idToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.req.idToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.req.idToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `transactionEvent.req.idToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `transactionEvent.req.idToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `transactionEvent.req.idToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.

## transactionEvent response

- schema: [`TransactionEventResponse.json`](../../../ocpp-2-0-json/src/main/resources/TransactionEventResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `transactionEvent.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.totalCost` — number, optional — SHALL only be sent when charging has ended. Final total cost of this transaction, including taxes. In the currency configured with the Configuration Variable: &lt;&lt;configkey-currency,`Currency`&gt;&gt;. When omitted, the transaction was NOT free. To indicate a free transaction, the CSMS SHALL send 0.00.
- `transactionEvent.resp.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; is temporarily, so it may not be set in the &lt;&lt;cmn_idtokeninfotype,IdTokenInfoType&gt;&gt; afterwards. Also the chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules the one in &lt;&lt;cmn_idtokeninfotype,IdTokenInfoType&gt;&gt;.
- `transactionEvent.resp.idTokenInfo` — IdTokenInfoType, optional — ID_ Token urn:x-oca:ocpp:uid:2:233247 Contains status information about an identifier. It is advised to not stop charging for a token that expires during charging, as ExpiryDate is only used for caching purposes. If ExpiryDate is not given, the status has no end date.
- `transactionEvent.resp.idTokenInfo.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.status` — AuthorizationStatusEnumType (string), required, enum: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `transactionEvent.resp.idTokenInfo.cacheExpiryDateTime` — string, optional, format date-time — ID_ Token. Expiry. Date_ Time urn:x-oca:ocpp:uid:1:569373 Date and Time after which the token must be considered invalid.
- `transactionEvent.resp.idTokenInfo.chargingPriority` — integer, optional — Priority from a business point of view. Default priority is 0, The range is from -9 to 9. Higher values indicate a higher priority. The chargingPriority in &lt;&lt;transactioneventresponse,TransactionEventResponse&gt;&gt; overrules this one.
- `transactionEvent.resp.idTokenInfo.language1` — string, optional, maxLength 8 — ID_ Token. Language1. Language_ Code urn:x-oca:ocpp:uid:1:569374 Preferred user interface language of identifier user. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.evseId` — array, optional, minItems 1 — Only used when the IdToken is only valid for one or more specific EVSEs, not for the entire Charging Station.
- `transactionEvent.resp.idTokenInfo.evseId[]` — integer
- `transactionEvent.resp.idTokenInfo.groupIdToken` — IdTokenType, optional — Contains a case insensitive identifier to use for the authorization and the type of authorization to support multiple forms of identifiers.
- `transactionEvent.resp.idTokenInfo.groupIdToken.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.groupIdToken.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo` — array, optional, minItems 1
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].additionalIdToken` — string, required, maxLength 36 — This field specifies the additional IdToken.
- `transactionEvent.resp.idTokenInfo.groupIdToken.additionalInfo[].type` — string, required, maxLength 50 — This defines the type of the additionalIdToken. This is a custom type, so the implementation needs to be agreed upon by all involved parties.
- `transactionEvent.resp.idTokenInfo.groupIdToken.idToken` — string, required, maxLength 36 — IdToken is case insensitive. Might hold the hidden id of an RFID tag, but can for example also contain a UUID.
- `transactionEvent.resp.idTokenInfo.groupIdToken.type` — IdTokenEnumType (string), required, enum: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `transactionEvent.resp.idTokenInfo.language2` — string, optional, maxLength 8 — ID_ Token. Language2. Language_ Code urn:x-oca:ocpp:uid:1:569375 Second preferred user interface language of identifier user. Don’t use when language1 is omitted, has to be different from language1. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.personalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `transactionEvent.resp.idTokenInfo.personalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.idTokenInfo.personalMessage.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.idTokenInfo.personalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent.resp.idTokenInfo.personalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.idTokenInfo.personalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.
- `transactionEvent.resp.updatedPersonalMessage` — MessageContentType, optional — Message_ Content urn:x-enexis:ecdm:uid:2:234490 Contains message details, for a message to be displayed on a Charging Station.
- `transactionEvent.resp.updatedPersonalMessage.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `transactionEvent.resp.updatedPersonalMessage.customData.vendorId` — string, required, maxLength 255
- `transactionEvent.resp.updatedPersonalMessage.format` — MessageFormatEnumType (string), required, enum: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent.resp.updatedPersonalMessage.language` — string, optional, maxLength 8 — Message_ Content. Language. Language_ Code urn:x-enexis:ecdm:uid:1:570849 Message language identifier. Contains a language code as defined in &lt;&lt;ref-RFC5646,[RFC5646]&gt;&gt;.
- `transactionEvent.resp.updatedPersonalMessage.content` — string, required, maxLength 512 — Message_ Content. Content. Message urn:x-enexis:ecdm:uid:1:570852 Message contents.

### transactionEvent enumerations

- `transactionEvent` `AuthorizationStatusEnumType`: Accepted | Blocked | ConcurrentTx | Expired | Invalid | NoCredit | NotAllowedTypeEVSE | NotAtThisLocation | NotAtThisTime | Unknown — ID_ Token. Status. Authorization_ Status urn:x-oca:ocpp:uid:1:569372 Current status of the ID Token.
- `transactionEvent` `ChargingStateEnumType`: Charging | EVConnected | SuspendedEV | SuspendedEVSE | Idle — Transaction. State. Transaction_ State_ Code urn:x-oca:ocpp:uid:1:569419 Current charging state, is required when state has changed.
- `transactionEvent` `IdTokenEnumType`: Central | eMAID | ISO14443 | ISO15693 | KeyCode | Local | MacAddress | NoAuthorization — Enumeration of possible idToken types.
- `transactionEvent` `LocationEnumType`: Body | Cable | EV | Inlet | Outlet — Sampled_ Value. Location. Location_ Code urn:x-oca:ocpp:uid:1:569265 Indicates where the measured value has been sampled. Default = "Outlet"
- `transactionEvent` `MeasurandEnumType`: Current.Export | Current.Import | Current.Offered | Energy.Active.Export.Register | Energy.Active.Import.Register | Energy.Reactive.Export.Register | Energy.Reactive.Import.Register | Energy.Active.Export.Interval | Energy.Active.Import.Interval | Energy.Active.Net | Energy.Reactive.Export.Interval | Energy.Reactive.Import.Interval | Energy.Reactive.Net | Energy.Apparent.Net | Energy.Apparent.Import | Energy.Apparent.Export | Frequency | Power.Active.Export | Power.Active.Import | Power.Factor | Power.Offered | Power.Reactive.Export | Power.Reactive.Import | SoC | Voltage — Sampled_ Value. Measurand. Measurand_ Code urn:x-oca:ocpp:uid:1:569263 Type of measurement. Default = "Energy.Active.Import.Register"
- `transactionEvent` `MessageFormatEnumType`: ASCII | HTML | URI | UTF8 — Message_ Content. Format. Message_ Format_ Code urn:x-enexis:ecdm:uid:1:570848 Format of the message.
- `transactionEvent` `PhaseEnumType`: L1 | L2 | L3 | N | L1-N | L2-N | L3-N | L1-L2 | L2-L3 | L3-L1 — Sampled_ Value. Phase. Phase_ Code urn:x-oca:ocpp:uid:1:569264 Indicates how the measured value is to be interpreted. For instance between L1 and neutral (L1-N) Please note that not all values of phase are applicable to all Measurands. When phase is absent, the measured value is interpreted as an overall value.
- `transactionEvent` `ReadingContextEnumType`: Interruption.Begin | Interruption.End | Other | Sample.Clock | Sample.Periodic | Transaction.Begin | Transaction.End | Trigger — Sampled_ Value. Context. Reading_ Context_ Code urn:x-oca:ocpp:uid:1:569261 Type of detail value: start, end or sample. Default = "Sample.Periodic"
- `transactionEvent` `ReasonEnumType`: DeAuthorized | EmergencyStop | EnergyLimitReached | EVDisconnected | GroundFault | ImmediateReset | Local | LocalOutOfCredit | MasterPass | Other | OvercurrentFault | PowerLoss | PowerQuality | Reboot | Remote | SOCLimitReached | StoppedByEV | TimeLimitReached | Timeout — Transaction. Stopped_ Reason. EOT_ Reason_ Code urn:x-oca:ocpp:uid:1:569413 This contains the reason why the transaction was stopped. MAY only be omitted when Reason is "Local".
- `transactionEvent` `TransactionEventEnumType`: Ended | Started | Updated — This contains the type of this event. The first TransactionEvent of a transaction SHALL contain: "Started" The last TransactionEvent of a transaction SHALL contain: "Ended" All others SHALL contain: "Updated"
- `transactionEvent` `TriggerReasonEnumType`: Authorized | CablePluggedIn | ChargingRateChanged | ChargingStateChanged | Deauthorized | EnergyLimitReached | EVCommunicationLost | EVConnectTimeout | MeterValueClock | MeterValuePeriodic | TimeLimitReached | Trigger | UnlockCommand | StopAuthorized | EVDeparted | EVDetected | RemoteStop | RemoteStart | AbnormalCondition | SignedDataReceived | ResetCommand — Reason the Charging Station sends this message to the CSMS

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
