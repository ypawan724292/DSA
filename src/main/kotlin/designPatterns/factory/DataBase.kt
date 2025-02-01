package designPatterns.factory

class DataBase {
    private val customerList: HashMap<String?, String?>

    init {
        customerList = HashMap<String?, String?>()
    }

    fun addToDataBase(customerId: String?, subscriptionType: String?) {
        if (!customerList.containsKey(customerId)) customerList.put(customerId, subscriptionType)
        println("Successfull Added to the DB")
    }

    fun removeFromDataBase(customerId: String?) {
        if (customerList.containsKey(customerId)) customerList.remove(customerId)
        println("Successfully removed from the DB")
    }

    fun updateDataBase(customerId: String?, subscriptionType: String?) {
        if (!customerList.containsKey(customerId)) println("Record not found")
        else {
            customerList.put(customerId, subscriptionType)
            println("Sucessfully update the DB")
        }
    }
}
