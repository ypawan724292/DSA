package designPatterns.builder

/**
 * The Builder Design Pattern is a creational design pattern that allows for the step-by-step construction of complex objects.
 * It separates the construction of a complex object from its representation, allowing the same construction process to create
 * different representations.
 *
 * Key Components:
 * Builder: Specifies an abstract interface for creating parts of a Product object.
 * Concrete Builder: Implements the Builder interface and constructs and assembles parts of the product.
 * Product: Represents the complex object under construction.
 * Director: Constructs an object using the Builder interface.
 */
object BuilderDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val waiter = Waiter()
        val hawaiianPizzaBuilder: PizzaBuilder = HawaiianPizzaBuilder()
        val spicyPizzaBuilder: PizzaBuilder = SpicyPizzaBuilder()

        waiter.setPizzaBuilder(hawaiianPizzaBuilder)
        waiter.constructPizza()
        val hawaiianPizza = waiter.pizza
        println(hawaiianPizza)

        waiter.setPizzaBuilder(spicyPizzaBuilder)
        waiter.constructPizza()
        val spicyPizza = waiter.pizza
        println(spicyPizza)
    }
}