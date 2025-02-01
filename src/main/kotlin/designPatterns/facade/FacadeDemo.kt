package designPatterns.facade

/**
 * The Facade Design Pattern is a structural design pattern that provides a simplified interface to a complex subsystem.
 * It defines a higher-level interface that makes the subsystem easier to use.
 * Key Components:
 * Facade: Provides a simplified interface to the complex subsystem.
 * Subsystem Classes: Implement the subsystem functionality. They handle the work assigned by the Facade object.
 */
object FacadeDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val computer = ComputerFacade()
        computer.start()
    }
}