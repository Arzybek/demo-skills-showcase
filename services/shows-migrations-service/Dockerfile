FROM openjdk:17
COPY target/shows-migrations-service-0.0.1-SNAPSHOT.jar app.jar
ENV POSTGRES_SCHEMA "shows"
CMD ["java","-jar","/app.jar"]