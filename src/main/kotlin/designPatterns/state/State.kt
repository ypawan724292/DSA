package designPatterns.state

internal interface State {
    fun insertDollar(context: VendingMachine?)
    fun ejectMoney(context: VendingMachine?)
    fun dispense(context: VendingMachine?)
}
