package designPatterns.facade

internal class CPU {
    fun freeze() {
        println("CPU: Freezing processor.")
    }

    fun jump(position: Long) {
        println("CPU: Jumping to position " + position)
    }

    fun execute() {
        println("CPU: Executing instructions.")
    }
}