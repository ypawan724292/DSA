package designPatterns.singleton

internal object BillPughSingleton {
    // Static inner class that holds the instance
    private object SingletonHelper {
        val instance: BillPughSingleton = BillPughSingleton()
            // Public method to get the instance
            get() = SingletonHelper.field
    }
}