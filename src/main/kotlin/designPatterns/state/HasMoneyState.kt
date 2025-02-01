package designPatterns.state

internal class HasMoneyState : State {
    override fun insertDollar(context: VendingMachine?) {
        println("Already have a dollar")
    }

    override fun ejectMoney(context: VendingMachine) {
        println("Returning dollar")
        context.setState(NoMoneyState())
    }

    override fun dispense(context: VendingMachine) {
        println("Releasing product")
        context.setState(NoMoneyState())
    }
}