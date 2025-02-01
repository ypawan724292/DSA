package designPatterns.factory

class Gold(var dataBase: DataBase) : Subscription {
    override fun subscriptionType(): String {
        return this.javaClass.getName()
    }

    override fun addSubscription(customer: Customer): Boolean {
        println("Customer" + customer.customerId + "Added to the Gold List")
        dataBase.addToDataBase(customer.customerId, "Gold")
        return true
    }

    override fun removeSubscription(customer: Customer): Boolean {
        dataBase.removeFromDataBase(customer.customerId)
        println("Customer " + customer.customerId + " removed to the Gold List")
        return true
    }

    override fun updateSubscription(customer: Customer): Boolean {
        dataBase.updateDataBase(customer.customerId, "Gold")
        println("Customer " + customer.customerId + " Updated to the Gold List")
        return true
    }
}
