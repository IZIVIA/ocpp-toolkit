plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

coreProject()

dependencies {
    api(project(":ocpp-1-6-core"))
    api(project(":utils"))
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
            artifactId = "ocpp-1-6-security"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.6 Security")
                artifactId = "ocpp-1-6-security"
                description.set("OCPP 1.6 Security Whitepaper operations")
            }
        }
    }
}
