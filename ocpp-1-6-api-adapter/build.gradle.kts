plugins {
    kotlin("jvm")
    kotlin("kapt")
    java
    `maven-publish`
}

coreProject()

dependencies {
    implementation(project(":generic-api"))
    implementation(project(":ocpp-1-6-core"))
    implementation(project(":ocpp-1-6-security"))
    implementation(project(":ocpp-transport"))
    implementation(project(":operation-information"))
    implementation(project(":utils"))
    implementation("org.mapstruct:mapstruct:_")
    kapt("org.mapstruct:mapstruct-processor:_")
    runtimeOnly("ch.qos.logback:logback-classic:_")
    testImplementation(testFixtures(project(":generic-api")))
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            groupId = project.group.toString()
            artifactId = "ocpp-1-6-api-adapter"
            version = project.version.toString()

            from(components["java"])

            pom {
                name.set("OCPP 1.6 API Adapter")
                artifactId = "ocpp-1-6-api-adapter"
                description.set("OCPP 1.6 API Adapter")
            }
        }
    }
}
