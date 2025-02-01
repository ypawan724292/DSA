package designPatterns.state

internal class NoMoneyState : State {
    override fun insertDollar(context: VendingMachine) {
        println("Dollar inserted")
        context.setState(HasMoneyState())
    }

    override fun ejectMoney(context: VendingMachine) {
        println("No money to return")
    }

    override fun dispense(context: VendingMachine) {
        println("Payment required")
    }
}