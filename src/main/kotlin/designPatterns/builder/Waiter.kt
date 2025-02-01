package designPatterns.builder

internal class Waiter {
    private var pizzaBuilder: PizzaBuilder? = null

    fun setPizzaBuilder(pb: PizzaBuilder) {
        pizzaBuilder = pb
    }

    val pizza: Pizza?
        get() = pizzaBuilder!!.pizza

    fun constructPizza() {
        pizzaBuilder!!.buildDough()
        pizzaBuilder!!.buildSauce()
        pizzaBuilder!!.buildTopping()
    }
}