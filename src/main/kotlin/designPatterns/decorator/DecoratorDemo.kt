package designPatterns.decorator

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