plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-1-2-core"))
    api(project(":ocpp-json"))

    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.networknt:json-schema-validator:_")
    implementation(project(mapOf("path" to ":utils")))

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
            artifactId = "ocpp-1-2-json"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.2 JSON")
                artifactId = "ocpp-1-2-json"
                description.set("This module provides a JSON parser for OCPP 1.2")
            }
        }
    }
}
