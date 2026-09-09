# Action support matrix

**Derived from each version's `Actions` registry.** Maintained by hand.

Which OCPP action exists in which version, and who initiates it. Grep an action name here first to see whether the version you target has it at all.

| action | 1.5 | 1.6 | 2.0.1 | direction |
|---|:-:|:-:|:-:|---|
| `authorize` | yes | yes | yes | Charging Station -> CSMS |
| `bootNotification` | yes | yes | yes | Charging Station -> CSMS |
| `cancelReservation` | yes | yes | yes | CSMS -> Charging Station |
| `certificateSigned` | — | yes | yes | CSMS -> Charging Station |
| `changeAvailability` | yes | yes | yes | CSMS -> Charging Station |
| `changeConfiguration` | yes | yes | — | CSMS -> Charging Station |
| `clearCache` | yes | yes | yes | CSMS -> Charging Station |
| `clearChargingProfile` | — | yes | yes | CSMS -> Charging Station |
| `clearDisplayMessage` | — | — | yes | CSMS -> Charging Station |
| `clearVariableMonitoring` | — | — | yes | CSMS -> Charging Station |
| `clearedChargingLimit` | — | — | yes | Charging Station -> CSMS |
| `costUpdated` | — | — | yes | CSMS -> Charging Station |
| `customerInformation` | — | — | yes | CSMS -> Charging Station |
| `dataTransfer` | yes | yes | yes | either side |
| `deleteCertificate` | — | yes | yes | CSMS -> Charging Station |
| `diagnosticsStatusNotification` | yes | yes | — | Charging Station -> CSMS |
| `extendedTriggerMessage` | — | yes | — | CSMS -> Charging Station |
| `firmwareStatusNotification` | yes | yes | yes | Charging Station -> CSMS |
| `get15118EVCertificate` | — | — | yes | Charging Station -> CSMS |
| `getBaseReport` | — | — | yes | CSMS -> Charging Station |
| `getCertificateStatus` | — | — | yes | Charging Station -> CSMS |
| `getChargingProfiles` | — | — | yes | CSMS -> Charging Station |
| `getCompositeSchedule` | — | yes | yes | CSMS -> Charging Station |
| `getConfiguration` | yes | yes | — | CSMS -> Charging Station |
| `getDiagnostics` | yes | yes | — | CSMS -> Charging Station |
| `getDisplayMessages` | — | — | yes | CSMS -> Charging Station |
| `getInstalledCertificateIds` | — | yes | yes | CSMS -> Charging Station |
| `getLocalListVersion` | yes | yes | yes | CSMS -> Charging Station |
| `getLog` | — | yes | yes | CSMS -> Charging Station |
| `getMonitoringReport` | — | — | yes | CSMS -> Charging Station |
| `getReport` | — | — | yes | CSMS -> Charging Station |
| `getTransactionStatus` | — | — | yes | CSMS -> Charging Station |
| `getVariables` | — | — | yes | CSMS -> Charging Station |
| `heartbeat` | yes | yes | yes | Charging Station -> CSMS |
| `installCertificate` | — | yes | yes | CSMS -> Charging Station |
| `logStatusNotification` | — | yes | yes | Charging Station -> CSMS |
| `meterValues` | yes | yes | yes | Charging Station -> CSMS |
| `notifyChargingLimit` | — | — | yes | Charging Station -> CSMS |
| `notifyCustomerInformation` | — | — | yes | Charging Station -> CSMS |
| `notifyDisplayMessages` | — | — | yes | Charging Station -> CSMS |
| `notifyEVChargingNeeds` | — | — | yes | Charging Station -> CSMS |
| `notifyEVChargingSchedule` | — | — | yes | Charging Station -> CSMS |
| `notifyEvent` | — | — | yes | Charging Station -> CSMS |
| `notifyMonitoringReport` | — | — | yes | Charging Station -> CSMS |
| `notifyReport` | — | — | yes | Charging Station -> CSMS |
| `publishFirmware` | — | — | yes | CSMS -> Charging Station |
| `publishFirmwareStatusNotification` | — | — | yes | Charging Station -> CSMS |
| `remoteStartTransaction` | yes | yes | — | CSMS -> Charging Station |
| `remoteStopTransaction` | yes | yes | — | CSMS -> Charging Station |
| `reportChargingProfiles` | — | — | yes | CSMS -> Charging Station |
| `requestStartTransaction` | — | — | yes | CSMS -> Charging Station |
| `requestStopTransaction` | — | — | yes | CSMS -> Charging Station |
| `reservationStatusUpdate` | — | — | yes | Charging Station -> CSMS |
| `reserveNow` | yes | yes | yes | CSMS -> Charging Station |
| `reset` | yes | yes | yes | CSMS -> Charging Station |
| `securityEventNotification` | — | yes | yes | Charging Station -> CSMS |
| `sendLocalList` | yes | yes | yes | CSMS -> Charging Station |
| `setChargingProfile` | — | yes | yes | CSMS -> Charging Station |
| `setDisplayMessage` | — | — | yes | CSMS -> Charging Station |
| `setMonitoringBase` | — | — | yes | CSMS -> Charging Station |
| `setMonitoringLevel` | — | — | yes | CSMS -> Charging Station |
| `setNetworkProfile` | — | — | yes | CSMS -> Charging Station |
| `setVariableMonitoring` | — | — | yes | CSMS -> Charging Station |
| `setVariables` | — | — | yes | CSMS -> Charging Station |
| `signCertificate` | — | yes | yes | Charging Station -> CSMS |
| `signedFirmwareStatusNotification` | — | yes | — | Charging Station -> CSMS |
| `signedUpdateFirmware` | — | yes | — | CSMS -> Charging Station |
| `startTransaction` | yes | yes | — | Charging Station -> CSMS |
| `statusNotification` | yes | yes | yes | CSMS -> Charging Station |
| `stopTransaction` | yes | yes | — | Charging Station -> CSMS |
| `transactionEvent` | — | — | yes | Charging Station -> CSMS |
| `triggerMessage` | — | yes | yes | CSMS -> Charging Station |
| `unlockConnector` | yes | yes | yes | CSMS -> Charging Station |
| `unpublishFirmware` | — | — | yes | CSMS -> Charging Station |
| `updateFirmware` | yes | yes | yes | CSMS -> Charging Station |

## Counts

| version | actions | unreachable schemas | actions with no schema |
|---|--:|--:|--:|
| 1.5 | 24 | 0 | 0 |
| 1.6 | 39 | 0 | 0 |
| 2.0.1 | 64 | 0 | 2 |

