# Clean Architecture SpringBoot

This repository contains a **Clean Architecture**-based user management project organized as a Maven multi-module service.
I've designed `user-management-service` with microservice architecture in mind.
In other words, it's a small service that has one duty: manage users/ roles/ authenticate them

## Table of Contents
  - [Project Structure](#project-structure)
  - [Clean Architecture Overview](#clean-architecture-overview)
  - [Module Documentation](#module-documentation)
    - [1) `domain`](#1-domain)
    - [2) `application`](#2-application)
    - [3) `infrastructure`](#3-infrastructure)
    - [4) `presentation`](#4-presentation)
  - [Parent Module (`user-management-service`)](#parent-module-user-management-service)
  - [Build and Run](#build-and-run)
    - [Build all modules](#build-all-modules)
    - [Run tests](#run-tests)
    - [Run the application (common patterns)](#run-the-application-common-patterns)
  - [API Request Collection](#api-request-collection)
  - [Suggested Development Flow](#suggested-development-flow)
## Project Structure

```text
Clean Architecture Springboot/
├── readme.md
├── requests/
│   └── user-management-api/
│       ├── add-user.yaml
│       ├── archive-user-by-cin.yaml
│       ├── get-users.yaml
│       └── update-user-by-cin.yaml
└── user-management-service/
    ├── pom.xml
    ├── notes.md
    ├── todo.txt
    ├── domain/
    ├── application/
    ├── infrastructure/
    └── presentation/
```
Note that `user-management-api` has yaml configuration for api requests. 
These are **only** used by [ApiArk](https://github.com/berbicanes/apiark) which is a FOSS alternative to Postman.

## Clean Architecture Overview
![clean architecture image](https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

This service follows a layered architecture where dependencies point inward:

- `domain`: It holds the entities. `User` and `Role`
- `application` (use cases): Defines operations and the business logic. In short, these are the use cases within the use case diagram 
- `infrastructure` (interface adapters): It implements adapters used within iur business logic.
- `presentation` (framework): It's the framework layer. Can be swapped with any presentation layer (Spring, Quarkus, CLI, etc)


## Module Documentation

### 1) `domain`

Path: `user-management-service/domain`

Purpose:
- Contains enterprise/business entities and core domain concepts.
- Represents pure business logic and rules that should not depend on frameworks.

From your compiled classes, this module includes domain concepts like:
- `User`
- `Role`
- `UserState`

Typical responsibilities:
- Domain entities/value objects
- Domain-level invariants
- Business-centric enums/models

### 2) `application`

Path: `user-management-service/application`

Purpose:
- Implements use cases and application orchestration.
- Defines contracts (ports) that's used by use cases. Input ports (boundaries) are implemented within `application`, 
whereas output ports like [`UserPersistenceAdapter`](user-management-service/application/src/main/java/com/ums/application/ports/UserPersistenceAdapter.java) are implemented within `infrastructure`

Observed package intent (from compiled output):
- `ports/` for interfaces (input/output boundaries)
- `user/`, `role/` for use-case organization
- `auth/` for authentication-related application workflows (Work In Progress)
- `exception/`, `modal/` for application-level exceptions/models

Typical responsibilities:
- Use case services
- Input/output port definitions
- Transactional orchestration of domain behavior
- Application DTOs and errors

### 3) `infrastructure`

Path: `user-management-service/infrastructure`

Purpose:
- Provides technical implementations for the contracts defined in `application`.
- Integrates external systems (database, persistence, framework adapters).

Observed resources:
- `application.properties`
- `import.sql`: After starting ORM and creating tables, it runs the `INSERT` queries within the file

Typical responsibilities:
- Repository implementations
- JPA/Hibernate entities and mappings
- Database configuration and data seeding
- External integrations (security providers, messaging, etc.)

### 4) `presentation`

Path: `user-management-service/presentation`

Purpose:
- Exposes the system to clients (HTTP API/controllers).
- Handles request/response mapping and delegates to application use cases.

Typical responsibilities:
- REST controllers
- Request validation
- Response DTO mapping
- Exception-to-HTTP translation

## Parent Module (`user-management-service`)

Path: `user-management-service/pom.xml`

This is the aggregator/build entry for all service modules:
- `domain`
- `application`
- `infrastructure`
- `presentation`

Use this level for full project builds and tests.

## Build and Run

The exact runnable Spring Boot main class is not shown in the provided context.
The commands below are standard Maven multi-module commands and may need adaptation depending on where your boot entry is configured.

### Build all modules

```bash
cd /home/yassine/Documents/Projects/Clean-Architecture-test/user-management-service
mvn clean install
```

### Run tests

```bash
cd /home/yassine/Documents/Projects/Clean-Architecture-test/user-management-service
mvn test
```

### Run the application (common patterns)

If `presentation` is your boot entry module:

```bash
cd /home/yassine/Documents/Projects/Clean-Architecture-test/user-management-service
mvn -pl presentation spring-boot:run
```

If the boot plugin is configured at parent level:

```bash
cd /home/yassine/Documents/Projects/Clean-Architecture-test/user-management-service
mvn spring-boot:run
```

## API Request Collection

Path: `requests/user-management-api`

This folder contains request definitions for key user-management operations:

- `add-user.yaml`
- `get-users.yaml`
- `update-user-by-cin.yaml`
- `archive-user-by-cin.yaml`

Use these files as ready-to-run request templates in your API client workflow (or as endpoint behavior references during development/testing).

## Suggested Development Flow

1. Model business rules in `domain`.
2. Implement or adjust use cases and ports in `application`.
3. Implement adapters in `infrastructure`.
4. Expose or adjust endpoints in `presentation`.
5. Verify behavior with tests and the request files under `requests/user-management-api`.
