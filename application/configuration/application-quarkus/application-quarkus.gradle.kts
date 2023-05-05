plugins {
    id("io.quarkus")
}

dependencies {
    implementation(platform(project(":platform:quarkus-platform")))

    implementation("io.quarkus:quarkus-container-image-jib")
    implementation("io.quarkus:quarkus-config-yaml")
    implementation("io.quarkus:quarkus-arc")
    implementation("io.quarkus:quarkus-kotlin")

    implementation("org.apache.camel.quarkus:camel-quarkus-grpc")
    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
//    implementation("io.quarkus:quarkus-grpc")
    implementation("org.apache.camel.quarkus:camel-quarkus-core")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("org.apache.camel.quarkus:camel-quarkus-rest")
    implementation("io.quarkus:quarkus-arc")
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")

}