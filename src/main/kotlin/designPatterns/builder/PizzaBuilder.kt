package designPatterns.builder

internal interface PizzaBuilder {
    fun buildDough()
    fun buildSauce()
    fun buildTopping()
    val pizza: Pizza?
}