plugins {
  `java-platform`
}

javaPlatform {
  allowDependencies()
}
dependencies {
  api(platform("io.quarkus:quarkus-bom:${properties.get("quarkusVersion")}"))
//  api(platform("org.apache.camel.quarkus:camel-quarkus-bom:3.0.0-M1"))
}
