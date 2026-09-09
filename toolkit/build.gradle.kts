plugins {
    kotlin("jvm")
    java
    id("maven-publish")
}

coreProject()

dependencies {
    implementation("org.http4k:http4k-core:_")
    implementation("org.http4k:http4k-client-websocket:_")
    implementation("org.http4k:http4k-server-undertow:_")
    implementation("org.http4k:http4k-client-apache:_")
    implementation("com.fasterxml.jackson.core:jackson-databind:_")

    api(project(":utils"))
    api(project(":generic-api"))
    api(project(":ocpp-wamp"))
    api(project(":ocpp-transport-websocket"))
    api(project(":ocpp-1-2-core"))
    api(project(":ocpp-1-2-api"))
    api(project(":ocpp-1-2-api-adapter"))
    api(project(":ocpp-1-2-soap"))
    api(project(":ocpp-1-5-api-adapter"))
    api(project(":ocpp-1-5-core"))
    api(project(":ocpp-1-5-api"))
    api(project(":ocpp-1-5-soap"))
    api(project(":ocpp-1-6-api-adapter"))
    api(project(":ocpp-1-6-core"))
    api(project(":ocpp-1-6-security"))
    api(project(":ocpp-1-6-api"))
    api(project(":ocpp-1-6-soap"))
    api(project(":ocpp-2-0-api"))
    api(project(":ocpp-transport-soap"))
    api(project(":ocpp-2-0-api-adapter"))
    api(project(":ocpp-2-0-core"))
    api(project(":ocpp-transport"))
    api(project(":operation-information"))

    testImplementation("org.mockito.kotlin:mockito-kotlin:_")
    testImplementation(project(":utils"))
    testImplementation("ch.qos.logback:logback-classic:_")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-toolkit"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Toolkit")
                artifactId = "ocpp-toolkit"
                description.set("OCPP Toolkit")
            }
        }
    }
}
