plugins {
    kotlin("jvm")
    java
    `maven-publish`
}

dependencies {
    implementation(project(":operation-information"))
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.1")
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