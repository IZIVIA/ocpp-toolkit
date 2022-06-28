plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
    implementation(project(":ocpp-wamp"))
    implementation(project(":ocpp-transport"))
    implementation(project(":ocpp-2-0-core"))
    implementation(project(":ocpp-1-6-core"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-transport-websocket"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Transport Websocket")
                artifactId = "ocpp-transport-websocket"
                description.set("OCPP Transport Websocket")
            }
        }
    }
}
