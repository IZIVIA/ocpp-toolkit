plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-1-5-core"))
    api(project(":ocpp-soap"))

    implementation("com.fasterxml.jackson.core:jackson-core:_")
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:_")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.3")

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
            artifactId = "ocpp-1-5-soap"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.5 SOAP")
                artifactId = "ocpp-1-5-soap"
                description.set("This module provides a SOAP parser for OCPP 1.5")
            }
        }
    }
}
