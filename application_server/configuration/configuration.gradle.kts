plugins {
    id("io.quarkus")
}

dependencies {
    implementation(project(":application_server:core:domain"))
    implementation(project(":application_server:infrastructure:dataproviders"))
    implementation(project(":application_server:infrastructure:entrypoints:entrypoints-rest"))
    implementation(project(":application_server:infrastructure:entrypoints:entrypoints-grpc"))

    implementation(platform(project(":platform:quarkus-platform")))

    implementation("io.quarkus:quarkus-container-image-jib")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-kotlin")

    implementation("io.quarkus:quarkus-resteasy-jackson")

    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-grpc")

    implementation("io.quarkus:quarkus-micrometer-registry-prometheus")

    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
}