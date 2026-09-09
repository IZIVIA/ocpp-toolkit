#!/usr/bin/env python3
"""Regenerate the grep-able OCPP protocol reference under docs/protocol/.

Source of truth, in order of authority:
  1. the official OCPP JSON schemas vendored in ocpp-<v>-json/src/main/resources/
  2. each version's Actions enum, for the wire action name and its initiator
  3. the Kotlin model class names, so a grep hit leads straight to the code

Run from the repository root:  python3 docs/protocol/generate.py
"""
import json
import os
import re
import sys

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
OUT = os.path.join(ROOT, "docs", "protocol")

# version key -> (label, core module, json module, request schema suffix, kotlin package)
VERSIONS = [
    ("1.5", "OCPP 1.5", "ocpp-1-5-core", "ocpp-1-5-json", "", "com.izivia.ocpp.core15"),
    ("1.6", "OCPP 1.6", "ocpp-1-6-core", "ocpp-1-6-json", "Request", "com.izivia.ocpp.core16"),
    ("2.0.1", "OCPP 2.0.1", "ocpp-2-0-core", "ocpp-2-0-json", "Request", "com.izivia.ocpp.core20"),
]

ACTION_RE = re.compile(
    r'([A-Z][A-Z_0-9]*)\(\s*"([A-Za-z0-9]+)"\s*,\s*([A-Za-z0-9]+)::class\.java\s*,\s*'
    r'([A-Za-z0-9]+)::class\.java\s*,\s*OcppInitiator\.([A-Z_]+)\s*\)'
)

VALIDATOR = {"1.5": "V4", "1.6": "V4", "2.0.1": "V6"}

DRAFT_OF_FLAG = {
    "V4": "http://json-schema.org/draft-04/schema#",
    "V6": "http://json-schema.org/draft-06/schema#",
}

ARROW = {
    "CHARGING_STATION": "Charging Station -> CSMS",
    "CENTRAL_SYSTEM": "CSMS -> Charging Station",
    "LOCAL_CONTROLLER": "Local Controller",
    "ALL": "either side",
}


def parse_actions(core_module):
    path = None
    for dirpath, _, files in os.walk(os.path.join(ROOT, core_module)):
        if "build" in dirpath.split(os.sep):
            continue
        if "Actions.kt" in files:
            path = os.path.join(dirpath, "Actions.kt")
            break
    if path is None:
        raise SystemExit(f"no Actions.kt under {core_module}")
    text = open(path, encoding="utf-8").read()
    body = text[text.index("enum class Actions"):]
    actions = []
    for m in ACTION_RE.finditer(body):
        actions.append({
            "enum": m.group(1),
            "value": m.group(2),
            "req": m.group(3),
            "resp": m.group(4),
            "initiator": m.group(5),
        })
    return actions, os.path.relpath(path, ROOT)


def constraints(node):
    out = []
    for key, label in (
        ("maxLength", "maxLength"), ("minLength", "minLength"),
        ("maximum", "max"), ("minimum", "min"),
        ("maxItems", "maxItems"), ("minItems", "minItems"),
        ("format", "format"), ("multipleOf", "multipleOf"),
    ):
        if key in node:
            out.append(f"{label} {node[key]}")
    return out


def clean(text):
    return re.sub(r"\s+", " ", str(text)).strip()


def resolve(node, defs, seen):
    """Follow a $ref once, returning (node, type_name)."""
    ref = node.get("$ref")
    if not ref or not ref.startswith("#/definitions/"):
        return node, None
    name = ref.split("/")[-1]
    if name in seen:
        return {"type": "object"}, name
    return defs.get(name, {}), name


def flatten(node, defs, prefix, lines, seen=(), depth=0):
    """Emit one line per leaf field, keyed by its dotted JSON path."""
    if depth > 6:
        return
    required = set(node.get("required", []))
    props = node.get("properties") or {}
    for field, raw in props.items():
        target, type_name = resolve(raw, defs, seen)
        merged = dict(target)
        for k, v in raw.items():
            if k != "$ref":
                merged[k] = v
        path = f"{prefix}.{field}"
        jtype = merged.get("type", "object")
        req = "required" if field in required else "optional"

        bits = [type_name or (jtype if isinstance(jtype, str) else "/".join(jtype))]
        if type_name and isinstance(jtype, str) and jtype not in ("object",):
            bits[0] = f"{type_name} ({jtype})"
        bits.append(req)
        bits += constraints(merged)
        if merged.get("enum"):
            bits.append("enum: " + " | ".join(map(str, merged["enum"])))
        desc = merged.get("description")
        line = f"- `{path}` — " + ", ".join(bits)
        if desc:
            line += f" — {clean(desc)}"
        lines.append(line)

        if jtype == "array":
            items = merged.get("items", {})
            itarget, itype = resolve(items, defs, seen)
            imerged = dict(itarget)
            for k, v in items.items():
                if k != "$ref":
                    imerged[k] = v
            if imerged.get("properties"):
                flatten(imerged, defs, f"{path}[]", lines,
                        seen + ((itype,) if itype else ()), depth + 1)
            else:
                ibits = [itype or imerged.get("type", "object")]
                ibits += constraints(imerged)
                if imerged.get("enum"):
                    ibits.append("enum: " + " | ".join(map(str, imerged["enum"])))
                lines.append(f"- `{path}[]` — " + ", ".join(ibits))
        elif merged.get("properties"):
            flatten(merged, defs, path, lines,
                    seen + ((type_name,) if type_name else ()), depth + 1)


_CLASS_INDEX = {}


def class_path(core_module, class_name):
    """Repo-relative path of the .kt file declaring class_name, so a grep hit leads to code."""
    if core_module not in _CLASS_INDEX:
        idx = {}
        for dirpath, _, files in os.walk(os.path.join(ROOT, core_module)):
            if "build" in dirpath.split(os.sep):
                continue
            for f in files:
                if f.endswith(".kt"):
                    idx.setdefault(f[:-3], os.path.relpath(os.path.join(dirpath, f), ROOT))
        _CLASS_INDEX[core_module] = idx
    return _CLASS_INDEX[core_module].get(class_name)


def schema_path(json_module, name):
    rel = os.path.join(json_module, "src", "main", "resources", name + ".json")
    full = os.path.join(ROOT, rel)
    return (rel, full) if os.path.exists(full) else (rel, None)


def render_version(key, label, core_module, json_module, req_suffix, package):
    actions, actions_rel = parse_actions(core_module)
    camel = lambda v: v[0].upper() + v[1:]
    used = set()
    problems = []

    out = [f"# {label} — protocol reference", ""]
    out.append(f"**Generated — do not edit.** Run `python3 docs/protocol/generate.py` to refresh.")
    out.append("")
    out.append(f"Derived from the official OCPP JSON schemas in `{json_module}/src/main/resources/` "
               f"and the action registry in `{actions_rel}`. "
               f"For the normative prose, see [SPECS.md](SPECS.md).")
    out.append("")
    out.append("Every field is one line, keyed by its dotted JSON path, so a grep for a field name "
               "lands on a line that names its action, direction and constraints:")
    out.append("")
    out.append("```bash")
    out.append(f"grep -n 'idTag' docs/protocol/OCPP-{key}.md")
    out.append("```")
    out.append("")
    out.append(f"{len(actions)} actions registered.")
    out.append("")
    out.append("## Actions at a glance")
    out.append("")
    out.append("| action | direction | request schema | Kotlin |")
    out.append("|---|---|---|---|")
    for a in sorted(actions, key=lambda x: x["value"]):
        rel, full = schema_path(json_module, camel(a["value"]) + req_suffix)
        out.append(f"| `{a['value']}` | {ARROW[a['initiator']]} | "
                   f"{'`' + os.path.basename(rel) + '`' if full else '**missing**'} | `{a['req']}` / `{a['resp']}` |")
    out.append("")

    for a in sorted(actions, key=lambda x: x["value"]):
        c = camel(a["value"])
        out.append(f"## {a['value']}")
        out.append("")
        out.append(f"- direction: **{ARROW[a['initiator']]}** (`OcppInitiator.{a['initiator']}`)")
        out.append(f"- registry entry: `Actions.{a['enum']}`")
        for cls in (a["req"], a["resp"]):
            cp = class_path(core_module, cls)
            out.append(f"- Kotlin `{cls}`: " + (f"[`{cp}`](../../{cp})" if cp else "_class file not found_"))
        out.append("")
        for kind, name in (("req", c + req_suffix), ("resp", c + "Response")):
            rel, full = schema_path(json_module, name)
            heading = "request" if kind == "req" else "response"
            if not full:
                out.append(f"### {a['value']} {heading}")
                out.append("")
                out.append(f"No schema file `{os.path.basename(rel)}` — payloads of this direction "
                           f"are **not schema-validated**.")
                out.append("")
                problems.append(f"{a['value']} {heading}: expected `{rel}`, not found")
                continue
            used.add(os.path.basename(rel))
            schema = json.load(open(full, encoding="utf-8"))
            defs = schema.get("definitions", {})
            out.append(f"### {a['value']} {heading}")
            out.append("")
            meta = [f"schema: [`{os.path.basename(rel)}`](../../{rel})"]
            if schema.get("$id"):
                meta.append(f"`{schema['$id']}`")
            if schema.get("comment"):
                meta.append(clean(schema["comment"]))
            out.append("- " + " · ".join(meta))
            if schema.get("additionalProperties") is False:
                out.append("- `additionalProperties: false` — an unknown field fails validation")
            out.append("")
            lines = []
            flatten(schema, defs, f"{a['value']}.{kind}", lines)
            if lines:
                out += lines
            else:
                out.append("_No fields: empty payload._")
            out.append("")

        # enum definitions used by this action, listed once per action for grep-ability
        enums = []
        for kind, name in (("req", c + req_suffix), ("resp", c + "Response")):
            rel, full = schema_path(json_module, name)
            if not full:
                continue
            schema = json.load(open(full, encoding="utf-8"))
            for dname, d in (schema.get("definitions") or {}).items():
                if d.get("enum"):
                    enums.append((dname, d["enum"], d.get("description")))
        if enums:
            out.append(f"#### {a['value']} enumerations")
            out.append("")
            uniq = {(n, tuple(v), d or "") for n, v, d in enums}
            for dname, values, desc in sorted(uniq):
                line = f"- `{a['value']}` `{dname}`: " + " | ".join(map(str, values))
                if desc:
                    line += f" — {clean(desc)}"
                out.append(line)
            out.append("")

    # schemas present on disk that no action maps to
    all_schemas = {f for f in os.listdir(os.path.join(ROOT, json_module, "src", "main", "resources"))
                   if f.endswith(".json")}
    orphans = sorted(all_schemas - used)
    out.append("## Schema coverage")
    out.append("")
    out.append(f"- {len(used)} of {len(all_schemas)} shipped schema files are reachable from `Actions`.")
    if orphans:
        out.append(f"- **{len(orphans)} unreachable** — present in `src/main/resources/` but no `Actions` "
                   f"entry resolves to them, so they are never used for validation: "
                   + ", ".join(f"`{o}`" for o in orphans))
    if problems:
        out.append(f"- **{len(problems)} actions missing a schema:**")
        for p in problems:
            out.append(f"  - {p}")
    if not orphans and not problems:
        out.append("- Actions and schema files correspond exactly.")
    out.append("")

    drafts = {}
    for f in sorted(all_schemas):
        sc = json.load(open(os.path.join(ROOT, json_module, "src", "main", "resources", f), encoding="utf-8"))
        drafts.setdefault(sc.get("$schema", "(none declared)"), []).append(f)
    out.append("## Declared JSON Schema draft")
    out.append("")
    out.append(f"`{json_module}`'s parser validates every payload with "
               f"`SpecVersion.VersionFlag.{VALIDATOR[key]}`. What the files themselves declare:")
    out.append("")
    for draft, files in sorted(drafts.items(), key=lambda kv: -len(kv[1])):
        out.append(f"- `{draft}` — {len(files)} file(s)")
    mismatched = [d for d in drafts if DRAFT_OF_FLAG.get(VALIDATOR[key]) and d != DRAFT_OF_FLAG[VALIDATOR[key]]]
    if mismatched:
        out.append("")
        out.append(f"**Mismatch:** the validator is fixed at `{VALIDATOR[key]}` "
                   f"(`{DRAFT_OF_FLAG[VALIDATOR[key]]}`), so these files are validated against a "
                   f"different draft than they declare:")
        for d in mismatched:
            out.append(f"")
            out.append(f"- `{d}`: " + ", ".join(f"`{f}`" for f in drafts[d]))
    out.append("")
    return "\n".join(out) + "\n", actions, orphans, problems


def main():
    summaries = []
    per_version_actions = {}
    for key, label, core, jsonm, suffix, package in VERSIONS:
        text, actions, orphans, problems = render_version(key, label, core, jsonm, suffix, package)
        path = os.path.join(OUT, f"OCPP-{key}.md")
        open(path, "w", encoding="utf-8").write(text)
        per_version_actions[key] = {a["value"]: a for a in actions}
        summaries.append((key, len(actions), len(orphans), len(problems)))
        print(f"wrote docs/protocol/OCPP-{key}.md — {len(actions)} actions, "
              f"{len(orphans)} unreachable schema(s), {len(problems)} missing schema(s)")

    # cross-version action matrix
    keys = [v[0] for v in VERSIONS]
    every = sorted({a for k in keys for a in per_version_actions[k]})
    m = ["# Action support matrix", ""]
    m.append("**Generated — do not edit.** Run `python3 docs/protocol/generate.py` to refresh.")
    m.append("")
    m.append("Which OCPP action exists in which version, and who initiates it. Grep an action name "
             "here first to see whether the version you target has it at all.")
    m.append("")
    m.append("| action | " + " | ".join(keys) + " | direction |")
    m.append("|---|" + "|".join([":-:"] * len(keys)) + "|---|")
    for action in every:
        cells = []
        arrow = ""
        for k in keys:
            a = per_version_actions[k].get(action)
            cells.append("yes" if a else "—")
            if a:
                arrow = ARROW[a["initiator"]]
        m.append(f"| `{action}` | " + " | ".join(cells) + f" | {arrow} |")
    m.append("")
    m.append("## Counts")
    m.append("")
    m.append("| version | actions | unreachable schemas | actions with no schema |")
    m.append("|---|--:|--:|--:|")
    for key, n, orph, prob in summaries:
        m.append(f"| {key} | {n} | {orph} | {prob} |")
    m.append("")
    open(os.path.join(OUT, "ACTIONS.md"), "w", encoding="utf-8").write("\n".join(m) + "\n")
    print(f"wrote docs/protocol/ACTIONS.md — {len(every)} distinct actions")


if __name__ == "__main__":
    main()
