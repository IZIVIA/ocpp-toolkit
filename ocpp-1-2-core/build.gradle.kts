plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation(project(":utils"))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-1-2-core"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.2 Core")
                artifactId = "ocpp-1-2-core"
                description.set("OCPP 1.2 Core")
            }
        }
    }
}
