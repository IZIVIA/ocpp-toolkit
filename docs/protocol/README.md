# Protocol reference — the grep-able source of truth

The question "what fields does this action actually carry, in this OCPP version?" is answerable
from the official JSON schemas the repo already vendors, but not *quickly*: they are 252 separate
JSON files, one per direction per action, with no indication of who initiates an action and no way
to compare versions.

**The reference is committed — you do not need to generate anything.** Just grep:

```bash
grep -rn 'idTag`' docs/protocol/           # which actions carry idTag, in which version
grep -n 'transactionEvent.req' docs/protocol/OCPP-2.0.1.md
grep -n 'meterValues' docs/protocol/ACTIONS.md    # does 1.5 have it? who initiates it?
```

Regenerating is only needed after changing an `Actions` enum or the schema resources:

```bash
python3 docs/protocol/generate.py                                  # refresh the reference

# optional: also re-extract the OCA PDFs, which refreshes the spec citations and
# produces the full normative text locally for prose greps. Needs your own licensed
# copy of the documents plus ghostscript; the extracted text is not committed.
OCA_DOCS=~/Documents/OCA python3 docs/protocol/extract-specs.py
grep -rn -B2 -A8 'Authorization Cache' docs/protocol/spec/1.6/     # after extraction
```

Each field is one line keyed by its dotted JSON path, carrying everything needed to act on it:

```
- `authorize.resp.idTagInfo.status` — string, required, enum: Accepted | Blocked | Expired | Invalid | ConcurrentTx
```

## Files

| File | Contents |
|---|---|
| `ACTIONS.md` | every action × every version, with direction — start here |
| `OCPP-1.5.md` | 24 actions |
| `OCPP-1.6.md` | 39 actions, including the Security Whitepaper extension |
| `OCPP-2.0.1.md` | 64 actions |
| `spec/INDEX.md` | every document and every heading, mapped to its PDF page — committed |
| `spec/sections.json` | the same, machine-readable; `generate.py` reads it to cite sections — committed |
| `spec/<version>/*.txt` | the extracted specification text itself — **not committed**, see [SPECS.md](SPECS.md#licensing); run `extract-specs.py` to produce it locally |
| [SPECS.md](SPECS.md) | where the documents are, **which edition the vendored schemas match**, the verified divergences, and the licensing |

Extraction covers 18 documents and about 1,090 pages across the three versions — the specifications,
the OCPP-J and OCPP-S bindings, the 1.6 Security Whitepaper, and every errata sheet and changelog.

Generated files are not committed — see [Licensing](SPECS.md#licensing). Generate them once and
they stay until the schemas change. They are deliberately long and repetitive: they are a grep
target, not prose, so the module-doc length conventions in `docs/` do not apply to them.

## What it is generated from

In order of authority:

1. The official OCPP JSON schemas in `ocpp-<version>-json/src/main/resources/` — field names,
   types, `required`, `maxLength`/`format`/range constraints, enum value sets, and (for 2.0.1)
   OCA's own field descriptions.
2. Each version's `Actions` enum — the wire action name and its `OcppInitiator`, i.e. the
   direction. An action absent from that enum is unreachable over the wire regardless of its model,
   so the reference lists exactly what the registry admits.
3. The Kotlin model classes — every action links to the `.kt` file for its `Req` and `Resp`, so a
   grep hit leads straight to the code that implements it.

That last point is the reason to prefer this over reading the schemas directly: it ties the wire
contract, the dispatch registry and the implementation together in one line-addressable place.

## It also reports what does not line up

Each version file ends with a coverage section that compares the registry against the shipped
schema files. Three checks, all of which have found something real in this repo:

- **Actions with no schema file.** These do not degrade to "unvalidated" — they *throw*.
  `OcppJsonValidator` hands the missing resource to `JsonSchemaFactory.getSchema(null)`, which
  raises `IllegalArgumentException: argument "in" is null` (verified against
  json-schema-validator 1.5.9). `get15118EVCertificate` in 2.0.1 is registered in `Actions` with
  model classes but ships no schema, so it fails on the first payload unless the parser was built
  with `enableValidation = false`.
- **Schema files no action resolves to.** Dead weight in the jar, and usually a naming mismatch —
  the failure mode `docs/JSON.guidelines.md` warns about.
- **Declared draft vs. validated draft.** All 22 OCPP 1.6 Security Whitepaper schemas declare
  draft-06, but `Ocpp16JsonParser` validates every 1.6 payload as `V4`.

Re-run the generator after touching an `Actions` enum or the schema resources and those sections
will tell you whether the two still agree.

## Requirements

Nothing, to read it. To regenerate: `generate.py` needs only Python 3; `extract-specs.py` also needs
**ghostscript** (`brew install ghostscript`) and your own copy of the OCA PDFs. Neither writes
anything outside `docs/protocol/`.

## What it deliberately does not cover

SOAP wire format: no WSDL or XSD is vendored, so the OCPP-S contract's source of truth is the code
plus the OCPP-S specification text (which *is* extracted — `spec/1.6/ocpp-s-1.6-specification.txt`).
Its conventions are documented in [../SOAP.guidelines.md](../SOAP.guidelines.md).
