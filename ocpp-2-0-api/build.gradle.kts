plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":ocpp-2-0-core"))
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
