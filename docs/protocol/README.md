# Protocol reference — the grep-able source of truth

What fields does this action carry, in this OCPP version, and where does the specification say so?
This directory answers that with a grep, for all three supported versions and all 127 registered
actions.

```bash
grep -rn 'idTag`' docs/protocol/                  # which actions carry idTag, in which version
grep -n 'transactionEvent.req' docs/protocol/OCPP-2.0.1.md
grep -n 'meterValues' docs/protocol/ACTIONS.md    # does 1.5 have it? who initiates it?
grep -n 'Authorization Cache' docs/protocol/spec/INDEX.md   # where is it specified?
```

Each field is one line, keyed by its dotted JSON path, carrying everything needed to act on it:

```
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
```

and each action names its direction, its `Actions` entry, the `.kt` files implementing it, and the
section and page of the normative OCA document:

```
## authorize
- direction: **Charging Station -> CSMS** (`OcppInitiator.CHARGING_STATION`)
- registry entry: `Actions.AUTHORIZE`
- Kotlin `AuthorizeReq`: ocpp-1-6-core/.../authorize/AuthorizeReq.kt
- spec: `ocpp-1.6-edition-2` §4.1 Authorize — pdf-page 37
```

## Files

| File | Contents |
|---|---|
| `ACTIONS.md` | every action × every version, with direction — start here |
| `OCPP-1.5.md` | 24 actions |
| `OCPP-1.6.md` | 39 actions, including the Security Whitepaper extension |
| `OCPP-2.0.1.md` | 64 actions |
| `spec/INDEX.md` | table of contents of all 18 OCA documents, every heading mapped to its PDF page |
| `spec/sections.json` | the same, machine-readable |
| [SPECS.md](SPECS.md) | which edition the vendored schemas match, the verified divergences from OCA's schemas, and the licensing |

## What it is derived from

In order of authority:

1. **The official OCPP JSON schemas** in `ocpp-<version>-json/src/main/resources/` — field names,
   types, `required`, `maxLength`/`format`/range constraints, enum value sets, and (for 2.0.1)
   OCA's own field descriptions.
2. **Each version's `Actions` enum** — the wire action name and its `OcppInitiator`, i.e. the
   direction. An action absent from that registry is unreachable over the wire regardless of its
   model, so this reference lists exactly what the registry admits.
3. **The Kotlin model classes** — so a grep hit leads straight to the code that implements it.
4. **The OCA specification PDFs** — the section and page for each action, plus any errata or
   changelog page that names it. The documents are OCA copyright and are not in this repository;
   OCA distributes them at <https://www.openchargealliance.org/downloads/>. Page numbers are PDF
   page positions, not the printed numbers in the footer.

The errata cross-reference is the part that repays attention: OCPP 1.6 errata v4.0 §3.19 turns
*"a Charge Point **MAY** send a StatusNotification.req"* into *"**SHALL** send"*. No schema will
ever tell you that.

## Mismatches it records

Three checks were run across the registry, the shipped schemas and OCA's own schema distributions.
All three found something, and all three are still open — see
[SPECS.md](SPECS.md) and [../JSON.guidelines.md](../JSON.guidelines.md):

- **`get15118EVCertificate` (2.0.1) has no schema file**, so it throws at validation time rather
  than going unchecked. The two files exist in OCA's `part3` zip.
- **Two 2.0.1 schemas were edited to match this codebase's Kotlin models**, changing wire field
  names: `ClearVariableMonitoringRequest.id` → `ids` and
  `ClearVariableMonitoringResponse.clearMonitoringResult` → `clearMonitoringResults`.
- **All 22 OCPP 1.6 Security Whitepaper schemas declare draft-06** while `Ocpp16JsonParser`
  validates every 1.6 payload as `V4`.

## Keeping it current

These files are maintained by hand. They are long and repetitive because they are a grep target,
not prose, so the length conventions that apply to the rest of `docs/` do not apply here.

When you change the protocol surface, update this directory in the same commit:

| Change | Update |
|---|---|
| add an action to an `Actions` enum | its version file **and** `ACTIONS.md`, and add both schema files |
| add or change a field in a schema | the field lines for that action |
| add a new OCPP version | a new `OCPP-<v>.md`, a column in `ACTIONS.md`, and its documents in `spec/INDEX.md` |
| refresh the vendored schemas to a newer OCA edition | the affected field lines, and the edition table in [SPECS.md](SPECS.md) |

The scripts that first produced these files are in this branch's history if a bulk regeneration is
ever needed again: `git log --diff-filter=D -- docs/protocol/generate.py`.

## What it deliberately does not cover

**Normative prose.** Ordering, state machines, what a status value obliges a CSMS to do, error-code
selection, feature profiles, retry timing — those live in the specification documents. This
reference cites the section and page; it does not reproduce the text. See
[SPECS.md](SPECS.md#what-the-schemas-cannot-tell-you).

**SOAP wire format.** No WSDL or XSD is vendored, so the OCPP-S contract's source of truth is the
code plus the OCPP-S specification document. Its conventions are documented in
[../SOAP.guidelines.md](../SOAP.guidelines.md).
