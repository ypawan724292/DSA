package designPatterns.singleton


internal class EagerSingleton {
    // Public method to get the instance
    // The single instance, created immediately
    companion object {
        val instance: EagerSingleton = EagerSingleton()
    }
}