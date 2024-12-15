# Employee Management System

## Overview

This project consists of two Spring Boot applications, App1 and App2, to manage employee data. App2 handles database operations, while App1 exposes REST endpoints to receive requests from external consumers. App1 connects to App2 via REST.

## Architecture

- **App2**: Employee Database
    - Manages employee data using Spring Data JPA.
    - Uses H2 database.
    - Exposes REST endpoints for database operations.

## Prerequisites

- Java 17
- Maven
- Docker (for containerization)


### Clone the Repository

cd employee-management-system
Build the Applications
mvn clean install

### Run the Applications

### Using Docker

Build Docker Images

docker build -t app1 ./app1
docker build -t app2 ./app2

Run Docker Containers
docker run -d -p 8080:8080 --name app1 app1
docker run -d -p 8081:8081 --name app2 app2

### Without Docker

Run App2
cd app2
mvn spring-boot:run
Run App1
cd ../app1
mvn spring-boot:run


### API Documentation

App2: Employee Database
Create Employee
Endpoint: POST /api/employees
Request Body:
{
"name": "John Doe",
"roleId": 1
}
Response:
{
"id": 1,
"name": "John Doe",
"roleId": 1
}
Get Employee by ID
Endpoint: GET /api/employees/{id}
Response:
{
"id": 1,
"name": "John Doe",
"roleId": 1
}
Update Employee
Endpoint: PUT /api/employees/{id}
Request Body:
{
"name": "Jane Doe",
"roleId": 2
}
Response:
{
"id": 1,
"name": "Jane Doe",
"roleId": 2
}
Delete Employee
Endpoint: DELETE /api/employees/{id}
Response:
{
"message": "Employee deleted successfully"
}


### Running Tests

To run the tests, use the following command:

mvn test

### Swagger Documentation

Swagger is used for API documentation. Once the applications are running, you can access the Swagger UI at:

App1: http://localhost:8080/swagger-ui.html
App2: http://localhost:8081/swagger-ui.html