FROM maven:3.8-openjdk-17
COPY . .
RUN mvn clean package