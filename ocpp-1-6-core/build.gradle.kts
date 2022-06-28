plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:_")
    implementation(project(":utils"))
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
            artifactId = "ocpp-1-6-core"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.6 Core")
                artifactId = "ocpp-1-6-core"
                description.set("OCPP 1.6 Core")
            }
        }
    }
}
