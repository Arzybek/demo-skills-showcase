FROM openjdk:17 as build
WORKDIR /app
COPY ../../ ./
RUN ./mvnw clean install .

FROM openjdk:17.0.2-jdk-slim
WORKDIR /app
COPY --from=build /app/services/shows-data-service/target/shows-data-service-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "shows-data-service-0.0.1-SNAPSHOT.jar"]
