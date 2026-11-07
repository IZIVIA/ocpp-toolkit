plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

dependencies {
    api(project(":operation-information"))

    testImplementation("org.junit.jupiter:junit-jupiter-api:_")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:_")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:_")
    testImplementation("io.strikt:strikt-core:_")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-transport"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP Transport")
                artifactId = "ocpp-transport"
                description.set("OCPP Transport")
            }
        }
    }
}

