# Demo-skills-showcase

Demo project for skills demonstration \
**About project:** TV shows list type of website with complex backend (microservices architecture) \
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
    * ...
* docker \
  Contains docker-compose run files for easy running 

**RUN GUIDE:** \
`cd docker` \
Start postgres and configured keycloak: `sudo docker-compose -f compose.yml up -d` \
Start migration service with environment variable: POSTGRES_SCHEMA=shows \
Start data service \
Start frontend service \
You're up, frontend address by default is: localhost:8081/ \
Backend swagger is available at localhost:8080/swagger-ui/index.html \
Default pre configured users: `admin admin`; `user user`

**Technology stack:**
* Framework: Spring boot 2
* Build: Maven
* ORM: Hibernate
* DB: postgres
* Local run: Docker
* SSO: Keycloak
* Mapping: Mapstruct
* Errors: zalando/problem
* Http client: feign
* Swagger: springdoc-openapi
* Front: thymeleaf, bootstrap (pain in the ass, should've used separate front) 
* Migrations: liquibase
* And probabbly something else I forgot

-----
**PICTURES (some might be outdated but won't redo screenshots):**
| ![Main page](/pictures/1.jpg "Main page") | 
| :--: |
| *Main page* |
| ![Edit page](/pictures/2.jpg "Edit page") |
| *Edit page* |
| ![Users page](/pictures/3.jpg "Users page") |
| *Users page* |
| ![Users add show page](/pictures/4.jpg "Users add show page") |
| *Users add show page* |
| ![Swagger](/pictures/5.jpg "Swagger") |
| *Swagger* |
| ![Users added shows page](/pictures/6.jpg "Users added shows page") |
| *Users added shows page* |  