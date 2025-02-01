package designPatterns.facade

internal class HardDrive {
    fun read(lba: Long, size: Int): ByteArray? {
        println("HardDrive: Reading data from sector " + lba + " with size " + size)
        return ByteArray(size)
    }
}