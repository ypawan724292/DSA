package designPatterns.builder

internal class HawaiianPizzaBuilder : PizzaBuilder {
    override val pizza: Pizza = Pizza()

    override fun buildDough() {
        pizza.setDough("cross")
    }

    override fun buildSauce() {
        pizza.setSauce("mild")
    }

    override fun buildTopping() {
        pizza.setTopping("ham and pineapple")
    }
}
