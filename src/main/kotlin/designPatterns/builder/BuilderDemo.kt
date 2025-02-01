package designPatterns.builder

object BuilderDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val waiter = Waiter()
        val hawaiianPizzaBuilder: PizzaBuilder = HawaiianPizzaBuilder()
        val spicyPizzaBuilder: PizzaBuilder = SpicyPizzaBuilder()

        waiter.setPizzaBuilder(hawaiianPizzaBuilder)
        waiter.constructPizza()
        val hawaiianPizza = waiter.getPizza()
        println(hawaiianPizza)

        waiter.setPizzaBuilder(spicyPizzaBuilder)
        waiter.constructPizza()
        val spicyPizza = waiter.getPizza()
        println(spicyPizza)
    }
}