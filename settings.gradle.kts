rootProject.name = "ocpp-toolkit"

plugins {
    id("com.gradle.enterprise").version("3.7.2")
    id("de.fayard.refreshVersions") version "0.40.1"
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

include(
    "csms-api-integration",
    "generic-api",
    "ocpp-wamp",
    "ocpp-websocket",
    "ocpp-transport",
    "ocpp-1-6-api-adapter",
    "ocpp-1-6-core",
    "ocpp-1-6-soap",
    "ocpp-2-0-api-adapter",
    "ocpp-2-0-core",
    "ocpp-1-6-api",
    "ocpp-2-0-api",
    "utils",
    "operation-information"
)
