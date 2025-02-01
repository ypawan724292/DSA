package designPatterns.factory

/**
 * The Factory Design Pattern is a creational design pattern that provides an interface for creating objects in a superclass,
 * but allows subclasses to alter the type of objects that will be created.
 * Key Components:
 * Product: Defines the interface of objects the factory method creates.
 * Concrete Product: Implements the Product interface.
 * Creator: Declares the factory method, which returns an object of type Product.
 * Concrete Creator: Overrides the factory method to return an instance of a Concrete Product.
 */
object ApplicationDemo {

    @JvmStatic
    fun main(arg: Array<String>) {

        val emailNotification = NotificationFactory.createNotification("email")
        emailNotification.notifyUser()

        val smsNotification = NotificationFactory.createNotification("sms")
        smsNotification.notifyUser()
    }
}
