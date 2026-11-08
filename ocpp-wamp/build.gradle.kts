plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-json"))
    api(project(":utils"))

    api("org.http4k:http4k-core:_")
    api("org.http4k:http4k-realtime-core:_")
    api("io.undertow:undertow-core:_")

    implementation("com.fasterxml.jackson.core:jackson-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")

    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    runtimeOnly("ch.qos.logback:logback-classic:_")

    testImplementation("com.fasterxml.jackson.core:jackson-databind:_")
}

java {
    withJavadocJar()
    withSourcesJar()
}

// the load test measures throughput, which a shared CI runner does not guarantee: keep it out of check
val loadTestClass = "com.izivia.ocpp.wamp.WampLoadTest"

tasks.test {
    filter {
        excludeTestsMatching(loadTestClass)
    }
}

val test by testing.suites.existing(JvmTestSuite::class)
tasks.register<Test>("loadTest") {
    description = "Runs the WAMP load test, which is not part of check."
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    testClassesDirs = files(test.map { it.sources.output.classesDirs })
    classpath = files(test.map { it.sources.runtimeClasspath })
    useJUnitPlatform()
    filter {
        includeTestsMatching(loadTestClass)
    }
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-wamp"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Wamp")
                artifactId = "ocpp-wamp"
                description.set("OCPP Wamp")
            }
        }
    }
}
