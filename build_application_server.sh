#!/bin/sh
VERSION="0.0.1-SNAPSHOT"
./gradlew :build -x test
#./gradlew :application_server:configuration:build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true -Dquarkus.container-image.build=true -Dquarkus.container-image.image=quay.io/hogent-nthiers/server:$VERSION
./gradlew :application_server:configuration:build -x test -Dquarkus.package.type=native -Dquarkus.native.container-build=true