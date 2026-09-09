# buildSrc

## Purpose

Shared Gradle build logic for every module in the build. A single file,
`src/main/kotlin/Dependencies.kt`, defines the two convention functions each module's
`build.gradle.kts` opts into.

## The two conventions

- **`Project.kotlinProject()`** — adds the Kotlin stdlib. The minimal baseline.
- **`Project.coreProject()`** — calls `kotlinProject()`, then adds the standard runtime stack
  (http4k BOM, kotlin-reflect, kotlin-logging, slf4j-api) and the standard test
  stack (JUnit Jupiter api/params/engine, `junit-platform-launcher`, Strikt, MockK), and forces
  `useJUnitPlatform()` on the `test` task.

Almost every module calls `coreProject()`. Use `kotlinProject()` only for a module that
genuinely needs no logging, no datetime and no test stack.

## Key patterns

- **Dependencies are declared by string, not typed accessors** (`"implementation"(...)`),
  because these run outside a module's own `build.gradle.kts` scope.
- **Versions are never written here.** Every coordinate ends in `:_`, resolved by the
  refreshVersions plugin from the root `versions.properties`. To change a version, edit
  `versions.properties` — never this file.
- **`junit-platform-launcher` is declared explicitly.** Gradle 9 no longer puts it on the test
  runtime classpath implicitly; the source comment records this. Removing it breaks test
  execution with a confusing "no tests found" style failure.
- The root `build.gradle.kts` handles what applies to *all* subprojects unconditionally (Java 21
  toolchain, Kotlin 2.2 language/api level, `-Xjsr305=strict`, publishing, signing). `buildSrc`
  handles what modules opt into. Put new cross-cutting compiler or publishing config in the root
  build file; put new shared *dependencies* here.

## Gotcha

`buildSrc` is compiled before anything else and invalidates the whole build cache when it
changes. Edits here are expensive — batch them.

## See also

- `../build.gradle.kts` — root conventions applied to all subprojects
- `../versions.properties` — the single source of truth for dependency versions
- `../settings.gradle.kts` — module list; a new module must be registered there
