# Demo-skills-showcase

Demo project for skills demonstration \
**About project:** TV shows list type of website with complex backend (microservices architecture) \
**RUN GUIDE:** \
`cd docker` \
Start postgres and configured keycloak: `sudo docker-compose -f compose.yml up -d` \
Start migration service with environment variable: POSTGRES_SCHEMA=shows \
Start data service \
Start frontend service \
You're up, frontend address by default is: localhost:8081/ \
Backend swagger is available at localhost:8080/swagger-ui/index.html \
Default pre configured users: `admin admin`; `user user`  

-----
**PICTURES (some might be outdated):**
| ![Main page (admin)](/pictures/1.jpg "Main page (admin)") | 
| :--: |
| *Main page (admin)* |
| ![Edit page (admin)](/pictures/2.jpg "Edit page (admin)") |
| *Edit page (admin)* |
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

-----
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