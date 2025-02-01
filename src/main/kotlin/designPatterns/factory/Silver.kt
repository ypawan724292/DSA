package designPatterns.factory

class Silver(var dataBase: DataBase) : Subscription {
    override fun subscriptionType(): String {
        return this.javaClass.getName()
    }

    override fun addSubscription(customer: Customer): Boolean {
        println("Customer Added to the Silver List")
        dataBase.addToDataBase(customer.customerId, "Silver")
        return true
    }

    override fun removeSubscription(customer: Customer): Boolean {
        dataBase.removeFromDataBase(customer.customerId)
        println("Customer removed to the Silver List")
        return true
    }

    override fun updateSubscription(customer: Customer): Boolean {
        dataBase.updateDataBase(customer.customerId, "Silver")
        println("Customer Updated to the Silver List")
        return true
    }
}
