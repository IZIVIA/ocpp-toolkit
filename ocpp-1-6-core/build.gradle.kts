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

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = project.name
            version = project.version.toString()

            from(components["java"])
        }
    }
}