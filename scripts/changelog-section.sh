#!/bin/sh
# Prints the body of the `## [<version>]` section of a Keep a Changelog
# file, without its heading, leading and trailing blank lines trimmed.
#
# Usage: changelog-section.sh <version> [changelog, default CHANGELOG.md]
#
# The heading is matched by string comparison, not as a pattern: the line
# equals `## [<version>]` or starts with `## [<version>] `. The section ends
# before the next `## [` heading or the first link reference definition
# (`[label]: url`). Exits non-zero when the section is missing or empty.
set -eu

if [ "$#" -lt 1 ] || [ "$#" -gt 2 ]; then
    echo "usage: changelog-section.sh <version> [changelog, default CHANGELOG.md]" >&2
    exit 2
fi

version=$1
changelog=${2:-CHANGELOG.md}

if [ ! -f "$changelog" ] || [ ! -r "$changelog" ]; then
    echo "changelog-section.sh: cannot read $changelog" >&2
    exit 1
fi

status=0
CHANGELOG_VERSION=$version awk '
    BEGIN {
        heading = "## [" ENVIRON["CHANGELOG_VERSION"] "]"
        size = length(heading) + 1
        found = 0
        count = 0
    }
    !found {
        if ($0 == heading || substr($0, 1, size) == heading " ") {
            found = 1
        }
        next
    }
    /^## \[/ || /^\[[^]]+\]: / { exit }
    { lines[++count] = $0 }
    END {
        if (!found) {
            exit 3
        }
        first = 1
        while (first <= count && lines[first] ~ /^[ \t]*$/) {
            first++
        }
        last = count
        while (last >= first && lines[last] ~ /^[ \t]*$/) {
            last--
        }
        if (first > last) {
            exit 4
        }
        for (i = first; i <= last; i++) {
            print lines[i]
        }
    }
' "$changelog" || status=$?

case "$status" in
    0) ;;
    3)
        echo "changelog-section.sh: no section '## [$version]' in $changelog" >&2
        exit 1
        ;;
    4)
        echo "changelog-section.sh: section '## [$version]' in $changelog is empty" >&2
        exit 1
        ;;
    *)
        echo "changelog-section.sh: awk failed with exit $status on $changelog" >&2
        exit 1
        ;;
esac
