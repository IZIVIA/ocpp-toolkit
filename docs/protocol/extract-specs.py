#!/usr/bin/env python3
"""Turn the official OCA specification PDFs into grep-able, page-cited text.

The JSON schemas give the payload shape; only the specification documents give the
normative prose — SHALL/SHOULD requirements, sequences, the meaning of a status value,
error handling, feature profiles. This extracts that text so it can be grepped, with
every line traceable to a document and a PDF page.

The PDFs are OCA copyright and are not in this repository. Point this at your own
licensed copy:

    OCA_DOCS=~/Documents/OCA python3 docs/protocol/extract-specs.py

Output goes to docs/protocol/spec/ and is gitignored — see SPECS.md#licensing.
Requires ghostscript (`gs`), which is used for text extraction.
"""
import json
import os
import fnmatch
import re
import shutil
import subprocess
import sys
import tempfile

ROOT = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
OUT = os.path.join(ROOT, "docs", "protocol", "spec")
ROOTS = [os.path.expanduser(p) for p in
         os.environ.get("OCA_DOCS", "~/Documents/OCA").split(os.pathsep) if p.strip()]

# version -> [(filename patterns, slug, role, label)]
# Patterns are matched case-insensitively against file names found anywhere under a
# root in OCA_DOCS, so it does not matter how the download was unpacked or renamed.
# role: spec = normative protocol text, transport = wire binding, errata, changelog
MANIFEST = {
    "1.5": [
        (["ocpp*specification*1.5*.pdf", "OCPP 1.5 Specification.pdf"],
         "ocpp-1.5-specification", "spec", "OCPP 1.5 Specification"),
        (["*1*5*functional description*.pdf", "OCPP 1.5 Technical Description.pdf"],
         "ocpp-1.5-functional-description", "spec", "OCPP 1.5 — a functional description"),
    ],
    "1.6": [
        (["ocpp-1.6 edition 2.pdf", "OCPP 1.6 Specification - Edition 2.pdf", "ocpp*1.6*edition*2*.pdf"],
         "ocpp-1.6-edition-2", "spec", "OCPP 1.6 edition 2 (FINAL, 2017-09-28)"),
        (["*1.6*security*whitepaper*.pdf"],
         "ocpp-1.6-security-whitepaper", "spec", "OCPP 1.6 Security Whitepaper"),
        (["ocpp-j-1.6-specification.pdf"],
         "ocpp-j-1.6-specification", "transport", "OCPP-J 1.6 (JSON over WebSocket)"),
        (["ocpp-s-1.6-specification.pdf"],
         "ocpp-s-1.6-specification", "transport", "OCPP-S 1.6 (SOAP)"),
        (["ocpp-1.6-errata-sheet*.pdf"],
         "ocpp-1.6-errata", "errata", "OCPP 1.6 errata sheet"),
        (["ocpp-j-1.6-errata-sheet*.pdf"],
         "ocpp-j-1.6-errata", "errata", "OCPP-J 1.6 errata sheet"),
        (["ocpp-s-1.6-errata-sheet*.pdf"],
         "ocpp-s-1.6-errata", "errata", "OCPP-S 1.6 errata sheet"),
    ],
    "2.0.1": [
        (["OCPP-2.0.1_part0_introduction.pdf"],
         "ocpp-2.0.1-part0-introduction", "spec", "OCPP 2.0.1 Part 0 — Introduction"),
        (["OCPP-2.0.1_part1_architecture_topology.pdf"],
         "ocpp-2.0.1-part1-architecture", "spec", "OCPP 2.0.1 Part 1 — Architecture & Topology"),
        (["OCPP-2.0.1_part2_specification.pdf"],
         "ocpp-2.0.1-part2-specification", "spec",
         "OCPP 2.0.1 Part 2 — Specification (use cases & requirements)"),
        (["OCPP-2.0.1_part2_appendices*.pdf"],
         "ocpp-2.0.1-part2-appendices", "spec", "OCPP 2.0.1 Part 2 — Appendices"),
        (["OCPP-2.0.1_part4_ocpp-j-specification.pdf"],
         "ocpp-2.0.1-part4-ocpp-j", "transport", "OCPP 2.0.1 Part 4 — OCPP-J"),
        (["OCPP-2.0.1_part2_errata*.pdf"],
         "ocpp-2.0.1-part2-errata", "errata", "OCPP 2.0.1 Part 2 errata"),
        (["Changelog OCPP 2.0*2.0.1.pdf"],
         "changelog-2.0-to-2.0.1", "changelog", "Changelog OCPP 2.0 -> 2.0.1"),
        (["OCPP-2.0_part1_errata.pdf"],
         "ocpp-2.0-part1-errata", "errata", "OCPP 2.0 Part 1 errata (superseded by 2.0.1)"),
        (["OCPP-2.0_part2_errata.pdf"],
         "ocpp-2.0-part2-errata", "errata", "OCPP 2.0 Part 2 errata (superseded by 2.0.1)"),
        (["OCPP-2.0_part4_errata.pdf"],
         "ocpp-2.0-part4-errata", "errata", "OCPP 2.0 Part 4 errata (superseded by 2.0.1)"),
    ],
}

DOT_LEADER = re.compile(r"(?:\s*\.){4,}")
# "4.1. Authorize" (1.6, 2.0.1) and "5.4    Clear Cache" / "6.1    Authorize.req" (1.5)
NUMBERED = re.compile(r"^(\d+(?:\.\d+){0,3})\.?\s+(\S.*?)\s*$")
USECASE = re.compile(r"^([A-Z]\d{2})\s*[-–]\s*(\S.*?)\s*$")
ENDS_IN_PAGENO = re.compile(r"\.\.\.\s+\d+\s*$|\s\d{1,3}\s*$")


def norm(s):
    """Fold a title or action name so 'Boot Notification' == 'bootNotification'."""
    return re.sub(r"[^a-z0-9]", "", s.lower())


_FILES = None


def all_files():
    """Every PDF under every OCA_DOCS root, as (lowercased name, full path)."""
    global _FILES
    if _FILES is None:
        _FILES = []
        for root in ROOTS:
            for dirpath, _, names in os.walk(root):
                for n in names:
                    if n.lower().endswith(".pdf"):
                        _FILES.append((n.lower(), os.path.join(dirpath, n)))
    return _FILES


def resolve(patterns):
    """First PDF whose name matches any pattern, preferring earlier roots and patterns."""
    for pat in patterns:
        for name, path in all_files():
            if fnmatch.fnmatch(name, pat.lower()):
                return path
    return None


def extract(pdf, slug):
    """gs -> one text file per page. Returns a list of page strings, 1-indexed by position."""
    tmp = tempfile.mkdtemp(prefix=f"ocpp-{slug}-")
    try:
        r = subprocess.run(
            ["gs", "-q", "-dBATCH", "-dNOPAUSE", "-dSAFER", "-sDEVICE=txtwrite",
             "-sOutputFile=" + os.path.join(tmp, "p%05d.txt"), pdf],
            capture_output=True, text=True)
        if r.returncode != 0:
            raise RuntimeError(f"ghostscript failed on {pdf}:\n{r.stderr[:400]}")
        pages = []
        for f in sorted(os.listdir(tmp)):
            pages.append(open(os.path.join(tmp, f), encoding="utf-8", errors="replace").read())
        return pages
    finally:
        shutil.rmtree(tmp, ignore_errors=True)


def clean_page(text):
    out = []
    for line in text.splitlines():
        line = DOT_LEADER.sub(" ... ", line).rstrip()
        line = re.sub(r"^\s+", "", line)
        if line:
            out.append(line)
    return out


def headings(lines, page):
    """Body headings on this page. TOC lines (which end in a page number) are skipped."""
    found = []
    for line in lines:
        if ENDS_IN_PAGENO.search(line):
            continue
        for rx, kind in ((NUMBERED, "section"), (USECASE, "usecase")):
            m = rx.match(line)
            if m:
                num, title = m.group(1), m.group(2)
                # a heading, not a sentence that happens to start with a number
                if (len(title) > 80 or len(title.split()) > 10
                        or title.endswith((".", ",", ";", ":"))
                        or not title[0].isupper()):
                    break
                found.append({"id": num, "title": title, "page": page, "kind": kind})
                break
    return found


def main():
    if not shutil.which("gs"):
        sys.exit("ghostscript (gs) not found — install it, e.g. `brew install ghostscript`")
    live = [r for r in ROOTS if os.path.isdir(r)]
    if not live:
        sys.exit(f"No OCA document root found (tried: {', '.join(ROOTS)})\n"
                 f"Set OCA_DOCS to the folder(s) holding your licensed copy of the OCPP PDFs, "
                 f"colon-separated for more than one.")
    print(f"searching: {', '.join(live)}")

    os.makedirs(OUT, exist_ok=True)
    index = {}
    missing = []

    for version, docs in MANIFEST.items():
        vdir = os.path.join(OUT, version)
        os.makedirs(vdir, exist_ok=True)
        index[version] = []
        for patterns, slug, role, label in docs:
            pdf = resolve(patterns)
            if not pdf:
                missing.append(f"{version}: {label} (looked for {patterns[0]})")
                continue
            rel = os.path.relpath(pdf, os.path.dirname(ROOTS[0].rstrip("/")))
            pages = extract(pdf, slug)
            secs = []
            body = [f"# {label}",
                    f"# source: {rel}",
                    f"# {len(pages)} pages — line markers below are PDF page numbers",
                    ""]
            seen_head = set()
            for n, raw in enumerate(pages, 1):
                lines = clean_page(raw)
                for h in headings(lines, n):
                    key = (h["id"], norm(h["title"]))
                    if key in seen_head:
                        continue
                    seen_head.add(key)
                    secs.append(h)
                body.append(f"[[{slug} pdf-page {n}]]")
                body += lines
                body.append("")
            path = os.path.join(vdir, slug + ".txt")
            open(path, "w", encoding="utf-8").write("\n".join(body) + "\n")
            index[version].append({
                "slug": slug, "label": label, "role": role, "source": rel,
                "pages": len(pages), "text": os.path.relpath(path, ROOT),
                "sections": secs,
            })
            print(f"  {version}  {slug}: {len(pages)} pages, {len(secs)} headings")

    with open(os.path.join(OUT, "sections.json"), "w", encoding="utf-8") as f:
        json.dump(index, f, indent=1)

    # human index
    md = ["# Specification index", "",
          "**Generated — do not edit.** Run `python3 docs/protocol/extract-specs.py` to refresh.", "",
          "Extracted text of the OCA specification PDFs, with every heading mapped to its PDF page. "
          "Grep the `.txt` files for normative wording; the page markers (`[[<slug> pdf-page N]]`) "
          "tell you where to open the real document.", "",
          "```bash",
          "grep -n 'SHALL' docs/protocol/spec/1.6/ocpp-1.6-edition-2.txt | head",
          "grep -rn -B2 -A8 'Authorization Cache' docs/protocol/spec/1.6/",
          "```", ""]
    for version in MANIFEST:
        docs = index.get(version, [])
        if not docs:
            continue
        md.append(f"## OCPP {version}")
        md.append("")
        md.append("| document | role | pages | extracted text |")
        md.append("|---|---|--:|---|")
        for d in docs:
            md.append(f"| {d['label']} | {d['role']} | {d['pages']} | `{d['text']}` |")
        md.append("")
        for d in docs:
            if not d["sections"]:
                continue
            md.append(f"### {d['label']} — contents")
            md.append("")
            for s in d["sections"]:
                md.append(f"- `{d['slug']} pdf-page {s['page']}` — **{s['id']}** {s['title']}")
            md.append("")
    open(os.path.join(OUT, "INDEX.md"), "w", encoding="utf-8").write("\n".join(md) + "\n")
    print(f"wrote {os.path.relpath(OUT, ROOT)}/INDEX.md and sections.json")
    if missing:
        print("\nNOT FOUND in OCA_DOCS (skipped):")
        for m in missing:
            print("  " + m)


if __name__ == "__main__":
    main()
