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
cd docker \
sudo docker-compose -f postgres.yml up -d \
Start migration service with environment variable: POSTGRES_SCHEMA=shows \
Start data service \
Start frontend service \
You're up, frontend address by default is: localhost:8081/ \
Backend swagger is available at localhost:8080/swagger-ui.html

-----
**PICTURES:**\
![Main page](/pictures/1.jpg)

