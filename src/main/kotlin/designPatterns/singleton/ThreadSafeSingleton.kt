package designPatterns.singleton

/**
 * The Singleton Design Pattern is a creational design pattern that ensures a class has only one instance and provides a global point of access to it.
 * Key Components:
 * Singleton Class: The class that is responsible for ensuring that only one instance of itself is created and provides a global point of access to that instance.
 */

internal class ThreadSafeSingleton {
    // The single instance, initially null

    companion object {
        @get:Synchronized
        var instance: ThreadSafeSingleton? = null
            // Public method to get the instance, with synchronized keyword
            get() {
                // Check if instance is null
                if (field == null) {
                    // If null, create a new instance
                    field = ThreadSafeSingleton()
                }
                // Return the instance (either newly created or existing)
                return field
            }
            private set
    }

}