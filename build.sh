#!/bin/bash
docker build -t build-jars-inside-docker-image .
mkdir ./target
docker create -it --name build-jars-inside-docker-cont build-jars-inside-docker-image bash
docker cp build-jars-inside-docker-cont:services/shows-migrations-service/target/shows-migrations-service-0.0.1-SNAPSHOT.jar ./target/
docker cp build-jars-inside-docker-cont:services/shows-frontend-service/target/shows-frontend-service-0.0.1-SNAPSHOT.jar ./target/
docker cp build-jars-inside-docker-cont:services/shows-data-service/target/shows-data-service-0.0.1-SNAPSHOT.jar ./target/
docker ps
docker images
docker rm -f build-jars-inside-docker-cont
docker rmi build-jars-inside-docker-image