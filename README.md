# Demo-skills-showcase

Demo project for skills demonstration \
**About project:** TV shows list type of website with complex backend. \
**Structure:**
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
* docker \
  Contains docker-compose run files for easy running 

**RUN GUIDE:** \
`cd docker` \
Start postgres and configured keycloak: `sudo docker-compose -f compose.yml up -d` \
Start migration service with environment variable: POSTGRES_SCHEMA=shows \
Start data service \
Start frontend service \
You're up, frontend address by default is: localhost:8081/ \
Backend swagger is available at localhost:8080/swagger-ui.html 

**Technology stack:**
* Framework: Spring boot 2
* Build: Maven
* ORM: Hibernate
* DB: postgres
* Docker
* SSO: Keycloak
* Mapping: Mapstruct
* Errors: zalando/problem
* Http client: feign
* Swagger: springdoc-openapi
* Front: thymeleaf, bootstrap
* Migrations: liquibase
* And probabbly something else I forgot

-----
**PICTURES:**\
![Main page](/pictures/1.jpg)

