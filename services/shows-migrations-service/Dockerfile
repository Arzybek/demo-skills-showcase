FROM openjdk:17 as build
WORKDIR /app
COPY ../../ ./
RUN ./mvnw clean install

FROM openjdk:17.0.2-jdk-slim
WORKDIR /app
COPY --from=build /app/services/shows-migrations-service/target/shows-migrations-service-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "shows-migrations-service-0.0.1-SNAPSHOT.jar"]
