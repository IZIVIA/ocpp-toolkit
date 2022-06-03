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
        "implementation"("org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.3.2")
        "implementation"("org.slf4j:slf4j-api:_")

        "testImplementation"("org.junit.jupiter:junit-jupiter-api:_")
        "testRuntimeOnly"("org.junit.jupiter:junit-jupiter-engine:_")

        "testImplementation"("io.strikt:strikt-core:_")
        "testImplementation"("io.mockk:mockk:_")
    }

    tasks.getByPath("test").doFirst {
        with(this as Test) {
            useJUnitPlatform()
        }
    }
}
