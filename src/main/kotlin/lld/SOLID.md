# SOLID Principles in Software Development

SOLID is an acronym for five design principles that help software developers write maintainable and scalable code. Below are definitions, violation examples, and correct implementations (ratifications) in Kotlin.

## 1. Single Responsibility Principle (SRP)
**Definition:** A class should have only one reason to change, meaning it should have only one job.

### Violation:
```kotlin
class ReportManager {
    fun generateReport() {
        // Logic to generate report
    }
    
    fun printReport() {
        // Logic to print report
    }
}
```
Here, `ReportManager` is handling both report generation and printing, violating SRP.

### Ratification:
```kotlin
class ReportGenerator {
    fun generateReport(): String {
        return "Generated Report"
    }
}

class ReportPrinter {
    fun printReport(report: String) {
        println(report)
    }
}
```
Now, `ReportGenerator` is responsible only for report creation, and `ReportPrinter` handles printing.

---
## 2. Open/Closed Principle (OCP)
**Definition:** A class should be open for extension but closed for modification.

### Violation:
```kotlin
class DiscountCalculator {
    fun calculateDiscount(type: String, price: Double): Double {
        return when (type) {
            "NewYear" -> price * 0.9
            "BlackFriday" -> price * 0.8
            else -> price
        }
    }
}
```
Adding a new discount type requires modifying `calculateDiscount`, violating OCP.

### Ratification:
```kotlin
interface Discount {
    fun apply(price: Double): Double
}

class NewYearDiscount : Discount {
    override fun apply(price: Double) = price * 0.9
}

class BlackFridayDiscount : Discount {
    override fun apply(price: Double) = price * 0.8
}

class DiscountCalculator {
    fun calculateDiscount(discount: Discount, price: Double): Double {
        return discount.apply(price)
    }
}
```
Now, we can add new discounts without modifying existing code.

---
## 3. Liskov Substitution Principle (LSP)
**Definition:** Objects of a superclass should be replaceable with objects of a subclass without affecting correctness.

### Violation:
```kotlin
open class Bird {
    open fun fly() {
        println("Flying")
    }
}

class Penguin : Bird() {
    override fun fly() {
        throw UnsupportedOperationException("Penguins can't fly")
    }
}
```
`Penguin` violates LSP because it overrides `fly()` in a way that breaks expected behavior.

### Ratification:
```kotlin
open class Bird

open class FlyingBird : Bird() {
    open fun fly() {
        println("Flying")
    }
}

class Sparrow : FlyingBird()

class Penguin : Bird()
```
By separating `FlyingBird` from `Bird`, we prevent incorrect overrides.

---
## 4. Interface Segregation Principle (ISP)
**Definition:** A class should not be forced to implement interfaces it does not use.

### Violation:
```kotlin
interface Worker {
    fun work()
    fun eat()
}

class Robot : Worker {
    override fun work() {
        println("Working...")
    }

    override fun eat() {
        throw UnsupportedOperationException("Robots don't eat")
    }
}
```
`Robot` is forced to implement `eat()`, which is not applicable.

### Ratification:
```kotlin
interface Workable {
    fun work()
}

interface Eatable {
    fun eat()
}

class Human : Workable, Eatable {
    override fun work() {
        println("Working...")
    }
    override fun eat() {
        println("Eating...")
    }
}

class Robot : Workable {
    override fun work() {
        println("Working...")
    }
}
```
Now, interfaces are separated appropriately.

---
## 5. Dependency Inversion Principle (DIP)
**Definition:** High-level modules should not depend on low-level modules. Both should depend on abstractions.

### Violation:
```kotlin
class MySQLDatabase {
    fun connect() {
        println("Connected to MySQL")
    }
}

class Application {
    private val database = MySQLDatabase()
    
    fun start() {
        database.connect()
    }
}
```
`Application` is tightly coupled to `MySQLDatabase`, making it hard to switch databases.

### Ratification:
```kotlin
interface Database {
    fun connect()
}

class MySQLDatabase : Database {
    override fun connect() {
        println("Connected to MySQL")
    }
}

class Application(private val database: Database) {
    fun start() {
        database.connect()
    }
}
```
Now, `Application` depends on an abstraction (`Database`), allowing flexibility.

---
Following SOLID principles ensures a scalable, maintainable, and flexible codebase.

