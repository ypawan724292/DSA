package designPatterns.factory

class RoyalGold(var dataBase: DataBase) : Subscription {
    override fun subscriptionType(): String {
        return this.javaClass.getName()
    }

    override fun addSubscription(customer: Customer): Boolean {
        println("Customer Added to the RoyalGold List")
        dataBase.addToDataBase(customer.customerId, "RoyalGold")
        return true
    }

    override fun removeSubscription(customer: Customer): Boolean {
        dataBase.removeFromDataBase(customer.customerId)
        println("Customer removed to the RoyalGold List")
        return true
    }

    override fun updateSubscription(customer: Customer): Boolean {
        dataBase.updateDataBase(customer.customerId, "RoyalGold")
        println("Customer Updated to the RoyalGold List")
        return true
    }
}
