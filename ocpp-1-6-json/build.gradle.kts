plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-1-6-core"))
    api(project(":ocpp-json"))

    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.networknt:json-schema-validator:_")

    testImplementation(kotlin("test-junit"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-1-6-json"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.6 JSON")
                artifactId = "ocpp-1-6-json"
                description.set("This module provides a JSON parser for OCPP 1.6")
            }
        }
    }
}
