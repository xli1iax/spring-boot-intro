# Online Book Store API

A Spring Boot backend project for an online book store.

The application is being developed step by step as part of the Mate Academy Java Developer Program.  
At the current stage, the project includes the `Book` domain model, persistence layer, service layer, DTOs, MapStruct mapping, custom exception handling, REST endpoints, database configuration, Checkstyle, and GitHub Actions CI.

## Implemented Features

- Book entity with database mapping
- Book repository layer
- Book service layer
- REST controller for book operations
- Request and response DTOs
- MapStruct-based object mapping
- Custom `EntityNotFoundException`
- MySQL database configuration
- H2 in-memory database for tests
- Maven Checkstyle integration
- GitHub Actions CI
- Git branch and pull request workflow

## Technologies

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- MySQL
- H2 Database
- MapStruct
- Lombok
- Maven
- JUnit
- Checkstyle
- GitHub Actions
- REST API

## Current Domain Model

The current version implements the `Book` entity.

### Book Fields

| Field | Type | Description |
|---|---|---|
| `id` | `Long` | Primary key |
| `title` | `String` | Book title, required |
| `author` | `String` | Book author, required |
| `isbn` | `String` | Unique ISBN, required |
| `price` | `BigDecimal` | Book price, required |
| `description` | `String` | Book description |
| `coverImage` | `String` | URL of the cover image |

## Architecture

The application follows a layered architecture:

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
````

### Project Layers

* `controller` — handles HTTP requests and responses
* `service` — contains application logic
* `repository` — provides database access
* `model` — contains JPA entities
* `dto` — contains request and response DTOs
* `mapper` — maps entities to DTOs and DTOs to entities
* `exception` — contains custom exceptions
* `config` — contains mapper and application configuration

## DTOs

The application uses DTOs instead of exposing entities directly.

### `BookDto`

Used as a response DTO.

Contains:

* `id`
* `title`
* `author`
* `isbn`
* `price`
* `description`
* `coverImage`

### `CreateBookRequestDto`

Used as a request DTO when creating a new book.

Contains:

* `title`
* `author`
* `isbn`
* `price`
* `description`
* `coverImage`

## Mapping

MapStruct is used to convert between:

```text
Book ↔ BookDto
CreateBookRequestDto → Book
```

The mapper layer separates API data models from persistence entities.

## API Endpoints

### Retrieve Book Catalog

```http
GET /api/books
```

Returns a list of all books.

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

### Retrieve Book by ID

```http
GET /api/books/{id}
```

Returns details of a specific book.

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

If the requested book does not exist, the service throws `EntityNotFoundException`.

### Create a New Book

```http
POST /api/books
```

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

Example response:

```json
{
  "id": 3,
  "title": "Sample Book 3",
  "author": "Author C",
  "isbn": "9781122334455",
  "price": 29.99,
  "description": "Yet another sample book description.",
  "coverImage": "http://example.com/cover3.jpg"
}
```

## Repository Layer

The repository layer provides access to book data.

### `BookRepository`

Implemented methods:

```java
Book save(Book book);

List<Book> findAll();

Optional<Book> findById(Long id);
```

### `BookRepositoryImpl`

Provides the repository implementation for book persistence.

## Service Layer

The service layer works with DTOs.

### `BookService`

Implemented methods:

```java
BookDto save(CreateBookRequestDto bookDto);

List<BookDto> findAll();

BookDto findById(Long id);
```

### `BookServiceImpl`

Responsibilities:

* converts request DTOs into entities
* saves books using the repository
* converts entities into response DTOs
* retrieves the complete book catalog
* retrieves a book by ID
* throws `EntityNotFoundException` when a book is not found

## Database Configuration

The application uses MySQL as the main database.

Important JPA properties:

```properties
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
```

## Test Database

The test environment uses an H2 in-memory database.

Example configuration:

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

## Code Quality

The project uses Maven Checkstyle.

The Checkstyle configuration is executed during the Maven compile phase.

Generated MapStruct classes are excluded from unnecessary Checkstyle processing by limiting source directories to:

```xml
<sourceDirectories>src/main</sourceDirectories>
```

## Continuous Integration

GitHub Actions is configured in:

```text
.github/workflows/ci.yml
```

The CI workflow runs on every:

* push
* pull request

The workflow:

* checks out the repository
* configures JDK 17
* restores the Maven cache
* runs Maven verification

```bash
mvn --batch-mode --update-snapshots verify
```

## Git Workflow

The project follows a branch-based Git workflow:

1. Create a new branch from `master`
2. Implement one homework task
3. Commit the changes
4. Push the branch
5. Open a pull request
6. Merge only after mentor approval
7. Delete the branch after merging

Each homework assignment is implemented in a separate pull request.

## Running the Application

### Prerequisites

* Java 17
* Maven
* MySQL

### Clone the Repository

```bash
git clone https://github.com/xli1iax/spring-boot-intro.git
cd spring-boot-intro
```

### Configure the Database

Update the database connection properties in:

```text
src/main/resources/application.properties
```

### Run the Application

```bash
mvn spring-boot:run
```

The application will start on:

```text
http://localhost:8080
```

## Running Tests

```bash
mvn test
```

## Future Development

The project is planned to grow into a complete online book store with the following entities:

* User
* Role
* Book
* Category
* ShoppingCart
* CartItem
* Order
* OrderItem

Planned functionality includes:

* registration and authentication
* book search
* category management
* shopping cart
* order creation
* order history
* admin book management
* admin category management
* order status management

## What I Practiced

During this project, I practiced:

* building REST APIs with Spring Boot
* applying layered architecture
* implementing repository and service patterns
* working with Spring Data JPA and Hibernate
* mapping entities and DTOs with MapStruct
* using Lombok
* handling missing data with custom exceptions
* configuring MySQL and H2 databases
* configuring Maven plugins
* applying Checkstyle
* setting up GitHub Actions CI
* working with feature branches and pull requests


