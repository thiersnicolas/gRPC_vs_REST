# grpc-vs-rest

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./gradlew build
```
It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/grpc-vs-rest-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Related Guides

- Camel gRPC ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/grpc.html)): Expose gRPC endpoints and access external gRPC endpoints
- Camel Core ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/core.html)): Camel core functionality and basic Camel languages: Constant, ExchangeProperty, Header, Ref, Simple and Tokenize
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Camel Rest ([guide](https://camel.apache.org/camel-quarkus/latest/reference/extensions/rest.html)): Expose REST services and their OpenAPI Specification or call external REST services

## Provided Code

### gRPC

Create your first gRPC service

[Related guide section...](https://quarkus.io/guides/grpc-getting-started)

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)

## Docker
(windows)
add/uncomment application properties
quarkus.package.type=native
quarkus.native.container-build=true
quarkus.container-image.build=true
quarkus.container-image.image=quay.io/thiersnicolas/server:0.0.1-SNAPSHOT
and 
./gradlew :application_server:configuration:build -x test

or

(linux/mac)
./gradlew :application_server:configuration:build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.container-image.image=quay.io/hogent-nthiers/server:0.0.1-SNAPSHOT

## Deployment Openshift
download and use oc.exe: oc - OpenShift Command Line Interface (CLI)
docker push quay.io/thiersnicolas/server:$version
oc apply -f $PATH\application_server\configuration\metadata\deployment_config.yaml
oc apply -f $PATH\application_server\configuration\metadata\route_config.yaml
oc apply -f $PATH\application_server\configuration\metadata\service_config.yaml

###
