# 🏦 Bank Management System — Core JDBC

A lightweight **Java-based Bank Management System** built using **Core JDBC and MySQL** to demonstrate database connectivity and CRUD operations without using an ORM framework.

This project was converted from a Hibernate-based implementation to **pure JDBC** to understand how Java communicates with relational databases at a lower level.

---

## 📌 Project Overview

The application provides basic bank account management functionality using JDBC:

- **Create** — Add a new bank account
- **Read** — Find an account by account number or view all accounts
- **Update** — Update an account's balance
- **Delete** — Remove an account from the database

---

## 🚀 Features

- MySQL database integration
- Complete CRUD operations
- `PreparedStatement` for parameterized SQL queries
- `ResultSet` for retrieving database records
- Try-with-resources for automatic resource management
- Centralized database connection utility
- Clean separation between model, database connection, service, and application entry point

---

## 🛠️ Tech Stack

| Technology | Used For |
|---|---|
| Java | Application development |
| Core JDBC | Database interaction |
| MySQL 8.x | Relational database |
| Maven | Dependency management |
| Git & GitHub | Version control |

**Recommended:** JDK 17+

---

## 📂 Project Structure

```text
Bank-JDBC-Project/
│
├── pom.xml
│
└── src/
    └── main/
        └── java/
            └── com/
                └── example/
                    └── bank/
                        ├── Account.java
                        ├── DBConnection.java
                        ├── AccountService.java
                        └── Main.java
```

### Class Responsibilities

- **Account.java** — Represents the bank account model/POJO
- **DBConnection.java** — Provides a reusable JDBC database connection
- **AccountService.java** — Contains CRUD operations and database logic
- **Main.java** — Application entry point and demonstration

---

## 🗄️ Database Setup

Create the database and table using MySQL:

```sql
CREATE DATABASE IF NOT EXISTS bank_db;

USE bank_db;

CREATE TABLE IF NOT EXISTS accounts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(30) UNIQUE NOT NULL,
    holder_name VARCHAR(100) NOT NULL,
    balance DECIMAL(15,2) NOT NULL
);
```

---

## ⚙️ Configuration

Open:

```text
src/main/java/com/example/bank/DBConnection.java
```

Configure your MySQL credentials:

```java
private static final String URL =
        "jdbc:mysql://localhost:3306/bank_db";

private static final String USER = "root";

private static final String PASSWORD = "your_password";
```


---

## ▶️ How to Run

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/bank-jdbc-project.git

cd bank-jdbc-project
```

### 2. Configure MySQL

Make sure MySQL Server is running and execute the database setup SQL given above.

### 3. Build the Project

```bash
mvn clean compile
```

### 4. Run the Application

```bash
mvn exec:java -Dexec.mainClass="com.example.bank.Main"
```

Alternatively, open the project in **IntelliJ IDEA, Eclipse, or VS Code** and run `Main.java`.

---

## 🔄 Application Flow

```text
Main
  ↓
AccountService
  ↓
DBConnection
  ↓
JDBC API
  ↓
MySQL Database
```

The application sends SQL queries through JDBC, receives the database response, and maps the result into Java objects.

---

## 🧠 Key JDBC Concepts Learned

### 1. Database Connection

Used `DriverManager.getConnection()` to establish a connection between Java and MySQL.

### 2. PreparedStatement

Used parameterized SQL queries instead of directly concatenating user input.

```java
PreparedStatement ps =
    connection.prepareStatement(
        "SELECT * FROM accounts WHERE account_number = ?"
    );
```

This helps prevent **SQL Injection** and provides safer query execution.

### 3. executeQuery()

Used for `SELECT` operations that return a `ResultSet`.

```java
ResultSet rs = ps.executeQuery();
```

### 4. executeUpdate()

Used for `INSERT`, `UPDATE`, and `DELETE` operations.

```java
int rows = ps.executeUpdate();
```

### 5. ResultSet

Used `ResultSet` to read data returned by the database.

```java
while (rs.next()) {
    String accountNumber = rs.getString("account_number");
    double balance = rs.getDouble("balance");
}
```

### 6. Try-with-Resources

Used try-with-resources to automatically close JDBC resources such as:

- `Connection`
- `PreparedStatement`
- `ResultSet`

This helps prevent resource leaks and keeps database operations clean.

---

## 🔍 JDBC vs Hibernate/JPA

This project helped me understand the difference between **low-level JDBC database interaction** and **ORM-based database interaction**.

### JDBC

```text
Java
 ↓
JDBC
 ↓
SQL Query
 ↓
MySQL
```

With JDBC, SQL queries, connections, statements, and result mapping are handled explicitly.

### Hibernate/JPA

```text
Java Entity
 ↓
JPA / Hibernate
 ↓
Generated SQL
 ↓
MySQL
```

Hibernate provides ORM abstractions that reduce the amount of SQL and database-handling code developers need to write manually.

---

## 📚 What This Project Demonstrates

By building this project, I practiced:

- Core Java
- JDBC
- MySQL
- SQL CRUD operations
- PreparedStatement
- ResultSet
- Exception handling
- Try-with-resources
- Maven
- Git & GitHub
- Basic application architecture
- JDBC vs Hibernate/ORM concepts

---

## 🔮 Future Improvements

Possible future enhancements:

- Add transaction management
- Add input validation
- Add exception handling with custom exceptions
- Add a proper layered architecture
- Add logging
- Add unit testing
- Convert the application into a Spring Boot REST API
- Add authentication and authorization

---

## 📄 License

This project is open-source and available for learning and educational purposes.
