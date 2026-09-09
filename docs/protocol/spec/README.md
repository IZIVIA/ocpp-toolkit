# Specification tables of contents

Which OCA document specifies a topic, and on which page. One file per document, listing every
heading mapped to its PDF page.

The documents themselves are OCA copyright and are not in this repository; OCA distributes them at
<https://www.openchargealliance.org/downloads/>. These files carry only their tables of contents,
so you can cite and locate a section without opening each document to hunt for it.

| Version | Documents |
|---|---|
| [OCPP 1.5](1.5.md) | Specification; *a functional description* |
| [OCPP 1.6](1.6.md) | edition 2; Security Whitepaper edition 2; OCPP-J and OCPP-S bindings; three errata sheets |
| [OCPP 2.0.1](2.0.1.md) | Parts 0/1/2/4, Part 2 appendices, Part 2 errata; the 2.0 → 2.0.1 changelog and superseded 2.0 errata |

**Grep these files, do not read them whole** — the OCPP 2.0.1 Part 2 table of contents alone runs
to 1,124 headings.

```bash
grep -rn 'Authorization Cache' docs/protocol/spec/
grep -rn 'Smart Charging' docs/protocol/spec/1.6/
```

Page numbers are PDF page positions, not the printed numbers in the document footer. Which edition
each corresponds to is recorded in [../SPECS.md](../SPECS.md).

Every action in the [protocol reference](../README.md) already cites its own section and page, so
come here for topics that are not a single action — state machines, feature profiles, offline
behaviour, the transaction lifecycle.

---

[protocol reference](../README.md) · [cross-version action matrix](../ACTIONS.md)
