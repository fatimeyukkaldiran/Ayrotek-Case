# Ayrotek-Case


### Tech Stack

---
- Java 17
- Spring Boot
- Spring Data JPA
- MongoDB
- Maven 
- Restful API
- Swagger documentation
- MySql database
- JUnit 5


### Prerequisites

---
- Maven
- Docker Desktop

### Run & Build

---
run & build the application.

#### Maven

*$PORT: 8080*
```ssh
$ cd ayrotek/ayrotek-backend-challange
$ mvn clean install
$ mvn spring-boot:run
```
*$PORT: 8082*
```ssh
$ cd ayrotek/tax-calculator-service
$ mvn clean install
$ mvn spring-boot:run
```
Run the following command in the project folder you cloned to your local:

$ docker-compose up

### Swagger UI will be run on this url
`http://localhost:${8080}/swagger-ui.html`
