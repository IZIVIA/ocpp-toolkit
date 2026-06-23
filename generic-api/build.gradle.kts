plugins {
    kotlin("jvm")
    java
    `maven-publish`
    `java-test-fixtures`
}

coreProject()

dependencies {
    implementation(project(":operation-information"))

    // KGP 2.x no longer makes testFixturesImplementation extend implementation,
    // so kotlinx-datetime (added as implementation by coreProject) must be declared
    // explicitly for the testFixtures source set.
    testFixturesImplementation("org.jetbrains.kotlinx:kotlinx-datetime-jvm:0.3.2")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-generic-api"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Generic API")
                artifactId = "ocpp-generic-api"
                description.set("OCPP Generic API")
            }
        }
    }
}
