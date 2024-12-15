# Employee Management System

## Overview

This project consists of two Spring Boot applications, App1 and App2, to manage employee data. App2 handles database operations, while App1 exposes REST endpoints to receive requests from external consumers. App1 connects to App2 via REST.

## Architecture

- **App1**: Employee Service
    - Exposes REST endpoints for managing employees.
    - Implements role-based access control.
    - Uses Spring Retry for retry mechanisms.
    - Transforms data using MapStruct.
    - Secured with Spring Security.

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

App1: Employee Service

Create Employee

Endpoint: POST /employees
Request Header: Role: ADMIN
Request Body:
{
  "firstName": "John",
  "surname": "Doe",
  "role": "ADMIN"
}
Response:
{
  "name": "John Doe",
  "roleId": 1
}
Get Employee by ID
Endpoint: GET /employees/{id}
Response:
{
  "name": "John Doe",
  "roleId": 1
}
Update Employee
Endpoint: PUT /employees/{id}
Request Header: Role: USER
Request Body:
{
  "firstName": "Jane",
  "surname": "Doe",
  "role": "USER"
}
Response:
{
  "name": "Jane Doe",
  "roleId": 2
}
Delete Employee
Endpoint: DELETE /employees/{id}
Request Header: Role: ADMIN
Response:
{
  "message": "Employee deleted successfully"
}


Usage Examples

Using Postman

Create Employee

Method: POST
URL: http://localhost:8080/employees
Headers: Role: ADMIN
Body:
{
  "firstName": "John",
  "surname": "Doe",
  "role": "ADMIN"
}
Get Employee by ID

Method: GET
URL: http://localhost:8080/employees/1
Update Employee

Method: PUT
URL: http://localhost:8080/employees/1
Headers: Role: USER
Body:
{
  "firstName": "Jane",
  "surname": "Doe",
  "role": "USER"
}
Delete Employee

Method: DELETE
URL: http://localhost:8080/employees/1
Headers: Role: ADMIN

### Running Tests

To run the tests, use the following command:

mvn test

### Swagger Documentation

Swagger is used for API documentation. Once the applications are running, you can access the Swagger UI at:

App1: http://localhost:8080/swagger-ui.html
App2: http://localhost:8081/swagger-ui.html