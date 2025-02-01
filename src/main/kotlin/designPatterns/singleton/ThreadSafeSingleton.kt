package designPatterns.singleton

import designPatterns.singleton.ThreadSafeSingleton

internal object ThreadSafeSingleton {
    // The single instance, initially null
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