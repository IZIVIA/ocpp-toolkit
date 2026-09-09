# notifyEvent — OCPP 2.0.1

- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.NOTIFYEVENT`
- Kotlin `NotifyEventReq`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventReq.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventReq.kt)
- Kotlin `NotifyEventResp`: [`ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventResp.kt`](../../../ocpp-2-0-core/src/main/kotlin/com/izivia/ocpp/core20/model/notifyevent/NotifyEventResp.kt)
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.38 NotifyEvent — pdf-page 365
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.38.1 NotifyEventRequest — pdf-page 365
- spec: [`ocpp-2.0.1-part2-specification`](../spec/2.0.1/ocpp-2.0.1-part2-specification.md) §1.38.2 NotifyEventResponse — pdf-page 365
- errata mentions: [`ocpp-2.0.1-part2-errata`](../spec/2.0.1/ocpp-2.0.1-part2-errata.md) pdf-page 2, 19

## notifyEvent request

- schema: [`NotifyEventRequest.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEventRequest.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEvent.req.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.generatedAt` — string, required, format date-time — Timestamp of the moment this message was generated at the Charging Station.
- `notifyEvent.req.tbc` — boolean, optional — “to be continued” indicator. Indicates whether another part of the report follows in an upcoming notifyEventRequest message. Default value when omitted is false.
- `notifyEvent.req.seqNo` — integer, required — Sequence number of this message. First message starts at 0.
- `notifyEvent.req.eventData` — array, required, minItems 1
- `notifyEvent.req.eventData[].customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].eventId` — integer, required — Identifies the event. This field can be referred to as a cause by other events.
- `notifyEvent.req.eventData[].timestamp` — string, required, format date-time — Timestamp of the moment the report was generated.
- `notifyEvent.req.eventData[].trigger` — EventTriggerEnumType (string), required, enum: Alerting | Delta | Periodic — Type of monitor that triggered this event, e.g. exceeding a threshold value.
- `notifyEvent.req.eventData[].cause` — integer, optional — Refers to the Id of an event that is considered to be the cause for this event.
- `notifyEvent.req.eventData[].actualValue` — string, required, maxLength 2500 — Actual value (_attributeType_ Actual) of the variable. The Configuration Variable &lt;&lt;configkey-reporting-value-size,ReportingValueSize&gt;&gt; can be used to limit GetVariableResult.attributeValue, VariableAttribute.value and EventData.actualValue. The max size of these values will always remain equal.
- `notifyEvent.req.eventData[].techCode` — string, optional, maxLength 50 — Technical (error) code as reported by component.
- `notifyEvent.req.eventData[].techInfo` — string, optional, maxLength 500 — Technical detail information as reported by component.
- `notifyEvent.req.eventData[].cleared` — boolean, optional — _Cleared_ is set to true to report the clearing of a monitored situation, i.e. a 'return to normal'.
- `notifyEvent.req.eventData[].transactionId` — string, optional, maxLength 36 — If an event notification is linked to a specific transaction, this field can be used to specify its transactionId.
- `notifyEvent.req.eventData[].component` — ComponentType, required — A physical or logical component
- `notifyEvent.req.eventData[].component.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].component.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].component.evse` — EVSEType, optional — EVSE urn:x-oca:ocpp:uid:2:233123 Electric Vehicle Supply Equipment
- `notifyEvent.req.eventData[].component.evse.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].component.evse.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].component.evse.id` — integer, required — Identified_ Object. MRID. Numeric_ Identifier urn:x-enexis:ecdm:uid:1:569198 EVSE Identifier. This contains a number (&gt; 0) designating an EVSE of the Charging Station.
- `notifyEvent.req.eventData[].component.evse.connectorId` — integer, optional — An id to designate a specific connector (on an EVSE) by connector index number.
- `notifyEvent.req.eventData[].component.name` — string, required, maxLength 50 — Name of the component. Name should be taken from the list of standardized component names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].component.instance` — string, optional, maxLength 50 — Name of instance in case the component exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].variableMonitoringId` — integer, optional — Identifies the VariableMonitoring which triggered the event.
- `notifyEvent.req.eventData[].eventNotificationType` — EventNotificationEnumType (string), required, enum: HardWiredNotification | HardWiredMonitor | PreconfiguredMonitor | CustomMonitor — Specifies the event notification type of the message.
- `notifyEvent.req.eventData[].variable` — VariableType, required — Reference key to a component-variable.
- `notifyEvent.req.eventData[].variable.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.req.eventData[].variable.customData.vendorId` — string, required, maxLength 255
- `notifyEvent.req.eventData[].variable.name` — string, required, maxLength 50 — Name of the variable. Name should be taken from the list of standardized variable names whenever possible. Case Insensitive. strongly advised to use Camel Case.
- `notifyEvent.req.eventData[].variable.instance` — string, optional, maxLength 50 — Name of instance in case the variable exists as multiple instances. Case Insensitive. strongly advised to use Camel Case.

## notifyEvent response

- schema: [`NotifyEventResponse.json`](../../../ocpp-2-0-json/src/main/resources/NotifyEventResponse.json) · OCPP 2.0.1 FINAL
- `additionalProperties: false` — an unknown field fails validation

- `notifyEvent.resp.customData` — CustomDataType, optional — This class does not get 'AdditionalProperties = false' in the schema generation, so it can be extended with arbitrary JSON properties to allow adding custom data.
- `notifyEvent.resp.customData.vendorId` — string, required, maxLength 255

### notifyEvent enumerations

- `notifyEvent` `EventNotificationEnumType`: HardWiredNotification | HardWiredMonitor | PreconfiguredMonitor | CustomMonitor — Specifies the event notification type of the message.
- `notifyEvent` `EventTriggerEnumType`: Alerting | Delta | Periodic — Type of monitor that triggered this event, e.g. exceeding a threshold value.

---

[all OCPP 2.0.1 actions](README.md) · [cross-version action matrix](../ACTIONS.md) · [editions, divergences, licensing](../SPECS.md)
