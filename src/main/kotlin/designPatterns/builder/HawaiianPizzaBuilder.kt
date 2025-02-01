package designPatterns.builder

internal class HawaiianPizzaBuilder : PizzaBuilder {
    private val pizza: Pizza

    init {
        this.pizza = Pizza()
    }

    override fun buildDough() {
        pizza.setDough("cross")
    }

    override fun buildSauce() {
        pizza.setSauce("mild")
    }

    override fun buildTopping() {
        pizza.setTopping("ham and pineapple")
    }

    override fun getPizza(): Pizza {
        return this.pizza
    }
}