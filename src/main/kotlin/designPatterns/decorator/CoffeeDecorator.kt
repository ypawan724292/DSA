package designPatterns.decorator

internal abstract class CoffeeDecorator(protected var decoratedCoffee: Coffee) : Coffee {
    override fun getCost(): Double {
        return decoratedCoffee.getCost()
    }

    override fun getDescription(): String? {
        return decoratedCoffee.getDescription()
    }
}