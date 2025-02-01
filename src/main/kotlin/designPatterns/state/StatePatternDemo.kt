package designPatterns.state

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