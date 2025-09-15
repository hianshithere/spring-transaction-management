# Hibernate Transaction Management

This project demonstrates key concepts in Hibernate transaction management, focusing on the use of proxies, AOP (Aspect-Oriented Programming), and transaction control mechanisms. It includes practical Java code examples and explanations to help you understand how these concepts are applied in real-world applications.

## Table of Contents

1. [Learning about Proxy](#learning-about-proxy)
2. [Learning about AOP](#learning-about-aop)
3. [Learning about Transaction](#learning-about-transaction)
4. [Learning about Transaction Propagation](#learning-about-transaction-propagation)
5. [Learning about Transaction Isolation](#learning-about-transaction-isolation)
6. [Learning about Transaction Rollback](#learning-about-transaction-rollback)
7. [Learning about Transaction ReadOnly](#learning-about-transaction-readonly)
8. [Learning about Transaction Timeout](#learning-about-transaction-timeout)
9. [Learning about Transaction SavePoint](#learning-about-transaction-savepoint)
10. [Learning about Transaction Event Listener](#learning-about-transaction-event-listener)

---

## Learning about Proxy

A **proxy** is an intermediary object that controls access to another object. In Hibernate and Spring, proxies are used to add extra behavior (like transaction management, logging, or security) around method calls.

**Benefits of using a proxy:**
- Adds functionality before/after calling the actual object (e.g., logging, security, transaction management).
- Can decide whether to call the actual object based on certain conditions.
- In transaction management, a proxy can start a transaction before calling the actual object and commit or rollback the transaction after the method call, depending on the outcome.

### How does Hibernate use proxies?
- Controls access to database entities and connections.
- Implements lazy loading by returning a proxy instead of the actual entity until it is needed.
- Manages transactions by wrapping method calls with transaction boundaries.

When a client interacts with a proxy, the proxy can transparently manage transactions, ensuring that operations are committed or rolled back as needed.

### Example: Proxy Pattern in Java

This project demonstrates the proxy pattern using a simple attendance system.

#### Key Classes
- **Attendance**: Represents a student's attendance record.
- **DailySession (interface)**: Declares the `attendSession()` method.
- **Student**: Implements `DailySession`, represents the main object.
- **StudentProxy**: Extends `Student`, acts as a proxy to control access based on attendance.
- **Teacher**: Acts as the client, interacts with the proxy.

#### Example Code

```java
// Attendance.java
public class Attendance {
    private Date date;
    private boolean isPresent;
    public Attendance(Date date, boolean isPresent) {
        this.date = date;
        this.isPresent = isPresent;
    }
    public boolean isPresent() {
        return isPresent;
    }
}

// DailySession.java
public interface DailySession {
    void attendSession();
}

// Student.java
public class Student implements DailySession {
    private Attendance attendance;
    public Student(Attendance attendance) { this.attendance = attendance; }
    @Override
    public void attendSession() {
        System.out.println("student attending the session..");
    }
    public Attendance getAttendance() { return attendance; }
}

// StudentProxy.java
public class StudentProxy extends Student {
    public StudentProxy(Attendance attendance) { super(attendance); }
    @Override
    public void attendSession() {
        if (!getAttendance().isPresent()) {
            throw new RuntimeException("Student is absent and session is denied for this student..");
        } else {
            super.attendSession();
        }
    }
}

// Teacher.java
public class Teacher {
    public static void main(String[] args) {
        DailySession session = new StudentProxy(new Attendance(new Date(), false));
        session.attendSession(); // Throws exception if student is absent
    }
}
```

**Explanation:**
The `Teacher` (client) interacts with the `StudentProxy` (proxy), which controls access to the `Student` (main object). The proxy checks attendance before allowing the session, demonstrating a protection proxy.

---

## Learning about AOP

**Aspect-Oriented Programming (AOP)** is a programming paradigm that allows you to separate cross-cutting concerns (like transactions, logging, or security) from your business logic. In Spring, AOP is implemented using proxies that wrap your beans and add extra behavior before, after, or around method calls.

- **Proxy and AOP**: Proxy is a foundational concept for AOP. In AOP, cross-cutting concerns are implemented using proxies that wrap the main business logic.
- **Spring AOP**: Uses dynamic proxies (JDK or CGLIB) to apply aspects (like `@Transactional`) to beans.

---

## Learning about Transaction

A **transaction** is a unit of work that is either fully completed or not executed at all. In database systems, transactions ensure data integrity and consistency.

- **ACID Properties**: Atomicity, Consistency, Isolation, Durability.
- **Spring @Transactional**: The `@Transactional` annotation in Spring marks a method or class as transactional. Spring manages the transaction boundaries using proxies and AOP.

---

## Learning about Transaction Propagation

**Propagation** defines how transactions relate when calling methods from other transactional contexts. Spring supports several propagation behaviors:
- `REQUIRED` (default): Join existing or create new transaction.
- `REQUIRES_NEW`: Always create a new transaction.
- `SUPPORTS`: Join if exists, else non-transactional.
- `MANDATORY`, `NESTED`, `NOT_SUPPORTED`, `NEVER`.

**Example:**
```java
@Transactional(propagation = Propagation.REQUIRES_NEW)
public void saveData() { ... }
```

---

## Learning about Transaction Isolation

**Isolation** controls how transaction integrity is visible to other transactions. Common levels:
- `READ_UNCOMMITTED`
- `READ_COMMITTED`
- `REPEATABLE_READ`
- `SERIALIZABLE`

**Example:**
```java
@Transactional(isolation = Isolation.SERIALIZABLE)
public void process() { ... }
```

---

## Learning about Transaction Rollback

**Rollback** reverts changes if an error occurs. By default, Spring rolls back on unchecked exceptions.

**Example:**
```java
@Transactional(rollbackFor = Exception.class)
public void update() { ... }
```

---

## Learning about Transaction ReadOnly

**ReadOnly** optimizes transactions that do not modify data. It can help the database optimize queries.

**Example:**
```java
@Transactional(readOnly = true)
public List<Product> getProducts() { ... }
```

---

## Learning about Transaction Timeout

**Timeout** limits the duration of a transaction. If the transaction takes longer, it is rolled back.

**Example:**
```java
@Transactional(timeout = 5) // seconds
public void longRunningTask() { ... }
```

---

## Learning about Transaction SavePoint

**SavePoint** allows partial rollbacks within a transaction. In Spring, you can use `TransactionStatus` to create and rollback to savepoints programmatically.

**Example:**
```java
TransactionStatus status = transactionManager.getTransaction(def);
Object savepoint = status.createSavepoint();
// ...
status.rollbackToSavepoint(savepoint);
```

---

## Learning about Transaction Event Listener

**Event Listeners** can hook into transaction lifecycle events (commit, rollback, etc.). In Spring, you can use `@TransactionalEventListener`.

**Example:**
```java
@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
public void handleEvent(MyEvent event) { ... }
```

---

## Summary
- Proxies are essential for adding cross-cutting concerns like transaction management.
- Hibernate uses proxies for lazy loading and transaction boundaries.
- The proxy pattern is demonstrated in this project using a student attendance example.
- Spring AOP and `@Transactional` use proxies to manage transactions transparently.

---

## References
- [Hibernate Documentation](https://hibernate.org/orm/documentation/)
- [Spring Transaction Management](https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#transaction)
- [Proxy Pattern - Wikipedia](https://en.wikipedia.org/wiki/Proxy_pattern)
- [Spring AOP Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
