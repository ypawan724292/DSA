package designPatterns.singleton


internal class StaticBlockSingleton {
    // Public method to get the instance
    // The single instance
    companion object {
        // Static block for initialization
        var instance: StaticBlockSingleton? = null

        init {
            try {
                instance = StaticBlockSingleton()
            } catch (e: Exception) {
                throw RuntimeException("Exception occurred in creating singleton instance")
            }
        }
    }


}