# TaskFlow API 

TaskFlow API is a robust, production-ready backend service built with **Java** and **Spring Boot** and **Spring Data JPA**. It is designed to manage users and their assigned tasks within a fast-paced environment. 

---

## Architectures

The application follows a strict **Layered Architecture (Three-Tier Architecture)**.

* **Controller Layer (`@RestController`):** Handles incoming HTTP requests, validates input data using DTOs, and returns proper HTTP status codes.
* **Service Layer (`@Service`):** Contains the core business logic and coordinates data flow between controllers and repositories.
* **Repository Layer (`@Repository`):** Manages direct database communication via Spring Data JPA.
* **Data Transfer Objects (DTOs):** Implemented to decouple the API exposure layer from the internal Database Entities, ensuring data security and encapsulation.

---

## Tech Stack

* **Language:** Java 26
* **Framework:** Spring Boot 4.0.6 (Spring Web, Spring Data JPA, H2 Datanase and Lombock).
* **Database:** H2 Database (In-Memory SQL)
* **Build Tool:** Maven
* **Testing:** JUnit 5, Mockito
---

## Project Structure

```text
src/main/java/com/unosq/taskflow/
│
├── controllers/     # REST Endpoints (API entry points)
├── services/        # Business Logic 
├── repositories/    # Database Queries (JPA / SQL)
├── entities/        # Database Models (User, Task)
└── dtos/            # Data Transfer Objects
