# Movie Application - Java Spring Boot

This is a simple application to manage movies and users. It is a Java Spring Boot application with a PostgreSQL
database.
This application was built as part of final project at EPITA.

## Requirements

- Java 11
- Maven
- PostgreSQL

## Installation

- Clone the repository
- Create a database named `movies` in PostgreSQL
- Run the following command to create the tables in the database:

```bash
mvn spring-boot:run -Dspring-boot.run.arguments=--spring.datasource.initialization-mode=always
```

- Run the application with the following command:

```bash
mvn spring-boot:run
```

## Usage

- The API is accessible at `http://localhost:8080`

## API

### Movies

- `POST /movies` - Create a movie

### Roles

- `GET /roles` - Get all roles

### Users

- `GET /users/info?username={username}` - Get user info

### Accounts

- `POST /accounts/create` - Create an account

### Seen Movies

- `GET /seenmovies` - Get all seen movies
- `POST /seenmovies` - Create a seen movie
- `GET /seenmovies/top` - Get top seen movies

## Authors

[Rishab Gaddi](https://rishabgaddi.github.io/)