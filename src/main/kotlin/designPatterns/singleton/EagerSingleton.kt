package designPatterns.singleton

import designPatterns.singleton.EagerSingleton

internal object EagerSingleton {
    // Public method to get the instance
    // The single instance, created immediately
    val instance: EagerSingleton = EagerSingleton()
}