# Development Guide

This is a Kotlin/JVM **library** (group `com.izivia`, published to Maven Central), not an
application. There is no dev server to run, no ports to bind, no database, and no `.env` file —
"development" here means building modules, running the test suites, and (optionally) exercising
the opt-in integration/example tests against a real CSMS.

## Prerequisites

- **JDK 21** (CI uses Temurin 21; the root build pins `sourceCompatibility`/`targetCompatibility`
  and Kotlin's `jvmTarget` to `JVM_21` — see `build.gradle.kts`).
- **Nothing else.** The Gradle Wrapper (`./gradlew`) provisions Gradle itself; no local Gradle
  install, database, or Docker setup is required.

## Quick Start

```bash
git clone git@github.com:IZIVIA/ocpp-toolkit.git
cd ocpp-toolkit

# Build every module (compiles + runs all tests)
./gradlew build
```

There is no "start" step — see the [README](../README.md) for end-to-end usage examples of the
library itself (`Ocpp16ConnectionToCSMS`, `Ocpp20ConnectionToCSMS`, etc.).

## Environment Setup

No `.env`, secrets, or environment variables are needed for local build/test. The only
environment variables the build reads are used exclusively by the **publish** workflow (see
[Publishing](#publishing--release) below) and have no effect on `./gradlew build`:

| Variable | Purpose |
|---|---|
| `VERSION` | Derives the published version (`R-<version>` git tag); local builds always resolve to version `dev` (`build.gradle.kts`: `System.getenv("VERSION")?.substringAfter("R-") ?: "dev"`) |
| `SONATYPE_USERNAME` / `SONATYPE_PASSWORD` | Sonatype/Maven Central staging credentials |
| `GPG_PRIVATE_KEY` / `GPG_PASSPHRASE` | Artifact signing; if `GPG_PRIVATE_KEY` is unset, all `Sign` tasks are skipped (`onlyIf` in the root build) |

## Ports

Not applicable — this is a library with no long-running dev service. (The `ocpp-transport-websocket`
end-to-end test binds an ephemeral local port for the duration of a single test; see
[Testing](#testing).)

## Test Data

No seed scripts, fixtures directory, or mock API server are provided at the repo level. Test data
is constructed in-code:

- `generic-api` publishes a **`testFixtures`** source set with Kotlin builder DSLs (e.g.
  `TransactionEventReqBuilder`) for constructing generic `Req` objects. Every `*-api-adapter`
  module consumes it via `testImplementation(testFixtures(project(":generic-api")))`.
- The `*-json` modules ship the official OCPP JSON schemas under `src/main/resources` and validate
  serialized model instances against them directly (`JsonSchemaTest`) — there is no separate
  fixture corpus.
- Opt-in integration/example tests in `toolkit` (see [Testing](#testing)) exercise the library
  against a real external CSMS instance (e.g. [SteVe](https://github.com/steve-community/steve))
  that you must provide yourself; the repo does not bundle or start one.

## Build Commands

| Command | Purpose |
|---|---|
| `./gradlew build` | Compile and test every module (what CI runs) |
| `./gradlew :toolkit:build` | Build a single module (and the modules it depends on) |
| `./gradlew clean` | Remove build outputs (all modules) |
| `./gradlew clean build` | Clean rebuild — useful after `buildSrc` or kapt-related changes (see [Gotchas](#gotchas)) |
| `./gradlew refreshVersions` | Regenerate `versions.properties` with available dependency updates |
| `./gradlew publishToMavenLocal` | Build and install all publishable artifacts into `~/.m2` for local consumption by another project |

Reference each module's `build.gradle.kts` for module-specific tasks (e.g. `kaptKotlin` in the
`*-api-adapter` modules). There is no top-level custom task beyond the standard Gradle/Kotlin ones.

## Development Workflow

- **Hot reload**: not applicable (library, no running process).
- **Incremental compilation**: standard Kotlin/Gradle incremental compilation applies. Gradle's
  build cache (`org.gradle.caching=true`) and configure-on-demand
  (`org.gradle.configureondemand=true`) are enabled in `gradle.properties`, so repeated single-module
  builds are reasonably fast — with the exception below.
- **The Gradle daemon is deliberately disabled** (`org.gradle.daemon=false` in `gradle.properties`,
  annotated in the file itself with `# !!!! causes build issues !!!!`). Every `./gradlew` invocation
  therefore starts a fresh JVM instead of reusing a warm daemon, so builds are slower than a typical
  Gradle project. **Do not re-enable the daemon** to "speed things up" — it was turned off
  intentionally.
- JVM memory for the build itself: `-Xmx2G -XX:MaxMetaspaceSize=2G -XX:+UseParallelGC`
  (`gradle.properties`).

## Running Tests

| Command | Purpose |
|---|---|
| `./gradlew test` | Run all unit tests in every module |
| `./gradlew :ocpp-1-6-api-adapter:test` | Run tests for a single module |
| `./gradlew :toolkit:test --tests "com.izivia.ocpp.integration.test.CSMSSecurityWiringTest"` | Run a single test class |
| `./gradlew test --tests "*MapperTest"` | Run tests matching a pattern, across modules |

All modules use **JUnit 5 (Jupiter)** with **Strikt** assertions and **MockK**; `toolkit` and
`ocpp-1-6-api-adapter` additionally use `mockito-kotlin` in places. `useJUnitPlatform()` is forced
by the `coreProject()` convention in `buildSrc` — see
[Build-System Notes](#build-system-notes).

### Where tests live

Standard Gradle layout: `<module>/src/test/kotlin/...`, mirroring the package structure of
`src/main/kotlin`. There is no separate top-level test module.

### Opt-in integration/example tests (`toolkit`)

`toolkit/src/test` contains `ExampleTest.kt`, `ExampleCSApiTest.kt`, `IntegrationTest.kt`,
`IntegrationTestCSApi.kt`, and `SteveTest.kt`, which connect to a **real external CSMS** (e.g.
SteVe) rather than mocks. They are gated with JUnit 5's `@EnabledIfSystemProperty` and are
**skipped by default**. (`ServerSettingSettingsTest.kt`, `CSMSSecurityWiringTest.kt` and
`utils/KotlinInstantModuleTest.kt` in the same module are *not* gated and run normally.) To run
the gated ones:

```bash
./gradlew :toolkit:test -Dhas.local.steve=true
```

You must have a compatible CSMS reachable at the URL the test expects (read the test source for
the exact target) before enabling this flag. These tests also double as usage documentation for
`ApiFactory`'s `ocpp16ConnectionToCSMS`/`ocpp20ConnectionToCSMS` factory functions.

### `JsonSchemaTest` (the `*-json` modules)

Each `ocpp-*-json` module (`ocpp-1-5-json`, `ocpp-1-6-json`, `ocpp-2-0-json`) ships the official
OCPP JSON schemas as resources and has a `JsonSchemaTest` that round-trips model instances through
the parser and validates the result against those schemas. **A failure here is a real
protocol-conformance bug**, not a flaky or bad test — do not "fix" it by relaxing the assertion.

### End-to-end socket test (`ocpp-transport-websocket`)

`WebsocketTest.kt` includes a real-socket end-to-end case: it starts an `OcppWampServer` on a free
local port and connects a real `WebsocketClient` to it, polling for connection readiness instead
of sleeping (to avoid CI flakiness). No configuration is needed to run it — it is part of the
module's normal `test` task.

## Debugging

No application process to attach a remote debugger to. To debug a specific test:

- From an IDE (IntelliJ IDEA is the natural fit for this project, given `.editorconfig`'s `ij_*`
  keys), run/debug the individual test class or method directly — Gradle's IntelliJ integration
  will use the project's Kotlin/JVM 21 toolchain automatically.
- From the command line, attach a debugger to a Gradle test JVM with
  `./gradlew :<module>:test --debug-jvm --tests "<TestClass>"` and connect on port `5005`.
- For adapter modules (`*-api-adapter`), if a MapStruct-generated mapper doesn't behave as
  expected, check the kapt-generated source under
  `<module>/build/generated/source/kapt/main` before assuming the hand-written mapper code is
  wrong — see the kapt gotcha below.

## Common Gotchas

- **`org.gradle.daemon=false` is intentional** (see [Development Workflow](#development-workflow)).
  Don't re-enable it.
- **kapt and stale generated sources**: the `*-api-adapter` and `*-api` modules
  (`ocpp-1-5-api-adapter`, `ocpp-1-6-api-adapter`, `ocpp-2-0-api-adapter`, `ocpp-1-5-api`,
  `ocpp-1-6-api`, `ocpp-2-0-api`) use `kotlin("kapt")` to run the MapStruct annotation processor
  (`org.mapstruct:mapstruct-processor`). If a mapper change doesn't seem to take effect, or you
  get a confusing compile error referencing generated code, run
  `./gradlew :<module>:clean :<module>:build` for that module rather than debugging the symptom.
- **`junit-platform-launcher` is declared explicitly** in `buildSrc`'s `coreProject()` — Gradle 9
  no longer puts it on the test runtime classpath implicitly. Removing that dependency line
  produces a confusing "no tests found" style failure rather than a clear error.
- **Editing `buildSrc` invalidates the whole build cache.** `buildSrc` is compiled before every
  other module, so any change there forces a full recompile of the entire project. Batch
  `buildSrc` edits rather than making them one at a time.
- **`testFixturesImplementation` no longer extends `implementation`** under Kotlin Gradle Plugin
  2.x. If you add a `testFixtures` dependency that `main` already has, you must declare it again
  explicitly for `testFixtures`, or resolution will fail. `generic-api` is the module with
  `java-test-fixtures` enabled, so this bites there first.
- **Never edit a dependency version directly in a `build.gradle.kts` or in `buildSrc`.** All
  coordinates end in `:_` and are resolved from the single source of truth,
  `versions.properties`, by the refreshVersions plugin — see
  [Build-System Notes](#build-system-notes).

## Code Quality

There is **no ktlint, detekt, or Spotless task** configured in this build — style is enforced by
`.editorconfig` (`ij_*` keys) plus `kotlin.code.style=official` in `gradle.properties`, i.e. via
IDE formatting, not a CI-enforced linter/formatter task. Do not invent a lint/format Gradle command
that does not exist in this repo.

### Formatting

Configure your IDE to respect `.editorconfig` (UTF-8, LF line endings, 4-space indent, 120-char
max line length, final newline, trim trailing whitespace).

### Type Checking

Kotlin's compiler performs type checking as part of every `compileKotlin`/`compileTestKotlin`
task (`./gradlew build` or `./gradlew :<module>:compileKotlin`); there is no separate type-check
step.

## Build-System Notes

### refreshVersions workflow

Dependency **versions never appear in a module's `build.gradle.kts` or in `buildSrc`.** Every
coordinate is written with a trailing `:_` (e.g. `"org.mapstruct:mapstruct:_"`), and the actual
version is resolved from the root `versions.properties` file by the
[refreshVersions](https://github.com/Splitties/refreshVersions) Gradle plugin (declared in
`settings.gradle.kts`).

- To see what updates are available and refresh the file: `./gradlew refreshVersions`, then review
  and commit the diff to `versions.properties`.
- To bump one dependency, edit its `version.<group>..<artifact>=<value>` line in
  `versions.properties` directly — never add a version string anywhere else.

### buildSrc conventions

Every module's `build.gradle.kts` opts into one of two convention functions defined in
`buildSrc/src/main/kotlin/Dependencies.kt` (see `buildSrc/CLAUDE.md` for full detail):

- **`kotlinProject()`** — Kotlin stdlib only; the minimal baseline.
- **`coreProject()`** (used by almost every module) — calls `kotlinProject()`, then adds the
  standard runtime stack (http4k BOM, kotlin-reflect, kotlin-logging, slf4j-api)
  and the standard test stack (JUnit Jupiter api/params/engine, `junit-platform-launcher`, Strikt,
  MockK), and forces `useJUnitPlatform()`.

The root `build.gradle.kts` applies cross-cutting config unconditionally to every subproject (Java
21 toolchain, Kotlin 2.2 language/API level, `-Xjsr305=strict`/`-opt-in=kotlin.RequiresOptIn`
compiler args, `maven-publish`/`signing` plugin application, POM metadata, Sonatype publishing
repository). New shared *dependencies* belong in `buildSrc`; new shared *compiler or publishing*
config belongs in the root build file.

### kapt

Used by the `*-api-adapter` and `*-api` modules, to run MapStruct's annotation processor
(`kaptIncremental` is enabled via `kapt.incremental.apt=true` in `gradle.properties`). See the
kapt gotcha above if generated mapper code seems stale.

### Cache invalidation

Because the daemon is off and `buildSrc` changes invalidate the whole cache, the two most
expensive things you can do locally are (1) editing `buildSrc` repeatedly instead of batching
changes, and (2) running `clean` more often than necessary — prefer scoping a clean to the single
module you're debugging (`./gradlew :<module>:clean`) over a full `./gradlew clean`.

## Project Layout

25 Gradle modules plus `buildSrc` under a single root, following five repeating "layer" module
types, one per OCPP version where applicable. Detailed conventions for each layer are documented
separately — do not duplicate them here:

- [docs/ARCHITECTURE.md](ARCHITECTURE.md) — system-level architecture and module relationships.
- [docs/CORE.guidelines.md](CORE.guidelines.md) — conventions shared by the `ocpp-*-core` modules
  (data structures + operation declarations per OCPP version).
- [docs/API.guidelines.md](API.guidelines.md) — conventions shared by the `ocpp-*-api` modules.
- [docs/API-ADAPTER.guidelines.md](API-ADAPTER.guidelines.md) — conventions shared by the
  `ocpp-*-api-adapter` modules (MapStruct-based bridge between `generic-api` and each version's
  `-core` model).
- [docs/JSON.guidelines.md](JSON.guidelines.md) — conventions shared by the `ocpp-*-json` modules
  (OCPP-J wire format + `JsonSchemaTest`).
- [docs/SOAP.guidelines.md](SOAP.guidelines.md) — conventions shared by the `ocpp-*-soap` modules
  (OCPP-S wire format).
- [docs/protocol/](protocol/README.md) — the grep-able protocol reference: what fields each action
  carries in each version, who initiates it, and the section and page of the normative OCA document.
  Committed, so no generation step; `generate.py` refreshes it after an `Actions` or schema change.

Cross-cutting / non-versioned modules:

- **`toolkit`** — the aggregator/facade module and main entry point (`ApiFactory`, `CSMS`); see
  `toolkit/CLAUDE.md`.
- **`generic-api`** — version-agnostic OCPP model (`CSApi`/`CSMSApi`) that the API adapters map
  to/from; publishes the `testFixtures` builder DSLs used by adapter-module tests.
- **`operation-information`** — shared operation-description types (`RequestMetadata`,
  `OperationExecution`, `CSMSCallbacks`, etc.) used across layers.
- **`ocpp-transport`** / **`ocpp-transport-websocket`** / **`ocpp-transport-soap`** —
  `ClientTransport`/`ServerTransport` abstractions and their WebSocket/SOAP implementations.
- **`ocpp-wamp`** — the WAMP-like RPC-over-WebSocket layer used by OCPP-J.
- **`ocpp-json`** / **`ocpp-soap`** — shared parse/serialize/validate machinery consumed by the
  per-version `-json`/`-soap` modules.
- **`ocpp-1-6-security`** — the OCPP 1.6 Security Whitepaper operation interfaces; the one module
  outside the `ocpp-<version>-<layer>` naming scheme, published separately and opt-in.
- **`utils`** — small shared helpers.

Every module directory has its own `CLAUDE.md` documenting its specific purpose, key patterns, and
"adding a new X" checklist — consult the relevant one before making a change in that module.

## Adding a New Module

When adding a new module (e.g. supporting a new OCPP version or layer), the pieces to touch are
scattered across the build — miss one and the module either doesn't build or isn't visible to
consumers:

1. **Register it** in `settings.gradle.kts`'s `include(...)` block.
2. **Create `<module>/build.gradle.kts`**, applying the appropriate convention function
   (`kotlinProject()` or, in almost every case, `coreProject()` from `buildSrc`), plus a
   `publishing { publications { named<MavenPublication>("maven") { ... } } }` block mirroring an
   existing module (see e.g. `ocpp-1-6-api-adapter/build.gradle.kts`) so the module publishes
   correctly alongside the rest — `groupId`/`artifactId`/`version`, `from(components["java"])`,
   and a POM `name`/`description`.
3. If the module needs the generic API's test builders, add
   `testImplementation(testFixtures(project(":generic-api")))`.
4. If the module needs MapStruct code generation (an `*-api-adapter`-style module), apply
   `kotlin("kapt")` and add `kapt("org.mapstruct:mapstruct-processor:_")`.
5. **Aggregate it into `toolkit`**: add `api(project(":<module>"))` in `toolkit/build.gradle.kts`
   for every module a consumer needs transitively — `toolkit` only re-exposes modules declared as
   `api(...)`, not `implementation(...)`. See "Adding Support for a New OCPP Version Here" in
   `toolkit/CLAUDE.md` for the accompanying `ApiFactory`/`CSMS` wiring.
6. **Add `<module>/CLAUDE.md`** documenting the module's purpose, key patterns, and any
   module-specific "adding a new X" checklist, following the style of the existing 29 module
   `CLAUDE.md` files.
7. Any new version-independent dependency coordinate must be added with a trailing `:_` and its
   version placed in `versions.properties` — never hardcoded in the module's `build.gradle.kts`.

## CI and Release Process

### CI (`.github/workflows/ci.yml`)

Runs on every push to `dev` and on every pull request (any target branch). A single job: set up
JDK 21 (Temurin), checkout, set up Gradle, then `./gradlew build`. `dev` is the base branch for
pull requests.

### Publishing (`.github/workflows/publish.yml`)

Triggered by pushing a git tag matching `R-*`. The job runs
`./gradlew publishToSonatype closeAndReleaseSonatypeStagingRepository` with `VERSION` set to the
tag ref and the Sonatype/GPG secrets described in [Environment Setup](#environment-setup) supplied
as environment variables. The version published is `VERSION` with the `R-` prefix stripped (root
`build.gradle.kts`). Publishing/tagging is not something a day-to-day contributor does — it's the
release process for maintainers with access to those repository secrets.

## Useful Links

- [README.md](../README.md) — project goals, supported OCPP versions/flavors, usage examples.
- [docs/ARCHITECTURE.md](ARCHITECTURE.md) — system architecture.
- [docs/CONVENTIONS.md](CONVENTIONS.md) — naming, packages, nullability and error-handling idioms.
- Per-module `CLAUDE.md` files — module-specific implementation detail.
- [OCA — Open Charge Point Protocol](https://www.openchargealliance.org/) — the protocol this
  library implements.

## Need Help?

No team channel, wiki, or support contact is recorded in this repository. Refer to the module
`CLAUDE.md` files and the GitHub repository
(`https://github.com/IZIVIA/ocpp-toolkit`) issue tracker for questions not answered by this guide.

## Open Questions for Maintainers

- No `CONTRIBUTING.md`, code owners file, or PR template was found — is there an expected PR
  process, review requirement, or branch-naming convention beyond "PR against `dev`"?
- The opt-in `toolkit` integration tests (`-Dhas.local.steve=true`) assume a reachable SteVe (or
  compatible CSMS) instance; there is no documented local setup (e.g. a `docker-compose.yml`) for
  standing one up. Is there a recommended way to run one locally?
- No linter/formatter task (ktlint/detekt/Spotless) is wired into the build even though
  `.editorconfig` is extensive — is formatting enforcement purely manual/IDE-based by design, or
  is CI enforcement planned?
