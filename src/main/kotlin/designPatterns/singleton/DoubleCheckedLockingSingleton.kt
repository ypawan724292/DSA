package designPatterns.singleton


internal class DoubleCheckedSingleton {


    companion object {
        // The single instance, initially null, marked as volatile
        @Volatile
        var instance: DoubleCheckedSingleton? = null
            // Public method to get the instance
            get() {
                // First check (not synchronized)
                if (field == null) {
                    // Synchronize on the class object
                    synchronized(DoubleCheckedSingleton::class.java) {
                        // Second check (synchronized)
                        if (field == null) {
                            // Create the instance
                            field = DoubleCheckedSingleton()
                        }
                    }
                }
                // Return the instance (either newly created or existing)
                return field
            }
            private set

    }
}