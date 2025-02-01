# Object-Oriented Design (OOD) in Kotlin

Object-Oriented Design (OOD) is a methodology for designing software using objects and classes. Below are the key OOD concepts with examples in Kotlin.

## 1. Encapsulation
**Definition:** Encapsulation is the bundling of data (variables) and methods that operate on the data into a single unit (class) while restricting direct access to some details.

### Example:
```kotlin
class BankAccount(private var balance: Double) {
    fun deposit(amount: Double) {
        if (amount > 0) balance += amount
    }
    
    fun withdraw(amount: Double) {
        if (amount > 0 && amount <= balance) balance -= amount
    }
    
    fun getBalance(): Double {
        return balance
    }
}
```
Here, the balance variable is private, ensuring it cannot be accessed directly.

---
## 2. Inheritance
**Definition:** Inheritance allows a class (child) to acquire the properties and methods of another class (parent).

### Example:
```kotlin
open class Animal {
    open fun makeSound() {
        println("Animal makes a sound")
    }
}

class Dog : Animal() {
    override fun makeSound() {
        println("Bark")
    }
}
```
Here, `Dog` inherits from `Animal` and overrides the `makeSound` method.

---
## 3. Polymorphism
**Definition:** Polymorphism allows objects of different classes to be treated as objects of a common superclass.

### Example:
```kotlin
open class Shape {
    open fun draw() {
        println("Drawing a shape")
    }
}

class Circle : Shape() {
    override fun draw() {
        println("Drawing a Circle")
    }
}

class Square : Shape() {
    override fun draw() {
        println("Drawing a Square")
    }
}

fun renderShape(shape: Shape) {
    shape.draw()
}

fun main() {
    val circle = Circle()
    val square = Square()
    
    renderShape(circle) // Output: Drawing a Circle
    renderShape(square) // Output: Drawing a Square
}
```
Here, `Circle` and `Square` override `draw`, allowing different behaviors using a common interface.

---
## 4. Abstraction
**Definition:** Abstraction hides implementation details and only exposes the necessary functionalities.

### Example:
```kotlin
abstract class Vehicle {
    abstract fun startEngine()
}

class Car : Vehicle() {
    override fun startEngine() {
        println("Car engine started")
    }
}
```
Here, `Vehicle` defines an abstract method, which is implemented by `Car`.

---
## 5. Composition
**Definition:** Composition is a design principle where a class contains an instance of another class rather than inheriting from it.

### Example:
```kotlin
class Engine {
    fun start() {
        println("Engine started")
    }
}

class Car(private val engine: Engine) {
    fun startCar() {
        engine.start()
    }
}
```
Here, `Car` has an instance of `Engine` instead of inheriting from it.

---
By following Object-Oriented Design principles, we can create modular, scalable, and maintainable software systems in Kotlin.

