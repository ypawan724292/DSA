package designPatterns.decorator

internal class Sugar(coffee: Coffee?) : CoffeeDecorator(coffee) {
    override fun getCost(): Double {
        return super.getCost() + 0.2
    }

    override fun getDescription(): String {
        return super.getDescription() + ", sugar"
    }
}