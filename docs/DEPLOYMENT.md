# Release and Publishing

This is a **library**, not a deployable service. "Deployment" means publishing artifacts to Maven
Central. There is nothing to host, no runtime environment to provision, and no container image.

## Artifacts

Every module is published independently under group `com.izivia`, each with a javadoc jar and a
sources jar. Artifact ids mostly match the module directory, with three exceptions worth knowing:

| Module | Artifact id |
|---|---|
| `utils` | `ocpp-utils` |
| `generic-api` | `ocpp-generic-api` |
| `operation-information` | `ocpp-operation-information` |

Consumers normally depend on **`toolkit`**, which declares `api(project(...))` on every other
module and therefore pulls the whole surface transitively.

## Versioning

The root [`build.gradle.kts`](../build.gradle.kts) derives the version from the environment:

```kotlin
val versionNumber = System.getenv("VERSION")?.substringAfter("R-") ?: "dev"
```

Consequences:

- A local build is **always** version `dev`. `publishToMavenLocal` therefore installs `dev`
  artifacts — convenient for downstream testing, but remember to refresh them.
- The release version comes from the git tag. A tag `R-1.2.3` yields version `1.2.3`; the
  workflow passes `github.ref` as `VERSION` and `substringAfter("R-")` strips the prefix.

There is no version string committed anywhere in the repo. Do not add one.

## Release process

1. Merge everything intended for the release into `dev` (the default branch; CI runs there and on
   every PR).
2. Push a tag matching `R-*`, e.g. `R-1.2.3`.
3. [`.github/workflows/publish.yml`](../.github/workflows/publish.yml) triggers and runs:
   ```
   ./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository
   ```
   which stages to Sonatype and, if the staging repository validates, closes and releases it to
   Maven Central in one step. There is no manual promotion gate — a bad tag is a published bad
   release.

The job uses `concurrency: ocpp-publish`, so overlapping tag pushes serialise rather than race.

## Required secrets

The publish workflow injects five environment values; all are required for a real release:

| Env var | Source | Purpose |
|---|---|---|
| `VERSION` | `github.ref` (the tag) | derives the artifact version |
| `SONATYPE_USERNAME` | repo secret `DELIVERY_SONATYPE_USERNAME` | Sonatype auth |
| `SONATYPE_PASSWORD` | repo secret `DELIVERY_SONATYPE_PASSWORD` | Sonatype auth |
| `GPG_PRIVATE_KEY` | repo secret `DELIVERY_GPG_SECRET_KEY` | base64-encoded signing key |
| `GPG_PASSPHRASE` | repo secret `DELIVERY_GPG_KEY_PASSPHRASE` | signing key passphrase |

**Signing is conditional.** The root build wires `useInMemoryPgpKeys` only when `GPG_PRIVATE_KEY`
is present, and every `Sign` task carries `onlyIf { System.getenv("GPG_PRIVATE_KEY") != null }`.
So local builds skip signing silently — a build that succeeds locally proves nothing about
whether signing is correctly configured. Only a real tagged run exercises that path.

Sonatype endpoints are configured in the root build's `nexusPublishing` block (the
`ossrh-staging-api.central.sonatype.com` service URL and the `central.sonatype.com` snapshot
repository).

## POM metadata

The root build applies identical POM metadata to every publication: MIT licence, the
`IZIVIA/ocpp-toolkit` GitHub SCM coordinates, and the IZIVIA developer entry. Per-module
`build.gradle.kts` files supply only `artifactId`, `name` and `description`. Add those three for
any new module, or its POM will be incomplete and Sonatype validation will reject the whole
staging repository.

## Continuous integration

[`.github/workflows/ci.yml`](../.github/workflows/ci.yml) runs on pushes to `dev` and on pull
requests to any branch: JDK 21 (temurin), Gradle via `gradle/actions/setup-gradle`, then
`./gradlew build`. That is the whole gate — build plus tests. There is no lint step, no coverage
threshold, and no security scan. `concurrency: ocpp-ci` serialises runs.

Both workflows pin their actions to commit SHAs rather than tags; keep that practice when
updating them.

## Publishing locally

```
./gradlew publishToMavenLocal
```

Installs all modules at version `dev` into `~/.m2/repository`. The root build lists `mavenLocal()`
first in the repository order, so a locally installed `dev` build takes precedence — remember to
clear it when you are done, or you will debug against stale artifacts.

## See also

- [DEVELOPMENT.md](DEVELOPMENT.md) — day-to-day build and test commands
- [TECH_STACK.md](TECH_STACK.md) — the publishing plugins in use
