import org.gradle.api.Project
import org.gradle.api.tasks.testing.Test
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

/**
 * Configures the current project as a Kotlin project
 */
fun Project.kotlinProject() {
    dependencies {
        "implementation"(kotlin("stdlib-jdk8"))
    }
}

/**
 * Configures the current project with base dependencies:
 * - kotlin
 * - KLogging
 */
fun Project.coreProject() {
    kotlinProject()

    dependencies {
        "implementation"("org.http4k:http4k-bom:_")
        "implementation"("org.jetbrains.kotlin:kotlin-reflect:_")
        "implementation"("io.github.microutils:kotlin-logging:_")
        "implementation"("org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.4.1")
        "implementation"("org.slf4j:slf4j-api:_")

        "testImplementation"("org.junit.jupiter:junit-jupiter-api:_")
        "testImplementation"("org.junit.jupiter:junit-jupiter-params:_")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:_")
        // Gradle 9 no longer puts the JUnit Platform launcher on the test runtime
        // classpath implicitly; declare it explicitly (version aligned with Jupiter 6.1.0).
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher:6.1.0")

        "testImplementation"("io.strikt:strikt-core:_")
        "testImplementation"("io.mockk:mockk:_")
    }

    tasks.getByPath("test").doFirst {
        with(this as Test) {
            useJUnitPlatform()
        }
    }
}
