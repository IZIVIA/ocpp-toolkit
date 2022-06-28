plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")

    implementation("com.squareup.okhttp3:okhttp:4.9.3")

    implementation("org.jetbrains.kotlinx:kotlinx-datetime:_")

    runtimeOnly("ch.qos.logback:logback-classic:_")
}

java {
    withJavadocJar()
    withSourcesJar()
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
