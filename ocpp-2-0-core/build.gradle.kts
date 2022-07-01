plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-transport"))
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
            artifactId = "ocpp-2-0-core"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 2.0 Core")
                artifactId = "ocpp-2-0-core"
                description.set("OCPP 2.0 Core")
            }
        }
    }
}
