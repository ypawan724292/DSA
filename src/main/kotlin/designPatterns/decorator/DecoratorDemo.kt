package designPatterns.decorator


/**
 * The Decorator Design Pattern is a structural design pattern that allows behavior to be added to individual objects,
 * either statically or dynamically, without affecting the behavior of other objects from the same class.
 * It involves a set of decorator classes that are used to wrap concrete components.
 * Key Components:
 * Component: The interface or abstract class defining the methods that will be implemented.
 * Concrete Component: The class that implements the Component interface.
 * Decorator: An abstract class that implements the Component interface and contains a reference to a Component object.
 * Concrete Decorators: Classes that extend the Decorator class and add responsibilities to the component.
 */
object DecoratorDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        // Order a simple coffee
        var coffee: Coffee = SimpleCoffee()
        println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription())

        // Decorate it with milk
        coffee = Milk(coffee)
        println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription())

        // Decorate it with sugar
        coffee = Sugar(coffee)
        println("Cost: $" + coffee.getCost() + "; Description: " + coffee.getDescription())
    }
}