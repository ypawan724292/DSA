package designPatterns.facade

internal class Memory {
    fun load(position: Long, data: ByteArray?) {
        println("Memory: Loading data at position " + position)
    }
}