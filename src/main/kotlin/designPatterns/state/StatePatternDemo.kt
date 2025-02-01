package designPatterns.state

/**
 * The State Design Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes. This pattern is used to encapsulate the state-based behavior in separate classes and delegate the behavior to the current state object.
 * Key Components:
 * State: An interface or abstract class that defines the behavior associated with a particular state.
 * Concrete States: Classes that implement the State interface and define the behavior for specific states.
 * Context: The class that maintains an instance of a Concrete State subclass and delegates state-specific behavior to the current state object.
 */
object StatePatternDemo {
    @JvmStatic
    fun main(args: Array<String>) {
        val vendingMachine = VendingMachine()

        vendingMachine.insertDollar()
        vendingMachine.dispense()

        println("----")

        vendingMachine.insertDollar()
        vendingMachine.insertDollar()
        vendingMachine.ejectMoney()
        vendingMachine.dispense()

        println("----")

        vendingMachine.insertDollar()
        vendingMachine.dispense()
        vendingMachine.ejectMoney()
    }
}