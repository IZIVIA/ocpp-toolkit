# The OCA specification documents, and how this repo relates to them

Two sources of truth sit behind this toolkit, and they answer different questions:

| Source | Answers | Where |
|---|---|---|
| The official **JSON schemas** | payload shape: field names, types, cardinality, value sets | vendored in `ocpp-<v>-json/src/main/resources/` |
| The official **specification PDFs** | everything else: SHALL/SHOULD requirements, sequences, the meaning of a status, error handling, feature profiles, timing | **not** in this repository — your own licensed copy |

`extract-specs.py` turns the PDFs into page-cited text so the second half is grep-able too, and
`generate.py` then cites the exact section and PDF page on every action. See
[README.md](README.md) for the workflow.

## Getting the documents

OCA publishes them at <https://www.openchargealliance.org/downloads/>. Download is free but gated
behind accepting OCA's terms, which is why nothing from them is committed here — see
[Licensing](#licensing). Point the extractor at wherever you keep them:

```bash
OCA_DOCS=~/Documents/OCA python3 docs/protocol/extract-specs.py
# more than one folder:
OCA_DOCS=~/Documents/OCA:~/other/Normes python3 docs/protocol/extract-specs.py
```

Documents are located by filename pattern anywhere under those roots, so it does not matter how
the downloads were unpacked or renamed. Anything not found is listed at the end of the run.

As currently published by OCA, note that **two of these are newer than what the vendored schemas
correspond to**: *OCPP 1.6 Security Whitepaper Edition 4* and *OCPP 2.0.1 Edition 4 (all files)*
with *Errata 2026-06* and *Part 2 appendices v1.5*.

## Which edition the vendored schemas correspond to

Not inferred — established by comparing the repo's schemas byte-for-byte against OCA's own schema
distributions.

| Module | Files | Verified against | Result |
|---|--:|---|---|
| `ocpp-1-6-json` (security, 22 files) | 22 | Security Whitepaper **edition 2** (FINAL, 2020-03-31) schema set | **identical, `$id` included** |
| `ocpp-2-0-json` | 126 | `OCPP-2.0.1_part3_JSON_schemas.zip` (2.0.1 FINAL, 2020-03-31) | 124 identical once the stripped `$id` is ignored; **2 modified**, see below |
| `ocpp-1-5-json`, `ocpp-1-6-json` (core, 56 files) | 104 | no schema distribution to compare against on hand | unverified |

**`urn:...:1.6:2020:3:` and `urn:...:2:2020:3:` are a year and a month — 2020-03 — not an edition
number.** Both the whitepaper edition 2 and 2.0.1 FINAL were released 2020-03-31, and the
whitepaper edition 2 schemas carry exactly the `2020:3` URNs the repo ships. Reading the `3` as
"Edition 3" is the obvious mistake to make here, and it is wrong.

So: the 1.6 security schemas are **Whitepaper edition 2**, and the 2.0.1 schemas are **2.0.1
FINAL** — the first release, before Editions 2, 3 and 4. Later editions carry errata that change
field constraints, so when a peer disagrees with this toolkit about a payload, "our schema predates
the edition they implement" is a real candidate explanation. Nothing in the build checks this;
refreshing the schemas is a manual step.

## Two verified divergences from the official 2.0.1 schemas

Both are in `ClearVariableMonitoring`, and in both the vendored schema was edited to match this
codebase's Kotlin model rather than the model being written to match OCPP. Because the schema was
changed too, validation passes and no test can catch it — but the field name on the wire is wrong.

| Message | OCPP 2.0.1 field | This toolkit sends/expects |
|---|---|---|
| `ClearVariableMonitoringRequest` | `id` | `ids` |
| `ClearVariableMonitoringResponse` | `clearMonitoringResult` | `clearMonitoringResults` |

`ClearVariableMonitoringReq.ids` and `ClearVariableMonitoringResp.clearMonitoringResults` are plain
Kotlin properties with no `@JsonProperty`, so those are the names Jackson puts on the wire. A
conformant peer will not recognise them.

The *Part 2 Errata v1.0 (2021-10-01)* does not rename these fields — it still writes "for every
**id** in a ClearVariableMonitoringRequest ... add a **clearMonitoringResult** element". Editions 2
to 4 are not on hand, so confirm against the current edition before changing anything; but no
errata available here justifies the plural forms.

## Two schemas OCA ships that this repo does not

`Get15118EVCertificateRequest.json` and `Get15118EVCertificateResponse.json` are present in
`OCPP-2.0.1_part3_JSON_schemas.zip` and absent from `ocpp-2-0-json/src/main/resources/`. That is
what makes `get15118EVCertificate` throw at validation time rather than merely go unchecked — see
[../JSON.guidelines.md](../JSON.guidelines.md). Copying those two files across from OCA's zip is the
whole fix.

## What the schemas cannot tell you

Reach for the specification text — grep `docs/protocol/spec/` after extraction — when the question
is:

- **Ordering and state.** Which action must precede which, what a station may send before
  `BootNotification` is accepted, transaction lifecycle, status-transition tables.
- **Semantics of a value.** The schema says `status` is one of five strings; the spec says what a
  CSMS is obliged to do for each.
- **Error handling.** Which `CALL_ERROR` code applies to which failure.
- **Profiles.** Which actions belong to Core, Smart Charging, Remote Trigger, Reservation — and so
  what a station advertising a profile must support.
- **Timing and retries.** Heartbeat intervals, retry back-off, message timeouts.
- **Corrections.** The errata sheets change normative wording. OCPP 1.6 errata v4.0 §3.19, for
  instance, turns "a Charge Point **MAY** send a StatusNotification.req" into "**SHALL** send".
  A `grep` of the errata text is the only practical way to notice that.

## Licensing

The **schemas** vendored in this repository are made available by OCA under
[CC BY-ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) — see the `README.md` alongside
them in `ocpp-1-6-json/src/main/resources/` and `ocpp-2-0-json/src/main/resources/`.

The **specification PDFs** are OCA copyright and are obtained by each developer under OCA's own
terms. Neither the PDFs nor text extracted from them may be redistributed here.

*NoDerivatives* is the operative clause for the schemas: it permits redistributing them as they
stand, but not distributing a transformed version. The committed reference **is** such a
transformation — it restructures the schemas and reproduces OCA's field descriptions verbatim.
Publishing it here is a deliberate IZIVIA decision, taken so that developers and tooling can grep
the protocol without a generation step.

What is deliberately **not** committed is `spec/<version>/*.txt`: the extracted text of the
specification PDFs. Those files are close to verbatim copies of eighteen complete OCA documents
(~2.7 MB, ~44,000 lines), and this repository is public. Publishing the *citations* to those
documents — which is what `spec/INDEX.md` and `spec/sections.json` are — is a different thing from
republishing the documents. Run `extract-specs.py` to produce the text locally from your own
licensed copy.

Note also that the two `ClearVariableMonitoring` edits above mean the repository is already
distributing modified copies of two CC BY-ND schemas. Fixing the field names resolves both the
interoperability bug and that.
