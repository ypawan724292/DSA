package designPatterns.singleton

import designPatterns.singleton.StaticBlockSingleton

internal object StaticBlockSingleton {
    // Public method to get the instance
    // The single instance
    val instance: StaticBlockSingleton? = null

    // Static block for initialization
    init {
        try {
            instance = StaticBlockSingleton()
        } catch (e: Exception) {
            throw RuntimeException("Exception occurred in creating singleton instance")
        }
    }
}