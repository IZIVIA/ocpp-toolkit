plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

dependencies {
    implementation(project(":operation-information"))
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
