# Normative OCPP documents, and which edition this repo actually ships

The reference files in this directory are generated from the JSON schemas vendored under
`ocpp-<version>-json/src/main/resources/`. Those schemas are the *machine-readable* half of the
specification: they pin field names, types, cardinality and value sets, and that is what the
toolkit validates against at runtime. They do **not** carry the prose — state machines, sequence
diagrams, error handling, the meaning of a field. For that you need the specification documents
themselves.

This file records where those documents come from and, more importantly, **which edition the
vendored schemas correspond to**, because they are not the current ones.

## Where to get the documents

The Open Charge Alliance publishes them at <https://www.openchargealliance.org/downloads/>.
Download is free but gated behind accepting OCA's terms, which is why no specification document is
committed to this repository — see [Licensing](#licensing).

As published by OCA:

| Version | Document |
|---|---|
| 1.5 | *OCPP 1.5* (archived) |
| 1.6 | *OCPP 1.6 (all files & errata)* |
| 1.6 security | *OCPP 1.6 Security Whitepaper Edition 4* (Edition 3 archived) |
| 2.0.1 | *OCPP 2.0.1 Edition 4 (all files)*, plus *Edition 4 Errata 2026-06* and *Edition 4 Part 2 appendices v1.5* |

## Which edition the vendored schemas are from

Determined from the schemas themselves — `$id`, `title` and `comment` — not from assumption:

| Module | Files | Marker in the files | Edition it corresponds to |
|---|--:|---|---|
| `ocpp-1-5-json` | 48 | `title` only, draft-04, no `$id` | OCPP 1.5 |
| `ocpp-1-6-json` (core profile) | 56 | `title` only, draft-04, `$id` stripped | OCPP 1.6 |
| `ocpp-1-6-json` (security) | 22 | `$id: urn:OCPP:Cp:1.6:2020:3:*`, draft-06 | **Security Whitepaper Edition 3 (2020)** |
| `ocpp-2-0-json` | 126 | `comment: "OCPP 2.0.1 FINAL"` | **2.0.1 FINAL, i.e. the first release** |

**Both of the marked rows are behind what OCA publishes today** — the whitepaper is at Edition 4
and 2.0.1 is at Edition 4 with errata. Later editions change field constraints and add errata, so
when a peer disagrees with this toolkit about a payload, "our schema is an older edition" is a real
candidate explanation, and the generated reference reflects the vendored edition, not the current
one. Verify against the current document before concluding the peer is wrong.

Nothing in the build checks the vendored schemas against a published edition; refreshing them is a
manual step.

## What the schemas cannot tell you

Reach for the specification document, not the generated reference, when the question is:

- **Ordering and state.** Which action must precede which, what a charge point may send before
  `BootNotification` is accepted, transaction lifecycle.
- **Semantics of a value.** The schema says `status` is one of five strings; the spec says what a
  CSMS is obliged to do for each.
- **Error handling.** Which `CALL_ERROR` code applies to which failure.
- **Profiles.** Which actions belong to Core, Smart Charging, Remote Trigger, etc., and therefore
  what a station advertising a profile must support.
- **Timing and retries.** Heartbeat intervals, retry back-off, message timeouts.

## Licensing

The vendored schemas are made available by the Open Charge Alliance under
[CC BY-ND 4.0](https://creativecommons.org/licenses/by-nd/4.0/) — see the `README.md` alongside
them in `ocpp-1-6-json/src/main/resources/` and `ocpp-2-0-json/src/main/resources/`.

**NoDerivatives is the operative clause here.** It permits redistributing the schemas as they
stand, but not distributing a transformed version of them. The generated reference files in this
directory *are* a transformation — they restructure the schemas and reproduce OCA's own field
descriptions verbatim. That is why [generate.py](generate.py) is committed and its output is not:
each developer generates their own local copy from schemas the repository is already licensed to
carry, and nothing derived is redistributed.

If IZIVIA obtains permission from OCA to publish a derived reference, the `.gitignore` entry for
`docs/protocol/OCPP-*.md` is the only thing that needs removing.
