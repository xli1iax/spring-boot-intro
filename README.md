# Online Book Store

## Project Overview

Online Book Store is a Spring Boot application for managing an online bookstore.  
The project is being developed step by step, with each stage introducing new functionality and improving the application architecture.

The final application is planned to support user authentication, book and category management, shopping carts, orders, and role-based functionality for shoppers and administrators.

## Domain Model

The application is designed around the following domain entities:

- **User** — stores registered user information, including authentication details and personal data.
- **Role** — represents a user's role in the system, such as `USER` or `ADMIN`.
- **Book** — represents a book available in the store.
- **Category** — represents a category to which books can belong.
- **ShoppingCart** — represents a user's shopping cart.
- **CartItem** — represents an individual item in a shopping cart.
- **Order** — represents an order placed by a user.
- **OrderItem** — represents an individual item included in an order.

## Planned Functionality

### Shopper

A shopper will be able to:

- register and sign in;
- browse the book catalog;
- view detailed information about a specific book;
- search for books by title;
- browse available categories;
- view books from a selected category;
- add books to the shopping cart;
- view and manage shopping cart contents;
- place an order for the books in the cart;
- view previous orders;
- inspect individual items from an order.

### Administrator

An administrator will be able to:

- add new books;
- update book information;
- remove books;
- create categories;
- update categories;
- remove categories;
- view orders;
- update order statuses, such as `SHIPPED` or `DELIVERED`.

---

# Development Progress

## 1. Project Infrastructure

The project infrastructure includes:

- **Java 17**
- **Spring Boot**
- **Maven**
- **Checkstyle**
- **GitHub Actions CI**

Checkstyle is configured through `checkstyle.xml` and runs during the Maven build.

GitHub Actions automatically runs the project verification process for pushes and pull requests using:

```bash
mvn --batch-mode --update-snapshots verify
```

Development changes are organized through separate feature branches and pull requests.

---

## 2. Initial Book Persistence Layer

The first implementation stage introduced the `Book` entity and the basic persistence and service layers.

### Book Entity

The `Book` entity contains the following fields:

| Field | Type | Constraints |
|---|---|---|
| `id` | `Long` | Primary key |
| `title` | `String` | Not null |
| `author` | `String` | Not null |
| `isbn` | `String` | Not null, unique |
| `price` | `BigDecimal` | Not null |
| `description` | `String` | Optional |
| `coverImage` | `String` | Optional |

### Repository Layer

The initial repository abstraction provided basic operations for storing and retrieving books:

```java
Book save(Book book);
List<Book> findAll();
```

It was implemented using `BookRepository` and `BookRepositoryImpl`.

### Service Layer

The service layer exposed the corresponding book operations:

```java
Book save(Book book);
List<Book> findAll();
```

The implementation was provided by `BookServiceImpl`.

### Initial Application Configuration

During the initial persistence stage, Hibernate was configured with:

```properties
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

A `CommandLineRunner` bean was used during the early development stage.

### Test Database

The application uses MySQL for the main database configuration and an in-memory **H2** database for tests and CI builds.

Test configuration:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

H2 is included as a test-scoped Maven dependency.

---

## 3. Spring Boot Web Layer

The next stage introduced a REST API for working with books.

### DTO Layer

The application uses separate DTOs for API communication:

- `BookDto` — response DTO;
- `CreateBookRequestDto` — request DTO.

Entity-to-DTO conversion is handled with **MapStruct**.

The service layer communicates through DTOs instead of exposing entities directly.

### Exception Handling

A custom `EntityNotFoundException` is used when a requested book does not exist.

The temporary `CommandLineRunner` used during the initial stage was removed after introducing the web layer.

### Book REST API

#### Get all books

```http
GET /api/books
```

Returns the book catalog.

Example response:

```json
[
  {
    "id": 1,
    "title": "Sample Book 1",
    "author": "Author A",
    "isbn": "9781234567897",
    "price": 19.99,
    "description": "This is a sample book description.",
    "coverImage": "http://example.com/cover1.jpg"
  },
  {
    "id": 2,
    "title": "Sample Book 2",
    "author": "Author B",
    "isbn": "9789876543210",
    "price": 24.99,
    "description": "Another sample book description.",
    "coverImage": "http://example.com/cover2.jpg"
  }
]
```

#### Get book by ID

```http
GET /api/books/{id}
```

Returns detailed information about a specific book.

Example response:

```json
{
  "id": 1,
  "title": "Sample Book 1",
  "author": "Author A",
  "isbn": "9781234567897",
  "price": 19.99,
  "description": "This is a sample book description.",
  "coverImage": "http://example.com/cover1.jpg"
}
```

#### Create a book

```http
POST /api/books
```

Creates a new book.

Example request:

```json
{
  "title": "Sample Book 3",
  "author": "Author C",
  "isbn": "9781122334455",
  "price": 29.99,
  "description": "Yet another sample book description.",
  "coverImage": "http://example.com/cover3.jpg"
}
```

### MapStruct and Checkstyle

MapStruct-generated sources are excluded from Checkstyle validation by limiting Checkstyle source directories to:

```xml
<sourceDirectories>src/main</sourceDirectories>
```

The Maven compiler is configured with annotation processors for Lombok, Lombok-MapStruct binding, and MapStruct.

---

## 4. Spring Boot Data JPA — Current Development Stage

The current development stage focuses on replacing the custom repository implementation with **Spring Data JPA** and introducing database schema versioning.

Planned changes for this stage include:

- migrating `BookRepository` to `JpaRepository`;
- adding **Liquibase** for database schema management;
- changing Hibernate schema handling from automatic creation to validation;
- implementing soft deletion for books;
- extending `BookService` with update and delete operations;
- adding the remaining REST endpoints for book management.

Hibernate will use:

```properties
spring.jpa.hibernate.ddl-auto=validate
```

### Extended Book API

The existing endpoints remain available:

```http
GET /api/books
GET /api/books/{id}
POST /api/books
```

The Data JPA stage extends the API with the following operations.

#### Update a book

```http
PUT /api/books/{id}
```

Example request:

```json
{
  "title": "Updated Title",
  "author": "Updated Author",
  "isbn": "978-1234567890",
  "price": 19.99,
  "description": "Updated description",
  "coverImage": "https://example.com/updated-cover-image.jpg"
}
```

#### Delete a book

```http
DELETE /api/books/{id}
```

Book deletion is planned to use the **soft delete** approach so that deleted records remain stored in the database but are excluded from normal application queries.

---

## Technology Stack

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- H2
- Liquibase
- MapStruct
- Lombok
- Maven
- Checkstyle
- GitHub Actions
