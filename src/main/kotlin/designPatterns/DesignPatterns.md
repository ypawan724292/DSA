# Important Design Patterns in Kotlin

Design patterns are reusable solutions to common software design problems. Below are some important design patterns with Kotlin examples.

## 1. Singleton Pattern
**Definition:** Ensures that a class has only one instance and provides a global access point.

### Example:
```kotlin
object Singleton {
    fun showMessage() {
        println("This is a singleton instance")
    }
}

fun main() {
    Singleton.showMessage()
}
```

---
## 2. Factory Pattern
**Definition:** Provides an interface for creating objects in a superclass but allows subclasses to alter the type of objects that will be created.

### Example:
```kotlin
interface Vehicle {
    fun drive()
}

class Car : Vehicle {
    override fun drive() {
        println("Driving a car")
    }
}

class Bike : Vehicle {
    override fun drive() {
        println("Riding a bike")
    }
}

class VehicleFactory {
    fun createVehicle(type: String): Vehicle {
        return when (type) {
            "Car" -> Car()
            "Bike" -> Bike()
            else -> throw IllegalArgumentException("Unknown vehicle type")
        }
    }
}

fun main() {
    val factory = VehicleFactory()
    val car = factory.createVehicle("Car")
    car.drive()
}
```

---
## 3. Builder Pattern
**Definition:** Allows step-by-step object creation, useful when an object has many optional parameters.

### Example:
```kotlin
class Car private constructor(
    val brand: String?,
    val model: String?,
    val year: Int?
) {
    class Builder {
        private var brand: String? = null
        private var model: String? = null
        private var year: Int? = null

        fun setBrand(brand: String) = apply { this.brand = brand }
        fun setModel(model: String) = apply { this.model = model }
        fun setYear(year: Int) = apply { this.year = year }

        fun build() = Car(brand, model, year)
    }
}

fun main() {
    val car = Car.Builder()
        .setBrand("Toyota")
        .setModel("Corolla")
        .setYear(2022)
        .build()
    println("Car: ${car.brand} ${car.model} ${car.year}")
}
```

---
## 4. Observer Pattern
**Definition:** Defines a one-to-many dependency between objects, so when one object changes state, all its dependents are notified.

### Example:
```kotlin
interface Observer {
    fun update(message: String)
}

class User(val name: String) : Observer {
    override fun update(message: String) {
        println("$name received notification: $message")
    }
}

class NotificationService {
    private val observers = mutableListOf<Observer>()

    fun subscribe(observer: Observer) {
        observers.add(observer)
    }

    fun unsubscribe(observer: Observer) {
        observers.remove(observer)
    }

    fun notifyObservers(message: String) {
        observers.forEach { it.update(message) }
    }
}

fun main() {
    val user1 = User("Alice")
    val user2 = User("Bob")
    
    val notificationService = NotificationService()
    notificationService.subscribe(user1)
    notificationService.subscribe(user2)
    
    notificationService.notifyObservers("New message available!")
}
```

---
## 5. Strategy Pattern
**Definition:** Defines a family of algorithms, encapsulates each one, and makes them interchangeable.

### Example:
```kotlin
interface PaymentStrategy {
    fun pay(amount: Double)
}

class CreditCardPayment : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Paid $$amount using Credit Card")
    }
}

class PayPalPayment : PaymentStrategy {
    override fun pay(amount: Double) {
        println("Paid $$amount using PayPal")
    }
}

class ShoppingCart(private var paymentStrategy: PaymentStrategy) {
    fun setPaymentStrategy(strategy: PaymentStrategy) {
        this.paymentStrategy = strategy
    }

    fun checkout(amount: Double) {
        paymentStrategy.pay(amount)
    }
}

fun main() {
    val cart = ShoppingCart(CreditCardPayment())
    cart.checkout(100.0)
    
    cart.setPaymentStrategy(PayPalPayment())
    cart.checkout(50.0)
}
```

---
## 6. Flyweight Pattern
**Definition:** Minimizes memory usage by sharing objects instead of creating new ones.

### Example:
```kotlin
class Tree(val type: String)

object TreeFactory {
    private val treeMap = mutableMapOf<String, Tree>()
    
    fun getTree(type: String): Tree {
        return treeMap.getOrPut(type) { Tree(type) }
    }
}

fun main() {
    val oak1 = TreeFactory.getTree("Oak")
    val oak2 = TreeFactory.getTree("Oak")
    println(oak1 === oak2) // true
}
```

---
## 7. Adapter Pattern
**Definition:** Allows incompatible interfaces to work together.

### Example:
```kotlin
interface USB {
    fun connectWithUsbCable()
}

class USBDevice : USB {
    override fun connectWithUsbCable() {
        println("Connected via USB")
    }
}

class TypeCAdapter(private val usbDevice: USB) {
    fun connectWithTypeC() {
        usbDevice.connectWithUsbCable()
        println("Adapted to Type-C")
    }
}

fun main() {
    val usbDevice = USBDevice()
    val adapter = TypeCAdapter(usbDevice)
    adapter.connectWithTypeC()
}
```

---
## 8. Facade Pattern
**Definition:** Provides a simplified interface to a complex system.

### Example:
```kotlin
class CPU {
    fun start() = println("CPU started")
}

class Memory {
    fun load() = println("Memory loaded")
}

class HardDrive {
    fun read() = println("Hard drive read")
}

class ComputerFacade {
    private val cpu = CPU()
    private val memory = Memory()
    private val hardDrive = HardDrive()
    
    fun startComputer() {
        cpu.start()
        memory.load()
        hardDrive.read()
    }
}

fun main() {
    val computer = ComputerFacade()
    computer.startComputer()
}
```

---
These are some of the most commonly used design patterns in Kotlin, helping to create scalable, maintainable, and flexible applications.

