plugins {
    kotlin("jvm")
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":utils"))

    implementation("com.fasterxml.jackson.core:jackson-core:_")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:_")

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
            artifactId = "ocpp-json"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP JSON")
                artifactId = "ocpp-json"
                description.set("This module provides commun features for JSON parsers")
            }
        }
    }
}
