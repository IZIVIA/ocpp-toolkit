import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") apply true
    id("com.google.protobuf") version "0.8.18" apply false
}

val versionNumber = System.getenv("version")?.substringAfter("R-") ?: "dev"

allprojects {
    group = "fr.simatix.cs.simulator"
    version = versionNumber

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}

subprojects {
    tasks.withType<KotlinCompile>().all {
        kotlinOptions {
            jvmTarget = "17"
            freeCompilerArgs = listOf("-Xjsr305=strict", "-Xopt-in=kotlin.RequiresOptIn")
            languageVersion = "1.6"
            apiVersion = "1.6"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        ignoreFailures = true
    }
}