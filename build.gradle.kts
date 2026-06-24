import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import java.util.*

plugins {
    kotlin("jvm") version "2.4.0" apply true
    id("com.google.protobuf") version "0.8.18" apply false
    id("maven-publish")
    id("io.github.gradle-nexus.publish-plugin") version "2.0.0"
}

val versionNumber = System.getenv("VERSION")?.substringAfter("R-") ?: "dev"

println("building current version: $versionNumber")

allprojects {
    group = "com.izivia"
    version = versionNumber

    repositories {
        mavenLocal()
        mavenCentral()
        google()
    }
}

subprojects {

    apply {
        plugin("java")
        plugin("org.jetbrains.kotlin.jvm")
        plugin("maven-publish")
        plugin("signing")
    }

    tasks.withType<JavaCompile>().configureEach {
        sourceCompatibility = "21"
        targetCompatibility = "21"
    }

    tasks.withType<KotlinCompile>().configureEach {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_21)
            freeCompilerArgs.addAll("-Xjsr305=strict", "-opt-in=kotlin.RequiresOptIn")
            languageVersion.set(KotlinVersion.KOTLIN_2_2)
            apiVersion.set(KotlinVersion.KOTLIN_2_2)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }

    extensions.getByType<PublishingExtension>().publications {
        create<MavenPublication>("maven") {
            pom {
                url.set("https://github.com/IZIVIA/ocpp-toolkit")

                scm {
                    connection.set("scm:git:https://github.com/IZIVIA/ocpp-toolkit.git")
                    developerConnection.set("scm:git:git@github.com:IZIVIA/ocpp-toolkit.git")
                    url.set("https://github.com/IZIVIA/ocpp-toolkit")
                }

                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        id.set("IZIVIA")
                        name.set("IZIVIA")
                        email.set("izivia-fr-dms-deliv@izivia.com")
                        url.set("https://www.izivia.com/")
                        organization.set("IZIVIA")
                        organizationUrl.set("https://www.izivia.com/")
                    }
                }
            }
        }
    }

    extensions.getByType<SigningExtension>()
        .sign(extensions.getByType<PublishingExtension>().publications.named("maven").get())
    if (System.getenv("GPG_PRIVATE_KEY") != null) {
        extensions.getByType<SigningExtension>().useInMemoryPgpKeys(
            Base64.getDecoder().decode(System.getenv("GPG_PRIVATE_KEY")).decodeToString(),
            System.getenv("GPG_PASSPHRASE")
        )
    }

    tasks.withType<Sign> {
        onlyIf { System.getenv("GPG_PRIVATE_KEY") != null }
    }
}

nexusPublishing {
    repositories {
        sonatype {
            nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
            snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))
            username.set(System.getenv("SONATYPE_USERNAME"))
            password.set(System.getenv("SONATYPE_PASSWORD"))
        }
    }
}
