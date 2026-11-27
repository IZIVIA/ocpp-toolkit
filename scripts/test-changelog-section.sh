#!/bin/sh
# Tests for changelog-section.sh. Runs under any POSIX sh (dash, macOS sh)
# from any working directory; prints one line per case and exits non-zero
# when a case fails.
set -u

here=$(CDPATH='' cd -- "$(dirname -- "$0")" && pwd)
script="$here/changelog-section.sh"
repo_changelog="$here/../CHANGELOG.md"

tmp=$(mktemp -d)
trap 'rm -rf "$tmp"' EXIT
trap 'exit 1' INT TERM

passes=0
failures=0

pass() {
    passes=$((passes + 1))
    printf 'ok   %s\n' "$1"
}

fail() {
    failures=$((failures + 1))
    printf 'FAIL %s: %s\n' "$1" "$2"
}

# Runs the script with the given arguments; sets $status and writes its
# standard output and error to $tmp/out and $tmp/err.
run() {
    status=0
    sh "$script" "$@" > "$tmp/out" 2> "$tmp/err" || status=$?
}

# expect_section <case> <version> <changelog> <expected stdout>
expect_section() {
    run "$2" "$3"
    printf '%s\n' "$4" > "$tmp/expected"
    if [ "$status" -ne 0 ]; then
        fail "$1" "exit $status, stderr: $(cat "$tmp/err")"
    elif ! cmp -s "$tmp/expected" "$tmp/out"; then
        fail "$1" "unexpected output:
$(cat "$tmp/out")"
    else
        pass "$1"
    fi
}

# expect_failure <case> <stderr fragment> <arguments...>
expect_failure() {
    name=$1
    fragment=$2
    shift 2
    run "$@"
    if [ "$status" -eq 0 ]; then
        fail "$name" "exit 0, stdout: $(cat "$tmp/out")"
    elif ! grep -F -q -- "$fragment" "$tmp/err"; then
        fail "$name" "stderr does not name '$fragment': $(cat "$tmp/err")"
    elif [ -s "$tmp/out" ]; then
        fail "$name" "unexpected stdout: $(cat "$tmp/out")"
    else
        pass "$name"
    fi
}

changelog="$tmp/CHANGELOG.md"
cat > "$changelog" <<'EOF'
# Changelog

All notable changes to this project are documented in this file.

## [Unreleased]

### Added

- unreleased entry

## [4x0y0] - 2026-01-01

- metacharacter decoy

## [4.0.0] - 2026-02-01


### Added

- [link text](https://example.com) leads this bullet
- second entry


## [0.0.11.1] - 2020-01-02

- patch of a patch

## [0.0.11] - 2020-01-01

- plain patch

## [0.0.10] - 2019-12-31



## [0.0.9] - 2019-12-30

- last entry

[Unreleased]: https://example.com/compare/R-4.0.0...HEAD
[4.0.0]: https://example.com/compare/R-0.0.11.1...R-4.0.0
[0.0.9]: https://example.com/releases/tag/R-0.0.9
EOF

expect_section "present section, blank lines trimmed" 4.0.0 "$changelog" '### Added

- [link text](https://example.com) leads this bullet
- second entry'

run 4.0.0 "$changelog"
if grep -F -q -- '- [link text](https://example.com) leads this bullet' "$tmp/out"; then
    pass "bullet starting with [text](url) is kept"
else
    fail "bullet starting with [text](url) is kept" "bullet missing from: $(cat "$tmp/out")"
fi

expect_failure "missing version" "9.9.9" 9.9.9 "$changelog"
expect_failure "missing version names the file" "$changelog" 9.9.9 "$changelog"

expect_section "last section stops at the link references" 0.0.9 "$changelog" '- last entry'

only_unreleased="$tmp/only-unreleased.md"
cat > "$only_unreleased" <<'EOF'
## [Unreleased]

- unreleased entry
EOF
expect_failure "[Unreleased] is never matched for a real version" "1.0.0" 1.0.0 "$only_unreleased"

only_decoy="$tmp/only-decoy.md"
cat > "$only_decoy" <<'EOF'
## [4x0y0] - 2026-01-01

- metacharacter decoy
EOF
expect_failure "dots are not metacharacters (only 4x0y0 present)" "4.0.0" 4.0.0 "$only_decoy"

expect_section "0.0.11 does not match 0.0.11.1" 0.0.11 "$changelog" '- plain patch'
expect_section "0.0.11.1 does not match 0.0.11" 0.0.11.1 "$changelog" '- patch of a patch'

expect_failure "empty section" "0.0.10" 0.0.10 "$changelog"

inner_links="$tmp/inner-links.md"
cat > "$inner_links" <<'EOF'
## [2.0.0] - 2026-03-01

- newest

## [1.0.0] - 2026-02-01

- a
[label]: https://x
- more
[RFC](https://x) at column 0 is content

## [0.1.0] - 2026-01-01

- oldest

[2.0.0]: https://example.com/compare/R-1.0.0...R-2.0.0
[1.0.0]: https://example.com/compare/R-0.1.0...R-1.0.0
EOF
expect_section "link definition inside a section does not end it" 1.0.0 "$inner_links" '- a
[label]: https://x
- more
[RFC](https://x) at column 0 is content'

expect_section "trailing link block after a section with inner links" 0.1.0 "$inner_links" '- oldest'

undated="$tmp/undated.md"
cat > "$undated" <<'EOF'
## [5.0.0]

- undated entry

## [4.0.0] - 2026-02-01

- dated entry
EOF
expect_section "heading without a date" 5.0.0 "$undated" '- undated entry'

crlf="$tmp/crlf.md"
printf '## [Unreleased]\r\n\r\n## [6.0.0] - 2026-04-01\r\n\r\n### Fixed\r\n\r\n- crlf entry\r\n\r\n## [5.0.0] - 2026-03-01\r\n\r\n- older\r\n\r\n[6.0.0]: https://example.com/R-6.0.0\r\n' > "$crlf"
expect_section "CRLF changelog" 6.0.0 "$crlf" '### Fixed

- crlf entry'
expect_section "CRLF changelog, last section" 5.0.0 "$crlf" '- older'

expect_failure "no argument" "usage"
expect_failure "too many arguments" "usage" 4.0.0 "$changelog" extra
expect_failure "unreadable file" "$tmp/absent.md" 4.0.0 "$tmp/absent.md"

run 3.0.0 "$repo_changelog"
if [ "$status" -ne 0 ]; then
    fail "repository CHANGELOG.md, 3.0.0" "exit $status, stderr: $(cat "$tmp/err")"
elif [ "$(sed -n 1p "$tmp/out")" != "### Added" ]; then
    fail "repository CHANGELOG.md, 3.0.0" "first line is '$(sed -n 1p "$tmp/out")'"
else
    pass "repository CHANGELOG.md, 3.0.0"
fi

printf '%s passed, %s failed\n' "$passes" "$failures"
[ "$failures" -eq 0 ]
