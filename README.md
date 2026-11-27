# Spring Boot Multi-Datasource Embedded Database

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-8-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.3.0-6DB33F.svg)](https://spring.io/projects/spring-boot)

A Spring Boot and Spring Data JPA sample project demonstrating how to configure and run multiple embedded SQL datasources side by side—**Apache Derby**, **H2**, **HyperSQL (HSQLDB)**, and **SQLite**—each with isolated HikariCP connection pools, Hibernate `EntityManagerFactory` beans, transaction managers, and REST API endpoints.

## Key Features

- **Four Embedded SQL Datasources**: Simultaneously configures file-backed Apache Derby (`@Primary`), H2, HSQLDB, and SQLite databases using HikariCP.
- **Isolated Spring Data JPA Configuration**: Dedicated `@EnableJpaRepositories`, `LocalContainerEntityManagerFactoryBean`, `JdbcTemplate`, and `PlatformTransactionManager` beans per database package.
- **Custom Hibernate SQLite Dialect**: Includes a custom `SQLiteDialect` and `SQLiteIdentityColumnSupport` implementation so Hibernate/JPA can persist entities to SQLite.
- **Per-Datasource Schema Configuration**: Independent `hibernate.hbm2ddl.auto` properties for `derby`, `h2`, `hsql`, and `sqlite` defined in `application.yml`.
- **Shared JPA Domain Model**: Uses a common `@MappedSuperclass` (`OrderEntity`) and `@Embeddable` (`Address`) extended by per-database entities (`DerbyOrder`, `H2Order`, `HsqlOrder`, `SqliteOrder`).
- **REST Endpoints & H2 Console**: Exposes seed (`/dummy`) and query (`/orders`) endpoints for every embedded database, with the H2 web console enabled.

## Tech Stack

- **Language**: Java 8
- **Framework**: Spring Boot 2.3.0.RELEASE (Spring Web, Spring Data JPA, Spring Data JDBC)
- **ORM & Pooling**: Hibernate, HikariCP
- **Embedded Databases**: Apache Derby, H2 Database, HSQLDB (HyperSQL), SQLite (`org.xerial:sqlite-jdbc`)
- **Build Tool**: Gradle 6.4.1 (Gradle Wrapper included)

## Project Structure

```text
.
├── build.gradle
├── settings.gradle
├── gradlew / gradlew.bat
└── src/
    ├── main/
    │   ├── java/vn/foo/core/spring/
    │   │   ├── Application.java
    │   │   ├── components/
    │   │   │   ├── common/          # Shared OrderEntity (@MappedSuperclass) & Address (@Embeddable)
    │   │   │   ├── derby/order/     # DerbyOrder entity, DerbyOrderRepo, REST API (/derby)
    │   │   │   ├── h2/order/        # H2Order entity, H2OrderRepo, REST API (/h2)
    │   │   │   ├── hsql/order/      # HsqlOrder entity, HsqlOrderRepo, REST API (/hsql)
    │   │   │   └── sqlite/          # SqliteOrder entity, SqliteOrderRepo, REST API (/sqlite)
    │   │   ├── config/
    │   │   │   ├── derby/DerbyConfig.java
    │   │   │   ├── h2/H2Config.java
    │   │   │   ├── hsqldb/HsqlConfig.java
    │   │   │   ├── mysql/MySqlConfig.java
    │   │   │   └── sqlite/SQLiteConfig.java
    │   │   └── helper/
    │   │       ├── SQLiteDialect.java
    │   │       └── SQLiteIdentityColumnSupport.java
    │   └── resources/
    │       └── application.yml
    └── test/
        └── java/vn/foo/core/spring/ApplicationTests.java
```

## Prerequisites

- **JDK 8** (matching `sourceCompatibility = '1.8'` in `build.gradle`)
- **MySQL (optional/note)**: `MySqlConfig` defines an additional HikariCP `DataSource` bean pointing to `jdbc:mysql://localhost:3306/trackandtrace`. Ensure a matching local MySQL instance is available, or disable/update `MySqlConfig` if you only want to run the embedded databases.

## Getting Started

1. **Clone the repository**

   ```bash
   git clone https://github.com/sinhsac/spring-multi-datasource-embedded-database.git
   cd spring-multi-datasource-embedded-database
   ```

2. **Configure schema auto-generation (optional)**

   In [src/main/resources/application.yml](src/main/resources/application.yml), each datasource has a `hibernate.hbm2ddl.auto` setting (empty by default). To automatically create tables on first run, set them to `update`:

   ```yaml
   derby:
     hibernate:
       hbm2ddl:
         auto: update
   h2:
     hibernate:
       hbm2ddl:
         auto: update
   hsql:
     hibernate:
       hbm2ddl:
         auto: update
   sqlite:
     hibernate:
       hbm2ddl:
         auto: update
   ```

3. **Run the application**

   On Linux / macOS:

   ```bash
   ./gradlew bootRun
   ```

   On Windows:

   ```bat
   gradlew.bat bootRun
   ```

## Usage & API Endpoints

Once the server is running (default port `8080`), each embedded database exposes two endpoints: `/dummy` inserts 50 randomly generated orders and `/orders` returns all persisted records.

| Database | JDBC URL | Seed 50 Orders | Fetch All Orders |
| --- | --- | --- | --- |
| **Apache Derby** (`@Primary`) | `jdbc:derby:db/derby.dbfile;create=true` | `GET /derby/dummy` | `GET /derby/orders` |
| **H2** | `jdbc:h2:file:./db/h2db/db;MV_STORE=false` | `GET /h2/dummy` | `GET /h2/orders` |
| **HSQLDB (HyperSQL)** | `jdbc:hsqldb:file:db/hsqldb.db/foo-test` | `GET /hsql/dummy` | `GET /hsql/orders` |
| **SQLite** | `jdbc:sqlite:./db/sqlite.db` | `GET /sqlite/dummy` | `GET /sqlite/orders` |

Example requests with `curl`:

```bash
curl http://localhost:8080/h2/dummy
curl http://localhost:8080/h2/orders
```

Database files are created under the local `./db/` directory. The H2 console is enabled via `spring.h2.console.enabled: true` and accessible at `http://localhost:8080/h2-console`.

## References

- [Does hibernate fully support sqlite ?](https://stackoverflow.com/questions/17587753/does-hibernate-fully-support-sqlite)
- [SQLite dialect](https://github.com/gwenn/sqlite-dialect/)

## License

This project is licensed under the MIT License — see the [LICENSE](LICENSE) file for details.
