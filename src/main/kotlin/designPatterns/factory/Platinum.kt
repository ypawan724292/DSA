package designPatterns.factory

class Platinum(var dataBase: DataBase) : Subscription {
    override fun subscriptionType(): String {
        return this.javaClass.getName()
    }

    override fun addSubscription(customer: Customer): Boolean {
        println("Customer Added to the Platinum List")
        dataBase.addToDataBase(customer.customerId, "Platinum")
        return true
    }

    override fun removeSubscription(customer: Customer): Boolean {
        dataBase.removeFromDataBase(customer.customerId)
        println("Customer removed to the Platinum List")
        return true
    }

    override fun updateSubscription(customer: Customer): Boolean {
        dataBase.updateDataBase(customer.customerId, "Platinum")
        println("Customer Updated to the Platinum List")
        return true
    }
}
