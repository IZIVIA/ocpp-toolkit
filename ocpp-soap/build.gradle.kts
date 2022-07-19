plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":utils"))

    implementation("com.fasterxml.jackson.core:jackson-core:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:_")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:_")

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
            artifactId = "ocpp-soap"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP SOAP")
                artifactId = "ocpp-soap"
                description.set("This module provides commun features for SOAP parsers")
            }
        }
    }
}
