package designPatterns.builder

internal class SpicyPizzaBuilder : PizzaBuilder {
    private val pizza: Pizza

    init {
        this.pizza = Pizza()
    }

    override fun buildDough() {
        pizza.setDough("pan baked")
    }

    override fun buildSauce() {
        pizza.setSauce("hot")
    }

    override fun buildTopping() {
        pizza.setTopping("pepperoni and jalapeno")
    }

    override fun getPizza(): Pizza {
        return this.pizza
    }
}