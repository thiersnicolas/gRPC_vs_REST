pluginManagement {
    val quarkusPluginVersion: String by settings
    val quarkusPluginId: String by settings
    val springBootPluginVersion: String by settings
    val springBootPluginId: String by settings
    repositories {
        mavenCentral()
        gradlePluginPortal()
        mavenLocal()
    }
    plugins {
        id(quarkusPluginId) version quarkusPluginVersion
        id(springBootPluginId) version springBootPluginVersion
    }
}
rootProject.name = "grpc-vs-rest"

include(":platform:quarkus-platform")
include(":platform:spring-platform")

include(":application_client:configuration")

include(":application_server:configuration")
include(":application_server:core:domain")
include(":application_server:infrastructure:dataproviders")
include(":application_server:infrastructure:entrypoints:entrypoints-grpc")
include(":application_server:infrastructure:entrypoints:entrypoints-rest")




rootProject.children
        .flatMap { child -> if (child.children.isEmpty()) listOf(child) else child.children }
        .flatMap { child -> if (child.children.isEmpty()) listOf(child) else child.children }
        .flatMap { child -> if (child.children.isEmpty()) listOf(child) else child.children }
        .flatMap { child -> if (child.children.isEmpty()) listOf(child) else child.children }
        .forEach { subproject ->
            println("configure: " + subproject.name + ".gradle.kts")
            subproject.buildFileName = subproject.name + ".gradle.kts"
        }