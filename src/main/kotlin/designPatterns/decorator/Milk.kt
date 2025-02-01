package designPatterns.decorator

internal class Milk(coffee: Coffee?) : CoffeeDecorator(coffee) {
    override fun getCost(): Double {
        return super.getCost() + 0.5
    }

    override fun getDescription(): String {
        return super.getDescription() + ", milk"
    }
}