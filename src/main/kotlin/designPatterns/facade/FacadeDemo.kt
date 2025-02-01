package designPatterns.facade

object FacadeDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val computer = ComputerFacade()
        computer.start()
    }
}