package designPatterns.decorator

internal class SimpleCoffee : Coffee {
    override fun getCost(): Double {
        return 1.0
    }

    override fun getDescription(): String {
        return "Simple coffee"
    }
}