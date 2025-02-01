package designPatterns.factory

object Application {
    var dataBase: DataBase? = null

    @JvmStatic
    fun main(arg: Array<String>) {
        dataBase = DataBase()
        val customer1 = Customer("1")
        val customer2 = Customer("2")
        val customer3 = Customer("3")

        customer1.SubscribePlan(Gold(dataBase))
        customer2.SubscribePlan(Platinum(dataBase))
        customer1.updateSubcribePlan(RoyalGold(dataBase))
        customer2.unSubscribePlan()
    }
}
