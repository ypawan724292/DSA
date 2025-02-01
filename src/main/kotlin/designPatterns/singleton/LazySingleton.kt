package designPatterns.singleton

import designPatterns.singleton.LazySingleton

internal class LazySingleton {
    // The single instance, initially null
    companion object {
        var instance: LazySingleton? = null
            // Public method to get the instance
            get() {
                // Check if instance is null
                if (field == null) {
                    // If null, create a new instance
                    field = LazySingleton()
                }
                // Return the instance (either newly created or existing)
                return field
            }
            private set
    }
}