# Demo-skills-showcase

Demo project for skills demonstration \
**About project:** TV shows list type of website with complex backend (microservices architecture) \
**RUN GUIDE:** \
***Auto***:
- `./mvnw clean install` in root folder
- `cd docker` and start postgres, configured keycloak and services: `sudo docker-compose -f compose.yml up -d`
<details>
<summary><b>Manual:</b></summary>

- `cd docker`
- Start postgres and configured keycloak: `sudo docker-compose -f compose_old.yml up -d` 
- `mvn clean install` in root folder and `cd services/*-service/target` and `java
  -jar *.jar` or use IDE to start services 
- 1. Start migration service with environment variable: POSTGRES_SCHEMA=shows; 
  2. Start data service
  3. Start frontend service; 
</details>

You're up, frontend address by default is: localhost:8081/ \
Backend swagger is available at localhost:8080/swagger-ui/index.html \
Actuator is available at localhost:8080/actuator/ \
Default pre configured users: `admin admin`; `user user`

-----
<details>
<summary><b>SCREENSHOTS:</b></summary>

| ![Main page (admin)](/pictures/1.jpg "Main page (admin)") |
| :--: |
| *Main page (admin)* |
| ![Edit page (admin)](/pictures/2.jpg "Edit page (admin)") |
| *Edit page (admin)* |
| ![Show info page](/pictures/8.jpg "Show info page") |
| *Show info page* |
| ![Users page](/pictures/3.jpg "Users page") |
| *Users page* |
| ![Users add show page](/pictures/4.jpg "Users add show page") |
| *Users add show page* |
| ![Users added shows page](/pictures/6.jpg "Users added shows page") |
| *Users added shows page* |  
| ![Logic error page](/pictures/7.jpg "Logic error page") |
| *Logic error page* |
| ![Swagger](/pictures/5.jpg "Swagger") |
| *Swagger* |
</details>

-----
**Technology stack:**

* Framework: Spring boot 2
* Build: Maven
* ORM: Hibernate
* DB: postgres
* Containers: Docker, Docker-compose
* SSO: Keycloak
* Mapping: Mapstruct
* Errors: zalando/problem
* Http client: feign
* Swagger: springdoc-openapi
* Front: thymeleaf, bootstrap (pain in the ass, should've used separate front)
* Migrations: liquibase
* Utils: Logback, Actuator
* And probabbly something else I forgot

<details>
<summary><b>Structure:</b></summary>

* services\
  Contains backend services
    * Migration service \
      Service for database migrations with Liquibase
    * Shows data service \
      Service with API to access data about TV-Shows
    * Frontend service \
      Frontend service, using thymeleaf
* common \
  Contains common shared configurations, sources and e.t.c.
    * data
    * enums
    * ...
* docker \
  Contains docker-compose run files for easy running  
</details>
