# Protocol reference — the grep-able source of truth

What fields does this action carry, in this OCPP version, and where does the specification say so?
This directory answers that with a grep, for all three supported versions and all 127 registered
actions.

**One file per action**, so a lookup costs a few kilobytes rather than the whole protocol:

```bash
grep -rn 'idTag`' docs/protocol/                  # which actions carry idTag, in which version
cat docs/protocol/2.0.1/transactionEvent.md       # one action, in full
grep -n 'meterValues' docs/protocol/ACTIONS.md    # does 1.5 have it? who initiates it?
grep -rn 'Authorization Cache' docs/protocol/spec/   # where is it specified?
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

| Path | Contents |
|---|---|
| [`ACTIONS.md`](ACTIONS.md) | every action × every version, with direction — **start here**, ~1k tokens |
| [`1.5/`](1.5/README.md) | 24 actions, one file each |
| [`1.6/`](1.6/README.md) | 39 actions, including the Security Whitepaper extension |
| [`2.0.1/`](2.0.1/README.md) | 64 actions |
| [`spec/`](spec/README.md) | tables of contents of all 18 OCA documents — which one specifies a topic, and on which page |
| [`SPECS.md`](SPECS.md) | which edition the vendored schemas match, the verified divergences from OCA's schemas, and the licensing |

Each version directory has a `README.md` indexing its actions, plus the schema-coverage and
JSON-Schema-draft notes for that version.

### Why one file per action

The reference used to be three files; `OCPP-2.0.1.md` alone was 445 KB, about 113k tokens, which
made reading it to answer one question wasteful for a person and prohibitive for an agent. Split,
the median action file is under 5 KB and the largest — `2.0.1/transactionEvent.md` — is 26 KB. Grep
still spans everything, and the dotted field paths keep each matched line self-identifying.

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
| add an action to an `Actions` enum | a new `<version>/<action>.md`, a row in `<version>/README.md` **and** in `ACTIONS.md`, and both schema files |
| add or change a field in a schema | the field lines in `<version>/<action>.md` |
| add a new OCPP version | a new `<version>/` directory, a column in `ACTIONS.md`, and its documents under `spec/` |
| refresh the vendored schemas to a newer OCA edition | the affected action files, and the edition table in [SPECS.md](SPECS.md) |

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
